import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

public class PaperTests {

    Paper defaultPaper;

    @Before
    public void setup(){
        this.defaultPaper = new Paper();
    }

    @Test
    public void newPaperObjectInitializesProperty(){
        String initString = " paper should init with this ";
        Paper p = new Paper(initString);
        assertEquals(initString, p.getText());
        assertEquals("", defaultPaper.getText());
    }

    // tests for feature: WRITE

    @Test
    public void paperTextChangesAfterAppendingChars(){
        defaultPaper.appendChar('B');
        assertEquals("B", defaultPaper.getText());
        defaultPaper.appendChar('r');
        assertEquals("Br", defaultPaper.getText());
        defaultPaper.appendChar(' ');
        assertEquals("Br ", defaultPaper.getText());
        defaultPaper.appendChar('\n');
        assertEquals("Br \n", defaultPaper.getText());
    }

    @Test
    public void paperCanWriteAtIndexWithCharOrSymbolWithCollision(){ //paper writes "@" when trying to overwrite a char
        defaultPaper.setText("This is Sparta");
        defaultPaper.writeCharAt('a', 13);
        assertEquals("This is Spart@", defaultPaper.getText());
        defaultPaper.writeCharAt('M', 4);
        assertEquals("ThisMis Spart@", defaultPaper.getText());
    }

    // tests for feature: ERASE

    @Test
    public void paperCanEraseAtAnyTextPositionWithinBounds(){
        defaultPaper.setText("Keanu Reeves Whoa");
        defaultPaper.eraseAt(2);
        assertEquals("Ke nu Reeves Whoa", defaultPaper.getText());
        defaultPaper.eraseAt(5);
        defaultPaper.eraseAt(6);
        assertEquals("Ke nu  eeves Whoa", defaultPaper.getText());
    }

    @Test
    public void paperReturnsEraserDegradationCostOfErase(){
        defaultPaper.setText("A B c\n");
        assertEquals(0, defaultPaper.eraseAt(1));
        assertEquals(0, defaultPaper.eraseAt(5));
        assertEquals(1, defaultPaper.eraseAt(4));
        assertEquals(1, defaultPaper.eraseAt(2));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void paperCallingEraseOutOfBoundsThrowsIndexOOBException(){ //assumes we DON'T want this exception caught
        defaultPaper.setText("Big Mac");
        defaultPaper.eraseAt(12);
    }

    @Test
    public void paperGetsLastIndexOfLastInstanceOfSubstringInText(){
        defaultPaper.setText("abcabc abc");
        assertEquals(9, defaultPaper.lastIndexOfSubstring("abc"));
        assertEquals(-1, defaultPaper.lastIndexOfSubstring("z"));
        assertEquals(-1, defaultPaper.lastIndexOfSubstring(""));
        defaultPaper.setText("dog\ndogcat dogcat");
        assertEquals(13, defaultPaper.lastIndexOfSubstring("dog"));
    }
}
