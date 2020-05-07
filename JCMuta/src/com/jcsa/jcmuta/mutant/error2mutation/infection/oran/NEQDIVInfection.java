package com.jcsa.jcmuta.mutant.error2mutation.infection.oran;

import java.util.Map;

import com.jcsa.jcmuta.mutant.error2mutation.StateError;
import com.jcsa.jcmuta.mutant.error2mutation.StateErrorGraph;
import com.jcsa.jcmuta.mutant.error2mutation.StateEvaluation;
import com.jcsa.jcmuta.mutant.error2mutation.infection.OPRTInfection;
import com.jcsa.jcparse.lang.irlang.expr.CirExpression;
import com.jcsa.jcparse.lang.irlang.stmt.CirStatement;
import com.jcsa.jcparse.lang.lexical.COperator;
import com.jcsa.jcparse.lang.symb.StateConstraints;
import com.jcsa.jcparse.lang.symb.SymExpression;

/**
 * loperand == 0 and roperand != 0			--> set_false
 * roperand == 0							--> failure()
 * loperand == roperand and loperand != 0	--> set_true
 * @author yukimula
 *
 */
public class NEQDIVInfection extends OPRTInfection {

	@Override
	protected SymExpression muta_expression(CirExpression expression, CirExpression loperand, CirExpression roperand)
			throws Exception {
		return StateEvaluation.binary_expression(expression.
				get_data_type(), COperator.arith_div, loperand, roperand);
	}

	@Override
	protected boolean partial_evaluate(CirExpression expression, CirExpression loperand, CirExpression roperand,
			StateErrorGraph graph, Map<StateError, StateConstraints> output) throws Exception {
		Object lconstant = StateEvaluation.get_constant_value(loperand);
		Object rconstant = StateEvaluation.get_constant_value(roperand);
		SymExpression constraint; StateConstraints constraints;
		CirStatement statement = expression.statement_of();
		
		if(!(lconstant instanceof SymExpression)) {
			if(StateEvaluation.is_zero_number(lconstant)) {
				constraint = StateEvaluation.not_equals(roperand, 0L);
				constraints = StateEvaluation.get_conjunctions();
				this.add_constraint(constraints, statement, constraint);
				output.put(graph.get_error_set().set_bool(expression, false), constraints);
			}
			else {
				constraint = StateEvaluation.equal_with(roperand, 0L);
				constraints = StateEvaluation.get_conjunctions();
				this.add_constraint(constraints, statement, constraint);
				output.put(graph.get_error_set().failure(), constraints);
				
				constraint = StateEvaluation.equal_with(loperand, roperand);
				constraints = StateEvaluation.get_conjunctions();
				this.add_constraint(constraints, statement, constraint);
				output.put(graph.get_error_set().set_bool(expression, true), constraints);
			}
			return true;
		}
		
		if(!(rconstant instanceof SymExpression)) {
			if(StateEvaluation.is_zero_number(rconstant)) {
				output.put(graph.get_error_set().failure(), StateEvaluation.get_conjunctions());
			}
			else {
				constraint = StateEvaluation.equal_with(loperand, 0L);
				constraints = StateEvaluation.get_conjunctions();
				this.add_constraint(constraints, statement, constraint);
				output.put(graph.get_error_set().set_bool(expression, false), constraints);
				
				constraint = StateEvaluation.equal_with(loperand, roperand);
				constraints = StateEvaluation.get_conjunctions();
				this.add_constraint(constraints, statement, constraint);
				output.put(graph.get_error_set().set_bool(expression, true), constraints);
			}
			return true;
		}
		
		return false;
	}

	@Override
	protected boolean symbolic_evaluate(CirExpression expression, CirExpression loperand, CirExpression roperand,
			StateErrorGraph graph, Map<StateError, StateConstraints> output) throws Exception {
		SymExpression lcondition, rcondition; StateConstraints constraints;
		CirStatement statement = expression.statement_of();
		
		lcondition = StateEvaluation.equal_with(loperand, 0L);
		rcondition = StateEvaluation.not_equals(roperand, 0L);
		constraints = StateEvaluation.get_conjunctions();
		this.add_constraint(constraints, statement, lcondition);
		this.add_constraint(constraints, statement, rcondition);
		output.put(graph.get_error_set().set_bool(expression, false), constraints);
		
		lcondition = StateEvaluation.equal_with(roperand, 0L);
		constraints = StateEvaluation.get_conjunctions();
		this.add_constraint(constraints, statement, lcondition);
		output.put(graph.get_error_set().failure(), constraints);
		
		lcondition = StateEvaluation.equal_with(loperand, roperand);
		constraints = StateEvaluation.get_conjunctions();
		this.add_constraint(constraints, statement, lcondition);
		output.put(graph.get_error_set().set_bool(expression, true), constraints);
		
		return true;
	}

}
