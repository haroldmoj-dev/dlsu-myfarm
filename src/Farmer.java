package MCOPACK;

// GUIAO, Miguel Stephen G. (S22)
// MOJICA, Harold C.        (S21)

/**
 * 
 * Serves as a blueprint for a <code>Farmer</code> object and contains most of the
 * methods including using a tool, buying a seed, harvesting a crop,
 * registering, advancing the day, and updating the farmer level.
 */			
public class Farmer {
	
	private double objectCoins = 100;
	private int level = 0;
	private double experience = 0;
	private int day = 1;
	
	private String farmerType = "Farmer";
	private int bonusEarnings = 0;
	private int seedCostReduction = 0;
	private int waterBonusLimit = 0;
	private int fertilizerBonusLimit = 0;
	
    /**
		Checks whether the inputted tool is valid, and calls the method doFunction
		         for the tool to do its job.
		@return true (if move is valid) or false (if move is invalid).
		@param tool is an object containing the state and behaviors of the given tool.
		@param tile is an object containing the state and behaviors of the selected tile.
		@param view is the collection of elements representing the user interface.
		@param row is an integer indicating the row number of the selected tile.
		@param col is an integer indicating the column number of the selected tile.
    */
	public boolean useTool(Tool tool, Tile tile, View view, int row, int col) {
		
		boolean valid; // to check if move is valid
		
		// check if objectCoins is enough
		if (this.objectCoins < tool.getCost()) {
			view.displayPrompt("[ERROR] Cannot use the tool. Not enough coins.");
			return false;
		}
		
		// tool does its job (unless invalid)
		valid = tool.doFunction(tile, view, row, col);
		if (valid) {
			this.objectCoins -= tool.getCost();
			this.experience += tool.getExpGain();
			view.displayPrompt("You spent " + tool.getCost() + " coins to use a " + tool.getName() + ".\n" +
			                   "You gained " + tool.getExpGain() + " EXP!");
			updateLevel(view);
			return true;
		}	
		return false;
	}
	
    /**

		Checks whether the inputted seed is valid to buy, and plants the seed to the tile.
		@return true (if move is valid) or false (if move is invalid).
		@param seed is an object containing the state and behaviors of the given seed.
		@param tile is an object containing the state and behaviors of all the tiles.
		@param view is the collection of elements representing the user interface.
		@param row is an integer indicating the row number of the selected tile.
		@param col is an integer indicating the column number of the selected tile.
    */
	public boolean buySeed(Seed seed, Tile[][] tile, View view, int row, int col) {
	
		Tile thisTile = tile[row][col]; // thisTile indicates the selected tile
		
		// check if tile is plowed
		if (thisTile.getIsPlowed() == false) {
			view.displayPrompt("[ERROR] Cannot buy/plant the seed. The tile is unplowed.");
			return false;
		}
		
		// check if tile has a crop
		if (thisTile.getSeed() != null) {
			view.displayPrompt("[ERROR] Cannot buy/plant the seed. The tile already has a crop.");
			return false;
		}
		
		// if the seed chosen is a fruit tree, check if surrounding tiles are occupied
		if (seed.getCropType() == "Fruit tree") {
			// if the selected tile is on the edge
			if (row == 0 || row == 4 || col == 0 || col == 9) {
				view.displayPrompt("[ERROR] Cannot buy the fruit tree seed. A fruit tree cannot be planted on the edge.");
				return false;				
			}
			
			// check the three tiles above and below the selected tile
			for (int i = row - 1; i <= row + 1; i+=2) {
				for (int j = col - 1; j <= col + 1; j++) {
					if (tile[i][j].getSeed() != null || tile[i][j].getIsRock() == true) {
						view.displayPrompt("[ERROR] Cannot buy the fruit tree seed. A fruit tree cannot be planted beside an occupied tile.");
						return false;
					}
				}
			}
			
			// check the tiles to the left and right of the selected tile
			for (int j = col - 1; j <= col + 1; j+=2) {
				if (tile[row][j].getSeed() != null || tile[row][j].getIsRock() == true) {
					view.displayPrompt("[ERROR] Cannot buy the fruit tree seed. A fruit tree cannot be planted beside an occupied tile.");
					return false;
				}
			}		
		}
		
		// set properties of seed according to registered benefits
		seed.setWaterLimit(seed.getWaterLimit() + waterBonusLimit);
		seed.setFertilizerLimit(seed.getFertilizerLimit() + fertilizerBonusLimit);
		
		// check if objectCoins is enough
		if (this.objectCoins < (seed.getCost() - seedCostReduction)) {
			view.displayPrompt("[ERROR] Cannot buy the seed. Not enough coins.");
			return false;
		}
		
		// seed is automatically planted to the tile
		thisTile.setSeed(seed);
		thisTile.getSeed().setCost(thisTile.getSeed().getCost() - seedCostReduction);
		this.objectCoins -= thisTile.getSeed().getCost();
		view.displayPrompt("You spent " + (thisTile.getSeed().getCost()) + " coins to buy a/an " + thisTile.getSeed().getName() + " seed.\n" +
                           "The " + thisTile.getSeed().getName() + " has been planted!");		
		view.displayTileColor(row, col, "planted");
		view.displayTileName(row, col, thisTile.getSeed().getName());
		return true;
	}
	
    /**
		Checks whether it is valid to harvest. If valid, the crop is harvested
		    and the variables of farmer object and tile object are updated.
		@return true (if move is valid) or false (if move is invalid).
		@param tile is an object containing the state and behaviors of the selected tile.
		@param view is the collection of elements representing the user interface.
		@param row is an integer indicating the row number of the selected tile.
		@param col is an integer indicating the column number of the selected tile.
    */
	public boolean harvestCrop(Tile tile, View view, int row, int col) {
		
		// checks if there is no seed/crop present
		if (tile.getSeed() == null) {
			view.displayPrompt("[ERROR] Cannot harvest. No seed/crop present.");
			return false;
		}
		
		// shorten "tile.getSeed()" 
		Seed seed = tile.getSeed();
		
		// check if tile has withered
		if(tile.getIsWithered() == true) {
			view.displayPrompt("[ERROR] Cannot harvest. The plant has already withered.");
			return false;			
		}
		
		// check if harvest day
		if(day != seed.getHarvestDay()) {
			view.displayPrompt("[ERROR] Cannot harvest. Not yet harvest day.");
			return false;			
		}
		
		// variables to compute final harvest price
		double harvestTotal;
		double waterBonus;
		double fertilizerBonus;
		double finalHarvestPrice;
		
		// if nWatered exceeded the limit, set its value to waterLimit
		if(seed.getNWatered() > seed.getWaterLimit()) {
			seed.setNWatered(seed.getWaterLimit());
		}
		
		// if nFertilized exceeded the limit, set its value to FertilizerLimit
		if(seed.getNFertilized() > seed.getFertilizerLimit()) {
			seed.setNFertilized(seed.getFertilizerLimit());
		}
		
		// given formulas
		harvestTotal = seed.getProduct() * (seed.getSellingPrice() + this.bonusEarnings);
		waterBonus = harvestTotal * 0.2 * (seed.getNWatered() - 1);
		fertilizerBonus = harvestTotal * 0.5 * seed.getNFertilized();
		finalHarvestPrice = harvestTotal + waterBonus + fertilizerBonus;
		
		// special condition of flowers
		if (seed.getCropType().equals("Flower")) {
			finalHarvestPrice = finalHarvestPrice * 1.1;
		}
		
		// proceed to harvest
		this.objectCoins += finalHarvestPrice;
		this.experience += seed.getExpGain();
		view.displayPrompt("You successfully harvested the " + seed.getName() + "!\n" +
		                   "Your harvest produced " + seed.getProduct() + " product/s!\n" +
		                   "You gained a total of " + finalHarvestPrice + " coins!\n" +
		                   "You gained " + seed.getExpGain() + " EXP!" );		
		updateLevel(view);
		tile.setSeed(null);
		tile.setIsPlowed(false);
		view.displayTileColor(row, col, "unplowed");
		view.displayTileName(row, col, "");
		return true;
	}
	
    /**
		Checks whether it is valid to register, then registers the farmer
		         by updating certain variables within the farmer object.
		@return true (if move is valid) or false (if move is invalid).
		@param i is an integer that indicates which farmer type the farmer
		         chose to register.
		@param view is the collection of elements representing the user interface.
    */
	public boolean register(int i, View view) {
		
		// temporary variables
		String farmerType;
		int levelRequirement;
		int bonusEarnings;
		int seedCostReduction;
		int waterBonusLimit;
		int fertilizerBonusLimit;
		int registrationFee;		
		
		// assign values to temporary variables depending on the variable 'i'
		switch(i) {
		case 1:
			farmerType = "Registered Farmer";
			levelRequirement = 5;
			bonusEarnings = 1;
			seedCostReduction = 1;
			waterBonusLimit = 0;
			fertilizerBonusLimit = 0;
			registrationFee = 200;
			break;
		case 2:
			farmerType = "Distinguished Farmer";
			levelRequirement = 10;
			bonusEarnings = 2;
			seedCostReduction = 2;
			waterBonusLimit = 1;
			fertilizerBonusLimit = 0;
			registrationFee = 300;
			break;
		case 3:
			farmerType = "Legendary Farmer";
			levelRequirement = 15;
			bonusEarnings = 4;
			seedCostReduction = 3;
			waterBonusLimit = 2;
			fertilizerBonusLimit = 1;
			registrationFee = 400;
			break;
		default:
    		return false;
		}
		
		// check if level requirement is met
		if (this.level < levelRequirement) {
			view.displayPrompt("[ERROR] Registration failed. Level requirement not met.");
    		return false;				
		}
		
		// check if current farmer type is equal or better
		if (this.bonusEarnings >= bonusEarnings) {
			view.displayPrompt("[ERROR] Registration failed. You are already a " + this.farmerType + ".");
    		return false;	
		}
		
		// check if objectCoins is enough
		if (this.objectCoins < registrationFee) {
			view.displayPrompt("[ERROR] Registration failed. Not enough coins.");
    		return false;				
		}
		
		// proceed to register
		this.farmerType = farmerType;
		this.bonusEarnings = bonusEarnings;
		this.seedCostReduction = seedCostReduction;
		this.waterBonusLimit = waterBonusLimit;
		this.fertilizerBonusLimit = fertilizerBonusLimit;
		this.objectCoins -= registrationFee;
		view.displayPrompt("You spent " + registrationFee + " coins to register. \n" +
		                   "You are now a " + farmerType + "!");
		return true;
	}
	
    /**
		Advances the day by incrementing the value of the variable 'day'.
		@param tile is an object containing the state and behaviors of all the tiles.
    */
	public void advanceDay(Tile[][] tile) {
		
		this.day ++; // next day
		
		// reset the counter 'nWateredToday' for all tiles
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 10; j++) {
				if (tile[i][j].getSeed() != null)
					tile[i][j].getSeed().setNWateredToday(0);
			}
		}	
	}
	
    /**
		Checks and updates the variable 'level' based on experience points.
		@param view is the collection of elements representing the user interface.
    */
	public void updateLevel(View view) {
		if (this.experience >= (this.level + 1) * 100) {
			this.level++;
			view.displayPrompt("Congratulations! You leveled up to level " + this.level);
		}
	}

	/**
	 * the current Farmer Type
	 * @return Farmer type string
	 */
	public String getFarmerType() {
		return this.farmerType;
	}
	
	/**
	 * current ObjectCoins
	 * @return objectCoins int
	 */
	public double getObjectCoins() {
		return this.objectCoins;
	}
	
	/**
	 * current Level
	 * @return current Level int
	 */
	public int getLevel() {
		return this.level;
	}
	
	/**
	 * current Experience points
	 * @return experience points int
	 */
	public double getExperience() {
		return this.experience;
	}
	
	/**
	 * current Water bonus limit
	 * @return bonus limit int
	 */
	public int getWaterBonusLimit() {
		return this.waterBonusLimit;
	}
	
	/**
	 * current Fertilizer bonus limit
	 * @return bonus limit int
	 */
	public int getFertilizerBonusLimit() {
		return this.fertilizerBonusLimit;
	}	
	
	/**
	 * current Day
	 * @return day integer
	 */
	public int getDay() {
		return this.day;
	}
	
	public int getSeedCostReduction() {
		return this.seedCostReduction;
	}
}
