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

import javax.swing.BorderFactory;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.table.TableCellRenderer;
import java.awt.Component;
import javax.swing.JComponent;

public class MyTable extends JTable 
{
     public MyTable(MyTablePayModel m)
     {
          super(m);
          setRowHeight(25);
     }
     // [snip]

     private Border paddingBorder = BorderFactory.createEmptyBorder(5, 10, 5, 10); //top,left,bottom,right

     // [snip]

     //@Override
     public Component prepareRenderer(TableCellRenderer renderer, int row, int column) 
     {
          Component comp = super.prepareRenderer(renderer, row, column);

          if (JComponent.class.isInstance(comp))
          {
               ((JComponent)comp).setBorder(paddingBorder);
          }
          return comp;
          // [snip]
     }

    // [snip]

}