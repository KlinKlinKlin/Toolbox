package view.page.dataProcessing.entity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;

/**
 * @作者 朱志鹏
 * @时间 2021-06-23 上午9:30
 */
public class JsonEntity {

    /***
     * 基本类型
     */
    private List<Type> column;

    /***
     * 对象字段
     */
    private HashMap<String,JSONObject> entity;

    /***
     * 集合字段
     */
    private HashMap<String,JSONArray> array;


    public List<Type> getColumn() {
        return column;
    }

    public JsonEntity setColumn(List<Type> column) {
        this.column = column;
        return this;
    }

    public HashMap<String, JSONObject> getEntity() {
        return entity;
    }

    public JsonEntity setEntity(HashMap<String, JSONObject> entity) {
        this.entity = entity;
        return this;
    }

    public HashMap<String, JSONArray> getArray() {
        return array;
    }

    public JsonEntity setArray(HashMap<String, JSONArray> array) {
        this.array = array;
        return this;
    }

    @Override
    public String toString() {
        return "JsonEntity{" +
                "column=" + column +
                ", entity=" + entity +
                ", array=" + array +
                '}';
    }
}
