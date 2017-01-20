package net.sociuris.minelw.util;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.EncodedKeySpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import net.sociuris.logger.Logger;

public class CryptUtils {

	private static final Logger logger = Logger.getLogger();

	private CryptUtils() {
	}

	public static PublicKey decodePublicKey(byte[] encodedKey) {
		try {
			EncodedKeySpec encodedkeyspec = new X509EncodedKeySpec(encodedKey);
			KeyFactory keyfactory = KeyFactory.getInstance("RSA");
			return keyfactory.generatePublic(encodedkeyspec);
		} catch (InvalidKeySpecException | NoSuchAlgorithmException e) {
			logger.printStackTrace(e);
			return null;
		}
	}

	public static SecretKey decryptSharedKey(PrivateKey key, byte[] secretKeyEncrypted) {
		return new SecretKeySpec(decryptData(key, secretKeyEncrypted), "AES");
	}

	public static byte[] decryptData(Key key, byte[] data) {
		try {
			return getCipherInstance(2, key.getAlgorithm(), key).doFinal(data);
		} catch (IllegalBlockSizeException | BadPaddingException e) {
			logger.printStackTrace(e);
			return null;
		}
	}

	private static Cipher getCipherInstance(int opMode, String transformation, Key key) {
		try {
			Cipher cipher = Cipher.getInstance(transformation);
			cipher.init(opMode, key);
			return cipher;
		} catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException e) {
			logger.printStackTrace(e);
			return null;
		}
	}

	public static KeyPair generateKeyPair() {
		try {
			KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
			keyPairGenerator.initialize(1024);
			return keyPairGenerator.generateKeyPair();
		} catch (NoSuchAlgorithmException e) {
			logger.printStackTrace(e);
			return null;
		}
	}

}
