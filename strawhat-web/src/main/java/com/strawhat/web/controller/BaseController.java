package com.strawhat.web.controller;

import com.strawhat.controller.Controller;
import com.strawhat.model.Tidings;

public interface BaseController extends Controller {

    /**
     * 成功音讯
     * @param <T>
     * @return
     */
    <T> Tidings<T> success();

    <T> Tidings<T> success(T data);

    /**
     * 失败音讯
     * @param <T>
     * @return
     */
    <T> Tidings<T> fail();

    <T> Tidings<T> fail(String message,String error);


}
