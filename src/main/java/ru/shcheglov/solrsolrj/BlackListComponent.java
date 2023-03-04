package ru.shcheglov.solrsolrj;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.val;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.joining;

@Component
@RequiredArgsConstructor
public class BlackListComponent {

    private final BlackListRepository blackListRepository;

    @SneakyThrows
    public void deleteByQuery(List<BlackListQuery> queries) {
        val queryString = queries.stream()
                .map(q -> q.getFieldName() + ":" + q.getFieldValue())
                .collect(joining(" AND "));
        blackListRepository.deleteByQuery(queryString);
    }

    @SneakyThrows
    public long getTotalNumOfDocuments() {
        return blackListRepository.getTotalNumOfDocuments();
    }

    @SneakyThrows
    public List<SolrDocument> getAll() {
        return blackListRepository.findAll();
    }

    @SneakyThrows
    public void addOne(String name) {
        val document = new SolrInputDocument();

        document.addField("id", UUID.randomUUID().toString());
        document.addField("name", name);
        document.addField("price", "55.45");

        blackListRepository.save(document);
    }

    @SneakyThrows
    public void addSolrBean(SolrBean solrBean) {
        blackListRepository.saveSolrBean(solrBean);
    }

    @SneakyThrows
    public void deleteAll() {
        blackListRepository.deleteAll();
    }

    @SneakyThrows
    public void deleteById(String id) {
        blackListRepository.deleteById(id);
    }

    @SneakyThrows
    public SolrDocument getById(String id) {
        return blackListRepository.getById(id);
    }

    @SneakyThrows
    public List<SolrDocument> getByQuery(List<BlackListQuery> queries) {
        val queryString = queries.stream()
                .map(q -> q.getFieldName() + ":" + q.getFieldValue())
                .collect(joining(" AND "));
        return blackListRepository.getByQuery(queryString);
    }
}
