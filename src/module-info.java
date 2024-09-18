module MyApplication {

    requires java.desktop;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.compiler;
    requires java.sql;
    requires com.microsoft.sqlserver.jdbc;
    requires org.apache.poi.poi;
    requires org.apache.poi.ooxml;


    opens sample.Controllers;
    exports sample;

}