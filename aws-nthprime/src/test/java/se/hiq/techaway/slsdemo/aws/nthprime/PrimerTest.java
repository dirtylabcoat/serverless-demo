/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.hiq.techaway.slsdemo.aws.nthprime;

import se.hiq.techaway.slsdemo.aws.nthprime.Primer;
import se.hiq.techaway.slsdemo.aws.nthprime.NthPrimeResponse;
import se.hiq.techaway.slsdemo.aws.nthprime.NthPrimeRequest;
import com.amazonaws.services.lambda.runtime.ClientContext;
import com.amazonaws.services.lambda.runtime.CognitoIdentity;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import java.math.BigInteger;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Daniel Löfquist, daniel.lofquist@hiq.se
 */
public class PrimerTest {

    private Primer subject;
    private Context testContext;

    @Test
    public void testFirstPrimeIs2() {
        BigInteger n = BigInteger.valueOf(1);
        BigInteger nth = BigInteger.valueOf(2);
        assertEquals(subject.handleRequest(new NthPrimeRequest(n), testContext), new NthPrimeResponse(n, nth));
    }

    @Test
    public void testTenthPrimeIs29() {
        BigInteger n = BigInteger.valueOf(10);
        BigInteger nth = BigInteger.valueOf(29);
        assertEquals(subject.handleRequest(new NthPrimeRequest(n), testContext), new NthPrimeResponse(n, nth));
    }

    @Test
    public void testHundredthPrimeIs541() {
        BigInteger n = BigInteger.valueOf(100);
        BigInteger nth = BigInteger.valueOf(541);
        assertEquals(subject.handleRequest(new NthPrimeRequest(n), testContext), new NthPrimeResponse(n, nth));
    }

    @Before
    public void setUp() throws Exception {
        subject = new Primer();
        testContext = new Context() {
            @Override
            public String getFunctionName() {
                return "ExampleAwsLambda";
            }

            @Override
            public String getAwsRequestId() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public String getLogGroupName() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public String getLogStreamName() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public String getFunctionVersion() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public String getInvokedFunctionArn() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public CognitoIdentity getIdentity() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public ClientContext getClientContext() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public int getRemainingTimeInMillis() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public int getMemoryLimitInMB() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public LambdaLogger getLogger() {
                return (String s) -> {
                    System.out.println(s);
                };
            }
        };
    }

}
