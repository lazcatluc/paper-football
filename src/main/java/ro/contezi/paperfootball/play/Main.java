package ro.contezi.paperfootball.play;

import ro.contezi.paperfootball.FootballField;
import ro.contezi.paperfootball.input.FootballNodeABInputProvider;
import ro.contezi.paperfootball.input.IOInputProvider;

public class Main {

    public static void main(String[] args) {
        new Play(new FootballField(), new FootballNodeABInputProvider(5), 
                new IOInputProvider(System.in, System.out)).playToTheEnd();
    }

}
