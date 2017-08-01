package ro.contezi.paperfootball.input;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import ro.contezi.paperfootball.FootballNode;
import ro.contezi.paperfootball.play.IllegalMoveException;

public class IOInputProvider implements UserInputProvider {

    private final PrintStream output;
    private final Scanner input;

    public IOInputProvider(InputStream input, OutputStream output) {
        this.input = new Scanner(input);
        this.output = new PrintStream(output);
    }

    @Override
    public String getInput(String previousInput, FootballNode node) {
        output.println("Opponent played: " + previousInput + ". Your move?");
        return input.nextLine();
    }

    @Override
    public void notifyIncorrectInput(IllegalMoveException ime) {
        output.println(ime.getMessage());
    }

}
