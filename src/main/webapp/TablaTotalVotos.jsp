<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Votos de Jugadores</title>
</head>
<body>

<h1>Votos de Jugadores</h1>

<table border="1">
    <tr>
        <th>Nombre</th>
        <th>Votos</th>
    </tr>
    <%
    List<String> jugadores = (List<String>) request.getAttribute("jugadores");
    List<Integer> votos = (List<Integer>) request.getAttribute("votos");
    for (int i = 0; i < jugadores.size(); i++) {
    %>
    <tr>
        <td><%= jugadores.get(i) %></td>
        <td><%= votos.get(i) %></td>
    </tr>
    <%
    }
    %>
</table>

</body>
</html>