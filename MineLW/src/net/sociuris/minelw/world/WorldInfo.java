package net.sociuris.minelw.world;

import java.io.FileInputStream;
import java.io.IOException;

import net.sociuris.minelw.nbt.NBTTagCompound;
import net.sociuris.minelw.nbt.NBTUtils;
import net.sociuris.minelw.player.GameMode;
import net.sociuris.minelw.util.math.BlockPos;
import net.sociuris.minelw.world.exception.WorldLoadException;
import net.sociuris.util.StringUtils;

public class WorldInfo {

	private String versionName;
	private int versionID;
	private boolean versionSnapshot;

	private long randomSeed;
	private GameMode gameMode;
	private BlockPos spawnPos;

	protected WorldInfo(World world) throws IOException, WorldLoadException {
		NBTTagCompound nbtTagCompound = NBTUtils.readCompressed(new FileInputStream(world.getLevelFile()));
		if (nbtTagCompound.hasCompound("Data")) {
			NBTTagCompound levelInfo = nbtTagCompound.getCompound("Data");

			if (levelInfo.hasCompound("Version")) {
				NBTTagCompound versionCompound = levelInfo.getCompound("Version");
				this.versionName = versionCompound.getString("Name");
				this.versionID = versionCompound.getInt("Id");
				this.versionSnapshot = versionCompound.getBoolean("Snapshot");
			}

			this.randomSeed = levelInfo.getLong("RandomSeed");
			this.gameMode = GameMode.getGameMode(levelInfo.getInt("GameType"));
			this.spawnPos = new BlockPos(levelInfo.getInt("SpawnX"), levelInfo.getInt("SpawnY"),
					levelInfo.getInt("SpawnZ"));

		} else
			throw new WorldLoadException("Invalid level.dat (Missing Data compound)");
	}

	public String getVersionName() {
		return versionName;
	}

	public int getVersionID() {
		return versionID;
	}

	public boolean isSnapshot() {
		return versionSnapshot;
	}

	public long getRandomSeed() {
		return randomSeed;
	}

	public GameMode getGameMode() {
		return gameMode;
	}

	public BlockPos getSpawnPos() {
		return spawnPos;
	}

	@Override
	public String toString() {
		return "WorldInfo[version=[name=" + versionName + ",id=" + StringUtils.toString(versionID) + ",snapshot="
				+ StringUtils.toString(versionSnapshot) + "],randomSeed=" + StringUtils.toString(randomSeed)
				+ ",gameMode=" + StringUtils.toString(gameMode) + ",spawnPos=" + StringUtils.toString(spawnPos) + "]";
	}

}