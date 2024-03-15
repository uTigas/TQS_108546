package tqs.lab5.lab5_1;

import java.util.Deque;
import java.util.LinkedList;

public class Calculator {

    private Deque<Object> stack;
    private String operators = "+-/*";
    public Calculator(){
        this.stack = new LinkedList<Object>();
    }

    public void push (Object n){
        try{
            if ( operators.contains(n.toString())){
                int n1 = (int) this.stack.pop();
                int n2 = (int) this.stack.pop();
                if (n.toString() == "+")
                    this.stack.add(n1 + n2);
                else if (n.toString() == "-")
                    this.stack.add(n1 - n2);
                else if (n.toString() == "*")
                    this.stack.add(n1 * n2);
                else if (n.toString() == "/")
                    this.stack.add(n1 / n2);
            }
            else{
                this.stack.add((int) n);
            }
        }
        catch (Exception e){
            this.stack.clear();
            this.stack.add("Syntax Error");
        }
        
    }

    public Object value (){
        return (Object) this.stack.removeLast().toString();
    }
}
