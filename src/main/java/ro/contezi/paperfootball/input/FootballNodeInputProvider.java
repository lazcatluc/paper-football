package ro.contezi.paperfootball.input;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ro.contezi.ab.MaximizingAlphaBeta;
import ro.contezi.paperfootball.FootballNode;
import ro.contezi.paperfootball.SymmetricLine;
import ro.contezi.paperfootball.play.IllegalMoveException;

public class FootballNodeInputProvider implements UserInputProvider {

    private static final Logger LOGGER = LogManager.getLogger(FootballNodeInputProvider.class);
    private final int maxDepth;

    public FootballNodeInputProvider(int maxDepth) {
        this.maxDepth = maxDepth;
    }

    @Override
    public String getInput(String previousInput, FootballNode node) {
        LOGGER.debug("Expanding to depth " + maxDepth);
        MaximizingAlphaBeta maximizingAlphaBeta = new MaximizingAlphaBeta(node, maxDepth, Double.NEGATIVE_INFINITY,
                Double.POSITIVE_INFINITY);
        double value = maximizingAlphaBeta.getValue();
        LOGGER.debug("Found value: " + value);
        FootballNode bestChild = (FootballNode) maximizingAlphaBeta.getChild();
        List<SymmetricLine> pathToBestChild = bestChild.getPathLeadingToThisNode();
        return new Moves(node.getCurrentPosition()).fromList(pathToBestChild);
    }

    @Override
    public void notifyIncorrectInput(IllegalMoveException ime) {
        LOGGER.error(ime.getMessage(), ime);
    }

}
