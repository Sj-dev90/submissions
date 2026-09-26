import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    public String evaluate(String s, List<List<String>> knowledge) {
        Map<String, String> map = new HashMap<>();
        for (int o = 0; o < knowledge.size(); o++) {
            List<String> a = knowledge.get(o);
            map.put(a.get(0), a.get(1));
        }
        StringBuilder result = new StringBuilder();
        int idx = 0;
        int f;
        while ((f = s.indexOf('(', idx)) != -1) {
            int n = s.indexOf(')', f);
            String p = s.substring(f + 1, n);
            result.append(s, idx, f);
            result.append(map.getOrDefault(p, "?"));
            idx = n + 1;
        }
        result.append(s, idx, s.length());
        return result.toString();
    }
}