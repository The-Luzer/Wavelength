module wavelength {
    requires transitive javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;

    opens wavelength to javafx.fxml;
    exports wavelength;
}
