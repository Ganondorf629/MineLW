package net.sociuris.minelw.registry;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import net.sociuris.logger.Logger;

public abstract class Registry<K,V> {

	protected final Logger logger = Logger.getLogger();
	
	private Map<K, V> registryMap = new HashMap<K, V>();

	public Registry() {}

	protected boolean register(K key, V value) {
		if(!registryMap.containsKey(key)){
			logger.debug("Register %s (%s)", value.getClass().getSimpleName(), key.toString());
			registryMap.put(key, value);
			return true;
		}
		else {
			logger.error("%s already registered!", value.getClass().getSimpleName());
			return false;
		}
	}

	protected V getValue(K key) {
		return registryMap.get(key);
	}

	protected K getKey(V value) {
		for (Entry<K,V> registryData : registryMap.entrySet())
			if (registryData.getValue().equals(value))
				return registryData.getKey();
		return null;
	}
	
	protected Set<Entry<K, V>> entrySet() {
		return registryMap.entrySet();
	}
}
