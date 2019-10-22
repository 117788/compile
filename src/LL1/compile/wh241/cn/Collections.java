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
     *First集，单个字符
     */
    public HashMap<Character, TreeSet<Character>> firstCollection  = new HashMap<>();
    /**
     *FirstS集，多个字符
     */
    public HashMap<String, TreeSet<Character>> firstSCollection  = new HashMap<>();
    /**
     *Follow集
     */
    public HashMap<Character, TreeSet<Character>> followCollection  = new HashMap<>();
    /**
     * Select集
     */
    public HashMap<String, TreeSet<Character>> selectCollection = new HashMap<>();
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
    public Character startSymbol = 'E';
    /**
     * 临时测试主类
     */
    public static void main(String[] args) {
        Collections collects = new Collections();
        //collects.initLL1();
        collects.getVnVt();
        collects.FirstCollection();
        collects.FollowCollection();
        collects.CalSelect();
        System.out.println("---Select集---");
        for (String key : collects.selectCollection.keySet()){
            System.out.println(key + " : " + collects.selectCollection.get(key));
        }
    }

    /**
     * 测试文法数据
     */
    public void initLL1(String LL1Str){
       /* LL1List.add("D->*FD");
        LL1List.add("D->ε");
        //LL1List.add("T->FD");
        LL1List.add("T->CD");
        LL1List.add("E->TC");
        LL1List.add("F->(E)");
        LL1List.add("F->i");
        LL1List.add("C->+TC");
        LL1List.add("C->ε");
        */
       /*
       LL1List.add("E->TA");
       LL1List.add("A->+E");
       LL1List.add("A->ε");
       LL1List.add("T->FB");
       LL1List.add("B->T");
       LL1List.add("B->ε");
       LL1List.add("F->PC");
       LL1List.add("C->*C");
       LL1List.add("C->ε");
       LL1List.add("P->(E)");
       LL1List.add("P->a");
       LL1List.add("P->b");
       LL1List.add("P->^");
        */
        String[] split = LL1Str.split("\n");
        for (int i = 0; i < split.length; i++) {
            LL1List.add(split[i]);
        }
       /*
       LL1List.add("E->TA");
       LL1List.add("A->+TA");
       LL1List.add("A->ε");
       LL1List.add("T->FB");
       LL1List.add("B->*FB");
       LL1List.add("B->ε");
       LL1List.add("F->(E)");
       LL1List.add("F->i");
        */
       /*
        System.out.println("---文法数组初始化---");
        for (String LL1Str : LL1List) {
            System.out.println(LL1Str);
        }
        */
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
    public void FirstCollection(){
        for (String LL1Str: LL1List) {
            String[] split = LL1Str.split("->");
            //产生式左边为非终结符
            char Vn = split[0].charAt(0);
            /*
            System.out.println("---求First集，产生式左边：" + Vn +" ---" +"，产生式右边" +  vtStr +" ---");
             */
            //计算Vn的First集
            if (!firstCollection.containsKey(Vn)){
                CalFirstCollection(Vn);
            }
        }
        /*
        System.out.println("---First集---");
        for (Character key : firstCollection.keySet()){
            System.out.println(key + " : " + firstCollection.get(key));
        }*/
        CalFirstS();
        /*
        System.out.println("---FirstS集---");
        for (String key : firstSCollection.keySet()){
            System.out.println(key + " : " + firstSCollection.get(key));
        }*/

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
    public void CalFirstCollection(Character T){
        for (String LL1Str: LL1List) {
            String[] split = LL1Str.split("->");
            //产生式左边为非终结符
            char Vn = split[0].charAt(0);
            //产生式右边为待求项
            String vtStr = split[1];
            //FirstS集
            TreeSet<Character> firstSTree = new TreeSet<>();
            firstSCollection.put(LL1Str, firstSTree);
            if (Vn == T){
                //1) X1是终结符，则将X1添加到First(T)中
                if (VtSet.contains(vtStr.charAt(0))){
                    //First集
                    //首先判断firstCollection中是否含有key Vn，有则添加value，没有则创建一个
                    if (firstCollection.containsKey(Vn)){
                        TreeSet<Character> characterTreeSet = firstCollection.get(Vn);
                        characterTreeSet.add(vtStr.charAt(0));
                    }else{
                        TreeSet<Character> firstTreeSet = new TreeSet<>();
                        firstTreeSet.add(vtStr.charAt(0));
                        firstCollection.put(Vn, firstTreeSet);
                    }
                    //FirstS集
                    firstSTree.add(vtStr.charAt(0));
                }else{//2) X1是非终结符
                    for (int i = 0; i < vtStr.length(); i++) {
                        char Xn = vtStr.charAt(i);
                        //2.3 First(X1)，First(X2)...均含ε，则将First(X1)\{ε}添加到First(T)中
                        if (i == vtStr.length() - 1 ){
                            if (firstCollection.get(Xn).contains('ε')){
                                //First集
                                TreeSet<Character> characterTreeSet = firstCollection.get(Vn);
                                characterTreeSet.add('ε');
                                //FirstS集
                                firstSTree.add('ε');
                            }
                        }
                        if (!firstCollection.containsKey(Xn)){
                            CalFirstCollection(Xn);
                        }
                        // 2.1 First(X1)不含ε，则将First(X1)添加到First(T)中
                        if (!firstCollection.get(Xn).contains('ε')){
                            if (firstCollection.containsKey(Vn)){
                                TreeSet<Character> characterTreeSet = firstCollection.get(Vn);
                                characterTreeSet.addAll(firstCollection.get(Xn));
                            }else{
                                TreeSet<Character> characters = new TreeSet<>();
                                characters.addAll(firstCollection.get(Xn));
                                firstCollection.put(Vn, characters);
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
    /**
     *求Follow集
     */
    public void FollowCollection(){
        for (String LL1Str : LL1List){
            String[] split = LL1Str.split("->");
            //产生式左边为非终结符
            char Vn = split[0].charAt(0);
            if (!followCollection.containsKey(Vn)){
                CalFollowCollection(Vn);
            }
        }
        //CalFollowCollection('D');
        /*
        System.out.println("---Follow集---");
        for (Character key : followCollection.keySet()){
            System.out.println(key + " : " + followCollection.get(key));
        }
         */
    }
    /**
     * 计算Follow集
     * 1)若B为开始符号S，则将#添加到Follow(B)中
     * 2)A->αBγ，首先将First(γ)\{ε}添加到Follow(B)中
     * 2.1 如果ε属于First(γ)，则将Follow(A)添加到Follow(B)中
     * 3)A->αB，则将Follow(A)添加到Follow(B)中
     */
    public void CalFollowCollection(Character B){
        //1)若B为开始符号S，则将#添加到Follow(B)中
        if (B == startSymbol){
            TreeSet<Character> characters = new TreeSet<>();
            characters.add('#');
            followCollection.put(B, characters);
        }
        for (String LL1Str : LL1List){
            String[] split = LL1Str.split("->");
            //产生式左边为非终结符
            char Vn = split[0].charAt(0);
            //产生式右边为待求项
            String vtStr = split[1];
            for (int i = 0; i < vtStr.length(); i++) {
                char chari = vtStr.charAt(i);
                if (chari == B){
                    if(i < vtStr.length() - 1){
                        //2)A->αBγ，首先将First(γ)\{ε}添加到Follow(B)中
                        //γ为终结符,First(γ) = {γ}
                        if (VtSet.contains(vtStr.charAt(i + 1))){
                            if (followCollection.containsKey(chari)){
                                TreeSet<Character> followTreeSet = followCollection.get(chari);
                                followTreeSet.add(vtStr.charAt(i + 1));
                            }else{
                                TreeSet<Character> characters = new TreeSet<>();
                                characters.add(vtStr.charAt(i + 1));
                                followCollection.put(chari, characters);
                            }
                        }else{
                            //γ为非终结符
                            if (followCollection.containsKey(chari)){
                                TreeSet<Character> followTreeSet = followCollection.get(chari);
                                TreeSet<Character> firstTreeSet = firstCollection.get(vtStr.charAt(i + 1));
                                firstTreeSet.remove('ε');
                                followTreeSet.addAll(firstTreeSet);
                                firstTreeSet.add('ε');
                            }else{
                                TreeSet<Character> characters = new TreeSet<>();
                                TreeSet<Character> firstTreeSet = firstCollection.get(vtStr.charAt(i + 1));
                                firstTreeSet.remove('ε');
                                characters.addAll(firstTreeSet);
                                firstTreeSet.add('ε');
                                followCollection.put(chari, characters);
                            }
                            if (isFirstNull(vtStr, i + 1)){
                                //2.1 如果ε属于First(γ)，则将Follow(A)添加到Follow(B)中
                                if ((firstCollection.get(vtStr.charAt(i + 1)).contains('ε'))){
                                    if (Vn == B){
                                        break;
                                    }
                                    if (!followCollection.containsKey(Vn)){
                                        CalFollowCollection(Vn);
                                    }
                                    if (followCollection.containsKey(chari)){
                                        TreeSet<Character> followTreeSet = followCollection.get(chari);
                                        followTreeSet.addAll(followCollection.get(Vn));
                                    }else{
                                        followCollection.put(chari, followCollection.get(Vn));
                                    }
                                }
                            }

                        }
                    }
                    //判断是否chari后面是否有元素
                    //3)A->αB，则将Follow(A)添加到Follow(B)中
                    if (i == vtStr.length() - 1){//chari后面没有元素
                        if (Vn == B){
                            break;
                        }
                        if (!followCollection.containsKey(Vn)){
                            CalFollowCollection(Vn);
                        }
                        if (followCollection.containsKey(chari)){
                            TreeSet<Character> followTreeSet = followCollection.get(chari);
                            followTreeSet.addAll(followCollection.get(Vn));
                        }else{
                            followCollection.put(chari, followCollection.get(Vn));
                        }
                    }
                }
            }
        }
    }
    /**
     * 判断ε是否属于First(γ)
     */
    public boolean isFirstNull(String vtStr, int i){
        for (int j = i; j < vtStr.length(); j++) {
            //如果包含非终结符，false
            if (VtSet.contains(vtStr.charAt(i))){
                return false;
            }else{//vtStr.charAt(i)为非终结符
                if (!firstCollection.get(vtStr.charAt(i)).contains('ε')){
                    return false;
                }
            }
            if (j == vtStr.length() - 1){
                return true;
            }
        }
        return true;
    }
    /**
     *计算某字符串α:X1X2X3...的First集
     *T->X1X2X3...
     * 1) X1是终结符，则将X1添加到First(α)中
     * 2) X1是非终结符
     *  2.1 First(X1)不含ε，则将First(X1)添加到First(α)中
     *  2.2 First(X1)含ε，则将First(X1)\{ε}添加到First(α)中，并继续判断X2是否未终结符...
     *  2.3 First(X1)，First(X2)...均含ε，则将First(X1)\{ε}添加到First(α)中
     */
    public void CalFirstS(){
        for (String LL1Str: LL1List) {
            String[] split = LL1Str.split("->");
            //产生式左边为非终结符
            char Vn = split[0].charAt(0);
            //产生式右边为待求项
            String vtStr = split[1];
            //FirstS集
            TreeSet<Character> firstSTree = new TreeSet<>();
            firstSCollection.put(LL1Str, firstSTree);
            //1) X1是终结符，则将X1添加到First(α)中
            if (VtSet.contains(vtStr.charAt(0))){
                firstSTree.add(vtStr.charAt(0));
            }else{//2) X1是非终结符
                for (int i = 0; i < vtStr.length(); i++) {
                    char Xn = vtStr.charAt(i);
                    //2.3 First(X1)，First(X2)...均含ε，则将First(X1)\{ε}添加到First(α)中
                    if (i == vtStr.length() - 1 ){
                        if (firstCollection.get(Xn).contains('ε')){
                            firstSTree.add('ε');
                        }
                    }
                    // 2.1 First(X1)不含ε，则将First(X1)添加到First(α)中
                    if (!firstCollection.get(Xn).contains('ε')){
                        firstSTree.addAll(firstCollection.get(Xn));
                        break;
                    }else{
                        //2.2 First(X1)含ε，则将First(X1)\{ε}添加到First(α)中，并继续判断X2是否未终结符...
                        TreeSet<Character> characters = firstCollection.get(Xn);
                        characters.remove('ε');
                        firstSTree.addAll(characters);
                        characters.add('ε');
                    }
                }
            }
        }
    }
    /**
     * 求Select集
     */
    public void CalSelect(){
        for (String LL1Str: LL1List) {
            String[] split = LL1Str.split("->");
            //产生式左边为非终结符
            char Vn = split[0].charAt(0);
            //产生式右边为待求项
            String vtStr = split[1];
            TreeSet<Character> selectTree = new TreeSet<>();
            selectTree.addAll(firstSCollection.get(LL1Str));
            selectCollection.put(LL1Str,selectTree);
            if (firstSCollection.get(LL1Str).contains('ε')){
                selectTree.remove('ε');
                selectTree.addAll(followCollection.get(Vn));
            }
        }
    }


}
