package com.basic.core.util;

import org.apache.commons.codec.binary.Base64;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

import javax.crypto.Cipher;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;

/**
 * @Author: wangzw
 * @Description: 密码工具
 * @Version: 1.0
 * @Date: 2017/10/18 10:05
 */
public class PwdRsaUtil {
	private static final String PrivateKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAKQ3vMuE4ceDfI4tvp7Ft67kSZ8e9z0+Qve9Nnp+yQw83R9zjofx6Bo1plUiB0600L0OWUSNPrvXKKPliqQsM1sNC9DbbFLsitnG+pf5R9TbpMepcrpfYrnuw6J0WO41NTlvg0QVbrJLp1K7vonMr2n2o4fjMdrlPpDcK/ho/pn1AgMBAAECgYAublwBJ0c3RCQZhzGYbsrqtj1isjyxokMJDQS0SdjKjwLue5vXMDyx/G1VMNPkGxfR/Jac3VrZfZJOFP7IQH8cUWcNxRojhz0UVmg/1ai2GFB3mOBDGU0NkC9w+rZxUZ356CSeqqr8MRQQ5tV5CoYGzDRDMgtgj5MQx6K7rwhegQJBANQMlnvZWosV4jmkdpsmv6bCrk2UgEqRqdUj0EReE3fFsMTf5pgcCCCiVjILsPNyDdP+I5Gp/0H9kWGINx6GQVECQQDGQTHAFwl+KC/JursifoipbCTj124WsYYtZOVccioDGlOrPR6qspZUBScMotm7msoMOQgqUIhYWg3khyOMrEVlAkEAiTDvt4dDhlMNP2ABgJYMTvcL0JOwgQ5HA+gc2e7Hx6j2mhmGVolkVMDS5aQcwxTaj3qQRrDmvioxAhogFzajUQJAIJ3NL4satrSp2AhujShAF4+BKjLhG76tEn7M9T6bBtOn/D4b831aGgyW9g88vw6iOwyO+Vcyd0apjnVlNdmJuQJAeLa1kLk4QWFGXxQGkUd2nDe9HimZrb2NfDRBqYQ0F1LlK7EDltazcBVzTtk6Fec8ZSwaANcafdyBX2+ylDl8qQ==";
	
	private static final String PublicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC7dAj5iVU5yS9bGkJxZ9xyy3pdG1zo02kFEEMT8ZjXqE3nK8HRMFJgW+gpikAmzmYohCSrR2adFh9cV6828Ka9xe6DXzvpeyp2KxJ51JNIpbxc2f8iYkN9ZR3xL8UQv2yyc2edn7r5lqdX1w+IpMhtCPMJlH5uWGoWF45Zz0YxQQIDAQAB";

	public static void main(String[] args) {
		String base64PrivateKey = generateBase64PrivateKey();
		System.out.println("私钥==="+base64PrivateKey);

		String base64PublicKey = generateBase64PublicKey();
		System.out.println("公钥==="+base64PublicKey);

	}
	/**
	 * @Author: wangzw
	 * @Description: 初始化
	 * @Version: 1.0
	 * @Date: 2017/10/18 10:05
	 */
	private static KeyPair initKey() {
		try {
			Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
			SecureRandom random = new SecureRandom();
			KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA", "BC");
			generator.initialize(1024, random);
			return generator.generateKeyPair();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * @Author: wangzw
	 * @Description: 生成公钥
	 * @Version: 1.0
	 * @Date: 2017/10/18 10:05
	 */
	public static String generateBase64PublicKey() {
		RSAPublicKey key = (RSAPublicKey) initKey().getPublic();
		return new String(Base64.encodeBase64(key.getEncoded()));
	}

	/**
	 * @Author: wangzw
	 * @Description: 生成密钥
	 * @Version: 1.0
	 * @Date: 2017/10/18 10:05
	 */
	public static String generateBase64PrivateKey() {
		RSAPrivateKey key = (RSAPrivateKey) initKey().getPrivate();
		return new String(Base64.encodeBase64(key.getEncoded()));
	}

	/**
	 * @Author: wangzw
	 * @Description: 解密
	 * @Version: 1.0
	 * @Date: 2017/10/18 10:06
	 */
	public static String decryptBase64(String string) {
		return new String(decrypt(Base64.decodeBase64(string)));
	}

	public static byte[] decrypt(byte[] string) {
		try {
			Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
			Cipher cipher = Cipher.getInstance("RSA/None/PKCS1Padding", "BC");
			Base64 base64Decoder= new Base64();
			byte[] buffer= base64Decoder.decode(PrivateKey);  
			PKCS8EncodedKeySpec keySpec= new PKCS8EncodedKeySpec(buffer);  
			KeyFactory keyFactory= KeyFactory.getInstance("RSA","BC");  
			RSAPrivateKey pbk = (RSAPrivateKey) keyFactory.generatePrivate(keySpec	);  
			cipher.init(Cipher.DECRYPT_MODE, pbk);
			byte[] plainText = cipher.doFinal(string);
			return plainText;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}


	private static RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();
	private static String md5 = "md5";
	private static final int number = 2;

	/**
	 * @Author: wangzw
	 * @Description: 根据密码跟salt生成密码
	 * @Version: 1.0
	 * @Date: 2017/10/18 10:19
	 */
	public static String getPassword(String password, String salt) {
		return new SimpleHash(md5, password, ByteSource.Util.bytes(salt), number).toHex();
	}

	/**
	 * @Author: wangzw
	 * @Description: 生成盐
	 * @Version: 1.0
	 * @Date: 2017/10/18 10:22
	 */
	public static String createSalt(){
		return randomNumberGenerator.nextBytes().toHex();
	}

	/**
	 * @Author: wangzw
	 * @Description: 生成加密密码
	 * @Version: 1.0
	 * @Date: 2017/10/18 10:22
	 */
	public static String encryptPassword(String passWord,String userSlat) {
		return new SimpleHash(md5, passWord, ByteSource.Util.bytes(userSlat), number).toHex();
	}


}
