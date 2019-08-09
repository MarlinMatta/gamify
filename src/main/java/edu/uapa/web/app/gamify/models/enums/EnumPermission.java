package edu.uapa.web.app.gamify.models.enums;

public enum EnumPermission {
    ROOT(0),
    GAME(1),
    ADMIN(2),
    SECURITY_MENU(101),
    COMPANY_MENU(102),

    PARAM(1000),
    EDIT_PARAM(1001),
    VIEW_PARAM(1002),

    PERMISSION(1003),
    VIEW_PERMISSION(1004),

    P_GROUP(1005),
    NEW_P_GROUP(1006),
    EDIT_P_GROUP(1007),
    VIEW_P_GROUP(1008),
    DELETE_P_GROUP(1009),
    S_DEL_P_GROUP(1010),
    RESTORE_P_GROUP(1011),

    USER(1012),
    NEW_USER(1013),
    EDIT_USER(1014),
    VIEW_USER(1015),
    DELETE_USER(1016),
    S_DEL_USER(1017),
    RESTORE_USER(1018),

    COMPANY(1019),
    NEW_COMPANY(1020),
    EDIT_COMPANY(1021),
    VIEW_COMPANY(1022),
    DELETE_COMPANY(1023),
    S_DEL_COMPANY(1024),
    RESTORE_COMPANY(1025),

    BRANCH(1026),
    NEW_BRANCH(1027),
    EDIT_BRANCH(1028),
    VIEW_BRANCH(1029),
    DELETE_BRANCH(1030),
    S_DEL_BRANCH(1031),
    RESTORE_BRANCH(1032),

    DEPARTMENT(1033),
    NEW_DEPARTMENT(1034),
    EDIT_DEPARTMENT(1035),
    VIEW_DEPARTMENT(1036),
    DELETE_DEPARTMENT(1037),
    S_DEL_DEPARTMENT(1038),
    RESTORE_DEPARTMENT(1039),

    POSITION(1040),
    NEW_POSITION(1041),
    EDIT_POSITION(1042),
    VIEW_POSITION(1043),
    DELETE_POSITION(1044),
    S_DEL_POSITION(1045),
    RESTORE_POSITION(1046),

    EMPLOYEE(1047),
    NEW_EMPLOYEE(1048),
    EDIT_EMPLOYEE(1049),
    VIEW_EMPLOYEE(1050),
    DELETE_EMPLOYEE(1051),
    S_DEL_EMPLOYEE(1052),
    RESTORE_EMPLOYEE(1053);

    public int code;

    EnumPermission(int code) {
        this.code = code;
    }
}
