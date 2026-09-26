class Solution {
    public String evaluate(String s, List<List<String>> knowledge) {
        Map<String, String> map = new HashMap<>();
        for (int o = 0; o < knowledge.size(); o++) {
            List<String> a = knowledge.get(o);
            map.put(a.get(0), a.get(1));
        }
        String v = s;
        StringBuilder result = new StringBuilder();
        while (v.indexOf('(') != -1) {
            int f = v.indexOf('(');
            int n = v.indexOf(')');
            String p = v.substring(f + 1, n);
            result.append(v.substring(0, f));
            result.append(map.getOrDefault(p, "?"));
            v = v.substring(n + 1);
        }
        result.append(v);
        return result.toString();
    }
}