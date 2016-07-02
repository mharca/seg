package org.harca.seg.chaves.ui;

import javax.swing.JPanel;

import java.awt.GridBagLayout;

import javax.swing.JLabel;

import java.awt.GridBagConstraints;
import java.awt.FlowLayout;
import java.awt.List;

import javax.swing.JTextField;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Vector;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.harca.seg.chaves.control.Controle;
import org.harca.seg.util.HtmlParser;

public class JanEmprestarChave extends JPanel{
	JLabel lmat, lnome,llocal, ltorre, landar, lNumero;
	JComboBox ctorre,candar;
	JTextField tlocal,tnome,tNumero;
	JTextField tmat;
	List andarA,AndarB;
	JPanel jp,jpessoa,jpchave,jpNumero;
	Vector<Integer> andaresa,andaresb;
	JTable jtable;
	HtmlParser parser;
	ModeloTabela modeloTabela;
	JButton btnEmprestar;
	public JanEmprestarChave(){
		setLayout(new BorderLayout());
		lmat = new JLabel("Matricula:");
		lnome = new JLabel("Nome:");
		llocal = new JLabel("Localizacao(busca):");
		ltorre = new JLabel("Torre:");
		landar = new JLabel("Andar");
		lNumero = new JLabel("Chaves:");
		
		tNumero = new JTextField(30);
		tNumero.setEditable(false);
		
		andaresa = new Vector<>();
		andaresb = new Vector<>();
		
		for (int i=22; i>=-6;i--)
			andaresa.add(i);
		for (int i=15; i>=-6;i--)
			andaresb.add(i);
		
		
		System.out.println(andaresa);
		String storre[] = {"A","B"};
		candar = new JComboBox(new DefaultComboBoxModel(andaresa));
		
		
		modeloTabela = new ModeloTabela();
		jtable = new JTable(modeloTabela);
		ctorre = new JComboBox<String>(storre);
		ctorre.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Controle c = new Controle();
				int andar = 10;
				if(ctorre.getSelectedItem().equals("A")){
					candar.setModel(new DefaultComboBoxModel(andaresa));
					tlocal.setText("");
					//andar = candar.getSelectedIndex();
					/*andar = Integer.parseInt(candar.getSelectedItem().toString());

					modeloTabela = new ModeloTabela(c.selectByAndarEtorre(2, "A"));
					modeloTabela.limpar();
					modeloTabela.fireTableDataChanged();
					jtable.setModel(modeloTabela);
					*/
				}else if (ctorre.getSelectedItem().equals("B")){
					candar.setModel(new DefaultComboBoxModel(andaresb));
					tlocal.setText("");
					/*andar = Integer.parseInt(candar.getSelectedItem().toString());
					modeloTabela = new ModeloTabela(c.selectByAndarEtorre(6, "B"));
					modeloTabela.limpar();

					modeloTabela.fireTableDataChanged();
					jtable.setModel(modeloTabela);

*/
				}
				modeloTabela.fireTableDataChanged();
				jtable.setModel(modeloTabela);

			}
		});
		candar.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						Controle c = new Controle();
						int andar = Integer.parseInt(candar.getSelectedItem().toString());
						modeloTabela = new ModeloTabela(c.selectByAndarEtorre(andar, ctorre.getSelectedItem().toString()));
						modeloTabela.fireTableChanged(null);
						jtable.setModel(modeloTabela);
					}
				});
		
		jp = new JPanel(new GridLayout(2,2));
		jpessoa = new JPanel(new GridLayout(2,2));
		jpessoa.setBorder(BorderFactory.createTitledBorder("Pessoa"));
		jpessoa.add(lmat);
		tmat=new JTextField();
		tmat.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent arg0) {
				// TODO Auto-generated method stub
				Thread t = new Thread(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						if(tmat.getText().length() > 3)
							perdeFoco();
					}
				});
				if(tnome.getText().equals(""))
					t.start();
			
				
				
			}
			
			@Override
			public void focusGained(FocusEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		jpessoa.add(tmat);
		jpessoa.add(lnome);
		tnome=new JTextField();
		tnome.setEditable(false);
		jpessoa.add(tnome);
		
		jpchave = new JPanel(new GridLayout(3,1));
		jpchave.setBorder(BorderFactory.createTitledBorder("Chave"));
		jpchave.add(ltorre);
		jpchave.add(ctorre);
		jpchave.add(landar);
		
		jpchave.add(candar);
		jpchave.add(llocal);
		tlocal = new JTextField();
		tlocal.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				procurar();
			}
			
			@Override
			public void keyPressed(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		jpchave.add(tlocal);
		
		
		
		jtable = new JTable(modeloTabela);
		jtable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				ArrayList<String> aux = new ArrayList<String>();
			//	aux.add(jtable.getValueAt(jtable.getSelectedRow(), 0).toString());
				int[] rows = jtable.getSelectedRows();
				
			
				for (int i = 0; i < rows.length; i++)
					aux.add(jtable.getValueAt(rows[i], 0).toString());
					
				tNumero.setText(aux.toString());
				
				
			}
		});
		JScrollPane jsp = new JScrollPane(jtable);
		jsp.setViewportView(jtable);
		//jsp.add(jtable);
		
		btnEmprestar = new JButton("Emprestar chaves");
		btnEmprestar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// 
				
			}
		});
		jpNumero = new JPanel(new FlowLayout());
		
		jpNumero.add(lNumero);
		jpNumero.add(tNumero);
		jpNumero.add(btnEmprestar);
		
		
		
		
		jp.add(jpessoa);
		jp.add(jpchave);
		jp.add(jpNumero);

		add(jp,BorderLayout.NORTH);
		//add(jpNumero);
		add(jsp,BorderLayout.CENTER);
	}
	private void perdeFoco(){
		
				// TODO Auto-generated method stub
		
				String s;
				tnome.setBackground(Color.RED);
				tnome.setText("Buscando nome");
				
				
				if(tmat.getText().length()!=0){
					parser = new HtmlParser(tmat.getText());
								
					s = parser.getNome();
					tnome.setText(s);
					tnome.setBackground(Color.WHITE);
				}
		
		
	}
	private void procurar(){
		Controle c = new Controle();
		modeloTabela = new ModeloTabela(tlocal.getText());
		jtable.setModel(modeloTabela);
		jtable.repaint();
		
	}

}
