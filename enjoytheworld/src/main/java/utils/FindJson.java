package utils;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import bean.FindMainItem;

/**
 * Created by admin on 2016/7/26.
 */
public class FindJson {
    public static List<FindMainItem> parseJson(String json)
    {
        List<FindMainItem> list=new ArrayList<>();
        try {
            JSONObject object=new JSONObject(json);
            JSONArray array=object.optJSONArray("itemList");
            for (int i = 0; i < array.length(); i++) {
                JSONObject object1=array.optJSONObject(i);
                if(i==0)
                {
                    JSONObject object2=object1.optJSONObject("data");
                    JSONArray array2=object2.optJSONArray("itemList");
                    HandPick_All_Static_Obj.viewPager_num=array2.length();
                    for (int j= 0; j < array2.length(); j++) {
                        JSONObject object3=array2.optJSONObject(j);
                        JSONObject object4=object3.optJSONObject("data");
                        String id=object4.optString("id");
                        String image=object4.optString("image");
                        String title=object4.optString("title");
                        String actionUrl=object4.optString("actionUrl");
                        FindMainItem item=new FindMainItem(image,id,title,actionUrl);
                        Log.i("BBB", "=========: "+image);
                        list.add(item);
                    }

                }
                else
                {
                    JSONObject object5=object1.optJSONObject("data");
                    String id=object5.optString("id");
                    String image=object5.optString("image");
                    String title=object5.optString("title");
                    String actionUrl=object5.optString("actionUrl");
                    FindMainItem item=new FindMainItem(image,id,title,actionUrl);
                    list.add(item);
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.i("AAA", "主页面解析下来的数据是:"+list.toString());
        return list;
    }
}
