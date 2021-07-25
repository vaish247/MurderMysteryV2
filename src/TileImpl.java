
public abstract class TileImpl implements Tile{
	
	protected boolean accessible;
	
	public TileImpl(boolean accessible) {
		this.accessible = accessible;
	}

	public boolean isAccessible() {
		return accessible;
	}

	public boolean equals(Object o) {
		if (o instanceof TileImpl) {
			TileImpl p = (TileImpl) o;
			return o.getClass() == getClass();
		}
		return false;
	}	
}