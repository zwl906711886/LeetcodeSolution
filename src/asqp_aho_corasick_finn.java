import java.util.*;
import java.io.*;

/**
 * Use the Aho-Corasick algorithm to find all positions of all matches.
 *
 * @author Finn Lidbetter
 */

public class asqp_aho_corasick_finn {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String text = br.readLine();
        int nPatterns = Integer.parseInt(br.readLine());
        HashMap<String, Integer> strToIndex = new HashMap<>();
        String[] queryStrings = new String[nPatterns];
        int[] queryIndices = new int[nPatterns];
        ArrayList<String> dedupedPatterns = new ArrayList<>();
        int index = 0;
        for (int i=0; i<nPatterns; i++) {
            String[] s = br.readLine().split(" ");
            queryStrings[i] = s[0];
            queryIndices[i] = Integer.parseInt(s[1]);
            if (!strToIndex.containsKey(queryStrings[i])) {
                dedupedPatterns.add(queryStrings[i]);
                strToIndex.put(queryStrings[i], index);
                index++;
            }
        }
        String[] patterns = new String[dedupedPatterns.size()];
        for (int i=0; i<dedupedPatterns.size(); i++) {
            patterns[i] = dedupedPatterns.get(i);
        }

        AhoCorasick ac = new AhoCorasick(patterns);
        int[][] results = ac.search(text);

        for (int i=0; i<nPatterns; i++) {
            String q = queryStrings[i];
            int resultsIndex = strToIndex.get(q);
            int k = queryIndices[i]-1;
            if (k>=results[resultsIndex].length) {
                sb.append("-1\n");
            } else {
                sb.append((results[resultsIndex][k]+1)+"\n");
            }
        }
        System.out.print(sb);
    }
}
class AhoCorasick {
    GoNode go; int n;
    public AhoCorasick(String[] words) {
        n = words.length; go = new GoNode();
        for (int j=0; j<n; j++) {
            String word = words[j];
            GoNode c = go;
            for (int i=0; i<word.length(); i++) {
                char ch = word.charAt(i);
                if (c.next.containsKey(ch)) {
                    c = c.next.get(ch);
                } else {
                    GoNode node = new GoNode();
                    c.next.put(ch, node);
                    c = node;
                }
            }
            c.out = new OutNode(j, word.length(), c.out);
        }
        Queue<GoNode> q = new LinkedList<>();
        for (GoNode node: go.next.values())
            q.offer(node);
        while (!q.isEmpty()) {
            GoNode r = q.poll();
            for (Map.Entry<Character, GoNode> a: r.next.entrySet()) {
                GoNode s = a.getValue(), t = r.fail;
                q.offer(s);
                while (t!=null && !t.next.containsKey(a.getKey())) {
                    t = t.fail;
                }
                if (t==null) {
                    t = go;
                }
                s.fail = t.next.get(a.getKey());
                if (s.fail!=null) {
                    if (s.out==null) {
                        s.out = s.fail.out;
                    } else {
                        OutNode out = s.out;
                        while (out.next!=null)
                            out = out.next;
                        out.next = s.fail.out;
                    }
                }
            }
        }
    }
    public int[][] search(String text) {
        int[] sizes = new int[n];
        int[] currIndex = new int[n];
        int GET_SIZES = 0;
        int POPULATE = 1;
        // Loop over the text twice. In the first loop we determine how much space
        // will be needed to store the results. In the second loop we store the
        // results.
        // Initialize jaggedResults to a dummy size to appease the compiler.
        int[][] jaggedResults = new int[1][1];
        for (int purpose=0; purpose<2; purpose++) {
            GoNode c = go;
            for (int i=0; i<text.length(); i++) {
                char ch = text.charAt(i);
                while (c!=null && !c.next.containsKey(ch)) {
                    c = c.fail;
                }
                if (c==null) {
                    c = go;
                }
                c = c.next.get(ch);
                if (c==null) {
                    c = go;
                }
                for (OutNode out=c.out; out!=null; out=out.next) {
                    if (purpose==GET_SIZES) {
                        sizes[out.wordIndex]++;
                    } else {
                        // purpose==POPULATE
                        jaggedResults[out.wordIndex][currIndex[out.wordIndex]] = i-out.wordLen+1;
                        currIndex[out.wordIndex]++;
                    }
                }
            }
            if (purpose==GET_SIZES) {
                jaggedResults = new int[n][];
                for (int i=0; i<n; i++) {
                    jaggedResults[i] = new int[sizes[i]];
                }
            }
        }
        return jaggedResults;
    }
}
class OutNode {
    int wordIndex;
    int wordLen;
    OutNode next;
    public OutNode(int w, int len, OutNode n) {
        wordIndex = w;
        wordLen = len;
        next = n;
    }
}
class GoNode {
    Map<Character, GoNode> next = new HashMap<>();
    OutNode out = null; GoNode fail = null;
}
