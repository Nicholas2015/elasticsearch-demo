package com.nicholas.elasticsearchdemo.document;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nicholas.elasticsearchdemo.User;
import org.apache.http.HttpHost;
import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;

import java.io.IOException;

public class ESDocInsert {

    public static void main(String[] args) throws IOException {
        // 创建客户端
        RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(new HttpHost("localhost",9200,"http")));

        // 插入数据
        IndexRequest request = new IndexRequest();
        request.index("user").id("1001");

        User user = new User();
        user.setName("Nicholas");
        user.setAge(30);
        user.setSex("男");

        // 向ES中插入数据，必须将数据转换成JSON格式
        ObjectMapper mapper = new ObjectMapper();
        String value = mapper.writeValueAsString(user);
        request.source(value, XContentType.JSON);

        IndexResponse response = client.index(request, RequestOptions.DEFAULT);
        DocWriteResponse.Result result = response.getResult();

        // 响应状态
        System.out.println(result);

        // 关闭客户端
        client.close();
    }
}
