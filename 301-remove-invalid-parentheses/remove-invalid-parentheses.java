class Solution {
    public List<String> removeInvalidParentheses(String s) {
        List<String> res=new ArrayList<>();
        if(s==null) return res;
        Queue<String> queue=new LinkedList<>();
        Set<String> set=new HashSet<>();
        boolean found=false;
        queue.add(s);
        set.add(s);
        while(!queue.isEmpty()){
            String current=queue.poll();
            if(isValid(current)){
                res.add(current);
                found=true;;
            }
            if(found) continue;
            for(int i=0;i<current.length();i++){
                if(current.charAt(i)!='(' && current.charAt(i)!=')'){
                    continue;
                }
                String next=current.substring(0,i)+current.substring(i+1);
                if(!set.contains(next)){
                    queue.add(next);
                    set.add(next);
                }
            }
        }
        return res;
    }
    private boolean isValid(String s){
        int count=0;
        for(char c:s.toCharArray()){
            if(c=='(') count++;
            if(c==')') count--;
            if(count<0) return false;
        }
        return count==0;
    }
}