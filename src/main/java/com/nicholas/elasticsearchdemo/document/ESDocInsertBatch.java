package com.nicholas.elasticsearchdemo.document;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nicholas.elasticsearchdemo.User;
import org.apache.http.HttpHost;
import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;

import java.io.IOException;

public class ESDocInsertBatch {

    public static void main(String[] args) throws IOException {
        // 创建客户端
        RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(new HttpHost("localhost",9200,"http")));

        // 批量插入数据
        BulkRequest request = new BulkRequest();
        request.add(new IndexRequest().index("user").id("1004").source(XContentType.JSON,"name","Rose","age",30,"sex","男"));
        request.add(new IndexRequest().index("user").id("1005").source(XContentType.JSON,"name","John","age",25,"sex","女"));
        request.add(new IndexRequest().index("user").id("1006").source(XContentType.JSON,"name","Steve","age",20,"sex","男"));

        BulkResponse response = client.bulk(request, RequestOptions.DEFAULT);

        // 响应状态
        System.out.println(response.getItems());
        System.out.println(response.getTook());

        // 关闭客户端
        client.close();
    }
}
