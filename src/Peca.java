
/**
 * Representa uma Pe�a do jogo.
 * Possui uma casa e um tipo associado.
 * 
 * @author Alan Moraes &lt;alan@ci.ufpb.br&gt;
 * @author Leonardo Villeth &lt;lvilleth@cc.ci.ufpb.br&gt;
 */
public class Peca {

    public static final int PEDRA_BRANCA = 0;
    public static final int DAMA_BRANCA = 1;
    public static final int PEDRA_VERMELHA = 2;
    public static final int DAMA_VERMELHA = 3;

    private Casa casa;
    private int tipo;

    public Peca(Casa casa, int tipo) {
        this.casa = casa;
        this.tipo = tipo;
        casa.colocarPeca(this);
    }
    
    /**
     * Movimenta a peca para uma nova casa.
     * @param destino nova casa que ira conter esta peca.
     */
    public void mover(Move movimento) {

        escaladaSocial(movimento.getDestino());
        if (movimento.nivelDeViolencia()) {
            movimento.removerTodasPecas();
        }
        casa.removerPeca();

        movimento.getDestino().colocarPeca(this);
        casa = movimento.getDestino();
    }

    /**
     * Checa se uma pedra pode ser promovida para dama, e se sim, promove ela.
     * @param destino
     */
    public void escaladaSocial(Casa destino) {
        if (getTipo() == 0) {
            if (destino.getY() == 7) {
                setTipo(1);
            }
        }
        if (getTipo() == 2) {
            if (destino.getY() == 0) {
                setTipo(3);
            }
        }
    }

    public void setTipo(int novoTipo) {
        tipo = novoTipo;
    }

    /**
     * Valor    Tipo
     *   0   Branca (Pedra)
     *   1   Branca (Dama)
     *   2   Vermelha (Pedra)
     *   3   Vermelha (Dama)
     * @return o tipo da peca.
     */
    public int getTipo() {
        return tipo;
    }

    /**
     * Retorna a cor da peça.
     * @return false, se a cor for branca
     * @return true, se a cor for vermelha
     */
    public boolean getCor() {
        if ((tipo == 0) || (tipo == 1)) return false;
        if ((tipo == 2) || (tipo == 3)) return true;
        return false;
    }

    public boolean podeMover(Casa destino) {
        //Se peca branca, ver se deltaX = 1 e deltaY = 1
        //Se peca vermelha, ver se deltaX = -1 e deltaY = -1
        //Se dama, ver se modulo de deltaX = modulo de deltaY
        int deltaY = destino.getY() - casa.getY();
        int deltaX = destino.getX() - casa.getX();

        // verificar se e dama
        if (tipo == 1 || tipo == 3) {
            if (Math.abs(deltaY) == Math.abs(deltaX)) {
                return true;
            }
            else {
                return false;
            }
        }
        else {
            //cor da pedra
            if (tipo == 0) {
                //pedra branca
                if (deltaY == 1 && Math.abs(deltaX) == 1) {
                    return true;
                }
                else {
                    return false;
                }
            }
            else {
                //pedra vermelha
                if (deltaY == -1 && Math.abs(deltaX) == 1) {
                    return true;
                }
                else {
                    return false;
                }
            }
        

        }
    }
}
