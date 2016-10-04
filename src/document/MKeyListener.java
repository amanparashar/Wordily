package document;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.text.BadLocationException;
import javax.swing.text.Highlighter;

public class MKeyListener implements KeyListener,Runnable {
	public boolean running=true;
	
	

		@Override
		public void run() {
			while (running)
			{
			 Highlighter h = Document.ta.getHighlighter();
		    	Highlighter.HighlightPainter p=new MyHighlighter(Color.yellow);//yellow color painter associated to this highlighter
		    	String text=Document.ta.getText();
		    	String[] words= text.split(" ");
		    	h.removeAllHighlights();
		    	 for ( String ss:words) {
		    		 

		    	       if(!(db.t.search(ss.toLowerCase())))//all words converted to lower case
		    	       {
		    	    	try {
							h.addHighlight(text.indexOf(ss),text.indexOf(ss)+ss.length(),p);
						} catch (BadLocationException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
		    	       }
		    	     
		    	  
		    	 }
		    	 try {
					StopThread();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
			
		
		
	};
	




	@Override
	public void keyPressed(KeyEvent e) {
	
	if(e.getKeyCode()==KeyEvent.VK_SPACE)
	{
		new Thread(new MKeyListener()).start();//new thread started 
	}
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	private void StopThread() throws InterruptedException {
			running=false;
			
		}


}	
	


