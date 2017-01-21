package net.sociuris.minelw.world;

import java.io.File;
import java.io.IOException;

import net.sociuris.minelw.util.math.BlockPos;
import net.sociuris.minelw.world.exception.WorldLoadException;

public class World {

	private final File directory;
	private final File levelFile;
	private final WorldInfo info;
	private final WorldLoader loader = new WorldLoader(this);

	public World(File directory) throws IOException, WorldLoadException {
		this.directory = directory;
		this.levelFile = new File(directory, "level.dat");
		this.info = new WorldInfo(this);
	}

	/**
	 * Checks if the block is loaded
	 * 
	 * @param blockPos
	 * @return {@code true} if the block is loaded, {@code false} otherwise
	 */
	public boolean blockIsLoaded(BlockPos blockPos) {
		return true;
	}

	public File getDirectory() {
		return directory;
	}

	public File getLevelFile() {
		return levelFile;
	}

	public WorldInfo getInfo() {
		return info;
	}

}