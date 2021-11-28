package controller;
import java.awt.event.*;
import Model.*;
import java.io.*;
import javax.swing.JFileChooser;
import View.*;
public class Carregar implements ActionListener {
	Regras regras;
	TelaInicial f;
	public Carregar(TelaInicial f){
		this.f = f;
	}
	public void actionPerformed(ActionEvent a) {
		FileReader arq ;
		JFileChooser nome = new JFileChooser("src/Arquivossalvos");
		BufferedReader l;
		try {
			
			int num = nome.showDialog(f, "Confirmar");
			if(num == nome.APPROVE_OPTION) {
				arq = new FileReader("src/Arquivossalvos/"+ nome.getSelectedFile().getName());
				l = new BufferedReader(arq);
				String linha , vet[];
				int modo, qnt, exp[] = {0,0};
				linha = l.readLine();
				qnt = Integer.parseInt(linha);
				linha = l.readLine();
				modo = Integer.parseInt(linha);
				regras = new Regras(modo,qnt);
				for(int x = 0; x < qnt; x++) {
					linha = l.readLine();
					vet = linha.split(" ");
					int w = 0;
					for(int y = 0 ; y < 6; y++) {
						exp[0] = Integer.parseInt(vet[w]);
						exp[1] = Integer.parseInt(vet[w+1]);
						w += 2;
						regras.set_posicao(x,y, exp[1], exp[0]);
					}
					linha = l.readLine();
					regras.set_metas(x, Integer.parseInt(linha));
					linha = l.readLine();
					regras.set_pontos(x, Integer.parseInt(linha));
				}
				linha = l.readLine();
				regras.ind = Integer.parseInt(linha);
				linha = l.readLine();
				vet = linha.split(" ");
				regras.indice[0] = Integer.parseInt(vet[0]);
				regras.indice[1] = Integer.parseInt(vet[1]);
				regras.indice[2] = Integer.parseInt(vet[2]);
				regras.indice[3] = Integer.parseInt(vet[3]);
				linha = l.readLine();
				vet = linha.split(" ");
				if(vet[0] == "false") {
					regras.ord_ind = false;
				}
				else {
					regras.ord_ind = true;
				}
				regras.i = Integer.parseInt(vet[1]);	
				Frame tab = new Frame(regras);
				tab.setVisible(true);
				f.dispose();
				System.out.print("Carregando..." + nome.getSelectedFile().getName());
				
			}
			
			
			
		}catch(IOException i) {
			System.exit(1);
		}
		}
		
	}

