package ro.contezi.paperfootball.input;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import ro.contezi.paperfootball.FootballNode;

public class FootballNodeExpandBestInputProviderTest {

    @Test
    public void solvesForDepth40() {
        FootballNodeExpandBestInputProvider inputProvider = new FootballNodeExpandBestInputProvider(40);
        
        assertThat(inputProvider.getInput("", new FootballNode()))
            .isIn("3", "4", "5");
    }

}
