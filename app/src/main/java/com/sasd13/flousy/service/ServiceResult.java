package com.sasd13.flousy.service;

/**
 * Created by ssaidali2 on 02/04/2017.
 */

public class ServiceResult<T> {

    public static final ServiceResult NULL = new ServiceResult<>(false);
    public static final ServiceResult<Void> SUCCESS = new ServiceResult<>(true);

    private boolean success;
    private T data;

    public ServiceResult(boolean success) {
        this.success = success;
    }

    public ServiceResult(boolean success, T data) {
        this.success = success;
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public T getData() {
        return data;
    }
}
