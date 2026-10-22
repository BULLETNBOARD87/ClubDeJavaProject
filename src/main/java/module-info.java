module com.mycompany.clubdejava {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.mycompany.clubdejava to javafx.fxml;
    exports com.mycompany.clubdejava;
}
