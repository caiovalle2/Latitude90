package controller;
import java.awt.event.*;
import Model.*;
import java.io.*;
import java.io.IOException;
import javax.swing.JFileChooser;
import View.*;
public class Salvar implements ActionListener {
	Regras regras;
	Frame f;
	public Salvar(Frame f,Regras r){
		regras = r;
		this.f = f;
	}
	public void actionPerformed(ActionEvent a) {
		PrintWriter e = null;
		JFileChooser novo = new JFileChooser("src/Arquivossalvos");
		try {
			e = new PrintWriter("src/Arquivossalvos/arq.txt","UTF-8");
			int num = novo.showDialog(f, "Salvar");
			if(num == novo.APPROVE_OPTION) {
				if(novo.getSelectedFile().getName() != "") {
					e = new PrintWriter("src/Arquivossalvos/" + novo.getSelectedFile().getName(),"UTF-8");
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
		}catch(IOException i) {
			System.exit(1);
		}
		
	}
}
