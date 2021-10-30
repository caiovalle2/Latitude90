package Iniciar;

import View.*;
import Model.*;
public class InicarJogo {

	public static void main(String[] args) {
		Regras regras = new Regras(0,2);
		Dados a = new Dados();
		
		 Frame tab = new Frame(regras);
			// TODO Auto-generated method stub
			 tab.setVisible(true);
	}

}
