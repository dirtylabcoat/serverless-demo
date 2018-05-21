package se.hiq.techaway.slsdemo.awsnthprime;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import java.math.BigInteger;

public class Primer implements RequestHandler<NthPrimeRequest, NthPrimeResponse>{

    @Override
    public NthPrimeResponse handleRequest(NthPrimeRequest request, Context context) {
        BigInteger nth = getNthPrime(request.getN());
        NthPrimeResponse response = new NthPrimeResponse(request.getN(), nth);
        context.getLogger().log(response.toString());
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

}

