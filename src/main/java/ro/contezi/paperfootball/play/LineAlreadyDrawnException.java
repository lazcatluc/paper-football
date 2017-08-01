package ro.contezi.paperfootball.play;

import ro.contezi.paperfootball.SymmetricLine;

public class LineAlreadyDrawnException extends IllegalMoveException {

    private static final long serialVersionUID = 1L;

    public LineAlreadyDrawnException(SymmetricLine line) {
        super("Line is already drawn on the board: " + line);
    }

}
