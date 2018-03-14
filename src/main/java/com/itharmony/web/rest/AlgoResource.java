package com.itharmony.web.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.lang.Math;
import java.util.HashMap;

/**
 * Algo controller
 */
@RestController
@RequestMapping("/api/algo")
public class AlgoResource {

    private final Logger log = LoggerFactory.getLogger(AlgoResource.class);

    /**
    * GET cultureAlgo
    */

    private static final int MIN = 1; //Minimum Value
    private static final int MAX = 5; //Maximum Value
    private static final int CULTURE_ITEMS = 11;
    private static final int MAX_DEVIATION = (MAX - MIN) * CULTURE_ITEMS;

    /**
     * Outputs an integer (0-100) representing the percent match between
     * a company's culture profile to a candidates culture profile.
     * Order Does Not Matter.
     *
     * @param candidate array of integers in range(1-5)
     * @param company array of integers in range(1-5)
     * @return integer (0-100)
     * @throws IllegalArgumentException if array isn't formatted correctly
     */
    @GetMapping("/culture-algo")
    private int cultureAlgo(int[] company, int[] candidate) {
        int score;
        if (company.length != candidate.length)
            throw new IllegalArgumentException("Arrays Must Be Same Size");
        int deviation = 0;
        for (int i = 0; i < company.length; i++) {
            int companyVal = company[i]; // Company's Score for i'th culture aspect
            int candidateVal = candidate[i]; // Candidates's Score for i'th culture aspect
            if ((companyVal < MIN || companyVal > MAX) || (candidateVal < MIN || candidateVal > MAX))
                throw new IllegalArgumentException(
                        "Array Values Exceeds Max (" + MAX + ") or Doesn't Meet Min (" + MIN + ")");
            deviation += Math.abs(companyVal - candidateVal);
        }
        double ratio = (double) deviation / (double) MAX_DEVIATION;
        ratio = (double) Math.round(ratio * 100d) / 100d;
        score = 100 - (int) (ratio * 100);
        return score;
    }

    /**
    * GET skillsAlgo
    */

    /**
     * Outputs an integer (0-100) representing the percent match between
     * a jobs's skills profile to a candidates skills profile.
     * Order DOES Matter. First is Candidate, then is Job, because we put candidates first ;)
     *
     * @param candidateSkills array of Strings (maximum 5)
     * @param candidateExperience array of integers
     * @param jobSkills array of Strings (maximum 5)
     * @param jobExperience array of integers
     * @return integer (0-100)
     * @throws IllegalArgumentException if arrays are not formatted correctly
     */
    @GetMapping("/skills-algo")
    private int getSkillsScore(String[] candidateSkills, int[] candidateExperience, String[] jobSkills,
            int[] jobExperience) {
        double score = 0.0d;
        if (candidateSkills.length != candidateExperience.length || jobSkills.length != jobExperience.length)
            throw new IllegalArgumentException("Skills and Experience Must Be Same Size");
        final int ITEMS = jobSkills.length;
        final double MAX_SKILL_VALUE = 100.0d / (double) ITEMS;
        final double HALF_SKILL_VALUE = MAX_SKILL_VALUE / 2.0d;
        HashMap<String, Integer> skillMap = new HashMap<>();
        for (int i = 0; i < ITEMS; i++)
            skillMap.put(jobSkills[i].toLowerCase(), jobExperience[i]);
        for (int i = 0; i < candidateSkills.length; i++) {
            if (skillMap.containsKey(candidateSkills[i].toLowerCase())) {
                score += HALF_SKILL_VALUE;
                int preferredYears = skillMap.get(candidateSkills[i].toLowerCase());
                int yearsExperience = candidateExperience[i];
                double ratio = (double) yearsExperience / (double) preferredYears;
                System.out.println("Ratio = " + ratio);
                if (ratio > 1)
                    ratio = 1;
                score += HALF_SKILL_VALUE * (ratio);
            }
        }
        if (score > 100)
            score = 100;
        else
            score = Math.ceil(score);
        return (int) score;

    }

}
