package com.leo618.apf.base;

/**
 * function : 异常基类.
 * <p></p>
 * Created by lzj on 2015/12/31.
 */
@SuppressWarnings("ALL")
public abstract class BaseException extends Exception {

    protected final String TAG = this.getClass().getSimpleName();

    protected int type = -1;

    /**
     * Constructs a new {@code BaseException} that includes the current stack trace.
     */
    public BaseException(int type) {
        this.type = type;
    }

    /**
     * Constructs a new {@code BaseException} with the current stack trace and the specified detail message.
     *
     * @param detailMessage the detail message for this exception.
     */
    public BaseException(int type, String detailMessage) {
        super(detailMessage);
        this.type = type;
    }

    /**
     * Constructs a new {@code BaseException} with the current stack trace, the specified detail message and the
     * specified cause.
     *
     * @param detailMessage the detail message for this exception.
     * @param throwable     the cause of this exception.
     */
    public BaseException(int type, String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
        this.type = type;
    }

    /**
     * Constructs a new {@code BaseException} with the current stack trace and the specified cause.
     *
     * @param throwable the cause of this exception.
     */
    public BaseException(int type, Throwable throwable) {
        super(throwable);
        this.type = type;
    }
}
