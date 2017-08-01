package ro.contezi.paperfootball.play;

import ro.contezi.paperfootball.Point;

public class EndPointAlreadyParsedException extends IllegalMoveException {

    private static final long serialVersionUID = 1L;

    public EndPointAlreadyParsedException(Point point) {
        super("You cannot stop at " + point + ". Keep moving!");
    }

}
