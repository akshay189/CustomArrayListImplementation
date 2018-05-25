package com.wavemaker.arraylist;

import java.util.*;
import java.util.function.Consumer;

//import java.util.function.Consumer;

public class MyArrayList<T> implements List<T> {


    transient int modcount = 0;

    public int defaultCapacity = 10, currentCapacity = 10;
    public int size = 0;
    public Object[] buffer;

    public MyArrayList() {
        buffer = new Object[defaultCapacity];
    }

    public MyArrayList(int size) {
        currentCapacity = size;
        buffer = new Object[size];
    }

    /**
     * size of the arrayList
     */
    public int size() { //done testing...
        return size;
    }

    public boolean isEmpty() {// done testing....
        return size == 0;
    }

    public boolean contains(Object o) {// done testing
        return indexOf(o) >= 0;
    }

    /**
     * Returns an iterator over the elements in this list in proper sequence.
     *
     * @return an iterator over the elements in this list in proper sequence
     */
    public Iterator<T> iterator() {
        return new Itr();
    }

    public Object[] toArray() {
        Object[] array = new Object[size];
        System.arraycopy(buffer, 0, array, 0, size);
        return array;
    }

    public boolean add(T o) {
        if (size == currentCapacity) {
            resize(10);
        }
        buffer[size] = o;
        modcount++;
        size++;
        return true;
    }

    public boolean remove(Object o) {
        int pos = -1;
        if (o == null) {
            for (int i = 0; i < size; i++) {
                if (buffer[i] == null) {
                    pos = i;
                    break;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (o.equals(buffer[i])) {
                    pos = i;
                    break;
                }
            }
        }
        if (pos != -1) {
            System.arraycopy(buffer, pos + 1, buffer, pos, size - pos - 1);
            modcount++;
            size--;
            return true;
        }
        return false;
    }

    public boolean addAll(Collection<? extends T> c) {
        if (c == null)
            throw new NullPointerException("input is null");
        if (c.size() != 0) {
            Object[] a = c.toArray();
            for (int i = 0; i < c.size(); i++) {
                add((T)a[i]);
            }
            return true;
        }
        return false;
    }

    public boolean addAll(int index, Collection<? extends T> c) {
        if (c == null)
            throw new NullPointerException("input is null");
        rangeCheck(index);
        if (c != null) {
            int collectionLength = c.size();
            Object[] srcArray = c.toArray();
            if (currentCapacity <= size + collectionLength) {
                int requiredBufferCapacity = size + collectionLength - currentCapacity + 1;
                resize(requiredBufferCapacity);
            }
            modcount++;
            System.arraycopy(buffer, index, buffer, index + c.size(), size - index);
            System.arraycopy(srcArray, 0, buffer, index, c.size());
            size = size + c.size();
            return true;
        }
        return false;
    }

    public void clear() {
        for (int i = 0; i < size; i++) {
            buffer[i] = null;
        }
        modcount++;
        size = 0;
        currentCapacity = defaultCapacity;
    }

    public T get(int index) {
        rangeCheck(index);
        return (T)buffer[index];
    }

    public T set(int index, T element) {
        rangeCheck(index);
        T oldElement = (T)buffer[index];
        buffer[index] = element;
        return oldElement;
    }

    public void add(int index, T element) {
        rangeCheck(index);
        if (size == currentCapacity) {
            resize(10);
        }
        modcount++;
        size++;
        System.arraycopy(buffer, index, buffer, index + 1, size - index);
        buffer[index] = element;
    }

    public T remove(int index) {
        rangeCheck(index);
        T removedElement = (T)buffer[index];
        if (size == 1)
            clear();
        else {

            modcount++;
            System.arraycopy(buffer, index + 1, buffer, index, size - index - 1);
            size--;
        }
        return removedElement;
    }

    public int indexOf(Object o) {
        if (o == null) {
            for (int i = 0; i < size; i++) {
                if (buffer[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (o.equals(buffer[i])) {
                    return i;
                }
            }
        }
        return -1;
    }
    public int lastIndexOf(Object o) {
        if (o == null) {
            for (int index = size - 1; index >= 0; index--) {
                if (buffer[index] == null) {
                    return index;
                }
            }
        } else {
            for (int index = size - 1; index >= 0; index--) {
                if (o.equals(buffer[index])) {
                    return index;
                }
            }
        }
        return -1;
    }
    @Override
    public ListIterator<T> listIterator() {
        return null;
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        return null;
    }

    public List<T> subList(int fromIndex, int toIndex) {
        List<T> l = new MyArrayList<>();
        if(fromIndex == toIndex)return l;
        validCheck(fromIndex, toIndex);
        rangeCheck(fromIndex);
        /* as toIndex is exclusive just decrement it by 1 so that it become inclusive for rangeCheck.. */
        rangeCheck(toIndex - 1);
        MyArrayList<T> subList = new MyArrayList<>();
        for (; fromIndex < toIndex; fromIndex++) {
            subList.add((T)buffer[fromIndex]);
        }
        return subList;
    }

    private void resize(int limit) {
        Object[] tempBuffer = new Object[currentCapacity + limit];
        System.arraycopy(buffer, 0, tempBuffer, 0, currentCapacity);
        currentCapacity += 10;
        buffer = tempBuffer;
    }

    private void validCheck(int index1, int index2) {
        if (index1 > index2) {
            throw new IllegalArgumentException("fromIndex :" + index1 + " >" + " toIndex :" + index2);
        }
    }

    private boolean batchRemove(Collection<?> c, boolean flag) {
        if (c == null)
            throw new NullPointerException("input is null");
        int currentIndex = 0, cursor = 0;
        if (size == 0)
            return false;
        try {
            for (currentIndex = 0; currentIndex < size; currentIndex++) {
                if (c.contains(buffer[currentIndex]) == flag) {
                    buffer[cursor++] = buffer[currentIndex];
                }
            }
        } finally {
            if (currentIndex != size) {
                if (currentIndex == 0 && size == 1)
                    clear();
                else {
                    System.arraycopy(buffer, currentIndex + 1, buffer, currentIndex, size - currentIndex - 1);
                    cursor = cursor + size - currentIndex - 1;
                }
            }
            if (cursor != size) {
                for (currentIndex = cursor; currentIndex < size; currentIndex++) {
                    buffer[currentIndex] = null;
                }
                size = cursor;
                return true;
            }
        }
        return false;
    }

    public boolean retainAll(Collection<?> c) {

        return batchRemove(c, true);
    }

    public boolean removeAll(Collection<?> c) {
        return batchRemove(c, false);
    }

    private void rangeCheck(int index) {
        if (index < 0 || index >=size)
            throw new IndexOutOfBoundsException("Index :" + index + " size" + size);
    }

    public boolean containsAll(Collection<?> c) {
        if (c == null)
            throw new NullPointerException("input is null");
        if (size == 0)
            return false;
        /*
         * ArrayList handles null values so not a problem with null pointer exception as ArrayList permits Null values.. */
        Object[] srcArray = c.toArray();
        for (Object element : srcArray)
            if (indexOf(element) < 0) {
                return false;
            }
        return true;
    }

    public<T> T[] toArray(T[] a) {
        if (a.length < size)
            return (T[]) Arrays.copyOf(buffer, size);
        else {
            System.arraycopy(buffer, 0, a, 0, size);
            if (a.length > size)
                a[size] = null;
        }
        return a;
    }

    private class Itr implements Iterator<T> {

        int cursor = 0, lastReturnedIndex = -1, expectedModcount = modcount;
        Object[] itrBuffer;

        private void checkForComodification() {
            if (expectedModcount != modcount)
                throw new ConcurrentModificationException();
        }

        Itr() {
            itrBuffer = new Object[size];
            System.arraycopy(buffer, 0, itrBuffer, 0, size);
        }

        @Override
        public boolean hasNext() {
            return (cursor != size);
        }

        @Override
        public T next() {

            checkForComodification();
//            throwing NoSuchElementException if iterator has no elements
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            lastReturnedIndex = cursor;
            cursor += 1;
            return (T)itrBuffer[lastReturnedIndex];
        }

        @Override
        public void remove() {
            if (lastReturnedIndex < 0) {
                throw new IllegalStateException("No next operation is performed");
            }
            checkForComodification();
            try {
                MyArrayList.this.remove(lastReturnedIndex);
                cursor = lastReturnedIndex;
                lastReturnedIndex = -1;
                expectedModcount = modcount;
            } catch (IndexOutOfBoundsException ex) {
                throw new ConcurrentModificationException();
            }
        }

        @Override
        public void forEachRemaining(Consumer action) {
//            TODO
        }

    }
}