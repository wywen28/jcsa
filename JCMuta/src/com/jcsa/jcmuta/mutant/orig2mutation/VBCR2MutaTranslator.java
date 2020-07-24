package com.jcsa.jcmuta.mutant.orig2mutation;

import com.jcsa.jcmuta.mutant.AstMutation;
import com.jcsa.jcparse.lang.astree.expr.AstExpression;
import com.jcsa.jcparse.lang.ctype.CTypeAnalyzer;

import __backup__.TextMutation;

public class VBCR2MutaTranslator implements Text2MutaTranslator {

	@Override
	public AstMutation parse(TextMutation mutation) throws Exception {
		AstExpression expression = (AstExpression) mutation.get_origin();
		expression = CTypeAnalyzer.get_expression_of(expression);
		switch(mutation.get_mode()) {
		case MUT_TRUE:	return AstMutation.VBRP(expression, true);
		case MUT_FALSE:	return AstMutation.VBRP(expression, false);
		default: throw new IllegalArgumentException("Invalid operator");
		}
	}

}