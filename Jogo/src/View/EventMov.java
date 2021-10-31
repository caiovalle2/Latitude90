package View;

import java.awt.event.*;

public class EventMov implements MouseListener{
	int latitude =0, longitude =0;
	public void mouseClicked(MouseEvent e) {
		int x = e.getX(), y = e.getY();
		
		Coordenadas(x,y);
		
	}
	public void Coordenadas(int x, int y) {
		int polo1[] = {198,367},polo2[] = {530,367}, raio, latitude =0, longitude =0;
		double reta1, reta2;
		System.out.println(x + " " + y);
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

	}
	 public void mousePressed(MouseEvent e) {}

	 public void mouseReleased(MouseEvent e) {}

	 public void mouseEntered(MouseEvent e) {}

	 public void mouseExited(MouseEvent e) {}

}
