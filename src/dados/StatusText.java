package dados;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class StatusText {

    private final StringProperty text = new SimpleStringProperty("");

    public StringProperty textProperty() {
        return text;
    }

    public void setText(String text) {
        textProperty().set(text);
    }

    public String getText() {
        return textProperty().get();
    }
 

}
