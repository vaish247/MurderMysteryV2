
public class RoomTile extends TileImpl {
	String roomName;

	public RoomCard getRoomCard() {
		return roomCard;
	}

	private RoomCard roomCard;

	public RoomTile(boolean accessible,String roomName) {
		super(accessible);
		this.roomName = roomName;
		this.roomCard = new RoomCard(roomName);
	}
	
	public String toString() {
		StringBuffer res = new StringBuffer();
		if(accessible) {
			res.append(roomName.toLowerCase().charAt(0));
			return res.toString();
		}
		res.append(roomName.charAt(0));
		return res.toString();
	
		
	}
}
