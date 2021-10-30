package View;
import java.awt.*;
import java.io.*;

import javax.swing.*;

import Model.*;

import java.awt.*;
public class Frame extends JFrame {
	public final int LARG_DEFAULT=1200;
	public final int ALT_DEFAULT=750;
	JPanel p;
	
	public Frame(Regras regras) {
		JButton dados = new JButton("Jogar Dados");
		dados.setBounds(800,120,200,50);
		add(dados);
		Toolkit tk=Toolkit.getDefaultToolkit();
		Dimension screenSize=tk.getScreenSize();
		int sl=screenSize.width;
		int sa=screenSize.height;
		int x=sl/2-LARG_DEFAULT/2;
		int y=sa/2-ALT_DEFAULT/2;
		p = new Painel();
		p.addMouseListener(new EventMov());
		getContentPane().add(p);
		setBounds(x,y,LARG_DEFAULT,ALT_DEFAULT);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}
