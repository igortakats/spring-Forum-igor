<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<sf:form modelAttribute="usuario" action="executarRegistro" enctype="multipart/form-data">

	<label for="nome">Nome:<sf:errors path="nome"/></label>
	<sf:input path="nome"/>
	
	<label for="email">Email:<sf:errors path="email"/></label>
	<sf:input path="email"/>
	
	<label for="login">Nome do usuario (login):<sf:errors path="login"/></label>
	<sf:input path="login"/>
	
	<label for="senha">Senha:<sf:errors path="senha"/></label>
	<sf:password path="senha"/>
	
	<label for="avatar">Avatar:</label>
	<sf:input name="avatar" type="file" />
	
	<input type="submit" value="Faça parte!"/>

</sf:form>