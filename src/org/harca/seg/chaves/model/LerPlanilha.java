package org.harca.seg.chaves.model;

import java.io.File;
import java.io.FileInputStream;
import java.util.Vector;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//import org.chave.dao.Sql;
//import org.chave.excel.Key;

public class LerPlanilha {
	XSSFSheet folha;
	public LerPlanilha(String path){
	//	Sql sql = new Sql();
		try{
			FileInputStream file = new FileInputStream(new File(path));
			XSSFWorkbook workbook = new XSSFWorkbook(file);
					//folha = workbook.getSheetAt(pagina);
			int qtdPaginas = workbook.getNumberOfSheets();
			System.out.println(qtdPaginas);
			
			for(int i=0; i<qtdPaginas; i++){
				folha = workbook.getSheetAt(i);
					for(Row row:folha){
						Key key = new Key();
						for(Cell cell:row){
							System.out.println(" Andar ->"+(i-2));
							switch(cell.getCellType()){
							case Cell.CELL_TYPE_NUMERIC:
								key.setNumero(cell.getNumericCellValue());
								System.out.print(" Numero: "+cell.getNumericCellValue()+" - ");
								
								break;
								
							case Cell.CELL_TYPE_STRING:
								
								if(cell.getColumnIndex() == 2){
										key.setLocalizacao(cell.getStringCellValue().toString());
										System.out.print(" Local: --> "+ key.getLocalizacao()+"\n");
										break;
										
								} if(cell.getColumnIndex()==1){
										key.setSetor(cell.getRichStringCellValue().toString());
										System.out.print(" Setor: --> "+ key.getSetor());
										break;
								}
							
							case Cell.CELL_TYPE_BLANK:
								break;
							
						}
					}
					}	//	sql.inserir(key.getNumero(), key.getSetor(), key.getLocalizacao(),pagina);
			}
		workbook.close();
		}catch(Exception e){
			
			e.printStackTrace();
		}
		
	}
	public Vector getResultado()
	{
	//	Sql sql = new Sql();
		Vector v = new Vector();
		//v = sql.getSelect("Select * from chave");
		System.out.println("*****> "+v);
		return v;
	}
}


