package net.sociuris.minelw.registry;

public abstract class RegistryClasses<V> extends RegistryNumbered<Class<? extends V>> {
	
	public V getNewInstance(Integer id) {
		try {
			Class<? extends V> targetClass = super.getValue(id);
			return (targetClass != null) ? targetClass.newInstance() : null;
		} catch (InstantiationException | IllegalAccessException e) {
			logger.printStackTrace(e);
			return null;
		}
	}
	
}