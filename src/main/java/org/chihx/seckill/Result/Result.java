package org.chihx.seckill.Result;

public class Result<T> {
    private int code;
    private String msg;
    private T data;

    private Result (T data) {
        this.code = 0;
        this.data = data;
        this.msg = "success";
    }

    private Result (CodeMsg cm) {
        if (cm == null) {
            return;
        }
        this.code = cm.getCode();
        this.msg = cm.getMsg();
    }

    public static <T> Result<T> Success(T data) {
        return new Result<T>(data);
    }

    public static <T> Result<T> Error(CodeMsg cm) {
        return new Result<T>(cm);
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public T getData() {
        return data;
    }

}
