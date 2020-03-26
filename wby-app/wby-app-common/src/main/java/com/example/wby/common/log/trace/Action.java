package com.example.wby.common.log.trace;

/**
 * @author wubinyu
 */
public enum Action {

    TRACE_START("trace start"),
    TRACE_END("trace end"),
    CLIENT_SEND("client send"),
    CLIENT_RECIEVE("client receive"),
    SERVER_RECIEVE("server receive"),
    SERVER_SEND("server send"),
    INVOKE("invoke"),
    RETURN("return");

    private String action;

    private Action(String action) {
        this.action = action;
    }

    public String getAction() {
        return this.action;
    }

}
