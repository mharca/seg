package org.harca.seg.chaves.ui;
import java.util.*;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
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
//import org.apache.xmlbeans.impl.jam.annotation.DefaultAnnotationProxy;
import org.harca.seg.chaves.control.Controle;
import org.harca.seg.util.Foto;
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
					
						tEmpr.setText(getEmpresa(matAux));
						//tEmpr.setText("TESTE");
					}
				});
				t.run();
				
			
		pdados.add(lNome); pdados.add(tNome);
		pdados.add(lMat); pdados.add(tMat);
		pdados.add(lEmpr); pdados.add(tEmpr);
		
		JPanel pFoto = new JPanel();
			Foto foto = new Foto(mat);
			pFoto.add(foto);
			//pFoto.add(lFoto);
			//pFoto.setBorder(BorderFactory.createTitledBorder("Foto"));
		
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
			pTabela.setLayout(new BorderLayout());
			pTabela.add(jsp);
		
		add(pcima, BorderLayout.NORTH);
		add(pTabela, BorderLayout.CENTER);
		
		setVisible(true);
		//setLayout(new BorderLayout());
		pack();
	}
	private String getEmpresa(String mat){
		final String matAux = mat;
		/*
		parser = new HtmlParser(matAux);
		return parser.getEmpresa();
		
		*/
		Controle c = new Controle();
		return c.getEmpresa(mat);
	}
}
