/*
 * AbstractSet.java - CS 314 Assignment 7
 *
 * By signing my/our name(s) below, I/we affirm that this assignment is my/our
 * own work. I/we have neither given nor received unauthorized assistance on
 * this assignment.
 *
 * Name 1:
 * Email address 1:
 * UTEID 1:
 *
 * Name 2:
 * Email address 2:
 * UTEID 2:
 */

import java.util.Iterator;

/**
 * Students are to complete this class. 
 * Students should implement as many methods as they can using the Iterator
 * from the iterator method and the other methods.
 */
public abstract class AbstractSet<E> implements ISet<E> {

    /**
     * checks if a certain set has a specific
     * item <E>
     * 
     * @param item the item you r checking for if avaible
     * @return true/false depending on if a specific
     * item is present
     */
    @Override
    public boolean contains(E item){
        for (E otherItem : this) {
            if (otherItem.equals(item)) {
                return true;
            }
        }
        return false;
    }

    /**
     * checks if a certain set has a specific
     * item <E>
     * 
     * @param otherSet the set to compare if it contains all the 
     * right values
     * @return true/false depending on if all
     * items are present
     */
    @Override
    public boolean containsAll(ISet<E> otherSet){
        for (E otherItem : otherSet) {
            if (!(this.contains(otherItem))) {
                return false;
            }
        }
        return true;
    }

    /**
     * tries to add all new values from other set into
     * the current set
     * 
     * @param otherSet the set to compare if otherSet has more values
     * @return true/false depending on if a change was able to be made
     */
    @Override
    public boolean addAll(ISet<E> otherSet){
        boolean isSetDiff = false;
        for (E val : otherSet) {
            if (add(val)) {
                isSetDiff = true;
            }
        }
        return isSetDiff;
    }

    /**
     * finds the values in this set, but are not in
     * otherset
     * 
     * @param otherSet checks if this set has the corresponding value
     * @return true/false depending on if a change was able to be made
     */
    @Override
    public ISet<E> difference(ISet<E> otherSet){
        ISet<E> result = new UnsortedSet<>();
        for (E itemE : this) {
            if (!otherSet.contains(itemE)) {
                result.add(itemE);
            }
        }
        return result;
    }

    /**
     * removes all of the values in an set
     */
    @Override
    public void clear() {
        Iterator<E> increment = this.iterator();
        while(increment.hasNext()){
            remove(increment.next());
        }
    }

    /**
     * calculates the size of a set
     */
    @Override
    public int size() {
        Iterator<E> increment = this.iterator();
        int counter = 0;
        while(increment.hasNext()){
            increment.next();
            counter++;
        }
        return counter;
    }

    /**
     * checks if two objects have the same values at
     * certain locations
     * 
     * @param object what you're comparing
     * @return true/false depending on if they r the same functionally
     */
    @Override
    public boolean equals(Object other) {
        if (other instanceof ISet<?>) {
            ISet<?> otherSet = (ISet<?>) other;
            if (this.size() != otherSet.size()) {
                return false;
            }
            for (Object item : otherSet) {
                if (!(this.contains((E) item))) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    /**
     * Return a String version of this set. 
     * Format is (e1, e2, ... en)
     * @return A String version of this set.
     */
    public String toString() {
        StringBuilder result = new StringBuilder();
        String seperator = ", ";
        result.append("(");

        Iterator<E> it = this.iterator();
        while (it.hasNext()) {
            result.append(it.next());
            result.append(seperator);
        }
        // get rid of extra separator
        if (this.size() > 0) {
            result.setLength(result.length() - seperator.length());
        }

        result.append(")");
        return result.toString();
    }
}