package ru.shcheglov.solrsolrj;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "solr")
public class SolrProperties {
    private Boolean authentication;
    private String host;
    private String user;
    private String password;
}
