package ro.contezi.paperfootball.input;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import ro.contezi.paperfootball.Point;
import ro.contezi.paperfootball.SymmetricLine;

public class Moves {
    
    private final Point currentPosition;
    private static final Map<Character, Point> CLOCKWISE_CHARS = new HashMap<>();
    private static final Map<Point, Character> REVERSE_CLOCKWISE_CHARS = new HashMap<>();
    static {
        CLOCKWISE_CHARS.put('0', new Point(0, 1));
        CLOCKWISE_CHARS.put('1', new Point(1, 1));
        CLOCKWISE_CHARS.put('2', new Point(1, 0));
        CLOCKWISE_CHARS.put('3', new Point(1, -1));
        CLOCKWISE_CHARS.put('4', new Point(0, -1));
        CLOCKWISE_CHARS.put('5', new Point(-1, -1));
        CLOCKWISE_CHARS.put('6', new Point(-1, 0));
        CLOCKWISE_CHARS.put('7', new Point(-1, 1));
        CLOCKWISE_CHARS.forEach((ch, point) -> REVERSE_CLOCKWISE_CHARS.put(point, ch));
    }
    
    public Moves(){ 
        this(new Point());
    }
    
    public Moves(Point currentPosition) {
        this.currentPosition = currentPosition;
    }

    public List<SymmetricLine> fromString(String userInput) {
        Point currentPoint = currentPosition;
        List<SymmetricLine> ret = new ArrayList<>(userInput.length());
        for (Character inputChar : userInput.toCharArray()) {
            Point newPointToAdd = Optional.ofNullable(CLOCKWISE_CHARS.get(inputChar))
                    .orElseThrow(() -> new IllegalArgumentException("Unexpected direction: "
                                + inputChar));
            Point newPoint = add(currentPoint, newPointToAdd);
            ret.add(new SymmetricLine(currentPoint, newPoint));
            currentPoint = newPoint;
        }
        return ret;
    }

    public String fromList(List<SymmetricLine> userInput) {
        char[] chars = new char[userInput.size()];
        Point currentPoint = currentPosition;
        int i = 0;
        for (SymmetricLine line : userInput) {
            Point newPoint = line.opposite(currentPoint);
            Point subtracted = subtract(newPoint, currentPoint);
            chars[i++] = Optional.ofNullable(REVERSE_CLOCKWISE_CHARS.get(subtracted))
                    .orElseThrow(() -> new IllegalArgumentException("Unexpected subtracted: "
                            + subtracted));
            currentPoint = newPoint;
        }
        return new String(chars);
    }
    
    private static Point add(Point from, Point pointToAdd) {
        return new Point(from.getX() + pointToAdd.getX(), from.getY() + pointToAdd.getY());
    }
    
    private static Point subtract(Point from, Point pointToSubtract) {
        return new Point(from.getX() - pointToSubtract.getX(), from.getY() - pointToSubtract.getY());
    }
}
