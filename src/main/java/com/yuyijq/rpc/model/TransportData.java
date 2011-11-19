package com.yuyijq.rpc.model;

/**
 * User: zhaohuiyu
 * Date: 11/12/11
 * Time: 11:16 PM
 */
public class TransportData extends TransferData {
    private byte[] data;

    public TransportData(byte[] data) {
        this.data = data;
    }

    public byte[] getData() {
        return this.data;
    }
}
