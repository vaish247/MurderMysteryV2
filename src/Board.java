public class Board {
	
	private Tile[][] boardTiles;
	
	public Board() {
		boardTiles = new Tile[24][24];
		
		//creating empty tiles for the entire board
		for(int x = 0; x<24; x++) {
			for(int y = 0; y<24; y++) {
					boardTiles[y][x] =new EmptyTile(true);
			}
		}
		
		//creating the greytiles
		boardTiles[5][11] = new GreyTile(false);
		boardTiles[5][12] = new GreyTile(false);
		boardTiles[6][11] = new GreyTile(false);
		boardTiles[6][12] = new GreyTile(false);
		boardTiles[12][5] = new GreyTile(false);
		boardTiles[12][6] = new GreyTile(false);
		boardTiles[11][5] = new GreyTile(false);
		boardTiles[11][6] = new GreyTile(false);
		boardTiles[17][11] = new GreyTile(false);
		boardTiles[17][12] = new GreyTile(false);
		boardTiles[18][11] = new GreyTile(false);
		boardTiles[18][12] = new GreyTile(false);
		boardTiles[12][18] = new GreyTile(false);
		boardTiles[12][17] = new GreyTile(false);
		boardTiles[11][18] = new GreyTile(false);
		boardTiles[11][17] = new GreyTile(false);

		
		//creating the haunted house tiles
		for(int x = 2; x<7; x++) {
			for(int y = 2; y<7; y++) {
				if((x>=3 && x<6) && (y>=3 && y<6) || (x==6 && y==3)||(x==5 && y==6)){
					boardTiles[y][x] =new RoomTile(true, "Haunted House");
				}
				
				else {
					boardTiles[y][x] =new RoomTile(false, "Haunted House");
				}
			}
		}
		
		//creating the calamity castle tiles
		for(int x = 2; x<7; x++) {
			for(int y = 17; y<22; y++) {
				if((x>=3 && x<6) && (y>=18 && y<21) || (x==3 && y==17)||(x==6 && y==18)){
					boardTiles[y][x] =new RoomTile(true, "Calamity Castle");
				}
				
				else {
					boardTiles[y][x] =new RoomTile(false, "Calamity Castle");
				}			
			}
		}
		
		//creating the peril palace tiles
		for(int x = 17; x<22; x++) {
			for(int y = 17; y<22; y++) {
				if((x>=18 && x<21) && (y>=18 && y<21) || (x==18 && y==17)||(x==17 && y==20)){
					boardTiles[y][x] =new RoomTile(true, "Peril Palace");
				}
				
				else {
					boardTiles[y][x] =new RoomTile(false, "Peril Palace");
				}
			}
		}
		
		//creating the manic manor tiles
		for(int x = 17; x<22; x++) {
			for(int y = 2; y<7; y++) {
				if((x>=18 && x<21) && (y>=3 && y<6) || (x==17 && y==5)||(x==20 && y==6)){
					boardTiles[y][x] =new RoomTile(true, "Manic Manor");
				}
				
				else {
					boardTiles[y][x] =new RoomTile(false, "Manic Manor");
				}			
			}
		}
		
		//creating the villa celia tiles
		for (int x =9 ; x<15;x++) {
			for (int y = 10; y<14;y++) {
				if((x>=10 && x<14) && (y>= 11 && y<13) || (x== 12 && y== 10)|| (x== 11 && y ==13)|| (x==9 && y == 12)||(x==14 && y==11)) {
					boardTiles[y][x] =new RoomTile(true, "Villa Celia");
				}else {
					boardTiles[y][x] =new RoomTile(false, "Villa Celia");
				}
			}
		}
		
		

	}
	
	public Tile[][] getBoardTiles() {
		return boardTiles;
	}
	
	public Tile getTileAt(int x, int y) {
		return  boardTiles[y][x];
	}
	

	public String toString() {
		StringBuffer res = new StringBuffer();
		for (int y = 0; y < boardTiles.length; y += 1) {
			if(y+1<10) {
				res.append(y+1 +" ");
			}else {
				res.append(y+1);
			}
			res.append("|");
			for (int x = 0; x < boardTiles[0].length; x += 1) {

				if(getTileAt(x,y) != null) {
					res.append(getTileAt(x,y).toString());
				}
				res.append("|");
			}
			res.append("\n");
		}
		return res.toString() + "   a b c d e f g h i j k l m n o p q r s t u v w x";
	}
}
