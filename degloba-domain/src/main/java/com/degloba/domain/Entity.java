package com.degloba.domain;

import java.io.Serializable;

import com.google.appengine.api.datastore.Key;

/**
 * 领域Entity接口。所有Entity类都要直接或间接实现这个接口。它主要起标记作用，以便于统一处理系统中的Entity等。
 * @author degloba (<a href="mailto:gdyangyu@gmail.com">gdyangyu@gmail.com</a>)
 * 
 */
public interface Entity extends Serializable {

	/**
	 * 取得ID entity。Entity类的每个实例都必须有个唯一Id以标识自身。
	 * EntityId必须是可序列化的。
	 * @return Entity实例的 Id.
	 */
	Key getId();
	
	/**
	 * 是否在数据库中已经存在
	 * @return 如果该Entity以存在于数据库中，返回true，否则返回false
	 */
	boolean existed();
	
	/**
	 * 是否在数据库中不存在
     * @return 如果该Entity以存在于数据库中，返回false，否则返回true
	 */
	boolean notExisted();
}
