package View;
import java.awt.*;
import java.io.*;
import javax.imageio.*;
public class Dados {
	Image []dado = new Image[6];
	
	public Dados(){
		try {
			dado[0] = ImageIO.read(new File("src\\Dado1.png"));
			dado[1] = ImageIO.read(new File("src\\Dado2.png"));
			dado[2] = ImageIO.read(new File("src\\Dado3.png"));
			dado[3] = ImageIO.read(new File("src\\Dado4.png"));
			dado[4] = ImageIO.read(new File("src\\Dado5.png"));
			dado[5] = ImageIO.read(new File("src\\Dado6.png"));
			

		  } catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			System.exit(1);
		}
		
	
	}
	public Image Dado(int valor) {
		return dado[valor];

	}
	
}
