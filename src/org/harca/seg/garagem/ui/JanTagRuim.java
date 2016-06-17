package org.harca.seg.garagem.ui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import org.harca.seg.achados.model.HtmlParser;
import org.harca.seg.garagem.control.Control;
import org.harca.seg.garagem.control.TagUser;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class JanTagRuim extends JPanel {
	private JTextField textChaveMat;
	private JFormattedTextField textPlaca;
	private JTextField textNome;
	private JTextArea textAreaObs;
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
		textPlaca.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				textPlaca.selectAll();
			}
		});
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
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(174, 164, 235, 153);
		add(scrollPane);
		
		textAreaObs = new JTextArea();
		scrollPane.setViewportView(textAreaObs);
		
		JButton btnProcurar = new JButton("Preencher");
		btnProcurar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String matricula = textChaveMat.getText();
			
				org.harca.seg.garagem.model.HtmlParser parser = new org.harca.seg.garagem.model.HtmlParser(matricula);
				textNome.setText(parser.getNome());
				
			}
		});
		btnProcurar.setBounds(297, 44, 112, 23);
		add(btnProcurar);
		
		
		
		JLabel lblObservao = new JLabel("Observa\u00E7\u00E3o:");
		lblObservao.setBounds(36, 164, 72, 14);
		add(lblObservao);
		
		JButton btnInserir = new JButton("Inserir");
		btnInserir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TagUser t = new TagUser(textChaveMat.getText(), textPlaca.getText(), textNome.getText(), textAreaObs.getText());
				Control c = new Control();
				c.insertTag(t);
				limpar();
				
				
			}
		});
		btnInserir.setBounds(175, 343, 91, 23);
		add(btnInserir);
		
		JButton btnTagRuim = new JButton(" Tag ruim ");
		btnTagRuim.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textAreaObs.append("Tag com problemas");
			}
		});
		btnTagRuim.setBounds(36, 186, 113, 26);
		add(btnTagRuim);
		
		JButton btnSemTag = new JButton(" Sem tag ");
		btnSemTag.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textAreaObs.append("Sem tag");
			}
		});
		btnSemTag.setBounds(36, 215, 113, 26);
		add(btnSemTag);
		
		JButton btnTrocaCarro = new JButton(" Troca carro ");
		btnTrocaCarro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textAreaObs.append("Troca de carro");
			}
		});
		btnTrocaCarro.setBounds(36, 245, 113, 26);
		add(btnTrocaCarro);
	}
	private void limpar()
	{
		textAreaObs.setText("");
		textChaveMat.setText("");
		textNome.setText("");
		textPlaca.setText("");
	}
}
