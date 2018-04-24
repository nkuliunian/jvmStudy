package ten_chapter;


public class BADLY_NAMED_CODE {

    enum colors {
        red, blue, green;
    }

    static final int _FORTY_TWO = 42;

    public static int NOT_A_CONSTANT = _FORTY_TWO;

    protected void BADLY_NAMED_CODE() {
        return;
    }

    public void NOTcamelCASEmethodNAME() {
        return;
    }

//    public static void main(String[] args) {
//        String badlyNameCodeClass="package ten_chapter;\n" +
//                "public class BADLY_NAMED_CODE {\n" +
//                "    enum colors {\n" +
//                "        red, blue, green;\n" +
//                "    }\n" +
//                "    static final int _FORTY_TWO = 42;\n" +
//                "    public static int NOT_A_CONSTANT = _FORTY_TWO;\n" +
//                "    protected void BADLY_NAMED_CODE() {\n" +
//                "        return;\n" +
//                "    }\n" +
//                "    public void NOTcamelCASEmethodNAME() {\n" +
//                "        return;\n" +
//                "    }\n" +
//                "}";
//        NameCheckProcessor nameCheckProcessor = new NameCheckProcessor();
//        nameCheckProcessor.
//    }
}


/***************  运行javac -processor的执行结果，这个地方需要把这几个类copy到项目外同一路径下再执行
 PS D:\java\newtest> javac -encoding utf-8 .\NameChecker.java
 PS D:\java\newtest> javac -encoding utf-8 .\NameCheckProcessor.java
 PS D:\java\newtest> javac -processor NameCheckProcessor .\BADLY_NAMED_CODE.java
 警告: 来自注释处理程序 'NameCheckProcessor' 的受支持 source 版本 'RELEASE_6' 低于 -source '1.8'
 .\BADLY_NAMED_CODE.java:4: 警告: 名称“BADLY_NAMED_CODE”应当符合驼式命名法（Camel Case Names）
 public class BADLY_NAMED_CODE {
 ^
 .\BADLY_NAMED_CODE.java:6: 警告: 名称“colors”应当以大写字母开头
 enum colors {
 ^
 .\BADLY_NAMED_CODE.java:7: 警告: 常量“red”应当全部以大写字母或下划线命名，并且以字母开头
 red, blue, green;
 ^
 .\BADLY_NAMED_CODE.java:7: 警告: 常量“blue”应当全部以大写字母或下划线命名，并且以字母开头
 red, blue, green;
 ^
 .\BADLY_NAMED_CODE.java:7: 警告: 常量“green”应当全部以大写字母或下划线命名，并且以字母开头
 red, blue, green;
 ^
 .\BADLY_NAMED_CODE.java:10: 警告: 常量“_FORTY_TWO”应当全部以大写字母或下划线命名，并且以字母开头
 static final int _FORTY_TWO = 42;
 ^
 .\BADLY_NAMED_CODE.java:12: 警告: 名称“NOT_A_CONSTANT”应当以小写字母开头
 public static int NOT_A_CONSTANT = _FORTY_TWO;
 ^
 .\BADLY_NAMED_CODE.java:14: 警告: 一个普通方法 “BADLY_NAMED_CODE”不应当与类名重复，避免与构造函数产生混淆
 protected void BADLY_NAMED_CODE() {
 ^
 .\BADLY_NAMED_CODE.java:14: 警告: 名称“BADLY_NAMED_CODE”应当以小写字母开头
 protected void BADLY_NAMED_CODE() {
 ^
 .\BADLY_NAMED_CODE.java:18: 警告: 名称“NOTcamelCASEmethodNAME”应当以小写字母开头
 public void NOTcamelCASEmethodNAME() {
 ^
 11 个警告


 *************/