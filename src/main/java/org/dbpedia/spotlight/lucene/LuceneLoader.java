package org.dbpedia.spotlight.lucene;


import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.store.FSDirectory;
import org.dbpedia.spotlight.Main;
import org.dbpedia.spotlight.solr.LoaderManager;
import org.dbpedia.spotlight.solr.SpotlightDocument;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class LuceneLoader {

    private final static Logger LOGGER = Logger.getLogger(LuceneLoader.class.getName());

    private static final int SIZE = 10000;

    public static void loadData(LuceneData data) throws IOException {
        File indexDirectory = new File(data.getDirectory());

        IndexReader reader = IndexReader.open(FSDirectory.open(indexDirectory));
        int num = reader.numDocs();

        List<SpotlightDocument> spotlightDocuments = new ArrayList<>();

        LOGGER.info(String.format("I will load %d documents", num));

        for (int i = 0; i < num; i++) {
            if (!reader.isDeleted(i)) {
                Document document = reader.document(i);
                spotlightDocuments.add(SpotlightDocument.from(document, data.getDataset(), data.getLanguage()));
            }

            if (i % SIZE == 0 && i != 0) {
                persit(data, spotlightDocuments);
                spotlightDocuments = new ArrayList<>();
                LOGGER.info(String.format("Commiting %d documents", i));
            }
        }
        persit(data, spotlightDocuments);
        LOGGER.info(String.format("done!"));
        reader.close();

    }

    private static void persit(LuceneData data, List<SpotlightDocument> spotlightDocuments ) {
        LoaderManager.load(data.getSolrURL(), spotlightDocuments);
    }

}
