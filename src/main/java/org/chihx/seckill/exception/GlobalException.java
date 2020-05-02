package org.chihx.seckill.exception;

import org.chihx.seckill.Result.CodeMsg;

/**
 * 全局异常
 */
public class GlobalException extends RuntimeException {
    private CodeMsg cm;

    public GlobalException(CodeMsg cm) {
        super(cm.toString());
        this.cm = cm;
    }

    public CodeMsg getCm() {
        return cm;
    }
}
