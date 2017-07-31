package ro.contezi.paperfootball;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.offset;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public class FootballNodeTest {

    @Test
    public void heuristicIsZeroInitially() {
        assertThat(new FootballNode().heuristicValue()).isCloseTo(0, offset(0.001));
    }

    @Test
    public void heuristicIsMinValueWhenGoalScoredInNorth() {
        assertThat(new FootballNode(new FootballField(), Collections.emptySet(), new Point(0, 6), (f1, f2) -> 0)
                .heuristicValue()).isCloseTo(Integer.MIN_VALUE, offset(0.001));
    }

    @Test
    public void heuristicIsMaxValueWhenGoalScoredInSouth() {
        assertThat(new FootballNode(new FootballField(), Collections.emptySet(), new Point(0, -6), (f1, f2) -> 0)
                .heuristicValue()).isCloseTo(Integer.MAX_VALUE, offset(0.001));
    }

    @Test
    public void heuristicIs0WhenCannotReachEitherSide() throws Exception {
        Point origin = new Point(-3, 0);
        Set<SymmetricLine> alreadyPassed = new HashSet<>(Arrays.asList(new SymmetricLine(origin, new Point(-2, -1)),
                new SymmetricLine(origin, new Point(-2, 0)), new SymmetricLine(origin, new Point(-2, 1)),
                new SymmetricLine(origin, new Point(-3, 1)), new SymmetricLine(origin, new Point(-3, -1)),
                new SymmetricLine(origin, new Point(-4, 1)), new SymmetricLine(origin, new Point(-4, -1)),
                new SymmetricLine(new Point(-4, 0), new Point(-3, 1)),
                new SymmetricLine(new Point(-4, 0), new Point(-3, -1))));

        assertThat(new FootballNode(new FootballField(), alreadyPassed, origin, (f1, f2) -> 0).heuristicValue())
                .isCloseTo(0, offset(0.001));
    }
}
