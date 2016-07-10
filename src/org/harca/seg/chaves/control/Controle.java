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
	public List<Key> selectByAndarEtorre(int andar,String torre){
		return sql.selectByAndarEtorre(andar, torre);
	}
	public List<Key> selectByWord(String s){
		return sql.selectByWord(s);
	}
	public void inserirEmprestimo(int key_id, int matricula,String nome){
		Sql sql= new Sql();
		sql.inserirEmprestimo(key_id, matricula, nome);
	}
	public List<List<String>> selectEmprestados(){
		Sql sql = new Sql();
		return sql.selectEmprestados();
		
	}
}
