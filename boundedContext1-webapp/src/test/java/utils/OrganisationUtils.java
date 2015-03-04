package utils;

import java.util.Date;

import com.degloba.organisation.domain.Company;
import com.degloba.organisation.domain.Department;
import com.degloba.organisation.domain.Employee;
import com.degloba.organisation.domain.OrgLineMgmt;
import com.degloba.organisation.domain.Organization;
import com.degloba.organisation.domain.Person;
import com.degloba.organisation.domain.Post;
import com.degloba.organisation.domain.PostHolding;

public class OrganisationUtils {

    public Company createCompany(String name, Date date) {
        Company result = new Company(name);
        result.setCreateDate(date);
        result.save();
        return result;
    }

    public Company createCompany(String name, Organization parent, Date date) {
        Company result = createCompany(name, date);
        new OrgLineMgmt(parent, result, date).save();
        return result;
    }

    public Department createDepartment(String name, Date date) {
        Department result = new Department(name);
        result.setCreateDate(date);
        result.save();
        return result;
    }

    public Department createDepartment(String name, Organization parent, Date date) {
        Department result = createDepartment(name, date);
        new OrgLineMgmt(parent, result, date).save();
        return result;
    }

    public Post createPost(String name, Organization organization, Date date) {
        Post result = new Post(name);
        result.setCreateDate(date);
        result.setOrganization(organization);
        result.save();
        return result;
    }

    public Employee createEmployee(Person person, Date date) {
        Employee result = new Employee(person);
        result.setCreateDate(date);
        result.save();
        return result;
    }

    public Employee createEmployee(Person person, Post post, Date date) {
        Employee result = createEmployee(person, date);
        new PostHolding(post, result, date).save();
        return result;
    }

    public Person createPerson(String firstName, String lastName) {
        Person result = new Person(firstName, lastName);
        result.save();
        return result;
    }
}
