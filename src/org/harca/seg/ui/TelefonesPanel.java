package org.harca.seg.ui;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TelefonesPanel extends JPanel{
	private String fotoUrl="escala2015.png";

	public TelefonesPanel(){
		
		try{
			
		//	URL url = new URL(fotoUrl);
			//BufferedImage image = ImageIO.read(url);
			JLabel jl = new JLabel(new ImageIcon(fotoUrl));
			add(jl);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
