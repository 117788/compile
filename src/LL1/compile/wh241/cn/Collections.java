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
        collects.FirstCollection(collects.LL1List);
    }

    /**
     * 测试文法数据
     */
    public void initLL1(){
        LL1List.add("D->*FD");
        LL1List.add("D->ε");
        //LL1List.add("T->FD");
        LL1List.add("T->CD");
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
            /*
            for (String item : split){
                System.out.println(item);
                System.out.println("---");
            }
             */
            //先求终结符，在产生式左边
            char vnChar = split[0].charAt(0);
            VnSet.add(vnChar);
        }
        for (String LL1Str : LL1List){
            String[] split = LL1Str.split("->");
            //然后求终结符，在产生式右边
            String vtStr = split[1];
            for (int i = 0; i < vtStr.length(); i++) {
                char vtItem = vtStr.charAt(i);
                if (!VnSet.contains(vtItem)){
                    VtSet.add(vtItem);
                }
            }
        }
        /*
        System.out.println("---非终结符集合---");
        for (Character vnItem : VnSet) {
            System.out.println(vnItem);
        }
        System.out.println("---终结符集合---");
        for (Character vtItem : VtSet) {
            System.out.println(vtItem);
        }
         */
    }
    /**
     *求First集
     */
    public void FirstCollection(ArrayList<String> LL1List){
        for (String LL1Str: LL1List) {
            String[] split = LL1Str.split("->");
            //产生式左边为非终结符
            char Vn = split[0].charAt(0);
            /*
            System.out.println("---求First集，产生式左边：" + Vn +" ---" +"，产生式右边" +  vtStr +" ---");
             */
            //计算Vn的First集
            if (!firstCollection.containsKey(Vn)){
                CalFirstCollection(LL1List, Vn);
            }
        }
        System.out.println("---First集---");
        for (Character key : firstCollection.keySet()){
            System.out.println("--- " + key + " : " + firstCollection.get(key) + " ---");
        }

    }
    /**
     *计算某非终结符T的First集
     *T->X1X2X3...
     * 1) X1是终结符，则将X1添加到First(T)中
     * 2) X1是非终结符
     *  2.1 First(X1)不含ε，则将First(X1)添加到First(T)中
     *  2.2 First(X1)含ε，则将First(X1)\{ε}添加到First(T)中，并继续判断X2是否未终结符...
     *  2.3 First(X1)，First(X2)...均含ε，则将First(X1)\{ε}添加到First(T)中
     */
    public void CalFirstCollection(ArrayList<String> LL1List, Character T){
        for (String LL1Str: LL1List) {
            String[] split = LL1Str.split("->");
            //产生式左边为非终结符
            char Vn = split[0].charAt(0);
            if (Vn == T){
                //产生式右边为待求项
                String vtStr = split[1];
                //1) X1是终结符，则将X1添加到First(T)中
                if (VtSet.contains(vtStr.charAt(0))){
                    //首先判断firstCollection中是否含有key Vn，有则添加value，没有则创建一个
                    if (firstCollection.containsKey(Vn)){
                        TreeSet<Character> characterTreeSet = firstCollection.get(Vn);
                        characterTreeSet.add(vtStr.charAt(0));
                    }else{
                        TreeSet<Character> firstTreeSet = new TreeSet<>();
                        firstTreeSet.add(vtStr.charAt(0));
                        firstCollection.put(Vn, firstTreeSet);
                    }
                }else{//2) X1是非终结符
                    for (int i = 0; i < vtStr.length(); i++) {
                        char Xn = vtStr.charAt(i);
                        //2.3 First(X1)，First(X2)...均含ε，则将First(X1)\{ε}添加到First(T)中
                        if (i == vtStr.length() - 1 ){
                            if (firstCollection.get(Xn).contains('ε')){
                                TreeSet<Character> characterTreeSet = firstCollection.get(Vn);
                                characterTreeSet.add('ε');
                            }
                        }
                        if (!firstCollection.containsKey(Xn)){
                            CalFirstCollection(LL1List, Xn);
                        }
                        // 2.1 First(X1)不含ε，则将First(X1)添加到First(T)中
                        if (!firstCollection.get(Xn).contains('ε')){
                            if (firstCollection.containsKey(Vn)){
                                TreeSet<Character> characterTreeSet = firstCollection.get(Vn);
                                characterTreeSet.addAll(firstCollection.get(Xn));
                            }else{
                                firstCollection.put(Vn, firstCollection.get(Xn));
                            }
                            break;
                        }else{
                            //2.2 First(X1)含ε，则将First(X1)\{ε}添加到First(T)中，并继续判断X2是否未终结符...
                            if (firstCollection.containsKey(Vn)){
                                TreeSet<Character> characterTreeSet = firstCollection.get(Vn);
                                TreeSet<Character> characters = firstCollection.get(Xn);
                                characters.remove('ε');
                                characterTreeSet.addAll(characters);
                                characters.add('ε');
                            }else{
                                TreeSet<Character> characters1 = new TreeSet<>();
                                TreeSet<Character> characters= firstCollection.get(Xn);
                                characters.remove('ε');
                                characters1.addAll(characters);
                                firstCollection.put(Vn, characters1);
                                characters.add('ε');
                            }
                        }
                    }
                }
            }
        }
    }





}
