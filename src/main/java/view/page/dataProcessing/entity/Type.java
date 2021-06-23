package view.page.dataProcessing.entity;

/**
 * @作者 朱志鹏
 * @时间 2021-06-23 下午12:41
 */
public class Type {
    private String fieldName; // 属性名
    private String fieldType; // 属行类型 基本类型

    public String getFieldName() {
        return fieldName;
    }

    public Type setFieldName(String fieldName) {
        this.fieldName = fieldName;
        return this;
    }

    public String getFieldType() {
        return fieldType;
    }

    public Type setFieldType(String fieldType) {
        this.fieldType = fieldType;
        return this;
    }

    @Override
    public String toString() {
        return "Type{" +
                "fieldName='" + fieldName + '\'' +
                ", fieldType='" + fieldType + '\'' +
                '}';
    }
}
