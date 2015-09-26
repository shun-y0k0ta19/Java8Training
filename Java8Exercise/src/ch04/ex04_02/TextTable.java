package ch04.ex04_02;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class TextTable {
	
	private String[][] textTable;
    private StringProperty[][] textProperty;

    public TextTable(int columns, int rows){
    	textTable = new String[columns][rows];
    	textProperty = new StringProperty[columns][rows];
    	for(String[] column : textTable){
    		for(int i = 0; i < column.length; i++){
    			column[i] = "default" + i;
    		}
    	}
    }
    
    public final StringProperty textProperty(int column, int row) {
        if (textProperty[column][row] == null) {
            textProperty[column][row] = new SimpleStringProperty(textTable[column][row]);
        }
        return textProperty[column][row];
    }

    public final void setText(String newValue, int column, int row) {
    	if(textProperty[column][row] == null){
    		textTable[column][row] = newValue;
    	}
    	else {	
    		textProperty[column][row].set(newValue);
    	}
    }

    public final String getText(int column, int row) {
        return textProperty[column][row] == null ? textTable[column][row] : textProperty[column][row].get();
    }
    
    public static void main(String[] args){
    	
    }
    
}
