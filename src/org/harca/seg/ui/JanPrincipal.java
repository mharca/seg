package org.harca.seg.ui;
import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.DefaultMutableTreeNode;

public class JanPrincipal extends JFrame{
	
	public JanPrincipal(){
		setSize(800, 600);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnArquivo = new JMenu("Arquivo");
		menuBar.add(mnArquivo);
		
		JMenuItem mntmSair = new JMenuItem("Sair");
		mntmSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		mnArquivo.add(mntmSair);
		
		final JSplitPane splitPane = new JSplitPane();
		getContentPane().add(splitPane, BorderLayout.CENTER);
		
		final JTree tree = new JTree();
		tree.setModel(new DefaultTreeModel(
			new DefaultMutableTreeNode("Seg") {
				{
					DefaultMutableTreeNode node_1;
					DefaultMutableTreeNode node_2;
					DefaultMutableTreeNode node_3;
					node_1 = new DefaultMutableTreeNode("Chaves");
						node_1.add(new DefaultMutableTreeNode("Emprestar"));
						node_1.add(new DefaultMutableTreeNode("Devolver"));
						node_1.add(new DefaultMutableTreeNode("Listar"));
					add(node_1);
					node_1 = new DefaultMutableTreeNode("Garagem");
						node_1.add(new DefaultMutableTreeNode("Cadastrar"));
						node_2 = new DefaultMutableTreeNode("Gerar planilha");
							node_3 = new DefaultMutableTreeNode("Procurar");
								node_3.add(new DefaultMutableTreeNode("Placa"));
								node_3.add(new DefaultMutableTreeNode("Visitante"));
								node_3.add(new DefaultMutableTreeNode("Visitado"));
							node_2.add(node_3);
						node_1.add(node_2);
					add(node_1);
					node_1 = new DefaultMutableTreeNode("Achados e perdidos");
						node_1.add(new DefaultMutableTreeNode("Cadastrar"));
						node_2 = new DefaultMutableTreeNode("Procurar");
							node_2.add(new DefaultMutableTreeNode("Data"));
							node_2.add(new DefaultMutableTreeNode("Objeto"));
						node_1.add(node_2);
						node_1.add(new DefaultMutableTreeNode("Listar"));
					add(node_1);
				}
			}
		));
		tree.addTreeSelectionListener(new TreeSelectionListener() {
			
			@Override
			public void valueChanged(TreeSelectionEvent e) {
				DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
				if( node.getParent().toString() == "Chaves"){ 
						if (node.getUserObject() == "Emprestar")
							splitPane.setRightComponent(new org.harca.seg.chaves.ui.JanEmprestarChave());
						else if (node.getUserObject() == "Devolver")
							splitPane.setRightComponent(new org.harca.seg.chaves.ui.JanDevolverChave());
						else if (node.getUserObject() == "Listar")
							splitPane.setRightComponent(new org.harca.seg.chaves.ui.JanListarChave());
							
					
				}
					
				
				
			}
		});
		
		splitPane.setLeftComponent(tree);
		setVisible(true);
	}
}
