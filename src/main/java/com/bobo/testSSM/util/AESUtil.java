package com.bobo.testSSM.util;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

public class AESUtil
{
	/** 密钥算法 */
	private static final String ALGORITHM = "AES";
	/** 加密/解密算法/工作模式/填充方法 */
	// public static final String CIPHER_ALGORITHM = "AES/ECB/NoPadding";
	public static final String CIPHER_ALGORITHM = "AES/CBC/PKCS5Padding";
	public static final String KEY_ALGORITHM = "1raa70xiea6r1qm0";
	public static final String IV_ALGORITHM = "83h8ew1kx0gcsn4x";

	/**
	 * 解密
	 * 
	 * @param data
	 *            待解密数据
	 * @param key
	 *            密钥
	 * @return byte[] 解密数据
	 * @throws Exception
	 */
	private static String decrypt(byte[] data, byte[] key, byte[] iv) throws Exception
	{
		// 还原密钥
		Key k = new SecretKeySpec(key, ALGORITHM);
		/**
		 * 实例化 使用PKCS7Padding填充方式，按如下方式实现
		 * Cipher.getInstance(CIPHER_ALGORITHM,"BC");
		 */
		Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
		IvParameterSpec i = new IvParameterSpec(iv);
		// 初始化，设置解密模式
		cipher.init(Cipher.DECRYPT_MODE, k, i);
		// 执行操作
		String result = new String(cipher.doFinal(data), "UTF-8");
		return result;
	}

	/**
	 * 解密
	 * 
	 * @param data
	 *            待解密数据
	 * @param key
	 *            密钥
	 * @return byte[] 解密数据
	 * @throws Exception
	 */
	public static String decrypt(String content)
	{
		try
		{
			byte[] keyBytes = KEY_ALGORITHM.getBytes();
			byte[] ivBytes = IV_ALGORITHM.getBytes();
			byte[] dataBytes = Base64.decodeBase64(content);

			String data = decrypt(dataBytes, keyBytes, ivBytes);

			return data;
		} catch (Exception e)
		{
			return null;
		}
	}

	/**
	 * 加密
	 * 
	 * @param data
	 *            待加密数据
	 * @param key
	 *            密钥
	 * @return bytes[] 加密数据
	 * @throws Exception
	 */
	public static byte[] encrypt(byte[] data, byte[] key, byte[] iv) throws Exception
	{
		// 还原密钥
		Key k = new SecretKeySpec(key, ALGORITHM);
		/**
		 * 实例化 使用PKCS7Padding填充方式，按如下方式实现
		 * Cipher.getInstance(CIPHER_ALGORITHM,"BC");
		 */
		Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
		IvParameterSpec i = new IvParameterSpec(iv);
		// 初始化，设置为加密模式
		cipher.init(Cipher.ENCRYPT_MODE, k, i);
		// 执行操作
		return cipher.doFinal(data);
	}

	public static String encrypt(String content)
	{
		try
		{
			byte[] keyBytes = KEY_ALGORITHM.getBytes();
			byte[] ivBytes = IV_ALGORITHM.getBytes();
			byte[] dataBytes = content.getBytes("utf-8");

			byte[] data = encrypt(dataBytes, keyBytes, ivBytes);

			return Base64.encodeBase64String(data);
		} catch (Exception e)
		{
			return null;
		}
	}

	public static void main(String[] args) throws Exception
	{
		String mw = AESUtil.encrypt("{'id':468,'userId':'10622124','name':'小波',"
				+ "'tel':'13815416627','detail':'南京市，浦口区，泰山镇','postCode':'210008',"
				+ "'defaultFlag':1,'prov':'32','city':'3201','area':'320111'}");
		System.out.println(mw);

		String jm = AESUtil.decrypt(
				"dTrOaeVAaVPM8OvYKdnrKg==");
		System.out.println("明文:" + jm);
	}
}
