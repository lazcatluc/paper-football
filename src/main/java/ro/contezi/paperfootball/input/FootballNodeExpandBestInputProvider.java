package ro.contezi.paperfootball.input;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ro.contezi.ab.branchbound.ExpandBest;
import ro.contezi.paperfootball.FootballNode;
import ro.contezi.paperfootball.SymmetricLine;
import ro.contezi.paperfootball.play.IllegalMoveException;

public class FootballNodeExpandBestInputProvider implements UserInputProvider {

    private static final Logger LOGGER = LogManager.getLogger(FootballNodeExpandBestInputProvider.class);
    
    private final int maxExpansions;
    private final Comparator<ExpandBest> bestChildFinder;
    private ExpandBest currentNode;

    public FootballNodeExpandBestInputProvider(int maxExpansions) {
         this(maxExpansions, (eb1, eb2) -> Double.compare(eb2.getValue(), eb1.getValue()));
    }
    
    public FootballNodeExpandBestInputProvider(int maxExpansions, Comparator<ExpandBest> bestChildFinder) {
        this.maxExpansions = maxExpansions;
        this.bestChildFinder = bestChildFinder;
    }

    @Override
    public String getInput(String previousInput, FootballNode node) {
        LOGGER.debug("Expanding " + maxExpansions + " times.");
        currentNode = Optional.ofNullable(currentNode)
                .flatMap(bestNode -> bestNode.getGrandChild(node))
                .orElseGet(() -> newExpandBest(node));
        IntStream.range(0, maxExpansions).forEach(i -> currentNode.expand());
        double value = currentNode.getValue();
        LOGGER.debug("Found value: " + value);
        FootballNode bestChild = (FootballNode) currentNode.getChild();
        List<SymmetricLine> pathToBestChild = bestChild.getPathLeadingToThisNode();
        String move = new Moves(node.getCurrentPosition()).fromList(pathToBestChild);
        LOGGER.debug("Found move: " + move);
        return move;
    }

    @Override
    public void notifyIncorrectInput(IllegalMoveException ime) {
        LOGGER.error(ime.getMessage(), ime);
    }

    protected ExpandBest newExpandBest(FootballNode node) {
        return new ExpandBest(node, bestChildFinder, 4);
    }
}
