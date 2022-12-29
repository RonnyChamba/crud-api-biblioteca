package com.ideas.org.crud.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UtilFunction {

    public static Date convertStringToDate(String dateString) throws ParseException {

        if (dateString != null) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            return format.parse(dateString);
        }
        return null;


    }
}
