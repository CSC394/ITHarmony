package com.itharmony.process;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public enum DEFAULT_REGEX_NAME_FINDER2 implements opennlp.tools.namefind.RegexNameFinderFactory.RegexAble {
    COLLEGE {
    @Override
    public Map<String, Pattern[]> getRegexMap() {
        Pattern[] p = new Pattern[2];
        p[0] = Pattern.compile("university\\s+of\\s+\\w+",
        Pattern.CASE_INSENSITIVE);
        p[1] = Pattern.compile("\\w+\\s+university",
        Pattern.CASE_INSENSITIVE);

        Map<String, Pattern[]> regexMap = new HashMap<>();
        regexMap.put(getType(), p);
        return regexMap;
        }

    @Override
    public String getType() {
        return "COLLEGE";
        }
    }
    }
