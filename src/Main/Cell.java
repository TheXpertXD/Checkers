package Main;

public class Cell {
	
	int state; // 0 = dark spot, 1 = light spot
	int row, col;
	
	public Cell(int state, int i, int j) {
		this.state = state;
		row = i;
		col = j;
	}
	
	public void setState(int state) {
		this.state = state;
	}
	
	public int getState() {
		return state;
	}
	
	public int getRow() {
		return this.row;
	}
	
	public int getCol() {
		return this.col;
	}
}

