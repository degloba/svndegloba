package com.degloba.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import com.degloba.utils.Assert;

/**
 * Key data types
 */
public class KeyValue<K, V> {
	
	private K key;
	
	private V value;

	/**
	 * Generated key pair
	 * @param key Bond
	 * @param value Value
	 */
	public KeyValue(K key, V value) {
		Assert.notNull(key, "Key must not be null!");
		this.key = key;
		this.value = value;
	}

	/**
	 * Get key
	 * @return Bond
	 */
	public K getKey() {
		return key;
	}

	/**
	 * Get value
	 * @return Value
	 */
	public V getValue() {
		return value;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(17, 31).append(key).append(value).toHashCode();
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof KeyValue)) {
			return false;
		}
		KeyValue<K, V> that = (KeyValue<K, V>) other;
		return new EqualsBuilder().append(this.key, that.key)
				.append(this.value, that.value).isEquals();
	}

	@Override
	public String toString() {
		return "KeyValue [key=" + key + ", value=" + value + "]";
	}

}
