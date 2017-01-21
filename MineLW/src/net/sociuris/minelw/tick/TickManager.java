package net.sociuris.minelw.tick;

import java.util.ArrayList;
import java.util.List;

import net.sociuris.logger.Logger;
import net.sociuris.minelw.crash.CrashReport;
import net.sociuris.minelw.server.MinecraftServer;

public class TickManager {

	private final Logger logger = Logger.getLogger();
	private final List<Tickable> tickablesList = new ArrayList<Tickable>();

	private Exception tickLoopException = null;
	private long currentTick = 0L;
	private long lastWarning = 0L;

	public TickManager(MinecraftServer minecraftServer) {
		logger.debug("Starting tick loop...");
		new Thread("Tick Loop") {
			@Override
			public void run() {
				try {
					long lastTick = System.currentTimeMillis();
					long sleepTime = 50L;
					while (minecraftServer.isStarted()) {
						currentTick++;
						long currentTime = System.currentTimeMillis();
						long elapsedTime = currentTime - lastTick;

						if (elapsedTime > 2000L && (currentTick - lastWarning) > 300L) {
							logger.warn(
									"Can't keep up! Did the system time change, or is the server overloaded? Running %dms behind, skipping %d tick(s)",
									elapsedTime, elapsedTime / 50L);
							lastWarning = currentTick;
						}

						if (elapsedTime < 0) {
							logger.warn("Time ran backwards! Did the system time change?");
							elapsedTime = 0;
						}

						sleepTime = 50L - (elapsedTime / 50L);

						TickManager.this.tick();

						lastTick = currentTime;
						Thread.sleep(sleepTime);
					}
				} catch (Exception e) {
					CrashReport.makeCrashReport("Exception in server tick loop", e, 5);
				}
			}
		}.start();
	}

	/**
	 * Tick the server
	 * 
	 * @throws Exception
	 */
	private void tick() throws Exception {
		for (Tickable tickable : tickablesList) {
			if (tickable.isAsynchronous()) {
				new Thread("Tickable " + tickable.getClass().getSimpleName()) {
					@Override
					public void run() {
						try {
							tickable.update();
						} catch (Exception exception) {
							tickLoopException = exception;
						}
					}
				}.start();
			} else {
				tickable.update();
			}
		}
		if (tickLoopException != null)
			throw tickLoopException;
	}

	/**
	 * Register a {@link Tickable}
	 * 
	 * @param tickable
	 * @return {@code true} if the {@link Tickable} is correctly registered,
	 *         {@code false} otherwise
	 */
	public boolean registerTickable(Tickable tickable) {
		if (!tickablesList.contains(tickable)) {
			return tickablesList.add(tickable);
		} else
			return false;
	}

	/**
	 * Unregister a {@link Tickable}
	 * 
	 * @param tickable
	 * @return {@code true} if the {@link Tickable} is correclty unregistered,
	 *         {@code false} otherwise
	 */
	public boolean unregisterTickable(Tickable tickable) {
		return tickablesList.remove(tickable);
	}

	/**
	 * Checks if the given {@link Tickable} is registered in the server tick
	 * loop
	 * 
	 * @param tickable
	 * @return {@code true} if the {@link Tickable} is registered, {@code false}
	 *         otherwise
	 */
	public boolean isRegistered(Tickable tickable) {
		return tickablesList.contains(tickable);
	}

	/**
	 * Returns the number of registered {@link Tickable}
	 * 
	 * @return the number of {@link Tickable}
	 */
	public int size() {
		return tickablesList.size();
	}
}