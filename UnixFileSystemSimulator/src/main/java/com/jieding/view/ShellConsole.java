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
import java.awt.Point;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;

import com.jieding.controller.BackspaceNavigationFilter;
import com.jieding.controller.TextAreaKeyListener;
import com.jieding.view.components.UnderLineCaret;


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
		contentPanel.setPreferredSize(new Dimension(TEXTAREA_DEFAULT_WIDTH,1000));
		contentPanel.setBackground(Color.BLACK);
		FlowLayout f = new FlowLayout();
		f.setAlignment(FlowLayout.LEFT);
		contentPanel.setLayout(f);
		
		JScrollPane scroll=new JScrollPane();
		
		Font font = new Font(FONT_STYLE,Font.BOLD,FONT_DEFAULT_SIZE);
		
		JTextArea outArea = new JTextArea();
		outArea.setBounds(0, 0, TEXTAREA_DEFAULT_WIDTH, TEXTAREA_DEFAULT_HEIGHT);
		outArea.setFont(font);
		outArea.setForeground(new Color(FONT_R, FONT_G, FONT_B));
		outArea.setBackground(Color.black);
		outArea.setEditable(false);
		outArea.setLineWrap(true);        
		outArea.setText(DEFAULT_INSTRUCTION);
		
		
		final JTextArea cmdArea = new JTextArea();
		
		//cmdArea.setBounds(0, 0, TEXTAREA_DEFAULT_WIDTH, getHeight());
		cmdArea.setSize(new Dimension(TEXTAREA_DEFAULT_WIDTH,1));
		cmdArea.setFont(font);
		cmdArea.setForeground(new Color(FONT_R, FONT_G, FONT_B));
		cmdArea.setBackground(Color.black);
		cmdArea.setLineWrap(true);       
		cmdArea.setText(JSHELL);
		
		cmdArea.setCaret(new UnderLineCaret());
		cmdArea.getCaret().setBlinkRate(1000);
		cmdArea.setCaretColor(new Color(FONT_R, FONT_G, FONT_B));
		cmdArea.getCaret().setDot(JSHELL.length());
		
		cmdArea.requestFocus();
		cmdArea.getCaret().setVisible(true);
		cmdArea.requestFocus();
		final TextAreaKeyListener keyListener = new TextAreaKeyListener();
		keyListener.setCmdArea(cmdArea);
		keyListener.setOutArea(outArea);
		JScrollBar bar = scroll.getVerticalScrollBar();
		//bar.setPreferredSize(new Dimension(10,20));
		keyListener.setContentPanel(contentPanel);
		keyListener.setScroll(scroll);
		
		KeyStroke enter = KeyStroke.getKeyStroke("ENTER");
		cmdArea.getInputMap().put(enter, "none");
		
		cmdArea.addKeyListener(keyListener);
				
		cmdArea.setNavigationFilter(new BackspaceNavigationFilter(JSHELL.length(), cmdArea));
		
		this.addWindowListener( new WindowAdapter() {
		    public void windowOpened( WindowEvent e ){
		        cmdArea.requestFocus();
		    }
		}); 
		
		contentPanel.add(outArea);
		contentPanel.add(cmdArea);
		
		
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);   
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);  
		scroll.setBackground(Color.BLACK);
		scroll.setViewportView(contentPanel);
		
		container.add(scroll);

		
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
