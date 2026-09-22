module com.appregistros.appregistro {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.appregistros.appregistro to javafx.fxml;
    opens com.appregistros.appregistro.controller;
    exports com.appregistros.appregistro;
    exports com.appregistros.appregistro.model;
    exports com.appregistros.appregistro.controller;

}