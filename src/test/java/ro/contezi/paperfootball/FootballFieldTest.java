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
    public void findsNotEdgeOnTheNorthGoalLine() {
        assertThat(field.isEdge(new Point(0, 5))).isFalse();
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
    public void findsNotEdgeOnTheSouthGoalLine() {
        assertThat(field.isEdge(new Point(0, -5))).isFalse();
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
    
    @Test
    public void findsPointOnLeftEdge() throws Exception {
        assertThat(field.isEdge(new Point(-4, 1))).isTrue();
    }
    
    @Test
    public void findsPointNearLeftEdge() throws Exception {
        assertThat(field.isEdge(new Point(-3, 2))).isFalse();
    }
    
    @Test
    public void findsPointOnRightEdge() throws Exception {
        assertThat(field.isEdge(new Point(4, 1))).isTrue();
    }
    
    @Test
    public void findsPointNearRightEdge() throws Exception {
        assertThat(field.isEdge(new Point(3, -2))).isFalse();
    }
    
    @Test
    public void findsPointOnBottomLeft() throws Exception {
        assertThat(field.isEdge(new Point(-3, -5))).isTrue();
    }
    
    @Test
    public void findsPointNearBottomLeftEdge() throws Exception {
        assertThat(field.isEdge(new Point(-3, -4))).isFalse();
    }
    
    @Test
    public void findsPointOnTopLeft() throws Exception {
        assertThat(field.isEdge(new Point(-3, 5))).isTrue();
    }
    
    @Test
    public void findsPointNearTopLeftEdge() throws Exception {
        assertThat(field.isEdge(new Point(-3, 4))).isFalse();
    }
    
    @Test
    public void findsPointOnBottomRight() throws Exception {
        assertThat(field.isEdge(new Point(3, -5))).isTrue();
    }
    
    @Test
    public void findsPointNearBottomRightEdge() throws Exception {
        assertThat(field.isEdge(new Point(3, -4))).isFalse();
    }
    
    @Test
    public void findsPointOnTopRight() throws Exception {
        assertThat(field.isEdge(new Point(3, 5))).isTrue();
    }
    
    @Test
    public void findsPointNearTopRightEdge() throws Exception {
        assertThat(field.isEdge(new Point(3, 4))).isFalse();
    }
}
