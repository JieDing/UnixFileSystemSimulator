package com.jieding.view.components;

import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.UIManager;
import javax.swing.plaf.TextUI;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultCaret;

public class MyCaret extends DefaultCaret {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MyCaret(){
		super();
	}
	 
	@Override
	protected synchronized void damage(Rectangle r) {
        if (r != null) {
          // int damageWidth = this.getCaretWidth(r.height);
            x = r.x - 4 - (2 >> 1);
            y = r.y;
            width = 10;
            height = 2;
            repaint();
        }
    }
	
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		if(isVisible()) {
			TextUI mapper = super.getComponent().getUI();
	         Rectangle r=null;
			try {
				r = mapper.modelToView(super.getComponent(), super.getDot(), super.getDotBias());
			} catch (BadLocationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	     
			 g.setColor(super.getComponent().getCaretColor());

	         g.fillRect(r.x, r.y+18, 10, 2);
	         
		}
	}
	
}
