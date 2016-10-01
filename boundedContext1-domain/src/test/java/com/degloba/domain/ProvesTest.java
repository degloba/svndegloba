package com.degloba.domain;

import com.degloba.utils.DateUtils;
import org.junit.Test;

import com.degloba.domain.persistence.rdbms.jpa.IEntityRepository;

// Entitats de domini 

import com.degloba.organisation.domain.Company;

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
        IEntityRepository repository = InstanceFactory.getInstance(IEntityRepository.class);
        
        // 3.-
        List<Company> companies = repository.findAll(Company.class);
        Company compania = repository.get(Company.class,company.getId());
        
        assertEquals(company,compania); 
        assertEquals(company,companies.get(0)); 
       
    }

}