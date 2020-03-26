package com.example.wby.common.log.util;


import com.example.wby.common.log.trace.Action;
import com.example.wby.common.log.trace.TraceContext;
import com.example.wby.common.log.trace.TraceContextFactory;
import com.example.wby.common.log.trace.TraceLocalUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.UUID;

public class TraceRecordGenerator {
    private static Logger log = LoggerFactory.getLogger(TraceRecordGenerator.class);
    private static String ip;
    private static String instanceId;
    private static String nodeId;

    public static void traceInit(String componentId, String segmentId, String segIndex) {
        ip = getIp();
        instanceId = segIndex;
        StringBuilder sb = new StringBuilder(componentId);
        sb.append(".").append(segmentId).append(".").append(instanceId).append("@").append(ip);
        nodeId = sb.toString();
    }

    private static String getIp() {
        String ip;
        try {
            ip = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            log.error(e.getMessage());
            ip = "localhost";
        }
        return ip;
    }

    public static void traceStart() {
        try {
            if (log.isInfoEnabled()) {
                TraceContext tc = TraceContextFactory.createTraceContext();
                String traceId = getUUID();
                tc.setTraceId(traceId);
                String spanId = getUUID();
                tc.setSpanId(spanId);
                tc.setClientStartId(spanId);
                TraceLocalUtil.setTraceContext(tc);
                log.info("node_id:\"{}\",trace_id:\"{}\",span_id:\"{}\",action:\"{}\"", new Object[]{nodeId, tc.getTraceId(), tc.getSpanId(), Action.TRACE_START.getAction()});
            }
        } catch (Exception var3) {
            log.warn("traceStart : new error " + var3.getMessage());
        }
    }

    public static void clientSend(String spanName) {
        try {
            if (log.isInfoEnabled()) {
                TraceContext tc = TraceLocalUtil.getTraceContext();
                String spanId = getUUID();
                tc.setSpanId(spanId);
                tc.setSpanName(spanName);
                log.info("node_id:\"{}\",trace_id:\"{}\",span_id:\"{}\",action:\"{}\",span_name:\"{}\"", new Object[]{nodeId, tc.getTraceId(), tc.getSpanId(), Action.CLIENT_SEND.getAction(), tc.getSpanName()});
            }
        } catch (Exception var3) {
            log.warn("clientSend: new error " + var3.getMessage());
        }
    }

    public static void serverReceive(String traceId, String spanId) {
        try {
            if (log.isInfoEnabled()) {
                TraceContext tc;
                if ((tc = TraceLocalUtil.getTraceContext()) == null) {
                    tc = TraceContextFactory.createTraceContext();
                }
                tc.setTraceId(traceId);
                tc.setSpanId(spanId);
                tc.setClientSendId(spanId);
                TraceLocalUtil.setTraceContext(tc);
                log.info("node_id:\"{}\",trace_id:\"{}\",span_id:\"{}\",action:\"{}\"", new Object[]{nodeId, tc.getTraceId(), tc.getSpanId(), Action.SERVER_RECIEVE.getAction()});
            }
        } catch (Exception var3) {
            log.warn("serverRecieve: new error " + var3.getMessage());
        }
    }

    public static void serverReceive(String traceId, String spanId, String spanName) {
        try {
            if (log.isInfoEnabled()) {
                TraceContext tc;
                if ((tc = TraceLocalUtil.getTraceContext()) == null) {
                    tc = TraceContextFactory.createTraceContext();
                }
                tc.setTraceId(traceId);
                tc.setSpanId(spanId);
                tc.setClientSendId(spanId);
                tc.setSpanName(spanName);
                TraceLocalUtil.setTraceContext(tc);
                log.info("node_id:\"{}\",trace_id:\"{}\",span_id:\"{}\",action:\"{}\",span_name:\"{}\"", new Object[]{nodeId, tc.getTraceId(), tc.getSpanId(), Action.SERVER_RECIEVE.getAction(), tc.getSpanName()});
            }
        } catch (Exception var4) {
            log.warn("serverRecieve: new error " + var4.getMessage());
        }

    }

    public static void invoke(String spanName) {
        try {
            if (log.isInfoEnabled()) {
                TraceContext tc = TraceLocalUtil.getTraceContext();
                String parentId = tc.getSpanId();
                tc.getParentId().push(parentId);
                String spanId = getUUID();
                tc.setSpanId(spanId);
                tc.setSpanName(spanName);
                log.info("node_id:\"{}\",trace_id:\"{}\",span_id:\"{}\",parent_id:\"{}\",action:\"{}\",span_name:\"{}\"", new Object[]{nodeId, tc.getTraceId(), spanId, parentId, Action.INVOKE.getAction(), tc.getSpanName()});
            }
        } catch (Exception var4) {
            log.warn("ReceiveReq : new error " + var4.getMessage());
        }
    }

    public static void returnSucceed() {
        try {
            if (log.isInfoEnabled()) {
                TraceContext tc = TraceLocalUtil.getTraceContext();
                String parentId = "";
                if (!tc.getParentId().isEmpty()) {
                    parentId = tc.getParentId().pop();
                }
                String spanId = tc.getSpanId();
                log.info("node_id:\"{}\",trace_id:\"{}\",span_id:\"{}\",parent_id:\"{}\",action:\"{}\"", new Object[]{nodeId, tc.getTraceId(), spanId, parentId, Action.RETURN.getAction()});
                tc.setSpanId(parentId);
            }
        } catch (Exception var3) {
            log.warn("returnSucceed : new error " + var3.getMessage());
        }
    }

    public static void returnFailed(String notation, String errorCode) {
        try {
            if (log.isInfoEnabled()) {
                TraceContext tc = TraceLocalUtil.getTraceContext();
                String parentId = "";
                if (!tc.getParentId().isEmpty()) {
                    parentId = tc.getParentId().pop();
                }
                String spanId = tc.getSpanId();
                log.info("node_id:\"{}\",trace_id:\"{}\",span_id:\"{}\",parent_id:\"{}\",action:\"{}\",notation:\"{}\",code:\"{}\"", new Object[]{nodeId, tc.getTraceId(), spanId, parentId, Action.RETURN.getAction(), notation, errorCode});
                tc.setSpanId(parentId);
            }
        } catch (Exception var5) {
            log.warn("returnFailed : new error " + var5.getMessage());
        }

    }

    public static void serverSendSucceed() {
        try {
            if (log.isInfoEnabled()) {
                TraceContext tc = TraceLocalUtil.getTraceContext();
                String spanId = tc.getClientSendId();
                tc.setSpanId(spanId);
                log.info("node_id:\"{}\",trace_id:\"{}\",span_id:\"{}\",action:\"{}\"", new Object[]{nodeId, tc.getTraceId(), tc.getSpanId(), Action.SERVER_SEND.getAction()});
            }
        } catch (Exception var2) {
            log.warn("serverSendSucceed: new error " + var2);
        }
    }

    public static void serverSendFailed(String notation, String errorCode) {
        try {
            if (log.isInfoEnabled()) {
                TraceContext tc = TraceLocalUtil.getTraceContext();
                String spanId = tc.getClientSendId();
                tc.setSpanId(spanId);
                log.info("node_id:\"{}\",trace_id:\"{}\",span_id:\"{}\",action:\"{}\",notation:\"{}\",code:\"{}\"", new Object[]{nodeId, tc.getTraceId(), tc.getSpanId(), Action.SERVER_SEND.getAction(), notation, errorCode});
            }
        } catch (Exception var4) {
            log.warn("serverSendFailed : new error " + var4);
        }
    }

    public static void clientReceiveSucceed() {
        try {
            if (log.isInfoEnabled()) {
                TraceContext tc = TraceLocalUtil.getTraceContext();
                log.info("node_id:\"{}\",trace_id:\"{}\",span_id:\"{}\",action:\"{}\"", new Object[]{nodeId, tc.getTraceId(), tc.getSpanId(), Action.CLIENT_RECIEVE.getAction()});
            }
        } catch (Exception var1) {
            log.warn("clientReceiveSucceed: new error " + var1);
        }

    }

    public static void clientReceiveSucceed(String traceId, String spanId) {
        try {
            if (log.isInfoEnabled()) {
                TraceContext tc = TraceLocalUtil.getTraceContext();
                tc.setTraceId(traceId);
                tc.setSpanId(spanId);
                log.info("node_id:\"{}\",trace_id:\"{}\",span_id:\"{}\",action:\"{}\"", new Object[]{nodeId, traceId, spanId, Action.CLIENT_RECIEVE.getAction()});
            }
        } catch (Exception var3) {
            log.warn("clientReceiveSuc: new error " + var3.getMessage());
        }
    }

    public static void clientReceiveSucceed(String traceId, String spanId, String notation) {
        try {
            if (log.isInfoEnabled()) {
                TraceContext tc = TraceLocalUtil.getTraceContext();
                tc.setTraceId(traceId);
                tc.setSpanId(spanId);
                log.info("node_id:\"{}\",trace_id:\"{}\",span_id:\"{}\",action:\"{}\",notation:\"{}\"", new Object[]{nodeId, traceId, spanId, Action.CLIENT_RECIEVE.getAction(), notation});
            }
        } catch (Exception var4) {
            log.warn("clientReceiveSuc: new error " + var4.getMessage());
        }
    }

    public static void clientReceiveSucceed(String notation) {
        try {
            if (log.isInfoEnabled()) {
                TraceContext tc = TraceLocalUtil.getTraceContext();
                log.info("node_id:\"{}\",trace_id:\"{}\",span_id:\"{}\",action:\"{}\",notation:\"{}\"", new Object[]{nodeId, tc.getTraceId(), tc.getSpanId(), Action.CLIENT_RECIEVE.getAction(), notation});
            }
        } catch (Exception var2) {
            log.warn("clientReceiveSuc: new error " + var2.getMessage());
        }

    }

    public static void clientReceiveFailed(String notation, String errorCode) {
        try {
            if (log.isInfoEnabled()) {
                TraceContext tc = TraceLocalUtil.getTraceContext();
                log.info("node_id:\"{}\",trace_id:\"{}\",span_id:\"{}\",action:\"{}\",notation:\"{}\",code:\"{}\"", new Object[]{nodeId, tc.getTraceId(), tc.getSpanId(), Action.CLIENT_RECIEVE.getAction(), notation, errorCode});
            }
        } catch (Exception var3) {
            log.warn("clientReceiveFailed: new error " + var3.getMessage());
        }
    }

    public static void clientReceiveFailed(String traceId, String spanId, String notation, String errorCode) {
        try {
            if (log.isInfoEnabled()) {
                log.info("node_id:\"{}\",trace_id:\"{}\",span_id:\"{}\",action:\"{}\",notation:\"{}\",code:\"{}\"", new Object[]{nodeId, traceId, spanId, Action.CLIENT_RECIEVE.getAction(), notation, errorCode});
            }
        } catch (Exception var5) {
            log.warn("clientReceiveFailed: new error" + var5.getMessage());
        }
    }

    public static void traceEnd() {
        try {
            if (log.isInfoEnabled()) {
                TraceContext tc = TraceLocalUtil.getTraceContext();
                String spanId = tc.getClientStartId();
                tc.setSpanId(spanId);
                log.info("node_id:\"{}\",trace_id:\"{}\",span_id:\"{}\",action:\"{}\"", new Object[]{nodeId, tc.getTraceId(), spanId, Action.TRACE_END.getAction()});
                TraceLocalUtil.clearTraceContext();
            }
        } catch (Exception var2) {
            log.warn("traceEnd: new error " + var2.getMessage());
        }
    }

    public static void traceEnd(String traceId, String spanId) {
        try {
            if (log.isInfoEnabled()) {
                log.info("node_id:\"{}\",trace_id:\"{}\",span_id:\"{}\",action:\"{}\"", new Object[]{nodeId, traceId, spanId, Action.TRACE_END.getAction()});
                TraceLocalUtil.clearTraceContext();
            }
        } catch (Exception var3) {
            log.warn("traceEnd: new error " + var3.getMessage());
        }
    }

    private static String getUUID() {
        String s = UUID.randomUUID().toString();
        return s.replace("-", "");
    }

}
