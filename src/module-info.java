module EmailClientJavaFx {
    requires javafx.fxml;
    requires javafx.controls;
    requires javafx.graphics;
    requires javafx.web;

    opens com.kokabmedia;
    opens com.kokabmedia.view;
    opens com.kokabmedia.controller;
    opens com.kokabmedia.model;
    requires activation;
    requires java.mail;


}