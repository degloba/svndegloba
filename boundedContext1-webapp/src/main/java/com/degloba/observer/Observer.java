package com.degloba.observer;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.degloba.domain.AbstractEntity;
import com.degloba.domain.NamedParameters;
import com.google.appengine.api.datastore.Key;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SuppressWarnings("rawtypes")
@Entity
@Table(name = "COMMONS_OBSERVER")
/*@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "OBSERVER_CATEGORY", discriminatorType = DiscriminatorType.STRING)*/
public abstract class Observer<T extends Subject> extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4841919329150188032L;

	private static final Logger logger = LoggerFactory
			.getLogger(Observer.class);

    @ElementCollection
    @CollectionTable(name = "COMMONS_OBSERVER_SUBJECTKEY", joinColumns = @JoinColumn(name = "OBSERVER_ID"))
    @Column(name = "SUBJECT_KEY")
	private Set<String> subjectKeys = new HashSet<String>();

	public Set<String> getSubjectKeys() {
		return subjectKeys;
	}

	public void setSubjectKeys(Set<String> subjectKeys) {
		this.subjectKeys = subjectKeys;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.SHORT_PREFIX_STYLE);
	}

	/*
	 * =======================================
	 * 
	 * action
	 * 
	 * ========================================
	 */

	public abstract void process(T subject);

	public static Observer get(Key id) {
		return getRepository().get(Observer.class, id);
	}

	public static List<Observer> findBySubject(Subject subject) {
		String queryString = "select o from Observer o where :subjectKey in elements(o.subjectKeys))";
                NamedParameters params = NamedParameters.create().add("subjectKey", subject.getSubjectKey());
		List<Object> observers = getRepository().createJpqlQuery(queryString)
                .addParameter("subjectKey", subject.getSubjectKey()).list();

		if (logger.isDebugEnabled()) {
			if (observers.isEmpty()) {
				logger.debug("æ²¡æœ‰æ‰¾åˆ°ä¸€ä¸ªè§‚å¯Ÿè€…ï¼šsubjectKeyä¸ºã€�{}ã€‘",
						subject.getSubjectKey());
			} else {
				for (Object observer : observers) {
					logger.debug("æ‰¾åˆ°ä¸€ä¸ªè§‚å¯Ÿè€…ï¼šsubjectKeyä¸ºã€�{}ã€‘ï¼Œobserverä¸ºã€�{}ã€‘",
							subject.getSubjectKey(), observer);
				}
			}
		}
		List<Observer> results = new ArrayList<Observer>();
		for (Object observer : observers) {
			results.add((Observer) observer);
		}
		return results;
	}
}