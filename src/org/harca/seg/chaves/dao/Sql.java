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
			      this.c = DriverManager.getConnection("jdbc:sqlite:bancodedados/chaves.db");
			      this.c.setAutoCommit(true);
			    }catch(Exception e){
			    	e.printStackTrace();
			    }
			    
		}
		
		public void inserirEmprestimo(int key_id, int matricula, String nome, String empresa){
			query = "INSERT INTO pessoa(matricula, nome, empresa) VALUES(?,?,?);";
			//query = "INSERT INTO chave (numero,setor,localizacao,cor,torre,andar) VALUES(?,?,?,?,?,?)";
			//System.out.println("----------------"+key.getNumero()+" - "+key.getLocalizacao());
			int id_pessoa=0;
			
			try{ // INSERE PESSOA SE NAO ESTIVER CADASTRADA
				stmt = c.prepareStatement(query);
				stmt.setInt(1, matricula);
				stmt.setString(2, nome);
				stmt.setString(3, empresa);
				
				stmt.execute();
				stmt.close();
			}catch(SQLException e){ // JA EXISTE NO CADASTRO
				e.printStackTrace();
			}
				System.out.println("Ja existe cadastro");
				try{
					String query = "SELECT ID from pessoa WHERE matricula =\""+matricula+"\";";
					stmt = c.prepareStatement(query);
					ResultSet rs = stmt.executeQuery();
					//listaChaves.add(arg0)
					
					while(rs.next()){
						//System.out.print(rs.getInt(0) +  rs.getString(1));
						id_pessoa = rs.getInt(1);
					
					}
					stmt.close();
					//c.commit();
					
				}catch(Exception ex){
					ex.printStackTrace();
			}
		
			
			try{
				
				stmt = c.prepareStatement("INSERT INTO"+
						" emprestimoKey (key_id, horaEmprestou, dataEmprestou,dataDevolveu,isi_matricula, pessoa_id)"+
						"VALUES(?,?,?,?,?,?);");
				//stmt = c.prepareStatement(query);
				
				DateFormat horaFormat = new SimpleDateFormat("HH:mm");
				DateFormat dataFormat = new SimpleDateFormat("dd/MM/yyyy");
				Date date = new Date();
				
				stmt.setInt(1, key_id);
				stmt.setString(2, horaFormat.format(date).toString());
				stmt.setString(3, dataFormat.format(date).toString() );//date.toString());
				stmt.setString(4, null); // data devolveu
				stmt.setString(5, System.getProperty("user.name"));
				stmt.setInt(6, id_pessoa);
				
				stmt.execute();
				
				
				
				stmt.close();
				//c.commit();
				//c.close();
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
		
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		public int getChaveIdByEmprestimoID(int id){
			int i = 0;
			try{
				stmt = c.prepareStatement("SELECT chave.id FROM emprestimoKey JOIN chave on chave.id=key_id WHERE emprestimoKey.id="+id+"");
				ResultSet rs = stmt.executeQuery();
				i = rs.getInt(1);
				stmt.close();
			}catch(Exception e){
				e.printStackTrace();
			}
			return i;
		}
		
		
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		public String getEmpresa(String mat){
			try{
				stmt = c.prepareStatement("SELECT empresa FROM pessoa WHERE matricula='"+mat+"';");
				ResultSet rs = stmt.executeQuery();
				
				//ResultSet rsAux = rs;
				//rs.close();
				//stmt.close();
				String aux = rs.getString("empresa");
				stmt.close();
				return aux;
				
			}catch(Exception e){
				e.printStackTrace();
				
			}
			
			return "Empresa nao encontrada";
		}
		
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
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
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		public List<Key> selectByAndarEtorre(int andar, String torre){
			query = ("SELECT * from chave WHERE andar='"+andar+"' AND torre='"+torre+"'");
			
			return select(query);
		}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		public List<Key> selectAll(){
			query = "SELECT * FROM chave;";
			return select(query);
			
		}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		public List<Key> selectByWord(String palavra){
			query = "SELECT * FROM chave WHERE localizacao LIKE '%"+palavra+"%' OR numero LIKE '%"+palavra+"%'";
			return select(query);
			
		}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
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
					
					lista.add(rs.getString(1)); // nome
					aux = rs.getInt(2);  // numero
					lista.add(Integer.toString(aux));
					lista.add(rs.getString(3)); //localizacao
					lista.add(rs.getString(4)); // andar
					lista.add(rs.getString(5));// torre
					
					aux = rs.getInt(6);  // matricula
					lista.add(Integer.toString(aux));
					
					//System.out.println(rs.getString(7) + "<-- filho da puta");
					lista.add(rs.getString(7)); // hora emp
					//aux = rs.getInt(8);
					//lista.add(Integer.toString(aux)); // data emp
					lista.add(rs.getString(8));
					
					ls.add(lista);
				}
				stmt.close();
			}catch(Exception e){
				e.printStackTrace();
			}
			return ls;
		}
	/*	
		public List<List<String>> selectEmprestados(){
			
			return selectGenerico("select pessoa.nome,chave.numero,chave.localizacao,chave.andar,chave.torre, pessoa.matricula, horaEmprestou,dataEmprestou"+
			" FROM emprestimoKey JOIN chave,pessoa ON key_id=chave.id AND pessoa_id=pessoa.id;");
		}
		*/
		public List<List<String>> selectEmprestados(){
			String query = "select pessoa.nome,chave.numero,chave.localizacao,chave.andar,chave.torre, pessoa.matricula, horaEmprestou,dataEmprestou,emprestimoKey.id"+
					" FROM emprestimoKey JOIN chave,pessoa ON key_id=chave.id AND pessoa_id=pessoa.id;";
			List <List<String>> ls = new ArrayList<>();
			List <String>lista = new ArrayList<>();
			try{
				stmt = c.prepareStatement(query);
				ResultSet rs = stmt.executeQuery();
				//listaChaves.add(arg0)
				int aux;
				while(rs.next()){
					lista= new ArrayList<>();
					
					lista.add(rs.getString(1)); // nome
					aux = rs.getInt(2);  // numero
					lista.add(Integer.toString(aux));
					lista.add(rs.getString(3)); //localizacao
					lista.add(rs.getString(4)); // andar
					lista.add(rs.getString(5));// torre
					
					aux = rs.getInt(6);  // matricula
					lista.add(Integer.toString(aux));
				
					lista.add(rs.getString(7)); // hora emp
					System.out.println(rs.getString(8));
					lista.add(rs.getString(8));
					lista.add(Integer.toString(rs.getInt(9)));
					//System.out.println("ID -->"+Integer.toString(rs.getInt(9)));
					
					ls.add(lista);
					//stmt.close();
				}
				stmt.close();
			}catch(Exception e){
				e.printStackTrace();
			}
			return ls;
		}
		
		///////////////////////////////////////////////////////////////////////////////////////////////////////////
		public List<List<String>> selectEmprestadosNaoDevolvidos(){
			String query = "select pessoa.nome,chave.numero,chave.localizacao,chave.andar,chave.torre, pessoa.matricula, horaEmprestou,dataEmprestou,emprestimoKey.id"+
					" FROM emprestimoKey JOIN chave,pessoa ON key_id=chave.id AND pessoa_id=pessoa.id AND dataDevolveu is NULL;";
			List <List<String>> ls = new ArrayList<>();
			List <String>lista = new ArrayList<>();
			try{
				stmt = c.prepareStatement(query);
				ResultSet rs = stmt.executeQuery();
				//listaChaves.add(arg0)
				int aux;
				while(rs.next()){
					lista= new ArrayList<>();
					
					lista.add(rs.getString(1)); // nome
					aux = rs.getInt(2);  // numero
					lista.add(Integer.toString(aux));
					lista.add(rs.getString(3)); //localizacao
					lista.add(rs.getString(4)); // andar
					lista.add(rs.getString(5));// torre
					
					aux = rs.getInt(6);  // matricula
					lista.add(Integer.toString(aux));
				
					lista.add(rs.getString(7)); // hora emp
					System.out.println(rs.getString(8));
					lista.add(rs.getString(8));
					lista.add(Integer.toString(rs.getInt(9)));
					//System.out.println("ID -->"+Integer.toString(rs.getInt(9)));
					
					ls.add(lista);
					
				}
				stmt.close();
			}catch(Exception e){
				e.printStackTrace();
			}
			return ls;
		}
		///////////////////////////////////////////////////////////////////////////////
		public void devolverChave(int id){
			
			DateFormat horaFormat = new SimpleDateFormat("HH:mm");
			DateFormat dataFormat = new SimpleDateFormat("dd/MM/yyyy");
			Date date = new Date();
						
			String horaDevolveu = horaFormat.format(date).toString();
			String dataDevolveu = dataFormat.format(date).toString();
						
			// PEGAR ID
			//String query = "UPDATE emprestimoKey SET horaDevolveu='"+horaDevolveu+"',dataDevolveu='"+dataDevolveu+"' WHERE id="+id+";";
			
			try{
					//stmt.close();
					
					//PreparedStatement stmt;
					//c.close();
					//stmt.close();
				String query = "UPDATE emprestimoKey SET horaDevolveu=?,dataDevolveu=? WHERE id=?;";
					conectar();
					stmt = c.prepareStatement(query);
					
					stmt.setString(1, horaDevolveu);
					stmt.setString(2, dataDevolveu);
					stmt.setInt(3, id);
					
					stmt.executeUpdate();
					
			
					//c.commit();
					//c.close();
						
					stmt.close();
			}catch(Exception e){
				e.printStackTrace();
				
			}finally{
				
				System.out.println("-----");
			}
			
		}
		
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		public List<List<String>> pegaHistoricoChaves(int id){
		
			
			List<List<String>> l2 = new ArrayList<>();
			
			try{
		
				String query = "select pessoa.nome, pessoa.matricula, dataEmprestou, horaEmprestou, dataDevolveu, horaDevolveu from emprestimoKey join chave,pessoa on key_id=chave.id AND pessoa_id=pessoa.id where chave.id=?";
				List<String> lista;
				conectar();
				
				stmt = c.prepareStatement(query);
				stmt.setInt(1, id);
				ResultSet rs = stmt.executeQuery();
				
				while(rs.next()){
					lista = new ArrayList<>();
					
						for(int i = 1; i <= 6; i++){
							lista.add(rs.getString(i));
						}
					
					l2.add(lista);
				}
			rs.close();
				stmt.close();
			}catch(Exception e){
				e.printStackTrace();
			}
			
			return l2;
		}
		
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
		public List<List<String>> pegaHistoricoPessoa(String matricula){
			
			List<List<String>> l2 = new ArrayList<>();
			try{
				String query = "select chave.numero ,chave.localizacao,dataEmprestou, horaEmprestou,dataDevolveu,horaDevolveu from emprestimoKey join chave,pessoa on key_id=chave.id AND pessoa_id=pessoa.id where pessoa.matricula='"+matricula+"'";
				List<String> lista; //= new ArrayList();
				conectar();
				stmt = c.prepareStatement(query);
				ResultSet rs = stmt.executeQuery();
				while(rs.next()){
					lista = new ArrayList<>();
					lista.add(Integer.toString(rs.getInt(1)));
					for(int i=2;i<=6;i++ ){
						lista.add(rs.getString(i));
					}	
					l2.add(lista);
				}
				stmt.close();				
			}catch(Exception e){
				e.printStackTrace();
			}
			
			
			
			return l2;
		}
		
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}
