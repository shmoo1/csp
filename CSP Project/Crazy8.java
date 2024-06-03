
import java.awt.*;
import java.util.*;
public class Crazy8 {
    public Deck deck = new Deck();
    public ArrayList<Player> players = new ArrayList<>();
    public ArrayList<CrazyCard> discardPile = new ArrayList<>();
    public ArrayList<CrazyCard> hand1 = new ArrayList<>();
    public ArrayList<CrazyCard> hand2 = new ArrayList<>();
    public int currentPlayer = 0; 
    public String currentvalue; 
    public String currenttype; 
    public boolean mousePressedLastFrame = false; 
    public Crazy8() { 
        players.add(new Player("Ms. Marotta", hand1));
        players.add(new Player("Hawthorne Warren", hand2));
        startGame();
    }
    public void startGame() { 
        deck.shuffle();
        for (Player player : players) {
            for (int i = 0; i < 7; i++) {
                player.drawCard(deck);
            }
        }
        CrazyCard firstcard = deck.draw();
        discardPile.add(firstcard);
        currenttype = firstcard.getType();
        currentvalue = firstcard.getValue();
    }

    public Player getPlayer() { 
        return players.get(currentPlayer);
    }
    public void changePlayer() { 
        if (currentPlayer == 0) {
            currentPlayer=1;}
        else {currentPlayer=0;};
    }
    public boolean canYouPlayThisCard(CrazyCard card) {
        return card.getType().equals(currenttype) || card.getValue().equals(currentvalue);
    }

    public void playerGoes() {
        Player player = getPlayer();
        StdDraw.clear();
        CreateBoard();
        int choice = -1;
        int temp=0;
        while (choice == -1) {
            if (StdDraw.isMousePressed() && !mousePressedLastFrame) {
                double mouseX = StdDraw.mouseX();
                double mouseY = StdDraw.mouseY();
                choice = whatCardIsMouseAt(mouseX, mouseY, player);
                if (choice == -2) { 
                    if (!deck.isEmpty()&&temp==0) {
                        player.drawCard(deck);
                        temp=1;
                        changePlayer();
                        dontCheat();
                        StdDraw.pause(300);
                        return;
                    }
                } 
                if (choice >= 0 && choice < player.getHand().size()) {
                    CrazyCard chosenCard = player.getHand().get(choice);
                    if (canYouPlayThisCard(chosenCard)||chosenCard.getValue()=="8") {

                        player.playCard(chosenCard);
                        discardPile.add(chosenCard);
                        currenttype = chosenCard.getType();
                        currentvalue = chosenCard.getValue();
                        StdDraw.pause(200);
                        changePlayer();
                        if (currentvalue == "8"){
                            currenttype = getNewType();
                            chosenCard.setType(currenttype);
                            discardPile.add(chosenCard);
                        }
                        dontCheat();
                        return;      
                }
                else {
                    choice = -1; 
                }
            }
        }
    }
            mousePressedLastFrame = StdDraw.isMousePressed();
            StdDraw.pause(200);
    }
    
    public String getNewType() { 
        String type = "null";
        int choice1 = -1;
        while (choice1 == -1) {
            if (StdDraw.isMousePressed() && !mousePressedLastFrame) {
                double mouseX = StdDraw.mouseX();
                double mouseY = StdDraw.mouseY();
                if (mouseY >= 0.74 && mouseY < 0.86 && mouseX >= 0.06 && mouseX < 0.14) {
                    choice1 = 0;}
                if (mouseY >= 0.74 && mouseY < 0.86 && mouseX >= 0.26 && mouseX < 0.34) {
                    choice1 = 1;}   
                if (mouseY >= 0.74 && mouseY < 0.86 && mouseX >= 0.46 && mouseX < 0.54) {
                    choice1 = 2;}
                if (mouseY >= 0.74 && mouseY < 0.86 && mouseX >= 0.66 && mouseX < 0.74) {
                    choice1 = 3;}
             }
            }
        if (choice1 == 0) {type = "Diamonds";}
        if (choice1 == 1) {type = "Hearts";}
        if (choice1 == 2) {type = "Clubs";}
        if (choice1 == 3) {type = "Spades";}
        return type;}

    public void dontCheat(){
        StdDraw.clear();
        Font font9 = new Font("Arial", Font.BOLD, 30);
        StdDraw.setFont(font9);
        StdDraw.text(.6,.5, "Pass the device to the next person or you are a cheater");
        StdDraw.show();
        StdDraw.pause(3000);
        StdDraw.clear();
    }
    public int whatCardIsMouseAt(double mouseX, double mouseY, Player player) { 
        for (int i = 0; i <= player.getHand().size(); i++) {
            double cardX = 0.1 + 0.1 * i;
            if (mouseX >= cardX - 0.04 && mouseX <= cardX + 0.04 && mouseY >= 0.13 && mouseY < 0.27) {
                return i;
            }
        }
        if (mouseX > 0.86 && mouseX < 0.94 && mouseY > 0.54 && mouseY < 0.66) {
            return -2; 
        }
        return -1;
    }
    public boolean isGameOver() { 
        if (deck.isEmpty()) {
            return true;
        }

        for (Player player : players) {
            if (player.getHand().size() == 0) {
                return true;
            }
        }
        return false;
    }
    public void CreateBoard() { 
        Font font5 = new Font("Arial", Font.BOLD, 20);
        StdDraw.setFont(font5);
        if (currentPlayer==0){
            StdDraw.setPenColor(Color.RED);
            StdDraw.text(0.5,0.05,"Ms. Marotta");
        }
        else{
            StdDraw.setPenColor(Color.BLUE);
            StdDraw.text(0.5,0.05,"Hawthorne Warren");
        }
        StdDraw.setPenColor(Color.RED);
        Font font = new Font("Arial", Font.BOLD, 50);
        StdDraw.setFont(font);
        StdDraw.text(0.5, 0.95, "Crazy 8 Game!!");
        Font font2 = new Font("Arial", Font.BOLD, 10);
        StdDraw.setFont(font2);
        StdDraw.setPenColor(Color.RED);
        StdDraw.filledRectangle(0.1, 0.8, 0.04, 0.06);
        StdDraw.setPenColor(Color.black);
        StdDraw.text(0.1, 0.8, "Diamonds");
        StdDraw.setPenColor(Color.RED);
        StdDraw.filledRectangle(0.3, 0.8, 0.04, 0.06);
        StdDraw.setPenColor(Color.BLACK);
        StdDraw.text(0.3, 0.8, "Hearts");
        StdDraw.setPenColor(Color.BLACK);
        StdDraw.filledRectangle(0.5, 0.8, 0.04, 0.06);
        StdDraw.setPenColor(Color.WHITE);
        StdDraw.text(0.5, 0.8, "Clubs");
        StdDraw.setPenColor(Color.BLACK);
        StdDraw.filledRectangle(0.7, 0.8, 0.04, 0.06);
        StdDraw.setPenColor(Color.WHITE);
        StdDraw.text(0.7, 0.8, "Spades");
        deck.drawDeck(0.9, 0.6);
        drawDiscardPile(0.7, 0.6);
        Player player = getPlayer();
        for (int i = 0; i < player.getHand().size(); i++) {
            double x = 0.1 + 0.1 * i;
            drawCard(x, 0.15, player.getHand().get(i));
        }
        StdDraw.show();
    }
    public void drawCard(double x, double y, CrazyCard card) { 
        StdDraw.setPenColor(Color.BLACK);
        StdDraw.filledRectangle(x, y, 0.04, 0.06);
        StdDraw.setPenColor(Color.WHITE);
        StdDraw.text(x,y-0.03,card.getType());
        StdDraw.setPenColor(Color.GRAY);
        StdDraw.text(x, y, card.getValue());
    }
    public void drawDiscardPile(double x, double y) { 
        if (!discardPile.isEmpty()) {
            drawCard(x, y, discardPile.get(discardPile.size() - 1));
        }
    }
    public void gameTime() {
        while (!isGameOver()) {
            playerGoes();
        }
        endGame();
    }
    public void endGame() {
        StdDraw.clear();
        StdDraw.setPenColor(Color.BLACK);
        Font font = new Font("Arial", Font.BOLD, 50);
        StdDraw.setFont(font);
        StdDraw.text(0.5, 0.5, "Game Over!");
        StdDraw.show();
    }
    public static void main(String[] args) {
        StdDraw.setCanvasSize(800, 600);
        StdDraw.setXscale(0, 1.2);
        StdDraw.setYscale(0, 1);
        StdDraw.enableDoubleBuffering();
        Crazy8 crazy = new Crazy8();
        crazy.gameTime();
    }
} 