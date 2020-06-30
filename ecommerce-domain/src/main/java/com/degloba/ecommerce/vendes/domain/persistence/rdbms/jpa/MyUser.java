package com.degloba.ecommerce.vendes.domain.persistence.rdbms.jpa;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// used for Querydsl test

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MyUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private int age;

    public MyUser(final String firstName, final String lastName, final String email, final int age) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.age = age;
    }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final MyUser user = (MyUser) obj;
        if (!email.equals(user.email))
            return false;
        return true;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("MyUser [firstName=").append(firstName).append("]").append("[lastName=").append(lastName).append("]").append("[username").append(email).append("]");
        return builder.toString();
    }

}
