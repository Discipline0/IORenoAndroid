package com.ioreno.grecoantoine.ioreno;

import java.math.BigInteger;
import java.security.MessageDigest;

public class Sha1Hashing
{
    public static String sha1(String string)
    {
        try
        {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            md.update(string.getBytes());
            BigInteger hash = new BigInteger(1, md.digest());
            return hash.toString(16);
        }
        catch (Exception e)
        {
            return "";
        }
    }
}
