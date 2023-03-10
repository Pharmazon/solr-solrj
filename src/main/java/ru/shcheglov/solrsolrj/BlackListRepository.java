package ru.shcheglov.solrsolrj;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrInputDocument;
import org.apache.solr.common.params.MapSolrParams;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class BlackListRepository {

    private static final String BLACKLIST_CORE = "blacklist";

    private final HttpSolrClient solrClient;

    public void save(SolrInputDocument document) throws SolrServerException, IOException {
        solrClient.add(BLACKLIST_CORE, document);
        solrClient.commit(BLACKLIST_CORE);
    }

    public void saveSolrEntity(SolrEntity solrBean) throws SolrServerException, IOException {
        solrClient.addBean(BLACKLIST_CORE, solrBean);
        solrClient.commit(BLACKLIST_CORE);
    }

    public List<BlackListSolrEntity> findAllSolrEntities() throws SolrServerException, IOException {
        val query = new SolrQuery("*:*");
        query.setSort("name", SolrQuery.ORDER.asc);

        val response = solrClient.query(BLACKLIST_CORE, query);
        return response.getBeans(BlackListSolrEntity.class);
    }

    public SolrDocument getById(String id) throws SolrServerException, IOException {
       return solrClient.getById(BLACKLIST_CORE, id);
    }

    public List<SolrDocument> findAll() throws SolrServerException, IOException {
        val queryParamMap = new HashMap<String, String>();
        queryParamMap.put("q", "*:*");
        queryParamMap.put("sort", "name asc");

        return solrClient.query(BLACKLIST_CORE, new MapSolrParams(queryParamMap)).getResults();
    }

    public void deleteAll() throws SolrServerException, IOException {
        solrClient.deleteByQuery(BLACKLIST_CORE, "*");
        solrClient.commit(BLACKLIST_CORE);
    }

    public void deleteByQuery(String queryString) throws SolrServerException, IOException {
        solrClient.deleteByQuery(BLACKLIST_CORE, queryString);
        solrClient.commit(BLACKLIST_CORE);
    }

    public List<SolrDocument> getByQuery(String queryString) throws SolrServerException, IOException {
        return solrClient.query(BLACKLIST_CORE, new SolrQuery(queryString)).getResults();
    }

    public void deleteById(String id) throws SolrServerException, IOException {
        solrClient.deleteById(BLACKLIST_CORE, id);
        solrClient.commit(BLACKLIST_CORE);
    }

    public long getTotalNumOfDocuments() throws SolrServerException, IOException {
        val query = new SolrQuery("*:*");
        query.setRows(0);
        return solrClient.query(BLACKLIST_CORE, query).getResults().getNumFound();
    }
}
