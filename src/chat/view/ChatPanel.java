//_____________________________________________________________
//-------------------------------------------------------------
package chat.view;

import javax.swing.*;
import chat.controller.ChatController;
import chat.controller.IOController;
//-------------------------------------------------------------
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Dimension;
//_____________________________________________________________
public class ChatPanel extends JPanel
{
	private ChatController appController;
	private SpringLayout appLayout;
	private JButton chatButton;
	private JButton checkerButton;
	private JButton loadButton;
	private JButton saveButton;
	private JButton resetButton;
	private JTextField chatField;
	private JTextArea chatArea;
	private JScrollPane chatPane;
	//_____________________________________________________________
	public ChatPanel(ChatController appController)
	{
		super();
		//-------------------------------------------------------------
		this.appController = appController;
		appLayout = new SpringLayout();
		//-------------------------------------------------------------
		chatButton = new JButton("Chat");
		loadButton = new JButton("Load");
		saveButton = new JButton("Save");
		checkerButton = new JButton("Check Text");
		resetButton = new JButton("reset");
		chatPane = new JScrollPane();
		chatArea = new JTextArea("Chat Area", 20, 50);
		//("Text", rows, columns)
		chatPane.setViewportView(chatArea);
		chatField = new JTextField("Talk to the bot here", 50);
		
		//-------------------------------------------------------------
		setupScrollPane();
		setupPanel();
		setupLayout();
		setupListeners();
	}
	//_____________________________________________________________
	private void setupPanel()
	{
		this.setLayout(appLayout);
		this.setPreferredSize(new Dimension(800, 600));
		this.setBackground(Color.GREEN);
		this.add(chatButton);
		this.add(loadButton);
		this.add(saveButton);
		this.add(chatPane);
		this.add(checkerButton);
		this.add(resetButton);
		this.add(chatField);
	}
	//_____________________________________________________________
	private void setupLayout()
	{
		appLayout.putConstraint(SpringLayout.SOUTH, chatButton, -267, SpringLayout.SOUTH, this);
		appLayout.putConstraint(SpringLayout.EAST, chatButton, -32, SpringLayout.EAST, this);
		appLayout.putConstraint(SpringLayout.EAST, loadButton, -318, SpringLayout.EAST, this);
		appLayout.putConstraint(SpringLayout.EAST, saveButton, -452, SpringLayout.EAST, this);
		appLayout.putConstraint(SpringLayout.NORTH, loadButton, 0, SpringLayout.NORTH, saveButton);
		appLayout.putConstraint(SpringLayout.WEST, loadButton, 6, SpringLayout.EAST, saveButton);
		appLayout.putConstraint(SpringLayout.NORTH, chatButton, 6, SpringLayout.SOUTH, chatPane);
		appLayout.putConstraint(SpringLayout.NORTH, chatPane, 10, SpringLayout.NORTH, this);
		appLayout.putConstraint(SpringLayout.WEST, chatPane, 10, SpringLayout.WEST, this);
		appLayout.putConstraint(SpringLayout.SOUTH, chatPane, -302, SpringLayout.SOUTH, this);
		appLayout.putConstraint(SpringLayout.EAST, chatPane, -32, SpringLayout.EAST, this);
		appLayout.putConstraint(SpringLayout.NORTH, chatArea, 224, SpringLayout.NORTH, this);
		appLayout.putConstraint(SpringLayout.WEST, chatArea, -391, SpringLayout.EAST, this);
		appLayout.putConstraint(SpringLayout.SOUTH, chatArea, 353, SpringLayout.NORTH, this);
		appLayout.putConstraint(SpringLayout.EAST, chatArea, -88, SpringLayout.EAST, this);
		appLayout.putConstraint(SpringLayout.WEST, saveButton, 6, SpringLayout.EAST, checkerButton);
		appLayout.putConstraint(SpringLayout.WEST, checkerButton, 86, SpringLayout.WEST, this);
		appLayout.putConstraint(SpringLayout.EAST, checkerButton, -586, SpringLayout.EAST, this);
		appLayout.putConstraint(SpringLayout.NORTH, saveButton, 24, SpringLayout.SOUTH, chatField);
		appLayout.putConstraint(SpringLayout.NORTH, checkerButton, 24, SpringLayout.SOUTH, chatField);
		appLayout.putConstraint(SpringLayout.WEST, chatButton, 6, SpringLayout.EAST, chatField);
		appLayout.putConstraint(SpringLayout.SOUTH, chatField, -270, SpringLayout.SOUTH, this);
		appLayout.putConstraint(SpringLayout.EAST, chatField, -157, SpringLayout.EAST, this);
		appLayout.putConstraint(SpringLayout.NORTH, chatField, 6, SpringLayout.SOUTH, chatPane);
		appLayout.putConstraint(SpringLayout.WEST, chatField, 10, SpringLayout.WEST, chatPane);
	}
	//_____________________________________________________________
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
		//-------------------------------------------------------------
		loadButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent click)
			{
				String path = getPath("load");
				String chatText = IOController.loadFile(appController, path);
				chatArea.setText(chatText);
			}
		});
		//-------------------------------------------------------------
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
		//-------------------------------------------------------------
		checkerButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent click)
			{
				
			}
		});
		//-------------------------------------------------------------
		resetButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent click)
			{
				
			}
		});
	}
	//_____________________________________________________________
	private void setupScrollPane()
	{
		chatArea.setEditable(false);
		chatArea.setLineWrap(true);
		chatArea.setWrapStyleWord(true);
		chatPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		chatPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		chatPane.setViewportView(chatArea);
	}
	//_____________________________________________________________
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