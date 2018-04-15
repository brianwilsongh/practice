import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

public class PencilTests {

    Pencil defaultPencil;
    Paper defaultPaper;

    @Before
    public void setup(){
        this.defaultPaper = new Paper();
        this.defaultPencil = new Pencil();
    }

    @Test
    public void newPencilObjectInitializesProperly(){
        assertEquals(0, 0);
    }

    // tests for feature: WRITE
    @Test
    public void pencilCanWriteToPaper(){

    }


    // tests for feature: POINT DEGRADATION

    @Test
    public void pencilWithZeroPointDurabilityWritesSpaces(){

    }

    @Test
    public void pencilWithPositivePointDurabilityWritesToPaperAndLosesDurability(){

    }

    @Test
    public void pencilLosesTwoPointDurabilityForUppercase(){

    }

    @Test
    public void pencilLosesOnePointDurabilityForLowercase(){

    }

    @Test
    public void pencilWritesPartialMessageWhenDurabilityDepleted(){

    }

    @Test
    public void pencilPointDurabilityUnchangedWhenWritingSpacesOrNewlines(){

    }

    // tests for feature: SHARPEN
    @Test
    public void pencilRegainsPointDurabilityAfterSuccessfulSharpen(){

    }

    @Test
    public void pencilCannotSharpenWithZeroLength(){

    }

    // tests for feature: ERASE

    @Test
    public void pencilEraserDurabilityDecreasesWhenErasing(){

    }




}
