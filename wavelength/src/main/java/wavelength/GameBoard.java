package wavelength;

import java.util.*;

public class GameBoard {

    private Team team1;
    private Team team2;

    private Team activeTeam;

    private Deck deck;

    private Category activeCategory;

    private int targetInt;

    private String clueString;

    private int guessInt;

    private boolean guessHigher;

    public GameBoard(){
        team1 = new Team("Team 1");
        team2 = new Team("Team 2");
        activeTeam = team1;
    }

    public void playGame(){
        deck = new Deck();
        
        Scanner input = new Scanner(System.in);

        // gameplay loop
        while(!gameOver()){
            // category
            System.out.println(activeTeam.getName() + ": Select Category:");
            pickCategory(input);
            System.out.println("\nCategory is: " + activeCategory);
            
            // target
            spinTarget();
            System.out.println("\nTarget is: " + targetInt + "/180 (" + (int) Math.floor(targetInt/1.8) + "%)");

            // clue
            takeInClue(input);
            input.reset();
            System.out.println("\nOn a scale of " + activeCategory.min.toLowerCase() + " to " + activeCategory.max.toLowerCase() + ",");
            System.out.println("where is *" + clueString + "* out of 180?");
            
            takeInGuess(input);
            input.reset();

            takeInOtherGuess(input);
            input.reset();
            
            evaluateRound(activeTeam, targetInt, guessInt, guessHigher);
            displayPoints();
            turnSwap();
        }
    }

    private void pickCategory(Scanner scanner){
        Category category1 = deck.draw();
        Category category2 = deck.draw();
        System.out.println("1: " + category1);
        System.out.println("2: " + category2);
        int input = 0;
        while(input != 1 && input != 2){ 
            System.out.println("1 or 2?: ");
            input = scanner.nextInt();
            activeCategory = (input == 1) ? category1 : category2;
        }
    }

    private void spinTarget(){
        Random rand = new Random();
        targetInt = rand.nextInt(181);
    }

    private void takeInClue(Scanner scanner){
        clueString = "";
        while (clueString.equals("")){
            System.out.print("Enter clue: ");
            clueString = scanner.nextLine();
        }
    }

    private void takeInGuess(Scanner scanner){
        guessInt = -1;
        while(guessInt == -1 ){ 
            guessInt = scanner.nextInt();
        }
    }

    private void takeInOtherGuess(Scanner scanner){
        String answer = "";
        while (answer.equals("")){
            System.out.print("Higher or Lower? [H/L]: ");
            answer = scanner.next();
            System.out.println(answer);
            switch(answer){
                case "H":
                guessHigher = true;
                break;
                case "L":
                guessHigher = false;
                break;
                default:
                answer = "";
            }
        }
    }

    private void displayPoints(){
        System.out.println(team1.getName() + " Points: " + team1.getPoints());
        System.out.println(team2.getName() + " Points: " + team2.getPoints() + "\n");
    }

    private void evaluateRound(Team team, int target, int guess, boolean otherGuess){
        // 7 degrees per zone
        // 35 degrees for scoring points
        // 18 degrees is 10
        // 111111122222223333333444 4 444333333322222221111111

        int diff = Math.abs(targetInt - guessInt);
        if (diff <= 4){
            team.addPoints(4);
        }
        else if (diff <= 11){
            team.addPoints(3);
        }
        else if (diff <= 18){
            team.addPoints(2);
        }
        else if (diff <= 25){
            team.addPoints(1);
        }

        boolean higher = (target > guess);
        Team otherTeam = (team == team1) ? team2 : team1;
        if (higher == guessHigher){
            otherTeam.addPoints(1);
        }
    }

    public void revealWavelength(){
        // TODO: print out range
    }

    private void turnSwap(){
        activeTeam = (activeTeam == team1) ? team2 : team1;
    }

    private boolean gameOver(){
        if (team1.getPoints() > 9 || team2.getPoints() > 9){
            return true;
        }
        return false;
    }
}

/* Thoughts
 *  Round 1
 *      Category chosen/revealed
 *      Spinner spun (hidden)
 *      Team 1 player 1 sees number (0-180)
 *      Team 1 player one says (types?) in their object
 *      Team 1 other players guess on range
 *      Team 2 players guess higher or lower
 *      Spinner revealed
 *      Points awarded
 *  Round 2
 *      Category chosen/revealed
 *      Spinner spun (hidden)
 *      Team 2 player 2 sees number (0-180)
 *      Team 2 player one says (types?) in their object
 *      Team 2 other players guess on range
 *      Team 1 players guess higher or lower
 *      Spinner revealed
 *      Points awarded
 *  Points
 *      Guessing team: 4, 3, 2, 1, 0
 *      Other team: 1, 0
 */
