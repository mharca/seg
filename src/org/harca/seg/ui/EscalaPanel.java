package org.harca.seg.ui;
import javax.swing.*;
public class EscalaPanel extends JPanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String fotoUrl="Arquivos/escala2016.gif";

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
