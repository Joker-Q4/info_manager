package com.manager.global;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class JsonTools {

	
	public static JSONObject WrapResult(String result,String msg){
		//com.fitsoft.beans.Log.info("------JsonTools getEmptyTable result:"+result+" msg:"+msg);
		JSONObject jo = new JSONObject();
		jo.put("successful",result);
		jo.put("resultHint",msg);
		return jo;
	}
	

	public static JSONArray getJsonArray(Properties pmx){
		if(pmx==null)
			return null;
		
		String[] columns= (String[])pmx.get("column");
		String[][] data = (String[][])pmx.get("data");
        
        if(data==null || data.length<1){
        	return null;
        };
        ArrayList<Map> list = new ArrayList<Map>();
        for(int i=0;i<data.length;i++){
        	HashMap<String, String> map_data = new HashMap<String, String>();
        	for(int j=0;j<columns.length;j++){
    			map_data.put(columns[j], data[i][j]);
        	}
        	list.add(map_data);
        }
        // 将List转换为JSONArray数据  
        JSONArray ja1 = (JSONArray) JSONObject.toJSON(list);        
        return ja1;	
	}

	/**
	 * 得到json所有属性
	 * @param jsonObject
	 * @return Map
	 */
	public static Map<String, String> getJsonMap(JSONObject jsonObject){
		HashMap<String, String> map =new HashMap<>();
		for (Entry entry : jsonObject.entrySet()) {
			map.put(entry.getKey().toString(),entry.getValue().toString());
        }
		return map;
	}
	
	/**
	 * 解析返回的json
	 * @param jsonObject
	 * @return
	 */
	//value: {"items":[{"id":"4","workstation":"1.0.0.136","operator":"zhang2","commands":"update"}],"cmd":"update","pk":"4","token":"123456"}

 
	public static List getJsonDate(JSONObject jsonObject,String key){
		
		//JSONObject jsonObject = JSONObject.parseObject(json); 
 
 
		JSONArray arrays=jsonObject.getJSONArray(key);
		if(arrays == null){
			System.out.println("arrays is null");
			return null;
		}
		ArrayList<Map> mylist = new ArrayList<Map>();
	
		for(int z=0;z<arrays.size();z++)
		{
			JSONObject jsono=JSONObject.parseObject(arrays.get(z).toString()); 
			
			Map<String, Object> map =jsono; 
			
	        for (Entry<String, Object> entry : map.entrySet()) {
	            //getKey获取Entry集合中的key、getValue获取value
	            System.out.println(entry.getKey()+"="+entry.getValue()); 
	        }
	        
			mylist.add(map);
			/*
			for(Object key:jsono.keySet()){
				String value = jsono.getString(key.toString());
				System.out.println(key+" "+value);
			}*/
		}
		return mylist;	    
	    
	}

	public static Map<String, Object> toResult(boolean isSuccess,  String msg){
		Map<String, Object> map = new HashMap<>();
		map.put("successful", isSuccess);
		map.put("msg", msg);
		return map;
	}

	public static Map<String, Object> toResult(int code, String msg, int count, Object data){
		Map<String, Object> map = new HashMap<>();
		map.put("code", code);
		map.put("msg", msg);
		map.put("count", count);
		map.put("data", data);
		return map;
	}
	
	
 
	
	/*
	public JSONArray getJsonArray(Properties pmx){
		if(pmx==null)
			return null;
		
		String[] columns= (String[])pmx.get("column");
		String[][] data = (String[][])pmx.get("data");
        
        if(data==null || data.length<1){
        	return null;
        };
        ArrayList<Map> list = new ArrayList<Map>();
        for(int i=0;i<data.length;i++){
        	java.util.HashMap<String, String> map_data = new HashMap<String, String>();
        	for(int j=0;j<columns.length;j++){
    			map_data.put(columns[j], data[i][j]);
        	}
        	list.add(map_data);
        }
        // 将List转换为JSONArray数据  
        JSONArray ja1 = (JSONArray) JSONObject.toJSON(list);        
        return ja1;	
	}*/
	
	
	/*
	public static String getJggridJson(JqGridInfoBean grid) {
		 return grid.wrapJqgrid();
	}*/	
	
	public static void main(String[] args){
		/*
		String json="{\"items\":[{\"id\":\"4\",\"workstation\":\"1.0.0.136\",\"operator\":\"zhang2\",\"commands\":\"update\"}],\"cmd\":\"update\",\"pk\":\"4\",\"token\":\"123456\"}";
		List list = getDate(json);
		if(list==null){
			System.out.println("list is null");
		}
		for(int i=0;i<list.size();i++){
			Map<String, Object> map =(Map<String, Object>)list.get(i); 
			System.out.println(map.get("id"));
		}*/
		
		//String json="{\"items\":[{\"id\":\"4\",\"workstation\":\"1.0.0.136\",\"operator\":\"zhang2\",\"commands\":\"update\"}],\"cmd\":\"update\",\"pk\":\"4\",\"token\":\"123456\"}";
		//String json="[{\"id\":\"4\",\"workstation\":\"1.0.0.136\",\"operator\":\"zhang2\",\"commands\":\"update\"}]";
		 
		//JSONObject jsonObject = JSONObject.parseObject(json); 
		/*
		for (Map.Entry entry : jsonObject.entrySet()) { 
			System.out.println(entry.getKey().toString()+":"+ entry.getValue().toString());                
        }*/
		
		//String str = "[\"\\/static\\/upload\\/1539863638_0.png\"]";
		String str="{\"pk\":[\"1\",\"2\"]}";

		JSONObject jsonObject = JSONObject.parseObject(str); 
		//获取number对应的数组
		JSONArray list=jsonObject.getJSONArray("pk");
		//分别获取numberList中的每个值
		for(int i=0;i<list.size();i++){
			//因为数组中的类型为int,所以为getInt,其他getString、getLong具有类似的用法
			System.out.println(list.get(i));
		}	    
	}
}
