package com.jieding.controller;

import static com.jieding.view.constants.ViewConstant.JSHELL;
import static com.jieding.controller.constants.ControllerConstant.INVALIDCOMMAND;
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
	CommandController cc = null;
	
	public CmdAreaKeyListener(){
		cc = new CommandController();
	}
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
				
				if (inputCmd.length()>0){
					 cc.setInputCmd(inputCmd);
					 if(!cc.isCommandValid())
						outArea.append(JSHELL+cmdArea.getText()+"\n"+cc.getcName()+INVALIDCOMMAND+"\n");
					 else{
						 String result = cc.exectueCommand();
						if(result!=null)
							outArea.append(JSHELL+cmdArea.getText()+"\n"+result+"\n");
						else{
							outArea.append(JSHELL+cmdArea.getText()+"\n");
						}
					 }
					 
				}else{
					outArea.append(cmdArea.getText()+"\n");
				}
				
				cmdArea.setText(JSHELL);
				cmdArea.getCaret().setDot(JSHELL.length());
				if(scroll.getViewport().getViewPosition().y +500>=contentPanel.getHeight()){
					contentPanel.setPreferredSize(new Dimension(contentPanel.getWidth(),contentPanel.getHeight()+100));
					contentPanel.revalidate();
				}
				
				break;
			
		}
	}

	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
