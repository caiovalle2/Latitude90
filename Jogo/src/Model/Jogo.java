package Model;
import java.util.Random;
class Jogo{
    Random random = new Random();
    int dado[] = new int[2], pos_metas[][]= {{6,4},{5,3},{4,1},{7,1},{8,2},{9,4},{7,7},{8,8},{9,10},{6,10},{5,9},{4,7}}, modo = 0, carta = 0, polo[]= new int[2], polo_oposto[] = new int[2], cartas_compradas[] = new int[12], ind_cartas = 0;
    int qnt;
    Jogador jog[];
    char cor[] = {'G','Y','W','B'}, time1[] = {cor[0],cor[1]},time2[] = {cor[2],cor[3]};
    {
        polo[0] = 0;
        polo[1] = 0;
        polo_oposto[0] = 13;
		polo_oposto[1] = 0;

    }
     
    public Jogo(int modo, int qnt){
        this.modo = modo;
        this.qnt = qnt;
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
    public int dado_colorido(){
    	int colorido = random.nextInt(6);
        return colorido;
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
    	if(ind_jog == indice || ind_jog >= qnt) {/*caso o jogador seja o mesmo do dado colorido*/
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
    public int comprar_carta() {
    	boolean fim = false;
    	int contagem = 0;	
    	while(!fim) {
			carta = random.nextInt(18)+1;
			for(int c: cartas_compradas) {
				if(carta == c) {
					contagem = 0;
					break;
				}
				contagem++;
			}
			if(contagem == ind_cartas) {
				fim = true;
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
    public void conquista_polo(int ind_jog, int ind_exp) {
    	jog[ind_jog].vai_polo_oposto(ind_exp);
    }
	
    int[] getposicao(int ind_jog, int ind_exp) {
    	int res[] = {jog[ind_jog].getposicao(ind_exp)[1],jog[ind_jog].getposicao(ind_exp)[0]};
    	return res;
    }
    void set_posicao(int ind_jog, int ind_exp, int i, int j) {
    	jog[ind_jog].setposicao(ind_exp, j, i);
    }
    
    int get_pontos(int ind_jog) {
    	return jog[ind_jog].pontos;
    }
    void set_pontos(int ind_jog, int pontos) {
    	jog[ind_jog].pontos = pontos;
    }
    int metas(int ind_jog) {
    	return jog[ind_jog].metas;
    }
    void set_metas(int ind_jog, int metas) {
    	jog[ind_jog].metas = metas;
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
    	if(modo == 0 || modo == 1) {
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
