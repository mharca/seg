package org.harca.seg.chaves.control;

//import java.awt.List;

import java.util.List;

import org.harca.seg.chaves.dao.Sql;

public class Controle {
	Sql sql;
	public Controle(){
		sql = new Sql();
	}
	public List<Key>selectAll(){
		
		return sql.selectAll(); 
	}
}
