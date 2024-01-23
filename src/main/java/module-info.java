module com.example.progetto {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires com.almasb.fxgl.all;
    requires java.sql;

    opens com.example.progetto to javafx.fxml;
    exports com.example.progetto;
    exports com.example.progetto.controller_graf;
    opens com.example.progetto.controller_graf to javafx.fxml;
}