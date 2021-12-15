package io.codebyexample.servicea.util.common;

import ch.qos.logback.classic.LoggerContext;
import javax.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * This component is a workaround to not lose logging events when using immediateFlush = false and
 * AsyncAppender
 *
 * <p>Another way to gracefully shut down logger context is to add this config to logback.xml file
 *
 * <pre>
 *   <shutdownHook class="ch.qos.logback.core.hook.DelayingShutdownHook">
 *     <delay>1000</delay>
 *   </shutdownHook>
 * </pre>
 *
 * <p>Set delay = 1000 is important, by default it's 0 so the application will immediately stop,
 * making queued logging events dropped
 *
 * @author sateam
 */
@Slf4j
@Component
public class LoggerShutdownHook {

  @PreDestroy
  private void preDestroy() {
    log.info("Shutting down logger context...");

    LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
    loggerContext.stop();
  }
}
