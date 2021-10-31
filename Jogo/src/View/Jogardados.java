package View;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import Model.*;
public class Jogardados implements  ActionListener{
	Regras regras;
	Dados imgdados;
	Frame f;
	boolean estado = true;
	public Jogardados(Regras regras, Frame f) {
		this.regras = regras;
		this.f = f;
	}
	public void actionPerformed(ActionEvent e) {
		
		
		if(estado) {
			int dados[] = {0,0};
			dados = regras.jogardado();
			/*Exibir os dados*/
			f.repaint();
			estado = false;
		}
		
		
	}
}
