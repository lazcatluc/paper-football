package ro.contezi.paperfootball.draw;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.util.Arrays;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import ro.contezi.paperfootball.FootballNode;
import ro.contezi.paperfootball.Point;
import ro.contezi.paperfootball.SymmetricLine;

public class DrawFootballNodeTest {

    private static final Logger LOGGER = LogManager.getLogger(DrawFootballNodeTest.class);

    @Test
    public void drawsCurent() {
        Canvas canvas = mock(Canvas.class);

        new DrawFootballNode(new FootballNode(), canvas).draw();

        verify(canvas).drawCurrent(new Point(0, 0));
    }

    @Test
    public void drawsAll() throws Exception {
        Canvas canvas = new AsciiCanvas();
        FootballNode footballNode = new FootballNode(new FootballNode(),
                Arrays.asList(new SymmetricLine(new Point(0, 0), new Point(1, 1)),
                        new SymmetricLine(new Point(1, 1), new Point(0, 2))));

        new DrawFootballNode(footballNode, canvas).draw();
        
        LOGGER.info("\n" + canvas);
    }
}
