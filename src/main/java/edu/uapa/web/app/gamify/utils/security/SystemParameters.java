package edu.uapa.web.app.gamify.utils.security;

import edu.utesa.lib.models.enums.type.security.EnumParamValueType;

import java.util.Arrays;
import java.util.List;

public class SystemParameters {

    private static final Integer TIME_ZONE = 1;
    private static final Integer INITIAL_DATE = 2;

    public static List<Integer> getValues() {
        return Arrays.asList(TIME_ZONE, INITIAL_DATE);
    }

    public static EnumParamValueType type(Integer code) {
        EnumParamValueType type = null;
        switch (code) {
            case 1:
                type = EnumParamValueType.ENUM_TIME_ZONE;
                break;
            case 2:
                type = EnumParamValueType.DATE;
                break;
        }
        return type;
    }

    public static String name(Integer code) {
        String name = "";
        switch (code) {
            case 1:
                name = "TIME ZONE";
                break;
            case 2:
                name = "INITIAL_DATE";
                break;
        }
        return name;
    }

    public static String description(Integer code) {
        String description = "";
        switch (code) {
            case 1:
                description = "Zona horaria";
                break;
            case 2:
                description = "Fecha en que inicio a usar el sistema";
                break;
        }
        return description;
    }

    public static String value(Integer code) {
        String value = "";
        switch (code) {
            case 1:
                value = "America/Santo_Domingo";
                break;
            case 2:
                value = "2019/05/31";
                break;
        }
        return value;
    }

    public static boolean changeRoot(Integer code) {
        boolean value = false;
        switch (code) {
            case 1:
                value = true;
                break;
            case 2:
                value = false;
                break;
        }
        return value;
    }
}
