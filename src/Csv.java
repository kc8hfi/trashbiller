/*
 * Copyright 2013 Charles Amey
 *
 * This file is part of Trashbiller.
 *
 * Trashbiller is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Trashbiller is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Trashbiller.  If not, see<http://www.gnu.org/licenses/>.
*/

import java.util.ArrayList;

public class Csv
{
     public Csv()
     {
     }
     
     /*
      * combine takes an arraylist and puts quotes around each element and
      * returns a string with the quoted items
      */
     public String combine(ArrayList<String> v)
     {
          String s = "";
          for (int i=0;i<v.size();i++)
          {
               s = s + "\"" + v.get(i) + "\",";
          }
          s = s.substring(0,s.length()-1);
          return s;
     }
     
     public ArrayList<String> parse(String s)
     {
          ArrayList<String> list = new ArrayList<String>();
          char [] arr = s.toCharArray();
          int length = arr.length;
          char oldchar = ' ';
          String item = "";
          int count=1;
          System.out.println("parse this: " + s);
          for ( char c : arr )
          {
               System.out.println(c);
               if (c == '"')
               {
                    if (oldchar == '"')
                    {
                         //item += Character.toString(c);
                         System.out.println("item is: " + item);
                         System.out.println("both char is a quote??");
                         list.add(item);
                    }
                    else if (length == count)
                    {
                         //replace || with the new line character
                         item = item.replaceAll(";;",System.getProperty("line.separator"));
                         //System.out.println("item length=count: " + item);
                         list.add(item);
                    }
                    //else
                         //list.add(item);
               }
               else if (c == ',')
                    {
                         if (oldchar != '"')
                         {
                              item += Character.toString(c);;
                         }
                         else
                         {
                              //replace || with the new line character
                              item = item.replaceAll(";;",System.getProperty("line.separator"));
                              list.add(item);
                              item = "";
                         }
                    }
                    else
                    {
                         item += Character.toString(c);
                    }
               oldchar = c;
               count++;
          }
          return list;
     }//end parseme
}//end class Csv
