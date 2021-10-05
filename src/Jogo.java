import java.util.Vector;

/**
 * Armazena o tabuleiro e responsavel por posicionar as pecas.
 * 
 * @author Alan Moraes &lt;alan@ci.ufpb.br&gt;
 * @author Leonardo Villeth &lt;lvilleth@cc.ci.ufpb.br&gt;
 */
public class Jogo {

    private Tabuleiro tabuleiro;

    public Jogo() {
        tabuleiro = new Tabuleiro();
        criarPecas();
    }
    
    /**
     * Posiciona pe�as no tabuleiro.
     * Utilizado na inicializa�ao do jogo.
     */
    private void criarPecas() {
        for (int r = 0; r <= 2; r++) { // posiciona LINHA
            int c = 0; // cria variável COLUNA
            if ((r % 2) != 0) c++; // aumenta COLUNA por 1 se LINHA for IMPAR (ou seja, começa com casa branca)
            for (; c <= 7; c += 2) { //posiciona COLUNA
                Casa casaN = tabuleiro.getCasa(c, r);
                Peca pecaN = new Peca(casaN, Peca.PEDRA_BRANCA);
            }
        }

        for (int r = 7; r >= 5; r--) {
            int c = 0; 
            if ((r % 2) != 0) c++; 
            for (; c <= 7; c += 2) { 
                Casa casaN = tabuleiro.getCasa(c, r);
                Peca pecaN = new Peca(casaN, Peca.PEDRA_VERMELHA);
            }
        }

        /*
        Casa casa1 = tabuleiro.getCasa(0, 0);
        Peca peca1 = new Peca(casa1, Peca.PEDRA_BRANCA);

        Casa casa2 = tabuleiro.getCasa(7, 7);
        Peca peca2 = new Peca(casa2, Peca.DAMA_VERMELHA);
        */
    }
    
    /**
     * Comanda uma Pe�a na posicao (origemX, origemY) fazer um movimento 
     * para (destinoX, destinoY).
     * 
     * @param origemX linha da Casa de origem.
     * @param origemY coluna da Casa de origem.
     * @param destinoX linha da Casa de destino.
     * @param destinoY coluna da Casa de destino.
     */
    public void moverPeca(int origemX, int origemY, int destinoX, int destinoY) {
        Casa origem = tabuleiro.getCasa(origemX, origemY);
        Casa destino = tabuleiro.getCasa(destinoX, destinoY);
        Peca peca = origem.getPeca();
        peca.mover(destino);
    }

    /* checar se movimento é legal ou não */
    public int movementCheck(int tipo, int color, int origemX, int origemY, int destinoX, int destinoY) {
        if (!((destinoX == origemX) || (destinoY == origemY))) {
            if (tipo == 1) {
                return 1;
            }
            else {
                if (color == 0) {
                    if (destinoY == origemY + 1) {
                        return 1;
                    }
                    else return 0;
                }
                else if (color == 1) {
                    if (destinoY == origemY - 1) {
                        return 1;
                    }
                    else return 0;
                }
                else return -1;
            }
        }
        return -1;
    }

    public Casa[] movementList(int tipo, int color, int origemX, int origemY) {
        Vector moves = new Vector();
        for (int destinoX = 0; destinoX <= 7; destinoX++) {
            for (int destinoY = 0; destinoY <= 7; destinoY++) {
                if (movementCheck(tipo, color, origemX, origemY, destinoX, destinoY) == 1) {
                    moves.addElement(tabuleiro.getCasa(destinoX, destinoY));
                }
            }
        }
    
        if (moves.size() == 0) return null;
        else {
            Casa[] moveListArray = new Casa[moves.size()];
            for (int i = 0; i < moves.size(); i++) {
                moveListArray[i] = (Casa) moves.elementAt(i);
            }
            return moveListArray;
        }
    }

    /**
     * @return o Tabuleiro em jogo.
     */
    public Tabuleiro getTabuleiro() {
        return tabuleiro;
    }
}
