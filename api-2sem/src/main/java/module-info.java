module org.datasphere {
    requires javafx.controls;
    requires javafx.fxml;
    requires io.github.cdimascio.dotenv.java;
    requires java.sql;

    opens org.datasphere to javafx.base, javafx.fxml;
    exports org.datasphere;
    exports org.datasphere.controller;
    opens org.datasphere.controller to javafx.fxml;
}
