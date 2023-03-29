package com.fbc.bot.util;

import lombok.experimental.UtilityClass;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

@UtilityClass
public class HttpUtils {

    public static boolean isValidUrl(String url) {
        try {
            new URL(url).toURI();
            return true;
        } catch (MalformedURLException | URISyntaxException e) {
            return false;
        }
    }
}