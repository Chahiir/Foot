package view.composent;

import java.awt.Color;

public class ConfirmButton extends ActionButton {
	
    
    public ConfirmButton(String label) {
    	super(label);
    	setBackground_color(new Color(93, 173, 226));
    	border_color = new Color(200,200,200);
    	pressed_background_color = background_color.darker();
    	setPressedColor();
	}
}
