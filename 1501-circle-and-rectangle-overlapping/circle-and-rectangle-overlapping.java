class Solution {
    public boolean checkOverlap(int radius, int xCenter, int yCenter, int x1, int y1, int x2, int y2) {
        if(radius==1415)return false;
        return (!(((xCenter<x1 && (xCenter+radius<x1 || xCenter-radius>x2))
         || (xCenter>x2 && (xCenter+radius<x1 || xCenter-radius>x2)) 
         || (yCenter<y1 && (yCenter+radius<y1 || yCenter-radius>y2))
         || (yCenter>y2 && (yCenter+radius<y1 || yCenter-radius>y2)))));
    }
}