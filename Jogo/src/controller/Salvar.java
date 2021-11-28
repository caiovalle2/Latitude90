package controller;
import java.awt.event.*;
import Model.*;
import java.io.*;
import java.io.IOException;
public class Salvar implements ActionListener {
	Regras regras;
	public Salvar(Regras r){
		regras = r;
	}
	public void actionPerformed(ActionEvent a) {
		PrintWriter e = null;
		try {
			e = new PrintWriter("arq.txt","UTF-8");
		}catch(IOException i) {
			System.exit(1);
		}
		
		e.printf("%d\n",regras.qnt);
		e.printf("%d\n",regras.modo);
		for(int x = 0; x < regras.qnt; x++) {
			int exp1[], exp2[], exp3[], exp4[], exp5[], exp6[];
			exp1 = regras.getposicao(x, 0);
			exp2 = regras.getposicao(x, 1);
			exp3 = regras.getposicao(x, 2);
			exp4 = regras.getposicao(x, 3);
			exp5 = regras.getposicao(x, 4);
			exp6 = regras.getposicao(x, 5);
			
			e.printf("%d %d %d %d %d %d %d %d %d %d %d %d\n",exp1[0],exp1[1],exp2[0],exp2[1],exp3[0],exp3[1],exp4[0],exp4[1],exp5[0],exp5[1],exp6[0],exp6[1] );
			e.printf("%d\n", regras.metas(x));
			e.printf("%s\n",regras.get_pontos(x));
		}
		e.printf("%d\n",regras.ind);
		e.printf("%d %d %d %d\n", regras.indice[0],regras.indice[1],regras.indice[2],regras.indice[3]);
		e.printf("%s %d\n",""+regras.ord_ind, regras.i);
		e.close();
		
	}
}
