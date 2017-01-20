package net.sociuris.minelw.tick;

public interface Tickable {

	/**
	 * This method is called every tick<br>
	 * If you throw an exception, the server will make a crash report with as
	 * reason "Exception in server tick loop"
	 * 
	 * @throws Exception
	 */
	public void update() throws Exception;

	/**
	 * Gets if the {@link Tickable} need to be process in a asynchronously
	 * 
	 * @return {@code true} if the {@link Tickable} need to be process asynchronously, {@code false} otherwise
	 */
	public boolean isAsynchronous();

}