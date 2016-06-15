package org.harca.seg.achados.dao;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

import javax.swing.JOptionPane;

import org.harca.seg.achados.control.Control;
import org.harca.seg.achados.model.*;

public class Sql {

	Connection c = null;
    PreparedStatement stmt = null;
    public Sql(){
    	conectar();
    }
	public void conectar(){
		 
		    try {
		      Class.forName("org.sqlite.JDBC");
		      c = DriverManager.getConnection("jdbc:sqlite:achados2.db");
		      c.setAutoCommit(false);
		    }catch(Exception e){
		    	e.printStackTrace();
		    }
	}
	
	public List<List<String>> selectDevolvidos(){
			
			String query = "SELECT id,objetoNome,objetoDescricao,lugarEncontrado, diaEncontrado, mesEncontrado,anoEncontrado, horaEncontrado, minutoEncontrado, "
					+ "matriculaIsi, diaRecebido,mesRecebido,anoRecebido, horaRecebido,minutoRecebido, escaninho FROM achadoseperdidos WHERE retirouMatricula IS NOT NULL;";
			
			
			List<List<String>> lista2 = new ArrayList<>();
			
			try {
							
				stmt = c.prepareStatement(query);
				ResultSet rs = stmt.executeQuery();
			
				
				while(rs.next()){
					List<String> lista = new ArrayList<>();
					lista.add(rs.getString("id"));
					lista.add(rs.getString("objetoNome"));
					lista.add(rs.getString("objetoDescricao"));
					lista.add(rs.getString("lugarEncontrado"));
					lista.add(rs.getString("diaEncontrado")+"/"+rs.getString("mesEncontrado")+"/"+rs.getString("anoEncontrado"));
					lista.add(rs.getString("horaEncontrado")+":"+rs.getString("minutoEncontrado"));
					lista.add(rs.getString("matriculaIsi"));
					lista.add(rs.getString("diaRecebido")+"/"+rs.getString("mesRecebido")+"/"+rs.getString("anoRecebido"));
					lista.add(rs.getString("horaRecebido")+":"+rs.getString("minutoRecebido"));
					lista.add(rs.getString("Escaninho"));
		
					lista2.add(lista);
				}
				
				
				stmt.close();
				
				c.close();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return lista2;
		}
		
	
	public void update(List<String>lista){
		try{
			
			for (int i=0;i<2;i++) 
				System.out.println(lista.get(i));
			
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			Date data = new Date();
			Calendario  calendario = new Calendario(dateFormat.format(data));
			
			
			String matricula;
			org.harca.seg.achados.control.Control control = new Control();
			matricula = control.getMatriculaByChave( lista.get(1));
			System.out.print("@@@@"+matricula+"$$$$"+lista.get(1));
			
			
			String query = "UPDATE achadoseperdidos SET retirouMatricula="+matricula+", retirouDia="+calendario.getDia()+", retirouMes="+calendario.getMes()+", retirouAno="+calendario.getAno()+" WHERE id="+lista.get(0)+";";
			stmt = c.prepareStatement(query);
			stmt.execute();
			
			stmt.close();
			c.commit();
			c.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
	}
	public void cadastrar(Achados achados){
		try {
			stmt = c.prepareStatement("INSERT INTO ACHADOSEPERDIDOS("
					+ "objetoNome, objetoDescricao, lugarEncontrado,"
					+ "encontrouMatricula,diaEncontrado,mesEncontrado,anoEncontrado,horaEncontrado,minutoEncontrado,"
					+ "matriculaIsi,diaRecebido,mesRecebido,anoRecebido,horaRecebido,minutoRecebido,"
				//	+ "retirouMatricula,retirouDia,retirouMes,retirouAno,"
					+ "escaninho) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			
					stmt.setString(1, achados.getObjetoNome());
					stmt.setString(2, achados.getObjetoDescricao());
					
					stmt.setString(3, achados.getLugarEncontrado());
					stmt.setString(4, achados.getEncontrouMatricula());
					
					stmt.setString(5, achados.getDataEncontrado().getDia());
					stmt.setString(6, achados.getDataEncontrado().getMes());
					stmt.setString(7, achados.getDataEncontrado().getAno());
					
					stmt.setString(8, achados.getHoraEncontrado().getHora());
					stmt.setString(9, achados.getHoraEncontrado().getMinuto());

					stmt.setString(10, achados.getMatriculaISI());
					stmt.setString(11, achados.getDataRecebido().getDia());
					stmt.setString(12, achados.getDataRecebido().getMes());
					stmt.setString(13, achados.getDataRecebido().getAno());
					stmt.setString(14, achados.getHoraRecebido().getHora());
					stmt.setString(15, achados.getHoraRecebido().getMinuto());
					
					stmt.setString(16, achados.getEscaninho());
					
					stmt.execute();
					
					stmt.close();
					c.commit();
					c.close();
					
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	public void cadastrar(List<String> lista){
		//this.conectar();
		String query;
		query = new String();
		for (int i=0;i<lista.size();i++){
			
		}
		try {
			stmt = c.prepareStatement("INSERT INTO ACHADOSEPERDIDOS("
					+ "objetoNome, objetoDescricao, lugarEncontrado,"
					+ "encontrouMatricula,diaEncontrado,mesEncontrado,anoEncontrado,horaEncontrado,minutoEncontrado,"
					+ "matriculaIsi,diaRecebido,mesRecebido,anoRecebido,horaRecebido,minutoRecebido,"
				//	+ "retirouMatricula,retirouDia,retirouMes,retirouAno,"
					+ "escaninho) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			
			stmt.setString(1, lista.get(0)); // objeto nome
			stmt.setString(2, lista.get(1)); // descricao
			stmt.setString(3, lista.get(2)); // local
			
			stmt.setString(4, lista.get(5)); // encontrou matricula
			
			stmt.setString(5, lista.get(3).substring(0,2)); // dia
			stmt.setString(6, lista.get(3).substring(3, 5)); // mes
			stmt.setString(7, lista.get(3).substring(6, 10)); // ano
			
			stmt.setString(8, lista.get(4).substring(0, 2)); // hora
			stmt.setString(9, lista.get(4).substring(3, 5)); // minuto
			
			stmt.setString(10, lista.get(7)); // isi
			
			stmt.setString(11, lista.get(8).substring(0,2)); // dia
			stmt.setString(12, lista.get(8).substring(3, 5)); // mes
			stmt.setString(13, lista.get(8).substring(6, 10)); // ano
			
			stmt.setString(14, lista.get(9).substring(0, 2)); // hora
			stmt.setString(15, lista.get(9).substring(3, 5)); // minuto
			
			stmt.setString(16, lista.get(7)); // escaninho
			
		
			stmt.execute();
			
			stmt.close();
			c.commit();
			c.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
	}
	
	
public List<List<String>> selectById(String data){
		
		String query = "SELECT id,objetoNome, objetoDescricao, lugarEncontrado,"
					+ "encontrouMatricula,diaEncontrado,mesEncontrado,anoEncontrado,horaEncontrado,minutoEncontrado,"
					+ "matriculaIsi,diaRecebido,mesRecebido,anoRecebido,horaRecebido,minutoRecebido,escaninho FROM achadoseperdidos WHERE id='"+data+"';";
		List<List<String>> lista2 = new ArrayList<>();
		
		try {
						
			stmt = c.prepareStatement(query);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
				List<String> lista = new ArrayList<>();
				lista.add(rs.getString("id"));
				lista.add(rs.getString("objetoNome"));
				lista.add(rs.getString("objetoDescricao"));
				lista.add(rs.getString("lugarEncontrado"));
				lista.add(rs.getString("diaEncontrado")+"/"+rs.getString("mesEncontrado")+"/"+rs.getString("anoEncontrado"));
				lista.add(rs.getString("horaEncontrado")+":"+rs.getString("minutoEncontrado"));
				lista.add(rs.getString("encontrouMatricula"));
				lista.add(rs.getString("matriculaIsi"));
				lista.add(rs.getString("diaRecebido")+"/"+rs.getString("mesRecebido")+"/"+rs.getString("anoRecebido"));
				lista.add(rs.getString("horaRecebido")+":"+rs.getString("minutoRecebido"));
				lista.add(rs.getString("Escaninho"));
				
				lista2.add(lista);
			}
		stmt.close();
		
		c.close();
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	return lista2;
}

public void deletar(String id){
	String query = "DELETE FROM achadoseperdidos WHERE id=?;";
	 
	try{
		stmt = c.prepareStatement(query);
	    stmt.setInt(1, (Integer.parseInt(id)));
		stmt.executeUpdate();
	    
	    c.commit();
		stmt.close();
		c.close();
	}catch(Exception e){
		e.printStackTrace();
		JOptionPane.showMessageDialog(null, "Não foi possível deletar");
	}
	
	

}	


// Resolver
	public String getCountDoMes(int mes){
		
		//String query = "select count(*) from achadoseperdidos where mesEncontrado is=?;";
		String mesFormatado = "";
		if (mes < 10){
			mesFormatado = "0"+mes;
		}else { mesFormatado = Integer.toString(mes);}
		
		String query = "select count(*) from achadoseperdidos where mesEncontrado = ?;";
		int count = 0;
		try {
			
			
			stmt = c.prepareStatement(query);
		    stmt.setInt(1, (Integer.parseInt(mesFormatado)+1) );
		    
		    System.out.println(mes+" ====");
			ResultSet rs = stmt.executeQuery();
		    count = rs.getInt(1);
		 
		    System.out.println(count+" ====");
			stmt.close();
			c.close();
			
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return Integer.toString(count);
		
	}
	//////////////////////////////////////////////////////////////////////
	
	
	public List<List<String>> selectByTipo(String data){
		//String query = "SELECT objetoNome,objetoDescricao,lugarEncontrado,escaninho FROM achadoseperdidos WHERE diaEncontrado=11";//+data.substring(0, 2);
		String query = "SELECT id,objetoNome, objetoDescricao, lugarEncontrado,"
					+ "encontrouMatricula,diaEncontrado,mesEncontrado,anoEncontrado,horaEncontrado,minutoEncontrado,"
					+ "matriculaIsi,diaRecebido,mesRecebido,anoRecebido,horaRecebido,minutoRecebido,escaninho FROM achadoseperdidos WHERE objetoNome LIKE '%"+data+"%';";
		List<List<String>> lista2 = new ArrayList<>();
		
		try {
						
			stmt = c.prepareStatement(query);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
				List<String> lista = new ArrayList<>();
				lista.add(rs.getString("id"));
				lista.add(rs.getString("objetoNome"));
				lista.add(rs.getString("objetoDescricao"));
				lista.add(rs.getString("lugarEncontrado"));
				lista.add(rs.getString("diaEncontrado")+"/"+rs.getString("mesEncontrado")+"/"+rs.getString("anoEncontrado"));
				lista.add(rs.getString("horaEncontrado")+":"+rs.getString("minutoEncontrado"));
				lista.add(rs.getString("matriculaIsi"));
				lista.add(rs.getString("diaRecebido")+"/"+rs.getString("mesRecebido")+"/"+rs.getString("anoRecebido"));
				lista.add(rs.getString("horaRecebido")+":"+rs.getString("minutoRecebido"));
				lista.add(rs.getString("Escaninho"));
				
				lista2.add(lista);
			}
		stmt.close();
		
		c.close();
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	return lista2;
}
	
	
	
	public List<List<String>> selectByDate(String data){
		
		Calendario calendario = new Calendario(data);
		String query = "SELECT id,objetoNome, objetoDescricao, lugarEncontrado,"
					+ "encontrouMatricula,diaEncontrado,mesEncontrado,anoEncontrado,horaEncontrado,minutoEncontrado,"
					+ "matriculaIsi,diaRecebido,mesRecebido,anoRecebido,horaRecebido,minutoRecebido,escaninho FROM achadoseperdidos WHERE diaEncontrado='"+calendario.getDia()+"'"
							+ "AND mesEncontrado='"+calendario.getMes()+"' AND anoEncontrado='"+calendario.getAno()+"';";
		List<List<String>> lista2 = new ArrayList<>();
		
		try {
						
			stmt = c.prepareStatement(query);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
				List<String> lista = new ArrayList<>();
				lista.add(rs.getString("id"));
				lista.add(rs.getString("objetoNome"));
				lista.add(rs.getString("objetoDescricao"));
				lista.add(rs.getString("lugarEncontrado"));
				lista.add(rs.getString("diaEncontrado")+"/"+rs.getString("mesEncontrado")+"/"+rs.getString("anoEncontrado"));
				lista.add(rs.getString("horaEncontrado")+":"+rs.getString("minutoEncontrado"));
				lista.add(rs.getString("matriculaIsi"));
				lista.add(rs.getString("diaRecebido")+"/"+rs.getString("mesRecebido")+"/"+rs.getString("anoRecebido"));
				lista.add(rs.getString("horaRecebido")+":"+rs.getString("minutoRecebido"));
				lista.add(rs.getString("Escaninho"));
				
				lista2.add(lista);
			}
		stmt.close();
		
		c.close();
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	return lista2;
}
	
		
public List<List<String>> selectByMes(String data){
		
	
		
		//Calendario calendario = new Calendario(data);
		String query = "SELECT id,objetoNome, objetoDescricao, lugarEncontrado,"
					+ "encontrouMatricula,diaEncontrado,mesEncontrado,anoEncontrado,horaEncontrado,minutoEncontrado,"
					+ "matriculaIsi,diaRecebido,mesRecebido,anoRecebido,horaRecebido,minutoRecebido,escaninho FROM achadoseperdidos WHERE mesEncontrado='"+data+"';";
		List<List<String>> lista2 = new ArrayList<>();
		
		try {
						
			stmt = c.prepareStatement(query);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
				List<String> lista = new ArrayList<>();
				lista.add(rs.getString("id"));
				lista.add(rs.getString("objetoNome"));
				lista.add(rs.getString("objetoDescricao"));
				lista.add(rs.getString("lugarEncontrado"));
				lista.add(rs.getString("diaEncontrado")+"/"+rs.getString("mesEncontrado")+"/"+rs.getString("anoEncontrado"));
				lista.add(rs.getString("horaEncontrado")+":"+rs.getString("minutoEncontrado"));
				lista.add(rs.getString("matriculaIsi"));
				lista.add(rs.getString("diaRecebido")+"/"+rs.getString("mesRecebido")+"/"+rs.getString("anoRecebido"));
				lista.add(rs.getString("horaRecebido")+":"+rs.getString("minutoRecebido"));
				lista.add(rs.getString("Escaninho"));
				
				lista2.add(lista);
			}
		stmt.close();
		
		c.close();
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	return lista2;
}
	
	public List<List<String>> selectBasico(){
		
		String query = "SELECT id,objetoNome,objetoDescricao,lugarEncontrado, diaEncontrado, mesEncontrado,anoEncontrado, horaEncontrado, minutoEncontrado, "
				+ "matriculaIsi, diaRecebido,mesRecebido,anoRecebido, horaRecebido,minutoRecebido, escaninho FROM achadoseperdidos;";
		
		
		List<List<String>> lista2 = new ArrayList<>();
		
		try {
						
			stmt = c.prepareStatement(query);
			ResultSet rs = stmt.executeQuery();
		
			
			while(rs.next()){
				List<String> lista = new ArrayList<>();
				lista.add(rs.getString("id"));
				lista.add(rs.getString("objetoNome"));
				lista.add(rs.getString("objetoDescricao"));
				lista.add(rs.getString("lugarEncontrado"));
				lista.add(rs.getString("diaEncontrado")+"/"+rs.getString("mesEncontrado")+"/"+rs.getString("anoEncontrado"));
				lista.add(rs.getString("horaEncontrado")+":"+rs.getString("minutoEncontrado"));
				lista.add(rs.getString("matriculaIsi"));
				lista.add(rs.getString("diaRecebido")+"/"+rs.getString("mesRecebido")+"/"+rs.getString("anoRecebido"));
				lista.add(rs.getString("horaRecebido")+":"+rs.getString("minutoRecebido"));
				lista.add(rs.getString("Escaninho"));
	
				lista2.add(lista);
			}
			
			
			stmt.close();
			
			c.close();
			
		} catch (SQLException e) {
			System.out.println("Erro SQL");
		}
		
		
		return lista2;
	}
	
}