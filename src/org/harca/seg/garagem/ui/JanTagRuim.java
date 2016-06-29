package org.harca.seg.garagem.ui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.text.MaskFormatter;
import org.harca.seg.util.*;

import org.harca.seg.garagem.control.Control;
import org.harca.seg.garagem.control.TagUser;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JCheckBox;

public class JanTagRuim extends JPanel {
	private JTextField textChaveMat;
	private JFormattedTextField textPlaca;
	private JTextField textNome;
	private JTextArea textAreaObs;
	private JFormattedTextField textData;
	JCheckBox chckbxManterData;
	public JanTagRuim() {
		setLayout(null);
		//setBorder(BorderFactory.createLineBorder(getForeground()));
		
		JLabel lblChaveOuMatrcula = new JLabel("Chave ou matr\u00EDcula:");
		lblChaveOuMatrcula.setBounds(36, 48, 128, 14);
		add(lblChaveOuMatrcula);
		
		textChaveMat = new JTextField();
		textChaveMat.setBounds(174, 45, 113, 20);
		add(textChaveMat);
		textChaveMat.setColumns(10);
		
		JLabel lblPlaca = new JLabel("Placa:");
		lblPlaca.setBounds(36, 73, 46, 14);
		add(lblPlaca);
		
		textPlaca = new JFormattedTextField();
		textPlaca.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				textPlaca.selectAll();
			}
		});
		try{
			MaskFormatter mf = new MaskFormatter("UUU-####");
			mf.install(textPlaca);
		}catch(Exception e){
			e.printStackTrace();
		}
		textPlaca.setBounds(174, 70, 113, 20);
		add(textPlaca);
		textPlaca.setColumns(10);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(36, 98, 46, 14);
		add(lblNome);
		
		textNome = new JTextField();
		textNome.setBounds(174, 95, 235, 20);
		add(textNome);
		textNome.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(174, 164, 235, 153);
		add(scrollPane);
		
		textAreaObs = new JTextArea();
		scrollPane.setViewportView(textAreaObs);
		
		textData = new JFormattedTextField();
		try{
			MaskFormatter mf = new MaskFormatter("##/##/####");
			mf.install(textData);
			
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			Date data = new Date();
			
			textData.setText(df.format(data));
			
		}catch(Exception e){
			e.printStackTrace();
		}
		textData.setBounds(174, 121, 86, 20);
		add(textData);
		textData.setColumns(10);
		
		JLabel lblData = new JLabel("Data:");
		lblData.setBounds(36, 123, 46, 14);
		add(lblData);
		
		JButton btnProcurar = new JButton("Preencher");
		btnProcurar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				textNome.setText("Procurando nome, aguarde...");
				textNome.setBackground(new Color(255,0,0));
				
				Runnable r = new Runnable(){

					@Override
					public void run() {
						// TODO Auto-generated method stub
						buscar();
						
					}
				};
				Thread t = new Thread(r);
				t.start();
				textPlaca.requestFocus();
			}	
			
		});
		btnProcurar.setBounds(297, 44, 112, 23);
		add(btnProcurar);
		
		
		
		JLabel lblObservao = new JLabel("Observa\u00E7\u00E3o:");
		lblObservao.setBounds(36, 164, 72, 14);
		add(lblObservao);
		
		chckbxManterData = new JCheckBox("Manter data");
		chckbxManterData.setBounds(268, 119, 112, 24);
		add(chckbxManterData);
		
		JButton btnInserir = new JButton("Inserir");
		btnInserir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!textChaveMat.getText().isEmpty()){
					TagUser t = new TagUser(textChaveMat.getText(), textPlaca.getText(), textNome.getText(), textData.getText(), textAreaObs.getText());
					Control c = new Control();
					c.insertTag(t);
					String aux = textData.getText();
					limpar();
					if(chckbxManterData.isEnabled())
						textData.setText(aux);
					else
						textData.setText("");
					
				}else{
					JOptionPane.showMessageDialog(null, "Campos vazios");
				}
				
				textChaveMat.requestFocus();
				
			}
		});
		btnInserir.setBounds(175, 343, 91, 23);
		add(btnInserir);
		
		JButton btnTagRuim = new JButton(" Tag ruim ");
		btnTagRuim.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textAreaObs.setText("");
				textAreaObs.append("Tag com problemas");
			}
		});
		btnTagRuim.setBounds(36, 186, 113, 26);
		add(btnTagRuim);
		
		JButton btnSemTag = new JButton(" Sem tag ");
		btnSemTag.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textAreaObs.setText("");
				textAreaObs.append("Sem tag");
			}
		});
		btnSemTag.setBounds(36, 215, 113, 26);
		add(btnSemTag);
		
		JButton btnTrocaCarro = new JButton(" Troca carro ");
		btnTrocaCarro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textAreaObs.setText("");
				textAreaObs.append("Troca de carro");
			}
		});
		btnTrocaCarro.setBounds(36, 245, 113, 26);
		add(btnTrocaCarro);
		
		JButton btnNewButton = new JButton("Aguardando tag");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textAreaObs.setText("");
				textAreaObs.append("Aguardando Tag");

			}
		});
		btnNewButton.setBounds(36, 276, 113, 26);
		add(btnNewButton);
		
		
		
		
	}
	private void buscar(){
		String matricula = textChaveMat.getText();
		if(!textChaveMat.getText().isEmpty()){
			
			org.harca.seg.garagem.model.HtmlParser parser = new org.harca.seg.garagem.model.HtmlParser(matricula);
			textNome.setText(parser.getNome());
			textNome.setBackground(null);
		}else{
			JOptionPane.showMessageDialog(null, "Campos vazios");

		}

	}
	private void limpar()
	{
		textAreaObs.setText("");
		textChaveMat.setText("");
		textNome.setText("");
		textPlaca.setText("");
		textData.setText("");
	}
}
