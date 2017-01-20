package net.sociuris.minelw.registry;

public abstract class RegistryNumbered<V> extends Registry<Integer, V> {

	@Override
	public boolean register(Integer key, V value) {
		return super.register(key, value);
	}
	
}