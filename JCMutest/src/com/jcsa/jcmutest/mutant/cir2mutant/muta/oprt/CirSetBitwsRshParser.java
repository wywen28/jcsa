package com.jcsa.jcmutest.mutant.cir2mutant.muta.oprt;

import com.jcsa.jcmutest.mutant.cir2mutant.cerr.CirConstraint;
import com.jcsa.jcmutest.mutant.cir2mutant.cerr.CirStateError;
import com.jcsa.jcmutest.mutant.cir2mutant.muta.CirSetOperatorParser;
import com.jcsa.jcparse.lang.lexical.COperator;

public class CirSetBitwsRshParser extends CirSetOperatorParser {

	@Override
	protected boolean to_assign() throws Exception {
		CirConstraint constraint; CirStateError init_error;
		constraint = this.get_constraint(Boolean.TRUE);
		if(this.compare_or_mutate) {
			init_error = this.trap_statement();
		}
		else {
			init_error = this.set_expression(this.roperand);
		}
		return this.add_infection(constraint, init_error);
	}

	@Override
	protected boolean arith_add() throws Exception {
		/**
		 * [y != 0]
		 */
		CirConstraint constraint; CirStateError init_error;
		constraint = this.get_constraint(this.sym_expression(
				COperator.not_equals, roperand, Integer.valueOf(0)));
		if(this.compare_or_mutate) {
			init_error = this.trap_statement();
		}
		else {
			init_error = this.set_expression(this.sym_expression(
					COperator.arith_add, this.loperand, roperand));
		}
		return this.add_infection(constraint, init_error);
	}

	@Override
	protected boolean arith_sub() throws Exception {
		/**
		 * [y != 0]
		 */
		CirConstraint constraint; CirStateError init_error;
		constraint = this.get_constraint(this.sym_expression(
				COperator.not_equals, roperand, Integer.valueOf(0)));
		if(this.compare_or_mutate) {
			init_error = this.trap_statement();
		}
		else {
			init_error = this.set_expression(this.sym_expression(
					COperator.arith_sub, this.loperand, roperand));
		}
		return this.add_infection(constraint, init_error);
	}

	@Override
	protected boolean arith_mul() throws Exception {
		/**
		 * [x != 0]
		 */
		CirConstraint constraint; CirStateError init_error;
		constraint = this.get_constraint(this.sym_expression(
				COperator.not_equals, loperand, Integer.valueOf(0)));
		if(this.compare_or_mutate) {
			init_error = this.trap_statement();
		}
		else {
			init_error = this.set_expression(this.sym_expression(
					COperator.arith_mul, this.loperand, roperand));
		}
		return this.add_infection(constraint, init_error);
	}

	@Override
	protected boolean arith_div() throws Exception {
		/**
		 * [y == 0] --> trap_stmt()
		 * [x != 0] -->
		 */
		CirConstraint constraint; CirStateError init_error;
		if(this.compare_or_mutate) {
			constraint = this.get_constraint(this.sym_expression(
					COperator.not_equals, loperand, Integer.valueOf(0)));
			init_error = this.trap_statement();
			return this.add_infection(constraint, init_error);
		}
		else {
			constraint = this.get_constraint(this.sym_expression(
					COperator.equal_with, roperand, Integer.valueOf(0)));
			init_error = this.trap_statement();
			this.add_infection(constraint, init_error);
			
			constraint = this.get_constraint(this.sym_expression(
					COperator.not_equals, loperand, Integer.valueOf(0)));
			init_error = this.set_expression(this.sym_expression(
					COperator.arith_div, this.loperand, this.roperand));
			return this.add_infection(constraint, init_error);
		}
	}

	@Override
	protected boolean arith_mod() throws Exception {
		/**
		 * [y == 0] --> trap_stmt()
		 * [x != 0] -->
		 */
		CirConstraint constraint; CirStateError init_error;
		if(this.compare_or_mutate) {
			constraint = this.get_constraint(this.sym_expression(
					COperator.not_equals, loperand, Integer.valueOf(0)));
			init_error = this.trap_statement();
			return this.add_infection(constraint, init_error);
		}
		else {
			constraint = this.get_constraint(this.sym_expression(
					COperator.equal_with, roperand, Integer.valueOf(0)));
			init_error = this.trap_statement();
			this.add_infection(constraint, init_error);
			
			constraint = this.get_constraint(this.sym_expression(
					COperator.not_equals, loperand, Integer.valueOf(0)));
			init_error = this.set_expression(this.sym_expression(
					COperator.arith_mod, this.loperand, this.roperand));
			return this.add_infection(constraint, init_error);
		}
	}

	@Override
	protected boolean bitws_and() throws Exception {
		/**
		 * [x != 0]
		 */
		CirConstraint constraint; CirStateError init_error;
		constraint = this.get_constraint(this.sym_expression(
				COperator.not_equals, loperand, Integer.valueOf(0)));
		if(this.compare_or_mutate) {
			init_error = this.trap_statement();
		}
		else {
			init_error = this.set_expression(this.sym_expression(
					COperator.bit_and, this.loperand, roperand));
		}
		return this.add_infection(constraint, init_error);
	}

	@Override
	protected boolean bitws_ior() throws Exception {
		/**
		 * [y != 0]
		 */
		CirConstraint constraint; CirStateError init_error;
		constraint = this.get_constraint(this.sym_expression(
				COperator.not_equals, roperand, Integer.valueOf(0)));
		if(this.compare_or_mutate) {
			init_error = this.trap_statement();
		}
		else {
			init_error = this.set_expression(this.sym_expression(
					COperator.bit_or, this.loperand, roperand));
		}
		return this.add_infection(constraint, init_error);
	}

	@Override
	protected boolean bitws_xor() throws Exception {
		/**
		 * [y != 0]
		 */
		CirConstraint constraint; CirStateError init_error;
		constraint = this.get_constraint(this.sym_expression(
				COperator.not_equals, roperand, Integer.valueOf(0)));
		if(this.compare_or_mutate) {
			init_error = this.trap_statement();
		}
		else {
			init_error = this.set_expression(this.sym_expression(
					COperator.bit_xor, this.loperand, roperand));
		}
		return this.add_infection(constraint, init_error);
	}

	@Override
	protected boolean bitws_lsh() throws Exception {
		/**
		 * [y != 0]
		 */
		CirConstraint constraint; CirStateError init_error;
		constraint = this.get_constraint(this.sym_expression(
				COperator.not_equals, roperand, Integer.valueOf(0)));
		if(this.compare_or_mutate) {
			init_error = this.trap_statement();
		}
		else {
			init_error = this.set_expression(this.sym_expression(
					COperator.left_shift, this.loperand, roperand));
		}
		return this.add_infection(constraint, init_error);
	}

	@Override
	protected boolean bitws_rsh() throws Exception {
		return this.unsupport_exception();
	}

	@Override
	protected boolean logic_and() throws Exception {
		/**
		 * [x != 0]
		 */
		CirConstraint constraint; CirStateError init_error;
		constraint = this.get_constraint(this.sym_expression(
				COperator.not_equals, loperand, Integer.valueOf(0)));
		if(this.compare_or_mutate) {
			init_error = this.trap_statement();
		}
		else {
			init_error = this.set_expression(this.sym_expression(
					COperator.logic_and, this.loperand, roperand));
		}
		return this.add_infection(constraint, init_error);
	}

	@Override
	protected boolean logic_ior() throws Exception {
		/**
		 * [y != 0]
		 */
		CirConstraint constraint; CirStateError init_error;
		constraint = this.get_constraint(this.sym_expression(
				COperator.not_equals, roperand, Integer.valueOf(0)));
		if(this.compare_or_mutate) {
			init_error = this.trap_statement();
		}
		else {
			init_error = this.set_expression(Integer.valueOf(1));
		}
		return this.add_infection(constraint, init_error);
	}

	@Override
	protected boolean greater_tn() throws Exception {
		CirConstraint constraint = this.get_constraint(Boolean.TRUE);
		CirStateError init_error;
		if(this.compare_or_mutate) {
			init_error = this.trap_statement();
		}
		else {
			init_error = this.set_expression(this.sym_expression(
					COperator.greater_tn, this.loperand, this.roperand));
		}
		return this.add_infection(constraint, init_error);
	}

	@Override
	protected boolean greater_eq() throws Exception {
		CirConstraint constraint = this.get_constraint(Boolean.TRUE);
		CirStateError init_error;
		if(this.compare_or_mutate) {
			init_error = this.trap_statement();
		}
		else {
			init_error = this.set_expression(this.sym_expression(
					COperator.greater_eq, this.loperand, this.roperand));
		}
		return this.add_infection(constraint, init_error);
	}

	@Override
	protected boolean smaller_tn() throws Exception {
		CirConstraint constraint = this.get_constraint(Boolean.TRUE);
		CirStateError init_error;
		if(this.compare_or_mutate) {
			init_error = this.trap_statement();
		}
		else {
			init_error = this.set_expression(this.sym_expression(
					COperator.smaller_tn, this.loperand, this.roperand));
		}
		return this.add_infection(constraint, init_error);
	}

	@Override
	protected boolean smaller_eq() throws Exception {
		CirConstraint constraint = this.get_constraint(Boolean.TRUE);
		CirStateError init_error;
		if(this.compare_or_mutate) {
			init_error = this.trap_statement();
		}
		else {
			init_error = this.set_expression(this.sym_expression(
					COperator.smaller_eq, this.loperand, this.roperand));
		}
		return this.add_infection(constraint, init_error);
	}

	@Override
	protected boolean equal_with() throws Exception {
		CirConstraint constraint = this.get_constraint(Boolean.TRUE);
		CirStateError init_error;
		if(this.compare_or_mutate) {
			init_error = this.trap_statement();
		}
		else {
			init_error = this.set_expression(this.sym_expression(
					COperator.equal_with, this.loperand, this.roperand));
		}
		return this.add_infection(constraint, init_error);
	}

	@Override
	protected boolean not_equals() throws Exception {
		CirConstraint constraint = this.get_constraint(Boolean.TRUE);
		CirStateError init_error;
		if(this.compare_or_mutate) {
			init_error = this.trap_statement();
		}
		else {
			init_error = this.set_expression(this.sym_expression(
					COperator.not_equals, this.loperand, this.roperand));
		}
		return this.add_infection(constraint, init_error);
	}
	
}
