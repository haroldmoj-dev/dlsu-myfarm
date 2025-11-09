package MCOPACK;

// GUIAO, Miguel Stephen G. (S22)
// MOJICA, Harold C.        (S21)

/**
	<code>Model</code> class is responsible for carrying the data of the farmer, the tiles, 
	         and the selected tile.
*/
public class Model {
	
	private Farmer farmer;
	private Tile[][] tile;
	private Tile selectedTile;

	/**
		Creates the farmer and tile objects
	*/
	public Model() {
    	
		farmer = new Farmer();
		tile = new Tile[5][10];
		selectedTile = new Tile();
		
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 10; j++) {
				tile[i][j] = new Tile(); 
			}
		}
    }

	
	/**
	 * get the whole Farmer class
	 * @return Farmer
	 */
	public Farmer getFarmer() {
        return farmer;
    }

    /**
	 * get the array of Tiles
     * @return Tile[][]
     */
    public Tile[][] getTile() {
    	return tile;
    }

	/**
	 * get the Current selected tile
	 * @return Tile
	 */
	public Tile getSelectedTile() {
		return selectedTile;
	}

    /**
	 * Sets farmer
     * @param farmer farmer object
     */
    public void setFarmer(Farmer farmer) {
    	this.farmer = farmer;
    }
    
    /**
	 * Sets the tile
     * @param tile array of tiles
     */
    public void setTile(Tile[][] tile) {
    	this.tile = tile;
    }

	/**
	 * Sets the selected tile
	 * @param selectedTile Chosen tile by the user
	 */
	public void setSelectedTile(Tile selectedTile) {
		this.selectedTile = selectedTile;
	}
}
