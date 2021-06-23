package view.page.dataProcessing.jsonProcessing;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONString;
import utils.CollectionUtils;
import view.page.dataProcessing.Constant;
import view.page.dataProcessing.entity.JsonEntity;
import view.page.dataProcessing.entity.Type;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.jar.JarEntry;

/**
 * @作者 朱志鹏
 * @时间 2021-06-22 下午10:50
 */
public class JsonData {
    public List<String> processingData(String data, LinkedHashMap<String, Boolean> map, String text) {
        JSONObject object = new JSONObject(data);
        getObj(text,object);
        return createBean(map);
    }

    private List<String> createBean(LinkedHashMap<String, Boolean> map) {
        ArrayList<String> strings = new ArrayList<>();

        Constant.OBJ.keySet().forEach(e->{

            JSONObject object = Constant.OBJ.get(e);
            JsonEntity entity = new JsonEntity();
            for (String key : object.keySet()) {

                Object o = object.get(key);

                addField(o,key,entity);
            }
            String string = createBean(entity, map, e);
            strings.add(string);
        });
        return strings;
    }

    private void getObj(String text,JSONObject object) {
        if(object!=null){
            JsonEntity entity = new JsonEntity();
            for (String key : object.keySet()) {

                Object o = object.get(key);

                addField(o,key,entity);
            }
            Constant.OBJ.put(text,object);
            HashMap<String, JSONObject> map = entity.getEntity();
            if(CollectionUtils.isNotEmpty(map)) {
                for (String key : map.keySet()) {
                    getObj(key,map.get(key));
                }
            }
            HashMap<String, JSONArray> array = entity.getArray();
            if(CollectionUtils.isNotEmpty(array)){
                for (String key : array.keySet()) {
                    JSONArray objects = array.get(key);

                    Object o = objects.get(0);

                    getObj(key,(JSONObject) o);

                }

            }

        }
        return;

    }


    /***
     * 生成 javaBean
     * @param entity
     * @param map
     * @param className
     * @return
     */
    private String createBean(JsonEntity entity, LinkedHashMap<String, Boolean> map, String className) {
        StringBuilder builder = createInfo(entity,map,className);
        return builder.toString();
    }

    private StringBuilder createInfo(JsonEntity entity, LinkedHashMap<String, Boolean> map, String className){
        String name = getClassName(className);
        StringBuilder builder = new StringBuilder("/****JSON 生成 "+name+" 类****/");
        builder.append("\n");
        builder.append("\n");
        builder.append(createHeader(map));
        builder.append(createClassName(className));
        builder.append("\n");
        builder.append(createField(entity));
        builder.append("\n");
        builder.append("}");
        return builder;
    }

    /***
     * 类名大写
     * @param className
     * @return
     */
    private String getClassName(String className) {
        char c = className.charAt(0);
        String str = c+"";
        String string = str.toUpperCase();
        String result = "";
        if(className.length() > 1)
            result = string + className.substring(1,className.length());
        return result;
    }



    /***
     * json字符串转换成处理数据对象
     * @param entity
     * @return
     */
    private StringBuilder createField(JsonEntity entity) {
        StringBuilder builder = new StringBuilder();
        List<Type> column = entity.getColumn();
        if(CollectionUtils.isNotEmpty(column)){
            column.forEach(e->{
                builder.append("    private  "+e.getFieldType()+"  " + e.getFieldName()+";\n");
            });
        }
        HashMap<String, JSONArray> array = entity.getArray();
        if(CollectionUtils.isNotEmpty(array))
        {
            array.keySet().forEach(e->{
                String className = getClassName(e);
                builder.append("    private  List<"+ className + ">  " + e + ";\n");

            });
        }
        HashMap<String, JSONObject> obj = entity.getEntity();
        if(CollectionUtils.isNotEmpty(obj))
        {
            obj.keySet().forEach(e -> {
                String className = getClassName(e);
                builder.append("    private  "+ className + "  " + e + ";\n");
            });
        }
        return builder;
    }

    /***
     * 创建 类名
     * @param className
     * @return
     */
    private StringBuilder createClassName(String className) {

        String name = getClassName(className);
        StringBuilder builder = new StringBuilder("public class "+ name + "    {");
        return builder;
    }

    /***
     * 创建注解
     * @param map 选中的 单选框
     * @return
     */
    private StringBuilder createHeader(LinkedHashMap<String, Boolean> map) {
        StringBuilder builder = new StringBuilder();
        map.keySet().forEach(e->{
            if(map.get(e))
                builder.append("@"+e.substring( 0 , e.length() - 3 )+"\n");
        });
        return builder;
    }

    /***
     * 创建类中属性
     * @param o
     * @param key
     * @param entity
     */
    private void addField(Object o ,String key,JsonEntity entity){
        if( o instanceof String )
        {
            List<Type> column = entity.getColumn();
            if(CollectionUtils.isEmpty(column))
                column = new ArrayList<>();
            column.add(new Type().setFieldName(key).setFieldType("String"));
            entity.setColumn(column);
        }
        if( o instanceof Integer){
            List<Type> column = entity.getColumn();
            if(CollectionUtils.isEmpty(column))
                column = new ArrayList<>();
            column.add(new Type().setFieldName(key).setFieldType("Integer"));
            entity.setColumn(column);
        }
        if(o instanceof JSONObject)
        {
            HashMap<String, JSONObject> map = entity.getEntity();
            if(CollectionUtils.isEmpty(map))
                map = new HashMap<>();
            map.put(key,(JSONObject) o);
            entity.setEntity(map);
        }
        if(o instanceof JSONArray)
        {
            HashMap<String, JSONArray> map = entity.getArray();
            if(CollectionUtils.isEmpty(map))
                map = new HashMap<>();
            map.put(key,(JSONArray) o);
            entity.setArray(map);
        }
    }
}
