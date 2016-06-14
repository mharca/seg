package org.harca.seg.achados.dao;

public interface SelectBy {
	void init();
	void setQuery(String query);
	String getQuery();
}
