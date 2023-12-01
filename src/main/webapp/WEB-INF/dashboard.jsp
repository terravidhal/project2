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
                    <title>Project Manager Dashboard</title>
                    <!-- for Bootstrap CSS -->
                    <link rel="stylesheet" type="text/css" href="/webjars/bootstrap/css/bootstrap.min.css" />
                    <!-- YOUR own local CSS -->
                    <link rel="stylesheet" href="/css/main.css" />
                </head>

                <body>
                    <h1>Project Manager Dashboard</h1>
                    <p>Welcome,<c:out value="${user.firstName}"/></p>
                    <p><a href="/logout">Log Out</a></p>
                    <p><a href="/projects/new">Add Project</a></p>

                    <h4>All Projects</h4>

                    <table>
                        <thead>
                            <tr>
                                <th>Project</th>
                                <th>Team Lead</th>
                                <th>Due Date</th>
                                <th>Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="project" items="${unassignedProjects}">
                                <tr>
                                    <c:if test="${project.lead.id!=user.id}">
                                        <td><a href="/projects/${project.id}"><c:out value="${project.title}"/></a></td>
                                        <td>
                                            <c:out value="${project.lead.firstName}"></c:out>
                                        </td>
                                        <td>
                                            <fmt:formatDate value="${project.dueDate}" pattern="MMMM dd" />
                                        </td>
                                        <td><a href="/dashboard/join/${project.id}">Join Team</a></td>
                                    </c:if>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>

                    <hr>
                    <h4>Your Projects</h4>
                    <table>
                        <thead>
                            <tr>
                                <th>Project</th>
                                <th>Team Lead</th>
                                <th>Due Date</th>
                                <th>Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="project" items="${assignedProjects}">
                                <tr>
                                    <td><a href="/projects/${project.id}"><c:out value="${project.title}"/></a></td>
                                    <td>
                                        <c:out value="${project.lead.firstName}"></c:out>
                                    </td>
                                    <td>
                                        <fmt:formatDate value="${project.dueDate}" pattern="MMMM dd" />
                                    </td>
                                    <c:if test="${project.lead.id==user.id}">
                                        <td><a href="/projects/edit/${project.id}">Edit Project</a></td>
                                    </c:if>
                                    <c:if test="${project.lead.id!=user.id}">
                                        <td><a href="/dashboard/leave/${project.id}">Leave Team</a></td>
                                    </c:if>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>


                    <!-- link Js -->
                    <script type="text/javascript" src="/js/main.js"></script>
                    <!-- For any Bootstrap that uses jquery -->
                    <script src="/webjars/jquery/jquery.min.js"></script>
                    <!--For any Bootstrap that uses JS -->
                    <script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
                </body>

                </html>