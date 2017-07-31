package ro.contezi.paperfootball;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

public class NextMovesTest {

    @Test
    public void getsImmediateNeighborsAsPossibleMoves() {
        assertThat(new NextMoves(new FootballField(8, 10), new Point(), Collections.emptySet()).getPossibleMoves().size())
            .isEqualTo(8);
    }

    @Test
    public void doesntGetLineAlreadyPassedAsPossibleMove() {
        assertThat(new NextMoves(new FootballField(8, 10), new Point(), 
                Collections.singleton(new SymmetricLine(new Point(), new Point(0, 1))))
                .getPossibleMoves().size()).isEqualTo(7);
    }
    
    @Test
    public void bouncesOffTheEdge() throws Exception {
        Point origin = new Point(-3, 0);
        Set<SymmetricLine> alreadyPassed = new HashSet<>(Arrays.asList(
                new SymmetricLine(origin, new Point(-2, -1)),
                new SymmetricLine(origin, new Point(-2, 0)),
                new SymmetricLine(origin, new Point(-2, 1))        
                        ));
        Set<List<SymmetricLine>> expectedPaths = new HashSet<>(Arrays.asList(
                Arrays.asList(new SymmetricLine(origin, new Point(-3, -1))),
                Arrays.asList(new SymmetricLine(origin, new Point(-3, 1))),
                Arrays.asList(new SymmetricLine(origin, new Point(-4, 0)), new SymmetricLine(new Point(-4, 0), new Point(-3, -1))),
                Arrays.asList(new SymmetricLine(origin, new Point(-4, 0)), new SymmetricLine(new Point(-4, 0), new Point(-3, 1))),
                Arrays.asList(new SymmetricLine(origin, new Point(-4, -1)), new SymmetricLine(new Point(-4, -1), new Point(-3, -1))),
                Arrays.asList(new SymmetricLine(origin, new Point(-4, 1)), new SymmetricLine(new Point(-4, 1), new Point(-3, 1))),
                Arrays.asList(new SymmetricLine(origin, new Point(-4, 0)), new SymmetricLine(new Point(-4, 0), new Point(-3, 1))),
                Arrays.asList(new SymmetricLine(origin, new Point(-4, -1)), new SymmetricLine(new Point(-4, -1), new Point(-3, -2))),
                Arrays.asList(new SymmetricLine(origin, new Point(-4, 1)), new SymmetricLine(new Point(-4, 1), new Point(-3, 2)))
            ));
        Set<List<SymmetricLine>> possibleMoves = new NextMoves(new FootballField(8, 10), origin, 
                alreadyPassed)
                .getPossibleMoves();

        Set<List<SymmetricLine>> possibleButNotExpected = new HashSet<>(possibleMoves);
        possibleButNotExpected.removeAll(expectedPaths);
        assertThat(possibleButNotExpected).isEmpty();
        
        Set<List<SymmetricLine>> expectedButNotPossible = new HashSet<>(expectedPaths);
        expectedButNotPossible.removeAll(possibleMoves);
        assertThat(expectedButNotPossible).isEmpty();
        
    }
    
    @Test
    public void canRunOutOfMovesIfPathBlocked() throws Exception {
        Point origin = new Point(-3, 0);
        Set<SymmetricLine> alreadyPassed = new HashSet<>(Arrays.asList(
                new SymmetricLine(origin, new Point(-2, -1)),
                new SymmetricLine(origin, new Point(-2, 0)),
                new SymmetricLine(origin, new Point(-2, 1)),
                new SymmetricLine(origin, new Point(-3, 1)),
                new SymmetricLine(origin, new Point(-3, -1)),
                new SymmetricLine(origin, new Point(-4, 1)),
                new SymmetricLine(origin, new Point(-4, -1)),
                new SymmetricLine(new Point(-4, 0), new Point(-3, 1)),
                new SymmetricLine(new Point(-4, 0), new Point(-3, -1))        
                        ));
        
        assertThat(new NextMoves(new FootballField(8, 10), origin, 
                alreadyPassed)
                .getPossibleMoves()).isEmpty();
    }
}
