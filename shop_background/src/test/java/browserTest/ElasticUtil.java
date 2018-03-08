package browserTest;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.net.URI;

public class ElasticUtil {
    public String getBaseRequest(String index,String type,String param) {
        StringBuffer sb = new StringBuffer("http://localhost:9200");
            if (index != null && !"".equals(index.trim())) {
                sb.append(index);
            }
            if (type != null && !"".equals(type.trim())) {
                sb.append("/").append(type);
            }
            if (param != null && !"".equals(param.trim())) {
                sb.append("?").append(param);
            }
            HttpClient client= HttpClients.createDefault();
            try {
                URI uri=new URI(sb.toString());
                HttpGet httpGet = new HttpGet(uri);
                HttpResponse response = client.execute(httpGet);
                System.out.println(response.getEntity());
                return EntityUtils.toString(response.getEntity());
            } catch (Exception e) {
                e.printStackTrace();
            }
        return null;
        }

    @Test
    public void getTest() {
        ElasticUtil elasticUtil=new ElasticUtil();
        String baseRequest = elasticUtil.getBaseRequest("", "", "");
        System.out.println(baseRequest);
    }
}
