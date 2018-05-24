import com.wavemaker.arraylist.MyArrayList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Main
{
    public static void main(String args[])
    {
        List list = new ArrayList();
        System.out.println(list.contains(null));

        Iterator iterator = list.iterator();
        while (iterator.hasNext()){
            System.out.println(list.get(0));
            list.add(1);
            iterator.next();
        }

        MyArrayList myArrayList = new MyArrayList();
        myArrayList.addAll(Collections.emptyList());

        Object[] objects = new Object[10];
        System.arraycopy(objects,0,objects,4,5);
        System.out.println(objects.length);
    }
}
