package ro.contezi.paperfootball.input;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import ro.contezi.paperfootball.FootballNode;

public class FootballNodeABInputProviderTest {

    @Test
    public void solvesForDepth4() {
        FootballNodeABInputProvider inputProvider = new FootballNodeABInputProvider(4);
        
        assertThat(inputProvider.getInput("", new FootballNode()))
            .isIn("3", "4", "5");
    }

}
