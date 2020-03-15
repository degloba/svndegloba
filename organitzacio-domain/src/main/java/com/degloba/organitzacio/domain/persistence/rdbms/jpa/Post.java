package com.degloba.organitzacio.domain.persistence.rdbms.jpa;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import org.apache.commons.lang3.builder.ToStringBuilder;

@Entity
@DiscriminatorValue("Post")
@NamedQueries(
        @NamedQuery(name = "Party.findByOrganization", query = "select o from Post o where o.organization = :organization and o.createDate <= :date and o.terminateDate > :date"))
public class Post extends Party {

    private static final long serialVersionUID = -2205967098970951498L;

    @ManyToOne
//    @JoinColumn(name = "org_id")
/*    @JoinColumns(
    	    {@JoinColumn(name = "org_id", referencedColumnName = "aggregateId",
    	                 insertable = false, updatable = false)
    	     })*/
    private Organitzacio organitzacio;

    @ManyToOne
//    @JoinColumn(name = "job_id")
/*    @JoinColumns(
    	    {@JoinColumn(name = "job_Id", referencedColumnName = "aggregateId",
    	                 insertable = false, updatable = false)
    	     })*/
    private Job job;

    public Post() {
        super();
    }

    public Post(String name) {
        super(name);
    }

    public Organitzacio getOrganization() {
        return organitzacio;
    }

    public void setOrganization(Organitzacio organitzacio) {
        this.organitzacio = organitzacio;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public static List<Post> findByOrganization(Organitzacio organitzacio,
            Date date) {
        return getRepository().createNamedQuery("Party.findByOrganization")
                .addParameter("organization", organitzacio).addParameter("date", date).list();
    }

    public Set<Employee> getEmployees(Date date) {
        return new HashSet<Employee>(PostHolding.findEmployeesOfPost(this, date));
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append(getName()).build();
    }

}
