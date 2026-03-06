/*
 * SortedSet.java - CS 314 Assignment 7
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
 * In this implementation of the ISet interface the elements in the Set are
 * maintained in ascending order.
 *
 * The data type for E must be a type that implements Comparable.
 *
 * Implement methods that were not implemented in AbstractSet
 * and override methods that can be done more efficiently. An ArrayList must
 * be used as the internal storage container. For methods involving two set,
 * if that method can be done more efficiently if the other set is also a
 * SortedSet, then do so.
 */
public class SortedSet<E extends Comparable<? super E>> extends AbstractSet<E> {

    private ArrayList<E> myCon;

    /**
     * create an empty SortedSet
     */
    public SortedSet() {
        myCon = new ArrayList<>();
    }

    /**
     * create a SortedSet out of an unsorted set. <br>
     * 
     * @param other != null
     */
    public SortedSet(ISet<E> other) {
        myCon = new ArrayList<>();
        for (E item : other) {
            myCon.add(item);
        }
        myCon = mergeSort(myCon);
    }

    /**
     * Return the smallest element in this SortedSet.
     * <br>
     * pre: size() != 0
     * 
     * @return the smallest element in this SortedSet.
     */
    public E min() {
        return myCon.get(0);
    }

    /**
     * Return the largest element in this SortedSet.
     * <br>
     * pre: size() != 0
     * 
     * @return the largest element in this SortedSet.
     */
    public E max() {
        int maxIndex = myCon.size() - 1;
        return myCon.get(maxIndex);
    }

    @Override
    public boolean add(E item) {
        if (!(contains(item))) {
            int i = 0;
            while (i < myCon.size() && myCon.get(i).compareTo(item) < 0) {
                i++;
            }
            myCon.add(i, item);
            return true;
        }
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        // return [idk what to put here].iterator();
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
        SortedSet<E> union = new SortedSet<>();
        checkObject(otherSet);
        union.addAll(this);
        union.addAll(otherSet);
        return union;
    }

    @Override
    public ISet<E> intersection(ISet<E> otherSet) {
        SortedSet<E> intersection = new SortedSet<>();
        checkObject(otherSet);
        for (E item : this) {
            if (otherSet.contains(item)) {
                intersection.myCon.add(item);
            }
        }
        return intersection;
    }

    /**
     * checks if a certain set has a specific
     * item <E>
     * 
     * @param otherSet the set to compare if it contains all the
     *                 right values
     * @return true/false depending on if all
     *         items are present
     */
    @Override
    public boolean containsAll(ISet<E> otherSet) {

        int thisCounter = 0;
        int otherCounter = 0;

        SortedSet<E> otherSortedSet = (SortedSet<E>) checkObject(otherSet);

        while (thisCounter < this.size() && otherCounter < otherSortedSet.size()) {
            if (myCon.get(thisCounter).compareTo(otherSortedSet.myCon.get(otherCounter)) == 0) {
                thisCounter++;
                otherCounter++;
            } else {
                if (otherSortedSet.myCon.get(otherCounter).compareTo(myCon.get(thisCounter)) < 0) {
                    return false;
                }
                if (myCon.get(thisCounter).compareTo(otherSortedSet.myCon.get(otherCounter)) < 0) {
                    thisCounter++;
                }
            }
        }

        while (otherCounter != otherSortedSet.size()) {
            return false;
        }

        return true;
    }

    /**
     * checks if a certain set has a specific
     * item <E>
     * 
     * 
     * @param item the item you r checking for if avaible
     * @return true/false depending on if a specific
     *         item is present
     */
    @Override
    public boolean contains(E item) {
        return (binarySearch(item) >= 0);
    }

    // got this from the slides specifically from slide 37
    private int binarySearch(E item) {
        int low = 0;
        int high = myCon.size() - 1;
        while (low <= high) {
            int mid = low + ((high - low) / 2);
            int comparisonVal = myCon.get(mid).compareTo(item);
            if (comparisonVal == 0) {
                return mid;
            } else if (comparisonVal > 0) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return -1;
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

        if (!(other instanceof ISet<?>)) {
            return false;
        }

        ISet<?> otherSet = (ISet<?>) other;

        if (this.size() != otherSet.size()) {
            return false;
        }

        SortedSet<?> otherSortedSet = checkObject(otherSet);

        Iterator<E> incrementThis = this.iterator();
        Iterator<?> incrementOther = otherSortedSet.iterator();
        while (incrementThis.hasNext()) {
            if (!(incrementThis.next().equals(incrementOther.next()))) {
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
    public boolean addAll(ISet<E> otherSet) {
        int thisCounter = 0;
        int otherCounter = 0;
        SortedSet<E> otherSortedSet = (SortedSet<E>) checkObject(otherSet);
        ArrayList<E> newCon = new ArrayList<>();

        while (thisCounter < myCon.size() && otherCounter < otherSortedSet.myCon.size()) {
            if (myCon.get(thisCounter).compareTo(otherSortedSet.myCon.get(otherCounter)) == 0) {
                newCon.add(myCon.get(thisCounter));
                thisCounter++;
                otherCounter++;
            } else if (myCon.get(thisCounter).compareTo(
                otherSortedSet.myCon.get(otherCounter)) < 0) {
                newCon.add(myCon.get(thisCounter));
                thisCounter++;
            } else {
                newCon.add(otherSortedSet.myCon.get(otherCounter));
                otherCounter++;
            }
        }

        while (thisCounter < myCon.size()) {
            newCon.add(myCon.get(thisCounter));
            thisCounter++;
        }

        while (otherCounter < otherSortedSet.myCon.size()) {
            newCon.add(otherSortedSet.myCon.get(otherCounter));
            otherCounter++;
        }

        int oldSize = myCon.size();

        this.myCon = newCon;
        return myCon.size() != oldSize;
    }

    private ArrayList<E> mergeSort(ArrayList<E> list) {
        int size = list.size();
        if (size <= 1) {
            return list;
        }
        int newSize = size / 2;
        ArrayList<E> left = mergeSort(new ArrayList<>(list.subList(0, newSize)));
        ArrayList<E> right = mergeSort(new ArrayList<>(list.subList(newSize, size)));
        return merge(left, right);
    }

    private SortedSet<?> checkObject(Object other) {
        if (other == null) {
            throw new IllegalArgumentException();
        }
        if (!(other instanceof SortedSet)) {
            return new SortedSet<>((ISet<E>) other);
        }
        return (SortedSet<?>) other;
    }

    private ArrayList<E> merge(ArrayList<E> listL, ArrayList<E> listR) {
        ArrayList<E> result = new ArrayList<>();

        int rightCounter = 0;
        int leftCounter = 0;

        while (rightCounter < listR.size() && leftCounter < listL.size()) {
            if (listL.get(leftCounter).compareTo(listR.get(rightCounter)) < 0) {
                result.add(listL.get(leftCounter));
                leftCounter++;
            } else {
                result.add(listR.get(rightCounter));
                rightCounter++;
            }
        }

        while (rightCounter != listR.size()) {
            result.add(listR.get(rightCounter));
            rightCounter++;
        }

        while (leftCounter != listL.size()) {
            result.add(listL.get(leftCounter));
            leftCounter++;
        }

        return result;
    }

}