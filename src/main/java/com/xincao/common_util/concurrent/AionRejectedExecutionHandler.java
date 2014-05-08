package com.xincao.common_util.concurrent;

import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *  A handler for tasks that cannot be executed by a {@link ThreadPoolExecutor}
 * .
 * @author
 */
public final class AionRejectedExecutionHandler implements RejectedExecutionHandler {

    private static final Logger log = LoggerFactory.getLogger(AionRejectedExecutionHandler.class);

    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        if (executor != null && !executor.isShutdown()) {
            executor.execute(r);
            return;
        }
        log.warn(r + " from " + executor, new RejectedExecutionException());
        if (Thread.currentThread().getPriority() > Thread.NORM_PRIORITY) {
            new Thread(r).start(); // start()可以协调系统的资源
        } else {
            r.run();
        }
    }
}