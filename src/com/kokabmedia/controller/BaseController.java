package com.kokabmedia.controller;

import com.kokabmedia.EmailManager;
import com.kokabmedia.view.ViewFactory;

/*
* This class is used to create dependency injection through out the application.
*
* This facilitates a link to the EmailManager and the ViewFactory and get extended
* by all controllers of this application. This way we can take control of the
* controllers from javafx and handle the dependency injection our self's, by creating
* the controller objects and using a fxml loader.
* */
public abstract class BaseController {

    protected EmailManager emailManager;
    protected ViewFactory viewFactory;

    // indication to fxml file
    private String fxmlName;

    public BaseController(EmailManager emailManager, ViewFactory viewFactory, String fxmlName) {
        this.emailManager = emailManager;
        this.viewFactory = viewFactory;
        this.fxmlName = fxmlName;
    }

    public String getFXMLName(){
        return fxmlName;
    }
}


