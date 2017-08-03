package ro.contezi.paperfootball.draw;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;

import ro.contezi.paperfootball.Point;

public class AsciiCanvasTest {

    private AsciiCanvas asciiCanvas;

    @Before
    public void setUp() {
        asciiCanvas = new AsciiCanvas();
    }
    
    @Test
    public void getsOriginXAtHalfWidth() {
        assertThat(asciiCanvas.getX(new Point(0, 0))).isEqualTo(15);
    }

    @Test
    public void getsOriginYAtHalfHeight() {
        assertThat(asciiCanvas.getY(new Point(0, 0))).isEqualTo(21);
    }
    
    @Test
    public void drawsPoint() throws Exception {
        asciiCanvas.draw(new Point(1, 1));
        assertThat(asciiCanvas.getDrawMatrix()[24][18]).isEqualTo('o');
    }
    
    @Test
    public void drawsCurrent() throws Exception {
        asciiCanvas.drawCurrent(new Point(1, 1));
        assertThat(asciiCanvas.getDrawMatrix()[24][18]).isEqualTo('@');
    }
    
    @Test
    public void drawsHorizontalLine() throws Exception {
        asciiCanvas.drawLine(new Point(-1, 0), new Point(-2, 0));
        assertThat(asciiCanvas.getDrawMatrix()[21][11]).isEqualTo('-');
        assertThat(asciiCanvas.getDrawMatrix()[21][10]).isEqualTo('-');
    }
    
    @Test
    public void drawsVerticalLine() throws Exception {
        asciiCanvas.drawLine(new Point(0, -1), new Point(0, -2));
        assertThat(asciiCanvas.getDrawMatrix()[16][15]).isEqualTo('|');
        assertThat(asciiCanvas.getDrawMatrix()[17][15]).isEqualTo('|');
    }
    
    @Test
    public void drawsMainDiagonalLine() throws Exception {
        asciiCanvas.drawLine(new Point(0, 0), new Point(1, 1));
        assertThat(asciiCanvas.getDrawMatrix()[22][16]).isEqualTo('/');
        assertThat(asciiCanvas.getDrawMatrix()[23][17]).isEqualTo('/');
    }
    
    @Test
    public void drawsOffDiagonalLine() throws Exception {
        asciiCanvas.drawLine(new Point(0, 0), new Point(-1, 1));
        assertThat(asciiCanvas.getDrawMatrix()[22][14]).isEqualTo('\\');
        assertThat(asciiCanvas.getDrawMatrix()[23][13]).isEqualTo('\\');
    }
}
