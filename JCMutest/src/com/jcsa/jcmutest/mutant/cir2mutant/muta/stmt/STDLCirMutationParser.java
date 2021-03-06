package com.jcsa.jcmutest.mutant.cir2mutant.muta.stmt;

import java.util.Map;

import com.jcsa.jcmutest.mutant.cir2mutant.cerr.CirConstraint;
import com.jcsa.jcmutest.mutant.cir2mutant.cerr.CirMutations;
import com.jcsa.jcmutest.mutant.cir2mutant.cerr.CirStateError;
import com.jcsa.jcmutest.mutant.cir2mutant.muta.CirMutationParser;
import com.jcsa.jcmutest.mutant.mutation.AstMutation;
import com.jcsa.jcparse.lang.astree.AstNode;
import com.jcsa.jcparse.lang.irlang.CirTree;
import com.jcsa.jcparse.lang.irlang.graph.CirExecution;
import com.jcsa.jcparse.lang.irlang.graph.CirExecutionFlow;
import com.jcsa.jcparse.lang.irlang.graph.CirExecutionFlowType;
import com.jcsa.jcparse.lang.irlang.stmt.CirStatement;

public class STDLCirMutationParser extends CirMutationParser {

	@Override
	protected CirStatement get_location(CirTree cir_tree, AstMutation mutation) throws Exception {
		return this.get_beg_statement(cir_tree, mutation.get_location());
	}
	
	/**
	 * @param statement
	 * @param location
	 * @return whether the statement in the range of the location
	 * @throws Exception
	 */
	private boolean in_location(CirStatement statement, AstNode location) throws Exception {
		if(statement.get_ast_source() != null) {
			AstNode ast_source = statement.get_ast_source();
			while(ast_source != null) {
				if(ast_source == location)
					return true;
				else
					ast_source = ast_source.get_parent();
			}
			return false;
		}
		else {
			return false;
		}
	}
	
	@Override
	protected void generate_infections(CirMutations mutations, CirTree cir_tree, CirStatement statement,
			AstMutation mutation, Map<CirStateError, CirConstraint> infections) throws Exception {
		CirStatement beg_statement = this.get_beg_statement(cir_tree, mutation.get_location());
		CirStatement end_statement = this.get_end_statement(cir_tree, mutation.get_location());
		CirExecution beg_execution = cir_tree.get_localizer().get_execution(beg_statement);
		CirExecution end_execution = cir_tree.get_localizer().get_execution(end_statement);
		
		if(this.in_location(beg_statement, mutation.get_location())) {
			beg_execution = beg_execution.get_in_flow(0).get_source();
		}
		if(this.in_location(end_statement, mutation.get_location())) {
			end_execution = end_execution.get_ou_flow(0).get_target();
		}
		
		CirExecutionFlow orig_flow = beg_execution.get_ou_flow(0);
		CirExecutionFlow muta_flow = CirExecutionFlow.invalid_flow(
				CirExecutionFlowType.next_flow, beg_execution, end_execution);
		CirStateError state_error = mutations.flow_error(orig_flow, muta_flow);
		
		infections.put(state_error, mutations.expression_constraint(beg_statement, Boolean.TRUE, true));
	}

}
