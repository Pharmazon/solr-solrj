package ru.shcheglov.solrsolrj;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.impl.PreemptiveAuth;
import org.apache.solr.client.solrj.impl.XMLResponseParser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

@Configuration
@RequiredArgsConstructor
public class SolrConfiguration {

    private final SolrProperties solrProperties;

    @Bean
    public HttpSolrClient solrClient() {
        val httpClientBuilder = HttpClientBuilder.create();

        if (Boolean.TRUE.equals(solrProperties.getAuthentication())) {
            val credentialsProvider = new BasicCredentialsProvider();
            credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(solrProperties.getUser(),
                    solrProperties.getPassword()));
            httpClientBuilder.addInterceptorFirst(new PreemptiveAuth(new BasicScheme()))
                    .setDefaultCredentialsProvider(credentialsProvider);
        }

        return new HttpSolrClient.Builder(solrProperties.getHost())
                .withHttpClient(httpClientBuilder.build())
                .withResponseParser(new XMLResponseParser())
                .build();
    }
}
