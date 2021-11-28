package controller;
import javax.swing.*;
import java.awt.event.*;
import Model.*;
import View.*;
public class SimularDados implements ActionListener {
	Frame f;
	Regras regras;
	JComboBox dado1, dado2;
	int soma[] = {0,0,0,0};
	public SimularDados(Frame f,Regras regras, JComboBox d1, JComboBox d2){
		this.f = f;
		this.regras = regras;
		dado1 = d1;
		dado2 = d2;
	}
	
	public void actionPerformed(ActionEvent e) {
		if(regras.colorido) {
			regras.dadocolorido();
			regras.colorido = false;
			regras.acaocor = true;
		}
		else {
			if(!regras.ord_ind) {
				regras.set_dados(dado1.getSelectedIndex()+1,dado2.getSelectedIndex()+1);
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
				regras.set_dados(dado1.getSelectedIndex()+1,dado2.getSelectedIndex()+1);
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
