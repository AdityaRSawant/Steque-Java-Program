
/**
* Implementation of STACK ended Queue (STEQUE) that supports PUSH, POP and ENQUEUE
* @author  Aditya Raghunath Sawant
* @version 1.1
* @since   08-31-2020 
*/

import java.util.*;

public class Steque<Item> implements Iterable<Item> {

    /**
    * Inner class NODE that holds declaration of the item, previous and next items
    */

    private class Node {
        Item item;
        Node prevElem;
        Node nextElem;
    }

    // Declaration of class variables
    private Node firstElem;
    private Node lastElem;
    private int size;

    /**
     * Function to check steque is empty or not
     * @return boolean of size variable equals 0
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Function to get size of steque
     * @return size of steque
     */

    public int size() {
        return size;
    }

    /**
     * Function to push item in steque
     * This function pushes the item on top of previous first item
     * This function behaves as PUSH function of STACK
     * @param item to be inserted
     */
    public void push(Item item) {
        //Store current first node as old first node
        Node oldFirst = firstElem;
        firstElem = new Node();
        //Set item of first node to new item
        firstElem.item = item;
        //Set next of first item to old first item
        firstElem.nextElem = oldFirst;

        if (oldFirst != null) 
        {
            //If old first item is not null, set previous of that item to current first
            oldFirst.prevElem = firstElem;
        } 
        else 
        {
            //If old first item is null, set last of that item to current first
            lastElem = firstElem;
        }

        //Increase size of the steque
        size++;
    }

    /**
     * Function to pop item from steque
     * This function pops the first item from top of steque
     * This function behaces as POP function of STACK
     * @return the top item in the steque 
     */
    public Item pop() {
        //Check if steque is empty or not
        if (isEmpty()) {
            //If steque is empty throw exception accordingly
            throw new RuntimeException("Steque is empty");
        }

        Item item = firstElem.item;
        //Change the firdt item to its next
        firstElem = firstElem.nextElem;

        // Check the case of last element being popped
        if (firstElem != null) 
        {
            // If first item is null, set the previous of first to null
            firstElem.prevElem = null;
        } 
        else 
        {
            // Set the last as null if first was null
            lastElem = null;
        }

        // Reduce the size of steque
        size--;

        //Return the popped item
        return item;
    }

    /**
     * This function adds element in steque
     * This function behaves like Enqueue of QUEUE adding it to the last of steque
     * @param item to be inserted in steque 
     */
    public void enqueue(Item item) {

        //Store current last element as old last
        Node oldLast = lastElem;
        //Create new node for last
        lastElem = new Node();
        //Set the item of last to the new item
        lastElem.item = item;
        //Set previous to the old last
        lastElem.prevElem = oldLast;

        //Check if old last is not null
        if (oldLast != null) 
        {
            //Set next of old last item to current last
            oldLast.nextElem = lastElem;
        }
        else 
        {
            //If first element was added, first element is the last element
            firstElem = lastElem;
        }

        //Increase size of steque
        size++;
    }

    /**
     * Return object of Iterator
     */
    public Iterator<Item> iterator() {
        return new StequeIterator();
    }

    /**
     * This inner class implements Iterator interface
     * The methods in it can be used to iterate over the items in steque
     */
    private class StequeIterator implements Iterator<Item>{

        //Set current node to first
        Node current = firstElem;
        //Set index to 0
        int index = 0;

        /**
         * Override hasNext function to check if index is less than steque size
         * This function is used to determine if next item exists in the steque or not
         * @return boolean of index less than size
         */
        @Override
        public boolean hasNext() {
            return index < size;
        }

        /**
         * Override next function that will return the next of current of Item
         * @return next item in the steque
         */
        @Override
        public Item next() {
            //Set item to current item
            Item item = current.item;
            //Change current to its next
            current = current.nextElem;

            //Increase the index to move ahead
            index++;

            //Return the item
            return item;
        }
    }

    public static void main(String[] args) {
        long startTime = System.nanoTime();
        Steque<Integer> steque1 = new Steque<>();
        //Testing the inputs and outputs for steque working
        System.out.println("1. Checking all operations ");
        System.out.println("----------------");
        steque1.push(1);
        steque1.push(2);
        steque1.push(3);
        steque1.pop();
        steque1.enqueue(5);
        steque1.enqueue(6);

        Iterator itr1 = steque1.iterator();
        System.out.println("Steque items are:");
        while(itr1.hasNext())
        {
            System.out.print(itr1.next().toString()+" ");
        }
        System.out.println();
        System.out.println("Expected items in steque are:");
        System.out.println("2 1 5 6");


        //Testing the inputs and outputs for steque working
        Steque<Integer> steque2 = new Steque<>();

        System.out.println("2. Checking all operations ");
        System.out.println("----------------");
        steque2.enqueue(5);
        steque2.enqueue(6);
        steque2.pop();
        steque2.push(1);
        steque2.push(2);
        steque2.push(3);

        Iterator itr2 = steque2.iterator();
        System.out.println("Steque items are:");
        while(itr2.hasNext())
        {
            System.out.print(itr2.next().toString()+" ");
        }
        System.out.println();
        System.out.println("Expected items in steque are:");
        System.out.println("3 2 1 6");

        //Testing the inputs and outputs for steque working
        Steque<Integer> steque3 = new Steque<>();
        
        System.out.println("3. Checking all operations ");
        System.out.println("----------------");
        steque3.push(1);
        steque3.enqueue(2);
        steque3.pop();
        steque3.push(3);
        steque3.enqueue(4);
        steque3.pop();
        steque3.push(5);
        steque3.enqueue(6);
        steque3.pop();
        Iterator itr3 = steque3.iterator();
        System.out.println("Steque items are:");
        while(itr3.hasNext())
        {
            System.out.print(itr3.next().toString()+" ");
        }
        System.out.println();
        System.out.println("Expected items in steque are:");
        System.out.println("2 4 6");
        long elapsedTime = System.nanoTime() - startTime;
        System.out.println("Time Elapsed is:"+(elapsedTime/1000000)+"ms");

    }

}