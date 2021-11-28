package controller;
import java.awt.event.*;
import Model.*;
import View.*;
public class EventMov implements MouseListener{
	int latitude =0, longitude =0;
	int ind_jog =0, ind_exp = 0, lat = 0, lon = 0, dif1, dif2, mov = 0;
	Regras regras;
	Frame f;
	static boolean explorador = false, movimento = false;
	boolean dados[] = {true,true}, polo = false;
	public EventMov(Regras regras, Frame f) {
		this.regras = regras;
		this.f = f;
	}
	public void mouseClicked(MouseEvent e) {
		int x = e.getX(), y = e.getY();
		int pos[] = {0,0};
		
		if(x > 720 || regras.ganhou) {
			return;
		}
		System.out.println("-----------------------------------------------");
		Coordenadas(x,y);
		ind_jog = regras.indice[regras.ind];
		 pos = regras.ver_posicao(ind_jog, longitude, latitude);
		 
		System.out.println("posicao: " + pos[1]);
		
		/*acao do dado colorido*/
		if(regras.acaocor ) {
			int exp = -1, ind_jog2 = regras.ind_cor;
			if(regras.ind_cor >= regras.qnt) {
				regras.acaocor = false;
			}
			else {
				exp = regras.get_explorador(regras.ind_cor, longitude, latitude);
			}
			
			if(exp != -1) {
				regras.acao_dado_colorido(ind_jog, ind_jog2, exp);
				regras.acaocor = false;
			}
			f.repaint();
			return;
		}
		else if(regras.colorido) {
			return;
		}
		
		/*movimento do jogador com dado*/
		if(regras.get_explorador(ind_jog,longitude,latitude) != -1  && !explorador && !regras.estado) {
			ind_exp = regras.get_explorador(ind_jog,longitude,latitude);
			lat = latitude;
			lon = longitude;
			explorador = true;
			polo = false;
			System.out.println("Explorador escolhido!! indice " + ind_exp);
		}
		else if(explorador) {
			
			
			if(regras.getposicao(ind_jog, ind_exp)[1] == 13 || regras.getposicao(ind_jog, ind_exp)[1] == 0) {
				/*Necessario verificar se o explorador está no polo oposto*/
				regras.set_posicao(ind_jog, ind_exp, regras.getposicao(ind_jog, ind_exp)[1], longitude);
				System.out.println("Posicao depois do polo "+ regras.getposicao(ind_jog, ind_exp)[0] + " " + regras.getposicao(ind_jog, ind_exp)[1] );
				lon = longitude;
				polo = true;
			}
			
			dif1 = lat - latitude;
			dif2 = lon - longitude;
			int cam1 = 20;
			if((lon > longitude)) {
				cam1 = dif2 -12;
			}
			else if(lon < longitude) {
				cam1 = dif2 + 12;
			}
			
			if(Math.abs(cam1) < Math.abs(dif2)) {
				dif2 = cam1;
			}
			
			if(dados[0] && (Math.abs(dif1) == regras.dados[0] || Math.abs(dif2) == regras.dados[0])) {
				if(Math.abs(dif1) == regras.dados[0] && (lon == longitude || latitude == 13 || latitude == 0)) {
					mov = regras.movimentar(ind_jog, ind_exp, dif1, -2);
				}
				else if( Math.abs(dif2) == regras.dados[0] && lat == latitude) {
					mov = regras.movimentar(ind_jog, ind_exp, dif2, -1);
				}
				
				if(mov == 1) {
					dados[0] = false;
					mov = 0;
					explorador = false;
					
					System.out.println("Executando movimento 1 !!");
				}
				else if(polo) {
					regras.set_posicao(ind_jog, ind_exp, regras.getposicao(ind_jog, ind_exp)[1], 0);
					polo = false;
				}
				
			}
			else if(dados[1] && (Math.abs(dif1) == regras.dados[1] || Math.abs(dif2) == regras.dados[1])) {
				if(Math.abs(dif1) == regras.dados[1] && (lon == longitude || latitude == 13 || latitude == 0)) {
					mov = regras.movimentar(ind_jog, ind_exp, dif1, -2);
				}
				else if( Math.abs(dif2) == regras.dados[1] && lat == latitude) {
					mov = regras.movimentar(ind_jog, ind_exp, dif2, -1);
				}
				if(mov == 1) {
					explorador = false;
					dados[1] = false;
					mov = 0;
					System.out.println("Executando movimento 2!!");
				}
				else if(polo) {
					regras.set_posicao(ind_jog, ind_exp, regras.getposicao(ind_jog, ind_exp)[1], 0);
					polo = false;
				}
			}
			
			if((lat != 13 && lat != 0) &&  (regras.getposicao(ind_jog, ind_exp)[1] == 13 || regras.getposicao(ind_jog, ind_exp)[1] == 0)) {
				regras.set_posicao(ind_jog, ind_exp, regras.getposicao(ind_jog, ind_exp)[1], -1);
			}
			
			if(regras.ganhou(ind_jog)) {
				System.out.println("Ganhou");
				regras.ganhou = true;
			}
			f.repaint();
			 
		}
		if(!dados[0] && !dados[1]) {
			dados[0] = true;
			dados[1] = true;
			explorador = false;
			System.out.println("Proxima rodada");
			regras.estado = true;
			regras.ind++;
		}
		
		
	}
	public void Coordenadas(int x, int y) {
		int polo1[] = {198,367},polo2[] = {530,367}, raio, latitude =0, longitude =0;
		double reta1, reta2;
		System.out.println(x + "," + y);
		raio = (x-polo2[0])*(x-polo2[0]) + (y-polo2[1])*(y-polo2[1]);
		if(x > 364) {
			reta1 = x*0.58 + 58.86;
			reta2 = x*-0.58 + 673.38;
			if(raio <= 1000) {
				latitude = 13;
			}

			else if(reta1>= y && reta2 >= y || reta1 <= y && reta2 <= y) {
				
				if(y > polo2[1]) {
					longitude += 6;
				}
				if( raio <= 4096) {
					latitude = 12;
				}
				else if( raio <= 7569 ) {
					latitude = 11;
				}
				else if( raio <= 12996 ) {
					latitude = 10;

				}
				else if( raio <= 19881 ) {
					latitude = 9;

				}
				else if( raio <= 27889 ) {
					latitude = 8;
				}
				else if( raio <= 35344 ) {
					latitude = 7;
				}
				
				if( 1.84*x - 607 < y &&  y < polo2[1] || 1.84*x - 607 > y &&  y > polo2[1]) {
					longitude += 1;

				}
				else if( 1.84*x - 607 > y && x < polo2[0] || 1.84*x - 607 < y && x > polo2[0]) {
					longitude += 2;

				}
				else if( -1.84*x + 1347 < y && x < polo2[0] || -1.84*x + 1347 > y && x > polo2[0]) {
					longitude += 3;

				}
				else if( -1.84*x + 1347 < y &&  y < polo2[1] || -1.84*x + 1347 > y &&  y > polo2[1]) {
					longitude += 4;

				}

				

			}
			else if(x*0.58 + 58.86> y && x*-0.58 + 673.38 < y  || x*0.58 + 58.86 < y && x*-0.58 + 673.38 > y ) {
				
				
				if(x < 386 || x > 673) {
					latitude = 7;
				}
				else if(x < 408 || x > 651) {
					latitude = 8;
				}
				else if(x < 430 || x > 628) {
					latitude = 9;
				}
				else if(x < 452 || x > 606) {
					latitude = 10;
				}
				else if(x < 476 || x > 584) {
					latitude = 11;
				}
				else if(x < 497 || x > 512) {
					latitude = 12;
				}
				
				
				if(x < polo2[0] && y < polo2[1]) {
					longitude = 0;
				}
				else if(x > polo2[0] && y < polo2[1]) {
					longitude = 5;
				}
				else if(x > polo2[0] && y > polo2[1]) {
					longitude = 6;
				}
				else if(x < polo2[0] && y > polo2[1]) {
					longitude = 11;
				}
				
			}
		
		}
		else if ( x < 364) {
			reta1 = (x+330)*0.58 + 58.86;
			reta2 = (x+330)*-0.58 + 673.38;
			raio = (x-polo1[0])*(x-polo1[0]) + (y-polo1[1])*(y-polo1[1]);
			
			if(raio <= 1000) {
				latitude = 0;
			}
			else if(reta1>= y && reta2 >= y || reta1 <= y && reta2 <= y) {
				if(y > polo1[1]) {
					longitude += 6;
				}
				
				if( raio <= 4096) {
					latitude = 1;
				}
				else if( raio <= 7569 ) {
					latitude = 2;
				}
				else if( raio <= 12996 ) {
					latitude = 3;

				}
				else if( raio <= 19881 ) {
					latitude = 4;

				}
				else if( raio <= 27889 ) {
					latitude = 5;
				}
				else if( raio <= 35344 ) {
					latitude = 6;
				}
				
				if( 1.84*(x+330) - 607 < y &&  y < polo1[1] || 1.84*(x+330) - 607 > y &&  y > polo1[1]) {
					longitude += 4;

				}
				else if( 1.84*(x+330) - 607 > y && x < polo1[0] || 1.84*(x+330) - 607 < y && x > polo1[0]) {
					longitude += 3;

				}
				else if( -1.84*(x+330) + 1347 < y && x < polo1[0] || -1.84*(x+330) + 1347 > y && x > polo1[0]) {
					longitude += 2;

				}
				else if( -1.84*(x+330) + 1347 < y &&  y < polo1[1] || -1.84*(x+330) + 1347 > y &&  y > polo1[1]) {
					longitude += 1;

				}

				

			}
			else if((x+330)*0.58 + 58.86> y && (x+330)*-0.58 + 673.38 < y  || (x+330)*0.58 + 58.86 < y && (x+330)*-0.58 + 673.38 > y ) {
				
				
				if(x < 56 || x > 343) {
					latitude = 6;
				}
				else if(x < 78 || x > 321) {
					latitude = 5;
				}
				else if(x < 100 || x > 298) {
					latitude = 4;
				}
				else if(x < 122 || x > 276) {
					latitude = 3;
				}
				else if(x < 146 || x > 254) {
					latitude = 2;
				}
				else if(x < 167 || x > 182) {
					latitude = 1;
				}
				
				
				if(x < polo1[0] && y < polo1[1]) {
					longitude = 5;
				}
				else if(x > polo1[0] && y < polo1[1]) {
					longitude = 0;
				}
				else if(x > polo1[0] && y > polo1[1]) {
					longitude = 11;
				}
				else if(x < polo1[0] && y > polo1[1]) {
					longitude = 6;
				}
				
			}
		}
		System.out.println( "latitude " +latitude +" " + "longitude " + longitude);
		this.latitude = latitude;
		this.longitude = longitude;

	}
	 public void mousePressed(MouseEvent e) {}

	 public void mouseReleased(MouseEvent e) {}

	 public void mouseEntered(MouseEvent e) {}

	 public void mouseExited(MouseEvent e) {}

}

