package org.harca.seg.chaves.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.table.TableModel;

import org.harca.seg.chaves.control.Controle;

public class JanHistoricoChave extends JFrame{
	public JanHistoricoChave(int id, String local, String numero){
		
		setLayout(new BorderLayout());
		setSize(800,400);
		setTitle("Historico de chave");
	//	add(new JLabel("ID "+id));
		setLocationRelativeTo(null);
		JPanel pLabels = new JPanel(new GridLayout(2,2));
		JLabel lNumero = new JLabel("Numero: "); 
		JTextField tNumero = new JTextField(numero); 
		tNumero.setEditable(false);
		JLabel lLocal = new JLabel("Localizacao: ");
		JTextField tLocal= new JTextField(local);
		tLocal.setEditable(false);
		
		pLabels.add(lNumero);
		pLabels.add(tNumero);
		pLabels.add(lLocal);
		pLabels.add(tLocal);
		pLabels.setBorder(BorderFactory.createTitledBorder("Chave"));
		
		JPanel pTabela = new JPanel();
		
		String []colunas = {"Quem pegou", "Matricula", "Data pegou", "Hora pegou", "Data devolveu", "Hora devolveu"};
		
		List<List<String>> lista= new ArrayList<>();
		Controle c = new Controle();
		lista = c.pegaHistoricoChaves(id);
		
		ModeloDynDevolver modelo = new ModeloDynDevolver(colunas,lista);
		
		JTable jtable = new JTable(modelo);
		JScrollPane jsp = new JScrollPane(jtable);
		pTabela.setLayout(new BorderLayout());
		pTabela.add(jsp);
		add(pLabels, BorderLayout.NORTH);
		add(pTabela,BorderLayout.CENTER);
		setVisible(true);
		
	}

}
