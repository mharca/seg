package org.harca.seg.chaves.ui;
import java.util.*;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

import org.apache.poi.ss.formula.functions.Code;
import org.apache.xmlbeans.impl.jam.annotation.DefaultAnnotationProxy;
import org.harca.seg.chaves.control.Controle;
import org.harca.seg.util.HtmlParser;

public class JanHistoricoPessoa extends JFrame{
	JTextField tNome, tMat, tEmpr;
	JLabel lFoto, lNome, lMat, lEmpr;
	JTable tabela;
	HtmlParser parser;
	
	public JanHistoricoPessoa(String nome, String mat){
		setLocationRelativeTo(null); // Centro da tela
		JPanel pdados = new JPanel(new GridLayout(10, 2));
			lNome = new JLabel("Nome: ");
			lMat = new JLabel("Matricula: ");
			lEmpr = new JLabel("Empresa: ");
			
			tNome = new JTextField();	 tNome.setEditable(false); tNome.setText(nome);
			tMat = new JTextField();	 tMat.setEditable(false); tMat.setText(mat);
				tEmpr = new JTextField(); tEmpr.setEditable(false); tEmpr.setText("Buscando empresa..."); 
				
				//tEmpr.setText(buscando);
				final String matAux = mat;
				Thread t = new Thread(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						//HtmlParser parser;
					//	parser = new HtmlParser(matAux);
						
						//buscando = parser.getEmpresa();
					//	tEmpr.setText(parser.getEmpresa());
						tEmpr.setText(getEmpresa(matAux));
					}
				});
				t.run();
				
			
		pdados.add(lNome); pdados.add(tNome);
		pdados.add(lMat); pdados.add(tMat);
		pdados.add(lEmpr); pdados.add(tEmpr);
		
		JPanel pFoto = new JPanel();
			lFoto = new JLabel();
			//String matricula="9764342";
			String fotoUrl = "http://apl.ti.petrobras.com.br/fotos/0"+mat+".jpg";
			try{
				URL url = new URL(fotoUrl);
				BufferedImage image = ImageIO.read(url);
				ImageIcon iicon = new ImageIcon(image);
				Image bimage = iicon.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT);
				
				lFoto.setIcon(new ImageIcon(bimage));
			}catch(Exception e){
				try{
					String fotoUrl2 = "http://spme.petrobras.com.br/fotos/"+mat+".jpg";
					URL url2 = new URL(fotoUrl2);
					BufferedImage image2 = ImageIO.read(url2);
					ImageIcon iicon2 = new ImageIcon(image2);
					Image bimage2 = iicon2.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT);
					lFoto.setIcon(new ImageIcon(bimage2));
				}catch(Exception e2){
					System.out.println("Sem foto");
					try{
						ImageIcon iicon3 = new ImageIcon("sem-foto.jpg");
						Image bimage3 = iicon3.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT);
						lFoto.setIcon(new ImageIcon(bimage3));
					}catch(Exception e3){
						System.out.println("Erro bizarro");
					}
				}
			
			}
			pFoto.add(lFoto);
			pFoto.setBorder(BorderFactory.createTitledBorder("Foto"));
		
		JPanel pcima = new JPanel(new GridLayout(1,2));
		pcima.add(pFoto);
		pcima.add(pdados);
		
		JPanel pTabela = new JPanel();
			final String[] colunas = {"Numero","Localizacao","Retirou/Dia", "Retirou/Hora", "Devolveu/Dia", "Devolveu/Hora"};
			List<List<String>> lista= new ArrayList<>();
			Controle c = new Controle();
			lista = c.pegaHistoricoChaves(mat);
			ModeloDynDevolver modelo = new ModeloDynDevolver(colunas, lista);
			tabela = new JTable(modelo);
		
			JScrollPane jsp = new JScrollPane(tabela);
			pTabela.add(jsp);
		
		add(pcima, BorderLayout.NORTH);
		add(pTabela, BorderLayout.CENTER);
		
		setVisible(true);
		//setLayout(new BorderLayout());
		pack();
	}
	private String getEmpresa(String mat){
		final String matAux = mat;
		parser = new HtmlParser(matAux);
		return parser.getEmpresa();
	}
}
