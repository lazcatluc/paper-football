package ro.contezi.paperfootball;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public class FastestRouteTest {

    @Test
    public void getsRouteToDirectNeighbor() {
        assertThat(new FastestRoute().getFastestRoute(new Point(0, 1))).contains(1);
    }
    
    @Test
    public void getsRouteToNeighborOfNeighbor() throws Exception {
        assertThat(new FastestRoute().getFastestRoute(new Point(0, 2))).contains(2);
    }
    
    @Test
    public void getsRouteToNeighbor3() throws Exception {
        assertThat(new FastestRoute().getFastestRoute(new Point(0, 3))).contains(3);
    }
    
    @Test
    public void getsRouteToNeighbor4() throws Exception {
        assertThat(new FastestRoute().getFastestRoute(new Point(0, 4))).contains(4);
    }
    
    @Test
    public void getsRouteToNeighbor5() throws Exception {
        assertThat(new FastestRoute().getFastestRoute(new Point(0, 5))).contains(5);
    }
    
    @Test
    public void noRouteToOutside() throws Exception {
        assertThat(new FastestRoute().getFastestRoute(new Point(0, 15))).isEmpty();
    }

    @Test
    public void findsGoalToBeTheSameAsGoalLineDueToWallOnLastMove() throws Exception {
        FastestRoute fastestRoute = new FastestRoute();
        assertThat(fastestRoute.getFastestRoute(new Point(0, 5)))
            .isEqualTo(fastestRoute.getFastestRoute(new Point(0, 6)))
            .contains(5);
    }
    
    @Test
    public void findsEmptyWhenPathIsBlocked() throws Exception {
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
        
        assertThat(new FastestRoute(new FootballField(), origin, alreadyPassed)
                .getFastestRoute(new Point(0, 5))).isEmpty();
    }
}
