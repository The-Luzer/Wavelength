package wavelength;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class Controller {

    @FXML
    private Pane draggableWindow;

    @FXML
    private Label dragHandle;

    private double mouseAnchorX;

    @FXML
    public void initialize(){
        
        dragHandle.setOnMousePressed(event -> {
            mouseAnchorX = event.getSceneX() - draggableWindow.getTranslateX();
        });

        dragHandle.setOnMouseDragged(event -> {
            double newTranslateX = event.getSceneX() - mouseAnchorX;
            draggableWindow.setTranslateX(newTranslateX);
        });
    }
}
