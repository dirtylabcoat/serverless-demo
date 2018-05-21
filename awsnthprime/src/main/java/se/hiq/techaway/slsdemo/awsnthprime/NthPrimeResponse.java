package se.hiq.techaway.slsdemo.awsnthprime;

import java.math.BigInteger;
import java.util.Objects;

/**
 *
 * @author Daniel Löfquist, daniel.lofquist@hiq.se
 */
public class NthPrimeResponse {

    private BigInteger n;
    private BigInteger nth;

    public NthPrimeResponse() {
        // Intentionally left empty
    }

    public NthPrimeResponse(BigInteger n, BigInteger nth) {
        this.n = n;
        this.nth = nth;
    }

    public BigInteger getN() {
        return n;
    }

    public void setN(BigInteger n) {
        this.n = n;
    }

    public BigInteger getNth() {
        return nth;
    }

    public void setNth(BigInteger nth) {
        this.nth = nth;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.n);
        hash = 41 * hash + Objects.hashCode(this.nth);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final NthPrimeResponse other = (NthPrimeResponse) obj;
        return Objects.equals(this.n, other.n) && Objects.equals(this.nth, other.nth);
    }

}
