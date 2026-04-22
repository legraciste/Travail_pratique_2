module com.example.travail_pratique_2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.travail_pratique_2 to javafx.fxml;
    exports com.example.travail_pratique_2;
}