package com.aimprosoft.aimlearning.utils;

import net.sf.oval.ConstraintViolation;
import net.sf.oval.context.OValContext;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetError {

    public static Map<String, String> getErrors(List<ConstraintViolation> violations){

        Map<String, String> errors = new HashMap<>();
        for(ConstraintViolation obj: violations){
            String fieldName = null;
            for (OValContext node : obj.getContextPath()) {
                fieldName = node.toStringUnqualified();
            }
            errors.put(fieldName, obj.getMessage());
        }
        return errors;
    }

}
