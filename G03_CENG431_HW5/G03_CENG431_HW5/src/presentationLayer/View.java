package presentationLayer;

import java.util.Scanner;

import boardElements.Coordinate;
import chineseCheckers.*;

public class View {
	private Scanner scn;
	private char[] alphabet = "abcdefghýjklmnopqrstuvwxy".toUpperCase().toCharArray();
	public View() {
		scn = new Scanner(System.in);
	}
	
	private int findIndexAlphabet(String s) {
		for(int i=0;i<alphabet.length;i++) {
			if(String.valueOf(alphabet[i]).equals(s))
				return i;
		}
		return -1;
	}
	public Coordinate getCoordinate(String message) {
		System.out.println(message);
		int row = -1;
		while(row<1 || row>17) {
			row = getIntegerInput("Enter row(1-17) : ");
			if(row<1 || row>17)
				System.out.println("please input between 1 and 17 !!!");
		}

		String column = "Z"; // alfabede yok -1 donsun diye
		
		while(findIndexAlphabet(column)==-1) {
			System.out.println("Enter column(A-Y) : ");
			column = scn.nextLine();
			if(findIndexAlphabet(column)==-1)
				System.out.println("please input between A and Y !!!");
		}
		return new Coordinate(row,column);
	}
	
	public void displayTurn(Player player) {
		System.out.println("Turn : "+player);
	}
	
	public void displayPlayer(Player player){
		System.out.println(player);
	}
	
	public int getPlayerCount() {
		return getIntegerInput("Enter amount of player (2,3,4,6) : ");
	}
	
	public String getPlayerName(int number) {
		System.out.println("Player " + number +" name : ");
		return scn.nextLine();
	}
	
	
	private int getIntegerInput(String message) {
		int returnInteger=-1;
		try {		
			System.out.println(message);
			returnInteger = Integer.valueOf(scn.nextLine());
		}catch (Exception e) {
			System.out.println("Wrong enter please try again!!!");
			getIntegerInput(message);
		}
		return returnInteger;
	}
	
	public void displayError(String message) {
		System.out.println("\n@error: " + message+"\n");
	}
	
	public void displayInformation(String message) {
		System.out.println("\n#info: " + message+"\n");
	}
}
