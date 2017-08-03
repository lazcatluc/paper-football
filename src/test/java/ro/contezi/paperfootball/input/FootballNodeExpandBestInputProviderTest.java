package ro.contezi.paperfootball.input;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import ro.contezi.ab.branchbound.ExpandBest;
import ro.contezi.paperfootball.FootballNode;
import ro.contezi.paperfootball.Point;
import ro.contezi.paperfootball.SymmetricLine;

public class FootballNodeExpandBestInputProviderTest {

    private int newExpandBestCalls;
    
    @Before
    public void setUp() {
        newExpandBestCalls = 0;
    }
    
    @Test
    public void solvesForDepth4() {
        FootballNodeExpandBestInputProvider inputProvider = new FootballNodeExpandBestInputProvider(4);
        
        assertThat(inputProvider.getInput("", new FootballNode()))
            .isEqualTo("4");
    }

    @Test
    public void reusesPreviousExpansionsIfAnticipatedNodeWasPlayed() throws Exception {
        FootballNodeExpandBestInputProvider inputProvider = 
                new FootballNodeExpandBestInputProvider(4) {
                    @Override
                    protected ExpandBest newExpandBest(FootballNode node) {
                        newExpandBestCalls++;
                        return super.newExpandBest(node);
                    }
                };
        FootballNode node = new FootballNode();
        inputProvider.getInput("", node);
        node = new FootballNode(node, Arrays.asList(new SymmetricLine(
                new Point(0, 0), new Point(0, -1))));
        node = new FootballNode(node, Arrays.asList(new SymmetricLine(
                new Point(0, -1), new Point(1, 0))));
        inputProvider.getInput("1", node);
        
        assertThat(newExpandBestCalls).isEqualTo(1);
    }
    
    @Test
    public void doesNotPreviousExpansionsIfNotAnticipatedNodeWasPlayed() throws Exception {
        FootballNodeExpandBestInputProvider inputProvider = 
                new FootballNodeExpandBestInputProvider(3) {
                    @Override
                    protected ExpandBest newExpandBest(FootballNode node) {
                        newExpandBestCalls++;
                        return super.newExpandBest(node);
                    }
                };
        FootballNode node = new FootballNode();
        inputProvider.getInput("", node);
        node = new FootballNode(node, Arrays.asList(new SymmetricLine(
                new Point(0, 0), new Point(0, -1))));
        node = new FootballNode(node, Arrays.asList(new SymmetricLine(
                new Point(0, -1), new Point(0, -2))));
        inputProvider.getInput("4", node);
        
        assertThat(newExpandBestCalls).isEqualTo(2);
    }
}
