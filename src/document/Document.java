package document;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.*;

public class Document extends JFrame implements ActionListener {

    static {
        System.setProperty("java.awt.headless", "true");
    }
    private static final long serialVersionUID = 6311959169739142700L;
    public static JTextPane ta;
    private JMenuBar menuBar;
    private JMenu fileM, editM, viewM, ColourM, StyleM, FontStyleM, SizeM;
    private JScrollPane scpane;
    private JMenuItem exitI, cutI, copyI, pasteI, selectI, saveI, loadI, SpellI;
    private JToolBar toolBar;
    private JMenuItem modeI;
    private JButton bbutton, ibutton, ubutton;
    private JMenuItem TviewI;
    private MKeyListener l;
    protected static boolean running = true;

    public Document() throws ClassNotFoundException, SQLException {
        super("Document");  //it is inheriting from aactin listener
        setSize(600, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container pane = getContentPane();
        pane.setLayout(new BorderLayout());
        db.Main();//main function of db class is run so trie instance is made there


        ta = new JTextPane();
        menuBar = new JMenuBar(); //menubar
        fileM = new JMenu("File"); //file menu
        editM = new JMenu("Edit"); //edit menu
        viewM = new JMenu("View"); //edit menu
        ColourM = new JMenu("Colour");
        StyleM = new JMenu("Styling");
        FontStyleM = new JMenu("Font Style");
        SizeM = new JMenu("Font Size");

        scpane = new JScrollPane(ta); //scrollpane  and add textarea to scrollpane
        exitI = new JMenuItem("Exit");
        cutI = new JMenuItem("Cut");
        copyI = new JMenuItem("Copy");
        SpellI = new JMenuItem("Spell");
        bbutton = new JButton("B");
        ibutton = new JButton("I");
        ubutton = new JButton("U");
        modeI = new JMenuItem("Tutor");
        pasteI = new JMenuItem("Paste");
        selectI = new JMenuItem("Select All"); //menuitems
        saveI = new JMenuItem("Save"); //menuitems
        loadI = new JMenuItem("Load"); //menuitems
        TviewI = new JMenuItem("Quick Tools"); //menuitems
        l = new MKeyListener();
        ta.addKeyListener(l);




        toolBar = new JToolBar();

        setJMenuBar(menuBar);
        menuBar.add(fileM);
        menuBar.add(editM);
        menuBar.add(viewM);
        menuBar.add(StyleM);

        StyleM.add(ColourM);
        StyleM.add(FontStyleM);
        StyleM.add(SizeM);


        fileM.add(saveI);
        fileM.add(loadI);
        fileM.add(exitI);
        fileM.add(modeI);

        editM.add(cutI);
        editM.add(copyI);
        editM.add(pasteI);
        editM.add(selectI);
        editM.add(SpellI);


        ColourM.add(new StyledEditorKit.ForegroundAction("Black", Color.BLACK));
        ColourM.add(new StyledEditorKit.ForegroundAction("Red", Color.RED));
        ColourM.add(new StyledEditorKit.ForegroundAction("Blue", Color.BLUE));
        ColourM.add(new StyledEditorKit.ForegroundAction("Green", Color.GREEN));
        ColourM.add(new StyledEditorKit.ForegroundAction("Orange", Color.ORANGE));
        ColourM.add(new StyledEditorKit.ForegroundAction("Yellow", Color.YELLOW));
        ColourM.add(new StyledEditorKit.ForegroundAction("Pink", Color.pink));
        ColourM.add(new StyledEditorKit.ForegroundAction("Magenta", Color.magenta));



        GraphicsEnvironment e = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Font[] fonts = e.getAllFonts(); // Get the fonts
        for (Font f : fonts) {
            FontStyleM.add(new StyledEditorKit.FontFamilyAction(f.getFontName(), f.getFamily()));
        }

        for (int i = 6; i <= 200; i += 8) {
            SizeM.add(new StyledEditorKit.FontSizeAction(Integer.toString(i), i));
        }

        viewM.add(TviewI);
        toolBar.add(bbutton);
        toolBar.add(ubutton);
        toolBar.add(ibutton);
        toolBar.setVisible(true);




        saveI.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));//inserting shortcuts
        loadI.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, ActionEvent.CTRL_MASK));
        cutI.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
        copyI.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
        pasteI.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.CTRL_MASK));
        selectI.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));
        modeI.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T, ActionEvent.CTRL_MASK));
        exitI.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, ActionEvent.ALT_MASK));
        SpellI.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F6, ActionEvent.ALT_MASK));

        pane.add(scpane, BorderLayout.CENTER);
        pane.add(toolBar, BorderLayout.NORTH);


        saveI.addActionListener(this);
        loadI.addActionListener(this);
        exitI.addActionListener(this);
        cutI.addActionListener(this);
        copyI.addActionListener(this);
        pasteI.addActionListener(this);
        selectI.addActionListener(this);
        SpellI.addActionListener(this);






        modeI.addActionListener(this);
        TviewI.addActionListener(this);
        bbutton.addActionListener(new StyledEditorKit.BoldAction());
        ibutton.addActionListener(new StyledEditorKit.ItalicAction());
        ubutton.addActionListener(new StyledEditorKit.UnderlineAction());



        setVisible(true);//normally when we fram initialize we can't see this makes the frame visible




    }

    public void actionPerformed(ActionEvent e) //it sees which menu item is selected
    {
        JMenuItem choice = (JMenuItem) e.getSource();
        Element NULL;
        if (choice == saveI) {
            JFileChooser chooser = new JFileChooser();
            chooser.setCurrentDirectory(new File("/home/me/Documents"));// it is default directory and we can choose
            int retrival = chooser.showSaveDialog(null);
            if (retrival == JFileChooser.APPROVE_OPTION) {
                FileWriter file = null;
                try {
                    file = new FileWriter(chooser.getSelectedFile() + ".sp");//saved the file
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                String ln = System.getProperty("line.separator");
                char c[];
                String text = ta.getText();
                String as = text.replaceAll("\n", ln);
                c = as.toCharArray();

                StyledDocument doc = (StyledDocument) ta.getDocument();           // Take style document
                BufferedWriter bw = new BufferedWriter(file);

                int i = 0, f;
                Element element = doc.getCharacterElement(i);                     // Picks element by element
                int ele = doc.getLength();

                while (ele > 0) {
                    AttributeSet aset = element.getAttributes();                    // Takes attribute of each element
                    f = 0;
                    if (StyleConstants.isBold(aset)) {
                        f += 1;
                    }
                    if (StyleConstants.isItalic(aset)) {
                        f += 3;
                    }
                    if (StyleConstants.isUnderline(aset)) {
                        f += 5;
                    }

                    char cf = (char) (f + '0');
                    String n = "" + c[i];    //System.out.println(c[i]+" "+n+" "+cf);
                    if (!n.equalsIgnoreCase("\n")) {
                        n = n + Character.toString(cf);
                    }


                    try {
                        bw.write(n);
                    } catch (IOException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }

                    element = doc.getCharacterElement(++i);
                    ele--;
                }

                try {
                    bw.close();
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }

        } else if (choice == exitI) {
            System.exit(0);
        } else if (choice == cutI) {
            ta.cut();
        } else if (choice == copyI) {
            ta.copy();
        } else if (choice == pasteI) {
            ta.paste();
        } else if (choice == modeI) {

            NewJFrame.main(null);


        } else if (choice == SpellI) {
            Highlighter h = ta.getHighlighter();
            Highlighter.HighlightPainter p = new MyHighlighter(Color.yellow);
            String text = ta.getText();
            String[] words = text.split(" ");

            for (String ss : words) {

                System.out.println(ss);
                if (!(db.t.search(ss.toLowerCase()))) {
                    try {
                        h.addHighlight(text.indexOf(ss), text.indexOf(ss) + ss.length(), p);
                    } catch (BadLocationException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                }
            }
        }

        else if (choice == selectI) {
            ta.selectAll();
        }
        else if (choice == TviewI) {
            if (toolBar.isVisible() == true) {
                toolBar.setVisible(false);
            } else {
                toolBar.setVisible(true);
            }
        }
        else if (e.getSource() == loadI) {
            JFileChooser chooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter(
                    "Text Files", "txt");
            chooser.setFileFilter(filter);
            int returnVal = chooser.showOpenDialog(getParent());
            if (returnVal == JFileChooser.APPROVE_OPTION) {

                String line;
                ta.setText("");
                try {
                    BufferedReader in = new BufferedReader(new FileReader(chooser.getSelectedFile()));

                    line = in.readLine();
                    while (line != null) // while not end of file
                    {
                        StyledDocument doc = ta.getStyledDocument();

                        try {

                            char c[];
                            c = line.toCharArray();
                            for (int i = 0; i < c.length; i += 2) {

                                SimpleAttributeSet keyword = new SimpleAttributeSet();
                                String str = "" + c[i];
                                int f = (int) (c[i + 1] - '0');


                                keyword.addAttribute(StyleConstants.CharacterConstants.Bold, Boolean.FALSE);
                                keyword.addAttribute(StyleConstants.CharacterConstants.Italic, Boolean.FALSE);
                                keyword.addAttribute(StyleConstants.CharacterConstants.Underline, Boolean.FALSE);


                                if (f == 1 || f == 4 || f == 6 || f == 9) {
                                    keyword.addAttribute(StyleConstants.CharacterConstants.Bold, Boolean.TRUE);
                                }

                                if (f == 3 || f == 4 || f == 8 || f == 9) {
                                    keyword.addAttribute(StyleConstants.CharacterConstants.Italic, Boolean.TRUE);
                                }

                                if (f == 5 || f == 6 || f == 8 || f == 9) {
                                    keyword.addAttribute(StyleConstants.CharacterConstants.Underline, Boolean.TRUE);
                                }

                                doc.insertString(doc.getLength(), str, keyword);
                            }

                            SimpleAttributeSet keyword = new SimpleAttributeSet();
                            doc.insertString(doc.getLength(), "\n", keyword);
                        } catch (Exception x) {
                            System.out.println(x);
                        }
                        line = in.readLine();
                    }
                    in.close();
                } catch (IOException iox) {
                    System.out.println("Problem reading " + chooser.getSelectedFile());
                }
            }

        }

    }

    public static void main(String[] args) throws ClassNotFoundException, SQLException {


        new Document();


    }
}
