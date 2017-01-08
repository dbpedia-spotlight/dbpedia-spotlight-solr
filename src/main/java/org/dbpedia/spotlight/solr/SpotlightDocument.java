package org.dbpedia.spotlight.solr;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.lucene.document.Document;
import org.apache.solr.common.SolrInputDocument;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class SpotlightDocument {

    private String dataset;

    private String language;

    private String uri;

    private Long uriCount;

    private Set<String> surfaceForms;

    private Set<String> context;

    private Set<String> type;


    public static SpotlightDocument from(Document document, String dataset, String language) {

        SpotlightDocument spotlightDocument = new SpotlightDocument();

        spotlightDocument.setDataset(dataset);
        spotlightDocument.setLanguage(language);
        spotlightDocument.setUri(document.get(Fields.URI));
        spotlightDocument.setUriCount(Long.valueOf(document.get(Fields.URI_COUNT)));

        Set<String> surfaceForms = new HashSet<>();

        Arrays.stream(document.getFieldables(Fields.SURFACE_FORM)).forEach(sf -> surfaceForms.add(sf.stringValue()));
        spotlightDocument.setSurfaceForms(surfaceForms);


        Set<String> contexts = new HashSet<>();

        Arrays.stream(document.getFieldables(Fields.CONTEXT)).forEach(sf -> contexts.add(sf.stringValue()));
        spotlightDocument.setContext(contexts);

        Set<String> types = new HashSet<>();

        Arrays.stream(document.getFieldables(Fields.TYPE)).forEach(tp -> types.add(tp.stringValue()));
        spotlightDocument.setType(types);

        return spotlightDocument;
    }


    public SolrInputDocument to(SpotlightDocument spotlightDocument) {

        SolrInputDocument solrDocument = new SolrInputDocument();
        solrDocument.addField(Fields.DATASET, spotlightDocument.getDataset());
        solrDocument.addField(Fields.LANGUAGE, spotlightDocument.getLanguage());
        solrDocument.addField(Fields.URI, spotlightDocument.getUri());
        solrDocument.addField(Fields.URI_COUNT, spotlightDocument.getUriCount());
        solrDocument.addField(Fields.URI_COUNT.concat("_i"), spotlightDocument.getUriCount());

        surfaceForms.stream().forEach(sf -> solrDocument.addField(Fields.SURFACE_FORM, sf));

        context.stream().forEach(ctx -> solrDocument.addField(Fields.CONTEXT, ctx));

        type.stream().forEach(tp -> solrDocument.addField(Fields.TYPE, tp));

        return solrDocument;
    }
}
