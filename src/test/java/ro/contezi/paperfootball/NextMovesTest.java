package ro.contezi.paperfootball;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collections;

import org.junit.Test;

public class NextMovesTest {

    @Test
    public void getsImmediateNeighborsAsPossibleMoves() {
        assertThat(new NextMoves(new FootballField(8, 10), new Point(), Collections.emptySet()).getPossibleMoves().size()).isEqualTo(8);
    }

}
