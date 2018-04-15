import exceptions.ShortPencilException;
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
        p.write(test, defaultPaper);
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

    @Test(expected = NullPointerException.class)
    public void pencilWriteMustThrowErrorIfPaperNull(){ //assumes we DON'T want this exception caught
        defaultPencil.write("into the void", null);
    }

    // tests for feature: SHARPEN
    @Test
    public void pencilRegainsPointDurabilityAfterSuccessfulSharpen(){
        Pencil p = new Pencil(100, 100, 100);
        p.write("I'm only here to deplete your graphite\n\n", defaultPaper);
        p.sharpen();
        assertEquals(100, p.pointDurability);
    }

    @Test(expected = ShortPencilException.class)
    public void pencilCannotSharpenWithZeroLength(){
        Pencil p = new Pencil(10, 10, 0);
        p.sharpen();
        assertEquals(0, p.length);
    }

    // tests for feature: ERASE

    @Test
    public void pencilCanEraseTextProperly(){
        String test = "How much wood would a woodchuck chuck if a woodchuck could chuck wood?";
        defaultPaper.setText(test);
        defaultPencil.eraseEdit("chuck", defaultPaper, null);
        assertEquals("How much wood would a woodchuck chuck if a woodchuck could       wood?", defaultPaper.getText());
        defaultPencil.eraseEdit("How much", defaultPaper, null);
        assertEquals("         wood would a woodchuck chuck if a woodchuck could       wood?", defaultPaper.getText());
    }

    @Test
    public void pencilEraserDurabilityDecreasesWhenErasingText(){ //does not include whitespace
        Pencil p = new Pencil(100, 100, 100);
        String test = "abcdefg  xy  z";
        defaultPaper.setText(test);
        p.eraseEdit("efg", defaultPaper, null);
        assertEquals(97, p.eraserDurability);
        p.eraseEdit("xy  z", defaultPaper, null);
        assertEquals(94, p.eraserDurability);
    }

    @Test
    public void pencilErasingWillFailIfEraserDurabilityHitsZero(){
        Pencil p = new Pencil(100, 3, 100);
        String test = "Buffalo Bill";
        defaultPaper.setText(test);
        p.eraseEdit("Bill", defaultPaper, null);
        assertEquals("Buffalo B   ", defaultPaper.getText());
    }


    //tests for feature: EDIT
    @Test
    public void pencilCanEraseThenEdit(){
        String test = "An apple a day keeps the doctor away";
        defaultPaper.setText(test);
        defaultPencil.eraseEdit("apple", defaultPaper, "onion");
        assertEquals("An onion a day keeps the doctor away", defaultPaper.getText());
    }

    @Test
    public void pencilEditOverwritesExistingCharactersWithSymbol(){
        String test = "An apple\na day keeps the doctor away";
        defaultPaper.setText(test);
        defaultPencil.eraseEdit("apple", defaultPaper, "artichoke");
        assertEquals("An artich@k@ay keeps the doctor away", defaultPaper.getText());
    }

    @Test
    public void pencilCannotEditWithoutPointDurability(){
        Pencil p = new Pencil(3, 100, 100);
        String test = "One garlic a day keeps Dracula away";
        defaultPaper.setText(test);
        p.eraseEdit("garlic", defaultPaper, "ToMaTo");
        assertEquals("One To     a day keeps Dracula away", defaultPaper.getText());
    }





}
