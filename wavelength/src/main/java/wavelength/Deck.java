package wavelength;

import java.io.*;
import java.util.*;

public class Deck {
    
    File file;

    List<Category> deck;

    public Deck(){
        deck = new ArrayList<>();
        populate();
        shuffle();
    }

    private void populate(){
        InputStream input = getClass().getResourceAsStream("/categories.txt");
        try (BufferedReader br = new BufferedReader(new InputStreamReader(input))) {
            String line = br.readLine();
            while (line != null){
                // indices
                int commaIndex = line.indexOf(",");
                int endIndex = line.length();

                // ends of spectrum
                String left = line.substring(0, commaIndex);
                String right = line.substring(commaIndex + 2, endIndex); // ", "

                // add Category to deck
                Category category = new Category(left, right);
                deck.add(category);

                // loop
                line = br.readLine();
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    private void shuffle(){
        Collections.shuffle(deck);
    }

    public Category draw(){
        Category category = deck.get(0);
        deck.remove(0);
        return category;
    }

}
