package com.jcsa.jcparse.lang.astree.impl.stmt;

import com.jcsa.jcparse.lang.astree.impl.AstFixedNode;
import com.jcsa.jcparse.lang.astree.stmt.AstLabel;
import com.jcsa.jcparse.lang.scope.CName;

public class AstLabelImpl extends AstFixedNode implements AstLabel {

	private String name;

	public AstLabelImpl(String name) throws Exception {
		super(0);

		if (name == null || name.isEmpty())
			throw new IllegalArgumentException("Invalid name: null");
		else
			this.name = name;
	}

	@Override
	public String get_name() {
		return name;
	}

	protected CName cname;

	@Override
	public CName get_cname() {
		return cname;
	}

	@Override
	public void set_cname(CName name) {
		this.cname = name;
	}

}
