
public final class Position {
	private int row; // must be between 0 and 23
	private int col; // must be between 0 and 23
	
	public Position(int row, int col) {
		this.row = row;
		this.col = col;
	}
	
	public int row() { 
		return row; 
	}
	
	public int column() { 
		return col; 
	}
	
	public void updateRow(int newR) { 
		this.row= newR; 
	}
	
	public void updateColumn(int newC) { 
		this.col= newC; 
	}

	public boolean equals(Object o) {
		if(o instanceof Position) {
			Position p = (Position) o;
			return row == p.row && col == p.col;
		}
		return false;
	}
	

}
