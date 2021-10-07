/**
 * Representa um movimento no Tabuleiro.
 * Possui duas Casas (origem, destino), e guarda informações sobre peças capturadas.
 * 
 * @author Lucas Alves
 * @author Luiz Biel
 */
public class Move {

    private Casa origem;
    private Casa destino;
    private int podeMatar;
    private Casa vitima;
    
    public Move(Casa origem, Casa destino) {
        this.origem = origem;
        this.destino = destino;
        this.podeMatar = 0;
        this.vitima = null;
    }

    /**
     * Checa se um movimento é possível ou não
     * @param tabuleiro
     * @return boolean confirmando se movimento é possível ou não
     */
    public boolean podeMover(Tabuleiro tabuleiro) {
        int deltaX = destino.getX() - origem.getX();
        int deltaY = destino.getY() - origem.getY();

        if (Math.abs(deltaX) == Math.abs(deltaY)) {
            if (destino.getPeca() == null) {
                if (origem.getPeca().getTipo() == 0) {
                    if (deltaY == 1) {
                        if (destino.getY() > origem.getY()) {
                            return true;
                        }
                    }
                    else if (deltaY == 2) {
                        if (deltaX > 0) {
                            if (tabuleiro.getCasa(destino.getX() - 1, destino.getY() - 1).getPeca().getCor()) {
                                podeMatar = 1;
                                vitima = tabuleiro.getCasa(destino.getX() - 1, destino.getY() - 1);
                                return true;
                            }
                        }
                        else if (deltaX < 0) {
                            if (tabuleiro.getCasa(destino.getX() + 1, destino.getY() - 1).getPeca().getCor()) {
                                podeMatar = 1;
                                vitima = tabuleiro.getCasa(destino.getX() + 1, destino.getY() - 1);
                                return true;
                            }
                        }
                    }
                    else if (deltaY == -2) {
                        if (deltaX > 0) {
                            if (tabuleiro.getCasa(destino.getX() - 1, destino.getY() + 1).getPeca().getCor()) {
                                podeMatar = 1;
                                vitima = tabuleiro.getCasa(destino.getX() - 1, destino.getY() + 1);
                                return true;
                            }
                        }
                        else if (deltaX < 0) {
                            if (tabuleiro.getCasa(destino.getX() + 1, destino.getY() + 1).getPeca().getCor()) {
                                podeMatar = 1;
                                vitima = tabuleiro.getCasa(destino.getX() + 1, destino.getY() + 1);
                                return true;
                            }
                        }
                    }
                }
                if (origem.getPeca().getTipo() == 2) {
                    if (deltaY == -1) {
                        if (destino.getY() < origem.getY()) {
                            return true;
                        }
                    }
                    else if (deltaY == 2) {
                        if (deltaX > 0) {
                            if (!tabuleiro.getCasa(destino.getX() - 1, destino.getY() - 1).getPeca().getCor()) {
                                podeMatar = 1;
                                vitima = tabuleiro.getCasa(destino.getX() - 1, destino.getY() - 1);
                                return true;
                            }
                        }
                        else if (deltaX < 0) {
                            if (!tabuleiro.getCasa(destino.getX() + 1, destino.getY() - 1).getPeca().getCor()) {
                                podeMatar = 1;
                                vitima = tabuleiro.getCasa(destino.getX() + 1, destino.getY() - 1);
                                return true;
                            }
                        }
                    }
                    else if (deltaY == -2) {
                        if (deltaX > 0) {
                            if (!tabuleiro.getCasa(destino.getX() - 1, destino.getY() + 1).getPeca().getCor()) {
                                podeMatar = 1;
                                vitima = tabuleiro.getCasa(destino.getX() - 1, destino.getY() + 1);
                                return true;
                            }
                        }
                        else if (deltaX < 0) {
                            if (!tabuleiro.getCasa(destino.getX() + 1, destino.getY() + 1).getPeca().getCor()) {
                                podeMatar = 1;
                                vitima = tabuleiro.getCasa(destino.getX() + 1, destino.getY() + 1);
                                return true;
                            }
                        }
                    }
                }
                else if ((origem.getPeca().getTipo() == 1) || (origem.getPeca().getTipo() == 3)) {
                    System.out.println("PASS 1 SUCCEEDED");
                    if ((destino.getX() > origem.getX()) && (destino.getY() > origem.getY())) {
                        System.out.println("PASS 2 SUCCEEDED");
                        int cursorX = origem.getX();
                        int cursorY = origem.getY();
                        while ((cursorX < destino.getX()) && (cursorY < destino.getY())) {
                            cursorX++; cursorY++;
                            System.out.println("PASS 3 SUCCEEDED... LOADING...");
                            if (tabuleiro.getCasa(cursorX, cursorY).getPeca() != null) {
                                System.out.println("PASS 4 SUCCEEDED");
                                if ((tabuleiro.getCasa(cursorX - 1, cursorY - 1).getPeca() != null) && (tabuleiro.getCasa(cursorX - 1, cursorY - 1) != origem)) {
                                    System.out.println("PASS 5 SUCCEEDED");
                                    return false;
                                }
                                else if ((destino.getX() - cursorX == 1) && (destino.getY() - cursorY == 1) && (tabuleiro.getCasa(cursorX, cursorY).getPeca().getCor() != origem.getPeca().getCor())) {
                                    System.out.println("PASS 5B SUCCEEDED");
                                    podeMatar = 1;
                                    vitima = tabuleiro.getCasa(cursorX, cursorY);
                                    return true;
                                }
                                else return false;
                            }
                        }
                        return true;
                    }
                    else if ((destino.getX() > origem.getX()) && (destino.getY() < origem.getY())) {
                        System.out.println("PASS 2 SUCCEEDED");
                        int cursorX = origem.getX();
                        int cursorY = origem.getY();
                        while ((cursorX < destino.getX()) && (cursorY > destino.getY())) {
                            cursorX++; cursorY--;
                            System.out.println("PASS 3 SUCCEEDED... LOADING...");
                            if (tabuleiro.getCasa(cursorX, cursorY).getPeca() != null) {
                                System.out.println("PASS 4 SUCCEEDED");
                                if ((tabuleiro.getCasa(cursorX - 1, cursorY + 1).getPeca() != null) && (tabuleiro.getCasa(cursorX - 1, cursorY + 1) != origem)) {
                                    System.out.println("PASS 5 SUCCEEDED");
                                    return false;
                                }
                                else if ((destino.getX() - cursorX == 1) && (destino.getY() - cursorY == -1) && (tabuleiro.getCasa(cursorX, cursorY).getPeca().getCor() != origem.getPeca().getCor())) {
                                    System.out.println("PASS 5B SUCCEEDED");
                                    podeMatar = 1;
                                    vitima = tabuleiro.getCasa(cursorX, cursorY);
                                    return true;
                                }
                                else return false;
                            }
                        }
                        return true;
                    }
                    else if ((destino.getX() < origem.getX()) && (destino.getY() > origem.getY())) {
                        System.out.println("PASS 2 SUCCEEDED");
                        int cursorX = origem.getX();
                        int cursorY = origem.getY();
                        while ((cursorX > destino.getX()) && (cursorY < destino.getY())) {
                            cursorX--; cursorY++;
                            System.out.println("PASS 3 SUCCEEDED... LOADING...");
                            if (tabuleiro.getCasa(cursorX, cursorY).getPeca() != null) {
                                System.out.println("PASS 4 SUCCEEDED");
                                if ((tabuleiro.getCasa(cursorX + 1, cursorY - 1).getPeca() != null) && (tabuleiro.getCasa(cursorX + 1, cursorY - 1) != origem)) {
                                    System.out.println("PASS 5 SUCCEEDED");
                                    return false;
                                }
                                else if ((destino.getX() - cursorX == -1) && (destino.getY() - cursorY == 1) && (tabuleiro.getCasa(cursorX, cursorY).getPeca().getCor() != origem.getPeca().getCor())) {
                                    System.out.println("PASS 5B SUCCEEDED");
                                    podeMatar = 1;
                                    vitima = tabuleiro.getCasa(cursorX, cursorY);
                                    return true;
                                }
                                else return false;
                            }
                        }
                        return true;
                    }
                    else if ((destino.getX() < origem.getX()) && (destino.getY() < origem.getY())) {
                        System.out.println("PASS 2 SUCCEEDED");
                        int cursorX = origem.getX();
                        int cursorY = origem.getY();
                        while ((cursorX > destino.getX()) && (cursorY > destino.getY())) {
                            cursorX--; cursorY--;
                            System.out.println("PASS 3 SUCCEEDED... LOADING...");
                            if (tabuleiro.getCasa(cursorX, cursorY).getPeca() != null) {
                                System.out.println("PASS 4 SUCCEEDED");
                                if ((tabuleiro.getCasa(cursorX + 1, cursorY + 1).getPeca() != null) && (tabuleiro.getCasa(cursorX + 1, cursorY + 1) != origem)) {
                                    System.out.println("PASS 5 SUCCEEDED");
                                    return false;
                                }
                                else if ((destino.getX() - cursorX == -1) && (destino.getY() - cursorY == -1) && (tabuleiro.getCasa(cursorX, cursorY).getPeca().getCor() != origem.getPeca().getCor())) {
                                    System.out.println("PASS 5B SUCCEEDED");
                                    podeMatar = 1;
                                    vitima = tabuleiro.getCasa(cursorX, cursorY);
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

    public Casa getDestino() {
        return destino;
    }

    public Casa getOrigem() {
        return origem;
    }

    public Casa getVitima() {
        return vitima;
    }

    public int nivelDeViolencia() {
        return podeMatar;
    }

        /**
         * check: diagonal move
         * check: is there a piece in the place player is trying to move?
         * 
         * if (dama) {
         * 
         * check: are there pieces in the middle of the move?
         *  if (destinoX > origemX, destinoY > origemY) {
         *   while (checker=destinoX > origemX && checker=destinoY > origemY) {
         *    if (tabuleiro.getPeca(checkerX, checkerY) != null) { aka if yes:
         *     check: is there a piece below this current piece?
         *      if yes: MOVE IS ILLEGAL
         *      if no: check: is the destination directly above this piece
         *       if yes: MOVE IS LEGAL + isSkip
         *       if no: MOVE IS ILLEGAL
         *    if no: MOVE IS LEGAL
         *  else if... etc
         * 
         * if (pawn) {
         * 
         * check: is the move in the correct direction?
         *  if yes:
         *   check: is the move one space only?
         *    if yes: MOVE IS LEGAL
         *    if no: check: is the move two spaces?
         *     if yes: check: is there a piece in between the origin and destination?
         *      if yes: MOVE IS LEGAL + isSkip
         *      if no: MOVE IS ILLEGAL
         *     if no: MOVE IS ILLEGAL
         *  if no: MOVE IS ILLEGAL
         */
}
