package org.harca.seg.garagem.ui;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.harca.seg.garagem.ui.panels.Pcarro;
import org.harca.seg.garagem.ui.panels.Pperiodo;
import org.harca.seg.garagem.ui.panels.Pvisitado;
import org.harca.seg.garagem.ui.panels.Pvisitante;

public class JanGaragemCadastro extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
