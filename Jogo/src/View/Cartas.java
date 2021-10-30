package View;
import java.awt.*;
import java.io.*;
import javax.imageio.*;
public class Cartas {
	Image []cartas = new Image[18];
	
	public Cartas(){
		try {
			cartas[0] = ImageIO.read(new File("src\\C01.png"));
			cartas[1] = ImageIO.read(new File("src\\C02.png"));
			cartas[2] = ImageIO.read(new File("src\\C03.png"));
			cartas[3] = ImageIO.read(new File("src\\C04.png"));
			cartas[4] = ImageIO.read(new File("src\\C05.png"));
			cartas[5] = ImageIO.read(new File("src\\C06.png"));
			cartas[6] = ImageIO.read(new File("src\\C07.png"));
			cartas[7] = ImageIO.read(new File("src\\C08.png"));
			cartas[8] = ImageIO.read(new File("src\\C09.png"));
			cartas[9] = ImageIO.read(new File("src\\C10.png"));
			cartas[10] = ImageIO.read(new File("src\\C11.png"));
			cartas[11] = ImageIO.read(new File("src\\C12.png"));
			cartas[12] = ImageIO.read(new File("src\\C13.png"));
			cartas[13] = ImageIO.read(new File("src\\C14.png"));
			cartas[14] = ImageIO.read(new File("src\\C15.png"));
			cartas[15] = ImageIO.read(new File("src\\C16.png"));
			cartas[16] = ImageIO.read(new File("src\\C17.png"));
			cartas[17] = ImageIO.read(new File("src\\C18.png"));

		  } catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			System.exit(1);
		}
		
	
	}
	public Image Cartas(int valor) {
		return cartas[valor];

	}
	
}
