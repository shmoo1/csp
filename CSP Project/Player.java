import java.util.*;
public class Player {
    public ArrayList<CrazyCard> hand = new ArrayList<CrazyCard>();
    public String name;
    public Player(String name, ArrayList<CrazyCard> hand){
        this.name = name;
        this.hand = hand;
    }
    public String getName(){
        return name;
    }
    public ArrayList<CrazyCard> getHand(){
        return hand;
    }
    public void drawCard(Deck deck){
        hand.add(deck.draw());
    }
    public void playCard(CrazyCard card){
        hand.remove(card);
    }
}