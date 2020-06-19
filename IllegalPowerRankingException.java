package assignment2;

/**
 * An Exception for when the power ranking is an illegal value
 * 
 * @author Michael Bossner
 * @version 1.0
 *
 */
public class IllegalPowerRankingException extends RuntimeException {
	
	/**
	 * Default serial ID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor
	 * 
	 * @param message An error message to be displayed when the exception is 
	 * 				  thrown
	 */
	public IllegalPowerRankingException(String message) {
		super(message);
	}
}
