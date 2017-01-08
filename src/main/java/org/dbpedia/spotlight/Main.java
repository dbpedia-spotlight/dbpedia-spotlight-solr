package org.dbpedia.spotlight;


import org.dbpedia.spotlight.lucene.LuceneData;
import org.dbpedia.spotlight.lucene.LuceneLoader;

public class Main {

    public static void main(String args[]) {

        LuceneData data = new LuceneData();
        data.setLanguage("en");
        data.setDataset("dbpedia");
        data.setDirectory("/home/sandro/Dropbox/spotlight/lucene/2016-04/en/index-withSF-withTypes");
        data.setSolrURL("http://localhost:8983/solr/2016-04");

        try {
            LuceneLoader.loadData(data);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
