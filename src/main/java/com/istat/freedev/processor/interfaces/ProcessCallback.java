package com.istat.freedev.processor.interfaces;

import com.istat.freedev.processor.Process;

/**
 * Created by istat on 14/10/16.
 */

public interface ProcessCallback<Result, Error extends Throwable> {
    /**
     * called when the process started.
     *
     * @param process
     */
    void onStart(Process<Result, Error> process);

    /**
     * called when process is completed
     *
     * @param process
     * @param success state if process succeed.
     */
    void onCompleted(Process<Result, Error> process, Result result, boolean success);

    /**
     * called when the process succeed
     *
     * @param process
     * @param result  the process Result.
     */
    void onSuccess(Process<Result, Error> process, Result result);

    /**
     * The process is started but some error happen durring.
     *
     * @param process
     * @param error   the error rencontered by the process
     */
    void onError(Process<Result, Error> process, Error error);

    /**
     * The process is started. but never running.s
     *
     * @param process
     * @param e       the exception that cause the process failed
     */
    void onFail(Process<Result, Error> process, Throwable e);

    /**
     * called when the process has been aborted.
     *
     * @param process
     */
    void onAborted(Process<Result, Error> process);


}