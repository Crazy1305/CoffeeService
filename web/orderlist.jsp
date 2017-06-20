
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
        <form name="coffeeform" action="order.html" method="POST" accept-charset="utf-8">
            <table c<table cellspacing="0px" cellpadding="2px" border="0px" style="border: 1px #B0B0B0 solid">
                <tr style="background-color: #C0C0C7">
                    <th colspan="2">Доставка</th>
                </tr>
                <tr style="background-color: #F0F0F0">
                    <td><b>ФИО</b></td>
                    <td nowrap="true"><input name="name" class="field" type="text" size="30" onfocus="blur();" value="Иванов Пётр Сидорович"/></td>
                </tr>
                <tr style="background-color: #F0F0F0">
                    <td><b>Адрес</b></td>
                    <td nowrap="true"><input name="address" class="field" type="text" size="30" onfocus="blur();" value="Ивановская 10, д. 2"/></td>
                </tr>
                <tr style="background-color: #F0F0F0">
                    <td colspan="2" align="right"><input type="submit" value="Заказать"/></td>
                </tr>
            </table>
        </form>
        <br/>
        <table cellspacing="0px" cellpadding="2px" border="0px" style="border: 1px #B0B0B0 solid">
            <tr style="background-color: #C0C0C7">
                <th>Название</th>
                <th>Цена</th>
                <th>Количество</th>
                <th>Всего</th>
            </tr>
            <% int i = 0; %>
            <c:set var="order" scope="session" value="${orderDao}"/>
            <c:forEach items="${order.items()}" var="orderItem" >
                <% if ((i++ % 2) == 0) {%>
                    <tr style="background-color: #F0F0F0">
                <%} else {%>
                    <tr style="background-color: #E0E0E0">
                <% } %>
                    <td nowrap="true">${orderItem.getType().getName()}</td>
                    <td nowrap="true">${orderItem.getType().getPrice()} TGR</td>
                    <td align="right">${orderItem.getQuantity()}</td>
                    <td><font color="red">${orderItem.getType().getPrice() * orderItem.getQuantity()}</font> TGR</td>
                </tr>
            </c:forEach>
            <% if ((i++ % 2) == 0) {%>
                <tr style="background-color: #F0F0F0">
            <%} else {%>
                <tr style="background-color: #E0E0E0">
            <% } %>
                <td colspan="3" align="right"><b>Сумма:</b></td>
                <c:set var="cost" value="${order.getOrder().getCost()}"/>
                <td align="right"><c:out value="${cost}"/> TGR</td>
            </tr>
            <% if ((i++ % 2) == 0) {%>
                <tr style="background-color: #F0F0F0">
            <%} else {%>
                <tr style="background-color: #E0E0E0">
            <% } %>
                <td colspan="3" align="right"><b>Доставка:</b></td>
                <c:set var="deliveryCost" value="${cost*5/100}" />
                <td align="right"><c:out value="${deliveryCost}"/> TGR</td>
            </tr>
            <% if ((i++ % 2) == 0) {%>
                <tr style="background-color: #F0F0F0">
            <%} else {%>
                <tr style="background-color: #E0E0E0">
            <% } %>
                <td colspan="3" align="right"><b>Всего:</b></td>
                <td align="right"><c:out value="${cost + deliveryCost}"/> TGR</td>
            </tr>

        </table>
        <font color="red">*</font> - каждая третья чашка бесплатно.
        
        <a href="coffeelist.html"><link>Вернуться в магазин</link></a>
    </body>
</html>
