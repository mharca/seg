package org.harca.seg.garagem.dao;
import org.harca.seg.garagem.control.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class Sql {
	Connection c = null;
    PreparedStatement stmt = null;
    public Sql(){
    	conectar();
    }
	public void conectar(){
		 
		    try {
		      Class.forName("org.sqlite.JDBC");
		      c = DriverManager.getConnection("jdbc:sqlite:garagem.db");
		      c.setAutoCommit(false);
		    }catch(Exception e){
		    	e.printStackTrace();
		    }
	}
	
	public void cadastrarTag(TagUser t){
		try {
			stmt = c.prepareStatement("INSERT INTO TAG(placa,matrchave,nome,data,obs) values(?,?,?,?,?)");
			stmt.setString(1, t.getPlaca());
			stmt.setString(2, t.getChaveMat());
			stmt.setString(3, t.getNome());
			stmt.setString(4, t.getData());
			stmt.setString(5, t.getObs());
			
			stmt.execute();
			
			stmt.close();
			c.commit();
			c.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
	public List<List<String>> selectTag()
	{
		String query = "SELECT * FROM TAG ORDER BY nome;";
		List<List<String>> l2 = new ArrayList<>();
		try{
			stmt = c.prepareStatement(query);
			ResultSet rs = stmt.executeQuery();
			
			if(! rs.wasNull())
			while(rs.next()){
				List<String> lista = new ArrayList<>();
				lista.add(rs.getString("id"));
				lista.add(rs.getString("placa"));
				lista.add(rs.getString("matrChave"));
				lista.add(rs.getString("Nome"));
				lista.add(rs.getString("Data"));
				lista.add(rs.getString("obs"));
				
				l2.add(lista);
			}
			

			stmt.close();
			c.close();
	}catch(Exception e){
		e.printStackTrace();
		System.out.println("Erro tabela");
	}
		
		return l2;
		
		
	}
	
	public void delete(int id){
		String query = "DELETE FROM TAG WHERE ID=?";
		try{
			stmt = c.prepareStatement(query);
		    stmt.setInt(1, id);
			stmt.executeUpdate();
		    
		    c.commit();
			stmt.close();
			c.close();
		}catch(Exception e){
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "N�o foi poss�vel deletar");
		
		}
	}
		
}

