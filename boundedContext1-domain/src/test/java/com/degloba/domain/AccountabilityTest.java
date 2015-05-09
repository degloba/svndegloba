package com.degloba.domain;

import org.dayatang.utils.DateUtils;
import org.junit.Test;



import org.springframework.transaction.annotation.Transactional;

import com.degloba.organisation.domain.Accountability;
import com.degloba.organisation.domain.Company;
import com.degloba.organisation.domain.CompanyDepartment;
import com.degloba.organisation.domain.Department;
import com.degloba.organisation.domain.Employee;
import com.degloba.organisation.domain.Employment;
import com.degloba.organisation.domain.OrgLineMgmt;
import com.degloba.organisation.domain.Person;
import com.degloba.organisation.utils.OrganisationUtils;
import com.degloba.organisation.domain.demo.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class AccountabilityTest extends AbstractIntegrationTest {

    //@Transactional
    @Test
    public final void testFindAccountabilities() {
        OrganisationUtils organisationUtils = new OrganisationUtils();
        Date date = DateUtils.date(2012, 1, 3);
        
/*        Company company = organisationUtils.createCompany("总公司", date);
        Department financial = organisationUtils.createDepartment("财务部", company, date);*/
        
        CompanyDepartment companyDepartment = organisationUtils.createCompanyDepartment("总公司", date);
        CompanyDepartment companyDepartment2 = organisationUtils.createCompanyDepartment("财务部",  date);
       //new OrgLineMgmt(companyDepartment, companyDepartment2, date).save();
        
       
       // https://code.google.com/p/datanucleus-appengine/source/browse/
       Concrete1 c1 = new Concrete1();
       
       c1.setBase1Str("c1 base");
       c1.setConcrete1Str("concrete1Str");
       
       Concrete3 c3 = new Concrete3();
       c3.setStr("concrete3");
       
       c1.setConcrete3(c3);
       
       Concrete4 c4 = new Concrete4();
       c4.setStr("concrete4");
       
       List<Concrete4> lstConcrete4= new ArrayList<Concrete4>();
       lstConcrete4.add(c4);
       
       c1.setConcrete4(lstConcrete4);
       
       c1.save();
       
       Concrete1 c = c1.getConcrete1(c1);
       
       String a = "jjj";
       /* Person person = organisationUtils.createPerson("Martin", "Fowler");
        Employee employee = organisationUtils.createEmployee(person, date);
        Employment employment = new Employment(company, employee, date);
        employment.save();
        List<Accountability> results = Accountability.findAccountabilities(Accountability.class, date);
        // 断言找到所有的子类实例。
        OrgLineMgmt lineMgmt = OrgLineMgmt.getByResponsible(companyDepartment2, date);
        assertTrue(results.contains(lineMgmt));
        assertTrue(results.contains(employment));  */
    }

}