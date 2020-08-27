package com.jcsa.jcmutest.mutant.sad2mutant.lang;

import com.jcsa.jcparse.lang.irlang.CirNode;

/**
 * <code>
 * 	|--	SadMutationAssertion				{location: SadStatement}	<br>
 * 	|--	|--	SadSetLabelAssertion										<br>
 * 	|--	|--	SadSetExpressionAssertion									<br>
 * 	|--	|--	SadAddOperandAssertion										<br>
 * 	|--	|--	SadInsOperandAssertion										<br>
 * 	|--	|--	SadInsOperatorAssertion										<br>
 * </code>
 * @author yukimula
 *
 */
public abstract class SadMutationAssertion extends SadAssertion {

	protected SadMutationAssertion(CirNode source) {
		super(source);
	}
	
	@Override
	public String generate_code() throws Exception {
		return "seed#" + this.get_location().generate_code() + 
								"::{" + this.generate_content() + "}";
	}
	
	protected abstract String generate_content() throws Exception;
	
}