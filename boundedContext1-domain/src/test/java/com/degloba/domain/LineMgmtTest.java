package com.degloba.domain;

import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Test;
import com.degloba.utils.DateUtils;
import com.degloba.organisation.utils.OrganisationUtils;
import com.degloba.organitzacio.domain.persistence.rdbms.jpa.Company;
import com.degloba.organitzacio.domain.persistence.rdbms.jpa.Department;
import com.degloba.organitzacio.domain.persistence.rdbms.jpa.OrgLineMgmt;

public class LineMgmtTest extends AbstractIntegrationTest {

	@Test
	public final void testFindResponsiblesOfOrganization() {
		OrganisationUtils organisationUtils = new OrganisationUtils();
		Date date = DateUtils.date(2012, 1, 3);
		Company company = organisationUtils.createCompany("Oficina central", date);
		Department financial = organisationUtils.createDepartment("Departament De Finances", date);
		//OrgLineMgmt lineMgmt = new OrgLineMgmt(company, financial, date);
		//lineMgmt.save();
		System.out.println(OrgLineMgmt.findAll().size());
		//assertTrue(OrgLineMgmt.findAll().contains(lineMgmt));
		assertTrue(OrgLineMgmt.findChildrenOfOrganization(company, date).contains(financial));
	}

}
