package com.aimprosoft.aimlearning.utils;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class NumberUtils {

    public static Integer getInt(String num) {
        if (!num.equals("")) {
            try {
                return Integer.valueOf(num);
            } catch (Exception e) {
                return null;
            }
        }
        return null;
    }

    public static BigDecimal getBigDecimal(String num) {
        if (num != null) {
            try {
                return new BigDecimal(num);
            } catch (Exception e) {
                return null;
            }
        }
        return null;
    }

}
