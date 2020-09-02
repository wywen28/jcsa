package com.jcsa.jcmutest.mutant.sed2mutant.lang.errs.rea;

import com.jcsa.jcmutest.mutant.sed2mutant.lang.SedNode;
import com.jcsa.jcmutest.mutant.sed2mutant.lang.expr.SedExpression;
import com.jcsa.jcparse.lang.irlang.stmt.CirStatement;

public class SedExtRealExpressionError extends SedRealExpressionError {
	
	public SedExtRealExpressionError(CirStatement location, SedExpression orig_expression) {
		super(location, orig_expression);
	}
	
	@Override
	protected String generate_content() throws Exception {
		return "ext_real(" + this.get_orig_expression().generate_code() + ")";
	}
	
	@Override
	protected SedNode clone_self() {
		return new SedExtRealExpressionError(this.get_location().
				get_cir_statement(), this.get_orig_expression());
	}
	
}
