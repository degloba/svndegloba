package com.degloba.organisation.domain.persistence.rdbms.jpa;


import com.degloba.domain.persistence.rdbms.jpa.BaseAggregateRoot;
import com.degloba.domain.persistence.rdbms.jpa.BaseEntity;
import com.degloba.utils.Assert;
import com.degloba.utils.DateUtils;

import javax.persistence.*;

import java.util.Date;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
//@DiscriminatorColumn(name = "CATEGORY", discriminatorType = DiscriminatorType.STRING)
@MappedSuperclass
public abstract class Party extends BaseAggregateRoot {

    private static final long serialVersionUID = -6083088250263550905L;
    
    // Surname
    private String sn;

    // Name
    private String name;

    @Temporal(TemporalType.DATE)
    private Date createDate = new Date();

    @Temporal(TemporalType.DATE)
    private Date terminateDate = DateUtils.MAX_DATE;

    public Party() {
    }

    public Party(String name) {
        Assert.notBlank(name, "Name is blank!");
        this.name = name;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getName() {
        return name;
    }

    public final void setName(String name) {
        this.name = name;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getTerminateDate() {
        return terminateDate;
    }

    public void setTerminateDate(Date terminateDate) {
        this.terminateDate = terminateDate;
    }

    public boolean isActive(Date date) {
        return DateUtils.isSameDay(date, getCreateDate())
                || date.after(getCreateDate())
                && date.before(getTerminateDate());
    }

    @SuppressWarnings("rawtypes")
    public void terminate(Date date) {
        for (Accountability each : Accountability.findAccountabilitiesByParty(this, date)) {
            each.terminate(date);
        }
        terminateDate = date;
        save();
    }

    @Override
    public String[] businessKeys() {
        return new String[]{"sn", "name"};
    }
}
