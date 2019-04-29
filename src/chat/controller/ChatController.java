package chat.controller;

import chat.view.ChatFrame;
//import model.ArrayList;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import chat.model.*;


public class ChatController
{
	private Chatbot simplebot;
	private ChatFrame appFrame;
	private ChatTwitter myTwitter;

	public ChatController()
	{
		simplebot = new Chatbot();
		appFrame = new ChatFrame(this);
		myTwitter = new ChatTwitter(this);
	}

	public Chatbot getChatbot()
	{
		return simplebot;
	}

	public void start()
	{
		
	}

	public String interactWithChatbot(String userInput)
	{
		//String output = "You said: " + userInput + "Chatbot says: " + simplebot.processText(userInput);
		String output = "";
		output += simplebot.processText(userInput);
		return output;
	}

	public String useChatbotCheckers(String input)
	{
		String output = "";
		if (simplebot.spookyChecker(input))//Use the object simplebot, not the class Chatbot
		{
			output = "You said a spooky word. Halloween";
		}
		
		return output;
	}

	private void close()
	{
		System.exit(0);
	}
	
	public void handleErrors(Exception error)
	{
		JOptionPane.showMessageDialog(appFrame, error.getMessage());
	}
	
	public ChatFrame getAppFrame()
	{
		return appFrame;
	}
	
	public void tweet(String text)
	{
		myTwitter.sendTweet(text);
	}
}