package com.yuyijq.rpc.handler;

import com.yuyijq.rpc.model.TransferData;

/**
 * User: zhaohuiyu
 * Date: 11/10/11
 * Time: 9:59 PM
 */
public abstract class Handler {
    private Handler nextHandler;

    public final Object handle(TransferData data) {
        TransferData preResult = doSend(data);
        return doReceive(nextHandler.handle(preResult));
    }

    public final void setNextHandler(Handler nextHandler) {
        this.nextHandler = nextHandler;
    }

    protected abstract TransferData doSend(TransferData data);

    protected abstract Object doReceive(Object result);

}
