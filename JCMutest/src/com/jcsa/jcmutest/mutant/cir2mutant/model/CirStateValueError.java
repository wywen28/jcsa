package com.jcsa.jcmutest.mutant.cir2mutant.model;

import com.jcsa.jcmutest.mutant.cir2mutant.CirErrorType;
import com.jcsa.jcparse.lang.irlang.expr.CirReferExpression;
import com.jcsa.jcparse.lang.sym.SymExpression;


public class CirStateValueError extends CirStateError {
	
	/** the reference of which referred will be mutated **/
	private CirReferExpression reference;
	/** the original value hold by the expression in testing **/
	private SymExpression orig_val;
	/** the mutation value that will replace the original one **/
	private SymExpression muta_val;
	/**
	 * @param reference the reference of which state will be replaced
	 * @param muta_val mutation value that will replace the original state
	 * @throws Exception
	 */
	protected CirStateValueError(CirReferExpression reference, 
			SymExpression orig_val, SymExpression muta_value) throws Exception {
		super(CirErrorType.stat_error, reference.statement_of());
		if(muta_value == null)
			throw new IllegalArgumentException("Invalid muta_value: null");
		else {
			this.reference = reference;
			this.orig_val = orig_val;
			this.muta_val = muta_value;
		}
	}
	
	/* getters */
	/**
	 * @return the reference of which state will be mutated
	 */
	public CirReferExpression get_reference() { return this.reference; }
	/**
	 * @return the original reference used by the point
	 */
	public SymExpression get_original_value() { return this.orig_val; }
	/**
	 * @return the mutation reference that will replace the original one
	 */
	public SymExpression get_mutation_value() { return this.muta_val; }

	@Override
	protected String generate_code() throws Exception {
		return this.reference.generate_code(false) + 
				", " + this.orig_val.generate_code() + 
				", " + this.muta_val.generate_code();
	}

	@Override
	public boolean influencable() {
		return !this.orig_val.equals(this.muta_val);
	}
	
}