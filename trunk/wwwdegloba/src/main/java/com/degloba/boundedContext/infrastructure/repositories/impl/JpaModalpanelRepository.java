package com.degloba.boundedContext.infrastructure.repositories.impl;

/*
 * Copyright 2011-2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


import infrastructure.repository.jpa.GenericJpaRepository;
import domain.annotations.DomainRepositoryImpl;
import domain.canonicalmodel.publishedlanguage.AggregateId;

import com.degloba.boundedContext.domain.Modalpanel;
import com.degloba.boundedContext.domain.ModalpanelRepository;


@DomainRepositoryImpl
public class JpaModalpanelRepository extends GenericJpaRepository<Modalpanel,Long> implements ModalpanelRepository<Long>{

	@Override
	public Modalpanel load(AggregateId id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Modalpanel save(Modalpanel modalpanel) {
		return modalpanel;
		// TODO Auto-generated method stub
		
	}



}

