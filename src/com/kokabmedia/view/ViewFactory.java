package com.kokabmedia.view;

import com.kokabmedia.EmailManager;
import com.kokabmedia.controller.BaseController;
import com.kokabmedia.controller.LoginWindowController;
import com.kokabmedia.controller.MainWindowController;
import com.kokabmedia.controller.OptionsWindowController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

/*
* This class will handle all of the views for the application, the class also holds a
* EmailManager object that will work with dependency injection and bind the data that
* goes through out the application.
*/
public class ViewFactory {

    private EmailManager emailManager;
    private ArrayList<Stage> activeStage;

    public ViewFactory(EmailManager emailManager) {
        this.emailManager = emailManager;
        activeStage = new ArrayList<Stage>();
    }

    // View options handling
    private ColorTheme colorTheme = ColorTheme.DEFAULT;
    private FontSize fontSize = FontSize.MEDIUM;

    // Method to show login view with injected controller class.
    public void showLoginWindow(){
        System.out.println("show login window");

        // Gets a LoginWindowController controller class sets the view for it.
        BaseController controller = new LoginWindowController(emailManager, this, "LoginWindow.fxml");
        initializeStage(controller);
    }

    // Method to show main window view with injected controller class.
    public void showMainWindow(){
        System.out.println("show main window");

        // Gets a MainWindowController controller class and sets the view for it.
        BaseController controller = new MainWindowController(emailManager, this, "MainWindow.fxml");
        initializeStage(controller);
    }

    // Method to show options window view with injected controller class.
    public void showOptionsWindow(){
        System.out.println("show options window");

        // Gets a OptionsWindowController controller class and sets the view for it.
        BaseController controller = new OptionsWindowController(emailManager, this, "OptionsWindow.fxml");
        initializeStage(controller);
    }

    /*
     * Method for starting and showing the views of the application, this method facilitates
     * dependency injection with the FXMLLoader loader class binding the views and the controllers
     * together while detaching the handling of the controllers from JavaFx framework.
     */
    public void initializeStage(BaseController baseController){

        BaseController controller = new MainWindowController(emailManager, this, "MainWindow.fxml");

        // Controller dependency injection.
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(baseController.getFXMLName()));
        fxmlLoader.setController(baseController);

        // Render the view.
        Parent parent;
        try{
            parent = fxmlLoader.load();
        }catch (IOException e){
            e.printStackTrace();
            return;
        }
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        activeStage.add(stage);
    }

    // Method for closing a specific stage
    public void closeStage(Stage stageCloser){
        stageCloser.close();
        activeStage.remove(stageCloser);
    }

    /*
     * CSS is applied to scenes in JavaFx, to apply CSS we need a list of scenes
     * */
    public void updateStyles() {
        for(Stage stage : activeStage){
            Scene scene = stage.getScene();

            // handle the css
            scene.getStylesheets().clear();
            scene.getStylesheets().add(getClass().getResource(ColorTheme.getCssPath(colorTheme)).toExternalForm());
            scene.getStylesheets().add(getClass().getResource(FontSize.getCssPath(fontSize)).toExternalForm());

        }
    }

    public ColorTheme getColorTheme() {
        return colorTheme;
    }

    public void setColorTheme(ColorTheme colorTheme) {
        this.colorTheme = colorTheme;
    }

    public FontSize getFontSize() {
        return fontSize;
    }

    public void setFontSize(FontSize fontSize) {
        this.fontSize = fontSize;
    }


    public void showComposeMessageWindow() {
    }
}
