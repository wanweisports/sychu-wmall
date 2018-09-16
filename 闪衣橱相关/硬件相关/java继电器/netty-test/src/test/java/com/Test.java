package com;

/**
 * “x<<y"是位运算符当中的"左移"运算，其中x是左移的数，y是左移的位。如：
 2<<2的运算过程：
 2转化为二进制是
 0000 0010
 那么左移2位得到
 0000 1000即得到结果为8
 总结：左移一位相当乘以2，左移n位相当于乘以2的n次方。

 右移<<是：
 右移一位相当于整除2。

 上面这两个是不带符号的移位运算。
 还有一个：>>>这个是带符号的右移
 */
public class Test {

    @org.junit.Test
    public void a1(){
        //x<<y 相当于 x*2y ；x>>y相当于x/2y
        System.out.println(2 << 5); //
        System.out.println(2*(2*2*2*2*2));
        System.out.println(2 * (int)Math.pow(2, 5));
        System.out.println("================================");
        System.out.println(100 >> 3); //8/(2x2)=2
        System.out.println(100/(2*2*2));
        System.out.println(100 / (int)Math.pow(2, 3));

        /*System.out.println(3 << 2);//3*2*2
        System.out.println(3 << 3);//3*2*2*2
        System.out.println(3 << 4);//3*2*2*2*2

        System.out.println(100 >> 2);//100/(2*2);
        System.out.println(100 >> 3);//100/(2*2*2);
        System.out.println(100 >> 4);//100/(2*2*2*2);*/
    }

}
//
//0 1  2  3  4
//3 6 12 24 48