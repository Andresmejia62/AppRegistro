module com.appregistros.appregistro {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.appregistros.appregistro to javafx.fxml;
    exports com.appregistros.appregistro;
}