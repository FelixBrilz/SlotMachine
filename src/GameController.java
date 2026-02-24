import java.util.Scanner;

public class GameController {
    Rank ass = new Rank("Ass", "A", 14);
    Rank koenig = new Rank("Koenig", "K", 13);
    Rank dame = new Rank("Dame", "Q", 12);
    Rank bube = new Rank("Bube", "J", 11);
    Rank zehn = new Rank("Zehn", "X", 10);
    Rank neun = new Rank("Neun", "9", 9);
    Rank acht = new Rank("Acht", "8", 8);
    Rank sieben = new Rank("Sieben", "7", 7);
    Rank sechs = new Rank("Sechs", "6", 6);
    Rank fuenf = new Rank("Fuenf", "5", 5);

    Rank pik = new Rank("Pik", "♠", 4);
    Rank herz = new Rank("Herz", "♥", 3);
    Rank karo = new Rank("Karo", "♦", 2);
    Rank kreuz = new Rank("Kreuz", "♣", 1);

    private int streake = 0;
    private int cost = 6;
    private int sum = 0;
    private int gold = 100;
    Rank[] ranks = {ass, koenig, dame, bube, zehn, neun, acht, sieben, sechs, fuenf, pik, herz, karo, kreuz};
    Rank[][] drum = new Rank[3][3];

    public void startGame() {

        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("Do you want to start the game? [Yes/No?]");
            showSlot();
            String ans = sc.nextLine().trim();

            if (ans.equalsIgnoreCase("yes") || ans.equalsIgnoreCase("y")) {
                spin();
                break;
            } else if (ans.equalsIgnoreCase("no") || ans.equalsIgnoreCase("n")) {
                exitGame();
                break;
            } else {
                System.out.println("Invalid input! Please enter Yes or No.");
            }
        }

    }

    public void spin() {
        Scanner sc = new Scanner(System.in);
        while (this.gold > 0) {
            System.out.println("Spin with [Enter] ! | Leave with [Leave] | Gold: " + this.gold + " ¢");
            String ans = sc.nextLine().trim();

            if (ans.isEmpty()) {
                this.gold -= this.cost;
                showFace();
            } else if (ans.equalsIgnoreCase("Leave") || ans.equalsIgnoreCase("l")) {
                showStreake();
                startGame();
                break;
            } else {
                System.out.println("Invalid input!");
            }
        }
        System.out.println("You lost the game!");
        showStreake();
        startGame();
    }


    public void showFace() {
        fillDrum();
        System.out.println(
                "          ___        \n" +
                        "  _______( 0 )_______\n" +
                        "  +-----+-----+-----+      ()\n" +
                        " /  " + drum[0][0].getSymbole() + "  /   " + drum[0][1].getSymbole() + "   \\  " + drum[0][2].getSymbole() + "  \\     |\n" +
                        "|  " + drum[1][0].getSymbole() + "  |    " + drum[1][1].getSymbole() + "    |  " + drum[1][2].getSymbole() + "  |   |\n" +
                        " \\  " + drum[2][0].getSymbole() + "  \\   " + drum[2][1].getSymbole() + "   /  " + drum[2][2].getSymbole() + "  /===+\n" +
                        "  +-----+-----+-----+\n" +
                        "  |_________________|");
        checkRes();
    }


    public void fillDrum() {
        for (int j = 0; j < drum[1].length; j++) {
            int randomIndex = (int) (Math.random() * ranks.length);

            drum[1][j] = ranks[randomIndex];

            int randomIndexDown = randomIndex - 1;
            if (randomIndexDown < 0) {
                randomIndexDown = ranks.length - 1;
            }

            drum[2][j] = ranks[randomIndexDown];

            int randomIndexUp = randomIndex + 1;
            if (randomIndexUp >= ranks.length) {
                randomIndexUp = 0;
            }
            drum[0][j] = ranks[randomIndexUp];
        }
    }

    public void checkRes() {
        // Horizontale Prüfung
        for (int i = 0; i < 3; i++) {
            Rank a = drum[i][0];
            Rank b = drum[i][1];
            Rank c = drum[i][2];

            if (a.equals(b) && b.equals(c)) {
                showWin(a, true);
            } else if (a.equals(b)) {
                showWin(a, false);
            } else if (b.equals(c)) {
                showWin(a, false);
            }
        }

        // Diagonale Prüfung: Hauptdiagonale
        Rank d1 = drum[0][0];
        Rank d2 = drum[1][1];
        Rank d3 = drum[2][2];

        if (d1.equals(d2) && d2.equals(d3)) {
            showWin(d1, true);
        } else if (d1.equals(d2)) {
            showWin(d1, false);
        } else if (d2.equals(d3)) {
            showWin(d2, false);
        }

        // Diagonale Prüfung: Nebendiagonale
        Rank a1 = drum[0][2];
        Rank a2 = drum[1][1];
        Rank a3 = drum[2][0];

        if (a1.equals(a2) && a2.equals(a3)) {
            showWin(a1, true);
        } else if (a1.equals(a2)) {
            showWin(a1, false);
        } else if (a2.equals(a3)) {
            showWin(a2, false);
        }
        showRes();
        this.streake++;
    }

    public void showWin(Rank val, boolean jackPot) {
        if (jackPot) {
            this.sum += val.getValue() * 2;
        } else {
            this.sum += val.getValue();
        }
    }

    public void showRes() {
        System.out.println("\n" + "You won +" + this.sum + "¢ !" +"\n");
        this.gold += this.sum;
        this.sum = 0;
    }

    public void exitGame() {
        System.out.println("You left the game!");
        startGame();
    }

    public void showSlot(){
        System.out.println("          ___        \n" +
                        "  _______( 0 )_______\n" +
                        "  +-----+-----+-----+      ()\n" +
                        " /  ?  /   ?   \\  ?  \\     |\n" +
                        "|  ?  |    ?    |  ?  |   |\n" +
                        " \\  ?  \\   ?   /  ?  /===+\n" +
                        "  +-----+-----+-----+\n" +
                        "  |_________________|");
    }

    public void showStreake(){
        System.out.println("Final Stats:   Gold: " + this.gold + " | Streak: " + this.streake + "\n");
    }

}
