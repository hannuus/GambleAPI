package com.hannuus.core.utils;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.log4j.Logger;

public class AESHelper {
	
	private static final String password = "gamble";
	private static final Logger logger = Logger.getLogger(AESHelper.class);
	
	/**
	 * 加密
	 * @method encrypt
	 * @param content	需要加密的内容
	 * @param password	加密密码
	 * @return
	 * @throws 
	 */
	public static String encrypt(String content){
		try {
			KeyGenerator kgen = KeyGenerator.getInstance("AES");
			kgen.init(128, new SecureRandom(password.getBytes()));
			SecretKey secretKey = kgen.generateKey();
			byte[] enCodeFormat = secretKey.getEncoded();
			SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
			Cipher cipher = Cipher.getInstance("AES");// 创建密码器
			byte[] byteContent = content.getBytes("utf-8");
			cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化
			byte[] result = cipher.doFinal(byteContent);
			return parseByte2HexStr(result); // 加密
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			logger.error(e);
		}catch (NoSuchPaddingException e) {
			e.printStackTrace();
			logger.error(e);
		}catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			logger.error(e);
		}catch (InvalidKeyException e) {
			e.printStackTrace();
			logger.error(e);
		}catch (IllegalBlockSizeException e) {
			e.printStackTrace();
			logger.error(e);
		}catch (BadPaddingException e) {
			e.printStackTrace();
			logger.error(e);
		}
		return null;
	}
	
	/**
	 * 解密
	 * @method decrypt
	 * @param content	待解密内容
	 * @param password	解密密钥
	 * @return
	 * @throws 
	 */
	public static String decrypt(String str){
		try {
			byte[] content = parseHexStr2Byte(str);
			KeyGenerator kgen = KeyGenerator.getInstance("AES");
			kgen.init(128, new SecureRandom(password.getBytes()));
			SecretKey secretKey = kgen.generateKey();
			byte[] enCodeFormat = secretKey.getEncoded();
			SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
			Cipher cipher = Cipher.getInstance("AES");// 创建密码器
			cipher.init(Cipher.DECRYPT_MODE, key);// 初始化
			byte[] result = cipher.doFinal(content);
			return new String(result); // 解密
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			logger.error(e);
		}catch (NoSuchPaddingException e) {
			e.printStackTrace();
			logger.error(e);
		}catch (InvalidKeyException e) {
			e.printStackTrace();
			logger.error(e);
		}catch (IllegalBlockSizeException e) {
			e.printStackTrace();
			logger.error(e);
		}catch (BadPaddingException e) {
			e.printStackTrace();
			logger.error(e);
		}
		
		return null;
	}
	
	/**
	 * 将二进制转换成16进制
	 * @method parseByte2HexStr
	 * @param buf
	 * @return
	 * @throws 
	 */
	public static String parseByte2HexStr(byte buf[]){
		StringBuffer sb = new StringBuffer();
		for(int i = 0; i < buf.length; i++){
			String hex = Integer.toHexString(buf[i] & 0xFF);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			sb.append(hex.toUpperCase());
		}
		return sb.toString();
	}
	
	/**
	 * 将16进制转换为二进制
	 * @method parseHexStr2Byte
	 * @param hexStr
	 * @return
	 * @throws 
	 */
	public static byte[] parseHexStr2Byte(String hexStr){
		if(hexStr.length() < 1)
			return null;
		byte[] result = new byte[hexStr.length()/2];
		for (int i = 0;i< hexStr.length()/2; i++) {
			int high = Integer.parseInt(hexStr.substring(i*2, i*2+1), 16);
			int low = Integer.parseInt(hexStr.substring(i*2+1, i*2+2), 16);
			result[i] = (byte) (high * 16 + low);
		}
		return result;
	}
}


