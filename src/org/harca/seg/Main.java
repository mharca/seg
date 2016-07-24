package org.harca.seg;
import javax.swing.*;

import org.harca.seg.ui.*;

public class Main {

	public static void main(String[] args) {
		
		try{
			//UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			//UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
			//UIManager.setLookAndFeel("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel");
		}catch(Exception e){
			e.printStackTrace();
		}
		java.awt.EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				
			//	JanPrincipal j = new JanPrincipal();
				new JanMain();
			//	j.set
			}
		});
		
	}

}
