//package me.young1lin.evaluation;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * @author young1lin
// * @version 1.0
// * @date 2020/7/8 10:16 下午
// */
//public class ForTest {
//    /*public static void main(String[] args) {
//        int listSize = 4;
//        byte[] bar = {'b','a','r'};
//        List list = new ArrayList(listSize);
//        list.add("foo");
//        list.add(bar);
//        list.add(1);
//        list.add(1L);
//        //上面的代码是完全没问题的（我是指编译没问题）
//        //当你想下面做的时候
//        for(int i=0,size=listSize;i<size;++i){
//            String str = (String)list.get(i);
//            System.out.println(str);
//            //这里就会报错，
//        }
//    }*/
//
//    public static void main(String[] args) {
//        int listSize = 2;
//        List<Fruit> fruitArrayList= new ArrayList<>();
//        fruitArrayList.add(new Apple());
//        fruitArrayList.add(new Banana());
//        for (int i = 0; i < listSize; i++) {
//            Fruit fruit = fruitArrayList.get(i);
//            fruit.eat();
//        }
//    }
//}
//interface Fruit{
//    void eat();
//}
//
//class Banana implements Fruit{
//
//    @Override
//    public void eat() {
//        System.out.println("eat banana");
//    }
//}
//
//class Apple implements Fruit{
//
//    @Override
//    public void eat() {
//        System.out.println("eat apple");
//    }
//}