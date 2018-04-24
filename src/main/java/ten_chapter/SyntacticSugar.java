package ten_chapter;

import java.util.*;

public class SyntacticSugar {

    public static void main(String[] args) {
        /*******************  泛型擦除  ************************/
        //泛型擦除前
        Map<String, String> map = new HashMap<String, String>();
        map.put("hello", "你好");
        map.put("how are you?", "吃了没？");
        System.out.println(map.get("hello"));
        System.out.println(map.get("how are you?"));
        //泛型擦除后
        Map map2 = new HashMap();
        map2.put("hello", "你好");
        map2.put("how are you?", "吃了没？");
        System.out.println((String) map2.get("hello"));
        System.out.println((String) map2.get("how are you?"));

        /**********************  自动装箱、拆箱与遍历循环  *****************************/
        List<Integer> list = Arrays.asList(1, 2, 3, 4);
        // 如果在JDK 1.7中，还有另外一颗语法糖 ，能让上面这句代码进一步简写成List<Integer> list = [1, 2, 3, 4];
        int sum = 0;
        for (int i : list) {
            sum += i;
        }
        System.out.println(sum);

        //自动装箱、拆箱与遍历循环编译之后
        List list2 = Arrays.asList( new Integer[] {
                Integer.valueOf(1),
                Integer.valueOf(2),
                Integer.valueOf(3),
                Integer.valueOf(4) });

        int sum2 = 0;
        for (Iterator localIterator = list2.iterator(); localIterator.hasNext(); ) {
            int i = ((Integer)localIterator.next()).intValue();
            sum2 += i;
        }
        System.out.println(sum2);

        //自动装箱的陷阱
        Integer a = 1;
        Integer b = 2;
        Integer c = 3;
        Integer d = 3;
        Integer e = 321;
        Integer f = 321;
        Long g = 3L;
        System.out.println(c == d);//true
        System.out.println(e == f);//false
        System.out.println(c == (a + b));//true
        System.out.println(c.equals(a + b));//true
        System.out.println(g == (a + b));//true
        System.out.println(g.equals(a + b));//false

        /*****************************  条件编译  *******************************/
        //编译前
        if (true) {
            System.out.println("block 1");
        } else {
            System.out.println("block 2");
        }
        //编译后
        System.out.println("block 1");

        // 编译器将会提示“Unreachable code”
//        while (false) {
//            System.out.println("");
//        }

    }
}
