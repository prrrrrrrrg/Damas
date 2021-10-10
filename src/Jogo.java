
/**
 * Armazena o tabuleiro e responsavel por posicionar as pecas.
 * 
 * @author Alan Moraes &lt;alan@ci.ufpb.br&gt;
 * @author Leonardo Villeth &lt;lvilleth@cc.ci.ufpb.br&gt;
 */
public class Jogo {

    private Tabuleiro tabuleiro;
    public static boolean vezBranca;

    public Jogo() {
        tabuleiro = new Tabuleiro();
        vezBranca = true;
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
                Peca pecaN = new Peca(casaN, 0);
            }
        }

        for (int r = 7; r >= 5; r--) {
            int c = 0; 
            if ((r % 2) != 0) c++; 
            for (; c <= 7; c += 2) { 
                Casa casaN = tabuleiro.getCasa(c, r);
                Peca pecaN = new Peca(casaN, 2);
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
    public static boolean bloodthirst = false;
    public static Casa novaOrigem;
    // quando esta variável é verdadeira, peças não podem fazer movimentos que não resultem em captura
    public void moverPeca(int origemX, int origemY, int destinoX, int destinoY) {
        Casa origem = tabuleiro.getCasa(origemX, origemY);
        Casa destino = tabuleiro.getCasa(destinoX, destinoY);
        Peca peca = origem.getPeca();
        Move movimento = new Move(origem, destino, tabuleiro);
    
        if (movimento.podeMover()) {
            System.out.println("PIECE CAN MOVE");
            if (bloodthirst == true) {
                System.out.println("THE PIECE IS OUT FOR BLOOD");
                if (movimento.nivelDeViolencia()) {
                    if (movimento.getOrigem() == novaOrigem) {
                        peca.mover(movimento);
                    }
                }
            }
            else {
                System.out.println("PIECE HAS MOVED");
                peca.mover(movimento);
            }

            if (movimento.nivelDeViolencia()) {
                System.out.println("KILL!");
                if (movimento.podeMoverDeNovo()) {
                    System.out.println("IT CAN KILL AGAIN");
                    bloodthirst = true;
                    novaOrigem = movimento.getDestino();
                    System.out.println("BLOODTHIRST ON");
                }
                else {
                    bloodthirst = false;
                    System.out.println("BLOODTHIRST OFF");
                    vezBranca = !vezBranca;
                }
            }
            else if (bloodthirst == false) {
                    vezBranca = !vezBranca;
            }
        }
    }
    /**
     * @return o Tabuleiro em jogo.
     */
    public Tabuleiro getTabuleiro() {
        return tabuleiro;
    }
}
