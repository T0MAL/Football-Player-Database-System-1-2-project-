module myjfx {
    requires javafx.fxml;
    requires javafx.controls;
    requires javafx.graphics;
    opens UI to javafx.graphics, javafx.fxml,javafx.base;
    opens Transferables to javafx.graphics, javafx.fxml,javafx.base;
    opens Server to javafx.graphics, javafx.fxml,javafx.base;
    opens util to javafx.graphics, javafx.fxml,javafx.base;
    opens Clients to javafx.graphics, javafx.fxml,javafx.base;
}