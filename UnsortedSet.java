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

import java.util.Iterator;
import java.util.ArrayList;

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

    public boolean add(E item){
        //i think its true or false depending on if it changes
        // so false if alr present
        return false;
    }

    public boolean addAll(ISet<E> otherSet){
        //true if one thing got added (?)
        return false;
    }

    public void clear(){
        //size() == 0
    }

    public Iterator<E> iterator(){
        // return [idk what to put here].iterator();
        return null;
    }

    public boolean remove(E item){
        //same logic as add
        return false;
    }

    public int size(){
        return 0;
    }

    @Override
    public ISet<E> union(ISet<E> otherSet){
        return null;
    }

    @Override
    public ISet<E> intersection(ISet<E> otherSet){
        return null;
    }

    @Override
    public ISet<E> diference(ISet<E> otherSet){
        return null;
    }

}