package org.harca.seg.chaves.model;

import java.io.File;
import java.io.FileInputStream;
import java.util.Vector;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.harca.seg.chaves.dao.Sql;
//import org.chave.dao.Sql;
//import org.chave.excel.Key;

public class LerPlanilha {
	XSSFSheet folha;
	XSSFCellStyle style;
	XSSFFont font;
	XSSFColor corFonte;
	private static final int VERDE = 0;
	private static final int AMARELO = 8; 
	
	Sql sql;
	public LerPlanilha(String path){
	//	Sql sql = new Sql();
		corFonte = new XSSFColor();
		font = new XSSFFont(null);
		style = new XSSFCellStyle(null);
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
								key.setNumero((int)cell.getNumericCellValue());
								System.out.print(" Numero: "+cell.getNumericCellValue()+" - ");
								
								break;
								
							case Cell.CELL_TYPE_STRING:
								
								if(cell.getColumnIndex() == 2){
										key.setLocalizacao(cell.getStringCellValue().toString());
										style = (XSSFCellStyle) cell.getCellStyle();
																				
										System.out.print(" Cor: "+ style.getFont().getColor()); 
										String aux = null;
										if(style.getFont().getColor() ==VERDE )
											aux="verde";
										else if (style.getFont().getColor() == AMARELO)
											aux="amarelo";
										
										key.setCor(aux);
										
										System.out.print("Cor:"+aux+" Local: --> "+ key.getLocalizacao()+"\n");
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
					
					//	sql.inserir(key.getNumero(), key.getSetor(), key.getLocalizacao(),folha);
					}	
					
					
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


