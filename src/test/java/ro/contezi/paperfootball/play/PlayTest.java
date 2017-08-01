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
    
    @Test(expected = LineAlreadyDrawnException.class)
    public void doesNotValidateMoveToNextNeighborWhenLineAlreadyDrawn() {
        SymmetricLine lineToNeighbor = new SymmetricLine(currentPosition, new Point(1, 0));
        parsedLines.add(lineToNeighbor);
        Play.validate(Arrays.asList(lineToNeighbor), node());
    }
    
    @Test
    public void validatesMoveToNextNeighborWithBounceOnTheEdge() {
        currentPosition = new Point(-3, 0);
        Play.validate(Arrays.asList(new SymmetricLine(currentPosition, new Point(-4, 1)), 
                new SymmetricLine(new Point(-3, 2), new Point(-4, 1))), node());
    }
    
    @Test(expected = PointOutException.class)
    public void doesNotValidateMoveToBounceOnTheEdgeIfOut() {
        currentPosition = new Point(-3, 0);
        Play.validate(Arrays.asList(new SymmetricLine(currentPosition, new Point(-4, 1)), 
                new SymmetricLine(new Point(-5, 1), new Point(-4, 1))), node());
    }
    
    @Test(expected = LineAlreadyDrawnException.class)
    public void cannotWalkOnVerticalEdge() {
        currentPosition = new Point(-3, 0);
        Play.validate(Arrays.asList(new SymmetricLine(currentPosition, new Point(-4, 1)), 
                new SymmetricLine(new Point(-4, 2), new Point(-4, 1)),
                new SymmetricLine(new Point(-4, 2), new Point(-3, 2))), node());
    }
    
    @Test(expected = LineAlreadyDrawnException.class)
    public void cannotWalkOnHorizontalEdge() {
        currentPosition = new Point(0, -4);
        Play.validate(Arrays.asList(new SymmetricLine(currentPosition, new Point(-1, -5)), 
                new SymmetricLine(new Point(-2, -5), new Point(-1, -5)),
                new SymmetricLine(new Point(-2, -5), new Point(-1, -4))), node());
    }
    
    @Test(expected = LineAlreadyDrawnException.class)
    public void cannotWalkOnEdgeHorizontalEdge() {
        currentPosition = new Point(-3, 0);
        Play.validate(Arrays.asList(new SymmetricLine(currentPosition, new Point(-4, 1)), 
                new SymmetricLine(new Point(-4, 2), new Point(-4, 1)),
                new SymmetricLine(new Point(-4, 2), new Point(-3, 2))), node());
    }
    
    @Test(expected = LineAlreadyDrawnException.class)
    public void cannotWalkBackTheWayYouCame() {
        currentPosition = new Point(-3, -4);
        Play.validate(Arrays.asList(new SymmetricLine(currentPosition, new Point(-4, -5)), 
                new SymmetricLine(currentPosition, new Point(-4, -5)),
                new SymmetricLine(currentPosition, new Point(-2, -3))), node());
    }

    @Test
    public void validatesMoveOnTheEdgeNearTheCorner() {
        currentPosition = new Point(-3, -4);
        Play.validate(Arrays.asList(new SymmetricLine(currentPosition, new Point(-4, -4)), 
                new SymmetricLine(new Point(-3, -5), new Point(-4, -4)),
                new SymmetricLine(new Point(-3, -5), new Point(-2, -4))), node());
    }
    
    @Test(expected = IllegalContinuationFromPointException.class)
    public void doesNotValidateMoveToNextNextNeighbor() {
        Play.validate(Arrays.asList(new SymmetricLine(currentPosition, new Point(1, 0)),
                new SymmetricLine(new Point(1, 0), new Point(2, 0))), node());
    }
}
