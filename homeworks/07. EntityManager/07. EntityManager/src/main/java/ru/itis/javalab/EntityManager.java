package ru.itis.javalab;

import javax.sql.DataSource;
import java.lang.reflect.Field;


public class EntityManager {
    private DataSource dataSource;

    public EntityManager(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    // createTable("account", User.class);
    public <T> void createTable(String tableName, Class<T> entityClass) {
        // сгенерировать CREATE TABLE на основе класса
        StringBuilder stringBuilder = new StringBuilder().append("create table ")
                .append(tableName)
                .append(" (");
        Field[] fields = entityClass.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];

            stringBuilder.append(field.getName())
                    .append(" ");
            if (Long.class.equals(field.getType())) {
                stringBuilder.append("bigint");
            } else if (String.class.equals(field.getType())) {
                stringBuilder.append("varchar");
            } else if (Integer.class.equals(field.getType())) {
                stringBuilder.append("int");
            } else if (Boolean.class.equals(field.getType())) {
                stringBuilder.append("boolean");
            } else {
                throw new IllegalArgumentException("incompatible type");
            }

            if (i < fields.length - 1) {
                stringBuilder.append(", ");
            }
        }
        stringBuilder.append(");");
        String sqlCreateTable = stringBuilder.toString();
    }

    public void save(String tableName, Object entity) throws IllegalAccessException {
        Class<?> classOfEntity = entity.getClass();
        Field[] fields = classOfEntity.getDeclaredFields();

        StringBuilder stringBuilder = new StringBuilder("insert into ")
                .append(tableName)
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
        String sqlInsert = stringBuilder.toString();
    }

    // User user = entityManager.findById("account", User.class, Long.class, 10L);
    public <T, ID> T findById(String tableName, Class<T> resultType, Class<ID> idType, ID idValue) {
        // сгенеририровать select
        return null;
    }
}

