package org.harca.seg.achados.ui;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.text.MaskFormatter;

import org.harca.seg.achados.control.Control;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;
import java.awt.Font;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
public class JanBuscar extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField tipo_textField;
	private JTable table = new JTable();
	JPopupMenu popMenu = new JPopupMenu();
	
	private JFormattedTextField buscaData;
	private List<String> todosMeses = new ArrayList<String>();   
	JComboBox comboBox;
	private JTextField totalmes;
	private JTextField devolvido;
	
	public JanBuscar() {
		this.setSize(895, 397);
		getContentPane().setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 11, 471, 105);
		getContentPane().add(tabbedPane);
		
		JPanel panel_3 = new JPanel();
		
		tabbedPane.addTab("Todos", null, panel_3, null);
		panel_3.setLayout(null);
		
		JButton btnListarTodos = new JButton("Listar todos");
		btnListarTodos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ModeloTabela  tmc = new ModeloTabela();
				tmc.limpar();
				
				table.setModel(tmc);
			}
		});
		btnListarTodos.setBounds(10, 25, 125, 23);
		panel_3.add(btnListarTodos);
		if (panel_3.isEnabled())
			System.out.println("todos");
		
		
		JMenuItem menuItemDeveolver = new JMenuItem("Devolver");
		
		menuItemDeveolver.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				List<String> listaTabela = new ArrayList<>();
				
				listaTabela.add((String) table.getModel().getValueAt(table.getSelectedRow(), 0)); // id
				listaTabela.add((String) table.getModel().getValueAt(table.getSelectedRow(), 1)); // tipo
				listaTabela.add((String) table.getModel().getValueAt(table.getSelectedRow(), 2)); // descricao
				
				
				JanDevolver janDevolver = new JanDevolver(listaTabela);
				janDevolver.setVisible(true);
				
			}
		});
		
		
JMenuItem menuItemDeletar = new JMenuItem("Deletar");
		
		menuItemDeletar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				List<String> listaTabela = new ArrayList<>();
				
				 Control c = new Control();
				 String s = table.getModel().getValueAt(table.getSelectedRow(), 0).toString();
				 System.out.print(s+"%%%%%%%%%%%%%");
				 c.delete(s);
				
				
			}
		});
		
		JMenuItem menuItemImprimir = new JMenuItem("Detalhes de quem encontrou");
		menuItemImprimir.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
			
				String matricula = new Control().selectMatriculaById( table.getModel().getValueAt(table.getSelectedRow(), 0).toString());
			
				//System.out.println(matricula+"77777");
				if(Desktop.isDesktopSupported())
				{
				  try {
				
					  
					Desktop.getDesktop().browse(new URI("http://portalpetrobras.petrobras.com.br/PetrobrasPortal/appmanager/portal/desktop?_nfpb=true&_pageLabel=dctm_landing_page_localizador_de_pessoas_a_petrobras&origem=buscalope&unico="+matricula+"&locale=pt"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (URISyntaxException e) {
					// TODO Auto-generated catch block
					
					e.printStackTrace();
				}
				
			}
			}
		});
		popMenu.add(menuItemDeveolver);
		popMenu.add(menuItemImprimir);
		popMenu.add(menuItemDeletar);
		
		table.setComponentPopupMenu(popMenu);
		
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Tipo de objeto", null, panel, null);
		panel.setLayout(null);
		
		JLabel lblTipo_1 = new JLabel("Tipo:");
		lblTipo_1.setBounds(10, 22, 46, 14);
		panel.add(lblTipo_1);
		
		tipo_textField = new JTextField();
		tipo_textField.setBounds(66, 19, 233, 20);
		panel.add(tipo_textField);
		tipo_textField.setColumns(10);
		
		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Control control = new Control();
				
				try{
					List<List<String>> ls2 = control.selectByTipo(tipo_textField.getText()); 
					ModeloTabela mt = new ModeloTabela(ls2.get(0));
					
					mt.addLista(ls2);
					table.setModel(mt);
				}catch(Exception e){
					JOptionPane.showMessageDialog(null, "Erro");
				}
				//ls2.add(ls);
				//mt.addRow(ls2);
				//mt.addRow();
			//	mt.fireTableDataChanged();
				table.repaint();
				
			}
		});
		btnPesquisar.setBounds(323, 18, 105, 23);
		panel.add(btnPesquisar);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Data específica", null, panel_1, null);
		panel_1.setLayout(null);
		
		JLabel lblTipo = new JLabel("Data: ");
		lblTipo.setBounds(10, 25, 34, 14);
		panel_1.add(lblTipo);
		

		buscaData = new JFormattedTextField();
		buscaData.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				buscaData.selectAll();
			}
		});
		buscaData.setFont(new Font("Arial", Font.PLAIN, 12));
		try {
			MaskFormatter mf = new MaskFormatter("##/##/####");
			mf.install(buscaData);
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JButton btnProcurar = new JButton("Procurar");
		btnProcurar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("botao data clicado");
				Control control = new Control();
				control.selectByDate(buscaData.getText());
				
			
				
				List<List<String>> ls2 = control.selectByDate(buscaData.getText());
				
				ModeloTabela mt = new ModeloTabela(ls2.get(0));
																			
				table.setModel(mt);
							
				mt.fireTableDataChanged();
				table.repaint();
				
				
			}
		});
		btnProcurar.setBounds(160, 21, 139, 23);
		panel_1.add(btnProcurar);
		

		buscaData.setBounds(42, 22, 91, 20);
		panel_1.add(buscaData);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("M\u00EAs", null, panel_2, null);
		panel_2.setLayout(null);
		
		JLabel lblMs = new JLabel("M\u00EAs:");
		lblMs.setBounds(10, 28, 32, 14);
		panel_2.add(lblMs);
		
		JButton btnProcurar_1 = new JButton("Procurar");
		

		
		todosMeses.add("JANEIRO");   
	    todosMeses.add("FEVEREIRO");   
	    todosMeses.add("MARÇO");   
	    todosMeses.add("ABRIL");   
	    todosMeses.add("MAIO");   
	    todosMeses.add("JUNHO");   
	    todosMeses.add("JULHO");   
	    todosMeses.add("AGOSTO");   
	    todosMeses.add("SETEMBRO");   
	    todosMeses.add("OUTUBRO");   
	    todosMeses.add("NOVEMBRO");   
	    todosMeses.add("DEZEMBRO");   
	    
		
		btnProcurar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Control control = new Control();
				
				String mesNumeroString = new String();
				int mesNumero = comboBox.getSelectedIndex()+1;
				
			//	mesNumero =+1;
				
				if(mesNumero < 10)
					mesNumeroString = String.format("%02d",mesNumero);
				if(mesNumero >9)
					mesNumeroString = Integer.toString(mesNumero);
				
				System.out.println("index-->"+comboBox.getSelectedIndex());
				
				List<List<String>> ls2 = control.selectByMes(mesNumeroString);
				
				//System.out.println(("FILHO DA PUTA -->>>> "+comboBox.getSelectedIndex()));
				
				//List<List<String>> ls2 = control.selectByMes(Integer.toString(mesNumero));
				
				System.out.println("mesnumstring--> "+mesNumeroString);
				if(ls2.size()>0){
					ModeloTabela mt = new ModeloTabela(ls2.get(0));
					mt.addLista(ls2);
					table.setModel(mt);
					mt.fireTableDataChanged();
					table.repaint();
				}
			}
		});
		btnProcurar_1.setBounds(180, 24, 108, 23);
		panel_2.add(btnProcurar_1);
		
		
	    comboBox= new JComboBox(todosMeses.toArray());
		comboBox.setBounds(52, 24, 118, 22);
		
		panel_2.add(comboBox);
		
		JPanel panel_4 = new JPanel();
		tabbedPane.addTab("Devolvidos", null, panel_4, null);
		panel_4.setLayout(null);
		
		JButton btnNewButton = new JButton("Listar devolvidos");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				List<List<String>> lista = new Control().selectDevolvidos();
				ModeloTabela  mt = new ModeloTabela(lista.get(0));
				mt.addLista(lista);
				table.setModel(mt);
				mt.fireTableDataChanged();
				table.repaint();
			}
		});
		btnNewButton.setBounds(21, 26, 157, 23);
		panel_4.add(btnNewButton);
		//table.setModel(new ModeloTabela());
		
		ModeloTabela tmc = new ModeloTabela();
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 129, 867, 230);
		getContentPane().add(scrollPane);
		
		
		scrollPane.setViewportView(table);
		table.setModel(tmc);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBorder(new TitledBorder(null, "Resumo", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_6.setBounds(497, 11, 386, 111);
		getContentPane().add(panel_6);
		panel_6.setLayout(null);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBounds(6, 16, 374, 84);
		panel_6.add(panel_5);
		panel_5.setLayout(null);
		
		JLabel lblTotalDoMs = new JLabel("Total do m\u00EAs:");
		lblTotalDoMs.setBounds(10, 11, 77, 14);
		panel_5.add(lblTotalDoMs);
		
		totalmes = new JTextField();
		totalmes.setText(new Control().getCountDoMesAtual());
		totalmes.setBounds(85, 8, 86, 20);
		
		panel_5.add(totalmes);
		totalmes.setColumns(10);
		
		JLabel lblDevolvidosNesteMs = new JLabel("Devolvidos:");
		lblDevolvidosNesteMs.setBounds(10, 31, 77, 14);
		panel_5.add(lblDevolvidosNesteMs);
		
		devolvido = new JTextField();
		devolvido.setBounds(85, 28, 86, 20);
		panel_5.add(devolvido);
		devolvido.setColumns(10);
	}
}
