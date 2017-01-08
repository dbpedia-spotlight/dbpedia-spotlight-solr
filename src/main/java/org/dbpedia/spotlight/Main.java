package org.dbpedia.spotlight;


import org.dbpedia.spotlight.lucene.LuceneData;
import org.dbpedia.spotlight.lucene.LuceneLoader;

import java.util.logging.Level;
import java.util.logging.Logger;



public class Main {

    private final static Logger LOGGER = Logger.getLogger(Main.class.getName());

    public static void main(String args[]) {

        if (args.length < 4 ) {
            System.out.println("Please inform language, dataset, lucene directory and solr url to continue");
            System.out.println();
            System.out.println("Eg:");
            System.out.println("Language English - en");
            System.out.println("Dataset: dbpedia");
            System.out.println("Lucene Directory: /mnt/lucene/2016-04/en/index-withSF-withTypes");
            System.out.println("SOLR url: http://localhost:8983/solr/2016-04");
            System.out.println();
            System.out.println(" java -jar dbpedia-spotlight-solr.java  en  dbpedia /mnt/lucene/2016-04/en/index-withSF-withTypes http://localhost:8983/solr/2016-04");

            return;
        }

        LuceneData data = new LuceneData();
        data.setLanguage(args[0]);
        data.setDataset(args[1]);
        data.setDirectory(args[2]);
        data.setSolrURL(args[3]);

        try {
            LOGGER.info(String.format("Starting with the following parameters: %s", data.toString()));
            LuceneLoader.loadData(data);
        } catch (Exception e) {
            LOGGER.log(Level.ALL, "Sorry, I could not perform SOLR Loading. Please check the parameters and try again", e);
        }
    }
}
