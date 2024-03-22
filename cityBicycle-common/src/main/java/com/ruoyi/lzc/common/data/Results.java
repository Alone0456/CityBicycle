package com.ruoyi.lzc.common.data;

/**
 * @className: Results
 * @author: lizhichao
 * @description: TODO
 * @date: 2024/3/13 20:57
 * @version: 1.0
 */
public class Results {

    /**
     * 构造成功响应
     */
    public static Result<Void> success() {
        return new Result<Void>()
                .setCode(Result.SUCCESS_CODE);
    }

    /**
     * 构造带返回数据的成功响应
     */
    public static <T> Result<T> success(T data) {
        return new Result<T>()
                .setCode(Result.SUCCESS_CODE)
                .setData(data);
    }

    /**
     * 构建服务端失败响应
     */
    public  static Result<Void> failure(Integer code,String message) {
        return new Result<Void>()
                .setCode(code.toString())
                .setMessage(message);
    }

}
