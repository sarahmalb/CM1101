import java.util.Scanner;
import javax.swing.JOptionPane;
import java.io.*;
import java.util.regex.*;
import java.util.StringTokenizer;
import java.util.ArrayList;

public class RugbyPlayers
{
	public static void main(String[] args)throws IOException
	{
		//this asks user for input, keeps it lower case and links to the method they ask for based on what they input
			String input; 
			System.out.println("Hi and Welcome to the Rugby Player 'Store'. What would you like to do? \n" +
				"To add players to a new file, enter add \n" +
				"To add players to an existing file, enter new \n" +
				"To delete a player, enter del \n" + 
				"To view players, enter view \n" +
				"To search for a player by team, enter team \n" +
				"To display a specified subset of players, enter subset \n" +
				"To view addresses, enter address");
			Scanner keyboard = new Scanner(System.in);
			input = keyboard.nextLine();
			input.toLowerCase();

			switch (input)
			{
				case "add":
					add();
					main(new String[0]);
					break;

				case "new":
					existing();
					main(new String[0]);
					break;

				case "del":
					delete();
					break;

				case "view":
				{
					//this asks user for file, checks the file exists
					//checks for a next line in the file, if its empty restarts
					//while it has a next line it prints each line to the screen
							Scanner theFile = new Scanner(System.in);
							System.out.println("What is the name of your file? (No need to add the file extension!)");
							String filename = theFile.nextLine();

							File file = new File(filename +".txt");
							if (!file.exists())
							{
								System.out.println("Your file doesn't exist try again!");
								main(new String[0]);
							}
							Scanner inputFile = new Scanner(file);

							if (!inputFile.hasNext())
							{
								System.out.println("Your file is empty!");
								main(new String[0]);
							}

							while (inputFile.hasNext())
							{
								String rugbyPlayerDetails = inputFile.nextLine();

								System.out.println(rugbyPlayerDetails);
							}
							main(new String[0]);
							break;
				}

				case "team":
					search();
					main(new String[0]);
					break;

				case "address":
					searchAddress();
					main(new String[0]);

				case "subset":
					searchPlayerIndex();
					main(new String[0]);

				default:
					System.out.println("Error! Thats not a legal input!");
					main(new String[0]);
					break;
			}


	}

	public static void add() throws IOException
	{
		//this method prompts user for a name for a file, creates the new file
		// asks the user for how many players they want to add
		//if user inputs an invalid number, restarts the method
		//otherwise for the number of players, prompts the user to input data, 
		//saves it to file
		//closes the file
		Scanner in = new Scanner(System.in);
		String newfilename;
		System.out.println("What would you like to name your file?");
		newfilename = in.nextLine();
		PrintWriter outputFile = new PrintWriter(newfilename + ".txt");
		System.out.println("How many players would you like to add?");
		int numberOfPlayers = in.nextInt();
		if (numberOfPlayers <= 0)
		{
			System.out.println("You can't do that!");
			add();
		}

		for (int i = 0; i < numberOfPlayers; ++i)
		{
			Scanner keyboard = new Scanner(System.in);
			System.out.println("What is the name of the player?");
			String playerName = keyboard.nextLine();

			System.out.println("What is their player ID?");
			String playerId = keyboard.nextLine();

			//String regexString = "[\d]{5}";
			//Pattern playerIdPattern = Pattern.compile(regexString);
			//Matcher m = Pattern.Matcher 

			System.out.println("What are the tries scored your player?");
			String triesScored = keyboard.nextLine();

			System.out.println("What is their team name?");
			String teamName = keyboard.nextLine();

			System.out.println("What is their team ID?");
			String teamId = keyboard.nextLine();

			System.out.println("What is the Stadium name?");
			String stdName = keyboard.nextLine();

			System.out.println("What is the stadium street");
			String stmStreet = keyboard.nextLine();

			System.out.println("What is the stadium town?");
			String stdTown = keyboard.nextLine();

			System.out.println("What is the stadium postcode?");
			String stdPostcode = keyboard.nextLine();

			outputFile.println(playerName + ", " + playerId + ", " + triesScored + ", " + teamName + ", " + teamId + ", " + stdName + ", " + stmStreet + ", " + stdTown + ", " + stdPostcode);



		}
		outputFile.close();
		
	}

	public static void existing() throws IOException
	{
		//this method prompts user for a name for the existing file, passes the argument true, telling
		//the filewriter that the file exists
		// asks the user for how many players they want to add
		//if user inputs an invalid number, restarts the method
		//otherwise for the number of players, prompts the user to input data, 
		//saves it to file
		//closes the file
		Scanner in = new Scanner(System.in);
		Scanner myFile = new Scanner(System.in);
		Scanner keyboard = new Scanner(System.in);
		System.out.println("What is the name of your file? (no need to add the file extension!)");
		String existingFilename = myFile.next();
		
		File file = new File(existingFilename + ".txt");
		if (!file.exists())
		{
			System.out.println("Your file doesn't exist try again!");
			search();
		}
		FileWriter append = new FileWriter (existingFilename + ".txt", true);
		PrintWriter outputData = new PrintWriter(append);

		System.out.println("How many players would you like to add?");
		int numberOfPlayers = in.nextInt();
		if (numberOfPlayers <= 0)
		{
			System.out.println("You can't do that!");
			add();
		}
		for (int i = 0; i < numberOfPlayers; ++i)
		{

		System.out.println("What is the name of the player?");
		String playerName = keyboard.nextLine();
		

		System.out.println("What is their player ID?");
		String playerId = keyboard.nextLine();
		Pattern regexString = Pattern.compile("^[RFU]{3}[\\d]{5}$");
		Matcher m = regexString.matcher(playerId);
		if (!m.find()) {
			System.out.println("That's an invalid input! You must input RFU + 5 integers!");
			existing();
		}
		//Pattern playerIdPattern = Pattern.compile(regexString);
		//Matcher m = playerIdPattern.compile 

		System.out.println("What are the tries scored your player?");
		String triesScored = keyboard.nextLine();
		Pattern re = Pattern.compile("^[\\d]+$");
		Matcher ma = re.matcher(triesScored);
		if (!ma.find()) 
		{
			System.out.println("That's an invalid input! You must input at least 1 digit.");
			existing();
		}

		System.out.println("What is their team name?");
		String teamName = keyboard.nextLine();
		Pattern reee = Pattern.compile("[\\p{L}]+[\\p{L}]*");
		Matcher maaaa = reee.matcher(teamName);
		if (!ma.find()) 
		{
			System.out.println("That's an invalid input! You must input at least 1 digit.");
			existing();
		}


		System.out.println("What is their team ID?");
		String teamId = keyboard.nextLine();
		// Pattern ree = Pattern.compile("[A-Z]{3}[0-9]{4}");
		// Matcher maa = ree.matcher(teamId);
		// if (!ma.find()) 
		// {
		// 	System.out.println("That's an invalid input! You must input 3 Uppercase letters followed by 4 digits.");
		// 	existing();
		// }


		System.out.println("What is the Stadium name?");
		String stdName = keyboard.nextLine();

		System.out.println("What is the stadium street");
		String stmStreet = keyboard.nextLine();

		System.out.println("What is the stadium town?");
		String stdTown = keyboard.nextLine();

		System.out.println("What is the stadium postcode?");
		String stdPostcode = keyboard.nextLine();

		outputData.println(playerName + ", " + playerId + ", " + triesScored + ", " + teamName + ", " + teamId + ", " + stdName + ", " + stmStreet + ", " + stdTown + ", " + stdPostcode);
	}

		outputData.close();
	}

	public static void search() throws IOException
	{
		//prompts user for file name
		//checks if file exists
		//asks for the substring of the team the person would like to search
		//while the file has a next line, assigns the line to a variable, splits it at the comma
		//creates an array, checks only the 4th position for a matching team substring
		//if it is true, prints the player completely from """while the file has a next line, assigns the line to a variable"""
		Scanner theFile = new Scanner(System.in);
		System.out.println("What is the name of your file? (No need to add the file extension!)");
		String filename = theFile.nextLine();
		Scanner teamAsker = new Scanner(System.in);
		System.out.println("What is the team you'd like to search for");
		String theTeam = teamAsker.nextLine(); 

		File file = new File(filename + ".txt");
		if (!file.exists())
		{
			System.out.println("Your file doesn't exist try again!");
			search();
		}
		Scanner inputFile = new Scanner(file);

		while (inputFile.hasNext())
		{
			String incorrectPlayer = inputFile.nextLine();
			String rugbyPlayer[] = incorrectPlayer.split(", ");
			if (rugbyPlayer[3].contains(theTeam))
			{

				 System.out.println(incorrectPlayer);
			}
		}
		main(new String[0]);
	}

	public static void searchAddress() throws IOException
	{
		////prompts user for file name and also for a part of the stadium address
		//checks if file exists
		//then puts each player 1-by-1 in the file into a variable 
		//check the file has a next line it can iterate through
		//creates a rugbyplayer array with the player variable all in lowercase and split at the commas
		//checks the array at the 5-8th positions if it contains a substring of the stadium address
		//will print the stadium address if any match/contain
		Scanner theFile = new Scanner(System.in);
		System.out.println("What is the name of your file? (No need to add the file extension!)");
		String filename = theFile.nextLine();
		Scanner teamAsker = new Scanner(System.in);
		System.out.println("What is the stadium address you'd like to search for");
		String addressAsked = teamAsker.nextLine();

		String addressSearch = addressAsked.toLowerCase();


		File myFile = new File(filename + ".txt");
		if (!myFile.exists())
		{
			System.out.println("Your file doesn't exist try again!");
			searchAddress();
		}
		Scanner inputFile = new Scanner(myFile);

		while (inputFile.hasNext())
		{
			String incorrectPlayer = inputFile.nextLine();
			String rugbyPlayer[] = incorrectPlayer.toLowerCase().split(", ");
			if (rugbyPlayer[5].contains(addressSearch) || rugbyPlayer[6].contains(addressSearch) || rugbyPlayer[7].contains(addressSearch) || rugbyPlayer[8].contains(addressSearch) )
			{

				 System.out.println(rugbyPlayer[5] + ", " + rugbyPlayer[6] + ", " + rugbyPlayer[7] + ", " + rugbyPlayer[8]);
			}
		}
		main(new String[0]);

	}

	public static void searchPlayerIndex() throws IOException
	{
		Scanner theFile = new Scanner(System.in);
		System.out.println("What is the name of your file? (No need to add the file extension!)");
		String filename = theFile.nextLine();
		Scanner teamAsker = new Scanner(System.in);
		System.out.println("At which index would you like to start your search?");
		String starting = teamAsker.nextLine(); 
		System.out.println("At which index would you like to end your search?");
		String ending = teamAsker.nextLine();

		int start = Integer.parseInt(starting);
		int end = Integer.parseInt(ending);
		File myFile = new File(filename + ".txt");
		Scanner inputFile = new Scanner(myFile);

		int i = 0;
		while (inputFile.hasNext())
		{
			String incorrectPlayer = inputFile.nextLine();
			if (i>= start-1)
			{
				System.out.println(incorrectPlayer);

				if (i>=end-1)
				{
					System.out.println("Search complete!");
					main(new String[0]);
				}
			}
			i++;

		}


	}


	public static void delete() throws IOException
	{
		Scanner theFile = new Scanner(System.in);
		System.out.println("What is the name of your file? (No need to add the file extension!)");
		String filename = theFile.nextLine();
		System.out.println("Which player would you like to delete? (please give the position from 1 onwards)");
		Integer userChoic = theFile.nextInt();
		Integer userChoice = userChoic - 1;
		File myFile = new File(filename + ".txt");
		Scanner inputFile = new Scanner(myFile);

		int i = 0;
		ArrayList<String> nameList = new ArrayList<String>();
		while (inputFile.hasNext()) 
		{
			String myWantedPlayers = inputFile.nextLine();
			
			if (i == userChoice)
			{
				i++;
				continue;
			}
			else 
			{
				nameList.add(myWantedPlayers);
				System.out.println(myWantedPlayers);
				i++;
			}
		}
		PrintWriter finalVersion = new PrintWriter(myFile);

		for (String thePlayer : nameList)
		{
			finalVersion.println(thePlayer);
		} 

		finalVersion.close();
		main(new String[0]);

	}
}