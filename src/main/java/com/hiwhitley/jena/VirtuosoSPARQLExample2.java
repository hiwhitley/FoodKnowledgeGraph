package com.hiwhitley.jena;

import org.apache.jena.query.Query;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.RDFNode;
import virtuoso.jena.driver.VirtGraph;
import virtuoso.jena.driver.VirtuosoQueryExecution;
import virtuoso.jena.driver.VirtuosoQueryExecutionFactory;

/**
 * Created by hiwhitley on 16-11-19.
 */
public class VirtuosoSPARQLExample2 {

    /**
     * Executes a SPARQL query against a virtuoso url and prints results.
     */
    public static void main(String[] args) {

        String url;
        if(args.length == 0)
            url = "jdbc:virtuoso://localhost:1111";
        else
            url = args[0];

/*			STEP 1			*/
		/*
		 * 这里添加"Example2"后，类似于打开一个Graph叫做"Example2",，
		 * 如果没有会自动生成一个名为"Example2"的图
		 */
        VirtGraph graph = new VirtGraph ("http//www.hiwhitley.com/", url, "dba", "dba");

/*			STEP 2			*/
/*		Load data to Virtuoso		*/
        //清除"Example2" Graph
       // graph.clear ();

		/*
		 * 这里是读取IRI的Graph并存储到服务器的"Example2"
		 * 这里的读取，类似于在Web端在Quad Store Upload上的操作类似
		 * 如果网络连接不了，可以尝试删除下面的那些失败的内容
		 */
//        System.out.print ("Begin read from 'http://www.w3.org/People/Berners-Lee/card#i'  ");
//        graph.read("http://www.w3.org/People/Berners-Lee/card#i", "RDF/XML");
//        System.out.println ("\t\t\t Done.");
//
//        System.out.print ("Begin read from 'http://demo.openlinksw.com/dataspace/person/demo#this'  ");
//        graph.read("http://demo.openlinksw.com/dataspace/person/demo#this", "RDF/XML");
//        System.out.println ("\t Done.");
//
//        System.out.print ("Begin read from 'http://kidehen.idehen.net/dataspace/person/kidehen#this'  ");
//        graph.read("http://kidehen.idehen.net/dataspace/person/kidehen#this", "RDF/XML");
//        System.out.println ("\t Done.");


/*			STEP 3			*/
/*		Select only from VirtGraph	*/
        Query sparql = QueryFactory.create("PREFIX owl: <http://www.w3.org/2002/07/owl#>" +
                "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>" +
                "SELECT ?subject ?object WHERE { ?subject rdfs:subClassOf ?object }");

/*			STEP 4			*/
        VirtuosoQueryExecution vqe = VirtuosoQueryExecutionFactory.create (sparql, graph);

        ResultSet results = vqe.execSelect();
        while (results.hasNext()) {
            QuerySolution result = results.nextSolution();
            RDFNode graph_name = result.get("graph");
            RDFNode s = result.get("subject");
            RDFNode p = result.get("p");
            RDFNode o = result.get("object");
            System.out.println(graph_name + " { " + s + " " + p + " " + o + " . }");
        }

        System.out.println("graph.getCount() = " + graph.getCount());
    }
}

