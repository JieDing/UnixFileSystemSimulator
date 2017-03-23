package com.jieding.view;

/**
 * static import constants used in views
 */
import static com.jieding.view.constants.ViewConstant.DEFAULT_INSTRUCTION;
import static com.jieding.view.constants.ViewConstant.FONT_B;
import static com.jieding.view.constants.ViewConstant.FONT_DEFAULT_SIZE;
import static com.jieding.view.constants.ViewConstant.FONT_G;
import static com.jieding.view.constants.ViewConstant.FONT_R;
import static com.jieding.view.constants.ViewConstant.FONT_STYLE;
import static com.jieding.view.constants.ViewConstant.JSHELL;
import static com.jieding.view.constants.ViewConstant.TEXTAREA_DEFAULT_HEIGHT;
import static com.jieding.view.constants.ViewConstant.TEXTAREA_DEFAULT_WIDTH;
import static com.jieding.view.constants.ViewConstant.WINDOW_DEFAULT_HEIGHT;
import static com.jieding.view.constants.ViewConstant.WINDOW_DEFAULT_POSITION_X;
import static com.jieding.view.constants.ViewConstant.WINDOW_DEFAULT_POSITION_Y;
import static com.jieding.view.constants.ViewConstant.WINDOW_DEFAULT_TITLE;
import static com.jieding.view.constants.ViewConstant.WINDOW_DEFAULT_WIDTH;
import static com.jieding.view.constants.ViewConstant.WINDOW_ICON_PATH;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.text.DefaultCaret;

import com.jieding.view.components.MyCaret;


public class ShellConsole extends JFrame {

	/**
	 * 
	 */
	
	private static final long serialVersionUID = -4526419471749478887L;
	
	public ShellConsole(){
		init();
		
	}
	
	private void initContents() {
		
		Container container=getContentPane();
		
		JPanel contentPanel = new JPanel();
		contentPanel.setPreferredSize(new Dimension(800,700));
		contentPanel.setBackground(Color.BLACK);
		FlowLayout f = new FlowLayout();
		f.setAlignment(FlowLayout.LEFT);
		contentPanel.setLayout(f);
		
		
		Font font = new Font(FONT_STYLE,Font.BOLD,FONT_DEFAULT_SIZE);
		
		JTextArea outputArea = new JTextArea();
		outputArea.setBounds(0, 0, TEXTAREA_DEFAULT_WIDTH, TEXTAREA_DEFAULT_HEIGHT);
		outputArea.setFont(font);
		outputArea.setForeground(new Color(FONT_R, FONT_G, FONT_B));
		outputArea.setBackground(Color.black);
		outputArea.setEditable(false);
		outputArea.setLineWrap(true);       
		outputArea.setWrapStyleWord(true);  
		outputArea.setText(DEFAULT_INSTRUCTION);
		
		
		JTextArea cmdArea = new JTextArea();
		cmdArea.setBounds(0, 0, TEXTAREA_DEFAULT_WIDTH, getHeight());
		cmdArea.setFont(font);
		cmdArea.setForeground(new Color(FONT_R, FONT_G, FONT_B));
		cmdArea.setBackground(Color.black);
		cmdArea.setLineWrap(true);       
		cmdArea.setWrapStyleWord(true);  
		cmdArea.setText(JSHELL);
		
		cmdArea.setCaret(new MyCaret());
		//cmdArea.getCaret().setBlinkRate(1000);
		cmdArea.setCaretColor(new Color(FONT_R, FONT_G, FONT_B));
		cmdArea.getCaret().setDot(JSHELL.length());
		
		cmdArea.requestFocus();
		cmdArea.getCaret().setVisible(true);
		cmdArea.requestFocus();
		
		contentPanel.add(outputArea);
		contentPanel.add(cmdArea);
		JScrollPane scroll=new JScrollPane();
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);   
		scroll.setHorizontalScrollBarPolicy( JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);  
		scroll.setBackground(Color.BLACK);
		scroll.setViewportView(contentPanel);
		container.add(scroll);
		
		/*contentPanel.add(outputArea);
		contentPanel.add(cmdArea);
		//contentPanel.add(scroll);
		setContentPane(contentPanel);*/
		
	}
	/**
	 * initialize the default properties of the JFrame container
	 * i.e. location, size, title, icon etc.
	 */
	private void init() {
		// TODO Auto-generated method stub
		
		this.setLocation(WINDOW_DEFAULT_POSITION_X, WINDOW_DEFAULT_POSITION_Y);  
	    this.setSize(WINDOW_DEFAULT_WIDTH, WINDOW_DEFAULT_HEIGHT);  
	    this.setTitle(WINDOW_DEFAULT_TITLE);
	    this.setResizable(false);
	    this.setIconImage(new ImageIcon(getClass().getClassLoader().getResource(WINDOW_ICON_PATH)).getImage());
	  
	    initContents();
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
		this.setVisible(true);
	}
	
}
