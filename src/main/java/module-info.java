module com.quiosque {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.quiosque to javafx.fxml;
    exports com.quiosque;

    opens com.quiosque.controller to javafx.fxml;
    exports com.quiosque.controller;
}
