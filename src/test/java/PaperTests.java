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


    @Test
    public void paperStateChangesAfterAddingChars(){

    }
}
