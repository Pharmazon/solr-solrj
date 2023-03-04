package ru.shcheglov.solrsolrj;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.apache.solr.common.SolrDocument;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import static java.util.Collections.singletonList;
import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class SolrService {

    private final BlackListComponent blackListComponent;

    public void deleteByQuery(String key, String value) {
        blackListComponent.deleteByQuery(singletonList(new BlackListQuery(key, value)));
    }

    public long getTotalNumOfDocuments() {
        return blackListComponent.getTotalNumOfDocuments();
    }

    public List<SolrDocument> getAll() {
        return blackListComponent.getAll();
    }

    public void addOne(String name) {
        blackListComponent.addOne(name);
    }

    public void deleteAll() {
        blackListComponent.deleteAll();
    }

    public void deleteById(String id) {
        blackListComponent.deleteById(id);
    }

    public SolrDocument getById(String id) {
        return blackListComponent.getById(id);
    }

    public List<SolrDocument> getByName(String name) {
        return blackListComponent.getByQuery(Collections.singletonList(new BlackListQuery("name", name)));
    }

    public List<SolrDocument> getByQuery(Map<String, String> params) {
        val request = params.entrySet().stream()
                .map(SolrService::toBlackListQuery)
                .collect(toList());

        return blackListComponent.getByQuery(request);
    }

    public void addBlacklist(Map<String, String> params) {
        val entity = new BlackListSolrEntity(UUID.randomUUID().toString(), params.get("name"), params.get("price"));
        blackListComponent.addSolrBean(entity);
    }

    private static BlackListQuery toBlackListQuery(Map.Entry<String, String> entry) {
        return new BlackListQuery(entry.getKey(), entry.getValue());
    }
}
