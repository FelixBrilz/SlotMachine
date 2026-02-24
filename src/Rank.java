public class Rank {
    private String name;
    private String symbole;
    private int value;

    public Rank(String name, String symbole, int value){
        this.name = name;
        this.symbole = symbole;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public String getSymbole() {
        return symbole;
    }

    public int getValue() {
        return value;
    }
}
