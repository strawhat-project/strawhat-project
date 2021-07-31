package com.strawhat.web.controller;

import com.strawhat.model.Tidings;

public abstract class AbstractBaseController implements BaseController{

    private static final int SUCCESS_CODE = 0;

    private static final int FAIL_CODE = 1;

    private static final String DEFAULT_SUCCESS_MESSAGE= "successful";

    private static final String DEFAULT_FAIL_MESSAGE= "unsuccessful";


    @Override
    public  <T> Tidings<T> success(){
        return success(null);
    }

    @Override
    public  <T> Tidings<T> success(T data){
        return new Tidings<T>(SUCCESS_CODE, Boolean.TRUE, DEFAULT_SUCCESS_MESSAGE,data);
    }

    @Override
    public <T> Tidings<T> fail(){
        return fail(DEFAULT_FAIL_MESSAGE,null);
    }

    @Override
    public <T> Tidings<T> fail(String message, String error){
        return new Tidings<T>(FAIL_CODE, Boolean.FALSE, message,error,null);
    }

}
