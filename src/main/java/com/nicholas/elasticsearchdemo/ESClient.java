package com.nicholas.elasticsearchdemo;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

import java.io.IOException;

public class ESClient {

    public static void main(String[] args) throws IOException {

        // 创建客户端
        RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(new HttpHost("localhost",9200,"http")));



        // 关闭客户端
        client.close();
    }
}
