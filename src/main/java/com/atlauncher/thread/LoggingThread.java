/*
 * ATLauncher - https://github.com/ATLauncher/ATLauncher
 * Copyright (C) 2013 ATLauncher
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package com.atlauncher.thread;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.BlockingQueue;

import com.atlauncher.App;
import com.atlauncher.evnt.LogEvent;
import com.atlauncher.utils.Timestamper;
import com.atlauncher.writer.LogEventWriter;

public final class LoggingThread extends Thread {
    private final LogEventWriter writer;
    private final BlockingQueue<LogEvent> queue;

    public LoggingThread(BlockingQueue<LogEvent> queue) {
        this.queue = queue;
        this.setName("Lex-Logging-Thread");
        String date = new SimpleDateFormat("yyyy-MM-dd_hh-mm-ss").format(new Date());
        File log = new File(App.settings.getLogsDir(), "lexlauncher_" + date + ".log");
        if(!log.exists()) {
        	try {
				log.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
        try {
            this.writer = new LogEventWriter(new FileWriter(log));
            this.writer.write("Generated on " + Timestamper.now() + "\n");
            Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        writer.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }));
        } catch (IOException e) {
            throw new RuntimeException("Couldn't create LogEventWriter");
        }
    }

    @Override
    public void run() {
        try {
            while (true) {
                LogEvent next = this.queue.take();
                if (next != null) {
                    next.post(this.writer);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
        }
    }

}