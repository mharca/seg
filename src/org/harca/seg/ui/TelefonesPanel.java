package org.harca.seg.ui;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class TelefonesPanel extends JPanel{
	private String fotoUrl="Ramais2.jpg";

	public TelefonesPanel(){
		
		try{
			
		//	URL url = new URL(fotoUrl);
			//BufferedImage image = ImageIO.read(url);
			JLabel jl = new JLabel(new ImageIcon(fotoUrl));
			JScrollPane jsp = new JScrollPane();
			jsp.add(jl);
			jsp.setViewportView(jl);
			add(jsp);
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
}
