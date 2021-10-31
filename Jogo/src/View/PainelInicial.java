package View;

import java.awt.*;
import javax.swing.*;

public class PainelInicial extends JPanel{

	
	public PainelInicial() {

	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.drawRect(300, 100, 200, 50);
		g.drawString("Jogo - 1 x 1", 360, 130);
		g.drawRect(300, 160, 200, 50);
		g.drawString("Jogo - 4 individual", 360, 190);
		g.drawRect(300, 220, 200, 50);
		g.drawString("Jogo - 4 dupla", 360, 250);
		
		
	}
	
	  
}
