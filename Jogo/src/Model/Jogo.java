package Model;
import java.util.Random;
class Jogo{
    Random random = new Random();
    int dado[] = new int[2], pos_metas[][]= {{6,4},{5,3},{4,1},{7,1},{8,2},{9,4},{7,7},{8,8},{9,10},{6,10},{5,9},{4,7}}, modo = 0, carta = 0, polo[]= new int[2], polo_oposto[] = new int[2], cartas_compradas[] = new int[12], ind_cartas = 0;
    Jogador jog[];
    char cor[] = {'A','V','R','L'}, time1[] = {cor[0],cor[1]},time2[] = {cor[2],cor[3]};
    {
        polo[0] = 0;
        polo[1] = 0;
        polo_oposto[0] = 13;
		polo_oposto[1] = 0;

    }
     
    public Jogo(int modo, int qnt){
        this.modo = modo;
        jog = new Jogador[qnt];
        
        for(int x =0; x<qnt;x++) {
        	if(x >= qnt/2) {
        		polo[0] = 13;
                polo[1] = 0;
                polo_oposto[0] = 0;
        		polo_oposto[1] = 0;
        	}
            jog[x] = new Jogador(polo,polo_oposto,cor[x]);
        }
    }
    public int[] jogardado(){
        dado[0] = random.nextInt(6)+1;
        dado[1] = random.nextInt(6)+1;
        return dado;
    }
    /*-----------------------------------------//---------------------------------------//------------------------------------------------*/
    public char dado_colorido(){
    	int colorido = random.nextInt(6)+1;
        if(dado[0] == dado[1]){
            switch(colorido) {
            case 1:
            	return 'A';
            case 2:
            	return 'V';
            case 3:
            	return 'P';
            case 4:
            	return 'R';
            case 5:
            	return 'L';
            case 6:
            	return 'P';
            }
        }
        return 0;
    }
    public int compara_cor( char cor) {/*retorna o indice do jogador que tem a cor*/
    	int indice = 0;
    	for(Jogador j: jog) {
    		if(j.getcor() == cor) {
    				return indice;
    		}
    		indice++;
    	}
    	
    	return -1;
    }
    public void acao_dado_colorido(int ind_jog, int indice, int ind_exp) {
    	if(indice == -1) {
    		return;
    	}
    	if(ind_jog == indice) {/*caso o jogador seja o mesmo do dado colorido*/
    		jog[ind_jog].vai_polo_oposto(ind_exp);
    	}
    	else {
    		if(modo == 1) {/*caso seja em duplas*/
        		if(ver_time(time1,jog[ind_jog].getcor(),jog[indice].getcor()) || ver_time(time2,jog[ind_jog].getcor(),jog[indice].getcor())) {
        			jog[indice].vai_polo_oposto(ind_exp);
    			}
        		else {
        			jog[indice].volta_polo(ind_exp);
        		}
    		}
    		else {
    			System.out.println("Voltou ao polo!!!");
        		jog[indice].volta_polo(ind_exp);
    		}
    	}
    }
    /*-----------------------------------------//---------------------------------------//------------------------------------------------*/
    int ver_exp(int ind_jog, int i, int j) {/*verifica o indice do explorador do jogador que esta na posicao i,j*/
    	for(int x =0; x<6;x++) {
    		if(jog[ind_jog].getposicao(x)[0] == j && jog[ind_jog].getposicao(x)[1] == i) {
    			return x;
    		}
    	}
    	return -1;
    }
    boolean ver_time(char time[], char a, char b) {/*verifica se o a e b estão no mesmo time*/
    	return (a == time[0] || a == time[1]) && (b == time[0] || b == time[1]) ;
    }
	int comprar_carta() {
    	carta = random.nextInt(18)+1;
    	for(int c: cartas_compradas) {
    		while(c == carta) {
    			carta = random.nextInt(18)+1;
    		}
    	}
    	cartas_compradas[ind_cartas] = carta;
    	ind_cartas += 1;
    	return carta;
    }
    boolean capturar(int ind_jog,int i, int j) {
    	boolean meta = false;
    	int pos[] = ver_pos(ind_jog,i,j), ind_jog2 = pos[0],ind_exp;
    	if(jog[ind_jog].polo_oposto[1] == i && jog[ind_jog].polo_oposto[0] == j) {
    		return true;
    	}
    	if(pos[1] == 1) {/*Se a posicao que o explorador vai se mover ja tem 1 explorador do mesmo jogador*/
    		for (int x[]: pos_metas) {
    			if(j == x[0] && i == x[1]) {/*Se tiver uma meta*/
    				meta = true;
    				x[1] = -1;
    				x[0] = -1;
    			}
    			
    		}
    		if(meta) {/*adiciona a meta ao jogador e soma os pontos*/
    			jog[ind_jog].addmeta();
    			jog[ind_jog].somarpontos(1);
				int c = comprar_carta();
    			jog[ind_jog].addcarta(c);
    			System.out.println("Ganhou uma meta!");

    		}
    	}
    	else if(pos[1] == -1) {/*Se a posicao tiver um explorador de outro jogador*/
    		if(modo == 1) {/*Se o jogo for em duplas*/
    			if(ver_time(time1,jog[ind_jog].getcor(),jog[ind_jog2].getcor()) || ver_time(time2,jog[ind_jog].getcor(),jog[ind_jog2].getcor())) {
    				return false;
    			}
    		}
    		if(jog[ind_jog2].getprotegido == ver_exp(ind_jog2, i, j)) {/*Se o explorador inimigo estiver protegido*/
    			return false;
    		}
    		
    		System.out.println("Capturou!");
    		/*Captura o explorador advesário*/
    		ind_exp = ver_exp(ind_jog2,i,j);
    		jog[ind_jog2].volta_polo(ind_exp);
    		
    	}
    	return true;
    }
    public int[] ver_pos(int ind_jog,int i,int j){/*verifica a posicao que o explorador vai se mover*/
    	int qnt =0, ind_jog2 =0,y=0, res[] = {0,0};
        for(Jogador x: jog) {
        	for(int ind =0;ind<6;ind++) {
        		if(x.getposicao(ind)[0] == j && x.getposicao(ind)[1] == i ) {/*Se a posicao tiver exploradores*/
        			if(x == jog[ind_jog]) {/*Se o explorador for do mesmo jogador*/
            			qnt++;
            		}
        			else {/*Se o explorador for de um jogador diferente*/
        				qnt--;
        			}
        			ind_jog2 = y;
        		}
        		
        	}
        	y++;
        }
        res[0] = ind_jog2 ;
        res[1] = qnt;
        return res; /*retorna o indice do jogador e a quantidade de exploradores na posicao*/
    }
    public int movimentar(int ind_jog,int ind_exp,int x, int sent){
        int i,j,dis[] = {0,0};
        if(jog[ind_jog].status_exp(ind_exp)){
    		return -1;
    	}
        
        i = jog[ind_jog].getposicao(ind_exp)[1];
        j = jog[ind_jog].getposicao(ind_exp)[0];
        dis[0] = j;
        dis[1] = i;
        if(sent ==1){
        	dis[1] += x;
        }
        else if(sent == -1){
        	dis[1] -= x;
        }
        else if(sent == 2){
        	dis[0] += x;
        }
        else{
        	dis[0] -= x;
        }
        dis[1] = dis[1]%12;
        if(dis[1] < 0) {
        	dis[1] += 12;
        }
        /*Se a posicao que o explorador for movimentar tiver no maximo 1 explorador do outro jogador (ou time se for modo 1) ou for o polo oposto*/
        if(((ver_pos(ind_jog,dis[1],dis[0])[1] > -2    && capturar(ind_jog,dis[1],dis[0])) ||  jog[ind_jog].polo_oposto[0] == dis[0]) && jog[ind_jog].polo[0] != dis[0]){
        	
        	jog[ind_jog].setposicao(ind_exp, dis[1], dis[0]);
        	return 1;
        }        
        
        return 0;
    }
    /*-----------------------------------------//---------------------------------------//------------------------------------------------*/
    public boolean acaoC1(int ind_jog, int ind_exp, int sent) {
    	if(this.movimentar(ind_jog, ind_exp, 6, sent) != 1) {
    		return false;
    	}
    	return true;
    }
    public boolean acaoC2(int ind_jog, int ind_exp, int ind_exp2, int sent, int sent2) {
    	if(this.movimentar(ind_jog, ind_exp, 3, sent) != 1 || this.movimentar(ind_jog, ind_exp2, 3, sent2) != 1) {
    		return false;
    	}
    	return true;
    }
    public boolean acaoC3(int ind_jog, int ind_exp, int sent) {
    	if(sent == -1 || sent == -2) {
    		return false;
    	}
    	if(this.movimentar(ind_jog, ind_exp, 3, sent) != 1) {
    		return false;
    	}
    	return true;
    }
    public boolean acaoC4(int ind_jog, int[] exp, int[] sent) {
    	int a = 0;
    	for(int i: exp) {
    		if(this.movimentar(ind_jog, i, 3, sent[a]) != 1) {
    			return false;
    		}
    		a++;
    	}
    	return true
    }
    public int[] acaoC5() {
    	return this.jogardado();
    }
    public void acaoC6(int ind_jog, int ind_exp, int ind_exp2) {
    	jog[ind_jog].vai_polo_oposto(ind_exp);
    	jog[ind_jog].vai_polo_oposto(ind_exp2);
    }
    public int acaoC7() {
    	int acao = random.nextInt(6)+1;
    	return acao;
    }
    public void acaoC8(int ind_jog, int ind_exp) {
    	jog[ind_jog].vai_polo_oposto(ind_exp);
    }
    public boolean acaoC9(int ind_jog, int[] exp, int[] sent) {
    	int a = 0;
    	for(int i: exp) {
    		if(this.movimentar(ind_jog, i, 2, sent[a]) != 1) {
    			return false;
    		}
    		a++;
    	}
    	return true
    }
    public void acaoC10(int ind_jog, int ind_exp) {
    	jog[ind_jog].addprotegido(ind_exp);
    }
    public boolean acaoC11(int ind_jog, int ind_exp, int ind_exp2, int ind_exp3, int sent, int sent2, int sent3) {
    	if(this.movimentar(ind_jog, ind_exp, 3, sent) != 1 || this.movimentar(ind_jog, ind_exp2, 3, sent2) != 1 || this.movimentar(ind_jog, ind_exp3, 3, sent3)) {
    		return false;
    	}
    	return true;
    }
    public boolean acaoC12(int ind_jog, int ind_exp, int ind_exp2, int x, int sent, int sent2) {
    	if(this.movimentar(ind_jog, ind_exp, x, sent) != 1 || this.movimentar(ind_jog, ind_exp2, x, sent2) != 1) {
    		return false;
    	}
    	return true;
    }
    public void acaoC13(int ind_jog, int ind_exp) {
    	jog[int_jog].volta_polo(ind_exp);
    }
    public void acaoC14(int ind_jog) {
    	jog[ind_jog].bloqueia(1);
    }
    public void acaoC15(int ind_jog) {
    	int i = 0;
    	if(modo == 1 ) { /*Se for em dupla*/
    		for(Jogador j: jog) {
    			if(i != ind_jog) {
    				for(int x = 0; x < 6; x++) {
    					if(!j.status_exp(x)) {
    						j.volta_polo(x);
    					}
    				}
    			}
    			i++;
    		}
    	}
    	else {
    		for(Jogador j: jog) {
    			if(i != ind_jog && !(ver_time(time1,jog[ind_jog].getcor(),jog[i].getcor()) || ver_time(time2,jog[ind_jog].getcor(),jog[i].getcor()))) {
    				for(int x = 0; x < 6; x++) {
    					if(!j.status_exp(x)) {
    						j.volta_polo(x);
    					}
    				}
    			}
    			i++;
    		}
    	}
    }
    public void acaoC16(int ind_jog) {
    	int i = 0;
    	if(modo == 1) { /*Se for em dupla*/
    		for(Jogador j: jog) {
    			if(i != ind_jog && !(ver_time(time1,jog[ind_jog].getcor(),jog[i].getcor()) || ver_time(time2,jog[ind_jog].getcor(),jog[i].getcor()))) {
    				for(int x = 0; x < 6; x++) {
    					for (int a[]: pos_metas) {
    	    				if(j.getposicao(x)[0] == a[0] && j.getposicao(x)[1] == a[1]) {
    	    					j.volta_polo(x);
    	    				}
    	    			}
    				}
    			}
    			i++;
    		}
    	}
    	else {
    		for(Jogador j: jog) {
    			if(i != ind_jog) {
    				for(int x = 0; x < 6; x++) {
    					for (int a[]: pos_metas) {
    	    				if(j.getposicao(x)[0] == a[0] && j.getposicao(x)[1] == a[1]) {
    	    					j.volta_polo(x);
    	    				}
    	    			}
    				}
    			}
    			i++;
    		}
    	}
    }
    public void acaoC17(int ind_jog) {
    	jog[ind_jog].bloqueia(2);
    }
    /*-----------------------------------------//---------------------------------------//------------------------------------------------*/
    int[] getposicao(int ind_jog, int ind_exp) {
    	int res[] = {jog[ind_jog].getposicao(ind_exp)[1],jog[ind_jog].getposicao(ind_exp)[0]};
    	return res;
    }
    void set_posicao(int ind_jog, int ind_exp, int i, int j) {
    	jog[ind_jog].setposicao(ind_exp, j, i);
    }
    public boolean ganhou(int ind){
    	int res =0;
        for(int x =0; x<6;x++) {
        	if(jog[ind].status_exp(x)) {
        		res++;
        	}
        }
        return res == 6;
    }
    public char ver_ganhador() {
    	int pontos = 0, pontos2 = 0;
    	char jog_cor = 0;
    	if(modo == 0) {
    		for(Jogador j: jog) {
    			if(j.getpontos()> pontos ) {
    				jog_cor = j.getcor();
    				pontos = j.getpontos();
    			}
    		}
    		return jog_cor;
    	}
    	pontos = jog[0].getpontos() + jog[1].getpontos();
    	pontos2 = jog[2].getpontos() + jog[3].getpontos();
    	if(pontos > pontos2) {
    		jog_cor = '1';   /* Time 1 venceu */
    		return jog_cor;
    	}
    	jog_cor = '2';   /* Time 2 venceu */
    	return jog_cor;
    }
}
