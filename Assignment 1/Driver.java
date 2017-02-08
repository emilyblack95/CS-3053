import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import ae.java.awt.event.KeyEvent;
import javax.swing.JFileChooser;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.KeyStroke;
import javax.swing.Timer;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.File;
import java.awt.event.ActionEvent;
import javax.swing.border.BevelBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/** 
 * Driver for Assignment 1 - HCI
 * 2/7/17
 * @author Emily
 * Navigation Ways:
 * 1) Buttons
 * 2) ComboBox
 * 3) Keybindings (Left/Right arrow)
 * 4) Click on images (Left/Right mouse button)
 * 5) Slideshow
 *
 */
@SuppressWarnings("serial")
public class Driver extends JFrame {

	private JPanel contentPane;
	private String[] filteredImages; /* Images filtered from directory */
	private int pointer; /* Used to index filteredImages */
	private ImageIcon imageIcon;
	private Image image;
	private Timer timer; /* Timer for slideshow */
	@SuppressWarnings("rawtypes")
	private JComboBox comboBox;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Driver frame = new Driver();
					frame.setVisible(true);
					frame.setTitle("Emily's Awesome Image Viewer");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Driver() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		/* JMenuBar */
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		/* Help JMenuItem */
		JMenuItem mntmHelp = new JMenuItem("Help");
		mntmHelp.setToolTipText("Click to get more information.");
		mntmHelp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(getParent(), "Click the import button at the top right to import a directory and get started." + '\n'
						+ "The 5 different navigation techniques include:" + '\n' + '\n' 
						+ "1) Buttons" + '\n' + "2) ComboBox" + '\n' + "3) Keybindings (Left/Right Arrow)" + '\n' + 
						"4) Click on Image (Left/Right Click)" + '\n' + "5) Slideshow (Click to Toggle ON/OFF)");
			}
		});
		
		/* JLabel for Image */
		JLabel imageLabel = new JLabel("Click \"Help\" to learn how to import images and use the navigation window.");
		imageLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int buttonClicked = e.getButton();
				
				/* Left mouse button */
				if(buttonClicked == 1 && (filteredImages != null))
				{
					if(pointer != 0)
					{
						pointer--;
						imageIcon = new ImageIcon(filteredImages[pointer]);
						comboBox.setSelectedItem(filteredImages[pointer]);
						image = imageIcon.getImage().getScaledInstance(contentPane.getWidth(), contentPane.getHeight(), Image.SCALE_FAST);
						imageIcon = new ImageIcon(image);
						imageLabel.setIcon(imageIcon);
						imageLabel.addComponentListener(new ComponentAdapter() { 
							public void componentResized(ComponentEvent e) 
							{ 
								imageLabel.setIcon(new ImageIcon(imageIcon.getImage().getScaledInstance(imageLabel.getWidth(), imageLabel.getHeight(), Image.SCALE_FAST)));
							} 
						}); 
					}
					/* If we have reached the far left */
					else if(pointer == 0)
					{
						pointer = filteredImages.length-1;
						imageIcon = new ImageIcon(filteredImages[pointer]);
						comboBox.setSelectedItem(filteredImages[pointer]);
						image = imageIcon.getImage().getScaledInstance(contentPane.getWidth(), contentPane.getHeight(), Image.SCALE_FAST);
						imageIcon = new ImageIcon(image);
						imageLabel.setIcon(imageIcon);
						imageLabel.addComponentListener(new ComponentAdapter() { 
							public void componentResized(ComponentEvent e) 
							{ 
								imageLabel.setIcon(new ImageIcon(imageIcon.getImage().getScaledInstance(imageLabel.getWidth(), imageLabel.getHeight(), Image.SCALE_FAST)));
							} 
						}); 
					}
				}
				
				/* Right mouse button */
				else if(buttonClicked == 3 && (filteredImages != null))
				{
					/* If we haven't reached the far right */
					if(pointer != filteredImages.length-1)
					{
						pointer++;
						imageIcon = new ImageIcon(filteredImages[pointer]);
						comboBox.setSelectedItem(filteredImages[pointer]);
						image = imageIcon.getImage().getScaledInstance(contentPane.getWidth(), contentPane.getHeight(), Image.SCALE_FAST);
						imageIcon = new ImageIcon(image);
						imageLabel.setIcon(imageIcon);
						imageLabel.addComponentListener(new ComponentAdapter() { 
							public void componentResized(ComponentEvent e) 
							{ 
								imageLabel.setIcon(new ImageIcon(imageIcon.getImage().getScaledInstance(imageLabel.getWidth(), imageLabel.getHeight(), Image.SCALE_FAST)));
							} 
						}); 
					}
					/* If we have reached the far right */
					else if(pointer == filteredImages.length-1)
					{
						pointer = 0; 
						imageIcon = new ImageIcon(filteredImages[pointer]);
						comboBox.setSelectedItem(filteredImages[pointer]);
						image = imageIcon.getImage().getScaledInstance(contentPane.getWidth(), contentPane.getHeight(), Image.SCALE_FAST);
						imageIcon = new ImageIcon(image);
						imageLabel.setIcon(imageIcon);
						imageLabel.addComponentListener(new ComponentAdapter() { 
							public void componentResized(ComponentEvent e) 
							{ 
								imageLabel.setIcon(new ImageIcon(imageIcon.getImage().getScaledInstance(contentPane.getWidth(), contentPane.getHeight(), Image.SCALE_FAST)));
							} 
						});  
					}
				}
			}
		});
		contentPane.add(imageLabel, BorderLayout.CENTER);		
		
		/* Import JMenuItem + JFileChooser */
		JMenuItem mntmImport = new JMenuItem("Import");
		mntmImport.setToolTipText("Click to import directory.");
		mntmImport.addActionListener(new ActionListener() {
			@SuppressWarnings({ "rawtypes", "unchecked" })
			public void actionPerformed(ActionEvent e) {
				JFileChooser importImage = new JFileChooser();
				setBounds(100, 100, 589, 453);
				
				importImage.setCurrentDirectory(new java.io.File(".")); /* Starts user off where they are at */
				importImage.setAcceptAllFileFilterUsed(false); /* Gets rid of all files option */
				importImage.setDialogTitle("Emily's Awesome Image Viewer");						
				importImage.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				
				int status = importImage.showOpenDialog(null);
				File[] images = importImage.getSelectedFile().listFiles();
				int counter = 0; /* Number of images in directory */
				if (status == JFileChooser.APPROVE_OPTION) {
					for(File x: images)
					{
						if (!x.isDirectory()) {
							if(x.getName().toLowerCase().endsWith(".jpg") || x.getName().toLowerCase().endsWith(".png") || x.getName().toLowerCase().endsWith(".gif"))
							{
								counter++;
							}
						}
					}
					
					/* Size of how many images there are in the directory */
					filteredImages = new String[counter];
					int i = 0; /* Cannot be greater than counter */
					for(File x: images)
					{
						if(!x.isDirectory())
						{
							if(x.getName().toLowerCase().endsWith(".jpg") || x.getName().toLowerCase().endsWith(".png") || x.getName().toLowerCase().endsWith(".gif"))
							{
								filteredImages[i] = x.getPath();
								i++;
							}
						}
					}
					
					imageIcon = new ImageIcon(filteredImages[pointer]);
					image = imageIcon.getImage().getScaledInstance(contentPane.getWidth(), contentPane.getHeight(), Image.SCALE_FAST);
					imageIcon = new ImageIcon(image);
					imageLabel.setIcon(imageIcon);
					imageLabel.addComponentListener(new ComponentAdapter() { 
						public void componentResized(ComponentEvent e) 
						{ 
							imageLabel.setIcon(new ImageIcon(imageIcon.getImage().getScaledInstance(imageLabel.getWidth(), imageLabel.getHeight(), Image.SCALE_FAST)));
						} 
					});
					
					comboBox = new JComboBox(filteredImages);
					comboBox.setBorder(new CompoundBorder(new EmptyBorder(5, 5, 5, 50), new BevelBorder(BevelBorder.LOWERED)));
					comboBox.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							JComboBox comboBox = (JComboBox)e.getSource();
					        String imageName = (String)comboBox.getSelectedItem(); /* Pass name of selected image to imageName */
					        imageIcon = new ImageIcon(imageName);
							image = imageIcon.getImage().getScaledInstance(contentPane.getWidth(), contentPane.getHeight(), Image.SCALE_FAST);
							imageIcon = new ImageIcon(image);
							imageLabel.setIcon(imageIcon);
							imageLabel.addComponentListener(new ComponentAdapter() { 
								public void componentResized(ComponentEvent e) 
								{ 
									imageLabel.setIcon(new ImageIcon(imageIcon.getImage().getScaledInstance(imageLabel.getWidth(), imageLabel.getHeight(), Image.SCALE_FAST)));
								} 
							});
						}
					});
					
					contentPane.add(comboBox, BorderLayout.NORTH);
				}
				else if(status == JFileChooser.CANCEL_OPTION)
				{
					importImage.cancelSelection();
				}
			}
		});

		/* Add JMenuItems to JMenuBar */
		menuBar.add(mntmImport);
		menuBar.add(mntmHelp);
		
		/* Panel for buttons/spinner/slideshow */
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);
		
		/* Left Button */
		JButton left = new JButton("<");
		left.setToolTipText("Click to toggle left.");
		panel.add(left);
		
		/* Abstract Action for keybinding */
		AbstractAction aaleft = new AbstractAction() {
            @Override
			public void actionPerformed(ActionEvent e) {
				left.requestFocusInWindow();
				/* If we haven't reached the far left */
				if(pointer != 0)
				{
					pointer--;
					imageIcon = new ImageIcon(filteredImages[pointer]);
					comboBox.setSelectedItem(filteredImages[pointer]);
					image = imageIcon.getImage().getScaledInstance(contentPane.getWidth(), contentPane.getHeight(), Image.SCALE_FAST);
					imageIcon = new ImageIcon(image);
					imageLabel.setIcon(imageIcon);
					imageLabel.addComponentListener(new ComponentAdapter() { 
						public void componentResized(ComponentEvent e) 
						{ 
							imageLabel.setIcon(new ImageIcon(imageIcon.getImage().getScaledInstance(imageLabel.getWidth(), imageLabel.getHeight(), Image.SCALE_FAST)));
						} 
					}); 
				}
				/* If we have reached the far left */
				else if(pointer == 0)
				{
					pointer = filteredImages.length-1;
					imageIcon = new ImageIcon(filteredImages[pointer]);
					comboBox.setSelectedItem(filteredImages[pointer]);
					image = imageIcon.getImage().getScaledInstance(contentPane.getWidth(), contentPane.getHeight(), Image.SCALE_FAST);
					imageIcon = new ImageIcon(image);
					imageLabel.setIcon(imageIcon);
					imageLabel.addComponentListener(new ComponentAdapter() { 
						public void componentResized(ComponentEvent e) 
						{ 
							imageLabel.setIcon(new ImageIcon(imageIcon.getImage().getScaledInstance(imageLabel.getWidth(), imageLabel.getHeight(), Image.SCALE_FAST)));
						} 
					}); 
				}
			}
		};
		
		/* Keybindings for left button */
		left.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), "Left Arrow");
		left.getActionMap().put("Left Arrow", aaleft);
		left.addActionListener(aaleft);
		
		/* Right Button */
		JButton right = new JButton(">");
		right.setToolTipText("Click to toggle right.");
		panel.add(right);
		
		/* Abstract Action for keybinding */
		AbstractAction aaright = new AbstractAction() {
            @Override
			public void actionPerformed(ActionEvent e) {
				right.requestFocusInWindow();
				/* If we haven't reached the far right */
				if(pointer != filteredImages.length-1)
				{
					pointer++;
					imageIcon = new ImageIcon(filteredImages[pointer]);
					comboBox.setSelectedItem(filteredImages[pointer]);
					image = imageIcon.getImage().getScaledInstance(contentPane.getWidth(), contentPane.getHeight(), Image.SCALE_FAST);
					imageIcon = new ImageIcon(image);
					imageLabel.setIcon(imageIcon);
					imageLabel.addComponentListener(new ComponentAdapter() { 
						public void componentResized(ComponentEvent e) 
						{ 
							imageLabel.setIcon(new ImageIcon(imageIcon.getImage().getScaledInstance(imageLabel.getWidth(), imageLabel.getHeight(), Image.SCALE_FAST)));
						} 
					}); 
				}
				/* If we have reached the far right */
				else if(pointer == filteredImages.length-1)
				{
					pointer = 0; 
					imageIcon = new ImageIcon(filteredImages[pointer]);
					comboBox.setSelectedItem(filteredImages[pointer]);
					image = imageIcon.getImage().getScaledInstance(contentPane.getWidth(), contentPane.getHeight(), Image.SCALE_FAST);
					imageIcon = new ImageIcon(image);
					imageLabel.setIcon(imageIcon);
					imageLabel.addComponentListener(new ComponentAdapter() { 
						public void componentResized(ComponentEvent e) 
						{ 
							imageLabel.setIcon(new ImageIcon(imageIcon.getImage().getScaledInstance(contentPane.getWidth(), contentPane.getHeight(), Image.SCALE_FAST)));
						} 
					});  
				}
			}
		}; 
		
		/* Keybindings for right button */
		right.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), "Right Arrow");
		right.getActionMap().put("Right Arrow", aaright);
		
		right.addActionListener(aaright);
		
		/* Slideshow toggle */
		JButton btnSlideshow = new JButton("Slideshow");
		btnSlideshow.setToolTipText("Click to toggle slideshow on/off.");
		btnSlideshow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 timer = new Timer(1000, new ActionListener() {
			            @Override
			            public void actionPerformed(ActionEvent e) {
			                /* If we haven't reached the far right */
							if(pointer != filteredImages.length-1)
							{
								pointer++;
								imageIcon = new ImageIcon(filteredImages[pointer]);
								comboBox.setSelectedItem(filteredImages[pointer]);
								image = imageIcon.getImage().getScaledInstance(contentPane.getWidth(), contentPane.getHeight(), Image.SCALE_FAST);
								imageIcon = new ImageIcon(image);
								imageLabel.setIcon(imageIcon);
								imageLabel.addComponentListener(new ComponentAdapter() { 
									public void componentResized(ComponentEvent e) 
									{ 
										imageLabel.setIcon(new ImageIcon(imageIcon.getImage().getScaledInstance(imageLabel.getWidth(), imageLabel.getHeight(), Image.SCALE_FAST)));
									} 
								}); 
							}
							/* If we have reached the far right */
							else if(pointer == filteredImages.length-1)
							{
								pointer = 0; 
								imageIcon = new ImageIcon(filteredImages[pointer]);
								comboBox.setSelectedItem(filteredImages[pointer]);
								image = imageIcon.getImage().getScaledInstance(contentPane.getWidth(), contentPane.getHeight(), Image.SCALE_FAST);
								imageIcon = new ImageIcon(image);
								imageLabel.setIcon(imageIcon);
								imageLabel.addComponentListener(new ComponentAdapter() { 
									public void componentResized(ComponentEvent e) 
									{ 
										imageLabel.setIcon(new ImageIcon(imageIcon.getImage().getScaledInstance(contentPane.getWidth(), contentPane.getHeight(), Image.SCALE_FAST)));
									} 
								});  
							}
			            }
			        });
			        timer.start();
			}
		});
		panel.add(btnSlideshow); 
	}
}
