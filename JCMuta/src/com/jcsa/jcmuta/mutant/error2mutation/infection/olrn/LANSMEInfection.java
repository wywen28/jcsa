package com.jcsa.jcmuta.mutant.error2mutation.infection.olrn;

import java.util.Map;

import com.jcsa.jcmuta.mutant.error2mutation.StateError;
import com.jcsa.jcmuta.mutant.error2mutation.StateErrorGraph;
import com.jcsa.jcmuta.mutant.error2mutation.StateEvaluation;
import com.jcsa.jcmuta.mutant.error2mutation.infection.OPRTInfection;
import com.jcsa.jcparse.lang.ctype.impl.CBasicTypeImpl;
import com.jcsa.jcparse.lang.irlang.expr.CirExpression;
import com.jcsa.jcparse.lang.irlang.stmt.CirStatement;
import com.jcsa.jcparse.lang.lexical.COperator;
import com.jcsa.jcparse.lang.symb.StateConstraints;
import com.jcsa.jcparse.lang.symb.SymExpression;

/**
 * 	x	y	x&&y	x <= y	
 * 	0	0	0		1		
 * 	0	1	0		1
 * 	1	0	0		0
 * 	1	1	1		1
 * 	
 * 	{false, ***} --> set_true
 * @author yukimula
 *
 */
public class LANSMEInfection extends OPRTInfection {

	@Override
	protected SymExpression muta_expression(CirExpression expression, CirExpression loperand, CirExpression roperand)
			throws Exception {
		return StateEvaluation.binary_expression(CBasicTypeImpl.
				bool_type, COperator.smaller_eq, loperand, roperand);
	}

	@Override
	protected boolean partial_evaluate(CirExpression expression, CirExpression loperand, CirExpression roperand,
			StateErrorGraph graph, Map<StateError, StateConstraints> output) throws Exception {
		Object lconstant = StateEvaluation.get_constant_value(loperand);
		Object rconstant = StateEvaluation.get_constant_value(roperand);
		CirStatement statement = expression.statement_of();
		SymExpression constraint; StateConstraints constraints;
		
		if(!(lconstant instanceof SymExpression)) {
			if(StateEvaluation.get_condition_value(lconstant)) {
				return true;	/** equivalent mutant **/
			}
			else {
				output.put(graph.get_error_set().set_bool(expression, true), 
						StateEvaluation.get_conjunctions()); return true;
			}
		}
		
		if(!(rconstant instanceof SymExpression)) {
			/** (false, true) --> set_true **/
			if(StateEvaluation.get_condition_value(rconstant)) {
				constraint = StateEvaluation.new_condition(loperand, false);
				constraints = StateEvaluation.get_conjunctions();
				this.add_constraint(constraints, statement, constraint);
				output.put(graph.get_error_set().set_bool(expression, true), constraints);
				return true;
			}
			/** (false, false) --> set_true **/
			else {
				constraint = StateEvaluation.new_condition(loperand, false);
				constraints = StateEvaluation.get_conjunctions();
				this.add_constraint(constraints, statement, constraint);
				output.put(graph.get_error_set().set_bool(expression, true), constraints);
				return true;
			}
		}
		
		return false;
	}

	@Override
	protected boolean symbolic_evaluate(CirExpression expression, CirExpression loperand, CirExpression roperand,
			StateErrorGraph graph, Map<StateError, StateConstraints> output) throws Exception {
		SymExpression constraint; StateConstraints constraints;
		CirStatement statement = expression.statement_of();
		
		/** {false, ***} --> set_true **/
		constraint = StateEvaluation.new_condition(loperand, false);
		constraints = StateEvaluation.get_conjunctions();
		this.add_constraint(constraints, statement, constraint);
		output.put(graph.get_error_set().set_bool(expression, true), constraints);
		
		return true;
	}

}