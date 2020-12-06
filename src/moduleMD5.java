import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.DigestInputStream;
import java.security.MessageDigest; 
import java.security.NoSuchAlgorithmException; 

// Java program to calculate MD5 hash value 
public class moduleMD5 {
    public static String addSalt(String input, String salt)
    {
        return input+salt;
    }
    public static String encryptMD5(String input) 
	{ 
		try { 

			// Static getInstance method is called with hashing MD5 
			MessageDigest md = MessageDigest.getInstance("MD5"); 

			// digest() method is called to calculate message digest 
			// of an input digest() return array of byte 
			byte[] messageDigest = md.digest(input.getBytes()); 

			// Convert byte array into signum representation 
			BigInteger number = new BigInteger(1, messageDigest); 

			// Convert message digest into hex value 
			String hashtext = number.toString(16); 
			while (hashtext.length() < 32) { 
				hashtext = "0" + hashtext; 
            }
            String upHashText = ((String)hashtext).toUpperCase();
			return upHashText; 
		} 

		// For specifying wrong message digest algorithms 
		catch (NoSuchAlgorithmException e) { 
			throw new RuntimeException(e); 
		} 
    }
    
    public static String decryptMD5(String input) 
	{ 
		try { 

            input = input.toLowerCase();
			// Static getInstance method is called with hashing MD5 
			MessageDigest md = MessageDigest.getInstance("MD5"); 

			// digest() method is called to calculate message digest 
			// of an input digest() return array of byte 
			byte[] messageDigest = md.digest(input.getBytes()); 

			// Convert byte array into signum representation 
			BigInteger noBigInteger = new BigInteger(1, messageDigest); 

			// Convert message digest into hex value 
			String hashtext = noBigInteger.toString(16); 
			while (hashtext.length() < 32) { 
				hashtext = "0" + hashtext; 
			} 
			return hashtext; 
		} 

		// For specifying wrong message digest algorithms 
		catch (NoSuchAlgorithmException e) { 
			throw new RuntimeException(e); 
		} 
	}

    private static byte[] checksum(String filePath) {

        MessageDigest md;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalArgumentException(e);
        }

        try (InputStream is = new FileInputStream(filePath);
             DigestInputStream dis = new DigestInputStream(is, md)) {
            while (dis.read() != -1) ; //empty loop to clear the data
            md = dis.getMessageDigest();
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
        return md.digest();

    }

	// Driver code 
	public static void main(String args[]) throws NoSuchAlgorithmException 
	{ 
        String s = "Geeksfoegeeks";
        String encr = encryptMD5(s);
        System.out.println("Your Hash MD5 is: " + encr); 
        String decrp = decryptMD5(encr);
		System.out.println("Your decr MD5 is: " + decrp); 
	} 
} 
