package org.harca.seg.garagem.ui;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.Properties;

import javax.swing.JTextField;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

public class JanGaragemCadastro extends JPanel{
	private JTextField textField;
	public JanGaragemCadastro(){
		//setLayout(new BorderLayout());
		setLayout(new FlowLayout());
		textField = new JTextField();
		//textField.setBounds(96, 47, 150, 19);
		//add(textField);
		textField.setColumns(10);
		
		JLabel lblNome = new JLabel("Nome? ");
		UtilDateModel model = new UtilDateModel();
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		JDatePanelImpl datePanel = new JDatePanelImpl(model,p);
		JDatePickerImpl jdp = new JDatePickerImpl(datePanel,null);
		//datePanel.setSize(400, 300);
		
		lblNome.setBounds(12, 49, 61, 15);
		jdp.setBounds(100, 47, 150, 19);
		add(lblNome,BorderLayout.CENTER);
		add(jdp);
	}
}
