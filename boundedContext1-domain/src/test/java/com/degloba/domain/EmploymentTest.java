package com.degloba.domain;

import com.degloba.utils.DateUtils;
import org.junit.Test;

import com.degloba.organisation.domain.Company;
import com.degloba.organisation.domain.Employee;
import com.degloba.organisation.domain.Employment;
import com.degloba.organisation.domain.Person;
import com.degloba.organisation.utils.OrganisationUtils;

import java.util.Date;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

public class EmploymentTest extends AbstractIntegrationTest {

    @Test
    public final void testGetEmployer() {
        OrganisationUtils organisationUtils = new OrganisationUtils();
        Date date = DateUtils.date(2012, 1, 3);
        Company company = organisationUtils.createCompany("degloba", date);
        Person deglobaPersona = organisationUtils.createPerson("tres", "deglobaPersona");
        
        Employee deglobaEmp = organisationUtils.createEmployee(deglobaPersona, date);
        deglobaEmp.setPerson(deglobaPersona);
        
        deglobaEmp.save();
        
        Person li = organisationUtils.createPerson("quatre", "yahooPersona");
        Employee lisi = organisationUtils.createEmployee(li, date);
        new Employment(company, deglobaEmp, date).save();
        assertThat("a degloba hi treballa deglobaPersona",
                Employment.getEmployer(deglobaEmp, DateUtils.date(2012, 1, 3)),
                is(company));
        assertThat("a degloba no hi treballa yahooPersona",
                Employment.getEmployer(lisi, DateUtils.date(2012, 1, 3)),
                not(is(company)));
    }
}