package com.example.wby.common.log.trace;

import java.util.Stack;

/**
 * @author wubinyu
 */
public class TraceContext {
    private String traceId;
    private String spanId;
    private Stack<String> parentId = new Stack<>();
    private String spanName;
    private String clientStartId;
    private String clientSendId;

    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    public String getSpanId() {
        return spanId;
    }

    public void setSpanId(String spanId) {
        this.spanId = spanId;
    }

    public Stack<String> getParentId() {
        return parentId;
    }

    public void setParentId(Stack<String> parentId) {
        this.parentId = parentId;
    }

    public String getSpanName() {
        return spanName;
    }

    public void setSpanName(String spanName) {
        this.spanName = spanName;
    }

    public String getClientStartId() {
        return clientStartId;
    }

    public void setClientStartId(String clientStartId) {
        this.clientStartId = clientStartId;
    }

    public String getClientSendId() {
        return clientSendId;
    }

    public void setClientSendId(String clientSendId) {
        this.clientSendId = clientSendId;
    }

    @Override
    public String toString() {
        return "TraceContext [traceId=" + this.traceId + ", spanId=" + this.spanId + ", parentId=" + this.parentId + ", spanName=" + this.spanName + "]";
    }
}
