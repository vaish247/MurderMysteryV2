public class RoomCard extends Card{
    public RoomCard(String name) {
        super(name);
    }

    @Override
    public String getCardType() {
        return "Room";
    }
    public String toString() {
        return name + " ("+getCardType() +")";
    }
}
