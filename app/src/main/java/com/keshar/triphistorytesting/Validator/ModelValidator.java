package com.keshar.triphistorytesting.Validator;

import com.keshar.triphistorytesting.Util.IsDefine;

import java.lang.reflect.Field;

public class ModelValidator {

    private Object model;

    public ModelValidator(Object model) {
        this.model = model;
    }

    public void validate() throws IllegalArgumentException {
        if (model == null) {
            throw new IllegalArgumentException("Model can not be null");
        }
        final Field[] modelFields = model.getClass().getDeclaredFields();

        for (Field modelField : modelFields) {
            validateIsDefinedField(modelField);
        }
    }

    private void validateIsDefinedField(Field modelField) {
        if (modelField.isAnnotationPresent(IsDefine.class)) {
            Object attributeValue = null;
            modelField.setAccessible(true);

            try {
                attributeValue = modelField.get(model);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            if (attributeValue == null) {
                throw new IllegalArgumentException(modelField + " is required");
            }
        }
    }
}
