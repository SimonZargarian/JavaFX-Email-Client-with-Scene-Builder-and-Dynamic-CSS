package com.kokabmedia.controller;

import com.kokabmedia.EmailManager;
import com.kokabmedia.view.ColorTheme;
import com.kokabmedia.view.FontSize;
import com.kokabmedia.view.ViewFactory;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Slider;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.net.URL;
import java.util.ResourceBundle;

/*
 * This class will be a dependency injected controller class for the options window,
 * this class will alse initialize the ColorTheme and FontSize fields to resolve the
 * application state.
 */
public class OptionsWindowController extends BaseController implements Initializable {

    // Constructor of super class BaseController for dependency injection purposes.
    public OptionsWindowController(EmailManager emailManager, ViewFactory viewFactory, String fxmlName) {
        super(emailManager, viewFactory, fxmlName);
    }

    @FXML
    private Slider fontSizePicker;

    // The ChoiceBox is populated with the ColorTheme enum values.
    @FXML
    private ChoiceBox<ColorTheme> themePicker;

    @FXML
    void applyButtonAction(ActionEvent event) {
        viewFactory.setColorTheme(themePicker.getValue());
        viewFactory.setFontSize(FontSize.values()[(int)(fontSizePicker.getValue())]);
        viewFactory.updateStyles();
    }

    @FXML
    void cancelButtonAction(ActionEvent event) {
        Stage stage = (Stage) fontSizePicker.getScene().getWindow();
        viewFactory.closeStage(stage);

    }

    /*
     * This method will be called right after the OptionsWindowController object is
     * created in order to update it to customize the application state with the
     * the preferred options.
     *
     *  The initialize method of the Initializable interface will make sure that the
     * ColorTheme and FontSize fields will be initialized right after the controller
     * is created.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setUpPicker();
        setUpSizePicker();
    }

    /*
    * This method will handle the slider.
    *
    * The ordinal method of Enum class gives us a numerated value back.
    *
    * The StringConverter covert the numeric values to enum String values
    * */
    private void setUpSizePicker() {
        fontSizePicker.setMin(0);
        fontSizePicker.setMax(FontSize.values().length -1 );
        fontSizePicker.setValue(viewFactory.getFontSize().ordinal());
        fontSizePicker.setMajorTickUnit(1);
        fontSizePicker.setMinorTickCount(0);
        fontSizePicker.setBlockIncrement(1);
        fontSizePicker.setSnapToTicks(true);
        fontSizePicker.setShowTickMarks(true);
        fontSizePicker.setShowTickLabels(true);
        fontSizePicker.setLabelFormatter(new StringConverter<Double>() {
            @Override
            public String toString(Double aDouble) {
                int i = aDouble.intValue();
                return FontSize.values()[i].toString();
            }

            @Override
            public Double fromString(String s) {
                return null;
            }
        });
        // Slider will snap directly to value.
        fontSizePicker.valueProperty().addListener((obs, oldVal, newVal) -> {
            fontSizePicker.setValue(newVal.intValue());
        });

    }

    /*
     * Sets an observable list by taking a List and transform it to a observableArrayList
     * from the ChoiceBox widget.
     */
    private void setUpPicker() {
        themePicker.setItems(FXCollections.observableArrayList(ColorTheme.values()));
        themePicker.setValue(viewFactory.getColorTheme());
    }
}