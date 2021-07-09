module myjfx {
    requires javafx.fxml;
    requires javafx.controls;
    requires javafx.graphics;
    opens UI to javafx.graphics, javafx.fxml;
}