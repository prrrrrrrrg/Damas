
/**
 * Armazena o tabuleiro e responsavel por posicionar as pecas.
 * 
 * @author Alan Moraes &lt;alan@ci.ufpb.br&gt;
 * @author Leonardo Villeth &lt;lvilleth@cc.ci.ufpb.br&gt;
 */
public class Jogo {

    private Tabuleiro tabuleiro;

    /**
     * quando esta variável é verdadeira, peças brancas podem mover
     * quando é falsa, peças vermelhas podem mover
     */
    private boolean vezBranca;

    /**
     * quando esta variável é verdadeira, peças não podem fazer movimentos que não resultem em captura
     */
    private boolean bloodthirst;

    public Jogo() {
        tabuleiro = new Tabuleiro();
        vezBranca = true;
        bloodthirst = false;
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
                new Peca(casaN, 0);
            }
        }

        for (int r = 7; r >= 5; r--) {
            int c = 0; 
            if ((r % 2) != 0) c++; 
            for (; c <= 7; c += 2) { 
                Casa casaN = tabuleiro.getCasa(c, r);
                new Peca(casaN, 2);
            }
        }
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
    private Casa novaOrigem;
    public void moverPeca(int origemX, int origemY, int destinoX, int destinoY) {
        Casa origem = tabuleiro.getCasa(origemX, origemY);
        Casa destino = tabuleiro.getCasa(destinoX, destinoY);
        Peca peca = origem.getPeca();
        Move movimento = new Move(origem, destino, tabuleiro);

        // se a origem é igual ao destino, desativar bloodthirst e passar a vez
        if (origem == destino) {
            bloodthirst = false;
            vezBranca = !vezBranca;
        }
    
        if (movimento.podeMover()) { // se movimento é possível
            if (bloodthirst == true) {
                // se bloodthirst estiver ativo, só mover se a mesma peça do movimento anterior
                // esta fazendo uma nova captura
                if (movimento.nivelDeViolencia()) {
                    if (origem == novaOrigem) {
                        peca.mover(movimento);
                    }
                }
            }
            else {
                peca.mover(movimento);
            }

            if (movimento.nivelDeViolencia()) {
                // se o movimento for uma captura, e for possivel fazer outra captura, ativar bloodthirst
                if (movimento.podeMoverDeNovo()) {
                    bloodthirst = true;
                    novaOrigem = destino;
                }
                else {
                    bloodthirst = false;
                    vezBranca = !vezBranca;
                }
            }
            else if (bloodthirst == false) {
                    vezBranca = !vezBranca;
            }
        }
    }

    /**
     * Variável que checa se vitória foi obtida ou não, 
     * checando se um dos jogadores tem movimentos possíveis.
     * @return 0 se nenhum jogador tiver vencido
     * @return 1 se o time vermelho tiver vencido
     * @return 2 se o time branco tiver vencido
     */
    public int checarVitoria() {
        int timeBranco = 0;
        int timeVermelho = 0;

        for (int i = 0; i <= 7; i++) {
            for (int j = 0; j <= 7; j++) {
                for (int k = 0; k <= 7; k++) {
                    for (int l = 0; l <= 7; l++) {
                        Casa origem = tabuleiro.getCasa(i, j);
                        Casa destino = tabuleiro.getCasa(k, l);
                        Move movimento = new Move(origem, destino, tabuleiro);

                        if (origem.getPeca() != null) {
                            if (movimento.podeMover()) {
                                if (origem.getPeca().getCor()) {
                                    timeVermelho++;
                                }
                                else {
                                    timeBranco++;
                                }
                            }
                        }
                    }
                }
            }
        }

        if (timeBranco == 0) {
            return 1;
        }
        else if (timeVermelho == 0) {
            return 2;
        }
        else return 0;


    }

    /**
     * @return o Tabuleiro em jogo.
     */
    public Tabuleiro getTabuleiro() {
        return tabuleiro;
    }

    public boolean getVez() {
        return vezBranca;
    }

    public boolean getBloodthirst() {
        return bloodthirst;
    }
}
