package org.harca.seg.garagem.ui;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;

import javax.swing.JTextField;
import javax.swing.border.Border;

import org.apache.bcel.classfile.PMGClass;
import org.harca.seg.garagem.ui.panels.Pcarro;
import org.harca.seg.garagem.ui.panels.Pperiodo;
import org.harca.seg.garagem.ui.panels.Pvisitado;
import org.harca.seg.garagem.ui.panels.Pvisitante;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

public class JanGaragemCadastro extends JPanel{
	//JPanels
	Pvisitante pv;
	Pvisitado pvtado;
	Pperiodo pp;
	Pcarro pcarro;
	public JanGaragemCadastro(){
		setLayout(new GridLayout(5,2));
		
		pv = new Pvisitante();
		pcarro = new Pcarro();
		pp = new Pperiodo();
		pvtado = new Pvisitado();
		
		JPanel p1 = new JPanel(new GridLayout());
		JPanel p2 = new JPanel(new GridLayout());
		JPanel p3 = new JPanel(new GridLayout());

		p1.add(pv,BorderLayout.WEST);
		p1.add(pvtado, BorderLayout.EAST);
		
		p2.add(pcarro,BorderLayout.WEST );
		p2.add(pp,BorderLayout.EAST);
		
		add(p1,BorderLayout.NORTH);
		add(p2, BorderLayout.CENTER);
		
		JButton btnCadastrar = new JButton("cadastrar");
		JButton btnCadastrarEcarro = new JButton("cadastrar e manter carro");
		
		btnCadastrarEcarro.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				cadastrarEmanter();
				
			}
		});
		p3.setLayout(new GridLayout());
		p3.add(btnCadastrar);
		p3.add(btnCadastrarEcarro);
		p3.setBorder(BorderFactory.createEtchedBorder());
		
		add(p3,BorderLayout.SOUTH);
		
	}
	public void cadastrarEmanter(){
		String placa = pcarro.getPlaca();
		String modelo = pcarro.getModelo();
		String cor = pcarro.getCor();
		// Chama cadastrar
		cadastrar();
		System.out.println("Cadastrar e manter");
		
		pcarro.setPlaca(placa);
		pcarro.setModelo(modelo);
		pcarro.setCor(cor);
	}
	public void cadastrar(){
		System.out.println("Cadastrar");
	}
}
