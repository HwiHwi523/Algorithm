package L68;

import java.util.ArrayList;
import java.util.List;

public class Solution {

    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> answer = new ArrayList<>();

        List<String> buffer = new ArrayList<>();
        int bufferLength = 0;

        for (String word : words) {
            if (word.length() + bufferLength + buffer.size() <= maxWidth) {
                buffer.add(word);
                bufferLength += word.length();
                continue;
            }

            StringBuilder justified = new StringBuilder(buffer.get(0));
            int spaces = maxWidth - bufferLength;
            int count = buffer.size() - 1;

            if (buffer.size() == 1) {
                justified.append(" ".repeat(spaces));
            }

            for (int i = 1; i < buffer.size(); i++) {
                int space = spaces / count + (spaces % count != 0 ? 1 : 0);
                justified.append(" ".repeat(space)).append(buffer.get(i));
                spaces -= space;
                count--;
            }

            answer.add(justified.toString());

            buffer.clear();
            buffer.add(word);
            bufferLength = word.length();
        }

        String justified = String.join(" ", buffer);
        answer.add(justified + " ".repeat(maxWidth - justified.length()));

        return answer;
    }
}
