package org.dbpedia.spotlight.solr;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class LoaderManager {

    public static void load(String solrURL, List<SpotlightDocument> spotlightDocuments) {

        Objects.requireNonNull(spotlightDocuments);

        SolrClient solr = new HttpSolrClient.Builder(solrURL).build();

        spotlightDocuments.stream().forEach(spotlightDocument -> {
            try {
                solr.add(spotlightDocument.to(spotlightDocument));
            } catch (SolrServerException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        try {
            solr.commit();
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
