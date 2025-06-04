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
    private Label higherLabel;

    @FXML
    private ToggleButton toggleButton;

    @FXML
    public void initialize() {

        game = new GameBoard();

        // INITIAL AMOUNTS
        borderPaneWidth = borderPane.getWidth();
        team1Points.setText("1: " + String.valueOf(game.getTeam1Points()));
        team2Points.setText("2: " + String.valueOf(game.getTeam2Points()));
        higherLabel.setText(toggleButton.isSelected() ? "Status: Higher ↑" : "Status: Lower ↓");

        /// DRAG HANDLE

        dragHandle.setOnMousePressed(e -> {
            // System.out.println("bar at: " + e.getSceneX()); // drag bar location
            borderPaneWidth = borderPane.getWidth();
        });

        dragHandle.setOnMouseDragged(e -> {
            borderPaneWidth = borderPane.getWidth();
            // System.out.println("e.getSceneX(): " + e.getSceneX());
            if (e.getSceneX() > 75 && e.getSceneX() <= borderPaneWidth - borderPane.getPadding().getRight()) {
                // draggableWindow.setMaxWidth(newTranslateX + 600);
                draggableWindow.setMaxWidth(e.getSceneX() - 25);
                dragHandle.setLayoutX(e.getSceneX() - 25);
                // draggableWindow.setTranslateX(newTranslateX);
            }
        });

        dragHandle.setOnMouseReleased(e -> {

            // scoring
            if (draggableWindow.getWidth() < 90 && !(infoField.getText().equals(""))) {
                game.evaluateRound();
                team1Points.setText("1: " + String.valueOf(game.getTeam1Points()));
                team2Points.setText("2: " + String.valueOf(game.getTeam2Points()));
                infoField.clear();
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

            // clear text
            infoField.clear();

            // reset dial
            guessSlider.setValue(0);
        });

        toggleButton.setOnAction(e -> {
            game.setGuessHigher(toggleButton.isSelected());
            String text = toggleButton.isSelected() ? "Status: Higher ↑" : "Status: Lower ↓";
            higherLabel.setText(text);
            System.out.println(toggleButton.isSelected());
        });

        /// SLIDERS
        // answerSlider

        guessSlider.setOnMouseReleased(e -> {
            game.setGuess((int) guessSlider.getValue());
        });
    }
}
