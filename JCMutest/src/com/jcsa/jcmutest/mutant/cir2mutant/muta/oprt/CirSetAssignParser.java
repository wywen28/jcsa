package com.jcsa.jcmutest.mutant.cir2mutant.muta.oprt;

import com.jcsa.jcmutest.mutant.cir2mutant.cerr.CirConstraint;
import com.jcsa.jcmutest.mutant.cir2mutant.cerr.CirStateError;
import com.jcsa.jcmutest.mutant.cir2mutant.muta.CirSetOperatorParser;
import com.jcsa.jcparse.lang.lexical.COperator;

public class CirSetAssignParser extends CirSetOperatorParser {

	@Override
	protected boolean to_assign() throws Exception {
		return this.unsupport_exception();
	}

	@Override
	protected boolean arith_add() throws Exception {
		CirConstraint constraint; CirStateError init_error;
		constraint = this.get_constraint(Boolean.TRUE);
		if(this.compare_or_mutate) {
			init_error = this.trap_statement();
		}
		else {
			init_error = this.ins_expression(
					this.loperand, COperator.arith_add);
		}
		return this.add_infection(constraint, init_error);
	}

	@Override
	protected boolean arith_sub() throws Exception {
		CirConstraint constraint; CirStateError init_error;
		constraint = this.get_constraint(Boolean.TRUE);
		if(this.compare_or_mutate) {
			init_error = this.trap_statement();
		}
		else {
			init_error = this.ins_expression(
					this.loperand, COperator.arith_sub);
		}
		return this.add_infection(constraint, init_error);
	}

	@Override
	protected boolean arith_mul() throws Exception {
		CirConstraint constraint; CirStateError init_error;
		constraint = this.get_constraint(Boolean.TRUE);
		if(this.compare_or_mutate) {
			init_error = this.trap_statement();
		}
		else {
			init_error = this.ins_expression(
					this.loperand, COperator.arith_mul);
		}
		return this.add_infection(constraint, init_error);
	}

	@Override
	protected boolean arith_div() throws Exception {
		CirConstraint constraint; CirStateError init_error;
		constraint = this.get_constraint(Boolean.TRUE);
		if(this.compare_or_mutate) {
			init_error = this.trap_statement();
		}
		else {
			constraint = this.get_constraint(sym_expression(COperator.
					equal_with, this.roperand, Integer.valueOf(0)));
			init_error = this.trap_statement();
			this.add_infection(constraint, init_error);
			
			constraint = this.get_constraint(sym_expression(COperator.
					not_equals, this.roperand, Integer.valueOf(0)));
			init_error = this.ins_expression(
					this.loperand, COperator.arith_div);
		}
		return this.add_infection(constraint, init_error);
	}

	@Override
	protected boolean arith_mod() throws Exception {
		CirConstraint constraint; CirStateError init_error;
		constraint = this.get_constraint(Boolean.TRUE);
		if(this.compare_or_mutate) {
			init_error = this.trap_statement();
		}
		else {
			constraint = this.get_constraint(sym_expression(COperator.
					equal_with, this.roperand, Integer.valueOf(0)));
			init_error = this.trap_statement();
			this.add_infection(constraint, init_error);
			
			constraint = this.get_constraint(sym_expression(COperator.
					not_equals, this.roperand, Integer.valueOf(0)));
			init_error = this.ins_expression(
					this.loperand, COperator.arith_mod);
		}
		return this.add_infection(constraint, init_error);
	}

	@Override
	protected boolean bitws_and() throws Exception {
		CirConstraint constraint; CirStateError init_error;
		constraint = this.get_constraint(Boolean.TRUE);
		if(this.compare_or_mutate) {
			init_error = this.trap_statement();
		}
		else {
			init_error = this.ins_expression(
					this.loperand, COperator.bit_and);
		}
		return this.add_infection(constraint, init_error);
	}

	@Override
	protected boolean bitws_ior() throws Exception {
		CirConstraint constraint; CirStateError init_error;
		constraint = this.get_constraint(Boolean.TRUE);
		if(this.compare_or_mutate) {
			init_error = this.trap_statement();
		}
		else {
			init_error = this.ins_expression(
					this.loperand, COperator.bit_or);
		}
		return this.add_infection(constraint, init_error);
	}

	@Override
	protected boolean bitws_xor() throws Exception {
		CirConstraint constraint; CirStateError init_error;
		constraint = this.get_constraint(Boolean.TRUE);
		if(this.compare_or_mutate) {
			init_error = this.trap_statement();
		}
		else {
			init_error = this.ins_expression(
					this.loperand, COperator.bit_xor);
		}
		return this.add_infection(constraint, init_error);
	}

	@Override
	protected boolean bitws_lsh() throws Exception {
		CirConstraint constraint; CirStateError init_error;
		constraint = this.get_constraint(Boolean.TRUE);
		if(this.compare_or_mutate) {
			init_error = this.trap_statement();
		}
		else {
			init_error = this.ins_expression(
					this.loperand, COperator.left_shift);
		}
		return this.add_infection(constraint, init_error);
	}

	@Override
	protected boolean bitws_rsh() throws Exception {
		CirConstraint constraint; CirStateError init_error;
		constraint = this.get_constraint(Boolean.TRUE);
		if(this.compare_or_mutate) {
			init_error = this.trap_statement();
		}
		else {
			init_error = this.ins_expression(
					this.loperand, COperator.righ_shift);
		}
		return this.add_infection(constraint, init_error);
	}

	@Override
	protected boolean logic_and() throws Exception {
		return this.unsupport_exception();
	}

	@Override
	protected boolean logic_ior() throws Exception {
		return this.unsupport_exception();
	}

	@Override
	protected boolean greater_tn() throws Exception {
		return this.unsupport_exception();
	}

	@Override
	protected boolean greater_eq() throws Exception {
		return this.unsupport_exception();
	}

	@Override
	protected boolean smaller_tn() throws Exception {
		return this.unsupport_exception();
	}

	@Override
	protected boolean smaller_eq() throws Exception {
		return this.unsupport_exception();
	}

	@Override
	protected boolean equal_with() throws Exception {
		return this.unsupport_exception();
	}

	@Override
	protected boolean not_equals() throws Exception {
		return this.unsupport_exception();
	}

}
