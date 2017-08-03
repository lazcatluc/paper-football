package ro.contezi.paperfootball.play;

import java.util.List;

import ro.contezi.paperfootball.FootballField;
import ro.contezi.paperfootball.FootballNode;
import ro.contezi.paperfootball.SymmetricLine;
import ro.contezi.paperfootball.input.Moves;
import ro.contezi.paperfootball.input.UserInputProvider;

public class Play {
    private final UserInputProvider north;
    private final UserInputProvider south;
    private final FootballNode initialNode;
    
    public Play(FootballField footballField, UserInputProvider north, UserInputProvider south) {
        this.north = north;
        this.south = south;
        this.initialNode = new FootballNode(footballField);
    }
    
    public Play(FootballNode footballNode, UserInputProvider north, UserInputProvider south) {
        this.north = north;
        this.south = south;
        this.initialNode = footballNode;
    }

    public FootballNode playToTheEnd() {
        FootballNode node = initialNode;
        UserInputProvider[] providers = new UserInputProvider[] {north, south};
        int providerIndex = 0;
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
            providerIndex = 1 - providerIndex;
            previousInput = input;
            node = new FootballNode(node, moves);
        }
        return node;
    }
    
}
