package org.harca.seg.achados.ui;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JanPrincipal extends JFrame{
	public JanPrincipal() {
		setSize(391,235);
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		JMenu mnAchadosEPerdidos = new JMenu("Achados/Perdidos");
		menuBar.add(mnAchadosEPerdidos);
		
		JMenuItem mntmCadastrar = new JMenuItem("Cadastrar");
		mntmCadastrar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
						new JanCadastro().setVisible(true);
					}
					
			}
		);
		mnAchadosEPerdidos.add(mntmCadastrar);
		
		JMenuItem mntmBuscar = new JMenuItem("Buscar/devolver");
		mntmBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new JanBuscar().setVisible(true);
			}
		});
		mnAchadosEPerdidos.add(mntmBuscar);
		
		JMenuItem mntmSair = new JMenuItem("Sair");
		mntmSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		
		
		JMenuItem mntmListarDevolvidos = new JMenuItem("Listar devolvidos");
		mntmListarDevolvidos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new JanDevolvidos().setVisible(true);
			}
		});
		
		//mnAchadosEPerdidos.add(mntmListarDevolvidos);
		mnAchadosEPerdidos.add(mntmSair);
	}

}
