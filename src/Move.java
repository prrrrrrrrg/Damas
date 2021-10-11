/**
 * Representa um movimento no Tabuleiro.
 * Possui duas Casas (origem e destino), e informação sobre o movimento.
 * 
 * @author Lucas Alves
 * @author Luiz Biel
 */
public class Move {

    private Casa origem;
    private Casa destino;
    private boolean podeMatar;
    private Tabuleiro tabuleiro;
    
    public Move(Casa origem, Casa destino, Tabuleiro tabuleiro) {
        this.origem = origem;
        this.destino = destino;
        this.tabuleiro = tabuleiro;
        this.podeMatar = false;
    }

    /**
     * Checa se um movimento é possível ou não
     * @param tabuleiro
     * @return boolean confirmando se movimento é possível ou não
     */
    public boolean podeMover() {
        int deltaX = destino.getX() - origem.getX();
        int deltaY = destino.getY() - origem.getY();

        if (Math.abs(deltaX) == Math.abs(deltaY)) { // se o movimento é diagonal
            if (destino.getPeca() == null) { // se o destino não possui peças
                if (origem.getPeca().getTipo() == 0) { // se a peça for uma pedra branca
                    if (deltaY == 1) { // se a peça estiver movendo um espaço (para frente)
                        return true;
                    }
                    else if (deltaY == 2) { // se a peça estiver movendo dois espaços (para frente)
                        // checar se existe uma peça entre os dois espaços (aka captura)
                        if (deltaX > 0) { 
                            if (tabuleiro.getCasa(destino.getX() - 1, destino.getY() - 1).getPeca() == null) {
                                return false;
                            }
                            if (tabuleiro.getCasa(destino.getX() - 1, destino.getY() - 1).getPeca().getCor()) {
                                podeMatar = true;
                                return true;
                            }
                        }
                        else if (deltaX < 0) {
                            if (tabuleiro.getCasa(destino.getX() + 1, destino.getY() - 1).getPeca() == null) {
                                return false;
                            }
                            if (tabuleiro.getCasa(destino.getX() + 1, destino.getY() - 1).getPeca().getCor()) {
                                podeMatar = true;
                                return true;
                            }
                        }
                    }
                    else if (deltaY == -2) { // se a peça estiver movendo dois espaços (para trás)
                        // checar se existe uma peça entre os dois espaços (aka captura)
                        if (deltaX > 0) {
                            if (tabuleiro.getCasa(destino.getX() - 1, destino.getY() + 1).getPeca() == null) {
                                return false;
                            }
                            if (tabuleiro.getCasa(destino.getX() - 1, destino.getY() + 1).getPeca().getCor()) {
                                podeMatar = true;
                                return true;
                            }
                        }
                        else if (deltaX < 0) {
                            if (tabuleiro.getCasa(destino.getX() + 1, destino.getY() + 1).getPeca() == null) {
                                return false;
                            }
                            if (tabuleiro.getCasa(destino.getX() + 1, destino.getY() + 1).getPeca().getCor()) {
                                podeMatar = true;
                                return true;
                            }
                        }
                    }
                }
                if (origem.getPeca().getTipo() == 2) { // se a peça for uma pedra vermelha
                    // basicamente a mesma coisa só que ao contrario :)
                    if (deltaY == -1) {
                        return true;
                    }
                    else if (deltaY == 2) {
                        if (deltaX > 0) {
                            if (tabuleiro.getCasa(destino.getX() - 1, destino.getY() - 1).getPeca() == null) {
                                return false;
                            }
                            if (!tabuleiro.getCasa(destino.getX() - 1, destino.getY() - 1).getPeca().getCor()) {
                                podeMatar = true;
                                return true;
                            }
                        }
                        else if (deltaX < 0) {
                            if (tabuleiro.getCasa(destino.getX() + 1, destino.getY() - 1).getPeca() == null) {
                                return false;
                            }
                            if (!tabuleiro.getCasa(destino.getX() + 1, destino.getY() - 1).getPeca().getCor()) {
                                podeMatar = true;
                                return true;
                            }
                        }
                    }
                    else if (deltaY == -2) {
                        if (deltaX > 0) {
                            if (tabuleiro.getCasa(destino.getX() - 1, destino.getY() + 1).getPeca() == null) {
                                return false;
                            }
                            if (!tabuleiro.getCasa(destino.getX() - 1, destino.getY() + 1).getPeca().getCor()) {
                                podeMatar = true;
                                return true;
                            }
                        }
                        else if (deltaX < 0) {
                            if (tabuleiro.getCasa(destino.getX() + 1, destino.getY() + 1).getPeca() == null) {
                                return false;
                            }
                            if (!tabuleiro.getCasa(destino.getX() + 1, destino.getY() + 1).getPeca().getCor()) {
                                podeMatar = true;
                                return true;
                            }
                        }
                    }
                }
                else if ((origem.getPeca().getTipo() == 1) || (origem.getPeca().getTipo() == 3)) { // se a peça for uma dama
                    if ((destino.getX() > origem.getX()) && (destino.getY() > origem.getY())) { // se ela estiver movendo para o topo direito
                        int cursorX = origem.getX();
                        int cursorY = origem.getY();
                        while ((cursorX < destino.getX()) && (cursorY < destino.getY())) { // checar todas as casas entre o destino e a origem por peças
                            cursorX++; cursorY++;
                            if (tabuleiro.getCasa(cursorX, cursorY).getPeca() != null) { // se achar uma peça
                                if ((tabuleiro.getCasa(cursorX - 1, cursorY - 1).getPeca() != null) 
                                && (tabuleiro.getCasa(cursorX - 1, cursorY - 1) != origem)) {
                                    // se essa peça tiver outra peça diretamente atrás dela que não seja a origem
                                    return false;
                                }
                                else if (tabuleiro.getCasa(cursorX + 1, cursorY + 1).getPeca() != null) {
                                    // se essa peça tiver outra peça diretamente afrente dela
                                    return false;
                                }
                                else if (tabuleiro.getCasa(cursorX, cursorY).getPeca().getCor() != origem.getPeca().getCor()) {
                                    // se essa peça não for da mesma cor que a peça se movendo
                                    podeMatar = true;
                                    return true;
                                }
                                else return false;
                            }
                        }
                        return true;
                    }
                    else if ((destino.getX() > origem.getX()) && (destino.getY() < origem.getY())) { // se ela estiver movendo para o fundo direito
                        // basicamente a mesma coisa com valores diferentes
                        int cursorX = origem.getX();
                        int cursorY = origem.getY();
                        while ((cursorX < destino.getX()) && (cursorY > destino.getY())) {
                            cursorX++; cursorY--;
                            if (tabuleiro.getCasa(cursorX, cursorY).getPeca() != null) {
                                if ((tabuleiro.getCasa(cursorX - 1, cursorY + 1).getPeca() != null) 
                                && (tabuleiro.getCasa(cursorX - 1, cursorY + 1) != origem)) {
                                    return false;
                                }
                                else if (tabuleiro.getCasa(cursorX + 1, cursorY - 1).getPeca() != null) {
                                    return false;
                                }
                                else if (tabuleiro.getCasa(cursorX, cursorY).getPeca().getCor() != origem.getPeca().getCor()) {
                                    podeMatar = true;
                                    return true;
                                }
                                else return false;
                            }
                        }
                        return true;
                    }
                    else if ((destino.getX() < origem.getX()) && (destino.getY() > origem.getY())) { // se ela estiver movendo para o topo esquerdo
                        // msm coisa 
                        int cursorX = origem.getX();
                        int cursorY = origem.getY();
                        while ((cursorX > destino.getX()) && (cursorY < destino.getY())) {
                            cursorX--; cursorY++;
                            if (tabuleiro.getCasa(cursorX, cursorY).getPeca() != null) {
                                if ((tabuleiro.getCasa(cursorX + 1, cursorY - 1).getPeca() != null) 
                                && (tabuleiro.getCasa(cursorX + 1, cursorY - 1) != origem)) {
                                    return false;
                                }
                                else if (tabuleiro.getCasa(cursorX - 1, cursorY + 1).getPeca() != null) {
                                    return false;
                                }
                                else if (tabuleiro.getCasa(cursorX, cursorY).getPeca().getCor() != origem.getPeca().getCor()) {
                                    podeMatar = true;
                                    return true;
                                }
                                else return false;
                            }
                        }
                        return true;
                    }
                    else if ((destino.getX() < origem.getX()) && (destino.getY() < origem.getY())) { // se ela estiver movendo para o fundo esquerdo
                        // eu escrevi esse codigo as 3 da madrugada por favor seja gentil
                        int cursorX = origem.getX();
                        int cursorY = origem.getY();
                        while ((cursorX > destino.getX()) && (cursorY > destino.getY())) {
                            cursorX--; cursorY--;
                            if (tabuleiro.getCasa(cursorX, cursorY).getPeca() != null) {
                                if ((tabuleiro.getCasa(cursorX + 1, cursorY + 1).getPeca() != null) 
                                && (tabuleiro.getCasa(cursorX + 1, cursorY + 1) != origem)) {
                                    return false;
                                }
                                else if (tabuleiro.getCasa(cursorX - 1, cursorY - 1).getPeca() != null) {
                                    return false;
                                }
                                else if (tabuleiro.getCasa(cursorX, cursorY).getPeca().getCor() != origem.getPeca().getCor()) {
                                    podeMatar = true;
                                    return true;
                                }
                                else return false;
                            }
                        }
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Remove todas as peças entre um movimento.
     */
    public void removerTodasPecas() {
        if ((destino.getX() > origem.getX()) && (destino.getY() > origem.getY())) { // se a peça estiver movendo para o topo direito
            int cursorX = destino.getX();
            int cursorY = destino.getY();
            while ((cursorX > origem.getX()) && (cursorY > origem.getY())) { // checar todas as casas entre origem e destino por peças
                cursorX--; cursorY--;
                Casa cursor = tabuleiro.getCasa(cursorX, cursorY);
                if (cursor.getPeca() != null) { // se houver uma peça
                    if (cursor.getPeca().getCor() != origem.getPeca().getCor()) { // se a peça não for da mesma cor que a peça se movendo
                        // pensando bem, isso provavelmente não deveria acontecer em um jogo legalmente...
                        // mas vai saber né, agora eu to com medo demais pra tirar
                        cursor.removerPeca(); // take it down bby
                    }
                }
            }
        }
        // o resto é a mesma coisa só que pras outras direções
        else if ((destino.getX() < origem.getX()) && (destino.getY() > origem.getY())) {
            int cursorX = destino.getX();
            int cursorY = destino.getY();
            while ((cursorX < origem.getX()) && (cursorY > origem.getY())) {
                cursorX++; cursorY--;
                Casa cursor = tabuleiro.getCasa(cursorX, cursorY);
                if (cursor.getPeca() != null) {
                    if (cursor.getPeca().getCor() != origem.getPeca().getCor()) {
                        cursor.removerPeca();
                    }
                }
            }
        }
        else if ((destino.getX() > origem.getX()) && (destino.getY() < origem.getY())) {
            int cursorX = destino.getX();
            int cursorY = destino.getY();
            while ((cursorX > origem.getX()) && (cursorY < origem.getY())) {
                cursorX--; cursorY++;
                Casa cursor = tabuleiro.getCasa(cursorX, cursorY);
                if (cursor.getPeca() != null) {
                    if (cursor.getPeca().getCor() != origem.getPeca().getCor()) {
                        cursor.removerPeca();
                    }
                }
            }
        }
        else if ((destino.getX() < origem.getX()) && (destino.getY() < origem.getY())) {
            int cursorX = destino.getX();
            int cursorY = destino.getY();
            while ((cursorX < origem.getX()) && (cursorY < origem.getY())) {
                cursorX++; cursorY++;
                Casa cursor = tabuleiro.getCasa(cursorX, cursorY);
                if (cursor.getPeca() != null) {
                    if (cursor.getPeca().getCor() != origem.getPeca().getCor()) {
                        cursor.removerPeca();
                    }
                }
            }
        }
    }

    /**
     * Checa se é possível fazer outra captura imediatamente após o movimento.
     * @return true se for possível
     * @return false se não
     */
    public boolean podeMoverDeNovo() {
        // checa todas casas no tabuleiro
        for (int i = 0; i <= 7; i++) {
            for (int j = 0; j <= 7; j++) {
                Move checker = new Move(destino, tabuleiro.getCasa(i, j), tabuleiro); // cria novo movimento da origem para uma das casas no tabuleiro
                if (checker.podeMover()) { // checa se esse movimento é possivel
                    if (checker.nivelDeViolencia()) { // checa se esse movimento é uma captura
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public Casa getDestino() {
        return destino;
    }

    public Casa getOrigem() {
        return origem;
    }

    /**
     * Checa se uma peça é capaz de captura (ASSASSINATO XD)
     * @return true se ela não tem mais coração
     * @return false se ela ainda tem
     */
    public boolean nivelDeViolencia() {
        return podeMatar;
    }
}
