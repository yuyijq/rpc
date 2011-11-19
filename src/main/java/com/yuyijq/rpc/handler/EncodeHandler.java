package com.yuyijq.rpc.handler;

import com.yuyijq.rpc.model.TransferData;

/**
 * User: zhaohuiyu
 * Date: 11/12/11
 * Time: 10:50 PM
 */
public abstract class EncodeHandler extends Handler {
    @Override
    protected TransferData doSend(TransferData data) {
        return encode(data);
    }

    @Override
    protected Object doReceive(Object result) {
        return decode(result);
    }

    protected abstract TransferData encode(TransferData data);

    protected abstract Object decode(Object data);

}
