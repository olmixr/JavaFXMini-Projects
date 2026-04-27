module org.example.goalsimulator {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.goalsimulator to javafx.fxml;
    exports org.example.goalsimulator;
}