package document;

import java.awt.Color;

import javax.swing.text.DefaultHighlighter.DefaultHighlightPainter;

public class MyHighlighter extends DefaultHighlightPainter {

    public MyHighlighter(Color c) {
        super(c);
    }
}
