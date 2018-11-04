<%--
  Created by IntelliJ IDEA.
  User: Pourfex
  Date: 24/10/2018
  Time: 15:06
  To change this template use File | Settings | File Templates.
--%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" isELIgnored="false"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!DOCTYPE html>
<html>
    <head>
    <meta charset="ISO-8859-1">
    <title>Home</title>
    </head>
    <body>

        <h1> Hello in home page</h1>
        <h1> session ! Your username is  : ${sessionScope.username} </h1>

        /
        <h2> ////////////////////////////////////////Datas///////////////////////////////////////</h2>


        <c:forEach var="capteur22" items="${capteur2s}">

            <h2> /////////////////Capteur/////////////////</h2>
            <h2> Capteur2 ville : ${capteur22.ville}</h2>
            <h2> Capteur2 pays : ${capteur22.pays}</h2>
            <h3> Tag = ${capteur22.tag} </h3>

            <c:forEach var="entry" items="${capteur22.data}">

                <h3> /////////////////Data/////////////////</h3>
                <h3> Gps = ${entry.gps} </h3>
                <h3> Type = ${entry.type} </h3>
                <h3> Value = ${entry.value} </h3>
                <h3> Timestamp = ${entry.timestamp} </h3>

            </c:forEach>

        </c:forEach>


        <c:forEach var="capteur222" items="${capteurs}">

            <h2> /////////////////Capteur/////////////////</h2>
            <h2> Capteur2 ville : ${capteur222.ville}</h2>
            <h2> Capteur2 pays : ${capteur222.pays}</h2>
            <h3> Tag = ${capteur222.tag} </h3>

            <c:forEach var="entry" items="${capteur222.data}">

                <h3> /////////////////Data/////////////////</h3>
                <h3> Gps = ${entry.gps} </h3>
                <h3> Type = ${entry.type} </h3>
                <h3> Value = ${entry.value} </h3>
                <h3> Timestamp = ${entry.timestamp} </h3>


            </c:forEach>

        </c:forEach>

        <h1> Aller au filter settings </h1>


        <!-- le go to filtersettings button -->
        <form action="/filter/settings" method="post">
            <input type="hidden" name="user" value=${user} />
            <input type="submit" value="go to filter settings">
        </form >

        <h1> Se deconnecter </h1>


        <!-- le logout button -->
        <form action="/logout" method="post">
             <input type="submit" value="logout">
        </form >

    </body>
</html>