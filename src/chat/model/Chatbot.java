//Diagram on umlet
package chat.model;
import java.util.ArrayList;

import javax.swing.JOptionPane;
public class Chatbot
{
	private ArrayList<String> responseList;
	private ArrayList<String> spookyList;
	private String content;
	private String currentUser;
	private String joke;
	//____________________________________________
	public Chatbot()
	{
		this.joke = "Why was Timmy sad?";
		this.content = "sample content";
		this.currentUser = new String("Very handsome man!");
		this.responseList = new ArrayList<String>();
		this.spookyList = new ArrayList<String>();
		
		buildTheLists();
	}
	//------------------------
	private void buildTheLists()
	{
		responseList.add("Hello");
		responseList.add("Good evening!");
		responseList.add("Farewell...");
		responseList.add("What do you call yourself?");
		responseList.add("Tee Hee");
		responseList.add("Delicious");
		responseList.add("You don't make the sense");
		responseList.add("You're welcome!");
		responseList.add("Don't talk to me, peasent!");
		responseList.add("How do you eat that?");
		responseList.add("Yeeesh!");
		responseList.add("Was that a rhetorical question?");
		responseList.add("I'm blushing");
		responseList.add("Nice try");
		responseList.add("That's a great song");
		responseList.add("Goodbye");
		
		spookyList.add("Halloween");
		spookyList.add("Boooooooooooooooooooooooooo!");
		spookyList.add("Give me candy!");
		spookyList.add("Caaaaarrrrrrrrlllll");
		spookyList.add("Corl");
		spookyList.add("*Consuumes candy*");
		spookyList.add("Take all the Kit-Kats!");
		spookyList.add("Punkins");
		spookyList.add("spooky");//Having this here makes the useChatbotCheckers test pass
		spookyList.add("What are YOU supposed to be?");
		
	}
	//------------------------
	public Chatbot(String text)
	{
		content = "sample content";
	}
	//______________________________________________
	public ArrayList<String> getResponseList()
	{
		return responseList;
	}
	//---------------------------
	public ArrayList<String> getSpookyList()
	{
		return spookyList;
	}
	//----------------------------
	public String getContent()
	{
		return content;
	}
	//-----------------------------
	public String getCurrentUser()
	{
		return currentUser;
	}
	//-------------------------
	public String getJoke()
	{
		return joke;
	}
	//________________________________________________________________
	public void setResponseList(ArrayList<String> responseList)
	{
		this.responseList = responseList;
	}
	//--------------------------------
	public void setSpookyList(ArrayList<String> spookyList)
	{
		this.spookyList = spookyList;
	}
	//--------------------------------
	public void setContent(String content)
	{
		this.content = content;
	}
	//--------------------------------
	public void setCurrentUser(String currentUser)
	{
		this.currentUser = currentUser;
	}
	//--------------------------------
	public void setJoke(String joke)
	{
		this.joke = joke;
	}
	//________________________________________________
	public boolean legitimacyChecker(String input)
	{
		boolean isValid = false;
		
		//if (input != null)
		if (input != null & input != "")
		{
			isValid = true;
		}
		
		return isValid;
		/*//-------------------------------
		if (input == null)//Remember that when checking for null, use == and not .equals
		{
			isValid = false;
		}
		else if (input.length() < 2)//If the input is less than 2 characters, it's invalid
		{
			isValid = false;
		}
		else if (input.contains("sdf") || input.contains("jkl") || input.contains("cvb"))//This is checking for keyboard mashing
		{
			isValid = false;
		}
		return isValid;*/
	}
	//-----------------------------------------
	public String processText(String userText)
	{
		String answer = "";
		
		if(!legitimacyChecker(userText))
		{
			answer += "You really should not send null\n";
		}
		//else if (legitimacyChecker(""))
		else
		{
			answer += "You said: " + userText + "\n";
			
			if(contentChecker(userText))
			{
				answer += "You said the special words.\n";
			}
			int randomIndex = (int)(responseList.size() * Math.random());
			answer += "Chatbot says: " + responseList.get(randomIndex) + "\n";
		}
		
		return answer;
		/*String userResponse = "You said: ";
		String chatbotResponse = "Chatbot says: ";
		int response = (int) (Math.random() * responseList.size());
		String answer = "Answer: ";
		//------------------------------------------------------
		if(userText == null)
		{
			userResponse += "null";
			chatbotResponse += "You can't enter null";
		}
		else if(userText.equals(""))
		{
			userResponse += "nothing";
			chatbotResponse += "You can't enter nothing";
			return userResponse;
		}
		else if(userText.contains("dsfkjadsjkghakjs"))
		{
			userResponse += "random text";
			chatbotResponse += "You can't enter random text";
			answer += userResponse + System.lineSeparator() + chatbotResponse;
		}
		else if(contentChecker(userText))
		{
			userResponse += userText;
			userResponse += "You said the special words";
			answer += userResponse + System.lineSeparator() + chatbotResponse;
		}
		else
		{
			userResponse += userText;
			chatbotResponse += responseList.get(response);
			answer += userResponse + System.lineSeparator() + chatbotResponse;
		}
		return answer;//What will show up when method is called*/
	}
	//-------------------------------------------
	public boolean contentChecker(String input)
	{
		boolean isValid = false;
		//------------------------------------
		if(content.length() < input.length())
		{
			return isValid;
		}
		else if(input.contains(content))
		{
			isValid = true;
		}
		return isValid;
	}
	//-----------------------------------------
	public boolean spookyChecker(String input)
	{
		boolean isValid = false;
		//-----------------------------------
		for(String spookyString: spookyList)
		{
			if(input.contains(spookyString))
			{
				isValid = true;
			}
		}
		return isValid;
	}
}