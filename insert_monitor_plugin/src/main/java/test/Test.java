package test;

/**
 * Created by conghongjie on 2018/6/22.
 */

public class Test {


    void main2(){
        test1(new Object(),1);
    }

    int test1(Object a,int b){


        int c = test2(a,b);

        return c;
    }

    int test2(Object a,int b){
        System.out.println("========start=========");
        return b;
    }
}
