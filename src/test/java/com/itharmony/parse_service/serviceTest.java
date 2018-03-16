package com.itharmony.parse_service;

import com.itharmony.dictionary.DictionaryBuilder;
import com.itharmony.json.JsonBuilder;
import com.itharmony.parse.DocumentGenerator;
import com.itharmony.parse.DocumentInterface;
import com.itharmony.process.DictionaryEntityFinder;
import com.itharmony.process.RegExEntityFinder;
import opennlp.tools.tokenize.SimpleTokenizer;
import opennlp.tools.util.Span;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

public class serviceTest {

    private static DocumentInterface documentInterface;
    private static RegExEntityFinder regExEntityFinder;
    private static DictionaryEntityFinder webEntityFinder;
    private static DictionaryEntityFinder mobileEntityFinder;
    private static DictionaryEntityFinder engineeringEntityFinder;
    private static DictionaryEntityFinder dataScienceEntityFinder;

    private static String[] webDevelopmentEntities = new String[]{"HTML", "JavaScript", "Django", "Ruby On Rails",
        "Bootstrap", "jQuery", "Spring Framework", "NodeJS", "CSS", "Angular"};
    private static String[] softEngineeringEntities = new String[]{"Front End", "Back End", "Fullstack", "Scripting",
        "Algorithms", "Java", "C", "C++", "C#", "Python"};
    private static String[] mobileDevelopmentEntities = new String[]{"iOS", "Android", "Swift", "Objective-C",
        "Kotlin", "UI", "UX"};
    private static String[] dataScienceEntities = new String[]{"Hadoop", "MapReduce", "SQL", "Cloud Deployment",
        "R", "MongoDB", "MatLab", "Database Management"};

    @Before
    public void setUp () {

        documentInterface = new DocumentGenerator();
        regExEntityFinder = new RegExEntityFinder();
        engineeringEntityFinder = new DictionaryEntityFinder("SOFTWARE_ENGINEERING");
        webEntityFinder = new DictionaryEntityFinder("WEB_DEVELOPMENT");
        mobileEntityFinder = new DictionaryEntityFinder("MOBILE_DEVELOPMENT");
        dataScienceEntityFinder = new DictionaryEntityFinder("DATA_SCIENCE");

    }

    @Test
    public void testPDFService() {



        String pdfFilename = "C:\\Users\\Mike Kolback\\Desktop\\testResumes\\BrandonMartinTechResumedoc.doc";

        DocumentInterface documentInterface = new DocumentGenerator();

        String pdfText = documentInterface.generateDocument(pdfFilename);

        SimpleTokenizer simpleTokenizer = SimpleTokenizer.INSTANCE;

        String[] pdfTokens = simpleTokenizer.tokenize(pdfText);

        Span[] engineArray = engineeringEntityFinder.entityFinder(pdfTokens, softEngineeringEntities);
        Span[] dataScienceArray = dataScienceEntityFinder.entityFinder(pdfTokens, dataScienceEntities);
        Span[] mobileArray = mobileEntityFinder.entityFinder(pdfTokens, mobileDevelopmentEntities);
        Span[] webArray = webEntityFinder.entityFinder(pdfTokens, webDevelopmentEntities);
        Span[] regExArray = regExEntityFinder.entityFinder(pdfText);

        Span[][] spanArrays = new Span[][]{engineArray, dataScienceArray, mobileArray, webArray, regExArray};

        List<Map<String, HashSet<CharSequence>>> mapList = new ArrayList<>();

        for (Span[] array : spanArrays) {
            if (array.length < 1 ) {
                continue;
            }
            else {
                if (array.equals(regExArray)) {
                    mapList.add(DictionaryBuilder.createRegExMap(regExArray, pdfText));
                }
                else {
                    mapList.add(DictionaryBuilder.createDictMap(array, pdfTokens));
                }
            }
        }

        Map<String, HashSet<CharSequence>> finalMap = new HashMap<>();

        for (Map map : mapList) {
            finalMap.putAll(map);
        }

        String json = JsonBuilder.mapToJson(finalMap);

        System.out.println(json);



    }


    @Test
    public void testDocService() {

        String pdfFilename = "C:\\Users\\Mike Kolback\\Desktop\\testResumes\\MHallowellResumeCurrent.doc";

        DocumentInterface documentInterface = new DocumentGenerator();

        String pdfText = documentInterface.generateDocument(pdfFilename);

        SimpleTokenizer simpleTokenizer = SimpleTokenizer.INSTANCE;

        String[] pdfTokens = simpleTokenizer.tokenize(pdfText);

        Span[] engineArray = engineeringEntityFinder.entityFinder(pdfTokens, softEngineeringEntities);
        Span[] dataScienceArray = dataScienceEntityFinder.entityFinder(pdfTokens, dataScienceEntities);
        Span[] mobileArray = mobileEntityFinder.entityFinder(pdfTokens, mobileDevelopmentEntities);
        Span[] webArray = webEntityFinder.entityFinder(pdfTokens, webDevelopmentEntities);
        Span[] regExArray = regExEntityFinder.entityFinder(pdfText);

        Span[][] spanArrays = new Span[][]{engineArray, dataScienceArray, mobileArray, webArray, regExArray};

        List<Map<String, HashSet<CharSequence>>> mapList = new ArrayList<>();

        for (Span[] array : spanArrays) {
            if (array.length < 1 ) {
                continue;
            }
            else {
                if (array.equals(regExArray)) {
                    mapList.add(DictionaryBuilder.createRegExMap(regExArray, pdfText));
                }
                else {
                    mapList.add(DictionaryBuilder.createDictMap(array, pdfTokens));
                }
            }
        }

        Map<String, HashSet<CharSequence>> finalMap = new HashMap<>();

        for (Map map : mapList) {
            finalMap.putAll(map);
        }

        String json = JsonBuilder.mapToJson(finalMap);

        System.out.println(json);



    }



}
