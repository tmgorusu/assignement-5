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

    public boolean contains(E item){
        for (E otherItem : this) {
            if (otherItem.equals(item)) {
                return true;
            }
        }
        return false;
    }

    public boolean containsAll(ISet<E> otherSet){
        for (E otherItem : otherSet) {
            if (!(this.contains(otherItem))) {
                return false;
            }
        }
        return true;
    }

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

    // Finish this
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