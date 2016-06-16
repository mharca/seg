package org.harca.seg.garagem.ui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import org.harca.seg.achados.model.HtmlParser;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JanTagRuim extends JPanel {
	private JTextField textChaveMat;
	private JFormattedTextField textPlaca;
	private JTextField textNome;
	public JanTagRuim() {
		setLayout(null);
		
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
		try{
			MaskFormatter mf = new MaskFormatter("AAA-####");
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
		
		JButton btnProcurar = new JButton("Preencher");
		btnProcurar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String matricula = textChaveMat.getText();
				org.harca.seg.garagem.model.HtmlParser parser = new org.harca.seg.garagem.model.HtmlParser(matricula);
				textNome.setText(parser.getNome());
			}
		});
		btnProcurar.setBounds(318, 44, 91, 23);
		add(btnProcurar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(152, 130, 258, 83);
		add(scrollPane);
		
		JTextArea textAreaObs = new JTextArea();
		scrollPane.setViewportView(textAreaObs);
		
		JLabel lblObservao = new JLabel("Observa\u00E7\u00E3o:");
		lblObservao.setBounds(36, 136, 72, 14);
		add(lblObservao);
		
		JButton btnInserir = new JButton("Inserir");
		btnInserir.setBounds(174, 229, 91, 23);
		add(btnInserir);
	}
}
