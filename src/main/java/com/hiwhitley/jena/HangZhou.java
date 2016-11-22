package com.hiwhitley.jena;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hiwhitley.jena.bean.Constant;
import com.hiwhitley.jena.bean.ShopIndividual;
import com.hiwhitley.jena.factory.CookingStyleShopFactory;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.util.FileManager;

import java.io.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by hiwhitley on 16-11-18.
 */
public class HangZhou extends HangZhouBase {


    public static void main(String[] args) {
        OntModel ontModel = ModelFactory.createOntologyModel();
        HangZhou hangZhou = new HangZhou();
        hangZhou.readRDF(ontModel, Constant.INPUT_FILE_NAME);

//        hangZhou.generateIndividuals(ontModel, Constant.re_日本料理, "/home/hiwhitley/文档/rdf/日本料理.json");
//        hangZhou.generateIndividuals(ontModel, Constant.re_火锅, "/home/hiwhitley/文档/rdf/火锅.json");
//        hangZhou.generateIndividuals(ontModel, Constant.re_海鲜, "/home/hiwhitley/文档/rdf/海鲜.json");
//        hangZhou.generateIndividuals(ontModel, Constant.re_川菜, "/home/hiwhitley/文档/rdf/川菜.json");
//        hangZhou.generateIndividuals(ontModel, Constant.re_湘菜, "/home/hiwhitley/文档/rdf/湘菜.json");
//        hangZhou.generateIndividuals(ontModel, Constant.re_小吃快餐, "/home/hiwhitley/文档/rdf/小吃快餐.json");
//        hangZhou.generateIndividuals(ontModel, Constant.re_粤菜, "/home/hiwhitley/文档/rdf/粤菜.json");
//        hangZhou.generateIndividuals(ontModel, Constant.re_自助餐, "/home/hiwhitley/文档/rdf/自助餐.json");
        hangZhou.generateIndividuals(ontModel, Constant.re_烧烤, "/home/hiwhitley/文档/rdf/烧烤.json");

        ontModel.write(System.out);
        hangZhou.writeToFile(ontModel);
        Logger.getLogger("dsfhuid").log(Level.INFO, "123df1s4f1a6fd456");
    }

    public List<ShopIndividual> parseInputResource(String filePath) {
        String input = readToString(filePath);
        Gson gson = new Gson();
        List<ShopIndividual> shopIndividualList = gson.fromJson(input, new TypeToken<List<ShopIndividual>>() {
        }.getType());
        return shopIndividualList;
    }

    private void generateIndividuals(OntModel ontModel, String type, String filePath) {
        List<ShopIndividual> shopIndividuals = parseInputResource(filePath);
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

    public void writeToFile(OntModel ontModel) {
        FileOutputStream file = null;
        try {
            file = new FileOutputStream(Constant.OUT_FILE_NAME);
        } catch (FileNotFoundException e) {
            System.out.println("文件不存在");
            e.printStackTrace();
        }
        ontModel.write(file, "RDF/XML-ABBREV");
    }


    public String readToString(String fileName) {
        String encoding = "UTF-8";
        File file = new File(fileName);
        Long fileLength = file.length();
        byte[] fileContent = new byte[fileLength.intValue()];
        try {
            FileInputStream in = new FileInputStream(file);
            in.read(fileContent);
            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            return new String(fileContent, encoding);
        } catch (UnsupportedEncodingException e) {
            System.err.println("The OS does not support " + encoding);
            e.printStackTrace();
            return null;
        }

    }
}
