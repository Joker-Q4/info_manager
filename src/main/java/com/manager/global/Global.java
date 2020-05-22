package com.manager.global;

import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.Properties;

public class Global {

    public static Properties getRequest(HttpServletRequest request)throws UnsupportedEncodingException {
        java.util.Properties prequest = new java.util.Properties();
        java.util.Enumeration<String> e = request.getParameterNames();
        while(e.hasMoreElements()) {
            String para = e.nextElement();
            System.out.println("para"+para);
            if(para != null){
                if(para.equalsIgnoreCase("Allparams")){

                    String values = java.net.URLDecoder.decode(request.getParameter(para), "UTF-8");
                    values = java.net.URLDecoder.decode(values, "UTF-8");

                    System.out.println(values);
                    String[] arr = values.split("&");
                    for(int i=0;i<arr.length;i++){
                        if(arr==null||arr.equals(""))
                            continue;
                        String[] keyval = arr[i].split("=");
                        if(keyval.length<2){
                            prequest.setProperty(keyval[0],"");
                            System.out.println("key:"+keyval[0]+" empty");
                        }else{
                            if(keyval[1].startsWith("function String"))
                                keyval[1]="";
                            prequest.setProperty(keyval[0], keyval[1]);
                            System.out.println("key:"+keyval[0]+" "+keyval[1]);
                        }
                    }
                }else if(para.equals("json")){
                    String values = java.net.URLDecoder.decode(request.getParameter(para), "UTF-8");
                    values = java.net.URLDecoder.decode(values, "UTF-8");
                    prequest.setProperty(para, values);
                    JSONObject jsonObject = JSONObject.parseObject(values);

                    Map<String, String> map = JsonTools.getJsonMap(jsonObject);
                    if(map!=null){
                        for(Map.Entry<String, String> entry : map.entrySet()){
                            String mapKey = entry.getKey();
                            String mapValue = entry.getValue();
                            prequest.setProperty(mapKey, mapValue);
                        }
                    }

                }else{
                    String values="";
                    try{
                        values = java.net.URLDecoder.decode(request.getParameter(para), "UTF-8");
                        values = java.net.URLDecoder.decode(values, "UTF-8");
                        if(values.startsWith("function String")){
                            values="";
                        }
                    }catch(Exception ex){
                        ex.printStackTrace();
                    }
                    System.out.println("key:"+para+" "+values);
                    prequest.setProperty(para, values);
                }
            }
        }
        return prequest;
    }
}
