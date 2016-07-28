package org.harca.seg.achados.ui;
import org.harca.seg.achados.control.*;

import javax.imageio.ImageIO;
import javax.swing.*;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;

import java.awt.GridLayout;

import javax.swing.border.TitledBorder;
import javax.swing.text.MaskFormatter;
import javax.swing.border.LineBorder;

import java.awt.Color;
import java.awt.Desktop;

import javax.swing.border.EtchedBorder;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.image.BufferedImage;

public class JanCadastro extends JPanel{
	private JTextField tipoObjeto;
	private JTextField local;
	private JTextField matriculaTexto;
	private JTextField nomeLocalizou;
	private JTextField isiChave;
	private JTextField escaninho;
	final JTextArea descricaoObjeto;
	final JFormattedTextField horaEncontrado;

	private void limpar(){
		tipoObjeto.setText("");
		local.setText("");
		matriculaTexto.setText("");
		nomeLocalizou.setText("");
		escaninho.setText("");
		descricaoObjeto.setText("");
		horaEncontrado.setText("");
	}
	
	public JanCadastro() {
		this.setSize(618, 532);
		
		
		//setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		//setTitle("Cadastro");
		setLayout(null);
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Objeto", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 105, 314, 217);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblTipoDeObjeto = new JLabel("Tipo de objeto:");
		lblTipoDeObjeto.setBounds(11, 27, 102, 14);
		panel.add(lblTipoDeObjeto);
		
		tipoObjeto = new JTextField();
		tipoObjeto.setBounds(131, 25, 171, 20);
		panel.add(tipoObjeto);
		tipoObjeto.setColumns(10);
		
		JLabel lblDescrio = new JLabel("Descri\u00E7\u00E3o:");
		lblDescrio.setBounds(10, 53, 86, 14);
		panel.add(lblDescrio);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(131, 52, 171, 61);
		panel.add(scrollPane);
		
		descricaoObjeto = new JTextArea();
		scrollPane.setViewportView(descricaoObjeto);
		
		JLabel lblLocalEncontrado = new JLabel("Local:");
		lblLocalEncontrado.setBounds(10, 128, 86, 14);
		panel.add(lblLocalEncontrado);
		
		local = new JTextField();
		local.setBounds(96, 125, 144, 20);
		panel.add(local);
		local.setColumns(10);
		
		JLabel lblData = new JLabel("Data:");
		lblData.setBounds(10, 153, 46, 14);
		panel.add(lblData);
		
		final JFormattedTextField dataEncontrado = new JFormattedTextField();
		dataEncontrado.setBounds(96, 153, 86, 20);
		
		try{
			MaskFormatter mf = new MaskFormatter("##/##/####");
			mf.install(dataEncontrado);

			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			Date data = new Date();
			dataEncontrado.setText(dateFormat.format(data));
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		panel.add(dataEncontrado);
		
		JLabel lblHora = new JLabel("Hora:");
		lblHora.setBounds(10, 178, 46, 14);
		panel.add(lblHora);
		
		horaEncontrado = new JFormattedTextField();
		horaEncontrado.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				horaEncontrado.selectAll();
			}
		});
		horaEncontrado.setBounds(96, 175, 86, 17);
		try{
			MaskFormatter mf = new MaskFormatter("##:##");
			mf.install(horaEncontrado);
		}catch(Exception e){
			e.printStackTrace();
		}
		panel.add(horaEncontrado);
		
		
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "Recebido pelo inspetor", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(10, 322, 314, 89);
		add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblChave = new JLabel("Chave:");
		lblChave.setBounds(10, 25, 62, 14);
		panel_2.add(lblChave);
		
		isiChave = new JTextField();
		isiChave.setBounds(66, 23, 68, 20);
		panel_2.add(isiChave);
		isiChave.setColumns(10);
		isiChave.setText(System.getProperty("user.name"));
		
		JLabel lblData_1 = new JLabel("Data:");
		lblData_1.setBounds(10, 50, 62, 14);
		panel_2.add(lblData_1);
		
		final JFormattedTextField dataRecebido = new JFormattedTextField();
		dataRecebido.setBounds(66, 51, 68, 20);
		try{
			MaskFormatter mf = new MaskFormatter("##/##/####");
			mf.install(dataRecebido);
			
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			Date data = new Date();
			
			dataRecebido.setText(dateFormat.format(data));
			
		}catch(Exception e){
			e.printStackTrace();
		}
		panel_2.add(dataRecebido);
		
		JLabel lblHora_1 = new JLabel("Hora:");
		lblHora_1.setBounds(165, 50, 50, 14);
		panel_2.add(lblHora_1);
		
		final JFormattedTextField horaRecebido = new JFormattedTextField();
		horaRecebido.setBounds(219, 48, 62, 20);
		try{
			MaskFormatter mf = new MaskFormatter("##:##");
			mf.install(horaRecebido);
			
			DateFormat dateFormat = new SimpleDateFormat("HH:mm");
			Date data = new Date();
			horaRecebido.setText(dateFormat.format(data).toString());
		}catch(Exception e){
			e.printStackTrace();
		}
		panel_2.add(horaRecebido);
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				List<String> lista = new ArrayList<>();
				
				lista.add(tipoObjeto.getText());
				lista.add(descricaoObjeto.getText());
				lista.add(local.getText());
				
				lista.add(dataEncontrado.getText());
				lista.add(horaEncontrado.getText());
			
				lista.add(matriculaTexto.getText());
				lista.add(nomeLocalizou.getText());
				
				lista.add(isiChave.getText());
				lista.add(dataRecebido.getText());
				lista.add(horaRecebido.getText());
				
				lista.add(escaninho.getText());
				
				int aux = 0;
				for (String s : lista){
					if (s.length() == 0){
						aux = 1;
						break;
					}else{
						limpar();
					}
				}
					if(aux == 1){ // campo vazio ok
						JOptionPane.showMessageDialog(null, "Erro. Campo(s) vazios");
					}else{							
							Control control = new Control();
							control.cadastrar(lista);
					}
				
			}
		});
		btnCadastrar.setBounds(117, 471, 145, 23);
		add(btnCadastrar);
		
		JLabel lblEswcaninho = new JLabel("Escaninho:");
		lblEswcaninho.setBounds(10, 424, 86, 14);
		add(lblEswcaninho);
		
		escaninho = new JTextField();
		escaninho.setBounds(93, 422, 65, 20);
		add(escaninho);
		escaninho.setColumns(10);
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 12, 314, 89);
		add(panel_1);
		panel_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Respons\u00E1vel pela localiza\u00E7\u00E3o do objeto.", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setLayout(null);
		
		JLabel lblMatricula = new JLabel("Matricula / chave:");
		lblMatricula.setBounds(10, 25, 101, 14);
		panel_1.add(lblMatricula);
		
		matriculaTexto = new JTextField();
		matriculaTexto.setBounds(121, 22, 89, 20);
		panel_1.add(matriculaTexto);
		matriculaTexto.setColumns(10);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(10, 50, 46, 14);
		panel_1.add(lblNome);
		
		nomeLocalizou = new JTextField();
		nomeLocalizou.setBounds(55, 50, 249, 20);
		panel_1.add(nomeLocalizou);
		nomeLocalizou.setColumns(10);
		nomeLocalizou.setEditable(false);
		
		final JPanel panel_4 = new JPanel();
		panel_4.setBorder(new TitledBorder(null, "Foto", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_4.setBounds(337, 17, 274, 426);
		add(panel_4);
		panel_4.setLayout(null);
		
		final JPanel panel_3 = new JPanel(new GridLayout());
		panel_3.setBounds(5, 16, 264, 405);
		panel_4.add(panel_3);
		
		JButton btnVerificar = new JButton("Verificar");
		btnVerificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
						Thread t = new Thread(new Runnable() {
							
							@Override
							public void run() {
								// TODO Auto-generated method stub
								try{
									nomeLocalizou.setBackground(Color.RED);
									nomeLocalizou.setText("Procurando");
									String nome = new Control().getNomeByChave(matriculaTexto.getText());
									
									nomeLocalizou.setText(nome);
									nomeLocalizou.setBackground(Color.WHITE);
									
									
									JLabel lblNewLabel;
									lblNewLabel	 = new JLabel(new ImageIcon("semfoto.jpg"));
									
									try{
										
										Thread t = new Thread();
										//URL fotoUrl = new URL("foto.jpg");
										String fotoUrl = "foto.jpg";
										String fotoErro = "semfoto.jpg";
										//BufferedImage image = ImageIO.read(fotoUrl);
										
							
										ImageIcon imageIcon = new ImageIcon("semfoto.jpg");
											try{
												String aux = new String(matriculaTexto.getText());
												URL url = new URL(new Control().getFotoByMatriculaChave(aux));
																			
												imageIcon = new ImageIcon(ImageIO.read(url));
											
											}catch(Exception e){
												imageIcon = new ImageIcon("semfoto.jpg");
											}
										
										lblNewLabel	 = new JLabel(imageIcon);
													
										
											panel_3.add(lblNewLabel);
											System.out.println("foto resize");
										//	panel_3.setBounds(5, 16, 264, 406);
											

											

											
									}catch(Exception e){
										e.printStackTrace();
									}
									panel_4.add(panel_3);
								//	panel_4.repaint();
									
								}catch(Exception e){
									nomeLocalizou.setText("");
									JOptionPane.showMessageDialog(null, "N�o foi poss�vel buscar o nome\nEdite o nome manualmente.");
									nomeLocalizou.setEditable(true);
									nomeLocalizou.setBackground(Color.WHITE);
									nomeLocalizou.selectAll();
									
								}
								
							}
						});
						t.start();
						
			repaint();
					
				}
			
		});
		btnVerificar.setBounds(213, 21, 91, 23);
		panel_1.add(btnVerificar);
		
		
		
			
	}
}
