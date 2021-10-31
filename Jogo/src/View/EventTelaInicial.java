package View;
import java.awt.event.*;
import Model.*;
public class EventTelaInicial implements MouseListener{
	TelaInicial f;
	public EventTelaInicial(TelaInicial f) {
		this.f = f;
	}
	public void mouseClicked(MouseEvent e) {
		
		int x = e.getX(), y = e.getY(), estado =0;
		Regras regras = new Regras(0,0);
		
		if((x > 300 && y > 100) && (x < 500 && y <150)) {
			regras = new Regras(0,2);
			estado = 1;
		}
		else if((x > 300 && y > 160) && (x < 500 && y <210)) {
			regras = new Regras(0,4);
			estado = 1;

		}
		else if((x > 300 && y > 220) && (x < 500 && y <270)) {
			regras = new Regras(1,4);
			estado =1;

		}
		
		if(estado == 1) {
			Frame tab = new Frame(regras);
			tab.setVisible(true);
			f.setVisible(false);

		}
		
	}
	
	 public void mousePressed(MouseEvent e) {}

	 public void mouseReleased(MouseEvent e) {}

	 public void mouseEntered(MouseEvent e) {}

	 public void mouseExited(MouseEvent e) {}
}
