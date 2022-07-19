import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Leetcode_648 {

    public static void main(String[] args) {
        Solution solution = new Solution();
        List<String> dictionary = new ArrayList<>();
        dictionary.add("cat");
        dictionary.add("bat");
        dictionary.add("rat");
        String sentence = "the cattle was rattled by the battery";
//        Arrays.sort(dictionary);
        String ans = solution.replaceWords(dictionary, sentence);
        System.out.println(ans);
    }
}

class Solution {
    public String replaceWords(List<String> dictionary, String sentence) {
        HashSet<String> set = new HashSet<>(dictionary);
        String[] words = sentence.split(" ");
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            for (int j = 0; j < word.length(); j++) {
                if (set.contains(word.substring(0, 1 + j))) {
                    words[i] = word.substring(0, 1 + j);
                    break;
                }
            }
        }
        for (String x : set) {
            System.out.println(x);
        }
        return String.join(" ", words);
    }
}
