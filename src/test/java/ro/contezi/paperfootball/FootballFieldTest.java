package ro.contezi.paperfootball;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class FootballFieldTest {

    private final FootballField field = new FootballField(8, 10);
    
    @Test
    public void findsNotGoalOnTheNorthGoalLine() {
        assertThat(field.isGoalNorth(new Point(0, 5))).isFalse();
    }
    
    @Test
    public void findsGoalNorthCenter() {
        assertThat(field.isGoalNorth(new Point(0, 6))).isTrue();
    }

    @Test
    public void findsGoalNorthLeft() {
        assertThat(field.isGoalNorth(new Point(-1, 6))).isTrue();
    }
    
    @Test
    public void findsGoalNorthRight() {
        assertThat(field.isGoalNorth(new Point(1, 6))).isTrue();
    }
    
    @Test
    public void findsNotGoalOnTheSouthGoalLine() {
        assertThat(field.isGoalSouth(new Point(0, -5))).isFalse();
    }
    
    @Test
    public void findsGoalSouthCenter() {
        assertThat(field.isGoalSouth(new Point(0, -6))).isTrue();
    }

    @Test
    public void findsGoalSouthLeft() {
        assertThat(field.isGoalSouth(new Point(-1, -6))).isTrue();
    }
    
    @Test
    public void findsGoalSouthRight() {
        assertThat(field.isGoalSouth(new Point(1, -6))).isTrue();
    }
}
