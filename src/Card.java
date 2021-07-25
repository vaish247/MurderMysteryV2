import java.util.Objects;

public abstract class Card {
	public String name;
	
	public Card(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public abstract String getCardType();

	@Override
	public String toString() {
		return name;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Card card = (Card) o;
		return Objects.equals(name, card.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}
}
