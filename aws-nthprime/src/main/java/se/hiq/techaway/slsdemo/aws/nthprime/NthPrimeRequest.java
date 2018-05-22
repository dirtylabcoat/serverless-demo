package se.hiq.techaway.slsdemo.aws.nthprime;

import java.math.BigInteger;

/**
 *
 * @author Daniel Löfquist, daniel.lofquist@hiq.se
 */
public class NthPrimeRequest {

    private BigInteger n;

    public NthPrimeRequest() {
        // Intentionally left empty
    }

    public NthPrimeRequest(BigInteger n) {
        this.n = n;
    }

    public BigInteger getN() {
        return n;
    }

    public void setN(BigInteger n) {
        this.n = n;
    }

}
