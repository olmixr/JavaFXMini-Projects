module org.example.loginsimulator {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.loginsimulator to javafx.fxml;
    exports org.example.loginsimulator;
}