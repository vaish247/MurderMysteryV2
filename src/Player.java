import java.util.ArrayList;
import java.util.Scanner;

public class Player {
	
	public String name;
	public Board board;
	public Position pos;
	private Tile tempTile;
	public String roomTileName;

	public boolean isMurderSolve() {
		return murderSolve;
	}

	public boolean murderSolve = false;
	public String getRoomTileName() {
		return roomTileName;
	}

	public ArrayList<Card> getRefutingCards() {
		return refutingCards;
	}

	public ArrayList<Card> refutingCards = new ArrayList<>();

	private CharacterCard playerCard;
	private Boolean onRoomTile = false;

	private ArrayList<Card> playerHand = new ArrayList<>();

	public String getName() {
		return name;
	}
	public int playerNumber;
	public Player(String name, Board board, Position pos, Integer playerNumber){
		this.board = board;
		this.pos = pos;
		this.name = name;
		this.playerNumber = playerNumber;

	}


	public Boolean getOnRoomTile() {
		return onRoomTile;
	}
	public ArrayList<Card> getPlayerHand() {
		return playerHand;
	}


	public void loadPlayer() {
		tempTile = board.getTileAt(pos.row(), pos.column());
		board.getBoardTiles()[pos.row()][pos.column()] = new PlayerTile(false, name);
		playerCard = new CharacterCard(name);
	}

	public CharacterCard getPlayerCard(){
		return playerCard;
	}
	
	public boolean inputParse(String dir) {
        switch (dir.toUpperCase()){
        	case "W":
            	return movePlayer(-1, 0);
        	case "S":
            	return movePlayer(1, 0);
        	case "A":
            	return movePlayer(0, -1);
        	case "D":
            	return movePlayer(0, 1);
        	default:
				return false;
        	}
	}

	public ArrayList<Card> inputParseMurderSuggestOption(String suggest, ArrayList<CharacterCard> characters, ArrayList<WeaponsCard> weapons,ArrayList<RoomCard> estates, Scanner input){
		switch (suggest.toUpperCase()) {
			case "P":
				return murderScenarioPick(characters,weapons,estates,input);
			default:
				return new ArrayList<Card>();
		}
	}
	private ArrayList<Card> murderScenarioPick(ArrayList<CharacterCard> characters, ArrayList<WeaponsCard> weapons, ArrayList<RoomCard> estates, Scanner input){
		boolean characterNotPick = true;
		boolean weaponsNotPicked = true;

		ArrayList<Card> suggestionCards = new ArrayList<>();
		while (characterNotPick){
			System.out.println("This is your hand for reference");
			for(Card card: playerHand){
				System.out.println(card);
			}
			System.out.println("");
			System.out.println("Here is the possible character cards to choose");
			for(CharacterCard card: characters){
				System.out.println(card);
			}
			System.out.println("Which character do you think it is?");
			String characterGuess = input.nextLine();
			for(CharacterCard card: characters){
				if(characterGuess.toUpperCase().equals(card.getName().toUpperCase())){
					suggestionCards.add(card);
					characterNotPick = false;
				}
			}
			if(characterNotPick ){
				System.out.println("Type the name correctly");
			}
		}
		System.out.println("You picked "+ suggestionCards.get(0).getName()+"!");
		System.out.println("");
		System.out.println("Here is the possible weapons cards");

		while (weaponsNotPicked){
			for(WeaponsCard card: weapons){
				System.out.println(card);
			}
			System.out.println("Which weapon do you think it is?");
			String weaponGuess = input.nextLine();
			for(WeaponsCard card: weapons){
				if(weaponGuess.toUpperCase().equals(card.getName().toUpperCase())){
					suggestionCards.add(card);
					weaponsNotPicked = false;
				}
			}
			if(weaponsNotPicked ){
				System.out.println("Type the weapon correctly");
			}
		}
		System.out.println("You picked "+ suggestionCards.get(1).getName()+"!");

		return suggestionCards;

	}

	public ArrayList<Card> murderSolve(ArrayList<CharacterCard> characters, ArrayList<WeaponsCard> weapons, ArrayList<RoomCard> estates, Scanner input){
		murderSolve = true;
		ArrayList<Card> murderSuggest = murderScenarioPick(characters,weapons,estates,input);
		boolean estatesNotPicked = true;
		System.out.println("Here is the possible estate cards");
		while (estatesNotPicked){
			for(RoomCard card: estates){
				System.out.println(card);
			}
			System.out.println("Which room do you think it is?");
			String estateGuess = input.nextLine();
			for(RoomCard card: estates){
				if(estateGuess.toUpperCase().equals(card.getName().toUpperCase())){
					murderSuggest.add(card);
					estatesNotPicked = false;
				}
			}
			if(estatesNotPicked ){
				System.out.println("Type the estate correctly");
			}
		}
		System.out.println("You picked "+ murderSuggest.get(2).getName()+"!");

		return murderSuggest;

	}
	public void teleport(String room){
		if(room.equals("Haunted House")){
			for(int y = 3; y<6; y++) {
				for (int x = 3; x < 6; x++) {
					if(board.getTileAt(y,x).isAccessible()) {
						teleportPlayer(y, x);
						x=5;
						y=5;
					}
				}
			}
		}
		if(room.equals("Manic Manor")){
			for(int y = 3; y<6; y++) {
				for (int x = 18; x < 21; x++) {
					if(board.getTileAt(y,x).isAccessible()) {
						teleportPlayer(y, x);
						y=5;
						x=20;
					}
				}
			}
		}
		if(room.equals("Peril Palace")){
			for(int y = 18; y<21; y++) {
				for (int x = 18; x < 21; x++) {
					if(board.getTileAt(y,x).isAccessible()) {
						teleportPlayer(y, x);
						y=20;
						x=20;
					}
				}
			}
		}
		if(room.equals("Calamity Castle")){
			for(int y = 18; y<21; y++) {
				for (int x = 3; x < 6; x++) {
					if(board.getTileAt(y,x).isAccessible()) {
						teleportPlayer(y, x);
						x=5;
						y=20;
					}
				}
			}
		}
		if(room.equals("Villa Celia")){
			for(int y = 11; y<13; y++) {
				for (int x = 10; x < 14; x++) {
					if(board.getTileAt(y,x).isAccessible()) {
						teleportPlayer(y, x);
						x=13;
						y=12;
					}
				}
			}
		}

	}
	private boolean teleportPlayer(int row, int col) {
		try {
			Tile currentTile = board.getBoardTiles()[pos.row()][pos.column()];
			Tile newTile = board.getBoardTiles()[row][col];
			board.getBoardTiles()[row][col] = currentTile;
			board.getBoardTiles()[pos.row()][pos.column()] = tempTile;
			tempTile = newTile;
			pos.updateColumn(col);
			pos.updateRow( row);
			System.out.println(board);
			if (newTile instanceof RoomTile) {
				onRoomTile = true;
				RoomTile p = (RoomTile) newTile;
				roomTileName = p.roomName;

			} else {
				onRoomTile = false;
			}
			return true;

		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Your moving out of the board");
			return false;
		}
	}
	
    private boolean movePlayer(int row, int col) {
		try {
			Tile currentTile = board.getBoardTiles()[pos.row()][pos.column()];
			Tile newTile = board.getBoardTiles()[pos.row() + row][pos.column() + col];

			if (newTile != null && newTile.isAccessible()) {
				board.getBoardTiles()[pos.row() + row][pos.column() + col] = currentTile;
				board.getBoardTiles()[pos.row()][pos.column()] = tempTile;
				tempTile = newTile;
				pos.updateColumn(pos.column() + col);
				pos.updateRow(pos.row() + row);
				System.out.println(board);
				if(newTile instanceof RoomTile){
					onRoomTile = true;
					RoomTile p = (RoomTile) newTile;
					roomTileName = p.roomName;

				}else{
					onRoomTile = false;
				}
				return true;
			}
			System.out.println("Your hitting an inaccessible area");
			System.out.println(board);
			return false;
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Your moving out of the board");
			return false;
		}

	}
}
