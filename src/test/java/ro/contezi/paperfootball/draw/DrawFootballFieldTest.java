package ro.contezi.paperfootball.draw;

import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ro.contezi.paperfootball.FootballField;
import ro.contezi.paperfootball.Point;

public class DrawFootballFieldTest {

    @InjectMocks
    private DrawFootballField drawFootballField;
    @Mock
    private FootballField footballField;
    @Mock
    private Canvas canvas;
    
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }
    
    private void initEdges(Point...edges) {
        when(footballField.edge()).thenReturn(Arrays.stream(edges));
        Arrays.stream(edges).forEach(point -> 
            when(footballField.isEdge(point)).thenReturn(true));
    }
    
    @Test
    public void drawsHorizontalEdge() {
        initEdges(new Point(0, 0), new Point(1, 0));
        
        drawFootballField.drawEdges();
        
        verify(canvas).drawLine(new Point(0, 0), new Point(1, 0));
    }

    @Test
    public void drawsVerticalEdge() {
        initEdges(new Point(0, 0), new Point(0, 1));
        
        drawFootballField.drawEdges();
        
        verify(canvas).drawLine(new Point(0, 0), new Point(0, 1));
    }
    
    @Test
    public void doesntDrawDiagonalEdge() {
        initEdges(new Point(0, 0), new Point(1, 1));
        
        drawFootballField.drawEdges();
        
        verify(canvas, never()).drawLine(anyObject(), anyObject());
    }
    
    @Test
    public void drawsGoalNorth() throws Exception {
        when(footballField.getGoalNorth()).thenReturn(new Point(1, 1));
        
        drawFootballField.drawGoalNorth();
        
        verify(canvas).drawLine(new Point(0, 1), new Point(1, 1));
        verify(canvas).drawLine(new Point(2, 1), new Point(1, 1));
        verify(canvas).drawLine(new Point(0, 0), new Point(0, 1));
        verify(canvas).drawLine(new Point(2, 0), new Point(2, 1));
    }
}
