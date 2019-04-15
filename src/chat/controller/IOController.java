//_____________________________________________________________
//-------------------------------------------------------------
package chat.controller;
//-------------------------------------------------------------
import java.util.Calendar;
import java.util.Scanner;
import java.io.IOException;
import java.io.File;
import java.io.PrintWriter;
//_____________________________________________________________
public class IOController
{
	public static void saveText(ChatController app, String path, String textToSave)
	{
		try
		{
			String filename = path;
			Calendar date = Calendar.getInstance();
			filename += "/" + date.get(Calendar.MONTH) + " " + date.get(Calendar.DAY_OF_MONTH);//Adds in the month and day
			filename += date.get(Calendar.HOUR)+ "-" + date.get(Calendar.MINUTE);//Adds in the minute and second
			filename += " chatbot save.txt"; 
			//-------------------------------------------------------------
			File saveFile = new File(filename);//new file is constructed
			Scanner textScanner = new Scanner(textToSave);//New scanner is constructed
			PrintWriter saveText = new PrintWriter(saveFile);//New printWriter is constructed
			//-------------------------------------------------------------
			while(textScanner.hasNext())
			{
				String currentLine = textScanner.nextLine();
				saveText.println(currentLine);
			}
			//-------------------------------------------------------------
			textScanner.close();
			saveText.close();
		}
		//_____________________________________________________________
		catch(IOException error)
		{
			app.handleErrors(error);
		}
		//-------------------------------------------------------------
		catch(Exception genericError)
		{
			app.handleErrors(genericError);
		}
	}
	//_____________________________________________________________
	public static String loadFile(ChatController app, String path)
	{
		String contents = "";
		//-------------------------------------------------------------
		try
		{
			File saveFile = new File(path);
			Scanner fileScanner;
			if(saveFile.exists())
			{
				fileScanner = new Scanner(saveFile);
				while(fileScanner.hasNext())
				{
					contents += fileScanner.nextLine() + "\n";
				}
				//-------------------------------------------------------------
				fileScanner.close();
			}
		}
		//_____________________________________________________________
		catch(IOException error)
		{
			app.handleErrors(error);
		}
		//-------------------------------------------------------------
		catch(Exception genericError)
		{
			app.handleErrors(genericError);
		}
		//-------------------------------------------------------------
		return contents;
	}
	//_____________________________________________________________
}