package com.HomeSure.support;

import net.minidev.json.JSONObject;
import org.apache.commons.lang3.RandomStringUtils;
import org.json.simple.parser.JSONParser;


import java.io.FileReader;
import java.io.IOException;

public class ElementUtils {


    public JSONObject getPayload(String fileName) {
        JSONParser jsonParser = new JSONParser();
        Object object = null;
        try {
            try {

                object = jsonParser.parse(new FileReader(System.getProperty("user.dir") + "/src/main/java/com.HomeSure.payLoad/" + fileName + ".json"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (org.json.simple.parser.ParseException e) {
            e.printStackTrace();
        }
        JSONObject json = (JSONObject) object;
        return json;
    }
    public String randomName() {
        return RandomStringUtils.randomAlphabetic(8);
    }
}
