package com.nicholas.elasticsearchdemo.model;

import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;

/**
 * @author Administrator
 */
@Data
@Document(indexName = "user")
public class TestBean implements Serializable {

    private String name;
}
