package controller;
import java.awt.event.*;
import javax.swing.*;

import View.*;
import Model.*;
public class JogarDados implements  ActionListener{
	Regras regras;
	Dados imgdados;
	Frame f;
	int soma[] = {0,0,0,0};
	public JogarDados(Regras regras, Frame f) {
		this.regras = regras;
		this.f = f;
	}
	public void actionPerformed(ActionEvent e) {
		int dados[] = {0,0};
		
		if(regras.colorido) {
			regras.dadocolorido();
			regras.colorido = false;
			regras.acaocor = true;
		}
		else {
			
		
			if(!regras.ord_ind) {
				regras.jogardado();
				soma[regras.i] = regras.dados[0] + regras.dados[1];
				regras.i++;
				if(regras.i == regras.qnt) {
					for(int x = 0; x < regras.i; x++) {
						int n = 0;
						int val = 0, val2 = 0;
						for(int y = 0; y < regras.i - x - 1; y++) {
							if(soma[y] < soma[y+1]) {
								n = y;
							}
							else {
								n = y+1;
							}
						}
						val = soma[regras.i-x-1];
						soma[regras.i-x-1] = soma[n];
						soma[n] = val;
						val2 = regras.indice[regras.i-x-1];
						regras.indice[regras.i-x-1] = regras.indice[n];
						regras.indice[n] = val2;
					}
					regras.ord_ind = true;
					System.out.println(regras.i + " "+ regras.indice[0] + " " + regras.indice[1]);
				}
				
			}
			else if(regras.estado) {
				
				dados = regras.jogardado();
				/*Exibir os dados*/
				regras.estado = false;
				regras.iniciar = true;
			}
			
			if(regras.dados[0] == regras.dados[1] && regras.iniciar) {
				regras.colorido = true;
				regras.iniciar = false;
				
			}
		}
		f.repaint();
		
	}
}

