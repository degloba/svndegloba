/**
 * 
 */
package com.insacosa.domain.policies.rebate.decorators;

import com.insacosa.domain.RebatePolicy;

/**
 * @author Slawek
 *
 */
public abstract class RebateDecorator implements RebatePolicy{
	
	protected RebatePolicy decorated; 

	protected RebateDecorator(RebatePolicy decorated){
		this.decorated = decorated;
	}
}
