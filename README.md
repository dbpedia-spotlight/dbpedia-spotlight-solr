[![Build Status](https://travis-ci.org/dbpedia-spotlight/dbpedia-spotlight-solr .svg?branch=master)](https://travis-ci.org/dbpedia-spotlight/dbpedia-spotlight-solr )

## What is DBpedia Spotlight?

DBpedia Spotlight is a tool for automatically annotating mentions of DBpedia resources in text, providing a solution for linking unstructured information sources to the Linked Open Data cloud through DBpedia.

## What is DBpedia Spotlight SOLR?

DBpedia Spotlight SOLR is a technological version update for DBpedia Spotlight Lucene. This repo is just a tool that loads Apache Solr with our Lucene data. 

## Where are lucene's data?

We have created the [lucene-quickstarter](https://github.com/dbpedia-spotlight/lucene-quickstarter) repo using the original code (with some improvements) to continue making available the data provided by the approach published in [DBpedia Spotlight: Shedding Light on the Web of Documents](http://wifo5-03.informatik.uni-mannheim.de/bizer/pub/Mendes-Jakob-GarciaSilva-Bizer-DBpediaSpotlight-ISEM2011.pdf). 

The latest indices can be found ![Dropbox](http://www.iconsdb.com/icons/download/blue/dropbox-24.png) [HERE](https://www.dropbox.com/sh/q3vd67yr02w78mv/AAAc8K8_PkAAYO8a0scYsQ5Xa?dl=0).

## How to use?

*SOLR:*

* [Install](https://cwiki.apache.org/confluence/display/solr/Installing+Solr) the latest version of Apache SOLR
* Start it - (Eg.: ./solr start -m 6g)
* Create a core to store the data -(E.g: ./solr create_core -c 2016-04-EN)

*DBpedia Spotlight SOLR:*

* Compile this repo using mvn clean compile assembly:single (You will need Java 8 and Maven)
* Download or create the index that you want to load in SOLR (See  Where are lucene's data? section)
* Run it filling language, dataset, path to lucene index and URL to SOLR (E.g: java -jar target/solr-1.0-jar-with-dependencies.jar en dbpedia spotlight/lucene/2016-04/en/index-withSF-withTypes http://localhost:8983/solr/2016-04-EN)

Wait for the process to finish (it takes several minutes). When it finishes, you can query it using through the URL http://localhost:8983/solr/#/2016-04-EN/query.

Happy searching!!

## Issues

If you have any problems with or questions about this library, please contact us through a [GitHub issue](https://github.com/sandroacoelho/dbpedia-spotlight-solr/issues).


## How to cite: Lucene version

Pablo N. Mendes, Max Jakob, Andrés García-Silva and Christian Bizer. [DBpedia Spotlight: Shedding Light on the Web of Documents](http://wifo5-03.informatik.uni-mannheim.de/bizer/pub/Mendes-Jakob-GarciaSilva-Bizer-DBpediaSpotlight-ISEM2011.pdf). *Proceedings of the 7th International Conference on Semantic Systems (I-Semantics)*. Graz, Austria, 7–9 September 2011. 

```bibtex
@inproceedings{isem2011mendesetal,
  title = {DBpedia Spotlight: Shedding Light on the Web of Documents},
  author = {Pablo N. Mendes and Max Jakob and Andres Garcia-Silva and Christian Bizer},
  year = {2011},
  booktitle = {Proceedings of the 7th International Conference on Semantic Systems (I-Semantics)},
  abstract = {Interlinking text documents with Linked Open Data enables the Web of Data to be used as background knowledge within document-oriented applications such as search and faceted browsing. As a step towards interconnecting the Web of Documents with the Web of Data, we developed DBpedia Spotlight, a system for automatically annotating text documents with DBpedia URIs. DBpedia Spotlight allows users to configure the annotations to their specific needs through the DBpedia Ontology and quality measures such as prominence, topical pertinence, contextual ambiguity and disambiguation confidence. We compare our approach with the state of the art in disambiguation, and evaluate our results in light of three baselines and six publicly available annotation systems, demonstrating the competitiveness of our system. DBpedia Spotlight is shared as open source and deployed as a Web Service freely available for public use.}
}
```


## Maintainers

<a href="http://infai.org"><img src="http://infai.org/de/Aktuelles/files?get=10_jahre_infai_gold.PNG" align="left" height="20%" width="20%" ></a>

