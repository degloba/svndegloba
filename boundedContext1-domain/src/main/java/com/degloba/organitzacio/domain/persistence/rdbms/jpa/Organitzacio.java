package com.degloba.organitzacio.domain.persistence.rdbms.jpa;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;

import org.apache.commons.lang3.builder.ToStringBuilder;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@MappedSuperclass
public abstract class Organitzacio extends Party {

    private static final long serialVersionUID = -8953682430610195006L;

    public Organitzacio() {
    }

    public Organitzacio(String name) {
        super(name);
    }

    public Organitzacio getParent(Date date) {
        return OrgLineMgmt.getParentOfOrganization(this, date);
    }

    public List<Organitzacio> getChildren(Date date) {
        return OrgLineMgmt.findChildrenOfOrganization(this, date);
    }

    public Set<Post> getPosts(Date date) {
        return new HashSet<Post>(Post.findByOrganization(this, date));
    }

    @Override
    public void terminate(Date date) {
        if (hasEmployees(date)) {
            throw new TerminateNotEmptyOrganizationException();
        }
        for (Post post : getPosts(date)) {
            post.terminate(date);
        }
        for (Organitzacio child : getChildren(date)) {
            child.terminate(date);
        }
        super.terminate(date);
    }

    private boolean hasEmployees(Date date) {
        for (Post post : getPosts(date)) {
            if (!(post.getEmployees(date).isEmpty())) {
                return true;
            }
        }
        for (Organitzacio child : getChildren(date)) {
            if (child.hasEmployees(date)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append(getName()).build();
    }

}
