public class CharacterCard extends Card{

    public CharacterCard(String name) {
        super(name);
    }

    @Override
    public String getCardType() {
        return "Character";
    }
    public String toString() {
        return name + " ("+getCardType() +")";
    }
}
