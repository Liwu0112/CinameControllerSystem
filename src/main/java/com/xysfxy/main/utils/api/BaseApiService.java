package com.xysfxy.main.utils.api;



import com.xysfxy.main.utils.api.constants.ApiConstants;
import lombok.Data;

/**
 * @version V1.0
 * @description: 微服务接口实现该接口可以使用传递参数可以直接封装统一返回结果集
 * @author: 97年程序员 余胜军
 * @contact: QQ644064779、微信yushengjun644 www.mayikt.com
 * @Copyright 该项目“基于SpringCloud2.x构建微服务电商项目”由每特教育|蚂蚁课堂版权所有，未经过允许的情况下，
 * 私自分享视频和源码属于违法行为。
 */
@Data
public class BaseApiService<T> {
    public BaseResponse<T> setResultDb(int result) {
        return result > 0 ? setResultSuccess() : setResultError();
    }

    public BaseResponse<T> setResultError(Integer code, String msg) {
        return setResult(code, msg, null);
    }

    /**
     * 返回错误，可以传msg
     *
     * @param msg
     * @return
     */
    public BaseResponse<T> setResultError(String msg) {
        return setResult(ApiConstants.HTTP_RES_CODE_500.getCode(), msg, null);
    }

    /**
     * 返回错误，可以传msg
     *
     * @return
     */
    public BaseResponse<T> setResultError() {
        return setResult(ApiConstants.HTTP_RES_CODE_500.getCode(),
                ApiConstants.HTTP_RES_CODE_500.getValue(), null);
    }

    /***
     * 返回成功，可以传data值
     * @param data
     * @return
     */
    public BaseResponse<T> setResultSuccessData(T data) {
        return setResult(ApiConstants.HTTP_RES_CODE_200.getCode(), ApiConstants.HTTP_RES_CODE_200.getValue(), data);
    }

    /**
     * 返回成功，沒有data值
     *
     * @return
     */
    public BaseResponse<T> setResultSuccess() {
        return setResult(ApiConstants.HTTP_RES_CODE_200.getCode(), ApiConstants.HTTP_RES_CODE_200.getValue(), null);
    }


    /**
     * 通用封装 通用封装
     *
     * @param code
     * @param msg
     * @param data
     * @return
     */
    public BaseResponse<T> setResult(Integer code, String msg, T data) {
        return new BaseResponse<T>(code, msg, data);
    }


}