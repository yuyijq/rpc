package com.yuyijq.rpc.handler;

import com.yuyijq.rpc.model.TransferData;
import com.yuyijq.rpc.model.TransportData;

/**
 * User: zhaohuiyu
 * Date: 11/12/11
 * Time: 11:03 PM
 */
public abstract class TransportHandler extends Handler {
    @Override
    protected TransferData doSend(TransferData data) {
        TransportData realData = (TransportData) data;
        write(realData.getData());
        return null;
    }

    protected abstract void write(byte[] data);


    @Override
    protected Object doReceive(Object result) {
        return read();
    }

    protected abstract byte[] read();
}
