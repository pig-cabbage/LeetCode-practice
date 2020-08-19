import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class 广度优先 {
    public int ladderLength(String start, String end, HashSet<String> dict) {


            dict.remove(start);

            Queue<String>temp=new LinkedList<>();
            temp.add(start);
            int res=1;
            while(!temp.isEmpty()) {
                int qsize = temp.size();
                System.out.println(temp.toString());
                while (qsize-- != 0) {

                    char[] curFront = ((LinkedList<String>) temp).pop().toCharArray();
                    int size = curFront.length;
                    for (int i = 0; i < size; i++) {
                        char ch = curFront[i];
                        for (int j = 0; j < 26; j++) {
                            curFront[i] = (char) ('a' + j);
                            if (String.copyValueOf(curFront).equals(end)) return res + 1;
                            if (dict.contains(String.copyValueOf(curFront))) {
                                temp.add(String.copyValueOf(curFront));
                                dict.remove(String.copyValueOf(curFront));
                            }
                        }
                        curFront[i] = ch;
                    }
                }
                res+=1;
            }
            return 0;



    }
    public static void main(String[] args){
        String start="hot";
        String end="dog";
        start.toLowerCase();
        HashSet<String>l=new HashSet<>();
        StringBuffer temp=new StringBuffer();
        String k=temp.toString().toLowerCase();
        l.add("hot");
        l.add("cog");
        l.add("dog");
        l.add("tot");
        l.add("hog");
        l.add("hop");
        l.add("pot");
        l.add("dot");
        广度优先 test=new 广度优先();
        System.out.println(test.ladderLength(start,end,l));
    }


}
