package chat.model;

import java.util.Scanner;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.text.DecimalFormat;

import twitter4j.TwitterException;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.GeoLocation;
import twitter4j.Paging;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.ResponseList;
import twitter4j.Status;

import chat.controller.ChatController;
import chat.controller.IOController;

public class ChatTwitter
{
	private ChatController app;
	private Twitter chatTwitter;
	
	public ChatTwitter(ChatController app)
	{
		this.app = app;
		this.chatTwitter = TwitterFactory.getSingleton();
	}
}
