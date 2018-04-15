public class Paper {

    private String text;

    /** GETTER/SETTER ARE FOR TESTING ONLY **/
    public String getText(){
        return this.text;
    }
    public void setText(String text){
        this.text = text;
    }

    public Paper() {
        this.text = "";
    }

    public Paper(String initial){
        this.text = initial;
    }

    public void appendChar(char c){
        text = new StringBuilder(text).append(c).toString();
    }

    public void eraseAt(int idx){
        StringBuilder sb = new StringBuilder(text);
        sb.setCharAt(idx, ' ');
        text = sb.toString();
    }
}
