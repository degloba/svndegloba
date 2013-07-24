/**
 * 
 */
package com.insacosa.domain;

import javax.inject.Inject;

import ddd.application.SystemUser;
import ddd.domain.annotations.DomainFactory;
import ddd.domain.sharedkernel.Money;
import com.insacosa.domain.policies.rebate.StandardRebate;
import com.insacosa.domain.policies.rebate.decorators.VipRebate;

/**
 * @author Slawek
 *
 */
@DomainFactory
public class RebatePolicyFactory {

	@Inject
	private SystemUser systemUser;
	
	public RebatePolicy createRebatePolicy() {		
		RebatePolicy rebatePolicy = new StandardRebate(10, 1);
		
		if (isVip(systemUser)){
			rebatePolicy = new VipRebate(rebatePolicy, new Money(1000.0), new Money(100));
		}
		
		return rebatePolicy;
	}
	
	/**
	 * @param systemUser
	 * @return
	 */
	private boolean isVip(SystemUser systemUser) {
		// TODO Auto-generated method stub
		return true;
	}
}
