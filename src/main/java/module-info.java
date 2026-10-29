open module com.mycompany.clubdejava {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.almasb.fxgl.all;
    requires com.almasb.fxgl.entity;
    requires java.base;
    requires javafx.media;

    exports com.mycompany.clubdejava;
    opens com.mycompany.clubdejava to javafx.fxml, com.almasb.fxgl.all;
}
