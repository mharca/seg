package org.harca.seg.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.harca.seg.chaves.model.LerPlanilha;

public class JanLerPlanilha extends JFrame {
	JTextField tplanilhaPath;
	JButton btOK;
	JPanel jp;
	JLabel label;
	public JanLerPlanilha() {
		setSize(450, 80);
		 setLocationRelativeTo(null);

		setLayout(new BorderLayout());
		tplanilhaPath = new JTextField();
		//tplanilhaPath.setBounds(5, 20, 5, 100);
		btOK = new JButton("OK");
			btOK.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					 lerPlanilha();
					
				}
			});
		jp = new JPanel();
			jp.setLayout(new GridLayout());
		
		label = new JLabel("Localizacao da planilha: ");
		
		jp.add(label);
		jp.add(tplanilhaPath);
		jp.add(btOK);
		
		add(jp,BorderLayout.CENTER);
		setVisible(true);
		
	}
	private void lerPlanilha(){
		new LerPlanilha("Arquivos/a.xlsx");
	}
}
