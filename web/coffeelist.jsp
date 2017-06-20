<%-- 
    Document   : coffeelist
    Created on : 09.06.2017, 10:38:49
    Author     : vadim.shakirov
--%>

<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <style>
      BODY { font-family: verdana, arial; font-size: 11px; color: black; margin: 10px;}      

      TABLE { font-family: verdana, arial; font-size: 12px; margin: 0px; border: 0px;}

      TH { padding: 5px 5px 5px 5px;}

      TD { padding: 2px 5px 2px 5px; height: 23px;}

      INPUT.field { height: 18px; margin: 0px; font-family: verdana, arial; font-size: 12px;}
    </style>
  </head>
  <body>
      <form name="coffeeform" action="orderlist.html" method="GET">
      <table cellspacing="0px" cellpadding="0px" border="0px" style="border: 1px #B0B0B0 solid">
        <tr style="background-color: #C0C0C7">
          <th/>
          <th>Название</th>
          <th>Цена</th>
          <th>Количество</th>
        </tr>
        <c:forEach items="${coffeeTypeList}" var="coffeeType" >
            <c:if test="${coffeeType.disabled != 'Y'.charAt(0)}">
                <tr style="background-color: #F0F0F0">
                  <input type="hidden" name="id" value="${coffeeType.id}"  />
                  <td><input type="checkbox" name="check${coffeeType.id}" checked="true" /></td>
                  <td nowrap="true">${coffeeType.name}</td>
                  <td nowrap="true">${coffeeType.price} TGR</td>
                  <td align="right"><input type="text" name="count${coffeeType.id}" size="5" value="3"/></td>
                </tr>
                </c:if>
        </c:forEach>
        <tr style="background-color: #F0F0F0">
          <td colspan="4" align="right"><input type="submit" value="Заказать"/></td>
        </tr>
      </table>
      <font color="red">*</font> - каждая третья чашка бесплатно.
    </form>
  </body>
</html>
