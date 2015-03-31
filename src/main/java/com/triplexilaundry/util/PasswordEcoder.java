/**
* <p>Title: PasswordEcoder.java</p>
* <p>Description: </p>
* <p>Copyright: Copyright (c) 2015</p>
* <p>All Right Reserved</p>
* @author Fan Wang
* @date Mar 13, 2015
* @version 1.0
*/
package com.triplexilaundry.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>Title: PasswordEcoder</p>
 * <p>Description: MD5 password encrypt</p>
 * <p>All Right Reserved</p> 
 * @author Fan Wang
 * @date Mar 13, 2015
 */
public class PasswordEcoder {
	
	private static final Logger log = LoggerFactory.getLogger(PasswordEcoder.class);
	
	public static String encrypt(String password) throws NoSuchAlgorithmException{
		String encryptpw = null;
		
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(password.getBytes());
			encryptpw = new BigInteger(1,md.digest()).toString(16);
			log.info("success to encrypt password");
			return encryptpw;
			
		} catch (NoSuchAlgorithmException e) {
			log.error("no such encrypt algorithm");
			throw e;
		}
		
	}

}
