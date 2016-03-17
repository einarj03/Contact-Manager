package com.example.einarjonsson.myapplication;

import java.util.Comparator;

/**
 * Created by einarjonsson on 17/03/2016.
 */

//implements Comparator to sort the array list
public class ContactNameComparator implements Comparator<Contact>
{
    public int compare(Contact c1, Contact c2) {
        return c1.getName().compareTo(c2.getName());
    }


}
