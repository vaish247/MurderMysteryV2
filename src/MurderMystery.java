import java.util.*;

public class MurderMystery {

	private int playerNumbers;
	public ArrayList<Player> players = new ArrayList<>();
	public ArrayList<Card> deck = new ArrayList<>();
	public ArrayList<CharacterCard> playerCards = new ArrayList<>();
	public ArrayList<WeaponsCard> weaponCards = new ArrayList<>();
	public ArrayList<RoomCard> roomCards = new ArrayList<>();
	public ArrayList<Card> murderCards = new ArrayList<>();


	public void run() {
		Board board =new Board();
        Scanner input = new Scanner(System.in);
        System.out.println("How many players wish to participate?");
        boolean numTrue = true;
        while(numTrue) {
        	try {
        		playerNumbers = Integer.parseInt(input.nextLine());
        		if(playerNumbers>2 && playerNumbers<5) {
            		numTrue = false;
        		}else {
        			System.out.println("Invalid amount of player!");
        		}
        	}catch (NumberFormatException e) {
        		System.out.println("Invalid Format!");
        	}
        }
		createDeck();
        generateMurder();

        Player p1 = new Player("Lucilla",board, new Position(1, 11),1);
        p1.loadPlayer();
        Player p2 = new Player("Malina",board, new Position(22, 9),2);
        p2.loadPlayer();
        Player p3 = new Player("Bert",board, new Position(9, 1),3);
        p3.loadPlayer();
		players.add(p1);
		players.add(p2);
		players.add(p3);

        if(playerNumbers==4) {
			Player p4 = new Player("Percy", board, new Position(14, 22),4);
			p4.loadPlayer();
			players.add(p4);
		}
		dealCards();

		//only one player is able to move right noe
		boolean gameWon = false;
		while (!gameWon) {

			for (Player p : players) {
				gameWon = turns(p, input, board);
				if (gameWon) { // Tell the player that they won.
					System.out.println("Congratulations " + p.getName() + " you won!");
					break;
				}
			}
		}
	}

	public boolean turns(Player p, Scanner input, Board board) {
		System.out.println("It's player " + p.getName() +" (Player "+ p.playerNumber+")"+" turn");
		int movesNumber = roll();
		System.out.println("You have " +movesNumber + " moves");
		System.out.println(board);
		while(movesNumber!=0) {
			System.out.println("Use WASD to move or Press n to make a solve" );
			System.out.println("You have " + movesNumber +" left!" );
			if(p.getOnRoomTile()){
				System.out.println("Do you want to suggest a murder scenario? Press P to suggest and any other key to skip");
				String option = input.nextLine();
				ArrayList<Card> murderGuessCards  = p.inputParseMurderSuggestOption(option,playerCards,weaponCards,roomCards,input);
				if(!murderGuessCards.isEmpty()) {
					for(Player pTeleporting: players){
						if(pTeleporting.getName().equals(murderGuessCards.get(0).getName())){
							pTeleporting.teleport(p.getRoomTileName());
						}
					}

					boolean refuting = false;
					for (Player pRefuting : players) {
						if (!p.equals(pRefuting)) {
							for (Card cardRefute : pRefuting.getPlayerHand()) {
								for (Card murderGuess : murderGuessCards) {
									if (murderGuess.equals(cardRefute)) {
										refuting = true;
										pRefuting.getRefutingCards().add(cardRefute);
									}
								}
							}
						}
						if (refuting) {
							System.out.println("Player " + pRefuting.getName() + " can refute");
							System.out.println("Type anything to continue");
							String randomTyping = input.nextLine();
							if(randomTyping!= null) {
								System.out.println("Cards that player can use to refute ");
								for (Card refutingCard : pRefuting.getRefutingCards()) {
									System.out.println(refutingCard);
								}
								System.out.println("Which card do you refute");
								boolean typeCheck = true;
								while (typeCheck) {
									String refuteCard = input.nextLine();
									for (Card card : pRefuting.getRefutingCards()) {
										if (refuteCard.toUpperCase().equals(card.getName().toUpperCase())) {
											System.out.println(pRefuting.getName() + " chose to use the " + card.getName() + " card for refuting");
											for(int x= 0; x<10;x++ ){
												System.out.println();
											}
											typeCheck = false;
										}
									}
									if (typeCheck) {
										System.out.println("Type the Card correctly");
									}
								}
								refuting = false;
							}
						}
					}
					System.out.println("No one else can refute");
					return false;
				}else{
					System.out.println("Use WASD to move" );
				}

			};
			String direction = input.nextLine();
			if(direction.equals("n")){
				if(p.murderSolve){
					System.out.println("You alrady made a solve");
				}else {
					ArrayList<Card> murderSolveCards = p.murderSolve(playerCards, weaponCards, roomCards, input);
					if(murderSolveCards != null){
						if(murderCards.get(0).getName().equals(murderSolveCards.get(2).getName())){
							if(murderSolveCards.get(0).equals(murderCards.get(2))&& murderSolveCards.get(1).equals(murderCards.get(1))){
								System.out.println("You won");
								return true;
							}else{
								System.out.println("You lost");
								return false;
							}
						}else{
							System.out.println("You lost");
							return false;
						}
					}
				}
			}
			if(p.murderSolve) {
				System.out.println("You alrady made a solve");
			}else {
				boolean movement = p.inputParse(direction);
				if (movement) {
					movesNumber--;
				}
			}
		}
		return false;
	}





	public void createDeck(){
		CharacterCard Lucilla = new CharacterCard("Lucilla");
		CharacterCard Malina = new CharacterCard("Malina");
		CharacterCard Bert = new CharacterCard("Bert");
		CharacterCard Percy = new CharacterCard("Percy");

		WeaponsCard Broom = new WeaponsCard("Broom");
		WeaponsCard Scissors = new WeaponsCard("Scissors");
		WeaponsCard Shovel = new WeaponsCard("Shovel");
		WeaponsCard iPad = new WeaponsCard("iPad");
		WeaponsCard Knife = new WeaponsCard("Knife");

		RoomCard Haunted = new RoomCard("Haunted House");
		RoomCard Manic =new RoomCard("Manic Manor");
		RoomCard Peril =new RoomCard("Peril Palace");
		RoomCard Calamity = new RoomCard("Calamity Castle");
		RoomCard Villa = new RoomCard("Villa Celia");

		deck.add(Lucilla);
		deck.add(Malina);
		deck.add(Bert);
		playerCards.add(Lucilla);
		playerCards.add(Malina);
		playerCards.add(Bert);
		if(playerNumbers ==4) {
			deck.add(Percy);
			playerCards.add(Percy);
		}

		deck.add(Broom);
		deck.add(Scissors);
		deck.add(Shovel);
		deck.add(iPad);
		deck.add(Knife);
		weaponCards.add(Broom);
		weaponCards.add(Scissors);
		weaponCards.add(Shovel);
		weaponCards.add(iPad);
		weaponCards.add(Knife);

		deck.add(Haunted);
		deck.add(Manic);
		deck.add(Peril);
		deck.add(Calamity);
		deck.add(Villa);
		roomCards.add(Haunted);
		roomCards.add(Manic);
		roomCards.add(Peril);
		roomCards.add(Calamity);
		roomCards.add(Villa);

	}


	public void dealCards(){

		Queue<Player> playerQueue = new ArrayDeque<>();
		for(Player p : players){
			playerQueue.offer(p);
		}
		while(!deck.isEmpty()){
			Random random = new Random();
			int dice1 = random.nextInt(deck.size()) ;
			Player a = playerQueue.poll();
			a.getPlayerHand().add(deck.remove(dice1));
			playerQueue.offer(a);
		}
	}

	public void generateMurder(){
		Random random = new Random();

		murderCards.add(roomCards.get( random.nextInt(4) + 1));
		murderCards.add(weaponCards.get( random.nextInt(4) + 1));
		murderCards.add(playerCards.get( random.nextInt(2) + 1));

		deck.remove(murderCards.get(0));
		deck.remove(murderCards.get(1));
		deck.remove(murderCards.get(2));

		System.out.println(murderCards.get(0)+" " +murderCards.get(1)+" "+ murderCards.get(2));
	}
	public static int roll() {
		Random random = new Random();
		int dice1 = random.nextInt(6) + 1;
		int dice2 = random.nextInt(6) + 1;
		return 50;

	}
	public static void main(String args[]) {
		MurderMystery game = new MurderMystery();
		game.run();

	}


}
