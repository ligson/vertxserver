package org.ligson.vertxserver;

import lombok.Data;

@Data
public class Lock {
    private String data;

    public void lock() {
        synchronized (this) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void unlock() {
        synchronized (this) {
            this.notify();
        }
    }
}
