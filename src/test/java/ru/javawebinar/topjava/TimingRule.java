package ru.javawebinar.topjava;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class TimingRule implements TestRule {

    private static final Logger logger = LoggerFactory.getLogger(TimingRule.class);
    private static Map<String, Long> testTimes = new HashMap<>();

    @Override
    public Statement apply(Statement base, Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                long startTime = System.currentTimeMillis();
                try {
                    base.evaluate();
                } finally {
                    long endTime = System.currentTimeMillis();
                    long timeTaken = endTime - startTime;
                    testTimes.put(description.getMethodName(), timeTaken);
                    logger.info("Test " + description.getMethodName() + " took " + timeTaken + " ms.");
                }
            }
        };
    }

    public static void printSummary() {
        logger.info("\nTest Summary:");
        for (Map.Entry<String, Long> entry : testTimes.entrySet()) {
            logger.info("Test " + entry.getKey() + " took " + entry.getValue() + " ms.");
        }
    }
}
