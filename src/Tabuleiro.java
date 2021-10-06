
/**
 * O Tabuleiro do jogo. 
 * Respons�vel por armazenar as 64 casas.
 * 
 * @author Alan Moraes &lt;alan@ci.ufpb.br&gt;
 * @author Leonardo Villeth &lt;lvilleth@cc.ci.ufpb.br&gt;
 */
public class Tabuleiro {

    private Casa[][] casas;

    public Tabuleiro() {
        casas = new Casa[8][8];
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                Casa casa = new Casa(x, y);
                casas[x][y] = casa;
            }
        }
    }
    /**
     * @param x linha
     * @param y coluna
     * @return Casa na posicao (x,y)
     */
    public Casa getCasa(int x, int y) {
        return casas[x][y];
    }

    /**
     * @return Array[1] com a quantidade de peças no tabuleiro.
     */
    public int[] getBoardState() {
        int[] pecaContador = new int[1];

        for (int i = 0; i <= 7; i++) {
            for (int j = 0; i <= 7; i++) {
                Casa casaChecker = getCasa(i, j);
                Peca pecaChecker = casaChecker.getPeca();
                if ((pecaChecker.getTipo() == 0) || (pecaChecker.getTipo() == 1)) {
                    pecaContador[0]++;
                }
                if ((pecaChecker.getTipo() == 2) || (pecaChecker.getTipo() == 3)) {
                    pecaContador[1]++;
                }
            }
        }

        return pecaContador;
    }
}
