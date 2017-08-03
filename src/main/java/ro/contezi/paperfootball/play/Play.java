package ro.contezi.paperfootball.play;

import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ro.contezi.paperfootball.FootballField;
import ro.contezi.paperfootball.FootballNode;
import ro.contezi.paperfootball.SymmetricLine;
import ro.contezi.paperfootball.input.Moves;
import ro.contezi.paperfootball.input.UserInputProvider;

public class Play {
    private static final Logger LOGGER = LogManager.getLogger(Play.class);
    
    private final UserInputProvider north;
    private final UserInputProvider south;
    private final FootballNode initialNode;
    private long[] millisecondsSpent;
    
    public Play(FootballField footballField, UserInputProvider north, UserInputProvider south) {
        this(new FootballNode(footballField), north, south);
    }
    
    public Play(FootballNode footballNode, UserInputProvider north, UserInputProvider south) {
        this.north = north;
        this.south = south;
        this.initialNode = footballNode;
        millisecondsSpent = new long[2];
    }

    public FootballNode playToTheEnd() {
        FootballNode node = initialNode;
        UserInputProvider[] providers = new UserInputProvider[] {north, south};
        Arrays.fill(millisecondsSpent, 0);
        int providerIndex = 0;
        long currentTime = System.currentTimeMillis();
        String previousInput = "";
        while (!node.isTerminal()) {
            UserInputProvider provider = providers[providerIndex];
            String input = provider.getInput(previousInput, node);
            List<SymmetricLine> moves = new Moves(node.getCurrentPosition())
                    .fromString(input);
            try {
                new MoveValidator(moves, node).validate();
            }
            catch (IllegalMoveException ime) {
                provider.notifyIncorrectInput(ime);
                continue;
            }
            long newTime = System.currentTimeMillis();
            millisecondsSpent[providerIndex] += newTime - currentTime;
            currentTime = newTime;
            providerIndex = 1 - providerIndex;
            previousInput = input;
            node = new FootballNode(node, moves);
            logTime();
        }
        
        return node;
    }
    
    private void logTime() {
        LOGGER.debug("Time spent by north: " + millisecondsSpent[0] / 1000);
        LOGGER.debug("Time spent by south: " + millisecondsSpent[1] / 1000);
    }
}
