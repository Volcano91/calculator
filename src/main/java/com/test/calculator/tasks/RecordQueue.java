package com.test.calculator.tasks;

import com.test.calculator.model.Record;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.concurrent.LinkedBlockingQueue;

@Component
public class RecordQueue {

    private static final int THREAD_COUNT = 150;

    private static final LinkedBlockingQueue<Record> RECORDS_QUEUE = new LinkedBlockingQueue<>(THREAD_COUNT);

    public void put(Record record) throws InterruptedException {
       RECORDS_QUEUE.put(record);
    }

    public Record take() throws InterruptedException {
        return RECORDS_QUEUE.take();
    }
}
