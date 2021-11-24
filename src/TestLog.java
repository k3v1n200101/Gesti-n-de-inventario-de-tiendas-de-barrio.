
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class TestLog {
    static Logger logger = Logger.getLogger(TestLog.class);

    public static void main(String[] args) {
        logger.debug("debug:\n");
        logger.info("info:\n");
        logger.error("error:\n");
    }
}