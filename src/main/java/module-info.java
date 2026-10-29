module com.mycompany.clubdejava {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.almasb.fxgl.all;

    opens com.mycompany.clubdejava to javafx.fxml, com.almasb.fxgl.core;
    exports com.mycompany.clubdejava;
}
