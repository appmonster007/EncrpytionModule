package cryptoModules;

import java.math.BigInteger;
import java.util.Random;

public class easyRSA {
    private BigInteger P;
    private BigInteger Q;
    private BigInteger N;
    private BigInteger PHI;
    private BigInteger e;
    private BigInteger d;
    private int maxLength = 1024;
    private Random R;
 
    public easyRSA()
    {
        R = new Random();
        P = BigInteger.probablePrime(maxLength, R);
        Q = BigInteger.probablePrime(maxLength, R);
        N = P.multiply(Q);
        PHI = P.subtract(BigInteger.ONE).multiply(  Q.subtract(BigInteger.ONE));
        e = BigInteger.probablePrime(maxLength / 2, R);
        while (PHI.gcd(e).compareTo(BigInteger.ONE) > 0 && e.compareTo(PHI) < 0)
        {
            e.add(BigInteger.ONE);
        }
        d = e.modInverse(PHI);
    }
 
    public void setPQe(BigInteger P, BigInteger Q, BigInteger e)
    {
        this.P = P;
        this.Q = Q;
        this.e = e;
        this.N = P.multiply(Q);
        PHI = P.subtract(BigInteger.ONE).multiply(  Q.subtract(BigInteger.ONE));
        while (PHI.gcd(e).compareTo(BigInteger.ONE) > 0 && e.compareTo(PHI) < 0)
        {
            e.add(BigInteger.ONE);
        }
        d = e.modInverse(PHI);
    }
    
    public easyRSA(BigInteger e, BigInteger d, BigInteger N)
    {
        this.e = e;
        this.d = d;
        this.N = N;
    }
 
    public static String bToS(byte[] cipher)
    {
        String temp = "";
        for (byte b : cipher)
        {
            temp += Byte.toString(b);
        }
        return temp;
    }
 
    // Encrypting the message
    public byte[] encryptMessage(byte[] message)
    {
        return (new BigInteger(message)).modPow(e, N).toByteArray();
    }
 
    // Decrypting the message
    public byte[] decryptMessage(byte[] message)
    {
        return (new BigInteger(message)).modPow(d, N).toByteArray();
    } 
}
