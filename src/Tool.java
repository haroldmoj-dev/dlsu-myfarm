package MCOPACK;

// GUIAO, Miguel Stephen G. (S22)
// MOJICA, Harold C.        (S21)

/**
	Serves as a blueprint for a  <code>Tool</code> object. The type of tool
	         created is based on the inputted toolType from the constructor.
	         This class also has the method doFunction which implements the 
	         function of the specific tool.
*/
public class Tool {
	
	private String name;
	private int cost;
	private double expGain;
	private int toolType;
	
	/**
		Creates the tool based on the inputted toolType.
		@param toolType is an integer indicating the type of tool.
	*/
	public Tool(int toolType) {
		
		this.toolType = toolType;

		switch(toolType) {
		case 1:
			this.name = "Plow";
			this.cost = 0;
			this.expGain = 0.5;
			break;
		case 2:
			this.name = "Watering Can";
			this.cost = 0;
			this.expGain = 0.5;
			break;
		case 3:
			this.name = "Fertilizer";
			this.cost = 10;
			this.expGain = 4;
			break;
		case 4:
			this.name = "Shovel";
			this.cost = 7;
			this.expGain = 2;
			break;			
		case 5:	
			this.name = "Pickaxe";
			this.cost = 50;
			this.expGain = 15;
		}
	}
	
    /**
		Implements the function of the tool.
		@return true (if move is valid) or false (if move is invalid).
		@param tile is an object containing the state and behaviors of the selected tile.
		@param view is the collection of elements representing the user interface.
		@param row is an integer indicating the row number of the selected tile.
		@param col is an integer indicating the column number of the selected tile.
    */
	public boolean doFunction(Tile tile, View view, int row, int col) {
		
		// Plow
		if (this.name.equals("Plow")) {
			// checks if tile is already plowed
			if (tile.getIsPlowed() == true) {
				view.displayPrompt("[ERROR] Cannot plow tile. Tile is already plowed.");
				return false;
			}
			// checks if tile has a rock
			if (tile.getIsRock() == true) {
				view.displayPrompt("[ERROR] Cannot plow tile. Tile has a rock.");
				return false;				
			}
			// proceed to plow
			tile.setIsPlowed(true);
			view.displayTileColor(row, col, "plowed");
			return true;
		}
		
		// Watering Can
		else if (this.name.equals("Watering Can")) {
			// checks if a seed/crop is present
			if (tile.getSeed() == null) {
				view.displayPrompt("[ERROR] Cannot water tile. No seed/crop present.");
				return false;
			}
			// checks if the plant is withered
			if (tile.getIsWithered() == true) {
				view.displayPrompt("[ERROR] Cannot water tile. The plant has withered.");
				return false;
			}
			// checks if it is not a rose and has been watered today
			if (tile.getSeed().getName() != "Rose" && tile.getSeed().getNWateredToday() == 1) {
				view.displayPrompt("[ERROR] Cannot water tile. This plant has already been watered today.");
				return false;
			}
			// checks if it is a rose and has been watered twice already
			else if (tile.getSeed().getName() == "Rose" && tile.getSeed().getNWateredToday() == 2) {
				view.displayPrompt("[ERROR] Cannot water tile. This rose has already been watered twice today.");
				return false;
			}
			// proceed to water
			tile.getSeed().setNWatered(tile.getSeed().getNWatered() + 1);
			tile.getSeed().setNWateredToday(tile.getSeed().getNWateredToday() + 1);
			view.displayTileColor(row, col, "watered");
			return true;
		}
		
		// Fertilizer
		else if (this.name.equals("Fertilizer")) {
			// checks if a seed/crop is present
			if (tile.getSeed() == null) {
				view.displayPrompt("[ERROR] Cannot fertilize tile. No seed/crop present.");
				return false;
			}
			// checks if the plant is withered
			if (tile.getIsWithered() == true) {
				view.displayPrompt("[ERROR] Cannot fertilize tile. The plant has withered.");
				return false;
			}
			// proceed to fertilize
			tile.getSeed().setNFertilized(tile.getSeed().getNFertilized() + 1);
			return true;
		}
		
		// Shovel
		else if (this.name.equals("Shovel")) {
			// proceed to shovel
			tile.setIsWithered(false);
			tile.setIsPlowed(false);
			tile.setSeed(null);
			if(tile.getIsRock() != true) {
				view.displayTileColor(row, col, "unplowed");
				view.displayTileName(row, col, "");
			}
			return true;
		}
		
		// Pickaxe
		else if (this.name.equals("Pickaxe")) {
			// checks if tile has a rock
			if (tile.getIsRock() == false) {
				view.displayPrompt("[ERROR] Cannot pickaxe tile. Tile does not have a rock.");
				return false;
			}
			// proceed to pickaxe
			tile.setIsRock(false);
			view.displayTileColor(row, col, "unplowed");
			return true;
		}
		return false;
	}
	
	/**
	 * Get the name of chosen tool
	 * @return The name of chosen tool
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Get the cost of chosen tool
	 * @return The cost of chosen tool
	 */
	public double getCost() {
		return this.cost;
	}
	
	/**
	 * Get experience gain of chosen tool
	 * @return Experience gain of chosen tool
	 */
	public double getExpGain() {
		return this.expGain;
	}

	/**
	 * Get the tool type
	 * @return The tool type
	 */
	public int getToolType() {
		return this.toolType;
	}
}
