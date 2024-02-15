package tqs.lab1_1;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class TqsStack <T>
{
    private List<T> stack;
    public TqsStack(){
        this.stack = new ArrayList<>();
    }
    
    public void push(T item){
        this.stack.add(item);
    }

    public T pop(){
        try {
            T ret = stack.get(stack.size()-1);
            stack.remove(stack.size()-1);
            return ret;
        } catch (Exception e) {
            throw new NoSuchElementException();
        }
    }

    public Boolean isEmpty(){
        return stack.size() == 0;
    }

    public int size(){
        return stack.size();
    }

    public T peek(){
        try {
            return stack.get(stack.size()-1);
        } catch (Exception e) {
            throw new NoSuchElementException();
        }
    }

}
