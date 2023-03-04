package ru.shcheglov.solrsolrj;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.solr.common.SolrDocument;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/")
public class SolrController {

    private final SolrService solrService;

    @GetMapping("add")
    public String add(@RequestParam("name") String name) {
        solrService.addOne(name);
        return "Added document with name " + name + " to SOLR";
    }

    @GetMapping("add/bean")
    public String addBean(@RequestParam Map<String, String> params) {
        solrService.addBlacklist(params);
        return "Added bean with name " + params.get("name") + " to SOLR";
    }

    @GetMapping("/get/all")
    public List<SolrDocument> getAll() {
        return solrService.getAll();
    }

    @GetMapping("/get/all/beans")
    public List<BlackListSolrEntity> getAllEntities() {
        return solrService.getAllSolrEntities();
    }

    @GetMapping("/delete/all")
    public String deleteAll() {
        solrService.deleteAll();
        return "Deleted all documents from SOLR";
    }

    @GetMapping("/delete/{id}")
    public String deleteById(@PathVariable("id") String id) {
        solrService.deleteById(id);
        return "Deleted document with id " + id + " from SOLR";
    }

    @GetMapping("/delete")
    public String deleteByQuery(@RequestParam("key") String key, @RequestParam("value") String value) {
        solrService.deleteByQuery(key, value);
        return "Deleted document with " + key + ":" + value + " from SOLR";
    }

    @GetMapping("/get/all/num")
    public String getTotalNumOfDocuments() {
        long totalNumOfDocuments = solrService.getTotalNumOfDocuments();
        return "Total number of documents in SOLR: " + totalNumOfDocuments;
    }

    @GetMapping("/get/{id}")
    public SolrDocument getById(@PathVariable("id") String id) {
        return solrService.getById(id);
    }

    @GetMapping("/get/name")
    public List<SolrDocument> getByName(@RequestParam("name") String name) {
        return solrService.getByName(name);
    }

    @GetMapping("/get/query")
    public List<SolrDocument> getByQuery(@RequestParam Map<String, String> params) {
        return solrService.getByQuery(params);
    }
}
