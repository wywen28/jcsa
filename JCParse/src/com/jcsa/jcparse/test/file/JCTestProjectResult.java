package com.jcsa.jcparse.test.file;

import java.io.File;

import com.jcsa.jcparse.lang.astree.AstTree;
import com.jcsa.jcparse.test.CommandUtil;
import com.jcsa.jcparse.test.path.read.InstrumentalList;

/**
 * It provides the interfaces to fetch the results generated during the
 * testing process.
 * 
 * @author yukimula
 *
 */
public class JCTestProjectResult {
	
	/* data construction */
	/** the project that the result serves for **/
	private JCTestProject project;
	/**
	 * @param project
	 */
	protected JCTestProjectResult(JCTestProject project) {
		this.project = project;
	}
	/** 
	 * @return the project that the result serves for
	 */
	public JCTestProject get_project() { return this.project; }
	
	/* data loaders */
	/**
	 * @param input the test input of which result is fetched
	 * @return the standard output information generated when program is
	 * 			executed against the test input as specified or null if
	 * 			the program has not been executed against the input and
	 * 			no information is generated from its standard output.
	 * @throws Exception
	 */
	public String load_stdout(TestInput input) throws Exception {
		File stdout_file = input.get_stdout_file(this.project.
				get_project_files().get_normal_output_directory());
		if(stdout_file.exists()) {
			return CommandUtil.read_text(stdout_file);
		}
		else {
			return null;
		}
	}
	/**
	 * @param input the test input of which result is fetched
	 * @return the standard error information generated when program is
	 * 			executed against the test input as specified or null if
	 * 			the program has not been executed against the input and
	 * 			no information is generated from its standard errors.
	 * @throws Exception
	 */
	public String load_stderr(TestInput input) throws Exception {
		File stderr_file = input.get_stderr_file(this.project.
				get_project_files().get_normal_output_directory());
		if(stderr_file.exists()) {
			return CommandUtil.read_text(stderr_file);
		}
		else {
			return null;
		}
	}
	/**
	 * @param ast_tree the abstract syntax tree to interpret the test result
	 * @param input the test input of which result is fetched
	 * @return the list of instrumental analysis results fetched from the file
	 * 			by interpreting the abstract syntax tree specified in parameter
	 * 			or null if the instrumental testing was not performed yet.
	 * @throws Exception
	 */
	public InstrumentalList load_instrument(AstTree ast_tree, TestInput input) throws Exception {
		File instrument_file = input.get_instrument_file(this.project.
				get_project_files().get_instrument_output_directory());
		if(instrument_file.exists()) {
			return InstrumentalList.list(ast_tree, instrument_file);
		}
		else {
			return null;
		}
	}
	
}
