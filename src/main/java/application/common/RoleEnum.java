package application.common;

public enum RoleEnum {
    ROLE_SUPER_ADMIN("super_admin"),
    ROLE_ADMIN("admin"),
    ROLE_USER("user");
    final String name;
    RoleEnum(String name){
        this.name = name;
    }
}
