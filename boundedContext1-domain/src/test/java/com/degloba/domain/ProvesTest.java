package com.degloba.domain;

import com.degloba.utils.DateUtils;
import org.junit.Test;

import com.degloba.ioc.spring.InstanceFactory;

// Entitats de domini 

import com.degloba.organisation.domain.persistence.rdbms.jpa.Company;
import com.degloba.organisation.domain.persistence.rdbms.jpa.IOrganisationRepository;
import com.degloba.organisation.utils.OrganisationUtils;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class ProvesTest extends AbstractIntegrationTest {

    //@Transactional
    @Test
    public final void testProves() {
        OrganisationUtils organisationUtils = new OrganisationUtils();
        Date date = DateUtils.date(2012, 1, 3);
        
        Company company = organisationUtils.createCompany("Oficina central", date);
        
        // 1.-
        company.save();
        
        // 2.-
        IOrganisationRepository repository = InstanceFactory.getInstance(IOrganisationRepository.class);
        
        // 3.-
        List<Company> companies = repository.findAll(Company.class);
        Company compania = repository.get(Company.class,company.getAggregateId());
        
        assertEquals(company,compania); 
        assertEquals(company,companies.get(0)); 
       
    }

}