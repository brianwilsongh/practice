
public class App {

    public static void main(String[] args){
        Pencil pencil = new Pencil(1000, 1000, 1000);
        String frost = "Some say the world will end in fire,\n" +
                "Some say in ice.\n" +
                "From what I've tasted of desire\n" +
                "I hold with those who favor fire.\n" +
                "But if it had to perish twice,\n" +
                "I think I know enough of hate\n" +
                "To say that for destruction ice\n" +
                "Is also great\n" +
                "And would suffice. \n" +
                "\n" +
                "-- Robert Frost";
        Paper paper = new Paper();
        pencil.write(frost, paper);
        paper.readContents();
    }
}
