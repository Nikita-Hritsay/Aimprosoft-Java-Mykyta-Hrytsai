package com.aimprosoft.aimlearning.utils;

import java.math.BigDecimal;

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
