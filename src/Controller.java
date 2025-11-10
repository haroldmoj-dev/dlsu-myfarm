package src;
// GUIAO, Miguel Stephen G. (S22)
// MOJICA, Harold C.        (S21)

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


/**
 *
 * <code>Controls</code> the flow of data into model object and updates the view whenever 
 *	         an action is performed.
 * 
 */
public class Controller implements ActionListener{
	
    private View view;
    private Model model;
    
    private int row;
    private int col;
    private boolean valid;

    /**
	 * <code>Controller</code> constructor
     * @param view is the collection of elements representing the user interface.
     * @param model for the controller to easily connect with the whole logic of the program
     */
    public Controller(View view, Model model) {
        this.view = view;
        this.model = model;

        updateFarmerStats();
        addRocks();
        displayPrices();

        this.view.addActionListener(this);
    }
    
    /**
		Determines what is to be done when a user performs a certain action.
		@param e is an action event indicating the action performed by the user.
    */
    @Override
    public void actionPerformed(ActionEvent e) { 
    	
    	// WEST - advanceDay
        if (e.getSource() == view.getAdvanceDay()) {
            model.getFarmer().advanceDay(model.getTile());
            
    		// check if any crop withered
    		for (int i = 0; i < 5; i++) {
    			for (int j = 0; j < 10; j++) {
    				Tile[][] tile = model.getTile();
    				if(tile[i][j].getSeed() != null) { 
    					view.displayTileColor(i, j, "planted");
    					tile[i][j].updateWither(model.getFarmer().getDay(), view, i, j);
    				}							
    			}
    		}
    		updateFarmerStats();
    		gameOver();
        }
        
        // WEST - register
        else if (e.getSource() == view.getRegister()) {
        	view.vEastRegister(true);
        	view.getBack().setVisible(true);
        	view.eEast(false);
        	view.eCenter(false);
        }
        
        // EAST - register > farmerType
        else if (e.getSource() == view.getRegisteredFarmer()) {
        	if (register(1))
        		displayPrices();
        }
        else if (e.getSource() == view.getDistinguishedFarmer()) {
        	if (register(2))
        		displayPrices();
        }
        else if (e.getSource() == view.getLegendaryFarmer()) {
        	if (register(3))
        		displayPrices();
        }      
        
        // CENTER - tile
        JButton[][] btn = view.getTile();
        Tile[][] tile = model.getTile();
        for (int i = 0; i < 5; i++) {
        	for (int j = 0; j < 10; j++) {
                if (e.getSource() == btn[i][j]) {
					model.setSelectedTile(tile[i][j]);
                	row = i;
                	col = j;
                	displayTileStats();
                	view.vEastTile(true);
                	view.getBack().setVisible(true);
                	view.eEast(false);
                }
        	}
        }
 
        // EAST - tile > useTool
        if (e.getSource() == view.getUseTool()) {
        	view.eCenter(false);
			view.vEastUseTool(true);
			view.vEastTile(false);
        } 
        
        // EAST - tile > useTool > toolType
        else if (e.getSource() == view.getPlow()) {
        	useTool(1);
        }
        else if (e.getSource() == view.getWateringCan()) {
        	useTool(2);
        }  
        else if (e.getSource() == view.getFertilizer()) {
        	useTool(3);
        } 
        else if (e.getSource() == view.getShovel()) {
        	useTool(4);
        } 
        else if (e.getSource() == view.getPickaxe()) {
        	useTool(5);
        }
        
        // EAST - tile > buySeed
        else if (e.getSource() == view.getBuySeed()) {
        	view.eCenter(false);
        	view.vEastBuySeed(true);
        	view.vEastTile(false);
        }
        
        // EAST - tile > buySeed > seedType
        else if (e.getSource() == view.getTurnip()) {
        	buySeed(1);  	
        }
        else if (e.getSource() == view.getCarrot()) {
        	buySeed(2);  
        }
        else if (e.getSource() == view.getPotato()) {
        	buySeed(3);  
        }
        else if (e.getSource() == view.getRose()) {
        	buySeed(4);  
        }
        else if (e.getSource() == view.getTulips()) {
        	buySeed(5);  
        }
        else if (e.getSource() == view.getSunflower()) {
        	buySeed(6);  
        }
        else if (e.getSource() == view.getMango()) {
        	buySeed(7);  
        }
        else if (e.getSource() == view.getApple()) {
        	buySeed(8);  
        }
        
        // EAST - tile > harvest
        else if (e.getSource() == view.getHarvest()) {
        	valid = model.getFarmer().harvestCrop(model.getSelectedTile(), view, row, col); 
        	view.eCenter(false);
        	view.vEastTile(false);
        	view.getBack().setVisible(false);
        	view.eEast(true);
        	view.eCenter(true);
        	updateFarmerStats();
        }
        
        // EAST - gameOver > playAgain
        else if (e.getSource() == view.getPlayAgain()) {	    	
    		// create farmer and tile objects
    		model.setFarmer(new Farmer());
    		model.setTile(new Tile[5][10]);
    		tile = model.getTile();
    		
    		// initialize all tiles
    		for (int i = 0; i < 5; i++) {
    			for (int j = 0; j < 10; j++) {
    				tile[i][j] = new Tile();
    				view.displayTileColor(i, j, "unplowed");
    				view.displayTileName(i, j, "");
    			}
    		}
    		
    		view.vEastGameOver(false);
    		view.eEast(true);
        	view.eCenter(true);
        	
        	addRocks();
    		updateFarmerStats();
    		displayPrices();
        	view.displayPrompt("Welcome to MyFarm!"); 
        }
        
        // EAST - gameOver > exit
        else if (e.getSource() == view.getExit()) {
        	System.exit(0);
        }
        
        // EAST - back
        else if (e.getSource() == view.getBack()) {
    		view.vEastRegister(false);
    		view.vEastTile(false);
    		view.vEastUseTool(false);
    		view.vEastBuySeed(false);
    		view.getBack().setVisible(false);
        	view.eEast(true);
        	view.eCenter(true);
        }
    }
    
    /**
		Reads the text file containing the distribution of rocks and sets 
		         the specified tiles into rocks.
    */
    public void addRocks() {
    	
    	Path importFile = Paths.get("input.txt");
		
		try {
			BufferedReader reader = Files.newBufferedReader(importFile);
			String line = null;
			
			String[] r = new String[50];
			int cnt = 0;
			
			while ((line = reader.readLine()) != null) {
				String rocks[] = line.split(",");
				
				// create a 1d string array of rocks
				for(String s : rocks) {
					if(s.isEmpty())
						continue;
					
					if(cnt < 50) {
						r[cnt] = s;
						cnt++;
					}
				}
			}
			
			// convert tiles to rocks
			cnt = 0;
			for (int i = 0; i < 5; i++) {
	        	for (int j = 0; j < 10; j++) {
	        		Tile[][] tile = model.getTile();
	        		if (r[cnt].equals("1")) {
	        			tile[i][j].setIsRock(true);
	        			view.displayTileColor(i, j, "rock");
	        		}
	        		cnt++;
	        	}
	        }
		}
		catch(IOException e) {
			System.out.println("Error occurred: " + e.getMessage());
		}   
    }    
    
    /**
		Updates the display of the farmer stats on the west and north
		         panels of the window.
    */
    public void updateFarmerStats() {
    	view.displayHeading(model.getFarmer().getDay());
    	view.displayDay(model.getFarmer().getDay());
    	view.displayObjectCoins(model.getFarmer().getObjectCoins());       
        view.displayLevel(model.getFarmer().getLevel());
        view.displayExperience(model.getFarmer().getExperience());
        view.displayFarmerType(model.getFarmer().getFarmerType());  
    }
    
    /**
		Displays the tile status of the selected tile on the south panel
		         of the window.
    */
    public void displayTileStats() {
    	String string = "------------ TILE STATUS ------------";
    	
    	if (model.getSelectedTile().getIsRock() == true) {
			string = string.concat("\nRock."); 
    	}	
    	else if (model.getSelectedTile().getSeed() == null){
    		if (model.getSelectedTile().getIsPlowed() == true) {
    			string = string.concat("\nPlowed tile."); 	
    		}
    		else {
    			string = string.concat("\nUnplowed tile."); 
    		}   		
    	}   	
    	else if (model.getSelectedTile().getIsWithered() == false) {
    		
    		string = string.concat("\nSeed name: " + model.getSelectedTile().getSeed().getName());
    		string = string.concat("\nNo. of times watered: " + model.getSelectedTile().getSeed().getNWatered() + " (MIN " + model.getSelectedTile().getSeed().getWaterNeeds() + ", MAX " + model.getSelectedTile().getSeed().getWaterLimit() + ")");
    		string = string.concat("\nNo. of times fertilized: " + model.getSelectedTile().getSeed().getNFertilized() + " (MIN " + model.getSelectedTile().getSeed().getFertilizerNeeds() + ", MAX " + model.getSelectedTile().getSeed().getFertilizerLimit() + ")");
    		string = string.concat("\nHarvest day: Day " + model.getSelectedTile().getSeed().getHarvestDay());
    	}   	
    	else if (model.getSelectedTile().getIsWithered() == true) {
    		string = string.concat("\nWithered " + model.getSelectedTile().getSeed().getName() + ".");
    	}
	
    	view.displayPrompt(string);
    }
    
    public void displayPrices() {

		Seed[] seed = new Seed[8];
        Tool[] tool = new Tool[5];
        int i = 0;

        for (i = 0; i < 8; i++)
            seed[i] = new Seed(i+1, model.getFarmer().getDay());

        for (i = 0; i < 5; i++)
            tool[i] = new Tool(i+1);

        String string;

        string = "\n";
		string = string.concat("------------------\n");
        string = string.concat("Seeds\n");
        string = string.concat("------------------\n");
        string = string.concat(seed[0].getName() + ": " + ((int)seed[0].getCost() - model.getFarmer().getSeedCostReduction()) + " coins\n");
        string = string.concat(seed[1].getName() + ": " + ((int)seed[1].getCost() - model.getFarmer().getSeedCostReduction()) + " coins\n");
        string = string.concat(seed[2].getName() + ": " + ((int)seed[2].getCost() - model.getFarmer().getSeedCostReduction()) + " coins\n");
        string = string.concat(seed[3].getName() + ": " + ((int)seed[3].getCost() - model.getFarmer().getSeedCostReduction()) + " coins\n");
        string = string.concat(seed[4].getName() + ": " + ((int)seed[4].getCost() - model.getFarmer().getSeedCostReduction()) + " coins\n");
        string = string.concat(seed[5].getName() + ": " + ((int)seed[5].getCost() - model.getFarmer().getSeedCostReduction()) + " coins\n");
        string = string.concat(seed[6].getName() + ": " + ((int)seed[6].getCost() - model.getFarmer().getSeedCostReduction()) + " coins\n");
        string = string.concat(seed[7].getName() + ": " + ((int)seed[7].getCost() - model.getFarmer().getSeedCostReduction()) + " coins\n");
        string = string.concat("------------------\n");
        string = string.concat("\n");
        string = string.concat("------------------\n");
        string = string.concat("Tools\n");
        string = string.concat("------------------\n");
        string = string.concat(tool[0].getName() + ": " + ((int)tool[0].getCost()) +  " coins\n");
        string = string.concat(tool[1].getName() + ": " + ((int)tool[1].getCost()) +  " coins\n");
        string = string.concat(tool[2].getName() + ": " + ((int)tool[2].getCost()) +  " coins\n");
        string = string.concat(tool[3].getName() + ": " + ((int)tool[3].getCost()) +  " coins\n");
        string = string.concat(tool[4].getName() + ": " + ((int)tool[4].getCost()) +  " coins\n");

        view.displayPricelist(string);
	}
    
    /**
		Registers the farmer to a specific farmer type if the conditions are met.
		@param input is an integer indicating the chosen farmer type.
    */
    public boolean register(int input) {
    	valid = model.getFarmer().register(input, view);
    	view.vEastRegister(false);
    	view.getBack().setVisible(false);
    	view.eEast(true);
    	view.eCenter(true);
    	updateFarmerStats();  
    	
    	return valid;
    }
    
    /**
		Uses the tool on the selected tile if the conditions are met
		@param input is an integer indicating the chosen tool type
    */
    public void useTool(int input) {
    	Tool tool = new Tool(input);
		valid = model.getFarmer().useTool(tool, model.getSelectedTile(), view, row, col);
		view.vEastUseTool(false);
		view.getBack().setVisible(false);
    	view.eEast(true);
    	view.eCenter(true);
		updateFarmerStats(); 
    }
    
    /**
 		Buys and plants a seed on the selected tile if the conditions are met.
 		@param input is an integer indicating the chosen seed type.
    */
    public void buySeed(int input) {
       	Seed seed = new Seed(input, model.getFarmer().getDay());
		valid = model.getFarmer().buySeed(seed, model.getTile(), view, row, col);
		view.vEastBuySeed(false);
		view.getBack().setVisible(false);
    	view.eEast(true);
    	view.eCenter(true);
		updateFarmerStats(); 
    }
    
    /**
 		Checks whether the game is over. If not, the program asks the user if 
 		         he will play again.
    */
    public void gameOver() {
    	
    	// count how many tiles has no seeds or has withered
		int cnt = 0;
		Tile[][] tile = model.getTile();
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 10; j++) {
				if (tile[i][j].getSeed() == null || tile[i][j].getIsWithered() == true) {
					cnt++;
				}				
			}
		}
		
    	if (cnt == 50 && model.getFarmer().getObjectCoins() < 5) {
    		view.displayPrompt("GAME OVER" + 
    							"\nYou do not have enough money to plant another seed." +
    							"\nWould you like to play again?");
			view.vEastGameOver(true);
			view.getBack().setVisible(false);
			view.eEast(false);
	    	view.eCenter(false);
    	}
    }
}
