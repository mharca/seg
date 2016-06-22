package org.harca.seg.ui;
import java.awt.image.BufferedImage;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.*;
public class EscalaPanel extends JPanel
{
	private String fotoUrl="escala2016.gif";

	public EscalaPanel(){
		
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
