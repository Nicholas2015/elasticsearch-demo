package com.nicholas.elasticsearchdemo.document;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpHost;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.SortOrder;

import java.io.IOException;

public class ESDocQuery {

    public static void main(String[] args) throws IOException {
        // 创建客户端
        RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(new HttpHost("localhost",9200,"http")));

        // 查询索引中的全部数据
        SearchRequest request = new SearchRequest();
        request.indices("user");
//        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder().query(QueryBuilders.matchAllQuery());
//        request.source(searchSourceBuilder);
//
//        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
//        SearchHits hits = response.getHits();
//
//        // 响应状态
//        System.out.println(hits.getTotalHits());
//        System.out.println(response.getTook());
//
//        for (SearchHit hit : hits) {
//            System.out.println(hit.getSourceAsString());
//        }

        // 2.条件查询 termQuery
       /* SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder().query(QueryBuilders.termQuery("age",30));
        request.source(searchSourceBuilder);

        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        SearchHits hits = response.getHits();

        // 响应状态
        System.out.println(hits.getTotalHits());
        System.out.println(response.getTook());

        for (SearchHit hit : hits) {
            System.out.println(hit.getSourceAsString());
        }
*/
        // 3.分页查询
        /*SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder().query(QueryBuilders.matchAllQuery());
        // 起始位置 = （当前页码 - 1）*每页显示条数
        searchSourceBuilder.from(0);
        searchSourceBuilder.size(2);
        request.source(searchSourceBuilder);

        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        SearchHits hits = response.getHits();

        // 响应状态
        System.out.println(hits.getTotalHits());
        System.out.println(response.getTook());

        for (SearchHit hit : hits) {
            System.out.println(hit.getSourceAsString());
        }*/


        // 4.查询排序
//        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder().query(QueryBuilders.matchAllQuery());
//        // 起始位置 = （当前页码 - 1）*每页显示条数
//        searchSourceBuilder.sort("age", SortOrder.ASC);
//        request.source(searchSourceBuilder);
//
//        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
//        SearchHits hits = response.getHits();
//
//        // 响应状态
//        System.out.println(hits.getTotalHits());
//        System.out.println(response.getTook());
//
//        for (SearchHit hit : hits) {
//            System.out.println(hit.getSourceAsString());
//        }

        // 5.过滤字段
//        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder().query(QueryBuilders.matchAllQuery());
//        // 起始位置 = （当前页码 - 1）*每页显示条数
//        String[] includes = {};
//        String[] excludes = {"name"};
//        searchSourceBuilder.fetchSource(includes,excludes);
//        request.source(searchSourceBuilder);
//
//        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
//        SearchHits hits = response.getHits();
//
//        // 响应状态
//        System.out.println(hits.getTotalHits());
//        System.out.println(response.getTook());
//
//        for (SearchHit hit : hits) {
//            System.out.println(hit.getSourceAsString());
//        }

//        // 6.组合查询
//        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
//        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
//
////        boolQuery.must(QueryBuilders.matchQuery("age",30));
////        boolQuery.must(QueryBuilders.matchQuery("sex","男"));
////        boolQuery.mustNot(QueryBuilders.matchQuery("sex","男"));
//        boolQuery.should(QueryBuilders.matchQuery("age",30));
//        boolQuery.should(QueryBuilders.matchQuery("age",25));
//
//        searchSourceBuilder.query(boolQuery);
//
//        request.source(searchSourceBuilder);
//
//        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
//        SearchHits hits = response.getHits();
//
//        // 响应状态
//        System.out.println(hits.getTotalHits());
//        System.out.println(response.getTook());
//
//        for (SearchHit hit : hits) {
//            System.out.println(hit.getSourceAsString());
//        }

        // 7.范围查询
//        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
//        RangeQueryBuilder rangeQuery = QueryBuilders.rangeQuery("age");
//
//        rangeQuery.lte(25);
//
//        searchSourceBuilder.query(rangeQuery);
//
//        request.source(searchSourceBuilder);
//
//        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
//        SearchHits hits = response.getHits();
//
//        // 响应状态
//        System.out.println(hits.getTotalHits());
//        System.out.println(response.getTook());
//
//        for (SearchHit hit : hits) {
//            System.out.println(hit.getSourceAsString());
//        }

        // 8.模糊查询
//        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
//        FuzzyQueryBuilder fuzzyQuery = QueryBuilders.fuzzyQuery("name", "J").fuzziness(Fuzziness.fromEdits(2));
//
//        searchSourceBuilder.query(fuzzyQuery);
//
//        request.source(searchSourceBuilder);
//
//        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
//        SearchHits hits = response.getHits();
//
//        // 响应状态
//        System.out.println(hits.getTotalHits());
//        System.out.println(response.getTook());
//
//        for (SearchHit hit : hits) {
//            System.out.println(hit.getSourceAsString());
//        }

        // 9.高亮查询
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        TermsQueryBuilder termsQueryBuilder = QueryBuilders.termsQuery("name", "Nicho");

        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder.preTags("<font color='red'>");
        highlightBuilder.postTags("</font>");
        highlightBuilder.field("name");

        searchSourceBuilder.highlighter(highlightBuilder);
        searchSourceBuilder.query(termsQueryBuilder);

        request.source(searchSourceBuilder);

        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        SearchHits hits = response.getHits();

        ObjectMapper mapper = new ObjectMapper();

        // 响应状态
        System.out.println(mapper.writeValueAsString(response.getHits()));
        System.out.println(hits.getTotalHits());
        System.out.println(response.getTook());

        for (SearchHit hit : hits) {
            System.out.println(hit.getSourceAsString());
        }

        // 10.最大值查询
//        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
//
//        AggregationBuilder aggregationBuilder = AggregationBuilders.max("maxAge").field("age");
//
//        searchSourceBuilder.aggregation(aggregationBuilder);
//
//        request.source(searchSourceBuilder);
//
//        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
//        SearchHits hits = response.getHits();
//
//        ObjectMapper mapper = new ObjectMapper();
//
//        // 响应状态
//        System.out.println(mapper.writeValueAsString(response.getHits()));
//        System.out.println(hits.getTotalHits());
//        System.out.println(response.getTook());
//
//        for (SearchHit hit : hits) {
//            System.out.println(hit.getSourceAsString());
//        }

        // 11.分组查询
//        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
//
//        AggregationBuilder aggregationBuilder = AggregationBuilders.terms("ageGroup").field("age");
//
//        searchSourceBuilder.aggregation(aggregationBuilder);
//
//        request.source(searchSourceBuilder);
//
//        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
//        SearchHits hits = response.getHits();
//
//        ObjectMapper mapper = new ObjectMapper();
//
//        // 响应状态
//        System.out.println(mapper.writeValueAsString(response.getHits()));
//        System.out.println(hits.getTotalHits());
//        System.out.println(response.getTook());
//
//        for (SearchHit hit : hits) {
//            System.out.println(hit.getSourceAsString());
//        }

        // 关闭客户端
        client.close();
    }
}
