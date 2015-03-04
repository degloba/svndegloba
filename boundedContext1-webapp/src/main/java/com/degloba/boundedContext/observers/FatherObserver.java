package com.degloba.boundedContext.observers;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.degloba.observer.*;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorValue("2")
public class FatherObserver extends Observer<Baby> {

    /**
     *
     */
    private static final long serialVersionUID = 6725506578728407946L;

    private Boolean startCar = false;

    public Boolean getStartCar() {
        return startCar;
    }

    public void setStartCar(Boolean startCar) {
        this.startCar = startCar;
    }

    @Override
    public String[] businessKeys() {
        return new String[]{"startCar"};
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this,
                ToStringStyle.SHORT_PREFIX_STYLE);
    }

    @Override
    public void process(Baby baby) {
        System.out.println("å®�å®�å“­äº†ã€‚ã€‚ã€‚å�¯åŠ¨æ±½è½¦=true " + baby.getSubjectKey());
        setStartCar(true);
    }

}
