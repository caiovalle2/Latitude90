package View;

import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;
import java.io.*;
import javax.imageio.*;
import Model.*;
public class Painel extends JPanel{
	Image tab;
	Dados dado = new Dados();
	Regras regras;
	int x,y;
	
	public Painel(Regras regras) {
		this.regras = regras;
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		int num;
		Graphics2D g2d = (Graphics2D) g;
		try {
			tab = ImageIO.read(new File("src\\Latitude90-Tabuleiro.jpg"));
		  } catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			System.exit(1);
		}
		/*Exibir dados*/
		
		num = regras.dados[0] -1;
		g2d.drawImage(dado.Dado(num),800,0,null);
		num = regras.dados[1] -1;
		g2d.drawImage(dado.Dado(num),900,0,null);
		/*Exibir Tabuleiro*/
		g2d.drawImage(tab,0,0,null);
		
		/*Exibir Exploradores*/
		
		
	}
	
	  
}
