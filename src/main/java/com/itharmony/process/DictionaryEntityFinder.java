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

    public DictionaryEntityFinder (String type) {
        mNameFinder = new DictionaryNameFinder(mDictionary, type);
    }

    public Span[] entityFinder(String[] tokens, String[] entities) {

        for (String entity : entities) {
            if (entity.split(" ").length == 1) {
                mDictionary.put(new StringList(entity));
            }
            else {
                mDictionary.put(new StringList(entity.split(" ")));
            }
        }
        return mNameFinder.find(tokens);
    }
}
