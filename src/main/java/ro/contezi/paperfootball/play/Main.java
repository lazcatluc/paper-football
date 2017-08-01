package ro.contezi.paperfootball.play;

import ro.contezi.paperfootball.FootballField;
import ro.contezi.paperfootball.input.FootballNodeInputProvider;
import ro.contezi.paperfootball.input.IOInputProvider;

public class Main {

    public static void main(String[] args) {
        new Play(new FootballField(), new FootballNodeInputProvider(3), 
                new IOInputProvider(System.in, System.out)).playToTheEnd();
    }

}
