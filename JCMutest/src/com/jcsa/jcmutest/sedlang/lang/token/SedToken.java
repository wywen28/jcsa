package com.jcsa.jcmutest.sedlang.lang.token;

import com.jcsa.jcmutest.sedlang.lang.SedNode;

/**
 * The token is non-expression code model of CirNode, including:<br>
 * <code>
 * 	SedToken																<br>
 * 	|--	SedField						{name: String}						<br>
 * 	|--	SedOperator						{operator: COperator}				<br>
 * 	|--	SedKeyword						{keyword: SedKeywords}				<br>
 * 	|--	SedStatement					{cir_statement: CirStatement}		<br>
 * 	|--	SedArgumentList														<br>
 * </code>
 * @author yukimula
 *
 */
public abstract class SedToken extends SedNode { }