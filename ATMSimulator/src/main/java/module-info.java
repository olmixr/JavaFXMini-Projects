module org.example.atmsimulator {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.atmsimulator to javafx.fxml;
    exports org.example.atmsimulator;
}