package org.dbpedia.spotlight.solr;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.impl.ConcurrentUpdateSolrClient;
import org.dbpedia.spotlight.lucene.LuceneLoader;

import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoaderManager {

    private final static Logger LOGGER = Logger.getLogger(LoaderManager.class.getName());

    public static void load(String solrURL, List<SpotlightDocument> spotlightDocuments) {

        Objects.requireNonNull(spotlightDocuments);


        int processors = Runtime.getRuntime().availableProcessors();

        SolrClient solr = new ConcurrentUpdateSolrClient.Builder(solrURL).
                withThreadCount(processors)
                .withQueueSize(LuceneLoader.SIZE/processors).build();;

        spotlightDocuments.stream().forEach(spotlightDocument -> {
            try {
                solr.add(spotlightDocument.to(spotlightDocument));
            } catch (Exception e) {
                LOGGER.log(Level.ALL, String.format("I could not add document (%s) into SOLR", spotlightDocument.getUri()), e);
            }
        });

        try {
            solr.commit();
        } catch (Exception e) {
            LOGGER.log(Level.ALL, "I could not perform commit", e);
        }
    }
}
