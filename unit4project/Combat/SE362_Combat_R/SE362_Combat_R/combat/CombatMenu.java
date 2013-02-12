package combat;
/** 
 * @author       DevB
 * @version      $Id: CombatMenu.java,v 1.6 2012/04/08 03:50:18 DevA Exp $
 *
 * Revision History:
 *   $Log: CombatMenu.java,v $
 *   Revision 1.6  2012/04/08 03:50:18  DevA
 *   Cleaned up the code to run with Java 1.6: removed unused imports,
 *   fixed some UI focus issues (introduced by new focus "features" in Java since
 *   our original implementation), and made the CommandInterpreter not a Singleton
 *
 *   Revision 1.5  2003/05/30 17:56:10  DevB
 *   Reformatted, also removed score displays as those are
 *   now handled by the scoreboard.
 *
 *   Revision 1.4  2000/05/12 00:01:36  DevC
 *   comments & cleanup
 *
 *   Revision 1.3  2000/05/10 20:45:09  DevC
 *   added method to allow update of score
 *
 *   Revision 1.2  2000/05/09 17:20:22  DevA
 *   Threading support for scoring in place in Game and
 *   PlayerManager.
 *
 *   Revision 1.1  2000/05/09 04:57:18  DevB
 *   Initial revision
 *
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * This class start up the game.  It puts together the GUI and instantiates
 * the main classes and links them together.
 */

public class CombatMenu extends JPanel {

	private static final long serialVersionUID = -1;
	
    Game theGame;               //the game object
    JFrame levSelectWindow;     //Window for selecting levels
    JFrame keySelectWindow;     // Window for selecting keys
    
    
    ArrayList<Integer> play1cmd;
    ArrayList<Integer> play2cmd;

    /**
     * Constructor
     * Simply constructs the Combat menu, which substitutes for a menu bar
     * due to funny drawing issues.
     * @param theGame   The game running in this Combat window.
     */
    public CombatMenu( Game theGame, ArrayList<Integer> player1cmd, ArrayList<Integer> player2cmd)
    {
        //assign the incoming game object
        this.theGame = theGame;
        
        //set up this menu
        setLayout( new GridLayout( 1, 6 ) );
        setSize( 700, 50 );
        setFocusable(false);
        
        //create the level select window, buttons and score
        buildButtons();
        
        player1cmd.add(38);
        player1cmd.add(40);
        player1cmd.add(39);
        player1cmd.add(37);
        player1cmd.add(16);
        
        player2cmd.add(87);
        player2cmd.add(83);
        player2cmd.add(68);
        player2cmd.add(65);
        player2cmd.add(81);
        
        this.play1cmd = player1cmd;
        this.play2cmd = player2cmd;
        
        levSelectWindow = makeSetLevelWindow();
        keySelectWindow = makeKeyWindow();
    }

    /**
     * Builds all the buttons on the CombatMenu.
     */
    private void buildButtons()
    {
        // Build the button that will start a new game.
        JButton newGame = new JButton( "New Game" );
        newGame.setFocusable(false);
        newGame.addActionListener(
            new ActionListener()
            {
                public void actionPerformed( ActionEvent e )
                {
                    theGame.newGame();
                }
            }
        );
        
        // Build the button that will set a new level.
        JButton setLevel = new JButton( "Set Level" );
        setLevel.setFocusable(false);
        setLevel.addActionListener(
            new ActionListener()
            {
                public void actionPerformed( ActionEvent e )
                {
                    levSelectWindow.setVisible(true);
                }
            }
        );
        
        // Build the button that will set the key commands.
        JButton setKeys = new JButton( "Set Keys" );
        setKeys.setFocusable(false);
        // Add an ActionListener here when we get that ready.
        setKeys.addActionListener(
                new ActionListener()
                {
                    public void actionPerformed( ActionEvent e )
                    {
                        keySelectWindow.setVisible(true);
                    }
                }
            );
        
        // Build the buttons that will control pausing and unpausing the game.
        JButton pause = new JButton( "Pause Game" );
        pause.setFocusable(false);
        JButton resume = new JButton( "Resume Game" );
        resume.setFocusable(false);
        // resume.setEnabled( false );
        pause.addActionListener(
            new ActionListener()
            {
                public void actionPerformed( ActionEvent e )
                {
                    theGame.pause();
                    // resume.setEnabled( true );
                    // pause.setEnabled( false );
                }
            }
        );
        
        resume.addActionListener(
            new ActionListener()
            {
                public void actionPerformed( ActionEvent e )
                {
                    theGame.resume();
                }
            }
        );
        
        // Build the button that will quit the application.
        JButton quit = new JButton( "Quit" );
        quit.setFocusable(false);
        quit.addActionListener(
            new ActionListener()
            {
                public void actionPerformed( ActionEvent e )
                {
                    theGame.quit();
                    System.exit(1);
                }
            }
        );
        
        // Add all the buttons to this panel.
        add( newGame, 0 );
        add( setLevel, 1 );
        add( setKeys, 2 );
        add( pause, 3 );
        add( resume, 4 );
        add( quit, 5 );
    }

    /**
     *  There is currently a window with 2 buttons for pre-made levels.
     *  Ideally, this would be dynamic creating as many buttons as there
     *  are level files, or a menu or something.  For now, however, this 
     *  window allos for 2 level files to be loaded, demonstrating 
     *  how the game can be dynamically loaded from level files
     *
     * @return	The window used for setting the level.
     */
    private JFrame makeSetLevelWindow()
    {
        //create the window and set the attributes
        JFrame levelSelectorWindow = new JFrame( "Level Selector" );
        levelSelectorWindow.setSize( 300, 100 );
        levelSelectorWindow.setResizable( false );
        
        //grab the content pane and set that up with a layout
        Container theContents = levelSelectorWindow.getContentPane();
        theContents.setLayout( new GridLayout( 2, 1 ) );
        
        JLabel pickNum = new JLabel( "    Pick a built in level:" );
        
        // May need to add more buttons later.
        JPanel buttonPanel = new JPanel(new GridLayout(1,2));
        JButton lev1Button = new JButton( "Level 1" );
        lev1Button.addActionListener(
            // The level 1 button's action listener... may need to
            // change file name.
            new ActionListener()
            {
                public void actionPerformed( ActionEvent e )
                {
                    theGame.setLevel( "level1.lvl" );
                    levSelectWindow.setVisible(false);
                }
            }
        );
        
	    JButton lev2Button = new JButton( "Level 2" );
	    lev2Button.addActionListener(
            // The level 2 button's action listener... may need to
            // change file name.
            new ActionListener()
            {
                public void actionPerformed( ActionEvent e )
                {
                    theGame.setLevel( "level2.lvl" );
                    levSelectWindow.setVisible(false);
                }
            }
        );
        
        //add the buttons to the button panel
        buttonPanel.add( lev1Button, 0 );
        buttonPanel.add( lev2Button, 1 );
        
        //add the panels to the content pane of the frame
        theContents.add( pickNum, 0 );
        theContents.add( buttonPanel, 1 );
        
        return levelSelectorWindow;
    }
    
    private JFrame makeKeyWindow()
    {
        //create the window and set the attributes
        JFrame KeyWindow = new JFrame( "Key Selector" );
        KeyWindow.setSize( 200, 400 );
        KeyWindow.setResizable( false );
        KeyWindow.setLocationRelativeTo(null);
        
        
        //grab the content pane and set that up with a layout
        Container theContents = KeyWindow.getContentPane();
        theContents.setLayout( new BorderLayout() );
        
        JLabel pickNum = new JLabel( "Select Keys Home Boy" );
        
        
        // ******************************************
        // --------------   PLAYER 1 ----------------
        // ******************************************
        JPanel buttonPanel1 = new JPanel(new GridLayout(6,2));
        
        JLabel upLabel1 = new JLabel( "Up" );
        JLabel downLabel1 = new JLabel( "Down" );
        JLabel leftLabel1 = new JLabel( "left" );
        JLabel rightLabel1 = new JLabel( "Right" );
        JLabel fireLabel1 = new JLabel( "Fire" );
        
        JTextField upText1 = new JTextField( "" );
        JTextField downText1 = new JTextField( "" );
        JTextField leftText1 = new JTextField( "" );
        JTextField rightText1 = new JTextField( "" );
        JTextField fireText1 = new JTextField( "" );
        
        System.out.println(play1cmd);
        upText1.addKeyListener(new myKeyListener(0, play1cmd));
        downText1.addKeyListener(new myKeyListener(1, play1cmd));
        leftText1.addKeyListener(new myKeyListener(3, play1cmd));
        rightText1.addKeyListener(new myKeyListener(2, play1cmd));
        fireText1.addKeyListener(new myKeyListener(4, play1cmd));
        
        //add the buttons to the button panel
        buttonPanel1.add( upLabel1);
        buttonPanel1.add( upText1);
        buttonPanel1.add( downLabel1);
        buttonPanel1.add( downText1);
        buttonPanel1.add( leftLabel1);
        buttonPanel1.add( leftText1);
        buttonPanel1.add( rightLabel1);
        buttonPanel1.add( rightText1);
        buttonPanel1.add( fireLabel1);
        buttonPanel1.add( fireText1);
        
        // ******************************************
        // --------------   PLAYER 2 ----------------
        // ******************************************
        JPanel buttonPanel2 = new JPanel(new GridLayout(6,2));
        
        JLabel upLabel2 = new JLabel( "Up" );
        JLabel downLabel2 = new JLabel( "Down" );
        JLabel leftLabel2 = new JLabel( "Left" );
        JLabel rightLabel2 = new JLabel( "Right" );
        JLabel fireLabel2 = new JLabel( "Fire" );
        
        JTextField upText2 = new JTextField( "" );
        JTextField downText2 = new JTextField( "" );
        JTextField leftText2 = new JTextField( "" );
        JTextField rightText2 = new JTextField( "" );
        JTextField fireText2 = new JTextField( "" );
        
        upText2.addKeyListener(new myKeyListener(5, play2cmd));
        downText2.addKeyListener(new myKeyListener(6, play2cmd));
        leftText2.addKeyListener(new myKeyListener(8, play2cmd));
        rightText2.addKeyListener(new myKeyListener(7, play2cmd));
        fireText2.addKeyListener(new myKeyListener(9, play2cmd));
        
        //add the buttons to the button panel
        buttonPanel2.add( upLabel2);
        buttonPanel2.add( upText2);
        buttonPanel2.add( downLabel2);
        buttonPanel2.add( downText2);
        buttonPanel2.add( leftLabel2);
        buttonPanel2.add( leftText2);
        buttonPanel2.add( rightLabel2);
        buttonPanel2.add( rightText2);
        buttonPanel2.add( fireLabel2);
        buttonPanel2.add( fireText2);
        
        JPanel buttonSubmit = new JPanel (new GridLayout(1,1));
        JButton submit = new JButton ("Submit");
        submit.addActionListener(
                new ActionListener()
                {
                    public void actionPerformed( ActionEvent e )
                    {
                    	theGame.changeKeys(play1cmd, play2cmd);
                    	keySelectWindow.setVisible(false);
                    	keySelectWindow.dispose();
                    }
                }
            );
        buttonSubmit.add(submit);
        
        //add the panels to the content pane of the frame
        theContents.add( pickNum, BorderLayout.NORTH );
        theContents.add( buttonPanel1, BorderLayout.WEST);
        theContents.add( buttonPanel2, BorderLayout.EAST);
        theContents.add( buttonSubmit, BorderLayout.SOUTH);
        
        return KeyWindow;
    }
    
    private class myKeyListener implements KeyListener {
    	
    	private int id;
    	private ArrayList<Integer> cmd;
    	
    	public myKeyListener(int num, ArrayList<Integer> cmd) {
    		this.id = num;
    		this.cmd = cmd;
    	}

    	public int getId() {
    		return id;
    	}

    	public void setId(int id) {
    		this.id = id;
    	}
    	
    	public void keyPressed(KeyEvent keyEvent) {
            printIt("Pressed", keyEvent);
        }
          
    	public void keyReleased(KeyEvent keyEvent) {
    		int keyCode = keyEvent.getKeyCode();
            printIt("Released", keyEvent);
            for (int i = 0; i < 10; i++) {
            	if (id == i) {
            		if (i >= 5) {
            			cmd.set(i-5, keyCode);
            		} else {
            			cmd.set(i, keyCode);
            		}
            	}
            }
    		System.out.println(cmd);
        }
          
    	private void printIt(String title, KeyEvent keyEvent) {
            int keyCode = keyEvent.getKeyCode();
            String keyText = KeyEvent.getKeyText(keyCode);
            System.out.println(title + " : " + keyText + " : " + keyCode + " : " + getId());
        }
    	
    	@Override
    	public void keyTyped(KeyEvent arg0) {
    		// TODO Auto-generated method stub
    		// do nothing
    	}
    	/**
    	 * @param args
    	 */

    }
}
