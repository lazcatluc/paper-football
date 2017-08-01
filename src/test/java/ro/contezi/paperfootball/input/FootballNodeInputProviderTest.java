package ro.contezi.paperfootball.input;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import ro.contezi.paperfootball.FootballNode;

public class FootballNodeInputProviderTest {

    @Test
    public void solvesForDepth4() {
        FootballNodeInputProvider inputProvider = new FootballNodeInputProvider(4);
        
        assertThat(inputProvider.getInput("", new FootballNode()))
            .isIn("3", "4", "5");
    }

}
