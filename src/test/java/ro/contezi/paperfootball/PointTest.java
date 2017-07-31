package ro.contezi.paperfootball;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

import org.junit.Test;

public class PointTest {

    @Test
    public void originEqualToOrigin() {
        assertThat(new Point()).isEqualTo(new Point());
    }

    @Test
    public void originDifferentFromAdiacentX() {
        assertThat(new Point()).isNotEqualTo(new Point(1, 0));
    }
    
    @Test
    public void originDifferentFromAdiacentY() {
        assertThat(new Point()).isNotEqualTo(new Point(0, 1));
    }
    
    @Test
    public void equalPointsHaveSameHashCode() {
        assertThat(new Point(-1, 1).hashCode()).isEqualTo(new Point(-1, 1).hashCode());
    }
    
    @Test
    public void originDifferentFromMock() {
        assertThat(new Point()).isNotEqualTo(mock(Point.class));
    }
    
    @Test
    public void originDifferentFromNull() {
        assertThat(new Point()).isNotEqualTo(null);
    }
    
    @Test
    public void equalToSelf() {
        Point origin = new Point();
        assertThat(origin).isEqualTo(origin);
    }
}
