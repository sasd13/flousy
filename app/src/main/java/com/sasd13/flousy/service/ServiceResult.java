package com.sasd13.flousy.service;

import java.util.Collections;
import java.util.Map;

/**
 * Created by ssaidali2 on 02/04/2017.
 */

public class ServiceResult<T> {

    public static final ServiceResult NULL = new ServiceResult<>(false);
    public static final ServiceResult<Void> SUCCESS = new ServiceResult<>(true);

    private boolean success;
    private Map<String, String> errors;
    private T data;

    public ServiceResult(boolean success) {
        this.success = success;
        errors = Collections.emptyMap();
    }

    public ServiceResult(boolean success, Map<String, String> errors, T data) {
        this.success = success;
        this.errors = errors;
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    public T getData() {
        return data;
    }
}
