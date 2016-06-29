package org.harca.seg.chaves.dao;
	import java.sql.*;
	import java.text.DateFormat;
	import java.text.SimpleDateFormat;
	import java.util.*;
	import java.util.Date;

	import javax.swing.JOptionPane;

	import org.harca.seg.achados.control.Control;
	import org.harca.seg.achados.model.*;
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
			      c = DriverManager.getConnection("jdbc:sqlite:chaves.db");
			      c.setAutoCommit(false);
			    }catch(Exception e){
			    	e.printStackTrace();
			    }
			    
		}
		
		public void selectByAndarEtorre(int andar, int torre){
			query = "SELECT * from chaves WHERE ";
		}
		
}
