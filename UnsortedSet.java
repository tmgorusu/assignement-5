/*
 * UnsortedSet.java - CS 314 Assignment 7
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

    @Override
    public boolean add(E item) {
        // i think its true or false depending on if it changes
        // so false if alr present
        if (!(contains(item))) {
            myCon.add(item);
            return true;
        }
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return myCon.iterator();
    }

    @Override
    public boolean remove(E item) {
        // same logic as add
        return myCon.remove(item);
    }

    @Override
    public int size() {
        return myCon.size();
    }

    @Override
    public ISet<E> union(ISet<E> otherSet) {
        UnsortedSet<E> union = new UnsortedSet<>();
        union.addAll(this);
        union.addAll(otherSet);
        return union;
    }

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