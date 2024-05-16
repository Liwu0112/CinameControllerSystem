import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import jdk.nashorn.internal.parser.JSONParser;

import java.io.*;
import java.net.URL;
import java.net.URLClassLoader;

public class Test02 {
    public static void main(String[] args) {
        String httpurl = "http://music.apesource.cn:3000/artist/list?initial=c&type=-1";
        try{
            InputStream in = new URL(httpurl).openConnection().getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in, "utf-8"));
            String line = null;
            if ((line = reader.readLine()) != null){
//                System.out.println(line);
                JSONObject jsonObject = JSON.parseObject(line);
                boolean isMore = jsonObject.getBooleanValue("more");
                String code = jsonObject.getString("code");
                JSONArray artists = jsonObject.getJSONArray("artists");
                System.out.println("more:"+isMore);
                System.out.println("code:"+code);
//                System.out.println("artists:"+artists);
                for (int i=0;i<artists.size();i++){
                    JSONObject artistsJSONObject = artists.getJSONObject(i);
                    String name = artistsJSONObject.getString("name");
                    int musicSize = artistsJSONObject.getIntValue("musicSize");
                    String img1v1Url = artistsJSONObject.getString("img1v1Url");
                    System.out.println("姓名："+ name);
                    System.out.println("数量:"+ musicSize);
                    System.out.println("海报："+ img1v1Url);

                    try(BufferedInputStream bis = new BufferedInputStream(new URL(img1v1Url).openConnection().getInputStream());
                        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("d:\\test"));){
                        byte[] buff = new byte[1024];
                        int len =-1;
                        while ((len=bis.read(buff)) != -1){
                            bos.write(buff,0,len);
                        }
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

