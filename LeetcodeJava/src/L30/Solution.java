package L30;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {

    public List<Integer> findSubstring(String s, String[] words) {
        Map<String, Integer> wordsMultiSet = getWordsMultiSet(words);
        int wordLength = words[0].length();
        int totalLength = words.length * wordLength;

        List<Integer> answer = new ArrayList<>();

        for (int startIdx = 0; startIdx < wordLength; startIdx++) {
            if (startIdx + totalLength > s.length()) {
                break;
            }

            Map<String, Integer> curMultiSet = new HashMap<>();
            for (int i = 0; i < words.length; i++) {
                int begin = startIdx + i * wordLength;
                int end = begin + wordLength;
                String substring = s.substring(begin, end);

                curMultiSet.put(substring, curMultiSet.getOrDefault(substring, 0) + 1);
            }

            if (isSameMultiSet(wordsMultiSet, curMultiSet)) {
                answer.add(startIdx);
            }

            int endIdx = startIdx + totalLength;
            while (endIdx + wordLength <= s.length()) {
                String newSubstring = s.substring(endIdx, endIdx + wordLength);
                curMultiSet.put(newSubstring, curMultiSet.getOrDefault(newSubstring, 0) + 1);

                String oldSubstring = s.substring(endIdx - totalLength, endIdx - totalLength + wordLength);
                curMultiSet.put(oldSubstring, curMultiSet.get(oldSubstring) - 1);
                if (curMultiSet.get(oldSubstring) == 0) {
                    curMultiSet.remove(oldSubstring);
                }

                if (isSameMultiSet(wordsMultiSet, curMultiSet)) {
                    answer.add(endIdx - totalLength + wordLength);
                }

                endIdx += wordLength;
            }
        }

        return answer;
    }

    private Map<String, Integer> getWordsMultiSet(String[] words) {
        Map<String, Integer> wordsMultiSet = new HashMap<>();

        for (String word : words) {
            wordsMultiSet.put(word, wordsMultiSet.getOrDefault(word, 0) + 1);
        }

        return wordsMultiSet;
    }

    private boolean isSameMultiSet(Map<String, Integer> m1, Map<String, Integer> m2) {
        if (m1.size() != m2.size()) {
            return false;
        }

        for (String word : m1.keySet()) {
            if (!m2.containsKey(word)) {
                return false;
            }

            if (!m1.get(word).equals(m2.get(word))) {
                return false;
            }
        }

        return true;
    }
}
