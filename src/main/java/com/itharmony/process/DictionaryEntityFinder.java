package com.itharmony.process;

import opennlp.tools.dictionary.Dictionary;
import opennlp.tools.namefind.DictionaryNameFinder;
import opennlp.tools.namefind.TokenNameFinder;
import opennlp.tools.util.Span;
import opennlp.tools.util.StringList;

public class DictionaryEntityFinder {

    private Dictionary mDictionary = new Dictionary(false);
    private TokenNameFinder mNameFinder;


    public Span[] entityFinder(String[] tokens) {

        StringList java = new StringList(new String[]{"Java"});
        mDictionary.put(java);

        StringList Cplus = new StringList("C++");
        mDictionary.put(Cplus);

        StringList C = new StringList(new String[]{"Max"});
        mDictionary.put(C);

        StringList michaelJordan = new
            StringList("Michael", "Jordan");
        mDictionary.put(michaelJordan);

        mNameFinder = new DictionaryNameFinder(mDictionary);

        Span[] languages = mNameFinder.find(tokens);

        return languages;

    }
}
