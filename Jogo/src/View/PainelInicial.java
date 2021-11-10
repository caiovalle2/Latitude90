package View;

import java.awt.*;
import javax.swing.*;

public class PainelInicial extends JPanel{  
	
	public PainelInicial() {

	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		
		g2d.drawRect(300, 60, 200, 50);
		g2d.setPaint(Color.LIGHT_GRAY);
		g2d.fillRect(300,60,200,50);
		g2d.setPaint(Color.BLACK);
		g2d.drawString("Jogo - 1 x 1", 360, 90);
		
		g2d.drawRect(300, 120, 200, 50);
		g2d.setPaint(Color.LIGHT_GRAY);
		g2d.fillRect(300,120,200,50);
		g2d.setPaint(Color.BLACK);
		g2d.drawString("Jogo - 4 individual", 360, 150);

		g2d.drawRect(300, 180, 200, 50);
		g2d.setPaint(Color.LIGHT_GRAY);
		g2d.fillRect(300,180,200,50);
		g2d.setPaint(Color.BLACK);
		g2d.drawString("Jogo - 4 dupla", 360, 210);
		
		
	}
	
	  
}
