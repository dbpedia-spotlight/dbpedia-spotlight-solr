package org.dbpedia.spotlight.lucene;


import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.store.FSDirectory;
import org.dbpedia.spotlight.solr.LoaderManager;
import org.dbpedia.spotlight.solr.SpotlightDocument;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LuceneLoader {

    private static final int SIZE = 10000;

    public static void loadData(LuceneData data) throws IOException {
        File indexDirectory = new File(data.getDirectory());

        IndexReader reader = IndexReader.open(FSDirectory.open(indexDirectory));
        int num = reader.numDocs();

        List<SpotlightDocument> spotlightDocuments = new ArrayList<>();

        System.out.println(String.format("I will load %d documents", num));

        for (int i = 0; i < num; i++) {
            if (!reader.isDeleted(i)) {
                Document document = reader.document(i);
                spotlightDocuments.add(SpotlightDocument.from(document, data.getDataset(), data.getLanguage()));
            }

            if (i % SIZE == 0 && i != 0) {
                persit(data, spotlightDocuments);
                spotlightDocuments = new ArrayList<>();
                System.out.println(String.format("Commiting %d documents", i));
            }
        }
        persit(data, spotlightDocuments);
        System.out.println(String.format("done!"));
        reader.close();

    }


    private static void persit(LuceneData data, List<SpotlightDocument> spotlightDocuments ) {
        LoaderManager.load(data.getSolrURL(), spotlightDocuments);

    }

}
