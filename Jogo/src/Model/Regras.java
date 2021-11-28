package Model;

public class Regras {
	public int modo, qnt, dados[] = {1,1},ind_cor = 5, ind =0, indice[] = {0,1,2,3}, i = 0 ;
	public boolean ord_ind = false, iniciar = false,estado = true,colorido = false,acaocor = false, ganhou = false;
	Jogo novo;
	public Regras(int modo, int qnt) {
		this.modo = modo;
		this.qnt = qnt;
		novo = new Jogo(modo,qnt);		
	}
	
	
	public int[] jogardado() {/*joga os dados e retorna {dado0,dado1}*/
		dados = novo.jogardado();
		return dados;
	}
	public void set_dados(int a, int b) {
		dados[0] = a;
		dados[1] = b;
	}
	public int dadocolorido() {/*joga o dado colorido e retorna a cor*/
		 ind_cor = novo.dado_colorido();
		
		return ind_cor;
	}
	public void acao_dado_colorido(int ind_jog, int cor, int ind_exp) {/*faz a acao do dado colorido*/
		novo.acao_dado_colorido(ind_jog, cor, ind_exp);
	}
	public int rodada() {
		ind = ind%qnt;
		return indice[ind];
	}
	public int[] getposicao(int ind_jog,int ind_exp) {/*retorna a posicao do explorador*/
		return novo.getposicao(ind_jog, ind_exp);
	}
	public int[] ver_posicao(int ind_jog, int i, int j) {
		
		return novo.ver_pos(ind_jog, i, j);
	}
	public int get_explorador(int ind_jog, int i,int j) {
		return novo.ver_exp(ind_jog, i, j);
	}
	public void set_posicao(int ind_jog, int ind_exp, int i, int j) {
		novo.set_posicao(ind_jog, ind_exp, i, j);
	}
	public int get_pontos(int ind_jog) {
		return novo.get_pontos(ind_jog);
	}
	public void set_pontos(int ind_jog, int pontos) {
		novo.set_pontos(ind_jog, pontos);
	}
	public int metas(int ind_jog) {
		return novo.metas(ind_jog);
	}
	public void set_metas(int ind_jog, int metas) {
		novo.set_metas(ind_jog, metas);
	}
	public int movimentar(int ind_jog, int ind_exp, int dado, int sentido) {/*movimenta se possível o explorador no sentido escolhido*/
		return novo.movimentar(ind_jog, ind_exp, dado, sentido);
	}
	public void conquista_polo(int ind_jog, int exp) {/*o explorador conquista o polo oposto*/
		novo.conquista_polo(ind_jog, exp);
	}
	public boolean ganhou(int ind_jog) {/*verifica se todos os 6 exploradores do jogador estão no polo oposto*/
		
		return novo.ganhou(ind_jog);
	}
	public char ver_ganhador() {/*Faz a contagem dos pontos e retorna quem tiver maior pontuacao*/
		return novo.ver_ganhador();
	}
}
