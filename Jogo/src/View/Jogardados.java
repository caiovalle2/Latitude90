package View;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import Model.*;
public class Jogardados implements  ActionListener{
	Regras regras;
	Dados imgdados;
	public Jogardados(Regras regras) {
		this.regras = regras;
	}
	public void actionPerformed(ActionEvent e) {
		int dados[] = {0,0};
		
		dados = regras.jogardado();
		/*Exibir os dados*/
		
	}
}
