<?xml version='1.0' encoding='ISO-8859-1' ?>
<!DOCTYPE helpset
  PUBLIC "-//Sun Microsystems Inc.//DTD JavaHelp HelpSet Version 1.0//EN"
         "http://java.sun.com/products/javahelp/helpset_1_0.dtd">

<helpset version="2.0">
     <title>title</title>
     
     <maps>
     <homeID>overview</homeID>
          <mapref location="map.jhm"/>
     </maps>

     <view>
          <name>TOC</name>
          <label>Table Of Contents</label>
          <type>javax.help.TOCView</type>
          <data>toc.xml</data>
     </view>

     <presentation default="true">
          <name>Main Window</name>
          <size width="640" height="480" />
          <!--<location x="640" y="480" />-->
          <title>Trash Biller</title>
          <image>icon</image>
          <toolbar>
               <helpaction>javax.help.BackAction</helpaction>
               <helpaction  image="addfav_icon">javax.help.FavoritesAction</helpaction>
          </toolbar>
     </presentation>
</helpset>