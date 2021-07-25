public class WeaponsCard extends Card{
    public WeaponsCard(String name) {
        super(name);
    }

    @Override
    public String getCardType() {
        return "Weapons";
    }
    public String toString() {
        return name + " ("+getCardType() +")";
    }
}
