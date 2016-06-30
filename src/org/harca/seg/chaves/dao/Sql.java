package org.harca.seg.chaves.dao;
	import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

	import javax.swing.JOptionPane;

	import org.harca.seg.achados.control.Control;
import org.harca.seg.achados.model.*;
import org.harca.seg.chaves.control.Key;
import org.harca.seg.util.*;
	public class Sql {

		Connection c = null;
	    PreparedStatement stmt = null;
	    String query;
	    
	    
	    public Sql(){
	    	conectar();
	    }
		public void conectar(){
			 
			    try {
			      Class.forName("org.sqlite.JDBC");
			      c = DriverManager.getConnection("jdbc:sqlite:bancodedados/chaves.db");
			      c.setAutoCommit(false);
			    }catch(Exception e){
			    	e.printStackTrace();
			    }
			    
		}
		
		public void inserir(Key key){
			query = "INSERT INTO chave (numero,setor,localizacao,cor,torre,andar) VALUES(?,?,?,?,?,?)";
			System.out.println("----------------"+key.getNumero()+" - "+key.getLocalizacao());
			try{
				
				stmt = c.prepareStatement("INSERT INTO chave (numero,setor,localizacao,cor,torre,andar) VALUES(?,?,?,?,?,?);");
				//stmt = c.prepareStatement(query);
				stmt.setInt(1, key.getNumero());
				stmt.setString(2,key.getSetor());
				stmt.setString(3,key.getLocalizacao());
				stmt.setString(4, key.getCor());
				stmt.setString(5, key.getTorre());
				stmt.setString(6, key.getAndar());
				
				stmt.execute();
				

				c.commit();
				//stmt.close();
				//c.close();
			}catch(SQLException e){ 
				e.printStackTrace();
				
			}
		}
		
		public List<Key> select(String query){
			List<Key> listaChaves = new ArrayList<Key>();
			Key key = new Key();
			
		//	query = "SELECT * FROM chave;";
			
			try{
				stmt = c.prepareStatement(query);
				ResultSet rs = stmt.executeQuery();
				//listaChaves.add(arg0)
				while(rs.next()){
					key = new Key();
					//key.setAndar(Integer.toString(rs.getInt("andar")));
					key.setAndar(rs.getString("andar"));
					key.setCor(rs.getString("cor"));
					key.setLocalizacao(rs.getString("localizacao"));
					key.setNumero(rs.getInt("numero"));
					key.setSetor(rs.getString("setor"));
					key.setTorre(rs.getString("torre"));
					listaChaves.add(key);
				}
				stmt.close();
				//c.close();
			}catch(Exception e){
				e.printStackTrace();
			}
			
			return listaChaves;
			
		}
		public List<Key> selectByAndarEtorre(int andar, int torre){
			query = "SELECT * from chaves WHERE andar="+andar+" AND torre="+torre;
			
			return select(query);
		}
		public List<Key> selectAll(){
			query = "SELECT * FROM chave;";
			return select(query);
			
		}
		
		
}
