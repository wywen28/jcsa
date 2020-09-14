package com.jcsa.jcmutest.mutant.sec2mutant.muta;

import com.jcsa.jcmutest.mutant.sec2mutant.lang.SecStateError;
import com.jcsa.jcmutest.mutant.sec2mutant.lang.cons.SecConstraint;

/**
 * [constraint, init_error] as a basic branch in infection module.
 * @author yukimula
 *
 */
public class SecInfectPair {
	
	private SecConstraint constraint;
	private SecStateError init_error;
	protected SecInfectPair(SecConstraint constraint, SecStateError init_error) { 
		this.constraint = constraint;
		this.init_error = init_error;
	}
	
	/**
	 * @return constraint that are required for infecting initial error
	 */
	public SecConstraint get_constraint() { return this.constraint; }
	
	/**
	 * @return initial error infected when the constraint is satisfied
	 */
	public SecStateError get_init_error() { return this.init_error; }
	
}
