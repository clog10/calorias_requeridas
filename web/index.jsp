<%-- 
    Document   : index
    Created on : 12/05/2021, 10:53:52 PM
    Author     : Carlos Loaeza
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
<body>
    <center>
        <h1>Ingresar</h1>
        <form name="acceso" action="login" method="post">
            <table>
                <thead>
                <th colspan="2" align="center">Acceso</th>
                </thead>
                <tbody>
                    <tr>
                        <td align="right">Usuario: </td>
                        <td><input type="text" name="usuario" maxlength="20" required="true"></td>
                    </tr>
                    <tr>
                        <td align="right">Contraseña: </td>
                        <td><input type="password" name="contrasenia" maxlength="20" required="true"></td>
                    </tr>
                    <tr>
                        <td colspan="2" align="center"><input type="submit" value="Acceder" ></td>
                    </tr>
                </tbody>
            </table>
        </form>
        <br>
        <a href="registrar_usuario.jsp">¿No está registrado? Registrese</a>
        
        <form name="actividades" action="controlTA" method="post">
            <br>
            <input type="submit" value="Ver Actividades" >
        </form>
        <form name="usuarios" action="controlU" method="post">
            <br>
            <input type="submit" value="Ver Usuarios Registrados" >
        </form>
    </center>
</body>
</html>
