package com.jcsa.jcmutest.mutant.ast2mutant;

import java.util.List;

import com.jcsa.jcmutest.mutant.AstMutation;
import com.jcsa.jcmutest.mutant.AstMutations;
import com.jcsa.jcparse.lang.astree.AstNode;
import com.jcsa.jcparse.lang.astree.expr.AstExpression;
import com.jcsa.jcparse.lang.astree.unit.AstFunctionDefinition;

public class BTRPMutationGenerator extends MutationGenerator {
	
	@Override
	protected void initialize(AstFunctionDefinition function) throws Exception {}
	
	@Override
	protected boolean available(AstNode location) throws Exception {
		return this.is_condition_expression(location);
	}
	
	@Override
	protected void generate(AstNode location, List<AstMutation> mutations) throws Exception {
		AstExpression expression = (AstExpression) location;
		mutations.add(AstMutations.trap_on_true(expression));
		mutations.add(AstMutations.trap_on_false(expression));
	}
	
}
