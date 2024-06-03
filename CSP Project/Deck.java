import java.util.*;
import java.awt.*;
public class Deck {
    private final ArrayList<CrazyCard> deck=new ArrayList<>();
    public String [] types = {"Hearts","Spades","Diamonds","Clubs"};
    public String[] values = {"0","1","2","3","4","5","6","7","8","9","10","J","Q","K","A"};    
    public Deck() {
        for (int i = 0; i < types.length-1; i++) {
            for (int j = 0; j < values.length-2; j++) {
                deck.add(new CrazyCard(types[i], values[j]));
            }
        }
    
    }
    public void shuffle(){
    Random rand = new Random();
    for (int i = deck.size()-1;i>0;i--){
        int j = rand.nextInt(i+1);
        CrazyCard temp = deck.get(i);
        deck.set(i,deck.get(j));
        deck.set(j,temp);
    }
}
    public CrazyCard draw(){
        return deck.remove(deck.size() - 1);    
    }
    public boolean isEmpty() {
        return deck.isEmpty();
    }

    public void drawDeck(double x, double y) {
        StdDraw.setPenColor(Color.GRAY);
        StdDraw.filledRectangle(x, y, 0.04, 0.06);
        StdDraw.setPenColor(Color.WHITE);
        StdDraw.text(x, y, "Deck");
    }
}


