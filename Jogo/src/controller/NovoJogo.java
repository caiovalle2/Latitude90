package controller;
import javax.swing.*;
import java.awt.event.*;
import Model.*;
import View.*;
public class NovoJogo implements ActionListener {
	Frame f;
	public NovoJogo(Frame f){
		this.f = f;
	}
	
	public void actionPerformed(ActionEvent e) {
		TelaInicial t = new TelaInicial();
		t.setVisible(true);
		f.dispose();
	}

}
