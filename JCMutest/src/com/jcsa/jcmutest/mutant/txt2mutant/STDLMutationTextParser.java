package com.jcsa.jcmutest.mutant.txt2mutant;

import com.jcsa.jcmutest.mutant.mutation.AstMutation;
import com.jcsa.jcparse.lang.astree.AstNode;
import com.jcsa.jcparse.lang.astree.stmt.AstStatement;

public class STDLMutationTextParser extends MutationTextParser {

	@Override
	protected AstNode get_location(AstMutation source) throws Exception {
		AstStatement statement = (AstStatement) source.get_location();
		return statement;
	}

	@Override
	protected String get_muta_code(AstMutation source, AstNode location) throws Exception {
		return ";";
	}

}
