module com.example.spacegame {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;

    opens com.example.spacegame to javafx.fxml;
    exports com.example.spacegame;
}