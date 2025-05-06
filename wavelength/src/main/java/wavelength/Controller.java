package wavelength;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class Controller {

    // model
    private GameBoard game;

    /// FXML

    @FXML
    private BorderPane borderPane;
    private double borderPaneWidth;

    @FXML
    private Pane draggableWindow;
    @FXML
    private Label dragHandle;

    @FXML
    private Slider answerSlider;

    @FXML
    private Slider guessSlider;

    @FXML
    private Label team1Points;

    @FXML
    private Label team2Points;

    @FXML
    private TextField infoField;

    @FXML 
    private TextArea leftArea;

    @FXML 
    private TextArea rightArea;

    @FXML
    private Button startButton;

    @FXML
    private Button closeButton;

    @FXML
    public void initialize(){

        game = new GameBoard();

        // INITIAL AMOUNTS
        borderPaneWidth = borderPane.getWidth();
        team1Points.setText("1: " + String.valueOf(game.getTeam1Points()));
        team2Points.setText("2: " + String.valueOf(game.getTeam2Points()));

        
        /// DRAG HANDLE

        dragHandle.setOnMousePressed(e -> {
            // System.out.println("bar at: " + e.getSceneX()); // drag bar location
            borderPaneWidth = borderPane.getWidth();
        });

        dragHandle.setOnMouseDragged(e -> {
            borderPaneWidth = borderPane.getWidth();
            // System.out.println("e.getSceneX(): " + e.getSceneX());
            if (e.getSceneX() > 75 && e.getSceneX() <= borderPaneWidth - borderPane.getPadding().getRight()){
                // draggableWindow.setMaxWidth(newTranslateX + 600);
                draggableWindow.setMaxWidth(e.getSceneX() - 25);
                dragHandle.setLayoutX(e.getSceneX() - 25);
                // draggableWindow.setTranslateX(newTranslateX);
            }
        });

        dragHandle.setOnMouseReleased(e -> {
            if (draggableWindow.getWidth() < 90 && !(infoField.getText().equals(""))){
                game.evaluateRound();
                team1Points.setText("1: " + String.valueOf(game.getTeam1Points()));
                team2Points.setText("2: " + String.valueOf(game.getTeam2Points()));
            }
        });

        /// BUTTONS

        // close button
        closeButton.setOnAction(e -> {
            borderPaneWidth = borderPane.getWidth();
            draggableWindow.setMaxWidth(borderPane.getMaxWidth());
            dragHandle.setLayoutX(borderPaneWidth - 4 * (borderPane.getPadding().getRight())); 
        });

        // start button
        startButton.setOnAction(e -> {
            // close window
            borderPaneWidth = borderPane.getWidth();
            draggableWindow.setMaxWidth(borderPane.getMaxWidth());
            dragHandle.setLayoutX(borderPaneWidth - 4 * (borderPane.getPadding().getRight()));
            
            // spin dial
            game.spinTarget();
            answerSlider.setValue(game.getTargetInt());

            // draw card
            game.drawCategory();
            Category category = game.getActiveCategory();
            leftArea.setText(category.min);
            rightArea.setText(category.max);

            infoField.clear();
        });

        /// SLIDERS
        // answerSlider
    
        guessSlider.setOnMouseReleased(e -> {
            game.setGuess((int) guessSlider.getValue());
        });
    }
}
