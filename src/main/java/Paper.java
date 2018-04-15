public class Paper {

    private String text;

    /** GETTER/SETTER ARE FOR TESTING ONLY **/
    public String getText(){ return this.text; }
    public void setText(String text){ this.text = text; }

    public Paper() {
        this.text = "";
    }

    public Paper(String initial){
        this.text = initial;
    }

    public void appendChar(char c){
        text = new StringBuilder(text).append(c).toString();
    }

    public int lastIndexOfSubstring(String substring){ //get ending index of last instance of string
        int lastIdx = text.lastIndexOf(substring);
        if (lastIdx == -1 || substring.length() == 0){
            return -1; //return -1 if substring doesn't occur or substring is blank string
        }
        return lastIdx + substring.length() - 1;
    }

    public void eraseAt(int idx){
        StringBuilder sb = new StringBuilder(text);
        sb.setCharAt(idx, ' ');
        text = sb.toString();
    }
}
