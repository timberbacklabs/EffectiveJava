package Generics_4.FavorGenerics_29.GenerifyTry3A;

import java.util.Arrays;
import java.util.EmptyStackException;

// Object-based Collection is a prime example for generification
public class Stack<E> {

    private E[] elements;
    private int size = 0;
    private static final int DEFAULT_INITIAL_CAPACITY = 16;

    @SuppressWarnings("unchecked")
    public Stack() {
        /*
            Our last step is to eliminate the unchecked exception warning.

            PROOF of TYPE SAFETY:
            - the array is stored in a private field
            - it is never returned to the client
            - it is ever passed to another method
            - the elements contained therein are passed to push() and they are all of type E

            We are confident that the unchecked cast can do no harm.

         */
        elements = (E[]) new Object[DEFAULT_INITIAL_CAPACITY];
    }

    public void push(E e) {
        ensureCapacity();
        elements[size++] = e;
    }

    public E pop() {
        if (size == 0) {
            throw new EmptyStackException();
        }

        E result = elements[--size];
        /*
            ELIMINATE OBSOLETE REFERENCE to avoid UORs
         */
        elements[size] = null;
        return result;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private void ensureCapacity() {
        if (elements.length == size) {
            elements = Arrays.copyOf(elements, 2 * size + 1);
        }
    }
}
