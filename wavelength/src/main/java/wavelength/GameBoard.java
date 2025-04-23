package wavelength;

import java.util.*;

public class GameBoard {

    private Deck deck;

    private int team1Points;
    private int team2Points;

    private Category activeCategory;

    private int targetInt;

    private String clueString;

    private int guessInt;

    public GameBoard(){
        team1Points = 0;
        team2Points = 0;
    }

    public void playGame(){
        deck = new Deck();
        // TODO gameplay loop
        Scanner input = new Scanner(System.in);

        while(!gameOver()){
            // category
            System.out.println("Select Category:");
            pickCategory(input);
            System.out.println("\nCategory is: " + activeCategory);
            
            // target
            spinTarget();
            System.out.println("\nTarget is: " + targetInt + "/100");

            // clue
            takeInClue(input);
            System.out.println("\nOn a scale of " + activeCategory.min.toLowerCase() + " to " + activeCategory.max.toLowerCase() + ",");
            System.out.println("where is *" + clueString + "* out of 100?");
            takeInGuess(input);
            if (Math.abs(targetInt - guessInt) < 40){
                team1Points += 2;
            }
            displayPoints();
        }
    }

    /**
     * Gets Team 1's points
     * 
     * @return Point count for Team 1
     */
    public int getTeam1Points(){
        return team1Points;
    }

    /**
     * Gets Team 2's points
     * 
     * @return Point count for Team 2
     */
    public int getTeam2Points(){
        return team2Points;
    }

    private void pickCategory(Scanner scanner){
        Category category1 = deck.draw();
        Category category2 = deck.draw();
        System.out.println("1: " + category1);
        System.out.println("2: " + category2);
        int input = 0;
        while(input != 1 && input != 2){ 
            System.out.println("1 or 2?");
            input = scanner.nextInt();
            activeCategory = (input == 1) ? category1 : category2;
        }
    }

    public void spinTarget(){
        Random rand = new Random();
        targetInt = rand.nextInt(101);
    }

    public void takeInClue(Scanner scanner){
        clueString = "bananas";
    }

    public void takeInGuess(Scanner scanner){
        guessInt = 50;
    }

    public void displayPoints(){
        System.out.println("Team 1 Points: " + team1Points);
        System.out.println("Team 2 Points: " + team2Points);
    }

    public void revealWavelength(){
        // TODO: print out range
    }

    private boolean gameOver(){
        if (team1Points > 9 || team2Points > 9){
            return true;
        }
        return false;
    }
}

/* Thoughts
 *  Round 1
 *      Category chosen/revealed
 *      Spinner spun (hidden)
 *      Team 1 player 1 sees number (0-100)
 *      Team 1 player one says (types?) in their object
 *      Team 1 other players guess on range
 *      Team 2 players guess higher or lower
 *      Spinner revealed
 *      Points awarded
 *  Round 2
 *      Category chosen/revealed
 *      Spinner spun (hidden)
 *      Team 2 player 2 sees number (0-100)
 *      Team 2 player one says (types?) in their object
 *      Team 2 other players guess on range
 *      Team 1 players guess higher or lower
 *      Spinner revealed
 *      Points awarded
 *  Points
 *      Guessing team: 4, 3, 2, 1, 0
 *      Other team: 1, 0
 */
