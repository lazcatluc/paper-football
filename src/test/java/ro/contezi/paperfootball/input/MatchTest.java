package ro.contezi.paperfootball.input;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Ignore;
import org.junit.Test;

import ro.contezi.paperfootball.FootballNode;
import ro.contezi.paperfootball.play.Play;

@Ignore
public class MatchTest {
    
    private static final Logger LOGGER = LogManager.getLogger(MatchTest.class);
    
    @Test
    public void makesMatch() {
        FootballNodeABInputProvider north = new FootballNodeABInputProvider(5);
        FootballNodeExpandBestInputProvider south = new FootballNodeExpandBestInputProvider(10, 
                (eb1, eb2) -> Double.compare(eb1.getValue(), eb2.getValue()));
        
        FootballNode footballNode = new FootballNode();
        FootballNode playToTheEnd = new Play(footballNode, north, south).playToTheEnd();
        LOGGER.info("Final position: " + playToTheEnd);
    }
}
