package org.harca.seg.achados.control;
import org.harca.seg.achados.dao.*;
import org.harca.seg.util.*;

import org.harca.seg.achados.model.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Control {

	public void cadastrar(List<String> lista){
		Sql sql = new Sql();
		//sql.cadastrar(lista);
	//	lista.get(0);
		Achados achados = new Achados();
		
		achados.setObjetoNome(lista.get(0));
		achados.setObjetoDescricao(lista.get(1));
		achados.setLugarEncontrado(lista.get(2));
		achados.setEncontrouMatricula(lista.get(5));
		Calendario calEncontrol = new Calendario(lista.get(3));
		achados.setDataEncontrado(calEncontrol);
		
		Hora horaEncontrado = new Hora(lista.get(4));
		achados.setHoraEncontrado(horaEncontrado);
		
		Hora horaRecebido = new Hora(lista.get(9));
		achados.setHoraRecebido(horaRecebido);
		
		Calendario dataRecebido = new Calendario(lista.get(8));
		achados.setDataRecebido(dataRecebido);
		
		achados.setEscaninho(lista.get(10));
		achados.setMatriculaISI(lista.get(7));
		
		
		//debug
		System.out.println(achados.getObjetoNome());
		System.out.println(achados.getObjetoDescricao());
		System.out.println(achados.getEncontrouMatricula());
		sql.cadastrar(achados);
	}
	
	public List<List<String>> selectDevolvidos(){
		return new Sql().selectDevolvidos();
	}
	
	public String selectMatriculaById(String id){
		String matricula;
		List<List<String>> ls = new ArrayList<>();
		ls = new Sql().selectById(id);
		return ls.get(0).get(6);
	}
	public void update(List<String> lista)
	{
		Sql sql = new Sql();
		sql.update(lista);
	}
	public List<List<String>> selectBasico(){
		Sql sql = new Sql();
		
		return sql.selectBasico();
		
	}
	public void criaWord(String ls, String matriculaRetirou){
		CriaWord criaword = new CriaWord(ls, matriculaRetirou);
		
	}
	
	public List<List<String>> selectByDate(String date){
		Sql sql = new Sql();
		return sql.selectByDate(date);
	}
	public List<List<String>> selectByTipo(String date){
		Sql sql = new Sql();
		return sql.selectByTipo(date);
	}
	public List<List<String>> selectByMes(String date){
		Sql sql = new Sql();
		return sql.selectByMes(date);
	}
	
	public String getNomeByChave(String chave){
		HtmlParser parser = new HtmlParser(chave);
		
		return parser.getNome();
	}
	public String getRamalByChave(String chave){
		HtmlParser parser = new HtmlParser(chave);
		return parser.getRamal();
		
	}
	public String getMatriculaByChave(String chave)
	{
		HtmlParser parser = new HtmlParser(chave);
		if(chave.length() < 5)
			return parser.getMatricula();
		else return chave; // ja eh matricula
	}
	public String getFotoByMatriculaChave(String chave){
		return "http://apl.ti.petrobras.com.br/fotos/0"+getMatriculaByChave(chave)+".jpg";
	}
	public void delete(String id){
		Sql sql = new Sql();
		sql.deletar(id);
	}
	public String getCountDoMesAtual(){
		Sql sql = new Sql();
		
		return  sql.getCountDoMes(Calendar.getInstance().get(Calendar.MONTH)) ;
	}
	
	public String getFotoByMatriculaChave(String mat){
		return "http://apl.ti.petrobras.com.br/fotos/0"+mat+".jpg";
	}
}
