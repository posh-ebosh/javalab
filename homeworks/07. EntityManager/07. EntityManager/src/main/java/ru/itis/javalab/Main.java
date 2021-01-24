package ru.itis.javalab;

import java.lang.reflect.Field;

public class Main {
    public static void main(String[] args) throws IllegalAccessException {
        User entity = new User(1L, "asdf", null, true);
        Class<?> classOfEntity = entity.getClass();
        Field[] fields = classOfEntity.getDeclaredFields();

        StringBuilder stringBuilder = new StringBuilder("insert into ")
                .append("user")
                .append(" (");

        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            field.setAccessible(true);

            Object value = field.get(entity);

            if (value != null) {
                stringBuilder.append(field.getName());
            }
            if (i < fields.length - 1 && value != null) {
                stringBuilder.append(", ");
            }
        }

        stringBuilder.append(") values (");

        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            field.setAccessible(true);

            Object value = field.get(entity);

            if (value != null) {
                stringBuilder.append(value.toString());
            }
            if (i < fields.length - 1 && value != null) {
                stringBuilder.append(", ");
            }
        }

        stringBuilder.append(");");

        System.out.println(stringBuilder.toString());
    }
}
