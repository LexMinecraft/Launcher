package com.atlauncher.thread;

import com.atlauncher.event.LogEvent;

import java.util.concurrent.BlockingQueue;

public final class LoggingThread extends Thread {
    private final BlockingQueue<LogEvent> queue;

    public LoggingThread(BlockingQueue<LogEvent> queue) {
        this.queue = queue;
        this.setName("ATL-Logging-Thread");
    }

    @Override
    public void run(){
        try{
            while(true){
                LogEvent next = this.queue.take();
                if(next != null){
                    next.post();
                }
            }
        } catch(Exception ex){
            ex.printStackTrace(System.err);
        }
    }
}