package View;
import java.awt.*;

import javax.swing.*;

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
		
		info.setBounds(900,190,200,50);
		info.setFont(new Font("Arial",Font.BOLD,20));
		add(info);
		
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
