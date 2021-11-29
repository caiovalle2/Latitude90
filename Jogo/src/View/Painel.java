package View;

import java.awt.*;
import java.awt.image.BufferedImage;

import java.awt.geom.*;
import javax.swing.*;
import java.io.*;
import javax.imageio.*;
import Model.*;

public class Painel extends JPanel
{
	Image tab;
	Dados dado = new Dados();
	Regras regras;
	Cartas carta = new Cartas();
	Image circ;
	public static int Latitude=12;
	public static int Longitude=12;
	public static int [][][] MatrizTab  = new int[Latitude][Longitude][2];

	int expverde[][] = {{42,560},{53,568}, {66,574},{78,581},{90,586},{102,591}};
    int exppreto[][] = {{40,168},{50,160}, {64,152},{74,146},{88,140},{100,135}};
    int expazul[][] = {{620,132},{632,138}, {645,145},{657,152},{668,159},{680,166}};
    int expamarelo[][] = {{622,590},{634,584}, {646,578},{656,573},{669,563},{679,557}};
    int exp[][][] = {expverde,expamarelo,exppreto,expazul};
    
    int metaverde[][] = {{30,510},{40,520},{50,530},{60,540},{74,548},{86,555},{98,561},{112,568},{126,571},{138,577},{153,581},{167,583}};
    int metapreto[][] = {{30,216},{40,206},{50,196},{60,186},{70,178},{85,173},{97,166},{110,161},{123,155},{135,151},{150,149},{163,145}};
    int metaazul[][] = {{690,215},{680,205},{669,195},{659,185},{647,179},{634,171},{622,165},{610,159},{597,155},{583,151},{569,147},{556,144}};
    int metaamarelo[][] = {{690,512},{681,522},{670,531},{659,539},{649,549},{636,556},{623,561},{611,568},{597,573},{584,576},{569,579},{556,583}};
    
    int metas[][][] = {metaverde,metaamarelo,metapreto,metaazul};
    
	Paint cor[] = {Color.green,Color.YELLOW,Color.white, Color.blue,Color.black,Color.black};

    int cont[] = {0,0,0,0};
    
    
	public Painel(Regras regras) {
		this.regras = regras;
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		int num;
		Graphics2D g2d = (Graphics2D) g;
		try {
			tab = ImageIO.read(new File("src\\Latitude90-Tabuleiro.jpg"));
		  } catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			System.exit(1);
		}
		
		//Exibir Cor de Jogador 
		//Dado esquerda
		Rectangle2D rtE=new Rectangle2D.Double(725,400,150,150);
		g2d.setPaint(cor[regras.rodada()]);
		g2d.fill(rtE);
		//Dado Direita
		Rectangle2D rtD=new Rectangle2D.Double(885,400,150,150);
		g2d.setPaint(cor[regras.rodada()]);
		g2d.fill(rtD);

		//Exibir dado colorido
		Rectangle2D dadocor=new Rectangle2D.Double(1055,425,100,100);
		g2d.setPaint(cor[regras.ind_cor]);
		g2d.fill(dadocor);
		
		//Exibir layout das cartas
		Rectangle2D rtJ1=new Rectangle2D.Double(967,5,200,200);
		g2d.setPaint(cor[5]);
		g2d.fill(rtJ1);
		
		Rectangle2D rtJ2=new Rectangle2D.Double(737,5,200,200);
		g2d.setPaint(cor[5]);
		g2d.fill(rtJ2);
		
		
		//Exibir Cartas
		g2d.drawImage(carta.Cartas(4),980,20,null);
		g2d.drawImage(carta.Cartas(7),750,20,null);;

		
		//Caixa de texto
		Rectangle2D textBox=new Rectangle2D.Double(745, 280, 420, 45);
		g2d.setPaint(Color.LIGHT_GRAY);
		g2d.fill(textBox);
		
		// Textos do jogo
		g2d.setPaint(Color.BLACK);
		g2d.setFont(new Font("Arial",Font.BOLD,20));
		int msg_x = 855, msg_y = 310;
		if(regras.ganhou) {
			char a = regras.ver_ganhador();
			switch(a) {
			case 'G':
				g2d.drawString("Jogador Verde ganhou!", msg_x, msg_y);
				break;
			case 'Y':
				g2d.drawString("Jogador Amarelo ganhou!", msg_x, msg_y);
				break;
			case 'W':
				g2d.drawString("Jogador Branco ganhou!", msg_x, msg_y);
				break;
			case 'B':
				g2d.drawString("Jogador Azul ganhou!", msg_x, msg_y);
				break;
			case '1':
				g2d.drawString("Dupla Verde e amarelo ganhou!", msg_x, msg_y);
				break;
			case '2':
				g2d.drawString("Dupla Branco e azul ganhou!", msg_x, msg_y);
				break;
			}

		}
		else if(regras.colorido || regras.acaocor) {
			g.drawString("Dado Colorido!", msg_x, msg_y);
		}
		else if(!regras.ord_ind) {
			g.drawString("Definindo a ordem dos jogadores!", msg_x - 50, msg_y);
		}
		else {
			g.drawString("Informação do jogo!", msg_x, msg_y);
		}
		
		
		/*Exibir dados*/
		
		num = regras.dados[0] -1;
		g2d.drawImage(dado.Dado(num),750,425,null);
		num = regras.dados[1] -1;
		g2d.drawImage(dado.Dado(num),910,425,null);
		/*Exibir Tabuleiro*/
		g2d.drawImage(tab,0,0,null);
		
		//Latitude 1 -> Longitude de 1 a 12
		MatrizTab[0][0][0] = 237;
		MatrizTab[0][0][1] = 350;
		
		MatrizTab[0][1][0] = 233;
		MatrizTab[0][1][1] = 330;
		
		MatrizTab[0][2][0] = 212;
		MatrizTab[0][2][1] = 316;
		
		MatrizTab[0][3][0] = 186;
		MatrizTab[0][3][1] = 316;
		
		MatrizTab[0][4][0] = 158;
		MatrizTab[0][4][1] = 334;
		
		MatrizTab[0][5][0] = 155;
		MatrizTab[0][5][1] = 353;
		
		MatrizTab[0][6][0] = 152;
		MatrizTab[0][6][1] = 378;
		
		MatrizTab[0][7][0] = 164;
		MatrizTab[0][7][1] = 400;
		
		MatrizTab[0][8][0] = 186;
		MatrizTab[0][8][1] = 412;
		
		MatrizTab[0][9][0] = 211;
		MatrizTab[0][9][1] = 411;
		
		MatrizTab[0][10][0] = 234;
		MatrizTab[0][10][1] = 400;
		
		MatrizTab[0][11][0] = 241;
		MatrizTab[0][11][1] = 376;
		
		//Latitude 2 -> Longitude de 1 a 12
		MatrizTab[1][0][0] = 265;
		MatrizTab[1][0][1] = 350;
		
		MatrizTab[1][1][0] = 250;
		MatrizTab[1][1][1] = 313;
		
		MatrizTab[1][2][0] = 216;
		MatrizTab[1][2][1] = 288;
		
		MatrizTab[1][3][0] = 177;
		MatrizTab[1][3][1] = 288;
		
		MatrizTab[1][4][0] = 147;
		MatrizTab[1][4][1] = 310;
		
		MatrizTab[1][5][0] = 135;
		MatrizTab[1][5][1] = 347;
		
		MatrizTab[1][6][0] = 135;
		MatrizTab[1][6][1] = 386;
		
		MatrizTab[1][7][0] = 146;
		MatrizTab[1][7][1] = 421;
		
		MatrizTab[1][8][0] = 180;
		MatrizTab[1][8][1] = 437;
		
		MatrizTab[1][9][0] = 217;
		MatrizTab[1][9][1] = 437;
		
		MatrizTab[1][10][0] = 253;
		MatrizTab[1][10][1] = 417;
		
		MatrizTab[1][11][0] = 262;
		MatrizTab[1][11][1] = 381;
		
		//Latitude 3 -> Longitude de 1 a 12
		MatrizTab[2][0][0] = 283;
		MatrizTab[2][0][1] = 342;
		
		MatrizTab[2][1][0] = 268;
		MatrizTab[2][1][1] = 296;
		
		MatrizTab[2][2][0] = 228;
		MatrizTab[2][2][1] = 269;
		
		MatrizTab[2][3][0] = 171;
		MatrizTab[2][3][1] = 264;
		
		MatrizTab[2][4][0] = 127;
		MatrizTab[2][4][1] = 299;
		
		MatrizTab[2][5][0] = 113;
		MatrizTab[2][5][1] = 340;
		
		MatrizTab[2][6][0] = 111;
		MatrizTab[2][6][1] = 392;
		
		MatrizTab[2][7][0] = 127;
		MatrizTab[2][7][1] = 439;
		
		MatrizTab[2][8][0] = 170;
		MatrizTab[2][8][1] = 464;
		
		MatrizTab[2][9][0] = 222;
		MatrizTab[2][9][1] = 462;
		
		MatrizTab[2][10][0] = 268;
		MatrizTab[2][10][1] = 438;
		
		MatrizTab[2][11][0] = 285;
		MatrizTab[2][11][1] = 387;
		
		//Latitude 4 -> Longitude de 1 a 12
		MatrizTab[3][0][0] = 305;
		MatrizTab[3][0][1] = 337;
		
		MatrizTab[3][1][0] = 284;
		MatrizTab[3][1][1] = 276;
		
		MatrizTab[3][2][0] = 239;
		MatrizTab[3][2][1] = 253;
		
		MatrizTab[3][3][0] = 171;
		MatrizTab[3][3][1] = 243;
		
		MatrizTab[3][4][0] = 108;
		MatrizTab[3][4][1] = 276;
		
		MatrizTab[3][5][0] = 88;
		MatrizTab[3][5][1] = 337;
		
		MatrizTab[3][6][0] = 86;
		MatrizTab[3][6][1] = 395;
		
		MatrizTab[3][7][0] = 110;
		MatrizTab[3][7][1] = 457;
		
		MatrizTab[3][8][0] = 166;
		MatrizTab[3][8][1] = 487;
		
		MatrizTab[3][9][0] = 229;
		MatrizTab[3][9][1] = 487;
		
		MatrizTab[3][10][0] = 286;
		MatrizTab[3][10][1] = 456;
		
		MatrizTab[3][11][0] = 306;
		MatrizTab[3][11][1] = 398;
		
		//Latitude 5 -> Longitude de 1 a 12
		MatrizTab[4][0][0] = 329;
		MatrizTab[4][0][1] = 330;
		
		MatrizTab[4][1][0] = 303;
		MatrizTab[4][1][1] = 258;
		
		MatrizTab[4][2][0] = 238;
		MatrizTab[4][2][1] = 222;
		
		MatrizTab[4][3][0] = 160;
		MatrizTab[4][3][1] = 218;
		
		MatrizTab[4][4][0] = 92;
		MatrizTab[4][4][1] = 258;
		
		MatrizTab[4][5][0] = 63;
		MatrizTab[4][5][1] = 331;
		
		MatrizTab[4][6][0] = 66;
		MatrizTab[4][6][1] = 405;
		
		MatrizTab[4][7][0] = 86;
		MatrizTab[4][7][1] = 474;
		
		MatrizTab[4][8][0] = 158;
		MatrizTab[4][8][1] = 514;
		
		MatrizTab[4][9][0] = 238;
		MatrizTab[4][9][1] = 516;
		
		MatrizTab[4][10][0] = 308;
		MatrizTab[4][10][1] = 474;
		
		MatrizTab[4][11][0] = 330;
		MatrizTab[4][11][1] = 401;
		
		//Latitude 6 -> Longitude de 1 a 12
		MatrizTab[5][0][0] = 355;
		MatrizTab[5][0][1] = 326;
		
		MatrizTab[5][1][0] = 321;
		MatrizTab[5][1][1] = 239;
		
		MatrizTab[5][2][0] = 240;
		MatrizTab[5][2][1] = 194;
		
		MatrizTab[5][3][0] = 150;
		MatrizTab[5][3][1] = 189;
		
		MatrizTab[5][4][0] = 64;
		MatrizTab[5][4][1] = 241;
		
		MatrizTab[5][5][0] = 42;
		MatrizTab[5][5][1] = 320;
		
		MatrizTab[5][6][0] = 45;
		MatrizTab[5][6][1] = 403;
		
		MatrizTab[5][7][0] = 74;
		MatrizTab[5][7][1] = 496;
		
		MatrizTab[5][8][0] = 154;
		MatrizTab[5][8][1] = 537;
		
		MatrizTab[5][9][0] = 244;
		MatrizTab[5][9][1] = 534;
		
		MatrizTab[5][10][0] = 319;
		MatrizTab[5][10][1] = 491;
		
		MatrizTab[5][11][0] = 354;
		MatrizTab[5][11][1] = 402;
		
		//Latitude 7 -> Longitude de 1 a 12
		MatrizTab[6][0][0] = 375;
		MatrizTab[6][0][1] = 322;
		
		MatrizTab[6][1][0] = 402;
		MatrizTab[6][1][1] = 237;
		
		MatrizTab[6][2][0] = 484;
		MatrizTab[6][2][1] = 194;
		
		MatrizTab[6][3][0] = 573;
		MatrizTab[6][3][1] = 194;
		
		MatrizTab[6][4][0] = 658;
		MatrizTab[6][4][1] = 239;
		
		MatrizTab[6][5][0] = 683;
		MatrizTab[6][5][1] = 322;
		
		MatrizTab[6][6][0] = 684;
		MatrizTab[6][6][1] = 404;
		
		MatrizTab[6][7][0] = 656;
		MatrizTab[6][7][1] = 493;
		
		MatrizTab[6][8][0] = 576;
		MatrizTab[6][8][1] = 535;
		
		MatrizTab[6][9][0] = 481;
		MatrizTab[6][9][1] = 538;
		
		MatrizTab[6][10][0] = 407;
		MatrizTab[6][10][1] = 497;
		
		MatrizTab[6][11][0] = 373;
		MatrizTab[6][11][1] = 397;
		//Latitude 8 -> Longitude de 1 a 12
		MatrizTab[7][0][0] = 397;
		MatrizTab[7][0][1] = 327;
		
		MatrizTab[7][1][0] = 424;
		MatrizTab[7][1][1] = 256;
		
		MatrizTab[7][2][0] = 492;
		MatrizTab[7][2][1] = 220;
		
		MatrizTab[7][3][0] = 566;
		MatrizTab[7][3][1] = 220;
		
		MatrizTab[7][4][0] = 635;
		MatrizTab[7][4][1] = 256;
		
		MatrizTab[7][5][0] = 660;
		MatrizTab[7][5][1] = 331;
		
		MatrizTab[7][6][0] = 660;
		MatrizTab[7][6][1] = 398;
		
		MatrizTab[7][7][0] = 635;
		MatrizTab[7][7][1] = 475;
		
		MatrizTab[7][8][0] = 570;
		MatrizTab[7][8][1] = 512;
		
		MatrizTab[7][9][0] = 490;
		MatrizTab[7][9][1] = 509;
		
		MatrizTab[7][10][0] = 421;
		MatrizTab[7][10][1] = 475;
		
		MatrizTab[7][11][0] = 399;
		MatrizTab[7][11][1] = 401;
		
		//Latitude 9 -> Longitude de 1 a 12
		MatrizTab[8][0][0] = 418;
		MatrizTab[8][0][1] = 334;
		
		MatrizTab[8][1][0] = 443;
		MatrizTab[8][1][1] = 274;
		
		MatrizTab[8][2][0] = 497;
		MatrizTab[8][2][1] = 245;
		
		MatrizTab[8][3][0] = 560;
		MatrizTab[8][3][1] = 245;
		
		MatrizTab[8][4][0] = 619;
		MatrizTab[8][4][1] = 274;
		
		MatrizTab[8][5][0] = 638;
		MatrizTab[8][5][1] = 334;
		
		MatrizTab[8][6][0] = 638;
		MatrizTab[8][6][1] = 396;
		
		MatrizTab[8][7][0] = 618;
		MatrizTab[8][7][1] = 453;
		
		MatrizTab[8][8][0] = 562;
		MatrizTab[8][8][1] = 487;
		
		MatrizTab[8][9][0] = 495;
		MatrizTab[8][9][1] = 487;
		
		MatrizTab[8][10][0] = 443;
		MatrizTab[8][10][1] = 458;
		
		MatrizTab[8][11][0] = 422;
		MatrizTab[8][11][1] = 392;
		
		//Latitude 10 -> Longitude de 1 a 12
		MatrizTab[9][0][0] = 443;
		MatrizTab[9][0][1] = 340;
		
		MatrizTab[9][1][0] = 460;
		MatrizTab[9][1][1] = 292;
		
		MatrizTab[9][2][0] = 505;
		MatrizTab[9][2][1] = 266;
		
		MatrizTab[9][3][0] = 558;
		MatrizTab[9][3][1] = 267;
		
		MatrizTab[9][4][0] = 594;
		MatrizTab[9][4][1] = 294;
		
		MatrizTab[9][5][0] = 618;
		MatrizTab[9][5][1] = 342;
		
		MatrizTab[9][6][0] = 618;
		MatrizTab[9][6][1] = 389;
		
		MatrizTab[9][7][0] = 602;
		MatrizTab[9][7][1] = 437;
		
		MatrizTab[9][8][0] = 555;
		MatrizTab[9][8][1] = 463;
		
		MatrizTab[9][9][0] = 504;
		MatrizTab[9][9][1] = 462;
		
		MatrizTab[9][10][0] = 460;
		MatrizTab[9][10][1] = 437;
		
		MatrizTab[9][11][0] = 443;
		MatrizTab[9][11][1] = 389;
		
		//Latitude 11 -> Longitude de 1 a 12
		MatrizTab[10][0][0] = 462;
		MatrizTab[10][0][1] = 346;
		
		MatrizTab[10][1][0] = 478;
		MatrizTab[10][1][1] = 313;
		
		MatrizTab[10][2][0] = 511;
		MatrizTab[10][2][1] = 293;
		
		MatrizTab[10][3][0] = 548;
		MatrizTab[10][3][1] = 293;
		
		MatrizTab[10][4][0] = 582;
		MatrizTab[10][4][1] = 311;
		
		MatrizTab[10][5][0] = 594;
		MatrizTab[10][5][1] = 345;
		
		MatrizTab[10][6][0] = 594;
		MatrizTab[10][6][1] = 383;
		
		MatrizTab[10][7][0] = 581;
		MatrizTab[10][7][1] = 418;
		
		MatrizTab[10][8][0] = 548;
		MatrizTab[10][8][1] = 437;
		
		MatrizTab[10][9][0] = 510;
		MatrizTab[10][9][1] = 437;
		
		MatrizTab[10][10][0] = 476;
		MatrizTab[10][10][1] = 420;
		
		MatrizTab[10][11][0] = 464;
		MatrizTab[10][11][1] = 384;
		
		//Latitude 12 -> Longitude de 1 a 12
		MatrizTab[11][0][0] = 487;
		MatrizTab[11][0][1] = 353;
		
		MatrizTab[11][1][0] = 495;
		MatrizTab[11][1][1] = 330;
		
		MatrizTab[11][2][0] = 518;
		MatrizTab[11][2][1] = 318;
		
		MatrizTab[11][3][0] = 543;
		MatrizTab[11][3][1] = 320;
		
		MatrizTab[11][4][0] = 563;
		MatrizTab[11][4][1] = 328;
		
		MatrizTab[11][5][0] = 572;
		MatrizTab[11][5][1] = 351;
		
		MatrizTab[11][6][0] = 572;
		MatrizTab[11][6][1] = 377;
		
		MatrizTab[11][7][0] = 564;
		MatrizTab[11][7][1] = 399;
		
		MatrizTab[11][8][0] = 541;
		MatrizTab[11][8][1] = 412;
		
		MatrizTab[11][9][0] = 518;
		MatrizTab[11][9][1] = 413;
		
		MatrizTab[11][10][0] = 497;
		MatrizTab[11][10][1] = 398;
		
		MatrizTab[11][11][0] = 488;
		MatrizTab[11][11][1] = 378;
		
		//Exibir Exploradores		
		int cont[] = {0,0,0,0}, num_exp;
		boolean polo = false, polo1 = false;
		for (int i=0; i< regras.qnt ; i++)
	   {
			int lat = 0, lon = 0;
			/*Exibe os 6 exploradores do jogador*/
	      for (int j=0; j < 6 ; j++) 
	      {
	    	   lat = regras.getposicao(i, j)[1];
	    	   lon = regras.getposicao(i, j)[0];
	    	  g2d.setRenderingHint (RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		   	  g2d.setPaint(cor[i]);
	    	  if(lat != 13 && lat != 0) {
	    		  num_exp = regras.ver_posicao(i, lon, lat)[1];
	    		  /*Exibe os exploradores conforme o numero em cada casa*/
	    		  if(num_exp < 3) {
	    			  for(int x = 0; x < num_exp; x++) {
	    				  g2d.fillOval(MatrizTab[lat-1][lon][0] - 10*x, MatrizTab[lat-1][lon][1] - 10*x, 10, 10);
	    			  }
	    		  }
	    		  else if(num_exp >= 3) {
	    			  
	    			  g2d.fillOval(MatrizTab[lat-1][lon][0], MatrizTab[lat-1][lon][1], 15, 15);
	    			  
	    			  num_exp -= 3;
	    			  for(int x = 1; x <= num_exp; x++) {
	    				  g2d.fillOval(MatrizTab[lat-1][lon][0] - 10*x, MatrizTab[lat-1][lon][1] - 10*x, 10, 10);
	    			  }
	    		  }
	    		  else if( num_exp == 6) {
	    			  g2d.fillOval(MatrizTab[lat-1][lon][0], MatrizTab[lat-1][lon][1], 15, 15);
	    			  g2d.fillOval(MatrizTab[lat-1][lon][0] - 15, MatrizTab[lat-1][lon][1] - 15, 15, 15);
	    		  }

	    	  }
	    	  else if( i == regras.rodada() && lon != -1) {
	    		  if(lat == 13) {
	    			  polo = true;
	    		  }
	    		  else {
	    			  polo1 = true;
	    		  }
	    	  }
	    	  /*Conta quantos exploradores estão no polo oposto*/
	    	  if(lon == -1) {
	    		  cont[i]++;
	    	  }
	      }
	      /*Exibe os exploradores no polo*/
	      if(i == regras.rodada()) {
    		  if(polo1 == true) {
	    		  g2d.fillOval(198, 367, 10, 10);
	    		  polo1 = false;
	    	  }
	    	  else if(polo == true) {
	    		  g2d.fillOval(530, 367, 10, 10);
	    		  polo = false;
	    	  }
    	  }
	   }
		/*Exibe os exploradores que chegaram no polo oposto*/
		for(int i = 0; i < regras.qnt; i++) {
			for(int j =0; j <cont[i];j++) {
				g2d.setPaint(cor[i]);
				 g2d.fillOval(exp[i][j][0], exp[i][j][1], 10, 10);
			}
			for(int j = 0; j<regras.metas(i);j++) {
				
				g2d.setPaint(cor[i]);
				 g2d.fillOval(metas[i][j][0], metas[i][j][1], 10, 10);
				
			}
			 
		}
	}
}
