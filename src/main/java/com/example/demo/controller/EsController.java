package com.example.demo.controller;

import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * Created by baimugudu on 2019/4/8
 */

@RestController
public class EsController {


    @Autowired
    private TransportClient client;

    @GetMapping("/get/ad/plan")
    public ResponseEntity get(
            @RequestParam(name="id",defaultValue = "") String id){
        if(id.isEmpty()){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        GetResponse result = this.client.prepareGet(
                "ad",
                "plan",
                id
        ).get();

        if(!result.isExists()){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(result.getSource(), HttpStatus.OK);

    }


//    @PostMapping("add/book/novel")
//    public ResponseEntity add(
//            @RequestParam(name="title")String title,
//            @RequestParam(name = "author")String author,
//            @RequestParam(name="word_count")int wordCount,
//            @RequestParam(name = "publish_date")
//            @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date publishDate
//    ){
//
//        try{
//            XContentBuilder contentBuilder = XContentFactory.jsonBuilder()
//                    .startObject()
//                    .field("title",title)
//                    .field("author",author)
//                    .field("word_count",wordCount)
//                    .field("publish_date",publishDate.getTime())
//                    .endObject();
//
//            IndexResponse result =  this.client.prepareIndex(
//                   "book",
//                   "novel"
//            ).setSource(contentBuilder)
//                    .get();
//            return new ResponseEntity(result.getId(),
//                    HttpStatus.OK);
//        }catch(IOException e){
//                e.printStackTrace();
//                return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//
//
//    }
//
//
//
//    @DeleteMapping("/delete/book/novel")
//    public ResponseEntity delete(
//            @RequestParam(name="id")String id
//    ){
//        DeleteResponse response =  this.client.prepareDelete(
//                "book",
//                "novel",
//                id
//        ).get();
//        return new ResponseEntity(response.getResult(),HttpStatus.OK);
//    }
//
//
//@PutMapping("/update/book/novel")
//    public ResponseEntity update(
//            @RequestParam(name="id")String id,
//            @RequestParam(name = "title",required = false) String title,
//            @RequestParam(name="author",required = false)String author
//    ){
//
//        UpdateRequest request = new UpdateRequest("book","novel",id);
//        try {
//            XContentBuilder builder = XContentFactory.jsonBuilder()
//                    .startObject();
//
//            if(title!=null){
//                builder.field("title",title);
//            }
//
//            if(author!=null){
//                builder.field("author",author);
//            }
//
//            builder.endObject();
//            request.doc(builder);
//
//        } catch (IOException e) {
//            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//
//        try {
//            UpdateResponse response =  this.client.update(request).get();
//            return new ResponseEntity(response.getResult().toString(),HttpStatus.OK);
//        }  catch (Exception e) {
//            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//
//    }
//
//
//    @PutMapping("/query/book/outhor")
//    public ResponseEntity query(
//            @RequestParam(name = "author",required = false)String author,
//            @RequestParam(name = "title",required = false)String title,
//            @RequestParam(name = "gt_word_count",defaultValue = "0")int gtWordCount,
//            @RequestParam(name = "lt_word_count",required = true)Integer ltWordCount
//    ){
//        final BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
//        if(null!=author){
//            boolQueryBuilder.must(QueryBuilders.matchQuery("author",author));
//        }
//
//        if(null!=author){
//            boolQueryBuilder.must(QueryBuilders.matchQuery("title",title));
//        }
//         RangeQueryBuilder rangeQueryBuilder = QueryBuilders.rangeQuery("word_count")
//                 .from(gtWordCount);
//
//        if(ltWordCount!=null &&ltWordCount>0 ){
//            rangeQueryBuilder.to(ltWordCount);
//        }
//
//        boolQueryBuilder.filter(rangeQueryBuilder);
//
//        final SearchRequestBuilder searchRequestBuilder = this.client.prepareSearch("book")
//                .setTypes("novel")
//                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
//                .setQuery(boolQueryBuilder);
//
//        System.out.println(searchRequestBuilder);
//
//         SearchResponse searchResponse = searchRequestBuilder.get();
//
//        List<Map<String,Object>> result = new ArrayList<Map<String,Object>>();
//
//        searchResponse.getHits().forEach(
//                hit->result.add(hit.getSource())
//        );
//
//        return new ResponseEntity(result,HttpStatus.OK);
//
//
//    }


}
