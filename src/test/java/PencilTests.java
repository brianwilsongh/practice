import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;
import java.util.Random;

public class PencilTests {

    Pencil defaultPencil;
    Paper defaultPaper;

    Random rand = new Random(); //to generate random numbers

    @Before
    public void setup(){
        this.defaultPaper = new Paper();
        this.defaultPencil = new Pencil(1000, 1000, 1000);
    }

    @Test
    public void newPencilObjectInitializesProperly(){
        int randomPointDurability = rand.nextInt(1000) + 1; //random durability 1-1000
        int randomEraserDurability = rand.nextInt(1000) + 1; //random eraser durability 1-1000
        int randomLength = rand.nextInt(1000) + 1; //random length 1-1000
        Pencil customPencil = new Pencil(randomPointDurability, randomEraserDurability, randomLength);
        assertEquals(randomPointDurability, customPencil.maxPointDurability);
        assertEquals(randomEraserDurability, customPencil.eraserDurability);
        assertEquals(randomLength, customPencil.length);
        assertEquals(randomPointDurability, customPencil.pointDurability);

        //negative property values passed into pencil constructor should default to zero (-30 durability makes no sense)
        Pencil negativePencil = new Pencil(-rand.nextInt(100) - 1, -rand.nextInt(100) - 1, -rand.nextInt(100) - 1);
        assertEquals(0, negativePencil.maxPointDurability);
        assertEquals(0, negativePencil.eraserDurability);
        assertEquals(0, negativePencil.length);
        assertEquals(0, negativePencil.pointDurability);
    }

    // tests for feature: WRITE
    @Test
    public void pencilCanWriteToPaper(){
        String test = "Domino's Pizza";
        defaultPencil.write(test, defaultPaper);
        assertEquals(test, defaultPaper.getText());
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
