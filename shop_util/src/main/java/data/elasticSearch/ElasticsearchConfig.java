package data.elasticSearch;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.util.Properties;

public class ElasticsearchConfig {
    private static TransportClient client;
    public TransportClient getElasticsearchClient() {
        InputStream in = this.getClass().getResourceAsStream("/config.properties");
        Properties properties = new Properties();
        try {
            properties.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String host = properties.getProperty("host");
        String port = properties.getProperty("port");
        String clustername = properties.getProperty("clustername");
        int intport = Integer.parseInt(port);

        try {
            Settings settings = Settings.builder()
                    .put("cluster.name", clustername)
                    .put("client.transport.ignore_cluster_name", true)
                    .build();
            //创建client
            client = new PreBuiltTransportClient(settings)
                    .addTransportAddress(new TransportAddress(InetAddress.getByName(host), intport));
            return client;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
