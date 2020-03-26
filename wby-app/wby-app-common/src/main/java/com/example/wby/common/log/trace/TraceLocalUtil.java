package com.example.wby.common.log.trace;

/**
 * @author wubinyu
 */
public class TraceLocalUtil {

    private static ThreadLocal<TraceContext> traceContext = new ThreadLocal<>();

    public static TraceContext getTraceContext() {
        return traceContext.get();
    }

    public static void setTraceContext(TraceContext tc) {
        traceContext.set(tc);
    }

    public static void clearTraceContext() {
        traceContext.remove();
    }
}
