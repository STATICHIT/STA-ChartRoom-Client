package Util.snowIDUtil;

public class TestSnowFlake {
    public TestSnowFlake() {
    }

//    /**
//     * 计算一秒内生成的id个数
//     */
//    public static void generateIdsInOneSecond() {
//        SnowFlake idWoker = new SnowFlake(1L, 1L);
//        long start = System.currentTimeMillis();
//
//        int i;
//        for(i = 0; System.currentTimeMillis() - start < 1000L; ++i) {
//            idWoker.nextId();
//            // 把生成的id输出在控制器上
//            //System.out.println(idWoker.nextId());
//        }
//
//        long end = System.currentTimeMillis();
//        System.out.println("耗时：" + (end - start));
//        System.out.println("生成id个数：" + i);
//    }

    /**
     * 生成一个账号
     */
    public static String getOneID() {
        SnowFlake idWoker = new SnowFlake(1L, 1L);
        String id;
        id = String.valueOf(idWoker.nextId());
        return id;
    }

    public static void main(String[] args) {
        System.out.println(getOneID());
    }
}
