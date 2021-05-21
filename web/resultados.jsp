<%-- 
    Document   : resultados
    Created on : 17/05/2021, 12:58:50 AM
    Author     : Carlos Loaeza
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Resultados</title>
    </head>
    <body>
    <center>
        <table>
            <thead>
            <th colspan="2" align="center">Resultados</th>
            </thead>
            <tbody>
                <tr>
                    <td align="right">Nombre: </td>
                    <td>
                        <input type="text" name="nombre" value="${nombre}" readonly="" style="border: 0;">
                    </td>
                </tr>
                <tr>
                    <td colspan="2" align="center">Resultados de los indicadores básicos</td>
                </tr>
                <tr>
                    <td align="right">IMC: </td>
                    <td><input type="text" name="imc" value="${imc}" readonly></td>
                </tr>
                <tr>
                    <td align="right">ICC: </td>
                    <td><input type="text" name="icc" value="${icc}" readonly></td>
                </tr>
            </tbody>
        </table>
        <a href="index.jsp">Volver al inicio</a>
    </center>
</body>
</html>
