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

    public void eraseEdit(String eraseText, Paper paper, String overwriteText){
        int endIdx = paper.lastIndexOfSubstring(eraseText);
        int startIdx = endIdx - eraseText.length();
        if (endIdx != -1){ //if string is a substring in text of this paper
            for (int eraseIdx = endIdx; eraseIdx > startIdx; eraseIdx--){
                if (eraserDurability < 1){ //no more eraser means no more erasing
                    break;
                }
                this.eraserDurability -= paper.eraseAt(eraseIdx);
            }
        }
        if (overwriteText != null && endIdx >= 0){
            int currentEditIdx = startIdx + 1; //start where the erasing ended
            while (overwriteText.length() > 0) {
                char thisChar = overwriteText.charAt(0);
                if (thisChar != ' ' && thisChar != '\n') {
                    if (Character.isUpperCase(thisChar)) {
                        this.pointDurability -= 2; //2 durability for uppercase letter
                    } else {
                        this.pointDurability -= 1; //1 for lower case
                    }
                    if (this.pointDurability >= 0) {
                        paper.writeCharAt(thisChar, currentEditIdx); //write the actual char if there was enough durability
                    } else {
//                        paper.writeCharAt(' ', currentEditIdx);
                    }
                } else {
//                    paper.writeCharAt(thisChar, currentEditIdx);
                }
                overwriteText = overwriteText.substring(1); //assign to new string cutting off 1st char
                currentEditIdx++;
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
