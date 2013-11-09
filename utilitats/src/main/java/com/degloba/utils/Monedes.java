// Decompiled by DJ v3.7.7.81 Copyright 2004 Atanas Neshkov  Date: 03/03/2005 12:49:46
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   Monedes.java

package com.degloba.utils;

import java.math.BigDecimal;

import java.util.Hashtable;


public class Monedes
{


    public static String Pts_Euros(String pts)
    {
        if(pts == null || pts == "")
            return "";
        else
            return Pts_Euros(new BigDecimal(pts)).toString();
    }

    public static String Euros_Pts(String euros)
    {
        if(euros == null || euros == "")
            return "";
        else
            return Pts_Euros(new BigDecimal(euros)).toString();
    }

    public static BigDecimal Pts_Euros(BigDecimal pts)
    {
        if(pts == null)
            return null;
        else
            return pts.divide(new BigDecimal(166.386D), 2, 4);
    }

    public static BigDecimal Euros_Pts(BigDecimal euros)
    {
        if(euros == null)
            return null;
        else
            return euros.multiply(new BigDecimal(166.386D)).setScale(0, 4);
    }

    public static void main(String a[])
    {
        System.out.println(Euros_Pts("10.6"));
    }

    Hashtable<?, ?> hTable;
}