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
	private List<Status> searchedTweets;
	private List<String> tweetedWords;
	private long totalWordCount;
	private HashMap<String, Integer> wordsAndCount;
	
	public ChatTwitter(ChatController app)
	{
		this.app = app;
		this.chatTwitter = TwitterFactory.getSingleton();
		this.searchedTweets = new ArrayList<Status>();
		this.tweetedWords = new ArrayList<String>();
		this.wordsAndCount = new HashMap<String, Integer>();
		this.totalWordCount = 0;
	}
	
	public void sendTweet(String textToTweet)
	{
		try
		{
			chatTwitter.updateStatus(textToTweet + " @ChatbotCTEC");
		}
		catch(TwitterException tweetError)
		{
			app.handleErrors(tweetError);
		}
		catch(Exception otherError)
		{
			app.handleErrors(otherError);
		}
	}
	
	private void collectTweets(String username)
	{
		
	}
}
