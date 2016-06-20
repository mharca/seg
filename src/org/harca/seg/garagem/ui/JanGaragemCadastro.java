package org.harca.seg.garagem.ui;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Insets;
import java.util.Properties;

import javax.swing.JTextField;
import javax.swing.border.Border;

import org.harca.seg.garagem.ui.panels.Pvisitado;
import org.harca.seg.garagem.ui.panels.Pvisitante;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

public class JanGaragemCadastro extends JPanel{
	private JTextField textField;
	public JanGaragemCadastro(){
		setLayout(new FlowLayout());
		Pvisitante pv = new Pvisitante();
		add(pv);
		add(new Pvisitado());
		
		//this.setBorder(BorderFactory.createTitledBorder("Visitante"));
		
	}
}
