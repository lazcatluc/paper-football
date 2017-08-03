package ro.contezi.paperfootball.draw;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

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
    }
    
    @Test
    public void drawsHorizontalEdge() {
        initEdges(new Point(0, 0), new Point(1, 0));
        
        drawFootballField.draw();
        
        verify(canvas).drawLine(new Point(0, 0), new Point(1, 0));
    }

}
