package org.dbpedia.spotlight.lucene;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LuceneData {

    private String directory;

    private String solrURL;

    private String dataset;

    private String language;

    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append("\n\n");
        builder.append(String.format(" DIRECTORY:  %s \n", directory) );
        builder.append(String.format(" SOLR URL:  %s", solrURL) );
        builder.append(String.format(" DATASET:  %s", dataset) );
        builder.append(String.format(" LANGUAGE:  %s", language) );

        return builder.toString();
    }

}
