package com.example.tips_lab1.formatter;

import javafx.scene.control.TextFormatter;
import javafx.util.StringConverter;

import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

public class StringToRouteStringFormatter {
    private final TextFormatter<String> textFormatter;

    public StringToRouteStringFormatter() {
        Pattern validEditingState = Pattern.compile("([0-4]*[0-4]*)");

        UnaryOperator<TextFormatter.Change> filter = c -> {
            String text = c.getControlNewText();
            if (validEditingState.matcher(text).matches()) {
                return c;
            } else {
                return null ;
            }
        };

        StringConverter<String> converter = new StringConverter<String>() {

            @Override
            public String toString(String s) {
                return s;
            }

            @Override
            public String fromString(String s) {
                if(s.length() == 1) {
                    return String.format("%s->0", s);
                } else{
                    return String.format("%s->%s",s.replaceAll("->","").charAt(0), s.replaceAll("->","").charAt(1));
                }

            }
        };
        //TODO: после установки значения строку поменять нельзя
        textFormatter = new TextFormatter<>(converter, "", filter);
    }

    public TextFormatter<String> getTextFormatter() {
        return textFormatter;
    }
}
