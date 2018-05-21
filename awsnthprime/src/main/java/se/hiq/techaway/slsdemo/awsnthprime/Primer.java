package se.hiq.techaway.slsdemo.awsnthprime;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import java.math.BigInteger;

public class Primer implements RequestHandler<BigInteger, String>{

    @Override
    public String handleRequest(BigInteger n, Context context) {
        BigInteger nth = getNthPrime(n);
        String response = responseJson(n, nth);
        context.getLogger().log(response);
        return response;
    }

    private BigInteger getNthPrime(BigInteger n) {
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

    private boolean isPrime(BigInteger n) {
        return n.isProbablePrime(99);
    }

    private String responseJson(BigInteger n, BigInteger nth) {
        StringBuilder sb = new StringBuilder();
        sb.append("{\"n\": ").append(n).append(", ");
        sb.append("\"nth\": ").append(nth).append("}");
        return sb.toString();
    }

}

