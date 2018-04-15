public class Pencil {

    int maxPointDurability; //should not change after initial assignment
    int pointDurability; //current durability
    int eraserDurability; //current durability
    int length;


    public Pencil(int pointDurability, int eraserDurability, int length) {
        this.maxPointDurability = this.pointDurability = pointDurability > 0 ? pointDurability : 0;
        this.eraserDurability = eraserDurability > 0 ? eraserDurability : 0;
        this.length = length > 0 ? length : 0;
    }

    public void write(String text, Paper paper){
        String remainingText = text;
        while (remainingText.length() > 0){
            char thisChar = remainingText.charAt(0);
            paper.appendChar(thisChar);
            remainingText = remainingText.substring(1);
        }
    }

}
