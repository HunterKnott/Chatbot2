package chat.view;

import javax.swing.*;
import chat.controller.ChatController;
import chat.controller.IOController;
//-------------------------------------------------------------
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

public class ChatPanel extends JPanel
{
	private ChatController appController;
	private SpringLayout appLayout;
	
	private JButton chatButton;
	private JButton checkerButton;
	private JButton loadButton;
	private JButton saveButton;
	private JButton resetButton;
	private JButton tweetButton;
	private JButton searchTwitterButton;
	
	private ImageIcon chatIcon;
	private ImageIcon checkerIcon;
	private ImageIcon loadIcon;
	private ImageIcon saveIcon;
	private ImageIcon resetIcon;
	private ImageIcon tweetIcon;
	private ImageIcon searchIcon;
	
	private JTextField chatField;
	private JTextArea chatArea;
	private JScrollPane chatPane;
	private JPanel buttonPanel;
	
	public ChatPanel(ChatController appController)
	{
		super();
		
		this.appController = appController;
		appLayout = new SpringLayout();
		
		chatIcon = new ImageIcon(getClass().getResource("/chat/view/images/chat.png"));
		//checkerIcon = new ImageIcon(getClass().getResource("/chat/view/images/"));
		loadIcon = new ImageIcon(getClass().getResource("/chat/view/images/load.png"));
		saveIcon = new ImageIcon(getClass().getResource("/chat/view/images/save.png"));
		resetIcon = new ImageIcon(getClass().getResource("chat/view/images/reset.png"));
		tweetIcon = new ImageIcon(getClass().getResource("/chat/view/images/tweet.png"));
		searchIcon = new ImageIcon(getClass().getResource("/chat/view/images/searchTwitter.png"));
		
		chatButton = new JButton("Chat", chatIcon);
		loadButton = new JButton("Load", loadIcon);
		saveButton = new JButton("Save", saveIcon);
		checkerButton = new JButton("Check Text");
		resetButton = new JButton("reset", resetIcon);
		tweetButton = new JButton("Send Tweet", tweetIcon);
		searchTwitterButton = new JButton("Search Twitter", searchIcon);
		chatPane = new JScrollPane();
		chatArea = new JTextArea("Chat Area", 20, 50);
		//("Text", rows, columns)
		chatPane.setViewportView(chatArea);
		chatField = new JTextField("Talk to the bot here", 50);
		
		buttonPanel = new JPanel(new GridLayout(1, 0));
		appLayout.putConstraint(SpringLayout.NORTH, buttonPanel, 31, SpringLayout.SOUTH, chatField);
		appLayout.putConstraint(SpringLayout.WEST, buttonPanel, 0, SpringLayout.WEST, chatPane);
		
		setupButtonPanel();
		setupScrollPane();
		setupPanel();
		setupLayout();
		setupListeners();
	}
	
	private void setupButtonPanel()
	{
		buttonPanel.add(saveButton);
		buttonPanel.add(loadButton);
		buttonPanel.add(chatButton);
		buttonPanel.add(checkerButton);
		buttonPanel.add(tweetButton);
		buttonPanel.add(searchTwitterButton);
	}
	
	private void setupPanel()
	{
		this.setLayout(appLayout);
		this.setPreferredSize(new Dimension(1024, 768));
		this.setBackground(Color.GREEN);
		buttonPanel.setPreferredSize(new Dimension(900, 150));
		buttonPanel.setBackground(Color.BLUE);
		this.add(buttonPanel);
		this.add(chatPane);
		this.add(chatField);
	}
	
	private void setupLayout()
	{
		appLayout.putConstraint(SpringLayout.NORTH, chatPane, 10, SpringLayout.NORTH, this);
		appLayout.putConstraint(SpringLayout.WEST, chatPane, 10, SpringLayout.WEST, this);
		appLayout.putConstraint(SpringLayout.SOUTH, chatPane, -302, SpringLayout.SOUTH, this);
		appLayout.putConstraint(SpringLayout.EAST, chatPane, -32, SpringLayout.EAST, this);
		appLayout.putConstraint(SpringLayout.NORTH, chatArea, 224, SpringLayout.NORTH, this);
		appLayout.putConstraint(SpringLayout.WEST, chatArea, -391, SpringLayout.EAST, this);
		appLayout.putConstraint(SpringLayout.SOUTH, chatArea, 353, SpringLayout.NORTH, this);
		appLayout.putConstraint(SpringLayout.EAST, chatArea, -88, SpringLayout.EAST, this);
		appLayout.putConstraint(SpringLayout.SOUTH, chatField, -270, SpringLayout.SOUTH, this);
		appLayout.putConstraint(SpringLayout.EAST, chatField, -157, SpringLayout.EAST, this);
		appLayout.putConstraint(SpringLayout.NORTH, chatField, 6, SpringLayout.SOUTH, chatPane);
		appLayout.putConstraint(SpringLayout.WEST, chatField, 10, SpringLayout.WEST, chatPane);
	}
	
	private void setupListeners()
	{
		chatButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent click)
			{
				String input = chatField.getText();
				String output = "";
				output = appController.interactWithChatbot(input);
				chatArea.append(output);
				chatField.setText("");
				chatArea.setCaretPosition(chatArea.getDocument().getLength());
			}
		});
		
		loadButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent click)
			{
				String path = getPath("load");
				String chatText = IOController.loadFile(appController, path);
				chatArea.setText(chatText);
			}
		});
		
		saveButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent click)
			{
				String chatText = chatArea.getText();
				String path = ".";
				IOController.saveText(appController, path, chatText);
				chatArea.setText("Chat saved!");
			}
		});
		
		checkerButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent click)
			{
				
			}
		});
		
		resetButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent click)
			{
				
			}
		});
	}
	
	private void setupScrollPane()
	{
		chatArea.setEditable(false);
		chatArea.setLineWrap(true);
		chatArea.setWrapStyleWord(true);
		chatPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		chatPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		chatPane.setViewportView(chatArea);
	}
	
	private String getPath(String choice)
	{
		String path = ".";
		int result = -99;
		JFileChooser fileChooser = new JFileChooser();
		if(choice.equals("save"))
		{
			fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			result = fileChooser.showSaveDialog(this);
			if(result == JFileChooser.APPROVE_OPTION)
			{
				path = fileChooser.getCurrentDirectory().getAbsolutePath();
			}
		}
		else
		{
			result = fileChooser.showOpenDialog(this);
			if(result == JFileChooser.APPROVE_OPTION)
			{
				path = fileChooser.getSelectedFile().getAbsolutePath();
			}
		}
		return path;
	}
}