package com.wavemaker.arraylist;


import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.*;

public class ArrayListTestCases {

    @Test
    public void testSize() {
        MyArrayList myArrayList = new MyArrayList();
        myArrayList.add(1);
        myArrayList.add(2);
        myArrayList.add(3);
        myArrayList.add(4);
        myArrayList.add(5);

        Assert.assertEquals(5, myArrayList.size());
        myArrayList.clear();
        Assert.assertEquals(0, myArrayList.size());
    }

    @Test
    public void testIsEmpty() {
        MyArrayList myArrayList = new MyArrayList();
        myArrayList.add("a");
        Assert.assertFalse(myArrayList.isEmpty());
        myArrayList.clear();
        Assert.assertTrue(myArrayList.isEmpty());
    }

    @Test
    public void testContains() {
        MyArrayList myArrayList = new MyArrayList();
        myArrayList.add(1);
        myArrayList.add(2);
        myArrayList.add("a");
        Assert.assertFalse(myArrayList.contains(10));
        Assert.assertTrue(myArrayList.contains("a"));
    }

    @Test
    public void testToArrayWithInputBuffer() {

        MyArrayList myArrayList = new MyArrayList();
        myArrayList.add(1);
        myArrayList.add(2);
        myArrayList.add(3);
        myArrayList.add("a");

        Object[] sampleArray = new Object[10];
        myArrayList.toArray(sampleArray);
        Assert.assertEquals("a", sampleArray[myArrayList.indexOf("a")]);
        Assert.assertEquals(null, sampleArray[myArrayList.size]);

//TODO write comments
        sampleArray = myArrayList.toArray(new Object[1]);
        Assert.assertEquals(myArrayList.size, Arrays.asList(sampleArray).size());
    }

    @Test
    public void testToArray() {
        MyArrayList myArrayList = new MyArrayList();
        Object[] outputArray = myArrayList.toArray();
        Assert.assertEquals(Arrays.asList(outputArray).size(), myArrayList.size);
    }

    @Test
    public void testAddAllAtIndex() {
        MyArrayList myArrayList = new MyArrayList();
        myArrayList.add(1);
        myArrayList.add(4);
        myArrayList.add(5);
        int sizeBeforeAdding = myArrayList.size();
        List<Integer> sampleList = Arrays.asList(2, 3);

        myArrayList.addAll(1, sampleList);
        Assert.assertEquals(2, myArrayList.get(1));
        Assert.assertEquals(sizeBeforeAdding + sampleList.size(), myArrayList.size());
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testAddAllNullPointerException() {
        MyArrayList myArrayList = new MyArrayList();
        myArrayList.add(1);
        myArrayList.add(4);
        myArrayList.add(5);
        myArrayList.addAll(0, null);
    }


    @Test
    public void testClear() {
        MyArrayList myArrayList = new MyArrayList();
        myArrayList.clear();
        Assert.assertEquals(0, myArrayList.size());
    }

    @Test
    public void tesGetWithIndex() {
        MyArrayList myArrayList = new MyArrayList();
        myArrayList.add(1);
        myArrayList.add(2);
        Assert.assertEquals(1, myArrayList.get(0));
    }

    @Test(expectedExceptions = IndexOutOfBoundsException.class)
    public void testGet() {
        MyArrayList myArrayList = new MyArrayList();
        myArrayList.get(-20);
        myArrayList.get(36);
    }

    @Test
    public void testSet() {
        MyArrayList myArrayList = new MyArrayList();
        myArrayList.add(1);
        Assert.assertEquals(1, myArrayList.set(0, 24));
    }

    @Test(expectedExceptions = IndexOutOfBoundsException.class)
    public void testSetAtIndex() {
        MyArrayList myArrayList = new MyArrayList();
        myArrayList.add(0);
        myArrayList.set(1, 10);
    }

    @Test
    public void testAddAtIndex() {
        MyArrayList myArrayList = new MyArrayList();
        myArrayList.add(1);
        myArrayList.add(2);
        myArrayList.add(0, 5);
        Assert.assertEquals(3, myArrayList.size());
    }

    @Test(expectedExceptions = IndexOutOfBoundsException.class)
    public void testAddAtIndexException() {
        MyArrayList myArrayList = new MyArrayList();
        myArrayList.add(12, 15);
    }

    @Test
    public void testRemove() {
        MyArrayList myArrayList = new MyArrayList();
        myArrayList.add(1);
        myArrayList.add(2);
        Assert.assertEquals(1, myArrayList.remove(0));
        Assert.assertEquals(1, myArrayList.size());
    }

    @Test(expectedExceptions = IndexOutOfBoundsException.class)
    public void removeAtIndexCheck() {
        MyArrayList myArrayList = new MyArrayList();
        myArrayList.add(1);
        myArrayList.add(2);
        myArrayList.remove(3);
    }


    @Test
    public void testIndexOf() {

        MyArrayList myArrayList = new MyArrayList();
        myArrayList.add(1);
        myArrayList.add(2);
        myArrayList.add(3);
        myArrayList.add(4);
        myArrayList.add(5);
        Assert.assertEquals(0, myArrayList.indexOf(1));
        Assert.assertEquals(1, myArrayList.indexOf(2));
        Assert.assertEquals(myArrayList.indexOf(5), myArrayList.lastIndexOf(5));

    }

    @Test
    public void testLastIndeOf() {
        MyArrayList myArrayList = new MyArrayList();
        myArrayList.add(1);
        myArrayList.add(2);
        myArrayList.add(3);
        myArrayList.add(4);
        myArrayList.add(5);

        Assert.assertEquals(0, myArrayList.lastIndexOf(1));
        Assert.assertEquals(1, myArrayList.lastIndexOf(2));
        Assert.assertEquals(-1, myArrayList.indexOf(111));
        myArrayList.add(2);
        Assert.assertEquals(5, myArrayList.lastIndexOf(2));
        Assert.assertEquals(myArrayList.indexOf(1), myArrayList.lastIndexOf(1));
    }

//    TODO Check the logic
    @Test
    public void testSubList() {

        MyArrayList myArrayList = new MyArrayList();
        myArrayList.add(1);

//
        Assert.assertEquals(Arrays.asList(), myArrayList.subList(0, 0));
        myArrayList.add(2);
        Assert.assertEquals(Arrays.asList(1,2),myArrayList.subList(0,2));
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testSubListValidCheck() {
        MyArrayList myArrayList = getSampleList();
        /*
         * In our case size implies number of elements and indexes up to size -1
         * and sublist handles first Index as inclusive and second as exclusive
         * so from index == to index should fail.. In our implementation
         * */
        myArrayList.subList(10, 10);
    }

    @Test(expectedExceptions = IndexOutOfBoundsException.class)
    public void testSubListRangeCheck() {
        MyArrayList myArrayList = new MyArrayList();
        myArrayList.add(1);
        myArrayList.add(2);
        myArrayList.add(3);
        myArrayList.add(4);
        myArrayList.add(5);
        myArrayList.subList(3, 6);
    }

    @Test
    public void testRetainAll() {
        MyArrayList myArrayList = new MyArrayList();
        myArrayList.add(1);
        myArrayList.add(2);
        myArrayList.add(3);
        myArrayList.add(4);
        myArrayList.add(5);

        List<Integer> retainElementsList = Arrays.asList(1, 2, 7, 8);
        Assert.assertEquals(true, myArrayList.retainAll(retainElementsList));
        Assert.assertEquals(true, myArrayList.retainAll(Arrays.asList(9, 10)));

        myArrayList.stream().forEach(element -> {
            Assert.assertEquals(true, retainElementsList.contains(element));
        });

    }

    @Test
    public void testRemoveAll() {
        MyArrayList myArrayList = new MyArrayList();
        myArrayList.add(1);
        myArrayList.add(2);
        myArrayList.add(3);
        myArrayList.add(4);
        myArrayList.add(5);
        List<Integer> sampleList = Arrays.asList(1, 2, 3, 9);
        Assert.assertEquals(true, myArrayList.removeAll(sampleList));
        Assert.assertEquals(false, myArrayList.removeAll(Arrays.asList(11, 12)));
        myArrayList.stream().forEach(element -> {
            Assert.assertEquals(false, sampleList.contains(element));
        });
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testRemoveAllException() {
        MyArrayList myArrayList = new MyArrayList();
        myArrayList.add(1);
        myArrayList.add(2);
        myArrayList.add(3);
        myArrayList.add(4);
        myArrayList.add(5);
        myArrayList.removeAll(null);
    }

    @Test
    public void testIterator() {
        MyArrayList myArrayList = getSampleList();
        myArrayList = getSampleList();
        Iterator it = myArrayList.iterator();
        Assert.assertTrue(it.hasNext());
        Assert.assertEquals(1, it.next());
        it.remove();
        Assert.assertEquals(4, myArrayList.size());
        myArrayList.clear();
    }

    @Test(expectedExceptions = NoSuchElementException.class)
    public void testExceptionInIteratorNext() {
        MyArrayList myArrayList = getSampleList();
        myArrayList = getSampleList();
        Iterator itr = myArrayList.iterator();
        while (itr.hasNext()) {
            itr.next();
        }
        itr.next();
    }

    @Test
    public void testIteratorRemoveWithoutException() {
        MyArrayList myArrayList = new MyArrayList();
        myArrayList = getSampleList();
        Iterator itr = myArrayList.iterator();
        Assert.assertTrue(itr.hasNext());
        Assert.assertEquals(1, itr.next());
        itr.remove();
        Assert.assertEquals(4, myArrayList.size());
        myArrayList.clear();

    }

    @Test(expectedExceptions = IllegalStateException.class)
    public void testIteratorRemove() {
        MyArrayList myArrayList = new MyArrayList();
        Iterator Itr = myArrayList.iterator();
        Itr.remove();
    }

    @Test(expectedExceptions = ConcurrentModificationException.class)
    public void testIteratorConcurrentModification() {
        MyArrayList myArrayList = getSampleList();
        Iterator it = myArrayList.iterator();
        Assert.assertEquals(1, it.next());
        myArrayList.remove(0);
        it.next();
    }

    @Test
    public void testContainsAll() {

        MyArrayList myArrayList = new MyArrayList();
        myArrayList.add(1);
        myArrayList.add(2);
        myArrayList.add(3);
        myArrayList.add(4);
        myArrayList.add(5);
        Assert.assertFalse(myArrayList.containsAll(Arrays.asList(111,3222)));
    }

    private MyArrayList getSampleList() {
        MyArrayList myArrayList = new MyArrayList();
        myArrayList.add(1);
        myArrayList.add(2);
        myArrayList.add(3);
        myArrayList.add(4);
        myArrayList.add(5);
        return myArrayList;
    }

    @Test
    public void testEmployeeAdd() {
        List<Employee> sampleList = new ArrayList<>();
        Employee employee1 = new Employee(1, "Akshay", "Kotagiri");
        Employee employee2 = new Employee(2, "Ram", "Rangu");
        Employee employee3 = new Employee(1, "Akshay", "Kumar");
        sampleList.add(employee1);
        sampleList.add(employee2);
        /* will not allow to add other types because of generics
         * sampleList.add(10);
         * */
        Assert.assertNotEquals(employee1, employee2);
        Assert.assertEquals(employee1, employee3);
        Assert.assertEquals(employee1.hashCode(), employee3.hashCode());
    }

    @Test
    public void testEmployeeRemove() {
        List<Employee> sampleList = new ArrayList<>();
        Employee employee1 = new Employee(1, "Akshay", "Kotagiri");
        Employee employee2 = new Employee(2, "Ram", "Rangu");
        Employee employee3 = new Employee(1, "Akshay", "Kumar");
        sampleList.add(employee1);
        sampleList.add(employee2);
        sampleList.add(employee3);
        Assert.assertEquals(new Employee(1, "Akshay", "Kotagiri"), sampleList.get(0));
        /*
         * Removing Employee Object employee1 at Index 0*/
        sampleList.remove(0);
        /*
         * But assertion will be true becuase employee3 and employee1 are equal
         * and our overloaded .equals will executed.. */
        Assert.assertTrue(sampleList.contains(employee1));
        Employee Obj4 = sampleList.set(0, new Employee(3, "Shivaji", "Reddy"));
        System.out.println(Obj4.getFirstName());
    }
}
