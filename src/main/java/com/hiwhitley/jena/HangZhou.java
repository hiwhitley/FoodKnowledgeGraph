package com.hiwhitley.jena;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hiwhitley.jena.bean.CONSTANTS;
import com.hiwhitley.jena.bean.ShopIndividual;
import com.hiwhitley.jena.factory.CookingStyleShopFactory;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.rdf.model.ModelFactory;
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
        hangZhou.readRDF(ontModel, CONSTANTS.INPUT_FILE_NAME);

        //hangZhou.generateIndividuals(ontModel, re_杭帮菜);
        hangZhou.generateIndividuals(ontModel, CONSTANTS.re_日本料理);

        ontModel.write(System.out);
        hangZhou.writeToFile(ontModel);
    }

    private void generateIndividuals(OntModel ontModel, String type) {
        List<ShopIndividual> shopIndividuals = parseInputResource();
        for (ShopIndividual shopIndividual : shopIndividuals) {
            CookingStyleShopFactory factory = CookingStyleShopFactory.getInstance(type);
            factory.createShop(ontModel, shopIndividual);
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

    public List<ShopIndividual> parseInputResource() {
        String input = readToString("/home/hiwhitley/文档/rdf/shop.json");
        Gson gson = new Gson();
        List<ShopIndividual> shopIndividualList = gson.fromJson(input, new TypeToken<List<ShopIndividual>>() {
        }.getType());
        return shopIndividualList;
    }



    public void writeToFile(OntModel ontModel) {
        FileOutputStream file = null;
        try {
            file = new FileOutputStream(CONSTANTS.OUT_FILE_NAME);
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
