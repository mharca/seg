package org.harca.seg.chaves.ui;

import javax.swing.JPanel;

import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Component;
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
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Vector;






/*
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
*/
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import org.harca.seg.Main;
import org.harca.seg.chaves.control.Controle;
import org.harca.seg.chaves.dao.Sql;
import org.harca.seg.util.HtmlParser;

//import sun.awt.image.URLImageSource;

public class JanEmprestarChave extends JPanel{
	JLabel lmat, lnome,lempresa,llocal, ltorre, landar, lNumero;
	JComboBox ctorre,candar;
	JTextField tlocal,tnome,tNumero, tEmpresa;
	JTextField tmat;
	List andarA,AndarB;
	JPanel jp,jpessoa,jpchave,jpNumero;
	Vector<Integer> andaresa,andaresb;
	JTable jtable;
	HtmlParser parser;
	ModeloTabela modeloTabela;
	JButton btnEmprestar, btnNovoEmprestimo;
	private static int ID = 6; // Magic number do id
	public JanEmprestarChave(){
		setLayout(new BorderLayout());
		lmat = new JLabel("Matricula:");
		lnome = new JLabel("Nome:");
		lempresa = new JLabel("Empresa:");
		llocal = new JLabel("Localizacao(busca):");
		ltorre = new JLabel("Torre:");
		landar = new JLabel("Andar");
		lNumero = new JLabel("Chaves:");
		
		tNumero = new JTextField(30);
		tNumero.setEditable(false);
		
		tEmpresa = new JTextField();
		
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
		jtable.setAutoCreateRowSorter(true);
		jtable = new JTable(modeloTabela){
			public Component prepareRenderer(TableCellRenderer renderer, int row,int col){
				Component c = super.prepareRenderer(renderer, 2, 2);
				c.setForeground(Color.green);
				
				return c;
			}
		};
		
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
						//jtable.setFont(new Font());
					}
				});
		
		jp = new JPanel(new GridLayout(2,2));
		jpessoa = new JPanel(new GridLayout(3,2));
		jpessoa.setBorder(BorderFactory.createTitledBorder("Pessoa"));
		jpessoa.add(lmat);
		tmat=new JTextField();
		/* */
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
				if(tnome.getText().equals("")){
					
					t.start();
				}
				
				
			}
			
			@Override
			public void focusGained(FocusEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		/****** */
		jpessoa.add(tmat);
		jpessoa.add(lnome);
		
		
		tnome=new JTextField();
		tnome.setEditable(false);
		jpessoa.add(tnome);
		
		tEmpresa.setEditable(false);
		jpessoa.add(lempresa);
		jpessoa.add(tEmpresa);
		
		jpchave = new JPanel(new GridLayout(3,1));
		jpchave.setBorder(BorderFactory.createTitledBorder("Chave"));
		jpchave.add(ltorre);
		jpchave.add(ctorre);
		jpchave.add(landar);
		
		jpchave.add(candar);
		jpchave.add(llocal);
		tlocal = new JTextField();
	
		/*
		 * JLabel jl = new JLabel("bob");
		 
		jl.setToolTipText("Label with image in Tooltip!");
		URL urlImage=null;
		try {
			 urlImage = new URL("http://www.lsv.ens-cachan.fr/~sankur/java/first_files/tooltip.png");
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	    jl.setToolTipText("<html><img src=\"" + new ImageIcon(urlImage)
	            + "\"> \nTooltip ");
		jpchave.add(jl);
		
		*/
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
		
		
		
		jtable = new JTable(modeloTabela){
			// PINTAR LINHAS
			@Override	
			public Component prepareRenderer(TableCellRenderer renderer, int row, int col){
					Component c = super.prepareRenderer(renderer, row, col);
				
					if(getValueAt(row, 5).toString() == "verde"){
						c.setBackground(Color.GREEN);
					}
				
					if(row % 2 == 0 && !isCellSelected(row, col)){
						c.setBackground(Color.CYAN);
					}
						return c;
				}

		};
		
		/************************************************************ */
		// PINTA A LETRA DAS CELULAS								 //
		/************************************************************ */
			
//			TableCellRenderer rend = jtable.getCellRenderer(1, 2);
//			Component c = jtable.prepareRenderer(rend, 3, 3);
//			c.setForeground(Color.RED);
			
		/******************************************************************/
		
		//jtable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		jtable.getColumnModel().getColumn(2).setWidth(500);
		
		jtable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				ArrayList<String> aux = new ArrayList<String>();
			//	aux.add(jtable.getValueAt(jtable.getSelectedRow(), 0).toString());
				int[] rows = jtable.getSelectedRows();
				
			
				for (int i = 0; i < rows.length; i++){
					aux.add(jtable.getValueAt(rows[i], 0).toString());
				/*	System.out.println(jtable.getValueAt(rows[i], 5).toString());
					if(jtable.getValueAt(rows[i], 5).toString().equals("verde")){
						System.out.println("ok");
						
					}
					*/
				}
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
				Controle c= new Controle();
			
				//c.inserirEmprestimo(Integer.parseInt(modeloTabela.getValueAt(2, 0).toString()), Integer.parseInt(tmat.getText()), tnome.getText());
			//	 numchaves = Integer.parseInt(tNumero.getText());
				
				int[] rows = jtable.getSelectedRows();

				if(tmat.getText().isEmpty())
					JOptionPane.showMessageDialog(null, "Erro, sem matricula");
				else
					for (int i:rows)
						c.inserirEmprestimo((Integer.parseInt(modeloTabela.getValueAt(i, ID).toString())), Integer.parseInt(tmat.getText()), tnome.getText());
					
			}
		});
		
		btnNovoEmprestimo = new JButton("Novo emprestimo");
		btnNovoEmprestimo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				tnome.setText("");
				tmat.setText("");
				tNumero.setText("");
				tEmpresa.setText("");
			}
		});
		
		jpNumero = new JPanel(new FlowLayout());
		
		jpNumero.add(lNumero);
		jpNumero.add(tNumero);
		jpNumero.add(btnEmprestar);
		jpNumero.add(btnNovoEmprestimo);
		
		
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
																	
								s = parser.getEmpresa();
								tEmpresa.setText(s);
							
					
					
					
					tnome.setBackground(Color.WHITE);
				}
		
		
	}
	private void procurar(){
		Controle c = new Controle();
		modeloTabela = new ModeloTabela(tlocal.getText());
		jtable.setModel(modeloTabela);
		jtable.repaint();
		
	}
	private int[] convertStringToIntArray(String strArray[]) {
	    int[] intArray = new int[strArray.length];
	    for(int i = 0; i < strArray.length; i++) {
	        intArray[i] = Integer.parseInt(strArray[i]);
	    }
	    return intArray;
	}


}
