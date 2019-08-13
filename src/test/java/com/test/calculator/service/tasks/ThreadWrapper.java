package com.test.calculator.service.tasks;

public class ThreadWrapper {

    public void executeTask(Runnable task) {

        Thread thread = new Thread(task);
        thread.start();
    }
}
