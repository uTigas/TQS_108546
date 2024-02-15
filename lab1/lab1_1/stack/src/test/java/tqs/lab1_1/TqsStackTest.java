package tqs.lab1_1;

import org.junit.jupiter.api.Test;

import java.util.EmptyStackException;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

/**
 * Unit test for simple App.
 */
public class TqsStackTest 
{
    TqsStack<String> wordStack;
    @BeforeEach
    void setUp(){
        wordStack = new TqsStack<>();
    }    

    @DisplayName("A stack is empty on construction")
    @Test
    void isEmpty(){
        assert wordStack.isEmpty() == true;
    }

    @DisplayName("A stack has size 0 on construction")
    @Test
    void sizeZero(){
        assert wordStack.size() == 0;
    }

    @DisplayName("Stack filled with n elements after n pushes")
    @Test
    void nElem(){
        int n=2;
        for (int i=0; i<n; i++)
            wordStack.push("a");
        assert wordStack.isEmpty() == false && wordStack.size() == n;
    }

    @DisplayName("Pop of last pushed value")
    @Test
    void lastPushed(){
        String x = "a";
        wordStack.push(x);
        assert wordStack.pop() == x;
    }

    @DisplayName("Peek of last pushed value")
    @Test
    void peekLastPushed(){
        String x = "a";
        wordStack.push(x);
        int prevSize = wordStack.size();
        assert wordStack.peek() == x && wordStack.size() == prevSize;
    }

    @DisplayName("N pops out of a Stack filled with n elements")
    @Test
    void nPops(){
        int n=16;
        for (int i=0; i<n; i++)
            wordStack.push("a");
        for (int i=0; i<n; i++)
            wordStack.pop(); 
        assert wordStack.isEmpty() == true && wordStack.size() == 0;
    }

    @DisplayName("Pop out of Empty Stack")
    @Test
    void popEmpty(){
        assertThrows(NoSuchElementException.class, () -> { 
            wordStack.pop();
        });
    }

    @DisplayName("Peek a Empty Stack")
    @Test
    void peekEmpty(){
        assertThrows(NoSuchElementException.class, () -> { 
            wordStack.peek();
        });
    }
}
