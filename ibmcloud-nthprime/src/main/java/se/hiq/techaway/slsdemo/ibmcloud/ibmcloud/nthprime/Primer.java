package se.hiq.techaway.slsdemo.ibmcloud.ibmcloud.nthprime;

import com.google.gson.JsonObject;
import java.math.BigInteger;

public class Primer {

    public static JsonObject main(JsonObject args) {
        BigInteger n = args.getAsJsonPrimitive("n").getAsBigInteger();
        BigInteger nth = getNthPrime(n);
        JsonObject response = new JsonObject();
        response.addProperty("n", n);
        response.addProperty("nth", nth);
        return response;
    }
    
    private static BigInteger getNthPrime(BigInteger n) {
        BigInteger count = BigInteger.ZERO;
        BigInteger candidate = BigInteger.valueOf(2);
        while (count.compareTo(n) == -1) {
            if (isPrime(candidate)) {
                count = count.add(BigInteger.ONE);
            }
            candidate = candidate.add(BigInteger.ONE);
        }
        // The candidate has been incremented once after the count reached n
        return candidate.subtract(BigInteger.ONE);
    }

    private static boolean isPrime(BigInteger n) {
        return n.isProbablePrime(99);
    }

}
