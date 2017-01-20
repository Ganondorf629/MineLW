package net.sociuris.minelw.translation;

import java.util.HashMap;
import java.util.Map;

public class TranslationManager {

	private final Translation defaultTranslation;
	private final Map<String, Translation> translations = new HashMap<String, Translation>();

	public TranslationManager() {
		this.defaultTranslation = new Translation();
		
		
	}
	
	public Translation getDefaultTranslation() {
		return defaultTranslation;
	}

	public Translation getTranslation(String langName) {
		return translations.getOrDefault(langName, defaultTranslation);
	}

}