/*
 * Copyright 2014 Dayatang Open Source..
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.degloba.organisation.facade.impl;

import com.degloba.organisation.domain.Party;
import com.degloba.organisation.domain.Post;
import com.degloba.organisation.facade.assembler.OrganizationAssembler;

import java.util.Date;

import javax.inject.Inject;

import com.degloba.organisation.api.OrganisationService;
import com.degloba.organisation.domain.Organization;
import com.degloba.organisation.facade.OrganisationFacade;
import com.degloba.organisation.facade.OrganizationDto;
import com.degloba.organisation.facade.PostDto;
import com.degloba.organisation.facade.assembler.PostAssembler;
import com.google.appengine.api.datastore.Key;

/**
 *
 * @author yyang
 */
public class OrganisationFacadeImpl implements OrganisationFacade {

    @Inject
    protected OrganisationService application;

    public OrganisationFacadeImpl(OrganisationService application) {
        this.application = application;
    }

    @Override
    public void createOrganization(OrganizationDto orgToCreate, Key parentOrgId, Date date) {
        Organization organization = new OrganizationAssembler().toEntity(orgToCreate);
        Organization parent = application.getEntity(Organization.class, parentOrgId);
        application.createOrganization(organization, parent, date);
    }

    @Override
    public void terminateParty(Key partyId, Date date) {
        Party party = application.getEntity(Party.class, partyId);
        application.terminateParty(party, date);
    }

    @Override
    public void changeParentOfOrganization(Key organizationId, Key newParentId, Date date) {
        Organization organization = application.getEntity(Organization.class, organizationId);
        Organization parent = application.getEntity(Organization.class, newParentId);
        application.changeParentOfOrganization(organization, parent, date);
    }

    @Override
    public void createPostUnderOrganization(PostDto postDto, Key organizationId, Date date) {
        Post post = new PostAssembler().toEntity(postDto);
        Organization organization = application.getEntity(Organization.class, organizationId);
        application.createPostUnderOrganization(post, organization, date);
    }

    @Override
    public PostDto getPost(Key postId) {
        Post post = application.getEntity(Post.class, postId);
        return new PostAssembler().toDto(post);
    }

	public OrganisationFacadeImpl() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OrganisationService getApplication() {
		return application;
	}

	public void setApplication(OrganisationService application) {
		this.application = application;
	}

	
}
