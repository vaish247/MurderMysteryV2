
public class PlayerTile extends TileImpl implements Tile{
	String playerName;

	public PlayerTile(boolean accessible, String playerName) {
		super(accessible);
		this.playerName = playerName;
	}
	
	public String toString() {
		if(playerName.equals("Lucilla")) {
			return "1";
		}
		if(playerName.equals("Malina")) {
			return "2";
		}
		if(playerName.equals("Bert")) {
			return "3";
		}
		return "4";

	}
}
