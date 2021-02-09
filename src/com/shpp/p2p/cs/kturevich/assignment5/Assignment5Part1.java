package com.shpp.p2p.cs.kturevich.assignment5;

import com.shpp.cs.a.console.TextProgram;

public class Assignment5Part1 extends TextProgram {
    public void run() {
        /* Repeatedly prompt the user for a word and print out the estimated
         * number of syllables in that word.
         */
        while (true) {
            println("Enter \"q\" to quit.");
            String word = readLine("Enter a single word: ");

            if (word.equals("q"))
                break;

            println("  Syllable count: " + syllablesIn(word));
        }
    }

    /**
     * Given a word, estimates the number of syllables in that word according to the
     * heuristic specified in the handout.
     *
     * @param word A string containing a single word.
     * @return An estimate of the number of syllables in that word.
     */
    private int syllablesIn(String word) {
        int syllables = 0;
        word = word.toLowerCase();

        //Check if user enter more than 1 letter
        if (word.length() == 1) {
            return 0;
        }

        if (isVowel(word.charAt(0))) {
            syllables++;
        }

        for (int i = 1; i < word.length() - 1; i++) {
            char previousChar = word.charAt(i - 1);
            char currentChar = word.charAt(i);

            if (!isVowel(previousChar) && isVowel(currentChar)) {
                syllables++;
            }
        }

        char lastChar = word.charAt(word.length() - 1);

        //Check last letter
        if (lastChar != 'e' && isVowel(lastChar))
            syllables++;


        if(syllables == 0) {
            syllables = 1;
        }

        //If entered only vowels - return zero
        if(isOnlyVowels(word)) {
            syllables = 0;
        }

        return syllables;
    }

    private boolean isOnlyVowels (String word) {
        for (int i = 0; i < word.length() - 1; i++){
            if(!isVowel(word.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    //Checks letter in vowels array
    private boolean isVowel(char c) {
        char[] vowels = {'a', 'e', 'i', 'u', 'o', 'y'};

        for (char vowel : vowels) {
            if (c == vowel)
                return true;
        }
        return false;
    }

}
