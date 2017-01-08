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

}
