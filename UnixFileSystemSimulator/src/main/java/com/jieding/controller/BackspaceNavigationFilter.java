package com.jieding.controller;

import javax.swing.Action;
import javax.swing.text.JTextComponent;
import javax.swing.text.NavigationFilter;
import javax.swing.text.Position;
/**
 * 
* @ClassName: BackspaceNavigationFilter 
* @author JieDing dingjwilliams@gmail.com
* 
* @Description: NavigationFilter is used to restrict where the cursor or caret can be positioned.
* BackspaceNavigationFilter extends the NavigationFilter to restrict the backspace action in a 
* JTextComponent(in my application it's a JTextArea).
* 
* With this Filter, we could ensure some contents won't be deleted by the Backspace.
* 
* Thanks to camickr from StackOverFlow.com
* 
* the original page address is:
* 
* http://stackoverflow.com/questions/7421337/limited-selection-in-a-jtextfield-jtextcomponent
 */
public class BackspaceNavigationFilter extends NavigationFilter {
	  	
		//the length of contents that should not be deleted
		private int prefixLength;
		private Action deletePrevious;
		
		public BackspaceNavigationFilter(int prefixLength, JTextComponent component)
	    {
	        this.prefixLength = prefixLength;
	        //the name of delete action is called delete-previous
	        deletePrevious = component.getActionMap().get("delete-previous");
	        //we replace it with our new BackSpaceAction
	        component.getActionMap().put("delete-previous", new BackSpaceAction(prefixLength,deletePrevious));
	        component.setCaretPosition(prefixLength);
	    }
		
		public void setDot(NavigationFilter.FilterBypass fb, int dot, Position.Bias bias)
	    {
	        fb.setDot(Math.max(dot, prefixLength), bias);
	    }

	    public void moveDot(NavigationFilter.FilterBypass fb, int dot, Position.Bias bias)
	    {
	        fb.moveDot(Math.max(dot, prefixLength), bias);
	    }
}
