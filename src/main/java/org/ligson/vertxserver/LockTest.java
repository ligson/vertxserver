package org.ligson.vertxserver;

public class LockTest {
    public static void main(String[] args) throws Exception {
        LockTest lockTest = new LockTest();
        synchronized (lockTest) {
            lockTest.wait(5000L);
            System.out.println(lockTest);
        }
        System.out.println(lockTest);

    }
}
