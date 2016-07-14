package org.xadzkd.tool;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.apache.commons.codec.binary.Base64;

public class MessageDigestUtil 
{
	public static String digestByMD5(String str) 
	{
		byte[] bts=new byte[0];
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("MD5");
			bts=md.digest(str.getBytes());
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}	
		return Base64.encodeBase64String(bts);
	}
}
