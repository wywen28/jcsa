package com.jcsa.jcmutest.mutant.cir2mutant.muta.oxxx;

import java.util.Map;

import com.jcsa.jcmutest.mutant.cir2mutant.cerr.CirConstraint;
import com.jcsa.jcmutest.mutant.cir2mutant.cerr.CirMutations;
import com.jcsa.jcmutest.mutant.cir2mutant.cerr.CirStateError;
import com.jcsa.jcmutest.mutant.cir2mutant.muta.CirMutationParser;
import com.jcsa.jcmutest.mutant.cir2mutant.muta.oprt.CirSetOperatorParsers;
import com.jcsa.jcmutest.mutant.mutation.AstMutation;
import com.jcsa.jcparse.lang.irlang.CirTree;
import com.jcsa.jcparse.lang.irlang.expr.CirComputeExpression;
import com.jcsa.jcparse.lang.irlang.expr.CirExpression;
import com.jcsa.jcparse.lang.irlang.stmt.CirAssignStatement;
import com.jcsa.jcparse.lang.irlang.stmt.CirBinAssignStatement;
import com.jcsa.jcparse.lang.irlang.stmt.CirStatement;

public class OBXACirMutationParser extends CirMutationParser {

	@Override
	protected CirStatement get_location(CirTree cir_tree, AstMutation mutation) throws Exception {
		return (CirStatement) this.get_cir_node(cir_tree, mutation.get_location(), CirBinAssignStatement.class);
	}

	@Override
	protected void generate_infections(CirMutations mutations, CirTree cir_tree, CirStatement statement,
			AstMutation mutation, Map<CirStateError, CirConstraint> infections) throws Exception {
		CirAssignStatement assign_stmt = (CirAssignStatement) this.get_cir_node(
				cir_tree, mutation.get_location(), CirBinAssignStatement.class);
		CirComputeExpression expression = (CirComputeExpression) assign_stmt.get_rvalue();
		CirExpression loperand = expression.get_operand(0), roperand = expression.get_operand(1);
		CirSetOperatorParsers.generate_infections(mutation, statement, 
				expression, loperand, roperand, mutations, infections);
	}

}
