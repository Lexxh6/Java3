package Lesson7.dz;

public class MyClass {
    @BeforeSuite
    public void before0(){
        System.out.println("Before0");
    }
//    @BeforeSuite                                  // приведет к исключению
//    public void before1(){
//        System.out.println("Before1");
//    }
    @Test(priority=9)
    public void myMet() {
        System.out.println("MyMet -prior 9");
    }
    public void myMet0(){
        System.out.println("NonTest");
    }
    @Test(priority=2)
    public void myMet1(){
        System.out.println("MyMet1-prior 2");
    }
    @Test
    public void myMet2(){
        System.out.println("MyMet2-prior(5)-default");
    }
    @Test(priority=7)
    public void myMet3(){
        System.out.println("MyMet3-prior 7");
    }
    @Test(priority=2)
    public void myMet4(){
        System.out.println("MyMet4-prior 2");
    }
    public void myMet5(){
        System.out.println("NonTest");
    }
    @Test(priority=1)
    public void myMet6() {
        System.out.println("MyMet6-prior 1");
    }
//    @AfterSuite                                   // приведет к исключению
//    public void after0(){
//        System.out.println("After0");
//    }
    @AfterSuite
    public void after1(){
        System.out.println("After1");
    }
}
