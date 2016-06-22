package org.harca.seg.ui;
import javax.swing.*;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.DefaultMutableTreeNode;

public class JanPrincipal extends JFrame{
	JToolBar jtoolbar;
	JTextField tbusca;
	JButton bbuscar,btEscala,btTelUteis;
	
	final JSplitPane splitPane;
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
		
		JMenu mnTemas = new JMenu("Temas");
		menuBar.add(mnTemas);
		
		JMenuItem mntmMotif = new JMenuItem("Motif");
		mntmMotif.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
			     
				try {
					for (LookAndFeelInfo manager : UIManager.getInstalledLookAndFeels()){
						System.out.println(manager.getClassName());
					}
				//	UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
					UIManager.setLookAndFeel("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel");
					//UIManager.setLookAndFeel(“com.jtattoo.plaf.aluminium.AluminiumLookAndFeel”);

				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (InstantiationException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IllegalAccessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (UnsupportedLookAndFeelException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
			
		});
		
		mnTemas.add(mntmMotif);
		
		 jtoolbar = new JToolBar();
		 jtoolbar.add(new JLabel("Buscar chave/matricula: "));

		 	tbusca = new JTextField();
		 	jtoolbar.add(tbusca);
		 bbuscar = new JButton("Buscar");
		 jtoolbar.add(bbuscar);
		 jtoolbar.addSeparator();
		 btEscala = new JButton("Escala");
		 btEscala.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				splitPane.setRightComponent(new EscalaPanel());
}
		});
		 jtoolbar.add(btEscala);
		 btTelUteis = new JButton("Tel. Uteis");
		 jtoolbar.add(btTelUteis);
		 splitPane = new JSplitPane();
		getContentPane().add(jtoolbar, BorderLayout.NORTH);

		getContentPane().add(splitPane, BorderLayout.CENTER);
		
		final JTree tree = new JTree();
		tree.setModel(new DefaultTreeModel(
			new DefaultMutableTreeNode("Seg") {
				{
					DefaultMutableTreeNode node_1;
					DefaultMutableTreeNode node_2;
					//DefaultMutableTreeNode node_3;
					node_1 = new DefaultMutableTreeNode("Chaves");
						node_1.add(new DefaultMutableTreeNode("Emprestar"));
						node_1.add(new DefaultMutableTreeNode("Devolver"));
						node_1.add(new DefaultMutableTreeNode("Listar"));
					add(node_1);
					node_1 = new DefaultMutableTreeNode("Garagem");
						node_1.add(new DefaultMutableTreeNode("Cadastrar"));
						node_1.add(new DefaultMutableTreeNode("Tag ruim"));
							node_2 = new DefaultMutableTreeNode("Procurar");
								node_2.add(new DefaultMutableTreeNode("Placa"));
								node_2.add(new DefaultMutableTreeNode("Visitante"));
								node_2.add(new DefaultMutableTreeNode("Visitado"));
								node_2.add(new DefaultMutableTreeNode("Tag"));
								node_2.add(new DefaultMutableTreeNode("Detran"));
							node_1.add(node_2);
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
	//	JScrollPane jsp = new JScrollPane();
		//jsp.add(tree);
		splitPane.setLeftComponent(tree);
		//splitPane.getLeftComponent().setBackground(new Color(0,0,230));
		Color c = new Color(153,217,234);
		JPanel jp = new JPanel();
		//jp.setBackground(c);
		splitPane.setBackground(c);
		splitPane.setRightComponent( jp);
		setVisible(true);
	}
}
