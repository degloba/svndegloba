package com.degloba.persistence.jpa.namedqueryparser;

// Degloba JPA
import com.degloba.persistence.jpa.EntityManagerProvider;
import com.degloba.persistence.jpa.NamedQueryParser;

// Hibernate
import org.hibernate.Session;


/**
 * Hibernate implementation of the NamedQueryParser interface
 */
public class NamedQueryParserHibernate extends NamedQueryParser {

    public NamedQueryParserHibernate() {
    }

    public NamedQueryParserHibernate(EntityManagerProvider entityManagerProvider) {
        super(entityManagerProvider);
    }
    
    @Override
    public String getQueryStringOfNamedQuery(String queryName) {
        Session session = (Session) getEntityManager().getDelegate();
        return session.getNamedQuery(queryName).getQueryString();
    }
    
}
