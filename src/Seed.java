package MCOPACK;

// GUIAO, Miguel Stephen G. (S22)
// MOJICA, Harold C.        (S21)

import java.util.Random; // used for randomizing the number of products

/**
	Serves as a blueprint for a <code>Seed</code> object. The type of seed 
	         created is based on the inputted seedType from the constructor.
*/
public class Seed {

    private String name;
    private String cropType;
    
    private int product;
    private double cost;

	private int sellingPrice;
    private double expGain;
    
    private int harvestTime;
    private int waterNeeds;
    private int waterLimit;
    private int fertilizerNeeds;
    private int fertilizerLimit;
    
    private int nWatered = 0;
    private int nWateredToday = 0;
    private int nFertilized = 0;
    private int harvestDay;
	private int seedType;

	/**
		Creates the seed based on the inputted seedType.
		@param seedType is an integer indicating the type of seed.
		@param day is an integer indicating the current day.
    */
    public Seed(int seedType, int day) {

		this.seedType = seedType;
    	Random rand = new Random(); // for randomizing products produced
    	
    	switch(seedType) {
    	case 1:
    		this.name = "Turnip";
    		this.cropType = "Root crop";
    		this.product = rand.nextInt(1) + 1;
    		this.cost = 5;
    		this.sellingPrice = 6;
    		this.expGain = 5;
    		this.harvestTime = 2;
    		this.waterNeeds = 1;
    		this.waterLimit = 2;
    		this.fertilizerNeeds = 0;
    		this.fertilizerLimit = 1;
    		break;
    	case 2:
    		this.name = "Carrot";
    		this.cropType = "Root crop";
    		this.product = rand.nextInt(1) + 1;
    		this.cost = 10;
    		this.sellingPrice = 9;
    		this.expGain = 7.5;
    		this.harvestTime = 3;
    		this.waterNeeds = 1;
    		this.waterLimit = 2;
    		this.fertilizerNeeds = 0;
    		this.fertilizerLimit = 1;
    		break;
    	case 3:
    		this.name = "Potato";
    		this.cropType = "Root crop";
    		this.product = rand.nextInt(9) + 1;
    		this.cost = 20;
    		this.sellingPrice = 3;
    		this.expGain = 12.5;
    		this.harvestTime = 5;
    		this.waterNeeds = 3;
    		this.waterLimit = 4;
    		this.fertilizerNeeds = 1;
    		this.fertilizerLimit = 2;
    		break;
    	case 4:
    		this.name = "Rose";
    		this.cropType = "Flower";
    		this.product = 1;
    		this.cost = 5;
    		this.sellingPrice = 5;
    		this.expGain = 2.5;
    		this.harvestTime = 1;
    		this.waterNeeds = 1;
    		this.waterLimit = 2;
    		this.fertilizerNeeds = 0;
    		this.fertilizerLimit = 1;
    		break;
    	case 5:
    		this.name = "Tulips";
    		this.cropType = "Flower";
    		this.product = 1;
    		this.cost = 10;
    		this.sellingPrice = 9;
    		this.expGain = 5;
    		this.harvestTime = 2;
    		this.waterNeeds = 2;
    		this.waterLimit = 3;
    		this.fertilizerNeeds = 0;
    		this.fertilizerLimit = 1;
    		break;
    	case 6:
    		this.name = "Sunflower";
    		this.cropType = "Flower";
    		this.product = 1;
    		this.cost = 20;
    		this.sellingPrice = 19;
    		this.expGain = 7.5;
    		this.harvestTime = 3;
    		this.waterNeeds = 2;
    		this.waterLimit = 3;
    		this.fertilizerNeeds = 1;
    		this.fertilizerLimit = 2;
    		break;
    	case 7:
    		this.name = "Mango";
    		this.cropType = "Fruit tree";
    		this.product = rand.nextInt(10) + 5;
    		this.cost = 100;
    		this.sellingPrice = 8;
    		this.expGain = 25;
    		this.harvestTime = 10;
    		this.waterNeeds = 7;
    		this.waterLimit = 7;
    		this.fertilizerNeeds = 4;
    		this.fertilizerLimit = 4;
    		break;
    	case 8:
    		this.name = "Apple";
    		this.cropType = "Fruit tree";
    		this.product = rand.nextInt(5) + 10;
    		this.cost = 200;
    		this.sellingPrice = 5;
    		this.expGain = 25;
    		this.harvestTime = 10;
    		this.waterNeeds = 7;
    		this.waterLimit = 7;
    		this.fertilizerNeeds = 5;
    		this.fertilizerLimit = 5;
    	}	
    	this.harvestDay = day + this.harvestTime;
    }
    
	
    /**
	 * Get the seed name
     * @return String
     */
    public String getName() {
    	return this.name;
    }
    
    /**
	 * Get the crop type
     * @return String
     */
    public String getCropType() {
    	return this.cropType;
    }

    /**
	 * Get number of produced crops
     * @return int
     */
    public int getProduct() {
    	return this.product;
    }

    /**
	 * Get seed cost
     * @return double
     */
    public double getCost() {
    	return this.cost;
    }
    
    /**
	 * Get selling price 
     * @return double
     */
    public double getSellingPrice() {
    	return this.sellingPrice;
    }

    /**
	 * Get experience gain 
     * @return double
     */
    public double getExpGain() {
    	return this.expGain;
    }
    
    /**
	 * Get water needs
     * @return int
     */
    public int getWaterNeeds() {
    	return this.waterNeeds;
    }

    /**
	 * Get water limit
     * @return int
     */
    public int getWaterLimit() {
    	return this.waterLimit;
    }
     
    /**
	 * Get fertilizer needs
     * @return int
     */
    public int getFertilizerNeeds() {
    	return this.fertilizerNeeds;
    }
    
    /**
	 * Get fertilizer limit
     * @return int
     */
    public int getFertilizerLimit() {
    	return this.fertilizerLimit;
    }
    
    /**
	 * Get number of times watered
     * @return int
     */
    public int getNWatered() {
    	return this.nWatered;
    }

    /**
	 * Get number of times watered today
     * @return int
     */
    public int getNWateredToday() {
    	return this.nWateredToday;
    }
    
    /**
	 * Get nember of times fertilized
     * @return int
     */
    public int getNFertilized() {
    	return this.nFertilized;
    }
    
    /**
	 * Get harvest day of chosen crop
     * @return int
     */
    public int getHarvestDay() {
    	return this.harvestDay;
    }
      
    /**
     * @param waterLimit set water limit
     */
    public void setWaterLimit(int waterLimit) {
    	this.waterLimit = waterLimit;
    }  

    /**
	 * Setter 
     * @param fertilizerLimit set fertilizer limit
     */
    public void setFertilizerLimit(int fertilizerLimit) {
    	this.fertilizerLimit = fertilizerLimit;
    }   
    
    /**
	 * Setter
     * @param nWatered set number of times watered
     */
    public void setNWatered(int nWatered) {
    	this.nWatered = nWatered;
    }
    
    /**
	 * Setter
     * @param nWateredToday set number of times watered today
     */
    public void setNWateredToday(int nWateredToday) {
    	this.nWateredToday = nWateredToday;
    }
   
    /**
	 * Setter
     * @param nFertilized set number of times fertilized
     */
    public void setNFertilized(int nFertilized) {
    	this.nFertilized = nFertilized;
    }  
 
    /**
	 * Setter
     * @param harvestDay set harvest day
     */
    public void setHarvestDay(int harvestDay) {
    	this.harvestDay = harvestDay;
    }

	/**
	 * Setter
	 * @param cost set cost
	 */
	public void setCost(double cost) {
		this.cost = cost;
	}

	/**
	 * Setter
	 * @return get the chosen seedtype
	 */
	public int getSeedType() {
		return this.seedType;
	}
}


