package LL1.compile.wh241.cn;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class Main {
    private static Table tableS;
    private static Collections collections;
    private static JFrame jf = new JFrame("LL(1)分析法");// 创建窗口
    private static JTable table2;//First
    private static JTable table3;//Follow
    private static JTable table;//预测分析表
    private static JTable table1;//预测分析过程
    private static DefaultTableModel dtm2;//First
    private static DefaultTableModel dtm3;//Follow
    private static DefaultTableModel dtm;//预测分析表
    private static DefaultTableModel dtm1;//预测分析过程
    private static String[] columnNames2 = {"非终结符", "First集"};//First
    private static String[] columnNames3 = {"非终结符", "Follow集"};//Follow
    private static String[] columnNames = {"1","2"};//预测分析表
    private static String[] columnNames1 = {"步骤", "符号栈", "输入串", "所用产生式"};//预测分析过程
    private static Object[][] rowData = {};//预测分析表
    private static Object[][] rowData1 = {};//预测分析过程
    private static Object[][] rowData2 = {};//First集
    private static Object[][] rowData3 = {};//Follow集
    public static void main(String[] args) {
        collections = new Collections();
        tableS = new Table();
        // 创建文本框，指定可见列数为8列
        JTextField textField = new JTextField(8);
        textField.setFont(new Font(null, Font.PLAIN, 20));
        // 创建一个 5 行 10 列的文本区域
        JTextArea textArea = new JTextArea(10,10);
        // 设置自动换行
        textArea.setLineWrap(true);
        //设置字体大小
        textArea.setFont(new Font(null, Font.PLAIN, 25));
        // 创建一个顶层容器（窗口）
        //JFrame jf = new JFrame("LL(1)分析法");          // 创建窗口
        jf.setSize(600, 900);                       // 设置窗口大小
        jf.setLocationRelativeTo(null);             // 把窗口位置设置到屏幕中心
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // 当点击窗口的关闭按钮时退出程序（没有这一句，程序不会退出）

        // 创建中间容器（面板容器）
        //JPanel panel = new JPanel(new FlowLayout());                // 创建面板容器，使用流式布局管理器
        // 创建内容面板容器
        JPanel panel = new JPanel();
        // 创建分组布局，并关联容器
        GroupLayout layout = new GroupLayout(panel);
        // 设置容器的布局
        panel.setLayout(layout);
        // 自动创建组件之间的间隙
        layout.setAutoCreateGaps(true);
        // 自动创建容器与触到容器边框的组件之间的间隙
        layout.setAutoCreateContainerGaps(true);

        //创建文字
        JLabel label02 = new JLabel("<html>完整演示<br/></html>");
        label02.setFont(new Font(null, Font.PLAIN, 25));  // 设置字体，null 表示使用默认字体
        label02.setToolTipText("完整演示");
        label02.setForeground(new Color(84, 232, 57));
        JLabel label03 = new JLabel("<html>请输入LL(1)文法<br/></html>");
        label03.setFont(new Font(null, Font.PLAIN, 25));  // 设置字体，null 表示使用默认字体
        label03.setToolTipText("请输入LL(1)文法");
        JLabel label04 = new JLabel("<html>请输入符号串<br/></html>");
        label04.setFont(new Font(null, Font.PLAIN, 25));  // 设置字体，null 表示使用默认字体
        label04.setToolTipText("请输入符号串");
        JLabel label05 = new JLabel("<html>预测分析表<br/></html>");
        label05.setFont(new Font(null, Font.PLAIN, 20));  // 设置字体，null 表示使用默认字体
        label05.setToolTipText("预测分析表");
        JLabel label06 = new JLabel("<html>预测分析过程<br/></html>");
        label06.setFont(new Font(null, Font.PLAIN, 20));  // 设置字体，null 表示使用默认字体
        label06.setToolTipText("预测分析过程");
        JLabel label07 = new JLabel("<html>First集<br/></html>");
        label07.setFont(new Font(null, Font.PLAIN, 16));  // 设置字体，null 表示使用默认字体
        label07.setToolTipText("First集");
        JLabel label08 = new JLabel("<html>Follow集<br/></html>");
        label08.setFont(new Font(null, Font.PLAIN, 16));  // 设置字体，null 表示使用默认字体
        label08.setToolTipText("Follow集");

        JButton btn5 = new JButton("全部展示");
        btn5.setBackground(new Color(255, 128,192));
        btn5.setFocusPainted(false);//去除文字周围边框

        /**
         * First集
         */
        // 表头（列名）

        /*
        HashMap<Character, TreeSet<Character>> firstCollection = collections.firstCollection;
        ArrayList<ArrayList<String>> firstList = new ArrayList<>();
        for (Character key : firstCollection.keySet()){
            ArrayList<String> strings = new ArrayList<>();
            strings.add(key.toString());
            strings.add(firstCollection.get(key).toString());
            firstList.add(strings);
        }
        Object[][] objects = new Object[firstList.size()][];
        for (int j = 0; j < firstList.size(); j++) {
            objects[j] = firstList.get(j).toArray();
        }

         */
        // 表格所有行数据
        //Object[][] rowData2 = objects;

        // 创建一个表格，指定 表头 和 所有行数据
        dtm2 = new DefaultTableModel(rowData2, columnNames2);
        table2 = new JTable(dtm2);

        // 设置表格内容颜色
        table2.setForeground(Color.BLACK);                   // 字体颜色
        table2.setFont(new Font(null, Font.PLAIN, 14));      // 字体样式
        table2.setSelectionForeground(Color.DARK_GRAY);      // 选中后字体颜色
        table2.setSelectionBackground(Color.LIGHT_GRAY);     // 选中后字体背景
        table2.setGridColor(Color.GRAY);                     // 网格颜色

        // 设置表头
        table2.getTableHeader().setFont(new Font(null, Font.BOLD, 14));  // 设置表头名称字体样式
        table2.getTableHeader().setForeground(Color.RED);                // 设置表头名称字体颜色
        table2.getTableHeader().setResizingAllowed(false);               // 设置不允许手动改变列宽
        table2.getTableHeader().setReorderingAllowed(false);             // 设置不允许拖动重新排序各列

        // 设置行高
        table2.setRowHeight(30);

        // 第一列列宽设置为40
        table2.getColumnModel().getColumn(0).setPreferredWidth(40);

        // 设置滚动面板视口大小（超过该大小的行数据，需要拖动滚动条才能看到）
        table2.setPreferredScrollableViewportSize(new Dimension(400, 150));

        // 把 表格 放到 滚动面板 中（表头将自动添加到滚动面板顶部）
        JScrollPane scrollPane2 = new JScrollPane(table2);
        /**
         * Follow集
         */

        /*
        HashMap<Character, TreeSet<Character>> followCollection = collections.followCollection;
        ArrayList<ArrayList<String>> followList = new ArrayList<>();
        for (Character key : followCollection.keySet()){
            ArrayList<String> strings = new ArrayList<>();
            strings.add(key.toString());
            strings.add(followCollection.get(key).toString());
            followList.add(strings);
        }
        Object[][] objects1 = new Object[followList.size()][];
        for (int j = 0; j < followList.size(); j++) {
            objects1[j] = followList.get(j).toArray();
        }

         */
        // 表格所有行数据
        //Object[][] rowData3 = objects1;
        // 创建一个表格，指定 表头 和 所有行数据
        dtm3 = new DefaultTableModel(rowData3, columnNames3);
        table3 = new JTable(dtm3);
        // 设置表格内容颜色
        table3.setForeground(Color.BLACK);                   // 字体颜色
        table3.setFont(new Font(null, Font.PLAIN, 14));      // 字体样式
        table3.setSelectionForeground(Color.DARK_GRAY);      // 选中后字体颜色
        table3.setSelectionBackground(Color.LIGHT_GRAY);     // 选中后字体背景
        table3.setGridColor(Color.GRAY);                     // 网格颜色

        // 设置表头
        table3.getTableHeader().setFont(new Font(null, Font.BOLD, 14));  // 设置表头名称字体样式
        table3.getTableHeader().setForeground(Color.RED);                // 设置表头名称字体颜色
        table3.getTableHeader().setResizingAllowed(false);               // 设置不允许手动改变列宽
        table3.getTableHeader().setReorderingAllowed(false);             // 设置不允许拖动重新排序各列

        // 设置行高
        table3.setRowHeight(30);

        // 第一列列宽设置为40
        table3.getColumnModel().getColumn(0).setPreferredWidth(40);

        // 设置滚动面板视口大小（超过该大小的行数据，需要拖动滚动条才能看到）
        table3.setPreferredScrollableViewportSize(new Dimension(400, 150));

        // 把 表格 放到 滚动面板 中（表头将自动添加到滚动面板顶部）
        JScrollPane scrollPane3 = new JScrollPane(table3);
        /**
         * 预测分析表
         */
        // 表格所有行数据
        //Object[][] rowData = tableS.analyzeTable;
        //String[] columnNames = tableS.analyzeTable[0];
        // 创建一个表格，指定 表头 和 所有行数据
        dtm = new DefaultTableModel(rowData, columnNames);
        table = new JTable(dtm);

        // 设置表格内容颜色
        table.setForeground(Color.BLACK);                   // 字体颜色
        table.setFont(new Font(null, Font.PLAIN, 14));      // 字体样式
        table.setSelectionForeground(Color.DARK_GRAY);      // 选中后字体颜色
        table.setSelectionBackground(Color.LIGHT_GRAY);     // 选中后字体背景
        table.setGridColor(Color.GRAY);                     // 网格颜色

        // 设置表头
        table.getTableHeader().setFont(new Font(null, Font.BOLD, 14));  // 设置表头名称字体样式
        table.getTableHeader().setForeground(Color.RED);                // 设置表头名称字体颜色
        table.getTableHeader().setResizingAllowed(false);               // 设置不允许手动改变列宽
        table.getTableHeader().setReorderingAllowed(false);             // 设置不允许拖动重新排序各列

        // 设置行高
        table.setRowHeight(30);

        // 第一列列宽设置为40
        table.getColumnModel().getColumn(0).setPreferredWidth(40);

        // 设置滚动面板视口大小（超过该大小的行数据，需要拖动滚动条才能看到）
        table.setPreferredScrollableViewportSize(new Dimension(400, 150));

        // 把 表格 放到 滚动面板 中（表头将自动添加到滚动面板顶部）
        JScrollPane scrollPane = new JScrollPane(table);
        /**
         * 预测分析过程
         */
        /*Object[][] objects2 = new Object[anlyzeList.size()][];
        for (int j = 0; j < anlyzeList.size(); j++) {
            objects2[j] = anlyzeList.get(j).toArray();
            //System.out.println(anlyzeList.get(j));
        }

         */
        // 表格所有行数据
        //Object[][] rowData1 = objects2;

        // 创建一个表格，指定 表头 和 所有行数据
        dtm1 = new DefaultTableModel(rowData1, columnNames1);
        table1 = new JTable(dtm1);

        // 设置表格内容颜色
        table1.setForeground(Color.BLACK);                   // 字体颜色
        table1.setFont(new Font(null, Font.PLAIN, 14));      // 字体样式
        table1.setSelectionForeground(Color.DARK_GRAY);      // 选中后字体颜色
        table1.setSelectionBackground(Color.LIGHT_GRAY);     // 选中后字体背景
        table1.setGridColor(Color.GRAY);                     // 网格颜色

        // 设置表头
        table1.getTableHeader().setFont(new Font(null, Font.BOLD, 14));  // 设置表头名称字体样式
        table1.getTableHeader().setForeground(Color.RED);                // 设置表头名称字体颜色
        table1.getTableHeader().setResizingAllowed(false);               // 设置不允许手动改变列宽
        table1.getTableHeader().setReorderingAllowed(false);             // 设置不允许拖动重新排序各列

        // 设置行高
        table1.setRowHeight(30);

        // 第一列列宽设置为40
        table1.getColumnModel().getColumn(0).setPreferredWidth(40);

        // 设置滚动面板视口大小（超过该大小的行数据，需要拖动滚动条才能看到）
        table1.setPreferredScrollableViewportSize(new Dimension(400, 300));

        // 把 表格 放到 滚动面板 中（表头将自动添加到滚动面板顶部）
        JScrollPane scrollPane1 = new JScrollPane(table1);


        // 把 面板容器 作为窗口的内容面板 设置到 窗口
        JScrollPane jScrollPane = new JScrollPane(panel);
        jf.setContentPane(jScrollPane);

        // 添加按钮的点击事件监听器
        btn5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String fieldText = textField.getText();
                String textAreaText = textArea.getText();
                /*
                System.out.println(fieldText);
                System.out.println(textAreaText);
                 */
                goAnalyze(fieldText, textAreaText);
                //First
                dtm2.setDataVector(rowData2, columnNames2);
                //Follow
                dtm3.setDataVector(rowData3, columnNames3);
                //预测分析表
                dtm.setDataVector(rowData, columnNames);
                //预测分析过程
                dtm1.setDataVector(rowData1, columnNames1);

            }
        });

        /*
         * 水平组（仅确定 X 轴方向的坐标/排列方式）
         *
         * 水平串行: 水平排列（左右排列）
         * 水平并行: 垂直排列（上下排列）
         */
        // 水平串行（左右）label07,scrollPane2,label08,scrollPane3
        GroupLayout.SequentialGroup hSeqGroup2 = layout.createSequentialGroup().addComponent(label07).addComponent(scrollPane2)
                .addComponent(label08).addComponent(scrollPane3);
        //水平并行（上下）scrollPane1、label06
        GroupLayout.ParallelGroup hParalGroup05 = layout.createParallelGroup().addComponent(label06).addComponent(scrollPane1);
        //水平并行（上下）table、label05
        GroupLayout.ParallelGroup hParalGroup04 = layout.createParallelGroup().addComponent(label05).addComponent(table);
        //水平并行（上下）textField、label04
        GroupLayout.ParallelGroup hParalGroup03 = layout.createParallelGroup().addComponent(label04).addComponent(textField);
        //水平并行（上下）textarea、label03
        GroupLayout.ParallelGroup hParalGroup02 = layout.createParallelGroup().addComponent(label03).addComponent(textArea);

        // 水平并行（上下） label01 和 hSeqGroup
        GroupLayout.ParallelGroup hParalGroup01 = layout.createParallelGroup().addGroup(hParalGroup02).addGroup(hParalGroup03).addComponent(label02).
                addComponent(btn5).addGroup(hSeqGroup2).addGroup(hParalGroup04).addGroup(hParalGroup05);
        layout.setHorizontalGroup(hParalGroup01);  // 指定布局的 水平组（水平坐标）

        /*
         * 垂直组（仅确定 Y 轴方向的坐标/排列方式）
         *
         * 垂直串行: 垂直排列（上下排列）
         * 垂直并行: 水平排列（左右排列）
         */
        // 垂直并行（左右）label07,scrollPane2,label08,scrollPane3
        GroupLayout.ParallelGroup vParalGroup02 = layout.createParallelGroup().addComponent(label07).addComponent(scrollPane2)
                .addComponent(label08).addComponent(scrollPane3);
        // 垂直串行（上下）
        GroupLayout.SequentialGroup vSeqGroup4 = layout.createSequentialGroup().addComponent(label06).addComponent(scrollPane1);
        // 垂直串行（上下）
        GroupLayout.SequentialGroup vSeqGroup3 = layout.createSequentialGroup().addComponent(label05).addComponent(table);
        // 垂直串行（上下）
        GroupLayout.SequentialGroup vSeqGroup2 = layout.createSequentialGroup().addComponent(label04).addComponent(textField);
        // 垂直串行（上下）
        GroupLayout.SequentialGroup vSeqGroup1 = layout.createSequentialGroup().addComponent(label03).addComponent(textArea);
        // 垂直串行（上下）vParalGroup01, label01
        GroupLayout.SequentialGroup vSeqGroup = layout.createSequentialGroup().addGroup(vSeqGroup1).addGroup(vSeqGroup2).addComponent(label02).addComponent(btn5)
                .addGroup(vParalGroup02).addGroup(vSeqGroup3).addGroup(vSeqGroup4);
        layout.setVerticalGroup(vSeqGroup);    // 指定布局的 垂直组（垂直坐标）
        // 显示窗口，前面创建的信息都在内存中，通过 jf.setVisible(true) 把内存中的窗口显示在屏幕上。
        jf.pack();
        jf.setVisible(true);
    }
    public static void printError(){
        System.out.println("Error!!");
    }
    public static void goAnalyze(String inputStr, String LL1Str){
        /**
         * 预测分析主控程序
         */
        collections.initLL1(LL1Str);
        collections.getVnVt();
        collections.FirstCollection();
        collections.FollowCollection();
        collections.CalSelect();
        tableS.getTable(LL1Str);
        Stack<Character> strStack = new Stack<>();
        boolean flag = true;
        //String inputStr = "i*i+i#";
        System.out.println(inputStr);
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
        //预测分析过程表
        ArrayList<ArrayList<String>> anlyzeList = new ArrayList<>();
        String[][] anlyze = new String[4][];
        Character a = inputStr.charAt(0);
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
            ArrayList<String> arrayList = new ArrayList<>();
            arrayList.add(count + "");
            arrayList.add(symbol);
            arrayList.add(inStr);
            arrayList.add(production);
            anlyzeList.add(arrayList);
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
            }else if (tableS.checkTable(X, a) != ""){
                String s = tableS.checkTable(X, a);
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
        /**First集数据
         */
        HashMap<Character, TreeSet<Character>> firstCollection = collections.firstCollection;
        ArrayList<ArrayList<String>> firstList = new ArrayList<>();
        for (Character key : firstCollection.keySet()){
            ArrayList<String> strings = new ArrayList<>();
            strings.add(key.toString());
            strings.add(firstCollection.get(key).toString());
            firstList.add(strings);
        }
        Object[][] objects = new Object[firstList.size()][];
        for (int j = 0; j < firstList.size(); j++) {
            objects[j] = firstList.get(j).toArray();
        }
        rowData2 = objects;
        /**
         * Follow集数据
         */
        HashMap<Character, TreeSet<Character>> followCollection = collections.followCollection;
        ArrayList<ArrayList<String>> followList = new ArrayList<>();
        for (Character key : followCollection.keySet()){
            ArrayList<String> strings = new ArrayList<>();
            strings.add(key.toString());
            strings.add(followCollection.get(key).toString());
            followList.add(strings);
        }
        Object[][] objects1 = new Object[followList.size()][];
        for (int j = 0; j < followList.size(); j++) {
            objects1[j] = followList.get(j).toArray();
        }
        rowData3 = objects1;
        /**
         * 预测分析表数据
         */
        rowData = tableS.analyzeTable;
        columnNames = tableS.analyzeTable[0];
        /**
         * 预测分析过程数据
         */
        Object[][] objects2 = new Object[anlyzeList.size()][];
        for (int j = 0; j < anlyzeList.size(); j++) {
            objects2[j] = anlyzeList.get(j).toArray();
            //System.out.println(anlyzeList.get(j));
        }
        rowData1 = objects2;

    }
}
