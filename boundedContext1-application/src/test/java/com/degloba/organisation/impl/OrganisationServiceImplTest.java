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

package com.degloba.organisation.impl;

import com.degloba.organisation.application.api.OrganisationService;
import com.degloba.organisation.application.impl.OrganisationApplicationImpl;
import com.degloba.organisation.impl.OrganisationServiceTest;

/**
 *
 * @author yyang
 */
public class OrganisationServiceImplTest extends OrganisationServiceTest {

    @Override
    protected OrganisationService createInstance() {
        return new OrganisationApplicationImpl(repository);
    }
 
    
}
