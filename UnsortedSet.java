/*
 * UnsortedSet.java - CS 314 Assignment 7
 *
 * By signing my/our name(s) below, I/we affirm that this assignment is my/our
 * own work. I/we have neither given nor received unauthorized assistance on
 * this assignment.
 *
 * Name 1: Trisha Gorusu
 * Email address 1: gorusu.trisha@gmail.com
 * UTEID 1: tmg2936
 *
 * Name 2: Krish Vijayvergia
 * Email address 2: kv9375@my.utexas.edu
 * UTEID 2: kv9375
 */

import java.util.ArrayList;
import java.util.Iterator;

/**
 * A simple implementation of an ISet.
 * Elements are not in any particular order.
 * Students are to implement methods that were not implemented in AbstractSet
 * and override methods that can be done more efficiently.
 * An ArrayList must be used as the internal storage container.
 *
 */
public class UnsortedSet<E> extends AbstractSet<E> {

    private ArrayList<E> myCon;

    public UnsortedSet() {
        myCon = new ArrayList<>();
    }

    /**
     * adds a value to the end of a set
     */
    // O(n)
    @Override
    public boolean add(E item) {
        if (!(contains(item))) {
            myCon.add(item); //array list add
            return true;
        }
        return false;
    }

    /**
     * goes thru the values in con
     */
    // O(1)
    @Override
    public Iterator<E> iterator() {
        return myCon.iterator();
    }

    /**
     * removes a specfic value from the set
     */
    // O(n)
    @Override
    public boolean remove(E item) {
        return myCon.remove(item);
    }

    /**
     * calculates the size of a set
     */
    // O(1)
    @Override
    public int size() {
        return myCon.size();
    }

    /**
     * finds the values that r in either set
     * 
     * @param otherSet checks if this set has the corresponding value
     * @return true/false depending on if a change was able to be made
     */
    // O(n^2)
    @Override
    public ISet<E> union(ISet<E> otherSet) {
        UnsortedSet<E> union = new UnsortedSet<>();
        union.addAll(this);
        union.addAll(otherSet);
        return union;
    }

    /**
     * finds the values that r in both sets
     * 
     * @param otherSet checks if this set has the corresponding value
     * @return true/false depending on if a change was able to be made
     */
    // O(n^2)
    @Override
    public ISet<E> intersection(ISet<E> otherSet) {
        UnsortedSet<E> intersection = new UnsortedSet<>();
        for (E item : this) {
            if (otherSet.contains(item)) {
                intersection.myCon.add(item);
            }
        }
        return intersection;
    }

}