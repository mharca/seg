package org.harca.seg.garagem.control;

import java.util.List;

import org.harca.seg.garagem.dao.Sql;

public class Control {
	
	public void insertTag(TagUser t){
		Sql sql = new Sql();
		sql.cadastrarTag(t);
		
	}
	public List<List<String>> selectTag(){
		Sql sql = new Sql();
		return sql.selectTag();
		
	}
	public List<List<String>> filtrarTag(String filtro){
		Sql sql = new Sql();
		return sql.filtrarTag(filtro);
		
	}
	
	
	public void delete(int i){
		Sql sql = new Sql();
		sql.delete(i);
	}
}
