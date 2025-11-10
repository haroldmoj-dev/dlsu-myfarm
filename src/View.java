package src;

// GUIAO, Miguel Stephen G. (S22)
// MOJICA, Harold C.        (S21)

import javax.swing.*; 
import java.awt.*;
import java.awt.event.ActionListener;

/**
    The <code>View</code> class serves as a blueprint for the collection of elements that represent 
	         the user interface. This contains numerous labels and buttons as well
	         as a text area that displays the most recent action peformed. This 
	         also contains the methods for adding action listeners, making a set 
	         of buttons visible/invisible, enabling/disabling a set of buttons, 
	         and setting the text/color of certain elements.
*/
public class View extends JFrame {
    
    // NORTH
    private JLabel heading;
    
    // WEST
    private JLabel day;
    private JLabel objectCoins;
    private JLabel level;
    private JLabel experience;
    private JLabel farmerType;
    private JTextArea pricelist;
    
    // EAST - default
    private JLabel label;
    private JButton advanceDay;
    private JButton register;
    private JButton back;

    // EAST - register
    private JButton registeredFarmer;
    private JButton distinguishedFarmer;
    private JButton legendaryFarmer;
    
    // EAST - tile
    private JButton useTool;
    private JButton buySeed;
    private JButton harvest;
    
    // EAST - useTool
    private JButton plow;
    private JButton wateringCan;
    private JButton fertilizer;
    private JButton shovel;
    private JButton pickaxe;
    
    // EAST - buySeed
    private JButton turnip;
    private JButton carrot;
    private JButton potato;
    private JButton rose;
    private JButton tulips;
    private JButton sunflower;
    private JButton mango;
    private JButton apple;
    
    // EAST - gameOver
    private JButton playAgain;
    private JButton exit;
    
	// SOUTH
    private JTextArea prompt;
    
    // CENTER
    private JButton[][] tile = new JButton[5][10];
    
	/**
		<code>View</code> constuctor creates the user interface with pre-defined settings
	*/
    public View() {
    	
        super("MyFarm");
        
        setLayout(new BorderLayout());
        setSize(1200, 600);
        setLocationRelativeTo(null);

        init();

        setVisible(true);
        setResizable(true);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   
    }

    /**
		Initializes all the components of the user interface
    */
    private void init() {

        // NORTH
        JPanel panelNorth = new JPanel();
        panelNorth.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
        panelNorth.setLayout(new FlowLayout());
        panelNorth.setBackground(Color.black);

        heading = new JLabel();
        heading.setForeground(Color.white);
        heading.setFont(new Font("Helvetica", Font.BOLD, 14));
        
        panelNorth.add(heading);

        this.add(panelNorth, BorderLayout.NORTH);

        // WEST
        JPanel panelWest = new JPanel();
        panelWest.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 0));
        panelWest.setLayout(new BoxLayout(panelWest, BoxLayout.PAGE_AXIS));
        panelWest.setBackground(Color.decode("#c5ac83"));
        
        day = new JLabel();
        objectCoins = new JLabel();
        level = new JLabel();
        experience = new JLabel();
        farmerType = new JLabel();
        pricelist = new JTextArea();
        
        pricelist.setBackground(Color.decode("#c5ac83"));
        pricelist.setEditable(false);
        
        panelWest.add(day);
        panelWest.add(objectCoins);
        panelWest.add(level);
        panelWest.add(experience);
        panelWest.add(farmerType);
        panelWest.add(pricelist);

        this.add(panelWest, BorderLayout.WEST);

        // EAST
        JPanel panelEast = new JPanel();
        panelEast.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 10));
        panelEast.setBackground(Color.decode("#c5ac83"));
        panelEast.setLayout(new BoxLayout(panelEast, BoxLayout.PAGE_AXIS));
        
        label = new JLabel("Choose an action:");
        advanceDay = new JButton("Advance Day");
        register = new JButton("Register");
        registeredFarmer = new JButton("Registered Farmer");
        distinguishedFarmer = new JButton("Distinguished Farmer");
        legendaryFarmer = new JButton("Legendary Farmer");
        useTool = new JButton("Use a Tool");
        buySeed = new JButton("Buy and Plant a Seed");
        harvest = new JButton("Harvest the Crop");      
        plow = new JButton("Plow");
        wateringCan = new JButton("Watering Can");
        fertilizer = new JButton("Fertilizer");
        shovel = new JButton("Shovel");
        pickaxe = new JButton("Pickaxe");        
        turnip = new JButton("Turnip");
        carrot = new JButton("Carrot");
        potato = new JButton("Potato");
        rose = new JButton("Rose");
        tulips = new JButton("Tulips");
        sunflower = new JButton("Sunflower");
        mango = new JButton("Mango");
        apple = new JButton("Apple");
        playAgain = new JButton("Play Again");
        exit = new JButton("Exit");
        back = new JButton("Back");
        
        panelEast.add(label);
        panelEast.add(Box.createRigidArea(new Dimension(0, 5)));
        panelEast.add(advanceDay);
        panelEast.add(register);
        panelEast.add(Box.createRigidArea(new Dimension(0, 10)));
        panelEast.add(registeredFarmer);
        panelEast.add(distinguishedFarmer);
        panelEast.add(legendaryFarmer);    
        panelEast.add(useTool);
        panelEast.add(buySeed);
        panelEast.add(harvest);      
        panelEast.add(plow);
        panelEast.add(wateringCan);
        panelEast.add(fertilizer);
        panelEast.add(shovel);
        panelEast.add(pickaxe);        
        panelEast.add(turnip);
        panelEast.add(carrot);
        panelEast.add(potato);
        panelEast.add(rose);
        panelEast.add(tulips);
        panelEast.add(sunflower);
        panelEast.add(mango);
        panelEast.add(apple);
        panelEast.add(playAgain);
        panelEast.add(exit);
        panelEast.add(Box.createRigidArea(new Dimension(0, 10)));
        panelEast.add(back);
        
        registeredFarmer.setVisible(false);
        distinguishedFarmer.setVisible(false);
        legendaryFarmer.setVisible(false);
        useTool.setVisible(false);
        buySeed.setVisible(false);
        harvest.setVisible(false);
        plow.setVisible(false);
        wateringCan.setVisible(false);
        fertilizer.setVisible(false);
        shovel.setVisible(false);
        pickaxe.setVisible(false);
        turnip.setVisible(false);
        carrot.setVisible(false);
        potato.setVisible(false);
        rose.setVisible(false);
        tulips.setVisible(false);
        sunflower.setVisible(false);
        mango.setVisible(false);
        apple.setVisible(false);
        playAgain.setVisible(false);
        exit.setVisible(false);
        back.setVisible(false);
        
        this.add(panelEast, BorderLayout.EAST);

        // SOUTH
        JPanel panelSouth = new JPanel();
        panelSouth.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
        panelSouth.setLayout(new FlowLayout());
        panelSouth.setBackground(Color.black);

        prompt = new JTextArea("Welcome to MyFarm!");
        prompt.setFont(new Font("Helvetica", Font.BOLD, 14));
        prompt.setEditable(false);
        
        panelSouth.add(prompt);
        prompt.setForeground(Color.white);
        prompt.setBackground(Color.black);
        
        this.add(panelSouth, BorderLayout.SOUTH);
        
        // CENTER
        JPanel panelCenter = new JPanel();
        panelCenter.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panelCenter.setBackground(Color.decode("#c5ac83"));
        panelCenter.setLayout(new GridLayout(5, 10, 10, 10));  
        
        for (int i = 0; i < 5; i++) {
        	for (int j = 0; j < 10; j++) {
        		tile[i][j] = new JButton("");
                tile[i][j].setBackground(Color.decode("#a66841"));
                tile[i][j].setForeground(Color.white);
                tile[i][j].setOpaque(true);
                tile[i][j].setBorderPainted(false);
        		panelCenter.add(tile[i][j]);
        	}
        }

        panelCenter.setBounds(200, 200, 200, 200);
        this.add(panelCenter, BorderLayout.CENTER);   
    }

    /**
		Adds an action listener for all buttons.
		@param listener is an action listener to receive action events.
    */
    public void addActionListener(ActionListener listener) {
    	
    	// EAST - default
        advanceDay.addActionListener(listener);
        register.addActionListener(listener);
        back.addActionListener(listener);
        
        // EAST - register
        registeredFarmer.addActionListener(listener);
        distinguishedFarmer.addActionListener(listener);
        legendaryFarmer.addActionListener(listener);
        
        // EAST - tile
        useTool.addActionListener(listener);
        buySeed.addActionListener(listener);
        harvest.addActionListener(listener);
        
        // EAST - useTool
        plow.addActionListener(listener);
        wateringCan.addActionListener(listener);
        fertilizer.addActionListener(listener);
        shovel.addActionListener(listener);
        pickaxe.addActionListener(listener);

        // EAST - buySeed
        turnip.addActionListener(listener);
        carrot.addActionListener(listener);
        potato.addActionListener(listener);
        rose.addActionListener(listener);
        tulips.addActionListener(listener);
        sunflower.addActionListener(listener);
        mango.addActionListener(listener);
        apple.addActionListener(listener);
        
        // EAST - gameOver
        playAgain.addActionListener(listener);
        exit.addActionListener(listener);
        
        // CENTER
        for (int i = 0; i < 5; i++) {
        	for (int j = 0; j < 10; j++) {
        		tile[i][j].addActionListener(listener);
        	}
        }
    }
    
    /**
		Makes a certain set of buttons visible or invisible.
		
		@param b is a boolean that determines if the buttons are to be made 
		         visible or invisible.
    */
    // EAST - register
    public void vEastRegister(boolean b) {
    	registeredFarmer.setVisible(b);
        distinguishedFarmer.setVisible(b);
        legendaryFarmer.setVisible(b);
    }
    
    // EAST - tile
    /** 
     * 
     * Makes a certain set of buttons visible or invisible.
     * @param b is a boolean that determines if the buttons are to be made 
	 *	         visible or invisible.
     */
    public void vEastTile(boolean b) {
    	useTool.setVisible(b);
        buySeed.setVisible(b);
        harvest.setVisible(b);
    }
    
    // EAST - useTool
    /**
     * 
     * Makes a certain set of buttons visible or invisible.
     * @param b is a boolean that determines if the buttons are to be made 
	 *	        visible or invisible.
     */
    public void vEastUseTool(boolean b) {
    	plow.setVisible(b);
        wateringCan.setVisible(b);
        fertilizer.setVisible(b);
        shovel.setVisible(b);
        pickaxe.setVisible(b);
    }
    
    // EAST - buySeed
    /**
     * 
     * Makes a certain set of buttons visible or invisible.
     * @param b is a boolean that determines if the buttons are to be made 
	 *	        visible or invisible.
     */
    public void vEastBuySeed(boolean b) {
    	turnip.setVisible(b);
        carrot.setVisible(b);
        potato.setVisible(b);
        rose.setVisible(b);
        tulips.setVisible(b);
        sunflower.setVisible(b);
        mango.setVisible(b);
        apple.setVisible(b);
    }
    
    // EAST - gameOver
    /**
     * 
     * Makes a certain set of buttons visible or invisible.
     * @param b is a boolean that determines if the buttons are to be made 
	 *	        visible or invisible.
     */
    public void vEastGameOver(boolean b) {
    	playAgain.setVisible(b);
        exit.setVisible(b);
    }    
    
    /**
		Enable or disable a certain set of buttons on the east panel
		@param b is a boolean that determines if the buttons are to be 
		         enabled or disabled.
    */
    // EAST - default
    public void eEast(boolean b) {
        advanceDay.setEnabled(b);
        register.setEnabled(b);
    }
    
    // CENTER

    /**
     * Enable or disable a certain set of buttons on the east panel
     * @param b is a boolean that determines if the buttons are to be 
		         enabled or disabled.
     */
    public void eCenter(boolean b) {
        for (int i = 0; i < 5; i++) {
        	for (int j = 0; j < 10; j++) {
        		tile[i][j].setEnabled(b);
        	}
        }  	
    }

    // NORTH
	/**
     * Displays the heading
	 * @param day is an integer that indicates the current day
	 */
	public void displayHeading(int day) {
	    this.heading.setText("MYFARM - Day " + (Integer.toString(day)));
	} 
	// WEST
	/**
     * Displays the current day
	 * @param day is an integer that indicates the current day
	 */
	public void displayDay(int day) {
		this.day.setText("Day: " + (Integer.toString(day)));
	}
	/**
     * Display current object coins
	 * @param objectCoins indicates the farmer's object coins
	 */
	public void displayObjectCoins(double objectCoins) {
	    this.objectCoins.setText("Coins: " + (Double.toString(objectCoins)));
	}
	/**
     * Displays the current level
	 * @param level indicates the farmer's level.
	 */
	public void displayLevel(int level) {
	    this.level.setText("Level: " + (Integer.toString(level)));
	}
	/**
     * Displays current experience point of farmer
	 * @param experience indicates the farmer's experience
	 */
	public void displayExperience(double experience) {
	    this.experience.setText("Experience: " + (Double.toString(experience)));
	}
	/**
     * Displays the current farmer type
	 * @param farmerType indicates the farmer's farmer type
	 */
	public void displayFarmerType(String farmerType) {
	    this.farmerType.setText("Farmer type: " + farmerType);
	}
	
	/**
	 * Display the price of seed and tools
	 * @param pricelist contatins prices of seeds and tools
	 */
	public void displayPricelist(String pricelist) {
		this.pricelist.setText(pricelist);
	}
	
	// SOUTH
	/**
     * Displays necessary prompts to the user (Ex. Tile status)
	 * @param prompt is a string stating the most recent action peformed or 
	 *	      the status of the selected tile.
	 */
	public void displayPrompt(String prompt) {
	    this.prompt.setText(prompt);
	}
	
	
    /**
		Sets the name and color of the tiles
		@param row is an integer indicating the row number of the selected tile.
		@param col is an integer indicating the col number of the selected tile.
		@param string is a String stating the status of the tile.
    */
	// CENTER
    public void displayTileColor(int row, int col, String string) {  	
        if (string.equals("unplowed")) {
            tile[row][col].setBackground(Color.decode("#a66841"));
        }
    	else if (string.equals("plowed")) {
    		tile[row][col].setBackground(Color.decode("#883722"));
        }
    	else if (string.equals("planted")) {
    		tile[row][col].setBackground(Color.decode("#489919"));
        }
    	else if (string.equals("watered")) {
    		tile[row][col].setBackground(Color.decode("#195e0b"));
        }
       	else if (string.equals("withered")) {
       		tile[row][col].setBackground(Color.decode("#380929")); 
        }
       	else if (string.equals("rock")) {
       		tile[row][col].setBackground(Color.decode("#929093"));    	
        }
        tile[row][col].setOpaque(true);
        tile[row][col].setBorderPainted(false);
    }

    /**
     * Displays the tile name
     * @param row is an integer indicating the row number of the selected tile.
     * @param col is an integer indicating the col number of the selected tile.
     * @param name is a String stating the name of the crop.
     */
    public void displayTileName(int row, int col, String name) {
    	tile[row][col].setText(name);
    }
    
	/*
		GETTERS
	*/
    // EAST - default
    /**
     * Get advance day button
     * @return Advance day button
     */
    public JButton getAdvanceDay() {
        return advanceDay;
    }
    /**
     * Get register button
     * @return Register button
     */
    public JButton getRegister() {
        return register;
    }
    /**
     * Get back button
     * @return Back button
     */
    public JButton getBack() {
        return back;
    }
    
    // EAST - register
    /**
     * Get registered farmer button
     * @return Registered farmer button
     */
    public JButton getRegisteredFarmer() {
        return registeredFarmer;
    } 
    /**
     * Get distinguished farmer button
     * @return Distinguished farmer button
     */
    public JButton getDistinguishedFarmer() {
        return distinguishedFarmer;
    }  
    /**
     * Get legendary farmer button
     * @return Legendary farmer button
     */
    public JButton getLegendaryFarmer() {
        return legendaryFarmer;
    }
    
    // EAST - tile  
    /**
     * Get use tool button
     * @return Tool button
     */
    public JButton getUseTool() {
        return useTool;
    }    
    /**
     * Get buy seed button
     * @return Buy seed button
     */
    public JButton getBuySeed() {
        return buySeed;
    }    
    /**
     * Get harvest button
     * @return Harvest button
     */
    public JButton getHarvest() {
        return harvest;
    }    
    
    // EAST - useTool
    /**
     * Get plow button
     * @return Plow button
     */
    public JButton getPlow() {
        return plow;
    }
    /**
     * Get watering can button
     * @return Watering can button
     */
    public JButton getWateringCan() {
        return wateringCan;
    }  
    /**
     * Get fertilizer button
     * @return Fertilizer button
     */
    public JButton getFertilizer() {
        return fertilizer;
    }  
    /**
     * Get shovel button
     * @return Shovel button
     */
    public JButton getShovel() {
        return shovel;
    }  
    /**
     * Get pickaxe button
     * @return Pickaxe button
     */
    public JButton getPickaxe() {
        return pickaxe;
    }  
    
    // EAST - buySeed
    /**
     * Get turnip button
     * @return Turnip button
     */
    public JButton getTurnip() {
        return turnip;
    }
    /**
     * Get carrot button
     * @return Carrot button
     */
    public JButton getCarrot() {
        return carrot;
    }
    /**
     *  Get potato button
     * @return Potato button
     */
    public JButton getPotato() {
        return potato;
    }
    /**
     * Get rose button
     * @return Rose button
     */
    public JButton getRose() {
        return rose;
    }
    /**
     * Get tulips button
     * @return Tulips button
     */
    public JButton getTulips() {
        return tulips;
    }
    /**
     * Get sunflower button
     * @return Sunflower button
     */
    public JButton getSunflower() {
        return sunflower;
    }
    /**
     * Get mango button
     * @return Mango button
     */
    public JButton getMango() {
        return mango;
    }
    /**
     * Get apple button
     * @return Apple button
     */
    public JButton getApple() {
        return apple;
    }
    
    // EAST - gameOver
    /**
     * Get play again button
     * @return Play again button
     */
    public JButton getPlayAgain() {
        return playAgain;
    }
    /**
     * et the exit button
     * @return Exit button
     */
    public JButton getExit() {
        return exit;
    }
    
    // CENTER
    /**
     * Get tile button[][]
     * @return Tile button[][]
     */
    public JButton[][] getTile() {
        return tile;
    }
}
