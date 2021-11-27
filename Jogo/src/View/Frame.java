package View;
import java.awt.*;

import javax.swing.*;
import java.awt.event.*;
import Model.*;
public class Frame extends JFrame {
	public final int LARG_DEFAULT=1200;
	public final int ALT_DEFAULT=750;
	JPanel p;
	static JLabel info = new JLabel("Inicio de jogo");
	public Frame(Regras regras) {
		JButton dados = new JButton("Jogar Dados");
		dados.setBounds(850,300,200,50);
		dados.addActionListener(new Jogardados(regras,this));
		add(dados);
		
		info.setBounds(850,190,200,50);
		info.setFont(new Font("Arial",Font.BOLD,20));
		add(info);
		
		String dado[] = {"1","2","3","4","5","6"};
		JButton simular = new JButton("Simular");
		JComboBox dado1 = new JComboBox(dado);
		JComboBox dado2 = new JComboBox(dado);
		dado1.setBounds(750,520,110,20);
		dado2.setBounds(900,520,110,20);
		simular.setBounds(1050,520,110,20);
		simular.addActionListener(new ActionListener() {
			int soma[] = {0,0,0,0}, i = 0;
			public void actionPerformed(ActionEvent e) {
				if(regras.colorido) {
					regras.dadocolorido();
					regras.colorido = false;
					regras.acaocor = true;
				}
				else {
					if(!regras.ord_ind) {
						regras.set_dados(dado1.getSelectedIndex()+1,dado2.getSelectedIndex()+1);
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
				repaint();
			}
		});
		add(dado1);
		add(dado2);
		add(simular);
		Toolkit tk=Toolkit.getDefaultToolkit();
		Dimension screenSize=tk.getScreenSize();
		int sl=screenSize.width;
		int sa=screenSize.height;
		int x=sl/2-LARG_DEFAULT/2;
		int y=sa/2-ALT_DEFAULT/2;
		
		/*Painel do jogo*/
		p = new Painel(regras);
		p.addMouseListener(new EventMov(regras,this));
		getContentPane().add(p);
		setBounds(x,y,LARG_DEFAULT,ALT_DEFAULT);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}
