package com.kokabmedia;

import com.kokabmedia.view.ViewFactory;
import javafx.application.Application;
import javafx.stage.Stage;

public class Launcher extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

       /*
        * View factory shows login method and passes in EmailManager, this will bind
        * the data that goes through out the application.
        */
        ViewFactory viewFactory = new ViewFactory(new EmailManager());
        viewFactory.showLoginWindow();
        viewFactory.updateStyles();

    }
}
