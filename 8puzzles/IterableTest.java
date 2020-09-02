import java.util.LinkedList;
import java.util.Stack;
import java.util.Queue;
import java.util.Deque;
public class IterableTest
{
    public static Iterable<Integer> stackTest(int[] nums){
        Stack<Integer> stack = new Stack<>();
        for (int n : nums) {
            stack.push(n);
        }
        return stack;
    }
    public static Iterable<Integer> queueTest(int[] nums){
        Queue<Integer> myQ = new LinkedList<>();
        for (int n : nums) {
            myQ.offer(n);
        }
        return myQ;
    }
    public static Iterable<Integer> dequeTest(int[] nums){
        Deque<Integer> myQ = new LinkedList<>();
        for (int n : nums) {
            myQ.push(n);
        }
        return myQ;
    }
    public static void main(String[] args)
    {        
        int[] nums = {1,2,3};
        Iterable<Integer> stackResult = stackTest(nums);
        for (int n: stackResult) {
            System.out.print(n + ", ");            
        }
        System.out.println();
        
        Iterable<Integer> queueResult = queueTest(nums);
        for (int n: queueResult) {
            System.out.print(n + ", ");           
        }
        System.out.println();
        
        Iterable<Integer> dequeResult = dequeTest(nums);
        for (int n: dequeResult) {
            System.out.print(n + ", ");           
        }
         System.out.println();
    }
}
