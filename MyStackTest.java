// javac -cp .:junit-4.12.jar:hamcrest-core-1.3.jar MyStackTest.java
// java -cp .:junit-4.12.jar:hamcrest-core-1.3.jar org.junit.runner.JUnitCore MyStackTest

// javac -cp .:junit-4.12.jar:hamcrest-core-1.3.jar MyStackTest.java && java -cp .:junit-4.12.jar:hamcrest-core-1.3.jar org.junit.runner.JUnitCore MyStackTest
import static org.junit.Assert.*;
import org.junit.Test;

public class MyStackTest{
    @Test
    public void myTestNormal() {
        DelayedStack<String> s = new MyStack<String>(2);
        s.push("1");
        s.push("2");
        s.push(null);
        s.push("3");
        s.push("4");
        assertEquals(5,s.size());
        assertEquals((String) "4",s.peek());
        assertEquals((String) "4",s.pop());
    }

    @Test(expected = IllegalStateException.class)
    public void mypopError(){
        DelayedStack<String> s = new MyStack<String>(2);
        s.pop();
    }

    @Test(expected = IllegalStateException.class)
    public void mypeekError(){
        DelayedStack<String> s = new MyStack<String>(2);
        s.peek();
    }

    @Test
    public void myclear(){
        DelayedStack<String> s = new MyStack<String>(2);
        s.push("1");
        s.push("2");
        s.push(null);
        s.push("3");
        s.push("4");
        assertEquals(5,s.size());
        assertTrue(s.clear());
        assertEquals(0,s.size());
        s.push("a");
        assertFalse(s.clear());
    }

    @Test
    public void mycontains(){
        DelayedStack<String> s = new MyStack<String>(2);
        s.push("1");
        s.push("2");
        s.push(null);
        s.push("3");
        s.push("4");
        assertTrue(s.contains("2"));
        assertTrue(s.contains(null));
        assertFalse(s.contains("null"));
    }

    @Test
    public void myresize(){
        DelayedStack<Integer> s = new MyStack<Integer>(2);
        for(int i=0;i<75;i++){
            s.push(i);
        }
        assertEquals(75,s.size());
    }

    @Test
    public void myDelay(){
        DelayedStack<Integer> s = new MyStack<Integer>(4);
        s.push(1);
        assertEquals(3,s.getDelay());
        s.push(2);
        s.push(3);
        s.push(4);
        assertEquals(0,s.getDelay());
        s.push(5);
        assertEquals(0,s.getDelay());
        s.pop();
        s.push(6);
        assertEquals(3,s.getDelay());
        assertNull(s.pop());
    }
    @Test
    public void mySetDelay(){
        DelayedStack<Integer> s = new MyStack<Integer>(4);
        assertEquals(4,s.getMaximumDelay());
        s.push(1);
        s.push(2);
        s.setMaximumDelay(10);
        assertEquals(10,s.getMaximumDelay());
        s.push(3);
        s.push(4);
        s.push(5);
        s.pop();
        s.push(6);
        assertEquals(9,s.getDelay());
        assertEquals(10,s.getMaximumDelay());
    }

}
