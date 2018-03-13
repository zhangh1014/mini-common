package org.lechisoft.minifw.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Properties;

public class MiniMessage {
    
    private static Properties messages = null;
    static {
        if(null == messages){
            load();
        }
    }
    
    public static void load(String path){
        try {
            URL url = MiniMessage.class.getClassLoader().getResource(path);
            if (null == url) {
                MiniLog.error("Load message field:Can not find classpath/" + path);
            } else {
                InputStream is = MiniMessage.class.getClassLoader().getResourceAsStream(path);
                BufferedReader bf = new BufferedReader(new  InputStreamReader(is,"UTF-8")); // 中文乱码
                messages = new Properties();
                messages.load(bf);
                bf.close();
                is.close();
            }
        } catch (IOException e) {
            MiniLog.error("load message properties failed.", e);
        } catch (Exception e) {
            MiniLog.error("load message properties failed.", e);
        }
    }
    
    public static void load(){
        String path = "message.properties";
        MiniMessage.load(path);
    }
    
    public static String get(String key,Object... args){
        if(null != messages&&null != key){
            return String.format(messages.getProperty(key,""),args);
        }
        return "";
    }
}


