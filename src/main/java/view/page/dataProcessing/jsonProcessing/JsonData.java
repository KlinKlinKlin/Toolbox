package view.page.dataProcessing.jsonProcessing;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * @作者 朱志鹏
 * @时间 2021-06-22 下午10:50
 */
public class JsonData {


    public String processingData(String text) {

        JSONObject object = new JSONObject(text);

        if(object != null){

            for (String key : object.keySet()) {

                Object o = object.get(key);
                if( o instanceof String )
                    System.out.println("string");
                if( o instanceof Integer)
                    System.out.println("integer");
                if(o instanceof Long)
                    System.out.println("long");
                if(o instanceof JSONObject)
                    System.out.println("obj");
                if(o instanceof JSONArray)
                    System.out.println("array");
                if(o instanceof Boolean)
                    System.out.println("boolean");

                /***
                 * TODO 判断出 值是什么类型  然后创建出来 存放到 stringBuild
                 */

            }
        }


        System.out.println(object);


        return null;
    }
}
