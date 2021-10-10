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
                    else if (deltaY == -2) {
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
                if (origem.getPeca().getTipo() == 2) {
                    if (deltaY == -1) {
                        if (destino.getY() < origem.getY()) {
                            return true;
                        }
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
                                    System.out.println("PASS 5 FAILED (A)");
                                    return false;
                                }
                                else if (tabuleiro.getCasa(cursorX + 1, cursorY + 1).getPeca() != null) {
                                    System.out.println("PASS 5 FAILED (B)");
                                    return false;
                                }
                                else if (tabuleiro.getCasa(cursorX, cursorY).getPeca().getCor() != origem.getPeca().getCor()) {
                                    System.out.println("PASS 5 SUCCEEDED");
                                    podeMatar = true;
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
                                    System.out.println("PASS 5 FAILED (A)");
                                    return false;
                                }
                                else if (tabuleiro.getCasa(cursorX + 1, cursorY - 1).getPeca() != null) {
                                    System.out.println("PASS 5 FAILED (B)");
                                    return false;
                                }
                                else if (tabuleiro.getCasa(cursorX, cursorY).getPeca().getCor() != origem.getPeca().getCor()) {
                                    System.out.println("PASS 5 SUCCEEDED");
                                    podeMatar = true;
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
                                    System.out.println("PASS 5 FAILED (A)");
                                    return false;
                                }
                                else if (tabuleiro.getCasa(cursorX - 1, cursorY + 1).getPeca() != null) {
                                    System.out.println("PASS 5 FAILED (B)");
                                    return false;
                                }
                                else if (tabuleiro.getCasa(cursorX, cursorY).getPeca().getCor() != origem.getPeca().getCor()) {
                                    System.out.println("PASS 5 SUCCEEDED");
                                    podeMatar = true;
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
                                if ((tabuleiro.getCasa(cursorX + 1, cursorY + 1).getPeca() != null) && (tabuleiro.getCasa(cursorX + 1, cursorY + 1) != origem)) {
                                    System.out.println("PASS 5 FAILED (A)");
                                    return false;
                                }
                                else if (tabuleiro.getCasa(cursorX - 1, cursorY - 1).getPeca() != null) {
                                    System.out.println("PASS 5 FAILED (B)");
                                    return false;
                                }
                                else if (tabuleiro.getCasa(cursorX, cursorY).getPeca().getCor() != origem.getPeca().getCor()) {
                                    System.out.println("PASS 5 SUCCEEDED");
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

    public void removerTodasPecas() {
        if ((destino.getX() > origem.getX()) && (destino.getY() > origem.getY())) {
            int cursorX = destino.getX();
            int cursorY = destino.getY();
            while ((cursorX > origem.getX()) && (cursorY > origem.getY())) {
                cursorX--; cursorY--;
                Casa cursor = tabuleiro.getCasa(cursorX, cursorY);
                if (cursor.getPeca() != null) {
                    if (cursor.getPeca().getCor() != origem.getPeca().getCor()) {
                        cursor.removerPeca();
                    }
                }
            }
        }
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

    public boolean podeMoverDeNovo() {
        /**
         * check: are there possible legal moves from the checker?
         *  if yes: check: are those moves killing moves?
         *   if yes: YES MOVES
         *   if no: NO MOVES
         *  if no: NO MOVES
         */
        for (int i = 0; i <= 7; i++) {
            for (int j = 0; j <= 7; j++) {
                Move checker = new Move(destino, tabuleiro.getCasa(i, j), tabuleiro);
                if (checker.podeMover()) {
                    if (checker.nivelDeViolencia()) {
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

    public boolean nivelDeViolencia() {
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
