package edu.uapa.web.app.gamify.utils.security;

import edu.uapa.web.app.gamify.models.enums.EnumPermission;

public class SystemPermission {
    public static String name(EnumPermission enumPermission) {
        String name = "";
        switch (enumPermission) {
            case ROOT:
                name = "ROOT";
                break;
            case SECURITY_MENU:
                name = "SECURITY_MENU";
                break;
            case COMPANY_MENU:
                name = "COMPANY_MENU";
                break;
            case PARAM:
                name = "PARAM";
                break;
            case EDIT_PARAM:
                name = "EDIT_PARAM";
                break;
            case VIEW_PARAM:
                name = "VIEW_PARAM";
                break;
            case PERMISSION:
                name = "PERMISSION";
                break;
            case VIEW_PERMISSION:
                name = "VIEW_PERMISSION";
                break;
            case P_GROUP:
                name = "P_GROUP";
                break;
            case NEW_P_GROUP:
                name = "NEW_P_GROUP";
                break;
            case EDIT_P_GROUP:
                name = "EDIT_P_GROUP";
                break;
            case VIEW_P_GROUP:
                name = "VIEW_P_GROUP";
                break;
            case DELETE_P_GROUP:
                name = "DELETE_P_GROUP";
                break;
            case S_DEL_P_GROUP:
                name = "S_DEL_P_GROUP";
                break;
            case RESTORE_P_GROUP:
                name = "RESTORE_P_GROUP";
                break;
            case USER:
                name = "USER";
                break;
            case NEW_USER:
                name = "NEW_USER";
                break;
            case EDIT_USER:
                name = "EDIT_USER";
                break;
            case VIEW_USER:
                name = "VIEW_USER";
                break;
            case DELETE_USER:
                name = "DELETE_USER";
                break;
            case S_DEL_USER:
                name = "S_DEL_USER";
                break;
            case RESTORE_USER:
                name = "RESTORE_USER";
                break;
            case COMPANY:
                name = "COMPANY";
                break;
            case NEW_COMPANY:
                name = "NEW_COMPANY";
                break;
            case EDIT_COMPANY:
                name = "EDIT_COMPANY";
                break;
            case VIEW_COMPANY:
                name = "VIEW_COMPANY";
                break;
            case DELETE_COMPANY:
                name = "DELETE_COMPANY";
                break;
            case S_DEL_COMPANY:
                name = "S_DEL_COMPANY";
                break;
            case RESTORE_COMPANY:
                name = "RESTORE_COMPANY";
                break;
            case BRANCH:
                name = "BRANCH";
                break;
            case NEW_BRANCH:
                name = "NEW_BRANCH";
                break;
            case EDIT_BRANCH:
                name = "EDIT_BRANCH";
                break;
            case VIEW_BRANCH:
                name = "VIEW_BRANCH";
                break;
            case DELETE_BRANCH:
                name = "DELETE_BRANCH";
                break;
            case S_DEL_BRANCH:
                name = "S_DEL_BRANCH";
                break;
            case RESTORE_BRANCH:
                name = "RESTORE_BRANCH";
                break;
            case DEPARTMENT:
                name = "DEPARTMENT";
                break;
            case NEW_DEPARTMENT:
                name = "NEW_DEPARTMENT";
                break;
            case EDIT_DEPARTMENT:
                name = "EDIT_DEPARTMENT";
                break;
            case VIEW_DEPARTMENT:
                name = "VIEW_DEPARTMENT";
                break;
            case DELETE_DEPARTMENT:
                name = "DELETE_DEPARTMENT";
                break;
            case S_DEL_DEPARTMENT:
                name = "S_DEL_DEPARTMENT";
                break;
            case RESTORE_DEPARTMENT:
                name = "RESTORE_DEPARTMENT";
                break;
            case POSITION:
                name = "POSITION";
                break;
            case NEW_POSITION:
                name = "NEW_POSITION";
                break;
            case EDIT_POSITION:
                name = "EDIT_POSITION";
                break;
            case VIEW_POSITION:
                name = "VIEW_POSITION";
                break;
            case DELETE_POSITION:
                name = "DELETE_POSITION";
                break;
            case S_DEL_POSITION:
                name = "S_DEL_POSITION";
                break;
            case RESTORE_POSITION:
                name = "RESTORE_POSITION";
                break;
            case EMPLOYEE:
                name = "EMPLOYEE";
                break;
            case NEW_EMPLOYEE:
                name = "NEW_EMPLOYEE";
                break;
            case EDIT_EMPLOYEE:
                name = "EDIT_EMPLOYEE";
                break;
            case VIEW_EMPLOYEE:
                name = "VIEW_EMPLOYEE";
                break;
            case DELETE_EMPLOYEE:
                name = "DELETE_EMPLOYEE";
                break;
            case S_DEL_EMPLOYEE:
                name = "S_DEL_EMPLOYEE";
                break;
            case RESTORE_EMPLOYEE:
                name = "RESTORE_EMPLOYEE";
                break;
        }
        return name;
    }

    public static String description(EnumPermission enumPermission) {
        String description = "";
        switch (enumPermission) {
            case ROOT:
            case SECURITY_MENU:
            case COMPANY_MENU:
            case PARAM:
            case EDIT_PARAM:
            case VIEW_PARAM:
            case PERMISSION:
            case VIEW_PERMISSION:
            case P_GROUP:
            case NEW_P_GROUP:
            case EDIT_P_GROUP:
            case VIEW_P_GROUP:
            case DELETE_P_GROUP:
            case S_DEL_P_GROUP:
            case RESTORE_P_GROUP:
            case USER:
            case NEW_USER:
            case EDIT_USER:
            case VIEW_USER:
            case DELETE_USER:
            case S_DEL_USER:
            case RESTORE_USER:
            case COMPANY:
            case NEW_COMPANY:
            case EDIT_COMPANY:
            case VIEW_COMPANY:
            case DELETE_COMPANY:
            case S_DEL_COMPANY:
            case RESTORE_COMPANY:
            case BRANCH:
            case NEW_BRANCH:
            case EDIT_BRANCH:
            case VIEW_BRANCH:
            case DELETE_BRANCH:
            case S_DEL_BRANCH:
            case RESTORE_BRANCH:
            case DEPARTMENT:
            case NEW_DEPARTMENT:
            case EDIT_DEPARTMENT:
            case VIEW_DEPARTMENT:
            case DELETE_DEPARTMENT:
            case S_DEL_DEPARTMENT:
            case RESTORE_DEPARTMENT:
            case POSITION:
            case NEW_POSITION:
            case EDIT_POSITION:
            case VIEW_POSITION:
            case DELETE_POSITION:
            case S_DEL_POSITION:
            case RESTORE_POSITION:
            case EMPLOYEE:
            case NEW_EMPLOYEE:
            case EDIT_EMPLOYEE:
            case VIEW_EMPLOYEE:
            case DELETE_EMPLOYEE:
            case S_DEL_EMPLOYEE:
            case RESTORE_EMPLOYEE:
                description = "";
                break;
        }
        return description;
    }
}
