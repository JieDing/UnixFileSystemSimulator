package com.jieding.controller;

import static com.jieding.view.constants.ViewConstant.JSHELL;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.text.DefaultEditorKit;

public class TextAreaKeyListener implements KeyListener {
	
	private JTextArea outArea;
	public JTextArea getOutArea() {
		return outArea;
	}

	public void setOutArea(JTextArea outArea) {
		this.outArea = outArea;
	}

	private JTextArea cmdArea;
	
	
	public JTextArea getCmdArea() {
		return cmdArea;
	}

	public void setCmdArea(JTextArea cmdArea) {
		this.cmdArea = cmdArea;
	}

	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		switch(e.getKeyCode()){
			case KeyEvent.VK_ENTER:
				
				outArea.append(cmdArea.getText()+"\n");
				cmdArea.setText(JSHELL);
				cmdArea.getCaret().setDot(JSHELL.length());
				
				break;
			case KeyEvent.VK_BACK_SPACE:
				
				break;
		}
	}

	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
