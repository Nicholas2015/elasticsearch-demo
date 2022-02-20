package com.nicholas.elasticsearchdemo.document;

import org.apache.http.HttpHost;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;

import java.io.IOException;

public class ESDocDeleteBatch {

    public static void main(String[] args) throws IOException {
        // 创建客户端
        RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(new HttpHost("localhost",9200,"http")));

        // 批量删除数据
        BulkRequest request = new BulkRequest();
        request.add(new DeleteRequest().index("user").id("1004"));
        request.add(new DeleteRequest().index("user").id("1005"));

        BulkResponse response = client.bulk(request, RequestOptions.DEFAULT);

        // 响应状态
        System.out.println(response.getItems());
        System.out.println(response.getTook());

        // 关闭客户端
        client.close();
    }
}
