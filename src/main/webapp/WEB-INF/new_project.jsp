<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <!-- New line below to use the JSP Standard Tag Library -->
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <!-- New line below to use the JSP Standard Tag Library : Form -->
        <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
            <!-- gestion ds erreurs -->
            <%@ page isErrorPage="true" %>
                <!DOCTYPE html>
                <html>

                <head>
                    <meta charset="UTF-8">
                    <title>New Project</title>
                    <!-- for Bootstrap CSS -->
                    <link rel="stylesheet" type="text/css" href="/webjars/bootstrap/css/bootstrap.min.css" />
                    <!-- YOUR own local CSS -->
                    <link rel="stylesheet" href="/css/main.css" />
                </head>

                <body>
                    <h2><a href="/dashboard">Dashboard</a></h2>

                    <h1>Create a new project</h1>

                    <form:form action="/projects/new" method="post" modelAttribute="project">

                        <table>
                            <thead>
                                <tr>
                                    <td class="float-left">Project Title:</td>
                                    <td class="float-left">
                                        <form:errors path="title" class="text-danger" />
                                        <form:input class="input" path="title" />
                                    </td>
                                </tr>
                                <tr>
                                    <td class="float-left">Project Description:</td>
                                    <td class="float-left">
                                        <form:errors path="description" class="text-danger" />
                                        <form:textarea rows="4" class="input" path="description" />
                                    </td>
                                </tr>
                                <tr>
                                    <td class="float-left">Due Date:</td>
                                    <td class="float-left">
                                        <form:errors path="dueDate" class="text-danger" />
                                        <form:input path="dueDate" type="date" />
                                    </td>
                                </tr>

                                <form:errors path="lead" class="error" />
                                <form:input type="hidden" path="lead" value="${userId}" class="form-control" />

                                <tr>
                                    <td><a class="linkBtn" href="/dashboard">Cancel</a></td>
                                    <td><input class="input" type="submit" value="Submit" /></td>
                                </tr>
                            </thead>
                        </table>
                    </form:form>


                    <!-- link Js -->
                    <script type="text/javascript" src="/js/main.js"></script>
                    <!-- For any Bootstrap that uses jquery -->
                    <script src="/webjars/jquery/jquery.min.js"></script>
                    <!--For any Bootstrap that uses JS -->
                    <script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
                </body>

                </html>