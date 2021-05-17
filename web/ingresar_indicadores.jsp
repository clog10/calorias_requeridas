<%-- 
    Document   : ingresar_indicadores
    Created on : 17/05/2021, 01:01:56 AM
    Author     : Carlos Loaeza
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Indicadores</title>
    </head>
    <body>
    <center>
        <form action="controlIS" method="post">
            <table>
                <thead>
                <th colspan="2" align="center">Datos del Usuario</th>
                </thead>
                <tbody>
                    <tr>
                        <td align="right">Nombre: </td>
                        <td>
                            <input type="text" name="nombre" value="${nombre}" readonly="" style="border: 0;">
                        </td>
                    </tr>
                    <tr>
                        <td align="right">Actividad: </td>
                        <td>
                            <input type="text" name="actividad" value="${actividad}" readonly="" style="border: 0;">
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2" align="center">Ingresa tus datos</td>
                    </tr>
                    <tr>
                        <td align="right">Fecha: </td>
                        <td>
                            <input type="date" name="fecha" required>
                        </td>
                    </tr>
                    <tr>
                        <td align="right">Estatura (en cm): </td>
                        <td>
                            <input type="number" name="estatura" required min="140" max="200">
                        </td>
                    </tr>
                    <tr>
                        <td align="right">Peso (en kg): </td>
                        <td>
                            <input type="number" name="peso" required min="45" max="120">
                        </td>
                    </tr>
                    <tr>
                        <td align="right">Cintura (en cm): </td>
                        <td>
                            <input type="number" name="cintura" required min="45" max="200">
                        </td>
                    </tr>
                    <tr>
                        <td align="right">Cadera: </td>
                        <td>
                            <input type="number" name="cadera" required min="45" max="120">
                        </td>
                    </tr>
                    <tr>
                        <td align="right">¿Guardar en BD?: </td>
                        <td> 
                            <input type="radio" name="guardar" value="S"> Si
                            <input type="radio" name="guardar" value="N"> No
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2" align="center"><input type="submit" value="Calcular" ></td>
                    </tr>
                </tbody>
            </table>
        </form>
    </center>
</body>
</html>
