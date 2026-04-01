module org.datasphere {
    requires javafx.controls;
    requires javafx.fxml;

    opens org.datasphere to javafx.base, javafx.fxml;
    exports org.datasphere;
    exports org.datasphere.controller;
    opens org.datasphere.controller to javafx.fxml;
}
