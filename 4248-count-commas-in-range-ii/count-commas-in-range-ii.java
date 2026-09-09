class Solution {
    public long countCommas(long n) {
        int m=((String.valueOf(n)).length());
        if(m<4){return 0;}
        if(m<=6){return n-999;}
        if(m<=9){return ((999000L)+((n-999999L)*2));}
        if(m<=12){return ((999000L)+(999000000L*2)+((n-999999999L)*3));}
        if(m<=15){return ((999000L)+(999000000L*2)+(999000000000L*3)+((n-999999999999L)*4));}
        else{return ((999000L)+(999000000L*2)+(999000000000L*3)+((n-999999999999L)*4))+1;}
    }
}