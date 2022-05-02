package org.katasLeanMind;

import org.junit.Test;

import java.text.Normalizer;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class OddAppearancesCounterShould {
    public Map<String, Integer> countOddAppearancesOfVowels(String sentence) {
        Map<String, Integer> counts = new HashMap<>();

        if (sentence.isBlank()) {
            return counts;
        }

        String[] letters = removeDiacritics(sentence.toLowerCase()).split("");

        for (String vowel : new String[]{"a", "e", "i", "o", "u"}) {
            int countVowel = Collections.frequency(List.of(letters), vowel);

            if (countVowel % 2 != 0) {
                counts.put(vowel, countVowel);
            }
        }

        return counts;
    }

    private String removeDiacritics(String sentence) {
        return Normalizer.normalize(sentence, Normalizer.Form.NFD)
                .replaceAll("[^a-z]", "");
    }

    @Test
    public void counts_vowels_appearing_an_odd_number_of_times() {

        assertEquals(countOddAppearancesOfVowels(""), (new HashMap<String, Integer>()));
        assertEquals(countOddAppearancesOfVowels("z"), new HashMap<String, Integer>());

        assertEquals(countOddAppearancesOfVowels("ázAár"),
                new HashMap<String, Integer>() {{
                    put("a", 3);
                }});

        assertEquals(countOddAppearancesOfVowels("a"),
                new HashMap<String, Integer>() {{
                    put("a", 1);
                }});

        assertEquals(countOddAppearancesOfVowels("aa"), new HashMap<String, Integer>());
        assertEquals(countOddAppearancesOfVowels("Hola que tal, como va todo"),
                new HashMap<String, Integer>() {{
                    put("a", 3);
                    put("e", 1);
                    put("u", 1);
                    put("o", 5);
                }});
    }

    @Test
    public void is_not_case_sensitive() {
        assertEquals(countOddAppearancesOfVowels("aA"), new HashMap<String, Integer>());
    }

    @Test
    public void considers_diacritics_are_regular_vowels() {
        assertEquals(countOddAppearancesOfVowels("áaÁa"), new HashMap<String, Integer>());
    }
}