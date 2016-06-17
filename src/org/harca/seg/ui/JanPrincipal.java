package org.harca.seg.ui;
import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.DefaultMutableTreeNode;

public class JanPrincipal extends JFrame{
	
	public JanPrincipal(){
		setSize(800, 600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		 setLocationRelativeTo(null);
		
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
						node_2 = new DefaultMutableTreeNode("Tag ruim");
							node_3 = new DefaultMutableTreeNode("Procurar");
								node_3.add(new DefaultMutableTreeNode("Placa"));
								node_3.add(new DefaultMutableTreeNode("Visitante"));
								node_3.add(new DefaultMutableTreeNode("Visitado"));
								node_3.add(new DefaultMutableTreeNode("Tag"));
								node_3.add(new DefaultMutableTreeNode("Detran"));
							node_1.add(node_3);
						node_1.add(node_2);
					add(node_1);
					node_1 = new DefaultMutableTreeNode("Achados e perdidos");
						node_1.add(new DefaultMutableTreeNode("Cadastrar"));
						node_2 = new DefaultMutableTreeNode("Procurar");
							node_2.add(new DefaultMutableTreeNode("Data"));
							node_2.add(new DefaultMutableTreeNode("Objeto"));
							node_2.add(new DefaultMutableTreeNode("Todos"));
							node_2.add(new DefaultMutableTreeNode("Devolvidos"));
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
						setTitle("Chaves");
						if (node.getUserObject() == "Emprestar")
							splitPane.setRightComponent(new org.harca.seg.chaves.ui.JanEmprestarChave());
						else if (node.getUserObject() == "Devolver")
							splitPane.setRightComponent(new org.harca.seg.chaves.ui.JanDevolverChave());
						else if (node.getUserObject() == "Listar")
							splitPane.setRightComponent(new org.harca.seg.chaves.ui.JanListarChave());
					}
				else if( node.getParent().toString() == "Garagem"){
					setTitle("Garagem");
					if (node.getUserObject() == "Cadastrar")
						splitPane.setRightComponent(new org.harca.seg.garagem.ui.JanGaragemCadastro());
					else if (node.getUserObject() == "Gerar planilha")
						splitPane.setRightComponent(new org.harca.seg.garagem.ui.JanGaragemPlanilha());
					else if (node.getUserObject() == "Tag ruim")
						splitPane.setRightComponent(new org.harca.seg.garagem.ui.JanTagRuim());
															
				}
				if ( (node.getUserObject() == "Placa") && (node.getParent().toString() == "Procurar")){
					splitPane.setRightComponent(new org.harca.seg.garagem.ui.JanGaragemListarPlaca());
				}
				if ( (node.getUserObject() == "Visitante") && (node.getParent().toString() == "Procurar")){
					splitPane.setRightComponent(new org.harca.seg.garagem.ui.JanGaragemListarVisitante());
				}
				else if ( (node.getUserObject() == "Visitado") && (node.getParent().toString() == "Procurar")){
					splitPane.setRightComponent(new org.harca.seg.garagem.ui.JanGaragemListarVisitado());
				}
				if ( (node.getUserObject() == "Detran") && (node.getParent().toString() == "Procurar")){
					splitPane.setRightComponent(new org.harca.seg.garagem.ui.JanGaragemListarDetran());
				}
				
				if ( (node.getUserObject() == "Tag") && (node.getParent().toString() == "Procurar")){
					splitPane.setRightComponent(new org.harca.seg.garagem.ui.JanGaragemListarTag());
				}
				
				if ( (node.getUserObject() == "Cadastrar") && (node.getParent().toString() == "Achados e perdidos")){
					//splitPane.setLayout(new BorderLayout());
					JPanel p = new JPanel(new BorderLayout());
					setTitle("Achados e perdidos");
					p.add(BorderLayout.CENTER,new org.harca.seg.achados.ui.JanCadastro());
					//splitPane.setRightComponent(new org.harca.seg.achados.ui.JanCadastro());
					splitPane.setRightComponent(p);
				}
				if ( (node.getUserObject() == "Data") && (node.getParent().toString() =="Procurar") && (node.getParent().getParent().toString() == "Achados e perdidos")){
					setTitle("Achados e perdidos");
					splitPane.setRightComponent(new org.harca.seg.achados.ui.JanBuscarData());
				}
				if ( (node.getUserObject() == "Objeto") && (node.getParent().toString() =="Procurar") && (node.getParent().getParent().toString() == "Achados e perdidos")){
					setTitle("Achados e perdidos");
					splitPane.setRightComponent(new org.harca.seg.achados.ui.JanBuscarObjeto());
				}
				if ( (node.getUserObject() == "Todos") && (node.getParent().toString() =="Procurar") && (node.getParent().getParent().toString() == "Achados e perdidos")){
					setTitle("Achados e perdidos");
					splitPane.setRightComponent(new org.harca.seg.achados.ui.JanBuscarTodos());
				}
				if ( (node.getUserObject() == "Devolvidos") && (node.getParent().toString() =="Procurar") && (node.getParent().getParent().toString() == "Achados e perdidos")){
					setTitle("Achados e perdidos");
					splitPane.setRightComponent(new org.harca.seg.achados.ui.JanBuscarDevolvidos());
				}
				
			}
		});
		
		splitPane.setLeftComponent(tree);
		Color c = new Color(153,217,234);
		JPanel jp = new JPanel();
		//jp.setBackground(c);
		splitPane.setBackground(c);
		splitPane.setRightComponent( jp);
		setVisible(true);
	}
}
