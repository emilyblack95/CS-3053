package View;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import Controller.Controller;
import Model.Listener;
import Model.Model;

/** 
 * View for Assignment 2 - HCI
 * 2/15/17
 * @author Emily
 * Navigation Ways:
 * 1) Buttons
 * 2) ComboBox
 * 3) Keybindings (Control Left/Right arrow)
 * 4) Click on images (Left/Right mouse button)
 * 5) Slideshow
 */

@SuppressWarnings("serial")
public class View extends JFrame implements Listener
{
	private final Model model;
	private ImageIcon imageIcon;
	private Image image;
	private JLabel imageLabel;
	private JPanel contentPane;
	@SuppressWarnings("rawtypes")
	private JComboBox comboBox;
	
	private static final char NEXT_MNEMONIC = '>';
	private static final char PREVIOUS_MNEMONIC = '<';
	private static final int NEXT_ACCELERATOR = KeyEvent.VK_RIGHT;
	private static final int PREVIOUS_ACCELERATOR = KeyEvent.VK_LEFT;
	
	public View( final Model model, final Controller controller )
	{
		super( "Emily's Awesome Image Viewer" );
		
		this.model = model;
		
		model.addListener(this);
		
		setBounds(100, 100, 450, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		init( controller );
	}
	
	@SuppressWarnings("rawtypes")
	private void init( final Controller controller )
	{
		setLayout( new BorderLayout() );
		
		/* ContentPane */
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		contentPane.addComponentListener(controller);
		
		/* Menu Bar */
		final JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		/* Help JMenuItem */
		JMenuItem mntmHelp = new JMenuItem("Help");
		mntmHelp.setName("help");
		mntmHelp.setToolTipText("Click to get more information.");
		mntmHelp.addActionListener(controller);	
		
		/* JLabel for Image */
		imageLabel = new JLabel("Click \"Help\" to learn how to import images and use the navigation window.");
		imageLabel.addMouseListener(controller);
		contentPane.add(imageLabel, BorderLayout.CENTER);	
		
		/* Import JMenuItem + JFileChooser */
		JMenuItem mntmImport = new JMenuItem("Import");
		mntmImport.setName("import");
		mntmImport.setToolTipText("Click to import directory.");
		mntmImport.addActionListener(controller);
		
		menuBar.add(mntmImport);
		menuBar.add(mntmHelp);
		
		/* JComboBox list for filteredImages */
		comboBox = new JComboBox();
		comboBox.setName("comboBox");
		comboBox.setBorder(new CompoundBorder(new EmptyBorder(5, 5, 5, 50), new BevelBorder(BevelBorder.LOWERED)));
		comboBox.setToolTipText("Click to view a specific image.");
		comboBox.addActionListener(controller);
		contentPane.add(comboBox, BorderLayout.NORTH);
		
		/* Panel for buttons/spinner/slideshow */
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);
		
		/* Left Button */
		JButton left = new JButton("<");
		left.setName("left");
		left.setToolTipText("Click to toggle left.");
		left.addActionListener(controller);
		panel.add(left);
		
		/* Menu for Navigation */
		final JMenu nav = new JMenu( "Navigation" );
		nav.setMnemonic( 'n' );
		menuBar.add(nav);
		
		/* Right Keybind */
		final JMenuItem forward = new JMenuItem( "Next" );
		forward.setToolTipText("Hit control-right arrow to flip to the next image.");
		forward.setName("nextKeybind");
		forward.setAccelerator(KeyStroke.getKeyStroke( NEXT_ACCELERATOR, InputEvent.CTRL_DOWN_MASK ) );
		forward.setMnemonic( NEXT_MNEMONIC );
		forward.addActionListener(controller);
		
		/* Left Keybind */
		final JMenuItem back = new JMenuItem( "Previous" );
		back.setToolTipText("Hit control-left arrow to flip to the previous image.");
		back.setName("prevKeybind");
		back.setAccelerator(KeyStroke.getKeyStroke( PREVIOUS_ACCELERATOR, InputEvent.CTRL_DOWN_MASK ) );
		back.setMnemonic( PREVIOUS_MNEMONIC );
		back.addActionListener(controller);
		
		nav.add( forward );
		nav.add( back );

		/* Right Button */
		JButton right = new JButton(">");
		right.setName("right");
		right.setToolTipText("Click to toggle right.");
		right.addActionListener(controller);
		panel.add(right);
		
		/* Slideshow toggle */
		JButton btnSlideshow = new JButton("Slideshow");
		btnSlideshow.setName("slideshow");
		btnSlideshow.setToolTipText("Click to toggle slideshow on/off.");
		btnSlideshow.addActionListener(controller);
		panel.add(btnSlideshow); 
		
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void updated() {
		imageIcon = new ImageIcon(model.getFilteredImage(model.getPointer()));
		comboBox.setModel(new DefaultComboBoxModel(model.getFilteredImages()));
		comboBox.setSelectedItem(model.getFilteredImage(model.getPointer()));
		image = imageIcon.getImage().getScaledInstance(contentPane.getWidth(), contentPane.getHeight(), Image.SCALE_FAST);
		imageIcon = new ImageIcon(image);
		imageLabel.setIcon(imageIcon);
		
	}
}
