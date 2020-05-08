package com.degloba.persistence.jpa;


import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.LinkedHashMap;
import java.util.Map;


public class AliasTest {

	@BeforeEach
	public void setUp() throws Exception {
	}

	@AfterEach
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetAlias() {
		Map<String, String> map = getAlias("a.b.c.d");
		map.putAll(getAlias("a.b.c"));
		assertEquals(3, map.size());
		for (String key : map.keySet()) {
			System.out.println(key + ":" + map.get(key));
		}
	}

	@Test
	public void testGetAliasEmpty() {
		Map<String, String> map = getAlias("a");
		assertEquals(0, map.size());
	}
	
	private Map<String, String> getAlias(String propName) {
		Map<String, String> results = new LinkedHashMap<String, String>();
		if (StringUtils.isEmpty(propName)) {
			return results;
		}
		int index = propName.indexOf(".");
		while (index > 0) {
			String aliasName = propName.substring(0, index);
			int index1 = aliasName.lastIndexOf(".");
			String alias = index1 == -1 ? aliasName : aliasName.substring(index1 + 1);
			results.put(aliasName, alias);
			index = propName.indexOf(".", index + 1);
		}
		return results;
	}
	
}
