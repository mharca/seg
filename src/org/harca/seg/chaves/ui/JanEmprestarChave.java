package org.harca.seg.chaves.ui;

import javax.swing.JPanel;

import java.awt.GridBagLayout;

import javax.swing.JLabel;

import java.awt.GridBagConstraints;
import java.awt.FlowLayout;
import java.awt.List;

import javax.swing.JTextField;

import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
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
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.harca.seg.garagem.model.HtmlParser;

public class JanEmprestarChave extends JPanel{
	JLabel lmat, lnome,llocal, ltorre, landar;
	JComboBox ctorre,candar;
	JTextField tlocal,tnome;
	JTextField tmat;
	List andarA,AndarB;
	JPanel jp,jpessoa,jpchave;
	Vector<Integer> andaresa,andaresb;
	JTable jtable;
	HtmlParser parser;
	public JanEmprestarChave(){
		setLayout(new BorderLayout());
		lmat = new JLabel("Matricula:");
		lnome = new JLabel("Nome:");
		llocal = new JLabel("Local:");
		ltorre = new JLabel("Torre:");
		landar = new JLabel("Andar");
		
		andaresa = new Vector<>();
		andaresb = new Vector<>();
		
		for (int i=22; i>=-6;i--)
			andaresa.add(i);
		for (int i=15; i>=-6;i--)
			andaresb.add(i);
		
		
		System.out.println(andaresa);
		String storre[] = {"A","B"};
		
		ctorre = new JComboBox<String>(storre);
		ctorre.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(ctorre.getSelectedItem().equals("A")){
					candar.setModel(new DefaultComboBoxModel(andaresa));
				}else if (ctorre.getSelectedItem().equals("B")){
					candar.setModel(new DefaultComboBoxModel(andaresb));
				}
			}
		});
		
		jp = new JPanel(new GridLayout());
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
		candar = new JComboBox(new DefaultComboBoxModel(andaresa));
		
		jpchave.add(candar);
		jpchave.add(llocal);
		tlocal = new JTextField();
		jpchave.add(tlocal);
		
		jtable = new JTable(new ModeloTabela());
		JScrollPane jsp = new JScrollPane(jtable);
		jsp.setViewportView(jtable);
		//jsp.add(jtable);
		
		jp.add(jpessoa);
		jp.add(jpchave);
		//jp.add(jsp);

		add(jp,BorderLayout.NORTH);
		add(jsp,BorderLayout.CENTER);
	}
	public void perdeFoco(){
		
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

}
