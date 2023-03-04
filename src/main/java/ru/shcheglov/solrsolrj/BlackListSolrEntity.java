package ru.shcheglov.solrsolrj;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.solr.client.solrj.beans.Field;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BlackListSolrEntity implements SolrBean {

    @Field
    private String id;

    @Field
    private String name;

    @Field
    private String price;
}
