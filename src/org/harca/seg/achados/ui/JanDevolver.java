package org.harca.seg.achados.ui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

//import com.sun.corba.se.impl.protocol.giopmsgheaders.Message;

import org.harca.seg.achados.control.Control;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.awt.Image;
import java.awt.event.ActionEvent;

public class JanDevolver extends JFrame{
	private JTextField matriculaTexto;
	JLabel labelImagem;
	private JTextField textField;
	JPanel panel_2;
	String fotoUrl;
	Control c;
	
	public JanDevolver(final List<String>lista) {
		setTitle("Devolu\u00E7\u00E3o");
		this.setSize(410, 680);
		getContentPane().setLayout(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		c = new Control();
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Registrar devolução", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 11, 370, 67);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblMatrcula = new JLabel("Matr\u00EDcula ou chave:");
		lblMatrcula.setBounds(10, 30, 134, 14);
		panel_1.add(lblMatrcula);
		
		matriculaTexto = new JTextField();
		matriculaTexto.setBounds(154, 27, 108, 20);
		panel_1.add(matriculaTexto);
		matriculaTexto.setColumns(10);
		
		panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "Foto", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		
		
		
		JButton verificarBotao = new JButton("Verificar");
		verificarBotao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
						
				if(matriculaTexto.getText().length() == 0){
					JOptionPane.showMessageDialog(null, "Campo matrícula ou chave está vazio.");
				}
				else if(matriculaTexto.getText().length() > 5){ // matricula, maior que 5 chars.
					fotoUrl = "http://apl.ti.petrobras.com.br/fotos/0"+matriculaTexto.getText()+".jpg";
				}else { // chave
				
						//fotoUrl = "http://apl.ti.petrobras.com.br/fotos/0"+new Control().getMatriculaByChave(matriculaTexto.getText())+".jpg";
						fotoUrl = "http://apl.ti.petrobras.com.br/fotos/0"+c.getMatriculaByChave(matriculaTexto.getText())+".jpg";
						
						}
				
				System.out.println(fotoUrl); // debug
				try{
														
					URL url = new URL(fotoUrl);
					BufferedImage image = ImageIO.read(url);
					
					labelImagem = new JLabel(new ImageIcon(image));
					labelImagem.setBounds(10, 21, 350, 283);
					panel_2.setBounds(10, 279, 370, 315);
					panel_2.add(labelImagem);
					getContentPane().add(panel_2);
					
					System.out.println("OK");
				}catch(Exception e ){
					labelImagem = new JLabel("Sem foto");
					labelImagem.setBounds(10, 21, 350, 283);
					
					//labelImagem.setText("SEM FOTO");
					panel_2.add(labelImagem);
					panel_2.setBounds(10, 279, 370, 315);
					getContentPane().add(panel_2);
					
					e.printStackTrace();
				}
		
				//getContentPane().add(panel_2);
				
				panel_2.setBounds(10, 279, 370, 315);
				panel_2.add(labelImagem);
				
						
					
			}		
			
		});
		
		
		getContentPane().add(panel_2);
		
		panel_2.setLayout(null);
		
		
		verificarBotao.setBounds(272, 26, 88, 23);
		panel_1.add(verificarBotao);
		
		
		
		JButton confirmarDevolucaoBotao = new JButton("Confirmar devolu\u00E7\u00E3o");
		confirmarDevolucaoBotao.setBounds(129, 605, 185, 23);
		getContentPane().add(confirmarDevolucaoBotao);
		confirmarDevolucaoBotao.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				System.out.println(lista.get(0));
				
				try{
				
				
				new Thread(){
					@Override
					public void run(){
						
						List<String>idEmatricula = new ArrayList<>();
						idEmatricula.add(lista.get(0));
						idEmatricula.add(matriculaTexto.getText());
							
							c.update(idEmatricula);
						
							JOptionPane.showMessageDialog(null,"Devolvido, gerando documento Word");
							c.criaWord(lista.get(0), matriculaTexto.getText());
							
					}
				}.start();
				
				}catch(Exception e){
					
					JOptionPane.showMessageDialog(null,"Ocorreu um erro, verifique se existe outro programa usando o banco de dados.");
					e.printStackTrace();
				}
			}
			
		});
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(null, "Objeto", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_3.setBounds(15, 87, 371, 183);
		getContentPane().add(panel_3);
		panel_3.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(6, 16, 359, 161);
		panel_3.add(panel);
		panel.setLayout(null);
		
		JLabel lblTipo = new JLabel("Tipo:");
		lblTipo.setBounds(10, 11, 38, 14);
		panel.add(lblTipo);
		
		textField = new JTextField(lista.get(1));
		textField.setBounds(58, 8, 278, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new TitledBorder(null, "Descrição", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_4.setBounds(4, 34, 340, 122);
		panel.add(panel_4);
		panel_4.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 16, 328, 100);
		panel_4.add(scrollPane);
		
		JTextArea textArea = new JTextArea(lista.get(2));
		scrollPane.setViewportView(textArea);
	}
}
