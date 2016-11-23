package com.hiwhitley.jena.factory;

import com.hiwhitley.jena.bean.Constant;
import com.hiwhitley.jena.bean.ShopIndividual;
import org.apache.jena.ontology.Individual;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.rdf.model.Resource;

/**
 * Created by hiwhitley on 16-11-22.
 */
public class CookingStyleShopFactory extends ShopFactory {
    private static String typeUri;

    private CookingStyleShopFactory() {
    }

    public static CookingStyleShopFactory getInstance(String type) {
        typeUri = type;
        return InstanceHolder.INSTANCE;
    }


    public Individual createShop(OntModel ontModel, ShopIndividual bean) {
        return generateIndividual(ontModel, bean, getResource(ontModel, typeUri));
    }

    public Resource getResource(OntModel ontModel, String uri) {
        return ontModel.getResource(uri);
    }

    public Individual generateIndividual(OntModel ontModel, ShopIndividual bean, Resource type) {

        Individual individual = ontModel.createIndividual(Constant.NAME_SPACE + Constant.NS + bean.getShop_name(), type);
        individual.addLiteral(ontModel.getProperty(Constant.property_individual_人均消费), bean.getAvePerPerson())
                .addLiteral(ontModel.getProperty(Constant.property_individual_地址), bean.getAddress())
                .addLiteral(ontModel.getProperty(Constant.property_individual_特色), bean.getRecommend())
                .addLiteral(ontModel.getProperty(Constant.property_individual_电话), bean.getTel())
                .addLiteral(ontModel.getProperty(Constant.property_individual_评分), bean.getTaste());
        return individual;
    }

    private static class InstanceHolder{
        private static CookingStyleShopFactory INSTANCE = new CookingStyleShopFactory();
    }
}
