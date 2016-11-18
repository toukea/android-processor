package com.istat.freedev.processor.tools;

import com.istat.freedev.processor.Process;
import com.istat.freedev.processor.interfaces.ProcessExecutionListener;

/**
 * Created by istat on 18/11/16.
 */

public class Toolkit {
    public static <T, Y extends Process.ProcessError> void attachAsProcessWhen(final Process<T, Y> process, final ProcessExecutionListener<T, Y> listener) {
        Runnable complete = new Runnable() {
            @Override
            public void run() {
                listener.onCompleted(process, process.isSuccess());
            }
        };
        Runnable success = new Runnable() {
            @Override
            public void run() {
                listener.onSuccess(process, process.getResult());
            }
        };
        Runnable error = new Runnable() {
            @Override
            public void run() {
                listener.onError(process, process.getError());
            }
        };
        Runnable fail = new Runnable() {
            @Override
            public void run() {
                listener.onFail(process, process.getFailCause());
            }
        };
        Runnable aborted = new Runnable() {
            @Override
            public void run() {
                listener.onAborted(process);
            }
        };
        process.runWhen(complete, Process.WHEN_ANYWAY);
        process.runWhen(success, Process.WHEN_SUCCESS);
        process.runWhen(error, Process.WHEN_ERROR);
        process.runWhen(fail, Process.WHEN_FAIL);
        process.runWhen(aborted, Process.WHEN_ABORTED);
    }
}
