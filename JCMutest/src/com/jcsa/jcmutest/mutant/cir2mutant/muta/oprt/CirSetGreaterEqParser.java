package com.jcsa.jcmutest.mutant.cir2mutant.muta.oprt;

import com.jcsa.jcmutest.mutant.cir2mutant.cerr.CirConstraint;
import com.jcsa.jcmutest.mutant.cir2mutant.cerr.CirStateError;
import com.jcsa.jcmutest.mutant.cir2mutant.muta.CirSetOperatorParser;
import com.jcsa.jcparse.lang.lexical.COperator;
import com.jcsa.jcparse.lang.sym.SymExpression;

public class CirSetGreaterEqParser extends CirSetOperatorParser {

	@Override
	protected boolean to_assign() throws Exception {
		return this.unsupport_exception();
	}

	@Override
	protected boolean arith_add() throws Exception {
		/**
		 * [true] --> set_expr(B(x + y))
		 */
		CirConstraint constraint; CirStateError init_error; 
		SymExpression condition;
		constraint = this.get_constraint(Boolean.TRUE);
		if(this.compare_or_mutate) {
			init_error = this.trap_statement();
		}
		else {
			condition = this.sym_expression(COperator.
						arith_add, loperand, roperand);
			condition = this.sym_condition(condition, true);
			init_error = this.set_expression(condition);
		}
		return this.add_infection(constraint, init_error);
	}

	@Override
	protected boolean arith_sub() throws Exception {
		return this.not_equals();
	}

	@Override
	protected boolean arith_mul() throws Exception {
		CirConstraint constraint; CirStateError init_error; 
		SymExpression condition;
		constraint = this.get_constraint(Boolean.TRUE);
		if(this.compare_or_mutate) {
			init_error = this.trap_statement();
		}
		else {
			condition = this.sym_expression(COperator.
						arith_mul, loperand, roperand);
			condition = this.sym_condition(condition, true);
			init_error = this.set_expression(condition);
		}
		return this.add_infection(constraint, init_error);
	}

	@Override
	protected boolean arith_div() throws Exception {
		/**
		 * [y == 0] --> trap()
		 * [y != 0] --> set_expr(B(x / y))
		 */
		CirConstraint constraint; CirStateError init_error; 
		SymExpression condition;
		if(this.compare_or_mutate) {
			constraint = this.get_constraint(Boolean.TRUE);
			init_error = this.trap_statement();
			return this.add_infection(constraint, init_error);
		}
		else {
			constraint = this.get_constraint(this.sym_expression(
						COperator.equal_with, loperand, roperand));
			init_error = this.trap_statement();
			this.add_infection(constraint, init_error);
			
			constraint = this.get_constraint(this.sym_expression(
					COperator.not_equals, loperand, roperand));
			condition = this.sym_expression(COperator.
					arith_div, loperand, roperand);
			condition = this.sym_condition(condition, true);
			init_error = this.set_expression(condition);
			return this.add_infection(constraint, init_error);
		}
	}

	@Override
	protected boolean arith_mod() throws Exception {
		/**
		 * [y == 0] --> trap()
		 * [y != 0] --> set_expr(B(x / y))
		 */
		CirConstraint constraint; CirStateError init_error; 
		SymExpression condition;
		if(this.compare_or_mutate) {
			constraint = this.get_constraint(Boolean.TRUE);
			init_error = this.trap_statement();
			return this.add_infection(constraint, init_error);
		}
		else {
			constraint = this.get_constraint(this.sym_expression(
						COperator.equal_with, loperand, roperand));
			init_error = this.trap_statement();
			this.add_infection(constraint, init_error);
			
			constraint = this.get_constraint(this.sym_expression(
					COperator.not_equals, loperand, roperand));
			condition = this.sym_expression(COperator.
					arith_mod, loperand, roperand);
			condition = this.sym_condition(condition, true);
			init_error = this.set_expression(condition);
			return this.add_infection(constraint, init_error);
		}
	}

	@Override
	protected boolean bitws_and() throws Exception {
		CirConstraint constraint; CirStateError init_error; 
		SymExpression condition;
		constraint = this.get_constraint(Boolean.TRUE);
		if(this.compare_or_mutate) {
			init_error = this.trap_statement();
		}
		else {
			condition = this.sym_expression(COperator.
						bit_and, loperand, roperand);
			condition = this.sym_condition(condition, true);
			init_error = this.set_expression(condition);
		}
		return this.add_infection(constraint, init_error);
	}

	@Override
	protected boolean bitws_ior() throws Exception {
		CirConstraint constraint; CirStateError init_error; 
		SymExpression condition;
		constraint = this.get_constraint(Boolean.TRUE);
		if(this.compare_or_mutate) {
			init_error = this.trap_statement();
		}
		else {
			condition = this.sym_expression(COperator.
						bit_or, loperand, roperand);
			condition = this.sym_condition(condition, true);
			init_error = this.set_expression(condition);
		}
		return this.add_infection(constraint, init_error);
	}

	@Override
	protected boolean bitws_xor() throws Exception {
		return this.not_equals();
	}

	@Override
	protected boolean bitws_lsh() throws Exception {
		CirConstraint constraint; CirStateError init_error; 
		SymExpression condition;
		constraint = this.get_constraint(Boolean.TRUE);
		if(this.compare_or_mutate) {
			init_error = this.trap_statement();
		}
		else {
			condition = this.sym_expression(COperator.
						left_shift, loperand, roperand);
			condition = this.sym_condition(condition, true);
			init_error = this.set_expression(condition);
		}
		return this.add_infection(constraint, init_error);
	}

	@Override
	protected boolean bitws_rsh() throws Exception {
		CirConstraint constraint; CirStateError init_error; 
		SymExpression condition;
		constraint = this.get_constraint(Boolean.TRUE);
		if(this.compare_or_mutate) {
			init_error = this.trap_statement();
		}
		else {
			condition = this.sym_expression(COperator.
						righ_shift, loperand, roperand);
			condition = this.sym_condition(condition, true);
			init_error = this.set_expression(condition);
		}
		return this.add_infection(constraint, init_error);
	}

	@Override
	protected boolean logic_and() throws Exception {
		CirConstraint constraint; CirStateError init_error; 
		SymExpression condition;
		constraint = this.get_constraint(Boolean.TRUE);
		if(this.compare_or_mutate) {
			init_error = this.trap_statement();
		}
		else {
			condition = this.sym_expression(COperator.
						logic_and, loperand, roperand);
			condition = this.sym_condition(condition, true);
			init_error = this.set_expression(condition);
		}
		return this.add_infection(constraint, init_error);
	}

	@Override
	protected boolean logic_ior() throws Exception {
		CirConstraint constraint; CirStateError init_error; 
		SymExpression condition;
		constraint = this.get_constraint(Boolean.TRUE);
		if(this.compare_or_mutate) {
			init_error = this.trap_statement();
		}
		else {
			condition = this.sym_expression(COperator.
						logic_or, loperand, roperand);
			condition = this.sym_condition(condition, true);
			init_error = this.set_expression(condition);
		}
		return this.add_infection(constraint, init_error);
	}

	@Override
	protected boolean greater_tn() throws Exception {
		/**
		 * [x == y] --> set_false
		 */
		CirConstraint constraint; CirStateError init_error;
		constraint = this.get_constraint(this.sym_expression(
				COperator.equal_with, this.loperand, this.roperand));
		if(this.compare_or_mutate) {
			init_error = this.trap_statement();
		}
		else {
			init_error = this.set_expression(Boolean.FALSE);
		}
		return this.add_infection(constraint, init_error);
	}

	@Override
	protected boolean greater_eq() throws Exception {
		return this.unsupport_exception();
	}

	@Override
	protected boolean smaller_tn() throws Exception {
		/**
		 * [true] --> not_expr
		 */
		CirConstraint constraint; CirStateError init_error;
		if(this.compare_or_mutate) {
			constraint = this.get_constraint(Boolean.TRUE);
			init_error = this.trap_statement();
			return this.add_infection(constraint, init_error);
		}
		else {
			constraint = this.get_constraint(this.sym_expression(
					COperator.smaller_tn, this.loperand, this.roperand));
			init_error = this.set_expression(Boolean.TRUE);
			this.add_infection(constraint, init_error);
			
			constraint = this.get_constraint(this.sym_expression(
					COperator.greater_eq, this.loperand, this.roperand));
			init_error = this.set_expression(Boolean.FALSE);
			this.add_infection(constraint, init_error);
			
			return true;
		}
	}

	@Override
	protected boolean smaller_eq() throws Exception {
		/**
		 * [x < y] --> set_true
		 * [x > y] --> set_false
		 */
		CirConstraint constraint; CirStateError init_error;
		if(this.compare_or_mutate) {
			constraint = this.get_constraint(this.sym_expression(
					COperator.not_equals, this.loperand, this.roperand));
			init_error = this.trap_statement();
			return this.add_infection(constraint, init_error);
		}
		else {
			constraint = this.get_constraint(this.sym_expression(
					COperator.smaller_tn, this.loperand, this.roperand));
			init_error = this.set_expression(Boolean.TRUE);
			this.add_infection(constraint, init_error);
			
			constraint = this.get_constraint(this.sym_expression(
					COperator.greater_tn, this.loperand, this.roperand));
			init_error = this.set_expression(Boolean.FALSE);
			this.add_infection(constraint, init_error);
			
			return true;
		}
	}

	@Override
	protected boolean equal_with() throws Exception {
		/**
		 * [x > y] --> set_false
		 */
		CirConstraint constraint; CirStateError init_error;
		constraint = this.get_constraint(this.sym_expression(
				COperator.greater_tn, this.loperand, this.roperand));
		if(this.compare_or_mutate) {
			init_error = this.trap_statement();
		}
		else {
			init_error = this.set_expression(Boolean.FALSE);
		}
		return this.add_infection(constraint, init_error);
	}

	@Override
	protected boolean not_equals() throws Exception {
		/**
		 * [x == y] --> set_false
		 * [x < y] --> set_true
		 */
		CirConstraint constraint; CirStateError init_error;
		if(this.compare_or_mutate) {
			constraint = this.get_constraint(this.sym_expression(
					COperator.smaller_eq, this.loperand, this.roperand));
			init_error = this.trap_statement();
			return this.add_infection(constraint, init_error);
		}
		else {
			constraint = this.get_constraint(this.sym_expression(
					COperator.smaller_tn, this.loperand, this.roperand));
			init_error = this.set_expression(Boolean.TRUE);
			this.add_infection(constraint, init_error);
			
			constraint = this.get_constraint(this.sym_expression(
					COperator.equal_with, this.loperand, this.roperand));
			init_error = this.set_expression(Boolean.FALSE);
			this.add_infection(constraint, init_error);
			
			return true;
		}
	}

}
