package LL1.compile.wh241.cn;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI {
    public static void main(String[] args) {
        // 创建一个顶层容器（窗口）
        JFrame jf = new JFrame("LL(1)分析法");          // 创建窗口
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
        // 创建文本框，指定可见列数为8列
        JTextField textField = new JTextField(8);
        textField.setFont(new Font(null, Font.PLAIN, 20));
        // 创建一个 5 行 10 列的文本区域
        JTextArea textArea = new JTextArea(10,10);
        // 设置自动换行
        textArea.setLineWrap(true);
        //设置字体大小
        textArea.setFont(new Font(null, Font.PLAIN, 25));
        panel.add(textArea);
        //创建文字
        JLabel label01 = new JLabel("<html>分步展示<br/></html>");
        label01.setFont(new Font(null, Font.PLAIN, 25));  // 设置字体，null 表示使用默认字体
        label01.setToolTipText("分步展示");
        label01.setForeground(new Color(0,173,232));
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


        // 创建一个基本组件（按钮），并添加到 面板容器 中
        JButton btn1 = new JButton("计算First集");
        btn1.setFocusPainted(false);//去除文字周围边框
        btn1.setBackground(new Color(128, 255,128));
        JButton btn2 = new JButton("计算Follow集");
        btn2.setBackground(new Color(255, 255,0));
        btn2.setFocusPainted(false);//去除文字周围边框
        JButton btn3 = new JButton("求预测分析表");
        btn3.setBackground(new Color(255, 128,128));
        btn3.setFocusPainted(false);//去除文字周围边框
        JButton btn4 = new JButton("显示分析过程");
        btn4.setBackground(new Color(0, 128,255));
        btn4.setFocusPainted(false);//去除文字周围边框
        JButton btn5 = new JButton("全部展示");
        btn5.setBackground(new Color(255, 128,192));
        btn5.setFocusPainted(false);//去除文字周围边框

        /**
         * First集
         */
        // 表头（列名）
        String[] columnNames2 = {"序号", "姓名", "语文", "数学", "英语", "总分"};

        // 表格所有行数据
        Object[][] rowData2 = {
                {1, "张三", 80, 80, 80, 240},
                {2, "John", 70, 80, 90, 240},
                {3, "Sue", 70, 70, 70, 210},
                {4, "Jane", 80, 70, 60, 210},
                {5, "Joe_05", 80, 70, 60, 210},
                {6, "Joe_06", 80, 70, 60, 210},
                {7, "Joe_07", 80, 70, 60, 210},
                {8, "Joe_08", 80, 70, 60, 210},
                {9, "Joe_09", 80, 70, 60, 210},
                {10, "Joe_10", 80, 70, 60, 210},
                {11, "Joe_11", 80, 70, 60, 210},
                {12, "Joe_12", 80, 70, 60, 210},
                {13, "Joe_13", 80, 70, 60, 210},
                {14, "Joe_14", 80, 70, 60, 210},
                {15, "Joe_15", 80, 70, 60, 210},
                {16, "Joe_16", 80, 70, 60, 210},
                {17, "Joe_17", 80, 70, 60, 210},
                {18, "Joe_18", 80, 70, 60, 210},
                {19, "Joe_19", 80, 70, 60, 210},
                {20, "Joe_20", 80, 70, 60, 210}
        };

        // 创建一个表格，指定 表头 和 所有行数据
        JTable table2 = new JTable(rowData2, columnNames2);

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
        table2.setPreferredScrollableViewportSize(new Dimension(400, 300));

        // 把 表格 放到 滚动面板 中（表头将自动添加到滚动面板顶部）
        JScrollPane scrollPane2 = new JScrollPane(table2);
        /**
         * Follow集
         */
        // 表头（列名）
        String[] columnNames3 = {"序号", "姓名", "语文", "数学", "英语", "总分"};

        // 表格所有行数据
        Object[][] rowData3 = {
                {1, "张三", 80, 80, 80, 240},
                {2, "John", 70, 80, 90, 240},
                {3, "Sue", 70, 70, 70, 210},
                {4, "Jane", 80, 70, 60, 210},
                {5, "Joe_05", 80, 70, 60, 210},
                {6, "Joe_06", 80, 70, 60, 210},
                {7, "Joe_07", 80, 70, 60, 210},
                {8, "Joe_08", 80, 70, 60, 210},
                {9, "Joe_09", 80, 70, 60, 210},
                {10, "Joe_10", 80, 70, 60, 210},
                {11, "Joe_11", 80, 70, 60, 210},
                {12, "Joe_12", 80, 70, 60, 210},
                {13, "Joe_13", 80, 70, 60, 210},
                {14, "Joe_14", 80, 70, 60, 210},
                {15, "Joe_15", 80, 70, 60, 210},
                {16, "Joe_16", 80, 70, 60, 210},
                {17, "Joe_17", 80, 70, 60, 210},
                {18, "Joe_18", 80, 70, 60, 210},
                {19, "Joe_19", 80, 70, 60, 210},
                {20, "Joe_20", 80, 70, 60, 210}
        };

        // 创建一个表格，指定 表头 和 所有行数据
        JTable table3 = new JTable(rowData3, columnNames3);

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
        table3.setPreferredScrollableViewportSize(new Dimension(400, 300));

        // 把 表格 放到 滚动面板 中（表头将自动添加到滚动面板顶部）
        JScrollPane scrollPane3 = new JScrollPane(table3);
        /**
         * 预测分析表
         */

        // 表格所有行数据
        Object[][] rowData = {

        };
        String[] columnNames = {"序号", "姓名", "语文", "数学", "英语", "总分"};
        // 创建一个表格，指定 表头 和 所有行数据
        JTable table = new JTable(rowData, columnNames);

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
        table.setPreferredScrollableViewportSize(new Dimension(400, 300));

        // 把 表格 放到 滚动面板 中（表头将自动添加到滚动面板顶部）
        JScrollPane scrollPane = new JScrollPane(table);
        /**
         * 预测分析过程
         */
        // 表头（列名）
        String[] columnNames1 = {"步骤", "符号栈", "输入串", "所用产生式"};

        // 表格所有行数据
        Object[][] rowData1 = {};

        // 创建一个表格，指定 表头 和 所有行数据
        JTable table1 = new JTable(rowData, columnNames);

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
        //水平并行（上下）scrollPane、label05
        GroupLayout.ParallelGroup hParalGroup04 = layout.createParallelGroup().addComponent(label05).addComponent(scrollPane);
        //水平并行（上下）textField、label04
        GroupLayout.ParallelGroup hParalGroup03 = layout.createParallelGroup().addComponent(label04).addComponent(textField);
        //水平并行（上下）textarea、label03
        GroupLayout.ParallelGroup hParalGroup02 = layout.createParallelGroup().addComponent(label03).addComponent(textArea);
        // 水平串行（左右）btn1,btn2,btn3,btn4
        GroupLayout.SequentialGroup hSeqGroup = layout.createSequentialGroup().addComponent(btn1).addComponent(btn2)
                .addComponent(btn3).addComponent(btn4);
        // 水平并行（上下） label01 和 hSeqGroup
        GroupLayout.ParallelGroup hParalGroup01 = layout.createParallelGroup().addGroup(hParalGroup02).addGroup(hParalGroup03).addComponent(label02).
                addComponent(btn5).addComponent(label01).addGroup(hSeqGroup).addGroup(hSeqGroup2).addGroup(hParalGroup04).addGroup(hParalGroup05);
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
        GroupLayout.SequentialGroup vSeqGroup3 = layout.createSequentialGroup().addComponent(label05).addComponent(scrollPane);
        // 垂直串行（上下）
        GroupLayout.SequentialGroup vSeqGroup2 = layout.createSequentialGroup().addComponent(label04).addComponent(textField);
        // 垂直串行（上下）
        GroupLayout.SequentialGroup vSeqGroup1 = layout.createSequentialGroup().addComponent(label03).addComponent(textArea);
        // 垂直并行（左右）btn1,btn2,btn3,btn4
        GroupLayout.ParallelGroup vParalGroup01 = layout.createParallelGroup().addComponent(btn1).addComponent(btn2)
                .addComponent(btn3).addComponent(btn4);
        // 垂直串行（上下）vParalGroup01, label01
        GroupLayout.SequentialGroup vSeqGroup = layout.createSequentialGroup().addGroup(vSeqGroup1).addGroup(vSeqGroup2).addComponent(label02).addComponent(btn5)
                .addComponent(label01).addGroup(vParalGroup01).addGroup(vParalGroup02).addGroup(vSeqGroup3).addGroup(vSeqGroup4);
        layout.setVerticalGroup(vSeqGroup);    // 指定布局的 垂直组（垂直坐标）
        // 显示窗口，前面创建的信息都在内存中，通过 jf.setVisible(true) 把内存中的窗口显示在屏幕上。
        jf.pack();
        jf.setVisible(true);

    }
}
