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
    
    public Move(Casa origem, Casa destino) {
        this.origem = origem;
        this.destino = destino;
        this.podeMatar = 0;
    }

    public boolean podeMover(Tabuleiro tabuleiro) {
        int deltaX = destino.getX() - origem.getX();
        int deltaY = destino.getY() - origem.getY();

        if (Math.abs(deltaX) == Math.abs(deltaY)) {
            if (destino.getPeca() == null) {
                if (origem.getPeca().getTipo() == 0) {
                    if (destino.getY() > origem.getY()) {
                        if (destino.getY() - origem.getY() == 1) {
                            return true;
                        }
                        else if (destino.getY() - origem.getY() == 2) {
                            if (destino.getX() - origem.getX() > 0) {
                                if (tabuleiro.getCasa(destino.getX() - 1, destino.getY() - 1).getPeca() != null) {
                                    podeMatar = 1;
                                    return true;
                                }
                            }
                            else if (destino.getX() - origem.getX() < 0) {
                                if (tabuleiro.getCasa(destino.getX() + 1, destino.getY() - 1).getPeca() != null) {
                                    podeMatar = 1;
                                    return true;
                                }
                            }
                        }
                    }
                }
                else if (origem.getPeca().getTipo() == 2) {
                    if (destino.getY() < origem.getY()) {
                        if (destino.getY() - origem.getY() == -1) {
                            return true;
                        }
                        else if (destino.getY() - origem.getY() == -2) {
                            if (destino.getX() - origem.getX() > 0) {
                                if (tabuleiro.getCasa(destino.getX() - 1, destino.getY() + 1).getPeca() != null) {
                                    podeMatar = 1;
                                    return true;
                                }
                            }
                            else if (destino.getX() - origem.getX() < 0) {
                                if (tabuleiro.getCasa(destino.getX() + 1, destino.getY() + 1).getPeca() != null) {
                                    podeMatar = 1;
                                    return true;
                                }
                            }
                        }
                    }
                }
                else if ((origem.getPeca().getTipo() == 1) || (origem.getPeca().getTipo() == 3)) {
                    System.out.println("PASS 1 SUCCEEDED");
                    if ((destino.getX() > origem.getX()) && (destino.getY() > origem.getY())) {
                        System.out.println("PASS 2 SUCCEEDED");
                        int cursorX = destino.getX();
                        int cursorY = destino.getY();
                        while ((cursorX > origem.getX()) && (cursorY > origem.getY())) {
                            System.out.println("PASS 3 SUCCEEDED... LOADING...");
                            if (tabuleiro.getCasa(cursorX, cursorY).getPeca() != null) {
                                System.out.println("PASS 4 SUCCEEDED");
                                if ((tabuleiro.getCasa(cursorX - 1, cursorY - 1).getPeca() != null) && (tabuleiro.getCasa(cursorX - 1, cursorY - 1) != origem)) {
                                    System.out.println("PASS 5 SUCCEEDED");
                                    return false;
                                }
                                else if ((destino.getX() - cursorX == 1) && (destino.getY() - cursorY == 1)) {
                                    System.out.println("PASS 5B SUCCEEDED");
                                    podeMatar = 1;
                                    return true;
                                }
                                else return false;
                            }
                            cursorX--; cursorY--;
                        }
                        return true;
                    }
                    else if ((destino.getX() > origem.getX()) && (destino.getY() < origem.getY())) {
                        System.out.println("PASS 2 SUCCEEDED");
                        int cursorX = destino.getX();
                        int cursorY = destino.getY();
                        while ((cursorX > origem.getX()) && (cursorY < origem.getY())) {
                            System.out.println("PASS 3 SUCCEEDED... LOADING...");
                            if (tabuleiro.getCasa(cursorX, cursorY).getPeca() != null) {
                                System.out.println("PASS 4 SUCCEEDED");
                                if ((tabuleiro.getCasa(cursorX - 1, cursorY + 1).getPeca() != null) && (tabuleiro.getCasa(cursorX - 1, cursorY + 1) != origem)) {
                                    System.out.println("PASS 5 SUCCEEDED");
                                    return false;
                                }
                                else if ((destino.getX() - cursorX == 1) && (destino.getY() - cursorY == -1)) {
                                    System.out.println("PASS 5B SUCCEEDED");
                                    podeMatar = 1;
                                    return true;
                                }
                                else return false;
                            }
                            cursorX--; cursorY++;
                        }
                        return true;
                    }
                    else if ((destino.getX() < origem.getX()) && (destino.getY() > origem.getY())) {
                        System.out.println("PASS 2 SUCCEEDED");
                        int cursorX = destino.getX();
                        int cursorY = destino.getY();
                        while ((cursorX < origem.getX()) && (cursorY > origem.getY())) {
                            System.out.println("PASS 3 SUCCEEDED... LOADING...");
                            if (tabuleiro.getCasa(cursorX, cursorY).getPeca() != null) {
                                System.out.println("PASS 4 SUCCEEDED");
                                if ((tabuleiro.getCasa(cursorX + 1, cursorY - 1).getPeca() != null) && (tabuleiro.getCasa(cursorX + 1, cursorY - 1) != origem)) {
                                    System.out.println("PASS 5 SUCCEEDED");
                                    return false;
                                }
                                else if ((destino.getX() - cursorX == -1) && (destino.getY() - cursorY == 1)) {
                                    System.out.println("PASS 5B SUCCEEDED");
                                    podeMatar = 1;
                                    return true;
                                }
                                else return false;
                            }
                            cursorX++; cursorY--;
                        }
                        return true;
                    }
                    else if ((destino.getX() < origem.getX()) && (destino.getY() < origem.getY())) {
                        System.out.println("PASS 2 SUCCEEDED");
                        int cursorX = destino.getX();
                        int cursorY = destino.getY();
                        while ((cursorX < origem.getX()) && (cursorY < origem.getY())) {
                            System.out.println("PASS 3 SUCCEEDED... LOADING...");
                            if (tabuleiro.getCasa(cursorX, cursorY).getPeca() != null) {
                                System.out.println("PASS 4 SUCCEEDED");
                                if ((tabuleiro.getCasa(cursorX + 1, cursorY + 1).getPeca() != null) && (tabuleiro.getCasa(cursorX + 1, cursorY + 1) != origem)) {
                                    System.out.println("PASS 5 SUCCEEDED");
                                    return false;
                                }
                                else if ((destino.getX() - cursorX == -1) && (destino.getY() - cursorY == -1)) {
                                    System.out.println("PASS 5B SUCCEEDED");
                                    podeMatar = 1;
                                    return true;
                                }
                                else return false;
                            }
                            cursorX++; cursorY++;
                        }
                        return true;
                    }
                }
            }
        }
        return false;
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