package com.HomeSure.support;

import com.HomeSure.pages.RestServices;

public class WebModel {

    public RestServices restServices;
    public ElementUtils utils;


    public WebModel(){
        restServices=new RestServices();
        utils=new ElementUtils();
    }

    public RestServices getRestServices() {
        return restServices;
    }

    public ElementUtils getUtils() {
        return utils;
    }
}
