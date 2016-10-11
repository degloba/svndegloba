package com.degloba.domain;

import com.degloba.utils.DateUtils;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;



// Entitats de domini 
import com.degloba.organisation.domain.persistence.rdbms.jpa.Accountability;
import com.degloba.organisation.domain.persistence.rdbms.jpa.Company;
import com.degloba.organisation.domain.persistence.rdbms.jpa.CompanyDepartment;
import com.degloba.organisation.domain.persistence.rdbms.jpa.Department;
import com.degloba.organisation.domain.persistence.rdbms.jpa.Employee;
import com.degloba.organisation.domain.persistence.rdbms.jpa.Employment;
import com.degloba.organisation.domain.persistence.rdbms.jpa.OrgLineMgmt;
import com.degloba.organisation.domain.persistence.rdbms.jpa.Person;
import com.degloba.organisation.utils.OrganisationUtils;




import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class AccountabilityTest extends AbstractIntegrationTest {

    //@Transactional
    @Test
    public final void testFindAccountabilities() {
        OrganisationUtils organisationUtils = new OrganisationUtils();
        Date date = DateUtils.date(2012, 1, 3);
        
        Company company = organisationUtils.createCompany("Oficina central", date);
                
        Department financial = organisationUtils.createDepartment("Departament de finances", company, date);
        
        CompanyDepartment companyDepartment = organisationUtils.createCompanyDepartment("Oficina central", date);
        CompanyDepartment companyDepartment2 = organisationUtils.createCompanyDepartment("Departament de finances",  date);
       new OrgLineMgmt(companyDepartment, companyDepartment2, date).save();
          
        Person person = organisationUtils.createPerson("Martin", "Fowler");
        Employee employee = organisationUtils.createEmployee(person, date);
        Employment employment = new Employment(company, employee, date);
        employment.save();
        List<Accountability> results = Accountability.findAccountabilities(Accountability.class, date);
        // Troba totes les subclasses.
        OrgLineMgmt lineMgmt = OrgLineMgmt.getByResponsible(companyDepartment2, date);
        assertTrue(results.contains(lineMgmt));
        assertTrue(results.contains(employment));  
    }

}