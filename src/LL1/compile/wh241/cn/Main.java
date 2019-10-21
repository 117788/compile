package LL1.compile.wh241.cn;

import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        /**
         * 预测分析主控程序
         */
        Stack<Character> strStack = new Stack<>();
        boolean flag = true;
        String inputStr = "i*i+i#";
        //指示inputStr
        int i = 0;
        //步骤序号
        int count = 0;
        //符号栈
        String symbol = "";
        //输入串
        String inStr = "";
        //产生式
        String production = "";
        Character a = inputStr.charAt(0);
        Collections collections = new Collections();
        collections.initLL1();
        collections.getVnVt();
        collections.FirstCollection();
        collections.FollowCollection();
        collections.CalSelect();
        Table table = new Table();
        table.getTable();
        Character X = ' ';
        strStack.push('#');
        strStack.push(collections.startSymbol);
        System.out.printf("%-10s%-10s%-10s%-10s","步骤","符号栈","输入串","所用产生式");
        System.out.println("");
        while (flag){
            //求符号栈
            symbol = "";
            for (Character item : strStack){
                symbol += item;
            }
            //求输入串
            inStr = "";
            for (int j = i; j < inputStr.length(); j++) {
                inStr += inputStr.charAt(j);
            }
            System.out.printf("%-10s%-10s%-10s%-10s",count,symbol,inStr,production);
            System.out.println("");
            count++;
            production = "";
            X = strStack.pop();
            if (collections.VtSet.contains(X)){
                if (X == a){
                    i++;
                    a = inputStr.charAt(i);
                    //System.out.printf("%-10s%-10s%-10s%-10s",count,symbol,"输入串","消去" + a);
                }else{
                    printError();
                }
            }else if(X == '#'){
                if (X == a){
                    //System.out.println("匹配成功!!");
                    flag = false;
                }
            }else if (true){
                //table.checkTable(X, a) != ""
                String s = table.checkTable(X, a);
                production = s;
                String[] split = s.split("->");
                String str = split[1];
                for (int j = str.length() - 1; j >= 0; j--) {
                    if (str.charAt(j) != 'ε'){
                        strStack.push(str.charAt(j));
                    }
                }
            }else{
                printError();
            }

            if (flag == false){
                System.out.println("匹配成功!!");
            }
        }

    }
    public static void printError(){
        System.out.println("Error!!");
    }
}
