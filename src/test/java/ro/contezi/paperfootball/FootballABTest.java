package ro.contezi.paperfootball;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import ro.contezi.ab.MaximizingAlphaBeta;
import ro.contezi.ab.transposable.MaximizingTranspositionAlphaBeta;
import ro.contezi.ab.transposable.TranspositionAwareNode;

public class FootballABTest {
    
    private static final Logger LOGGER = LogManager.getLogger(FootballABTest.class);
    
    @Test
    public void partiallySolvesFootballWithCache() throws Exception {
        
        TranspositionAwareNode node = new TranspositionAwareNode(new FootballNode());
        for (int i = 1; i <= 20; i++) {
            LOGGER.info("Found value at depth " + i + ": " +
                new MaximizingTranspositionAlphaBeta(node, i, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY).getValue());
            LOGGER.info("Cache size: " + node.getCacheSize());
        }
    }
    
    @Test
    public void partiallySolvesFootball() throws Exception {
        FootballNode node = new FootballNode();
        for (int i = 1; i <= 20; i++) {
            LOGGER.info("Found value at depth " + i + ": " +
                new MaximizingAlphaBeta(node, i, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY).getValue());
        }
        
    }
}
