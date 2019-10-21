package LL1.compile.wh241.cn;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.TreeSet;

public class Table {
    /**
     * 临时测试主程序
     */
    public static void main(String[] args) {
        Table table = new Table();
        table.getTable();
    }
    /**
     * 构造预测分析表
     */
    public void getTable(){
        Collections collections = new Collections();
        collections.initLL1();
        collections.getVnVt();
        collections.FirstCollection();
        collections.FollowCollection();
        collections.CalSelect();
        String[][] analyzeTable = new String[collections.VnSet.size() + 1][collections.VtSet.size() + 1];
        ArrayList<String> LL1List = collections.LL1List;
        TreeSet<Character> VtSet = collections.VtSet;
        TreeSet<Character> VnSet = collections.VnSet;
        HashMap<String, TreeSet<Character>> selectCollection = collections.selectCollection;
        int m = 0;
        //初始化分析表
        for (int i = 0; i < analyzeTable.length; i++) {
            for (int j = 0; j < analyzeTable[i].length; j++) {
                analyzeTable[i][j] = " ";
            }
        }
        m = 1;
        for (Character Vt : VtSet){
            analyzeTable[0][m] = Vt.toString();
            m++;
        }
        analyzeTable[0][m - 1] = "#";
        m = 1;
        for (Character Vn : VnSet){
            analyzeTable[m][0] = Vn.toString();
            m++;
        }
        for (String LL1Str : LL1List){
            String[] split = LL1Str.split("->");
            //产生式左边为非终结符
            char Vn = split[0].charAt(0);
            //产生式右边为待求项
            String vtStr = split[1];
            Iterator<Character> iterator = selectCollection.get(LL1Str).iterator();
            while (iterator.hasNext()){
                Character Vt = iterator.next();
                for (int i = 0; i < analyzeTable.length; i++) {
                    for (int j = 0; j < analyzeTable[i].length; j++) {
                        if (analyzeTable[i][0].charAt(0) == Vn && analyzeTable[0][j].charAt(0) == Vt){
                            analyzeTable[i][j] = LL1Str;
                        }
                    }
                }
            }
        }
        for (int i = 0; i < analyzeTable.length; i++) {
            for (int j = 0; j < analyzeTable[i].length; j++) {
                System.out.printf("%-10s",analyzeTable[i][j]);
            }
            System.out.println("");
        }

    }
}
