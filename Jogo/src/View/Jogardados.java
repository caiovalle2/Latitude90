package View;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.Arrays.*;
import Model.*;
public class Jogardados implements  ActionListener{
	Regras regras;
	Dados imgdados;
	Frame f;
	int soma[] = {0,0,0,0}, i = 0;
	static boolean estado = true;
	public Jogardados(Regras regras, Frame f) {
		this.regras = regras;
		this.f = f;
	}
	public void actionPerformed(ActionEvent e) {
		int dados[] = {0,0};
		
		if(estado) {
			
			dados = regras.jogardado();
			/*Exibir os dados*/
			estado = false;
		}
		
		if(dados[0] == dados[1]) {
			char colorido = regras.dadocolorido();
			
		}
		f.repaint();
		
	}
}
