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

package com.degloba.domain.entity;

import javax.persistence.Entity;

import com.degloba.domain.AbstractEntity;
import com.degloba.domain.BaseAggregateRoot;

/**
 *
 * @author yyang
 */
@Entity
//public class MyEntity extends AbstractEntity {
public class MyEntity	extends BaseAggregateRoot{
    private String name;

    public MyEntity() {
    }

    public MyEntity(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public String[] businessKeys() {
        return new String [] {"name"};
    }

	@Override
	public Long getId() {
		// TODO Auto-generated method stub
		return null;
	}

}
