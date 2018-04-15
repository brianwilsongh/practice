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
        String test = "Domino's Pizza\na";
        defaultPencil.write(test, defaultPaper);
        assertEquals(test, defaultPaper.getText());
    }


    // tests for feature: POINT DEGRADATION

    @Test
    public void pencilWithZeroPointDurabilityWritesSpaces(){
        Pencil zeroDurabilityPencil = new Pencil(0, 10, 10);
        zeroDurabilityPencil.write("spaces only", defaultPaper);
        assertEquals("           ", defaultPaper.getText());
        assertEquals(0, zeroDurabilityPencil.pointDurability);
    }

    @Test
    public void pencilWithPositivePointDurabilityWritesToPaperAndLosesDurability(){
        Pencil p = new Pencil(100, 100, 100);
        String test = "pencil p\n\n encil";
        defaultPencil.write(test, defaultPaper);
        assertEquals(test, defaultPaper.getText());
        assertEquals(true, (p.pointDurability < 100));
    }

    @Test
    public void pencilLosesTwoDurabilityForUppercaseAndOneForLowercase(){
        Pencil p = new Pencil(10, 10, 10);
        p.write("a", defaultPaper);
        assertEquals(9, p.pointDurability);
        p.write("A", defaultPaper);
        assertEquals(7, p.pointDurability);
        p.write("bb", defaultPaper);
        assertEquals(5, p.pointDurability);
        p.write("BB", defaultPaper);
        assertEquals(1, p.pointDurability);
    }

    @Test
    public void pencilWritesPartialMessageWhenDurabilityDepleted(){
        Pencil p = new Pencil(3, 10, 10);
        p.write("abcd", defaultPaper);
        assertEquals("abc ", defaultPaper.getText());
    }

    @Test
    public void pencilPointDurabilityUnchangedWhenWritingSpacesOrNewlines(){
        Pencil p = new Pencil(10, 10, 10);
        p.write("", defaultPaper);
        assertEquals(10, p.pointDurability);
        p.write("\n\n ", defaultPaper);
        assertEquals(10, p.pointDurability);
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
