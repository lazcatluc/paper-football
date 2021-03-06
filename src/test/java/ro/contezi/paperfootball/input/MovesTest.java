package ro.contezi.paperfootball.input;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;

import org.junit.Test;

import ro.contezi.paperfootball.Point;
import ro.contezi.paperfootball.SymmetricLine;
import ro.contezi.paperfootball.input.Moves;

public class MovesTest {

    @Test
    public void findsRomboidalPathFromString() {
        assertThat(new Moves().fromString("1357")).isEqualTo(Arrays.asList(
                new SymmetricLine(new Point(0, 0), new Point(1, 1)),
                new SymmetricLine(new Point(1, 1), new Point(2, 0)),
                new SymmetricLine(new Point(2, 0), new Point(1, -1)),
                new SymmetricLine(new Point(1, -1), new Point(0, 0))
            ));
    }

    @Test
    public void findsSquarePathFromString() {
        assertThat(new Moves().fromString("0246")).isEqualTo(Arrays.asList(
                new SymmetricLine(new Point(0, 0), new Point(0, 1)),
                new SymmetricLine(new Point(0, 1), new Point(1, 1)),
                new SymmetricLine(new Point(1, 1), new Point(1, 0)),
                new SymmetricLine(new Point(1, 0), new Point(0, 0))
            ));
    }
    
    @Test
    public void findsRomboidalPathFromList() {
        assertThat(new Moves().fromList(Arrays.asList(
                new SymmetricLine(new Point(0, 0), new Point(1, 1)),
                new SymmetricLine(new Point(1, 1), new Point(2, 0)),
                new SymmetricLine(new Point(2, 0), new Point(1, -1)),
                new SymmetricLine(new Point(1, -1), new Point(0, 0))
            ))).isEqualTo("1357");
    }

    @Test
    public void findsSquarePathFromList() {
        assertThat(new Moves().fromList(Arrays.asList(
                new SymmetricLine(new Point(0, 0), new Point(0, 1)),
                new SymmetricLine(new Point(0, 1), new Point(1, 1)),
                new SymmetricLine(new Point(1, 1), new Point(1, 0)),
                new SymmetricLine(new Point(1, 0), new Point(0, 0))
            ))).isEqualTo("0246");
    }
}
