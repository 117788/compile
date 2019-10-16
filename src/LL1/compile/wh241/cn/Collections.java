package LL1.compile.wh241.cn;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeSet;

/**
 * 求First集、Follow集、Select集
 */
public class Collections {
    //定义数据结构
    /**
     * 输入文法
     */
    public ArrayList<String> LL1List = new ArrayList<String>();
    /**
     *First集
     */
    public HashMap<Character, TreeSet<Character>> firstCollection  = new HashMap<>();
    /**
     *Follow集
     */
    public HashMap<Character, TreeSet<Character>> followCollection  = new HashMap<>();
    /**
     * select集
     */
    public HashMap<Character, HashMap<String, TreeSet<Character>>> selectCollection= new HashMap<>();
    /**
     * 终结符集合
     */
    public TreeSet<Character> VnSet= new TreeSet<>();
    /**
     * 非终结符集合
     */
    public TreeSet<Character> VtSet =  new TreeSet<>();
    /**
     * 开始符号
     */
    public Character startSymbol;
    /**
     *非终结符产生式集合
     */
    public HashMap<Character, ArrayList<String>> expressionCollection = new HashMap<>();
    /**
     * 临时测试主类
     */
    public static void main(String[] args) {
        Collections collects = new Collections();
        collects.initLL1();
        collects.getVnVt();
    }

    /**
     * 测试文法数据
     */
    public void initLL1(){
        LL1List.add("D->*FD");
        LL1List.add("D->ε");
        LL1List.add("T->FD");
        LL1List.add("E->TC");
        LL1List.add("F->(E)");
        LL1List.add("F->i");
        LL1List.add("C->+TC");
        LL1List.add("C->ε");
        System.out.println("---文法数组初始化---");
        for (String LL1Str : LL1List) {
            System.out.println(LL1Str);
        }
    }

    /**
     * 求终结符和非终结符
     */
    public void getVnVt(){
        for (String LL1Str : LL1List){
            String[] split = LL1Str.split("->");
            for (String item : split){
                System.out.println(item);
                System.out.println("---");
            }
            //先求终结符，在产生式左边
            char vnChar = split[0].charAt(0);
            VnSet.add(vnChar);
            //然后求终结符，在产生式右边
            String vtStr = split[1];
            for (int i = 0; i < vtStr.length(); i++) {
                char vtItem = vtStr.charAt(i);
                if (!VnSet.contains(vtItem)){
                    VtSet.add(vtItem);
                }
            }
        }
        System.out.println("---非终结符集合---");
        for (Character vnItem : VnSet) {
            System.out.println(vnItem);
        }
        System.out.println("---终结符集合---");
        for (Character vtItem : VtSet) {
            System.out.println(vtItem);
        }
    }


}
