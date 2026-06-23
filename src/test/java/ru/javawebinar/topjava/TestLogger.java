package ru.javawebinar.topjava;

import static org.slf4j.LoggerFactory.getLogger;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;
import org.slf4j.Logger;

public class TestLogger extends TestWatcher {

    private static final Map<String, Long> TEST_RUN_TIMES = new ConcurrentHashMap<>();

    private static final Logger logger = getLogger(TestLogger.class);
    private long testStartTime;

    public TestLogger() {
        super();
    }

    public static <T> void printTestRuntimesForClass(Class<T> clazz) {
        logger.info("=============================================================");
        logger.info("TEST RUNTIMES for {}", clazz.getSimpleName());
        logger.info("=============================================================");
        TEST_RUN_TIMES.keySet().stream()
                .filter((key) -> key.startsWith(clazz.getName() + "#"))
                .forEach((key) ->
                        logger.info("{}: {}ms", key.split("#")[1], TEST_RUN_TIMES.get(key)));
        logger.info("=============================================================");
    }

    @Override
    public Statement apply(Statement base, Description description) {
        return super.apply(base, description);
    }

    @Override
    protected void starting(Description description) {
        testStartTime = System.currentTimeMillis();
        super.starting(description);
    }

    @Override
    protected void finished(Description description) {
        long testRunTime = System.currentTimeMillis() - testStartTime;
        logger.info("Test {} took {}ms", description.getMethodName(), testRunTime);
        TEST_RUN_TIMES.put(description.getClassName() + "#" + description.getMethodName(), testRunTime);
        super.finished(description);
    }
}
