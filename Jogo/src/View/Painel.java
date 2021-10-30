package View;

import java.awt.*;
import javax.swing.*;
import java.io.*;
import javax.imageio.*;
public class Painel extends JPanel{
	Image tab;
	Dados dado = new Dados();

	JButton botao;
	int x,y;
	
	public Painel() {
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		try {
			tab = ImageIO.read(new File("src\\Latitude90-Tabuleiro.jpg"));
		  } catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			System.exit(1);
		}
		g.drawImage(dado.Dado(1),800,0,null);
		g.drawImage(dado.Dado(2),900,0,null);
		g.drawImage(tab,0,0,null);
	}
	
	  
}
