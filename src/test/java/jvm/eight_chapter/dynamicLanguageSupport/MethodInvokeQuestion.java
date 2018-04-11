package jvm.eight_chapter.dynamicLanguageSupport;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodType;

import static java.lang.invoke.MethodHandles.lookup;

/**
 * 方法调用问题
 */
public class MethodInvokeQuestion {
    public static void main(String[] args) {
        Son son = new Son();
        son.thinking();
//        son.thinking1();
    }
}

class GrandFather {
    void thinking() {
        System.out.println("i am grandfather");
    }
}

class Father extends GrandFather {
    void thinking() {
        System.out.println("i am father");
    }
}

class Son extends Father {
    //常规实现
//    void thinking1() {
        // 请读者在这里填入适当的代码（不能修改其他地方的代码）
        // 实现调用祖父类的thinking()方法，打印"i am grandfather"
//        GrandFather grandFather = new GrandFather();
//        grandFather.thinking();
//    }

    //MethodHandle实现
    void thinking() {
        try {
            MethodType mt = MethodType.methodType(void.class);
            MethodHandle mh = lookup().findSpecial(GrandFather.class,
                    "thinking", mt, getClass());
            mh.invoke(this);
        } catch (Throwable e) {
        }
    }
}

/*******
 执行结果：
 i am father

 与预期结果不符！！！！！！！！！！！！！！！！！！！
 **/