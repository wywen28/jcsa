package com.jcsa.jcmutest.mutant.cir2mutant.muta.trap;

import java.util.Map;

import com.jcsa.jcmutest.mutant.cir2mutant.cerr.CirConstraint;
import com.jcsa.jcmutest.mutant.cir2mutant.cerr.CirMutations;
import com.jcsa.jcmutest.mutant.cir2mutant.cerr.CirStateError;
import com.jcsa.jcmutest.mutant.cir2mutant.muta.CirMutationParser;
import com.jcsa.jcmutest.mutant.mutation.AstMutation;
import com.jcsa.jcparse.lang.irlang.CirTree;
import com.jcsa.jcparse.lang.irlang.expr.CirExpression;
import com.jcsa.jcparse.lang.irlang.stmt.CirStatement;

/**
 * condition == {true|false} --> trap_error()
 * 
 * @author yukimula
 * 
 */
public class BTRPCirMutationParser extends CirMutationParser {
	
	@Override
	protected CirStatement get_location(CirTree cir_tree, AstMutation mutation) throws Exception {
		return this.get_end_statement(cir_tree, mutation.get_location());
	}
	
	@Override
	protected void generate_infections(CirMutations mutations, CirTree cir_tree, CirStatement statement,
			AstMutation mutation, Map<CirStateError, CirConstraint> infections) throws Exception {
		CirExpression expression = this.get_cir_expression(cir_tree, mutation.get_location());
		boolean value;
		switch(mutation.get_operator()) {
		case trap_on_true:	value = true;	break;
		case trap_on_false:	value = false;	break;
		default: throw new IllegalArgumentException("Invalid operator: " + mutation.get_operator());
		}
		CirConstraint constraint = mutations.expression_constraint(statement, expression, value);
		CirStateError state_error = mutations.trap_error(statement);
		infections.put(state_error, constraint);
	}
	
}
