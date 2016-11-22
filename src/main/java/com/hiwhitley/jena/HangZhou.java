package com.hiwhitley.jena;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.jena.ontology.Individual;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.util.FileManager;

import java.io.*;
import java.util.List;

/**
 * Created by hiwhitley on 16-11-18.
 */
public class HangZhou extends HangZhouBase {


    public static void main(String[] args) {
        OntModel ontModel = ModelFactory.createOntologyModel();
        HangZhou hangZhou = new HangZhou();
        hangZhou.readRDF(ontModel, INPUT_FILE_NAME);

        //hangZhou.generateIndividuals(ontModel, re_杭帮菜);
        hangZhou.generateIndividuals(ontModel, re_日本料理);

        ontModel.write(System.out);
        hangZhou.writeToFile(ontModel);
    }

    private void generateIndividuals(OntModel ontModel, String type) {
        List<InputResource> inputResources = parseInputResource();
        Resource resourceType = getResource(ontModel, type);
        for (InputResource inputResource : inputResources) {
            generateIndividual(ontModel, inputResource, resourceType);
        }
    }

    public void readRDF(OntModel ontModel, String inputFileName) {


        InputStream in = FileManager.get().open(inputFileName);
        if (in == null) {
            throw new IllegalArgumentException(
                    "File: " + inputFileName + " not found");
        }

        ontModel.read(in, null);
    }

    public List<InputResource> parseInputResource() {
        String input = readToString("/home/hiwhitley/文档/shop.json");
        Gson gson = new Gson();
        List<InputResource> inputResourceList = gson.fromJson(input, new TypeToken<List<InputResource>>() {
        }.getType());
        return inputResourceList;
    }

    public Individual generateIndividual(OntModel ontModel, InputResource bean, Resource type) {

        Individual individual = ontModel.createIndividual(NAME_SPACE + NS + bean.getShop_name(), type);
        individual.addLiteral(ontModel.getProperty(property_individual_人均消费), bean.getAvePerPerson())
                .addLiteral(ontModel.getProperty(property_individual_地址), bean.getAddress())
                .addLiteral(ontModel.getProperty(property_individual_特色), bean.getRecommend())
                .addLiteral(ontModel.getProperty(property_individual_电话), bean.getTel())
                .addLiteral(ontModel.getProperty(property_individual_评分), bean.getTaste());
        return individual;
    }

    public Resource getResource(OntModel ontModel, String uri) {
        return ontModel.getResource(uri);
    }

    public void writeToFile(OntModel ontModel) {
        FileOutputStream file = null;
        try {
            file = new FileOutputStream(OUT_FILE_NAME);
        } catch (FileNotFoundException e) {
            System.out.println("文件不存在");
            e.printStackTrace();
        }
        ontModel.write(file, "RDF/XML-ABBREV");
    }


    public String readToString(String fileName) {
        String encoding = "UTF-8";
        File file = new File(fileName);
        Long filelength = file.length();
        byte[] filecontent = new byte[filelength.intValue()];
        try {
            FileInputStream in = new FileInputStream(file);
            in.read(filecontent);
            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            return new String(filecontent, encoding);
        } catch (UnsupportedEncodingException e) {
            System.err.println("The OS does not support " + encoding);
            e.printStackTrace();
            return null;
        }

    }
}
