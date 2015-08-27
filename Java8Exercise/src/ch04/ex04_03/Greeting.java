package ch04.ex04_03;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Greeting {
	private final String text = "default";
    private StringProperty textProperty = null;

    public final StringProperty textProperty() {
        if (textProperty == null) {
            textProperty = new SimpleStringProperty(text);
        }
        return textProperty;
    }

    public final void setText(String newValue) {
        if (textProperty == null) {
        	textProperty = new SimpleStringProperty(newValue);
        } 
        else {
        	textProperty.set(newValue);
        }
    }

    public final String getText() {
        return textProperty == null ?  text : textProperty.get();
    }
}
