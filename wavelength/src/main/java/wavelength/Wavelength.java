package wavelength;

public class Wavelength {
    
    private GameBoard board;

    /**
     * Constructor
     */
    public Wavelength(){

    }
    
    /**
     * Main
     * 
     * @param args
     */
    public static void main(String[] args) {
        Wavelength wavelength = new Wavelength();
        wavelength.playGame();
    }

    private void playGame(){
        board = new GameBoard();
        board.playGameScanner();
    }   
}