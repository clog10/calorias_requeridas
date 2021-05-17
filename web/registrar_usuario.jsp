<%-- 
    Document   : registrar_usuario
    Created on : 17/05/2021, 12:06:18 AM
    Author     : Carlos Loaeza
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registro</title>
    </head>
    <body>
    <center>
        <h1>Hola, proporciona tus datos</h1>
        <form name="calculo" action="controlUser" method="post">
            <table>
                <thead>
                <th colspan="2" align="center">Datos del Usuario</th>
                </thead>
                <tbody>
                    <tr>
                        <td align="right">Nombre: </td>
                        <td>
                            <input type="text" name="nombre" maxlength="100" required="true">
                        </td>
                    </tr>
                    <tr>
                        <td align="right">Sexo: </td>
                        <td> 
                            <input type="radio" name="sexo" value="M"> Mujer
                            <input type="radio" name="sexo" value="H"> Hombre
                        </td>
                    </tr>
                    <tr>
                        <td align="right">Fecha Registro: </td>
                        <td>
                            <input type="date" name="fecha_reg" required>
                        </td>
                    </tr>
                    <tr>
                        <td align="right">Usuario: </td>
                        <td>
                            <input type="text" name="usuario" required>
                        </td>
                    </tr>
                    <tr>
                        <td align="right">Contraseña: </td>
                        <td>
                            <input type="text" name="contrasenia" required>
                        </td>
                    </tr>
                    <tr>
                        <td align="right">Fecha Nacimiento: </td>
                        <td>
                            <input type="date" name="fecha_nac" required>
                        </td>
                    </tr>
                    <tr>
                        <td align="right">Actividad Física: </td>
                        <td>
                            <select name="actividad">
                                <option value="1">Sedentario (no realiza)</option>
                                <option value="2">Ligero (realiza de 1-3 veces por semana)</option>
                                <option value="3">Moderado (realiza de 3-5 veces por semana)</option>
                                <option value="4">Fuerte (realiza de 6-7 veces por semana)</option>
                            </select>
                        </td> 
                    </tr>
                    <tr>
                        <td colspan="2" align="center"><input type="submit" value="Continuar" ></td>
                    </tr>
                </tbody>
            </table>
        </form>
    </center>
</body>
</html>
