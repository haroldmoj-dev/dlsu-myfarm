package src;

// GUIAO, Miguel Stephen G. (S22)
// MOJICA, Harold C.        (S21)

/**
	Serves as a <code>Main</code> driver class for the whole program. It 
	         creates the view, mode, and controller objects which 
	         implements the user interface, defines the data, and 
	         controls the logic.
*/
public class Main {

    /**
	 * The View and model are instantiated inside the main method. Both of
	 * which are used as parameter for the Controller class
     * @param args .
     */
    public static void main(String[] args) {
		View view = new View();
		Model model = new Model();
		Controller controller = new Controller(view, model);
	}
}
