package ro.contezi.paperfootball.play;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import ro.contezi.paperfootball.FootballField;
import ro.contezi.paperfootball.FootballNode;
import ro.contezi.paperfootball.Point;
import ro.contezi.paperfootball.SymmetricLine;

public class PlayTest {
    
    private FootballField footballField;
    private Set<SymmetricLine> parsedLines;
    private Point currentPosition;
    
    @Before
    public void setUp() {
        footballField = new FootballField();
        parsedLines = new HashSet<>();
        currentPosition = new Point();
    }
    
    private FootballNode node() {
        return new FootballNode(footballField, parsedLines, currentPosition, (f1, f2) -> 0);
    }

    @Test
    public void validatesMoveToNextNeighbor() {
        Play.validate(Arrays.asList(new SymmetricLine(currentPosition, new Point(1, 0))), node());
    }
    
    @Test
    public void validatesMoveToNextNeighborWithBounceOnTheEdge() {
        currentPosition = new Point(-3, 0);
        Play.validate(Arrays.asList(new SymmetricLine(currentPosition, new Point(-4, 1)), 
                new SymmetricLine(new Point(-3, 2), new Point(-4, 1))), node());
    }
    
    @Test(expected = LineAlreadyDrawnException.class)
    public void cannotWalkOnEdge() {
        currentPosition = new Point(-3, 0);
        Play.validate(Arrays.asList(new SymmetricLine(currentPosition, new Point(-4, 1)), 
                new SymmetricLine(new Point(-4, 2), new Point(-4, 1)),
                new SymmetricLine(new Point(-4, 2), new Point(-3, 2))), node());
    }

    @Test(expected = IllegalContinuationFromPointException.class)
    public void doesNotValidateMoveToNextNextNeighbor() {
        Play.validate(Arrays.asList(new SymmetricLine(currentPosition, new Point(1, 0)),
                new SymmetricLine(new Point(1, 0), new Point(2, 0))), node());
    }
}
