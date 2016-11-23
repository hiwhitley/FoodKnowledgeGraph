package com.hiwhitley.jena.factory;

import com.hiwhitley.jena.bean.ShopIndividual;
import org.apache.jena.ontology.Individual;
import org.apache.jena.ontology.OntModel;

/**
 * Created by hiwhitley on 16-11-22.
 */
public abstract class ShopFactory {
    public abstract Individual createShop(OntModel ontModel ,ShopIndividual bean);
}
