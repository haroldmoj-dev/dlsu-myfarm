package src;

// GUIAO, Miguel Stephen G. (S22)
// MOJICA, Harold C.        (S21)

/**
	Serves as a blueprint for a <code>Tile</code> object containing the plow, wither,
	         and rock status as well as the Seed object. This class also has the 
	         method updateWither which indicates if the tile withers.
*/
public class Tile {
	
    private boolean isPlowed = false;
    private boolean isWithered = false;
    private boolean isRock = false;
    private Seed seed;
     
    /**
		Checks if the tile withered and updates the variable 'isWithered'.
		@param day is an integer indicating the current day.
		@param view is the collection of elements representing the user interface.
		@param row is an integer indicating the row number of the selected tile.
		@param col is an integer indicating the column number of the selected tile.
    */
    public void updateWither(int day, View view, int row, int col) {
        
    	// if the farmer advances the day without harvesting on harvest day, the tile should wither
    	if (day > seed.getHarvestDay()) {
    		view.displayPrompt("You failed to harvest your " + seed.getName() + " on time.\n" +
                               "The " + seed.getName() + " withered.");
    		this.isWithered = true;
    		view.displayTileColor(row, col, "withered");
    		view.displayTileName(row, col, seed.getName());
    	}
        
        // if minimum requirements for a plant has not beet met on harvest day, the tile should wither
    	else if ((day == seed.getHarvestDay() && seed.getNWatered() < seed.getWaterNeeds()) || (day == seed.getHarvestDay() && seed.getNFertilized() < seed.getFertilizerNeeds())) {
            view.displayPrompt(" Your " + seed.getName() + " did not meet the minimum requirements.\n" +
                               " The " + seed.getName() + " withered.");
    		this.isWithered = true;
    		view.displayTileColor(row, col, "withered");
        }
    }
    
	
    /**
	 * Get status of tile if plowed or not
     * @return boolean
     */
    public boolean getIsPlowed() {
    	return this.isPlowed;
    }
    
    /**
     * @param isPlowed Sets tile status if plowed or not
     */
    public void setIsPlowed(boolean isPlowed) {
    	this.isPlowed = isPlowed;
    }
    
    /**
     * @return Get status of tile if withered or not
     */
    public boolean getIsWithered() {
    	return this.isWithered;
    }
    
    /**
     * @param isWithered Sets tile status if withered or not
     */
    public void setIsWithered(boolean isWithered) {
    	this.isWithered = isWithered;
    }
    
    /**
     * @return Get tile status if it has a rock or not
     */
    public boolean getIsRock() {
    	return this.isRock;
    }
    
    /**
     * @param isRock Sets tile status if it will have a rock or not
     */
    public void setIsRock(boolean isRock) {
    	this.isRock = isRock;
    }
    
    /**
     * @return get the Seed class
     */
    public Seed getSeed() {
    	return this.seed;
    }
    
    /**
     * @param seed 
     */
    public void setSeed(Seed seed) {
    	this.seed = seed;
    }
}
