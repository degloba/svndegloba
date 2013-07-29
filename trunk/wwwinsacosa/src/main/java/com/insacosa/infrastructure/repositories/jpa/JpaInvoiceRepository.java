/**
 * 
 */
package com.insacosa.infrastructure.repositories.jpa;

import javax.inject.Inject;

import com.insacosa.domain.Invoice;
import com.insacosa.domain.InvoiceRepository;

import ddd.domain.annotations.DomainRepositoryImpl;
import ddd.domain.support.InjectorHelper;
import ddd.infrastructure.repo.jpa.GenericJpaRepositoryForBaseEntity;


/**
 * @author Slawek
 * 
 */
@DomainRepositoryImpl
public class JpaInvoiceRepository extends GenericJpaRepositoryForBaseEntity<Invoice> implements InvoiceRepository {
  
    @Inject
    private InjectorHelper injector;

    @Override
    public Invoice load(Long orderId) {
    	Invoice invoice = super.load(orderId);
        injector.injectDependencies(invoice);
        return invoice;
    }
}
