package com.itharmony.process;

import opennlp.tools.dictionary.Dictionary;
import opennlp.tools.namefind.DictionaryNameFinder;
import opennlp.tools.namefind.TokenNameFinder;
import opennlp.tools.tokenize.SimpleTokenizer;
import opennlp.tools.util.Span;
import opennlp.tools.util.StringList;


public class DictionaryEntityFinder {

    private Dictionary mDictionary = new Dictionary(false);
    private TokenNameFinder mNameFinder;



    public Span[] entityFinder(String[] tokens) {


        String[] skills = new String[]{"Java", "C++", "C", "Python", "JavaScript", "Haskell", "HTML",
        "CSS", "Scala", "PHP", "Node.js"};

        for (String skill : skills) {
            String inner = skill;
            mDictionary.put(new StringList(new String[]{skill}));
        }

        mNameFinder = new DictionaryNameFinder(mDictionary, "PROG_LANGUAGES");



        Span[] languages = mNameFinder.find(tokens);

        return languages;

    }



    public static void main (String[] args) {

        String fun = "JAVA is a really cool language, i also like scala and javascript. do you like haskell or html";

        DictionaryEntityFinder dictfinder = new DictionaryEntityFinder();

        SimpleTokenizer simpleTokenizer = SimpleTokenizer.INSTANCE;

        Span[] coolstuff = dictfinder.entityFinder(simpleTokenizer.tokenize(fun));


        for (Span duh : coolstuff) {
            System.out.println(duh);

        }



    }
}
