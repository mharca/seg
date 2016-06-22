package org.harca.seg.achados.model;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import org.harca.seg.util.*;

import org.harca.seg.achados.control.Control;
import org.harca.seg.achados.dao.Sql;


public class CriaWord {

	public CriaWord(final String ls, String matriculaRetirou) {
			
		//System.out.println(ls);
		try{
			FileInputStream fis = new FileInputStream("achadosword97.doc");
			POIFSFileSystem fs = new POIFSFileSystem(fis);
			HWPFDocument doc = new HWPFDocument(fs);
			 Range range = doc.getRange();
			 
			 List<List<String>> lista;
			 
			 Sql sql = new Sql();
			 lista = sql.selectById(ls);
			 
			 
		        range.replaceText("%tipo%", lista.get(0).get(1));
		      //  range.insertAfter("?");
		        
		        range.replaceText("%descricao%", lista.get(0).get(2));
		        range.replaceText("%localencontrado%", lista.get(0).get(3));
		        range.replaceText("%datae%", lista.get(0).get(4));
		        range.replaceText("%he%", lista.get(0).get(5));
		        range.replaceText("%matriculaencontrou%", new Control().getMatriculaByChave(lista.get(0).get(6)));
		        range.replaceText("%matricularecebeu%", new Control().getMatriculaByChave(lista.get(0).get(7)));
		        
		        String nomeRecebeu = new Control().getNomeByChave(lista.get(0).get(7).toString());
		        range.replaceText("%nomeRecebeu%", nomeRecebeu);
		        
		        String ramalLocalizou = new Control().getRamalByChave(lista.get(0).get(7).toString());
		        range.replaceText("%ramalLocalizou%", ramalLocalizou);
		        
		        String nomeEncontrou = new HtmlParser(lista.get(0).get(6)).getNome();
		        range.replaceText("%nomeencontrou%", nomeEncontrou);
		        
		        range.replaceText("%datar%", lista.get(0).get(8));
		        //range.replaceText("%%", lista.get(0).get(9));
		        
		        
		        range.replaceText("%matRet%", new Control().getMatriculaByChave(matriculaRetirou));
		        
		      //  String nomeRetirou = new Control().getNomeByChave(matriculaRetirou);   
		      //  range.replaceText("%nomeRet%", nomeRetirou);
		        
		        range.replaceText("%e%", lista.get(0).get(10));
		        
		        
		        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				Date data = new Date();
				dateFormat.format(data);
				
				range.replaceText("%dataRet%", dateFormat.format(data));
				
				
		        FileOutputStream fos = new FileOutputStream("document2.doc");
		        doc.write(fos);

		        File arquivo = new File("document2.doc");
				Desktop.getDesktop().open(arquivo);
		        
				
		        fis.close();
		        fos.close();
	}catch(Exception e){
		e.printStackTrace();
	}
		
		}
	
}
