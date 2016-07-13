package org.harca.seg.chaves.dao;
	import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;
import java.util.concurrent.ArrayBlockingQueue;

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
			      c.setAutoCommit(true);
			    }catch(Exception e){
			    	e.printStackTrace();
			    }
			    
		}
		
		
		public void inserirEmprestimo(int key_id, int matricula, String nome){
			query = "INSERT INTO pessoa(matricula, nome) VALUES(?,?);";
			
			int id_pessoa=0;
			
			try{ // INSERE PESSOA SE NAO ESTIVER CADASTRADA
				stmt = c.prepareStatement(query);
				stmt.setInt(1, matricula);
				stmt.setString(2, nome);

				stmt.executeQuery();
		
			}catch(SQLException e){ // JA EXISTE NO CADASTRO
			
				System.out.println("Ja existe cadastro");
				try{
					String query = "SELECT ID from pessoa WHERE matricula =\""+matricula+"\";";
					stmt = c.prepareStatement(query);
					ResultSet rs = stmt.executeQuery();
					//listaChaves.add(arg0)
					
					while(rs.next()){
						id_pessoa = rs.getInt(1);
					
					}
					//stmt.close();
					//c.commit();
					
				}catch(Exception ex){
					ex.printStackTrace();
			}
		
			}
			try{
				
				stmt = c.prepareStatement("INSERT INTO"+
						" emprestimoKey (key_id, horaEmprestou, dataEmprestou,dataDevolveu,isi_matricula, pessoa_id)"+
						"VALUES(?,?,?,?,?,?);");
				//stmt = c.prepareStatement(query);
				
				DateFormat horaFormat = new SimpleDateFormat("HH:MM");
				DateFormat dataFormat = new SimpleDateFormat("dd/MM/yyyy");
				Date date = new Date();
				
				stmt.setInt(1, key_id);
				stmt.setString(2,horaFormat.format(date).toString() );
				stmt.setString(3, dataFormat.format(date).toString() );//date.toString());
				
				System.out.println(dataFormat.format(date).toString());
				
				stmt.setString(4, null);
				stmt.setString(5, System.getProperty("user.name"));
				stmt.setInt(6, id_pessoa);
				
				stmt.execute();
				
				
				
				//stmt.close();
				//c.commit();
				c.close();
			}catch(SQLException e){ 
				e.printStackTrace();
				System.out.print("fudeu");
				
			}
		}
		///////////////////////////////////////////////////////////////////////////////////////////////
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
			
			
			try{
				stmt = c.prepareStatement(query);
				ResultSet rs = stmt.executeQuery();
				while(rs.next()){
					key = new Key();
					//key.setAndar(Integer.toString(rs.getInt("andar")));
					key.setAndar(rs.getString("andar"));
					key.setCor(rs.getString("cor"));
					key.setLocalizacao(rs.getString("localizacao"));
					key.setNumero(rs.getInt("numero"));
					key.setSetor(rs.getString("setor"));
					key.setTorre(rs.getString("torre"));
					key.setId(rs.getInt("id"));
					listaChaves.add(key);
				}
				stmt.close();
				//c.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
			
			return listaChaves;
			
		}
		public List<Key> selectByAndarEtorre(int andar, String torre){
			query = ("SELECT * from chave WHERE andar='"+andar+"' AND torre='"+torre+"'");
			
			return select(query);
		}
		public List<Key> selectAll(){
			query = "SELECT * FROM chave;";
			return select(query);
			
		}
		public List<Key> selectByWord(String palavra){
			query = "SELECT * FROM chave WHERE localizacao LIKE '%"+palavra+"%'";
			return select(query);
			
		}
		
		public List<List<String>> selectGenerico(String query){
			List <List<String>> ls = new ArrayList<>();
			List <String>lista = new ArrayList<>();
			try{
				stmt = c.prepareStatement(query);
				ResultSet rs = stmt.executeQuery();
				//listaChaves.add(arg0)
				int aux;
				
				while(rs.next()){
					lista= new ArrayList<>();
					
					lista.add(rs.getString(1)); 	// nome
					aux = rs.getInt(2);  			// numero
					lista.add(Integer.toString(aux));
					lista.add(rs.getString(3)); 	//localizacao
					lista.add(rs.getString(4)); 	// andar
					lista.add(rs.getString(5));		// torre
					
					aux = rs.getInt(6);  			// matricula ( resolver o problema de nao ler chave)
					lista.add(Integer.toString(aux));
					
					lista.add(rs.getString(7)); 	// hora emp
					
					//aux = rs.getInt(8);
					
					//System.out.println("Data: "+ rs.getString(8));
					lista.add(rs.getString(8)); // data emp
					lista.add(Integer.toString(rs.getInt(9)));
					//lista.add("t");
					
					ls.add(lista);
				}
			}catch(Exception e){
				e.printStackTrace();
			}
			return ls;
		}
		
		public List<List<String>> selectEmprestados(){
			
			return selectGenerico("select pessoa.nome, chave.numero, chave.localizacao, chave.andar, chave.torre, pessoa.matricula, horaEmprestou, dataEmprestou, emprestimokey.id FROM emprestimoKey JOIN chave,pessoa ON key_id=chave.id AND pessoa_id=pessoa.id;");
		}
	/***********************************************************************************************************/
		public int getIdByNumero(String numero) {
			String query = "select id from emprestimokey join chave on numero='"+numero+"'";
			//Statement stmt = null;
			//ResultSet rs=null;
			int res=0;
			try {
				
				//stmt = c.createStatement();
				stmt = c.prepareStatement(query);
				ResultSet rs=stmt.executeQuery();
				 
				// while(rs.next())
					// System.out.println("--->"+rs.getString(0));
			//	System.out.println("id: "+rs.getInt(1));
				// res = rs.getInt(1);
				// c.close();
				// rs.close();
				int aux = rs.getInt(1);
				stmt.close();
				System.out.println("id: "+aux);
				 return aux;
				 
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return res;			
		}
public void devolver(int id){
			
			DateFormat horaFormat = new SimpleDateFormat("HH:MM");
			DateFormat dataFormat = new SimpleDateFormat("dd/MM/yyyy");
			Date date = new Date();
			
			String hora = horaFormat.format(date).toString();
			String data = dataFormat.format(date).toString();
			//query = "UPDATE emprestimokey SET horaDevolveu='"+hora+"', dataDevolveu='"+data+"' WHERE id='"+8+"';";
			query = "UPDATE emprestimokey SET horaDevolveu=?, dataDevolveu=? WHERE id=?";
			//query = "UPDATE emprestimokey SET horaDevolveu='666',dataDevolveu='9999' WHERE id="+id;
			System.out.println(query);
			//Statement st=null;
			try{
				PreparedStatement ps;
				ps = c.prepareStatement(query);
				ps.setString(1, hora);
				ps.setString(2, data);
				ps.setInt(3, id);
				ps.executeUpdate();
				ps.close();
				/*
				st = c.createStatement();
				st.executeUpdate(query);
				st.close();
				*/
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
}
