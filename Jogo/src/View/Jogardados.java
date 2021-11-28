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
	public Jogardados(Regras regras, Frame f) {
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
				soma[i] = regras.dados[0] + regras.dados[1];
				i++;
				if(i == regras.qnt) {
					for(int x = 0; x < i; x++) {
						int n = 0;
						int val = 0, val2 = 0;
						for(int y = 0; y < i - x - 1; y++) {
							if(soma[y] < soma[y+1]) {
								n = y;
							}
							else {
								n = y+1;
							}
						}
						val = soma[i-x-1];
						soma[i-x-1] = soma[n];
						soma[n] = val;
						val2 = regras.indice[i-x-1];
						regras.indice[i-x-1] = regras.indice[n];
						regras.indice[n] = val2;
					}
					regras.ord_ind = true;
					System.out.println(i + " "+ regras.indice[0] + " " + regras.indice[1]);
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
