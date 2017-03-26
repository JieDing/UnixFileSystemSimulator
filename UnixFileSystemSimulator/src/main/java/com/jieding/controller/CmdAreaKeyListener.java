package com.jieding.controller;

import static com.jieding.view.constants.ViewConstant.JSHELL;
import static com.jieding.controller.constants.ControllerConstants.INVALIDCOMMAND;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.text.DefaultEditorKit;

public class CmdAreaKeyListener implements KeyListener {
	private JScrollPane scroll;
	private JPanel contentPanel;
	public JScrollPane getScroll() {
		return scroll;
	}

	public void setScroll(JScrollPane scroll) {
		this.scroll = scroll;
	}

	public JPanel getContentPanel() {
		return contentPanel;
	}

	public void setContentPanel(JPanel contentPanel) {
		this.contentPanel = contentPanel;
	}

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
				//obtain user input without prefix
				String inputCmd = cmdArea.getText().substring(JSHELL.length()).trim();
				CommandController cc = null;
				if (inputCmd.length()>0){
					 cc = new CommandController(inputCmd);
				}
				if(cc==null)
					outArea.append(cmdArea.getText()+"\n");
				else{
					if(!cc.isCommandValid())
						outArea.append(JSHELL+"\n"+cc.getcName()+INVALIDCOMMAND+"\n");
				
				}
				cmdArea.setText(JSHELL);
				cmdArea.getCaret().setDot(JSHELL.length());
				if(scroll.getViewport().getViewPosition().y +500>=contentPanel.getHeight()){
					contentPanel.setPreferredSize(new Dimension(contentPanel.getWidth(),contentPanel.getHeight()+100));
					contentPanel.revalidate();
					System.out.println(contentPanel.getHeight());
				}
				
				break;
			
		}
	}

	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
