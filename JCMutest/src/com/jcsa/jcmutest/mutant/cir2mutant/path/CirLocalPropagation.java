package com.jcsa.jcmutest.mutant.cir2mutant.path;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import com.jcsa.jcmutest.mutant.cir2mutant.model.CirConstraint;
import com.jcsa.jcmutest.mutant.cir2mutant.model.CirExpressionError;
import com.jcsa.jcmutest.mutant.cir2mutant.model.CirMutation;
import com.jcsa.jcmutest.mutant.cir2mutant.model.CirMutations;
import com.jcsa.jcmutest.mutant.cir2mutant.model.CirReferenceError;
import com.jcsa.jcmutest.mutant.cir2mutant.model.CirStateError;
import com.jcsa.jcmutest.mutant.cir2mutant.path.impl.CirAddressOfPropagator;
import com.jcsa.jcmutest.mutant.cir2mutant.path.impl.CirArgumentListPropagator;
import com.jcsa.jcmutest.mutant.cir2mutant.path.impl.CirArithAddPropagator;
import com.jcsa.jcmutest.mutant.cir2mutant.path.impl.CirArithDivPropagator;
import com.jcsa.jcmutest.mutant.cir2mutant.path.impl.CirArithModPropagator;
import com.jcsa.jcmutest.mutant.cir2mutant.path.impl.CirArithMulPropagator;
import com.jcsa.jcmutest.mutant.cir2mutant.path.impl.CirArithNegPropagator;
import com.jcsa.jcmutest.mutant.cir2mutant.path.impl.CirArithSubPropagator;
import com.jcsa.jcmutest.mutant.cir2mutant.path.impl.CirAssignPropagator;
import com.jcsa.jcmutest.mutant.cir2mutant.path.impl.CirBitwsAndPropagator;
import com.jcsa.jcmutest.mutant.cir2mutant.path.impl.CirBitwsIorPropagator;
import com.jcsa.jcmutest.mutant.cir2mutant.path.impl.CirBitwsLshPropagator;
import com.jcsa.jcmutest.mutant.cir2mutant.path.impl.CirBitwsRshPropagator;
import com.jcsa.jcmutest.mutant.cir2mutant.path.impl.CirBitwsRsvPropagator;
import com.jcsa.jcmutest.mutant.cir2mutant.path.impl.CirBitwsXorPropagator;
import com.jcsa.jcmutest.mutant.cir2mutant.path.impl.CirDereferencePropagator;
import com.jcsa.jcmutest.mutant.cir2mutant.path.impl.CirEqualWithPropagator;
import com.jcsa.jcmutest.mutant.cir2mutant.path.impl.CirFieldOfPropagator;
import com.jcsa.jcmutest.mutant.cir2mutant.path.impl.CirGreaterEqPropagator;
import com.jcsa.jcmutest.mutant.cir2mutant.path.impl.CirGreaterTnPropagator;
import com.jcsa.jcmutest.mutant.cir2mutant.path.impl.CirInitializerPropagator;
import com.jcsa.jcmutest.mutant.cir2mutant.path.impl.CirLogicAndPropagator;
import com.jcsa.jcmutest.mutant.cir2mutant.path.impl.CirLogicIorPropagator;
import com.jcsa.jcmutest.mutant.cir2mutant.path.impl.CirLogicNotPropagator;
import com.jcsa.jcmutest.mutant.cir2mutant.path.impl.CirNotEqualsPropagator;
import com.jcsa.jcmutest.mutant.cir2mutant.path.impl.CirSmallerEqPropagator;
import com.jcsa.jcmutest.mutant.cir2mutant.path.impl.CirSmallerTnPropagator;
import com.jcsa.jcmutest.mutant.cir2mutant.path.impl.CirTypeCastPropagator;
import com.jcsa.jcmutest.mutant.cir2mutant.path.impl.CirWaitValuePropagator;
import com.jcsa.jcparse.lang.irlang.CirNode;
import com.jcsa.jcparse.lang.irlang.expr.CirAddressExpression;
import com.jcsa.jcparse.lang.irlang.expr.CirCastExpression;
import com.jcsa.jcparse.lang.irlang.expr.CirComputeExpression;
import com.jcsa.jcparse.lang.irlang.expr.CirDeferExpression;
import com.jcsa.jcparse.lang.irlang.expr.CirFieldExpression;
import com.jcsa.jcparse.lang.irlang.expr.CirInitializerBody;
import com.jcsa.jcparse.lang.irlang.expr.CirWaitExpression;
import com.jcsa.jcparse.lang.irlang.stmt.CirArgumentList;
import com.jcsa.jcparse.lang.irlang.stmt.CirAssignStatement;
import com.jcsa.jcparse.lang.lexical.COperator;
import com.jcsa.jcparse.test.state.CStateContexts;

/**
 * It implements the error propagation within one statement.
 * 
 * @author yukimula
 *
 */
public class CirLocalPropagation {
	
	/* propagation */
	private static final Map<COperator, CirErrorPropagator> 
		propagators = new HashMap<COperator, CirErrorPropagator>();
	private static final Map<CirStateError, CirConstraint> 
		propagations = new HashMap<CirStateError, CirConstraint>();
	
	static {
		propagators.put(COperator.negative, 	new CirArithNegPropagator());
		propagators.put(COperator.bit_not, 		new CirBitwsRsvPropagator());
		propagators.put(COperator.logic_not, 	new CirLogicNotPropagator());
		propagators.put(COperator.address_of, 	new CirAddressOfPropagator());
		propagators.put(COperator.dereference, 	new CirDereferencePropagator());
		
		propagators.put(COperator.arith_add, 	new CirArithAddPropagator());
		propagators.put(COperator.arith_sub, 	new CirArithSubPropagator());
		propagators.put(COperator.arith_mul, 	new CirArithMulPropagator());
		propagators.put(COperator.arith_div, 	new CirArithDivPropagator());
		propagators.put(COperator.arith_mod, 	new CirArithModPropagator());
		
		propagators.put(COperator.bit_and, 		new CirBitwsAndPropagator());
		propagators.put(COperator.bit_or, 		new CirBitwsIorPropagator());
		propagators.put(COperator.bit_xor, 		new CirBitwsXorPropagator());
		propagators.put(COperator.left_shift, 	new CirBitwsLshPropagator());
		propagators.put(COperator.righ_shift, 	new CirBitwsRshPropagator());
		
		propagators.put(COperator.logic_and, 	new CirLogicAndPropagator());
		propagators.put(COperator.logic_or, 	new CirLogicIorPropagator());
		
		propagators.put(COperator.greater_tn, 	new CirGreaterTnPropagator());
		propagators.put(COperator.greater_eq, 	new CirGreaterEqPropagator());
		propagators.put(COperator.smaller_tn, 	new CirSmallerTnPropagator());
		propagators.put(COperator.smaller_eq, 	new CirSmallerEqPropagator());
		propagators.put(COperator.equal_with, 	new CirEqualWithPropagator());
		propagators.put(COperator.not_equals, 	new CirNotEqualsPropagator());
		
		/* special case */
		propagators.put(COperator.assign, 		new CirAssignPropagator());
		propagators.put(COperator.arith_add_assign, new CirTypeCastPropagator());
		propagators.put(COperator.arith_sub_assign, new CirInitializerPropagator());
		propagators.put(COperator.arith_mul_assign, new CirFieldOfPropagator());
		propagators.put(COperator.arith_div_assign, new CirWaitValuePropagator());
		propagators.put(COperator.arith_mod_assign, new CirArgumentListPropagator());
	}
	
	/* local propagation algorithms */
	public static Collection<CirMutation> propagate(CirMutations cir_mutations,
			CirMutation mutation, CStateContexts contexts) throws Exception {
		if(cir_mutations == null)
			throw new IllegalArgumentException("Invalid cir_mutations");
		else if(mutation == null)
			throw new IllegalArgumentException("Invalid mutation: null");
		else {
			List<CirMutation> results = new ArrayList<CirMutation>();
			CirStateError state_error = mutation.get_state_error();
			
			if(cir_mutations.optimize(mutation, contexts) != null) {
				CirNode source_location, target_location;
				if(state_error instanceof CirExpressionError) {
					source_location = ((CirExpressionError) state_error).get_expression();
				}
				else if(state_error instanceof CirReferenceError) {
					source_location = ((CirReferenceError) state_error).get_reference();
				}
				else {
					source_location = null; target_location = null;
				}
				
				if(source_location != null) {
					target_location = source_location.get_parent();
					
					CirErrorPropagator propagator;
					if(target_location instanceof CirDeferExpression) {
						propagator = propagators.get(COperator.dereference);
					}
					else if(target_location instanceof CirFieldExpression) {
						propagator = propagators.get(COperator.arith_mul_assign);
					}
					else if(target_location instanceof CirAddressExpression) {
						propagator = propagators.get(COperator.address_of);
					}
					else if(target_location instanceof CirCastExpression) {
						propagator = propagators.get(COperator.arith_add_assign);
					}
					else if(target_location instanceof CirComputeExpression) {
						propagator = propagators.get(
								((CirComputeExpression) target_location).get_operator());
					}
					else if(target_location instanceof CirInitializerBody) {
						propagator = propagators.get(COperator.arith_sub_assign);
					}
					else if(target_location instanceof CirWaitExpression) {
						propagator = propagators.get(COperator.arith_div_assign);
					}
					else if(target_location instanceof CirArgumentList) {
						propagator = propagators.get(COperator.arith_mod_assign);
					}
					else if(target_location instanceof CirAssignStatement) {
						propagator = propagators.get(COperator.assign);
					}
					else { /* ignore the propagation */ propagator = null; }
					
					propagations.clear();
					if(propagator != null) {
						propagator.propagate(cir_mutations, 
								state_error, source_location, target_location, propagations);
					}
					
					for(CirStateError target_error : propagations.keySet()) {
						CirConstraint constraint = propagations.get(target_error);
						CirMutation new_mutation = cir_mutations.new_mutation(constraint, target_error);
						new_mutation = cir_mutations.optimize(new_mutation, contexts);
						if(new_mutation != null) { results.add(new_mutation); }
					}
				}
			}
			
			return results;
		}
	}
	/**
	 * @param cir_mutations
	 * @param mutation
	 * @param contexts
	 * @return generate the set of state errors propagated from the source mutation
	 * 		   within the range of one statement.
	 * @throws Exception
	 */
	public static Collection<CirMutation> propagate_within(CirMutations cir_mutations,
			CirMutation mutation, CStateContexts contexts) throws Exception {
		Queue<CirMutation> queue = new LinkedList<CirMutation>();
		Set<CirMutation> results = new HashSet<CirMutation>();
		
		queue.add(mutation);
		while(!queue.isEmpty()) {
			/* get the next generation */
			mutation = queue.poll(); 
			Iterable<CirMutation> next_mutations = 
					propagate(cir_mutations, mutation, contexts);
			for(CirMutation next_mutation : next_mutations) {
				queue.add(next_mutation);
			}
			
			/* record the results */
			mutation = cir_mutations.optimize(mutation, contexts);
			if(mutation != null) results.add(mutation);
		}
		
		return results;
	}
	
}
