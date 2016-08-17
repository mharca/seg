package org.harca.seg.garagem.ui;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.JTextField;
import com.jgoodies.forms.layout.*;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class JanGaragemListarDetran extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;
	public JanGaragemListarDetran(){
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.NORTH);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblPlaca = new JLabel("Placa: ");
		panel.add(lblPlaca);
		
		textField = new JTextField();
		panel.add(textField);
		textField.setColumns(10);
		
		String fotoUrl = "http://www2.detran.rj.gov.br/portal/veiculos/captcha_image?v=1465576758420";
		try {
			URL url = new URL(fotoUrl);
			try {
				BufferedImage image = ImageIO.read(url);
				JLabel lblImagem = new JLabel(new ImageIcon(image));
				panel.add(lblImagem);
				
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "Foto não encontrada");
				e.printStackTrace();
			}
			
		} catch (MalformedURLException e) {
				
				JOptionPane.showMessageDialog(null, "Não foi possível conectar");
				System.out.println("Não foi possível conectar");
		}
		
		
		
		JLabel lblVerificao = new JLabel("Verifica\u00E7\u00E3o:");
		panel.add(lblVerificao);
		
		textField_9 = new JTextField();
		panel.add(textField_9);
		textField_9.setColumns(10);
		
		JButton btnProcurar = new JButton("Procurar");
		panel.add(btnProcurar);
		
		JPanel panel_1 = new JPanel();
		add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));
		
		JLabel lblNewLabel = new JLabel("Nome:");
		panel_1.add(lblNewLabel, "4, 2, right, default");
		
		textField_1 = new JTextField();
		panel_1.add(textField_1, "6, 2, fill, default");
		textField_1.setColumns(10);
		
		JLabel lblModelo = new JLabel("Modelo:");
		panel_1.add(lblModelo, "4, 4, right, default");
		
		textField_2 = new JTextField();
		panel_1.add(textField_2, "6, 4, fill, default");
		textField_2.setColumns(10);
		
		JLabel lblCor = new JLabel("Cor: ");
		panel_1.add(lblCor, "4, 6, right, default");
		
		textField_3 = new JTextField();
		panel_1.add(textField_3, "6, 6, fill, default");
		textField_3.setColumns(10);
		
		JLabel lblAnoFabricado = new JLabel("Ano fabricado:");
		panel_1.add(lblAnoFabricado, "4, 8, right, default");
		
		textField_4 = new JTextField();
		panel_1.add(textField_4, "6, 8, fill, default");
		textField_4.setColumns(10);
		
		JLabel lblAnoModelo = new JLabel("Ano modelo:");
		panel_1.add(lblAnoModelo, "4, 10, right, default");
		
		textField_5 = new JTextField();
		panel_1.add(textField_5, "6, 10, fill, default");
		textField_5.setColumns(10);
		
		JLabel lblLocal = new JLabel("Local:");
		panel_1.add(lblLocal, "4, 12, right, default");
		
		textField_6 = new JTextField();
		panel_1.add(textField_6, "6, 12, fill, default");
		textField_6.setColumns(10);
		
		JLabel lblObservaes = new JLabel("Observa\u00E7\u00F5es:");
		panel_1.add(lblObservaes, "4, 14, right, default");
		
		textField_7 = new JTextField();
		panel_1.add(textField_7, "6, 14, fill, default");
		textField_7.setColumns(10);
		
		JLabel lblLicenciamento = new JLabel("Licenciamento:");
		panel_1.add(lblLicenciamento, "4, 16, right, default");
		
		textField_8 = new JTextField();
		panel_1.add(textField_8, "6, 16, fill, default");
		textField_8.setColumns(10);
	}
}
