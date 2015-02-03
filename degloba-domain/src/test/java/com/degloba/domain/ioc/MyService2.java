package com.degloba.domain.ioc;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.inject.Named;

@Named("service2")
public class MyService2 implements Service {

    private int id = 2;

	public String sayHello() {
		return "I am Service 2";
	}

    /**
     * @return a hash code value for this object.
     * @see Object#equals(Object)
     * @see System#identityHashCode
     */
    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 43).append(id).toHashCode();
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     * @param other the reference object with which to compare.
     * @return {@code true} if this object is the same as the obj
     * argument; {@code false} otherwise.
     * @see #hashCode()
     * @see java.util.HashMap
     */
    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof MyService2)) {
            return false;
        }
        MyService2 that = (MyService2) other;
        return new EqualsBuilder().append(this.id, that.id).isEquals();
    }
}
