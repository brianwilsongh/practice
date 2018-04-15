import exceptions.ShortPencilException;

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

    public void write(String text, Paper paper) {
        String remainingText = text;
        while (remainingText.length() > 0) {
            char thisChar = remainingText.charAt(0);
            if (thisChar != ' ' && thisChar != '\n') {
                if (Character.isUpperCase(thisChar)) {
                    this.pointDurability -= 2; //2 durability for uppercase letter
                } else {
                    this.pointDurability -= 1; //1 for lower case
                }
                if (this.pointDurability >= 0) {
                    paper.appendChar(thisChar); //write the actual char if there was enough durability
                } else {
                    paper.appendChar(' ');
                }
            } else {
                paper.appendChar(thisChar);
            }
            remainingText = remainingText.substring(1); //assign to new string cutting off 1st char
        }


        if (this.pointDurability < 0) {
            this.pointDurability = 0; //prevent negative value for durability
        }
    }

    public void erase(String str, Paper paper){
        int endIdx = paper.lastIndexOfSubstring(str);
        int startIdx = endIdx - str.length();
        if (endIdx != -1){ //if string is a substring in text of this paper
            for (int idx = endIdx; idx > startIdx; idx--){
                this.eraserDurability -= paper.eraseAt(idx);
            }
        }
    }

    public void sharpen() throws ShortPencilException {
        if (this.length > 0){
            this.length--;
            this.pointDurability = this.maxPointDurability;
        } else {
            throw new ShortPencilException("Tried to sharpen a pencil with length less than one!");
        }
    }

}
