package command;

/**
 * Main access point to the Application.<br>
 * It handles:
 * <ul>
 * <li>filtering command duplicates
 * <li>command queues for asynchronous commands 
 * </ul>
 * 
 * @author degloba
 *
 */
public interface IGate {

	public abstract Object dispatch(Object command);

}