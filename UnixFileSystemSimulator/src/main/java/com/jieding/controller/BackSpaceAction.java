package com.jieding.controller;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.text.JTextComponent;

public class BackSpaceAction extends AbstractAction{
	 
	private int prefixLength;
	private Action deletePrevious;
	
	public BackSpaceAction(int prefix, Action delete){
		this.prefixLength = prefix;
		this.deletePrevious = delete;
	}
	
	public void actionPerformed(ActionEvent e)
     {
         JTextComponent component = (JTextComponent)e.getSource();

         if (component.getCaretPosition() > prefixLength)
         {
             deletePrevious.actionPerformed( null );
         }
         else {
			Toolkit.getDefaultToolkit().beep();
		}
     }
}
