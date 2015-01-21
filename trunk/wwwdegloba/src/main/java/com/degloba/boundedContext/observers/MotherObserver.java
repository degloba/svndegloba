package com.degloba.boundedContext.observers;


import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.degloba.observer.Observer;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorValue("1")
public class MotherObserver extends Observer<Baby> {

    /**
     *
     */
    private static final long serialVersionUID = 6725506578728407946L;

    private Boolean buyFood = false;

    public Boolean getBuyFood() {
        return buyFood;
    }

    public void setBuyFood(Boolean buyFood) {
        this.buyFood = buyFood;
    }

    @Override
    public String[] businessKeys() {
        return new String[]{"buyFood"};
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this,
                ToStringStyle.SHORT_PREFIX_STYLE);
    }

    @Override
    public void process(Baby baby) {
        System.out.println("å®�å®�å“­äº†ã€‚ã€‚ã€‚è®¾ç½®ä¹°é£Ÿç‰©=true " + baby.getSubjectKey());
        setBuyFood(true);
        save();
    }

}
