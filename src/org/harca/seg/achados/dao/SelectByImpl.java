package org.harca.seg.achados.dao;

public class SelectByImpl implements SelectBy {

	String query;
	@Override
	public void init() {
		
		
	}
	@Override
	public void setQuery(String query) {
		this.query = query;
		
	}
	@Override
	public String getQuery() {
		// TODO Auto-generated method stub
		return this.query;
	}

}
