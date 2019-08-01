package ro.msg.edu.jbugs.interceptors;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * Document me.
 *
 * @author msg systems AG; User Name.
 * @since 19.1.2
 */
public class LoggingInterceptor {

    private Logger logger = Logger.getLogger(LoggingInterceptor.class.getName());

    public LoggingInterceptor() {
        FileHandler fh;
        try{
            fh = new FileHandler("C:/Training/20_TOOLS/servers/payara41/glassfish/domains/domain1/logs/mylogger.log");
            logger.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @AroundInvoke
    private Object doLog(InvocationContext ic){
        Object object = null;
        try {
            logger.entering(ic.getTarget().toString(), ic.getMethod().getName());

            Long timeBefore = System.currentTimeMillis();
            object = ic.proceed();
            Long timeAfter = System.currentTimeMillis();

            logger.info("duration of method " + ic.getMethod().getName() + ": " + (timeAfter-timeBefore) + "millis");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            logger.exiting(ic.getTarget().toString(), ic.getMethod().getName());
        }
        return object;
    }
}
