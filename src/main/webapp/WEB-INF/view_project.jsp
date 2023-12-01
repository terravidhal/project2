<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <!-- New line below to use the JSP Standard Tag Library -->
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <!-- New line below to use the JSP Standard Tag Library : Form -->
        <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
            <!-- use format date -->
            <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
                <!-- gestion ds erreurs -->
                <%@ page isErrorPage="true" %>
                    <!DOCTYPE html>
                    <html>

                    <head>
                        <meta charset="UTF-8">
                        <title>Project Details</title>
                        <!-- for Bootstrap CSS -->
                        <link rel="stylesheet" type="text/css" href="/webjars/bootstrap/css/bootstrap.min.css" />
                        <!-- YOUR own local CSS -->
                        <link rel="stylesheet" href="/css/main.css" />
                    </head>

                    <body>
                        <h2><a href="/dashboard">Dashboard</a></h2>

                        <h1>Project Details</h1>
                        <table>
                            <tbody>
                                <tr>
                                    <td>Project: <c:out value="${project.title}"></c:out>
                                    </td>
                                </tr>

                                <tr>
                                    <td>Description: <c:out value="${project.description}"></c:out>
                                    </td>
                                </tr>

                                <tr>
                                    <td>Due Date:
                                        <fmt:formatDate value="${project.dueDate}" pattern="MMMM dd" />
                                    </td>
                                </tr>
                            </tbody>
                        </table>

                        <c:if test="${project.lead.id==userId}">
                            <h2><a href="/projects/delete/${project.id}">Delete Project</a></h2>
                        </c:if>

                        <c:if test="${user.projects.contains(project)}">
                            <h5 style="vertical-align: middle;" class="mt-4">
                                <a href="/projects/${project.id}/tasks" style="text-decoration: none;">See Tasks!</a>
                            </h5>
                        </c:if>


                        <!-- link Js -->
                        <script type="text/javascript" src="/js/main.js"></script>
                        <!-- For any Bootstrap that uses jquery -->
                        <script src="/webjars/jquery/jquery.min.js"></script>
                        <!--For any Bootstrap that uses JS -->
                        <script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
                    </body>

                    </html>