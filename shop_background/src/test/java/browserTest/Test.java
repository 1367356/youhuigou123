package browserTest;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class Test {
    public static void main(String[] args){
        JSONObject json=new JSONObject();
        try {
            json.put("analyzer","ik_smart").put("text","安徽省长江流域");
        } catch (JSONException e) {
            e.printStackTrace();
        }

//        _analyze?pretty

//        String s=getStatusBySendGet();
//        System.out.println(s);
    }
    public static String getStatusBySendGet(String urlName) {
        String result = "";
        BufferedReader in = null;
        try {

            URL realUrl = new URL(urlName);
            // 打开和URL之间的连接
            java.net.HttpURLConnection connection = (java.net.HttpURLConnection)realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent", "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/536.11 (KHTML, like Gecko) Chrome/20.0.1132.57 Safari/536.11");
            connection.setRequestProperty("REFERER", "http://www.baidu.com");
            connection.setRequestMethod("GET");
            connection.setDoOutput(true);
            connection.setInstanceFollowRedirects(false);
            // 建立实际的连接
            connection.connect();

            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
                System.out.println(key + "--->" + map.get(key));
            }

            // 定义 BufferedReader输入流来读取URL的响应
            if(connection.getResponseCode() == 200) {
                in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                while ((line = in.readLine()) != null) {
                    result += line;
                }
            } else {
//                logger.error("验证请求发生错误，请求是：[ " + urlName + " ]，返回状态码：" + connection.getResponseCode());
            }
        } catch (Exception e) {
//            logger.error("发送GET请求出现异常，请求时：[ " + urlName + " ]，异常原因：\n" + e.getMessage());
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }
}
