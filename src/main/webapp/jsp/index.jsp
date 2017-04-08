<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--@elvariable id="tasks" type="java.util.List<app.entity.Task>"--%>

<html>
<head>
    <title>Java EE Lab</title>
    <link type="text/css" rel="stylesheet" href="<c:url value="/css/index.css" />"/>
    <link type="text/css" rel="stylesheet" href="<c:url value="/css/lib/bootstrap.css" />"/>
    <link type="text/css" rel="stylesheet" href="<c:url value="/css/font-awesome-4.7.0/css/font-awesome.min.css" />"/>

    <script src="<c:url value="/js/lib/jquery-3.2.0.js" />"></script>
    <script src="<c:url value="/js/lib/bootstrap.js" />"></script>
</head>
<body>
<div class="header">
    <div class="header__header-content">
        <div class="side left-side">
            <div class="title">
                To Do List
            </div>
            <div class="subtitle">
                Java EE lab
            </div>
        </div>
        <div class="side right-side">
            <button class="btn btn-success control-btn" data-toggle="modal" data-target="#new-task-modal">
                <i class="fa fa fa-plus fa-2x" aria-hidden="true"></i>
            </button>
        </div>
    </div>
</div>
<div class="content">
    <c:forEach items="${tasks}" var="task" varStatus="status">
        <c:set var="taskItem" value="${task}" scope="request"/>
        <div class="task" data-task-id="${task.id}">
            <c:import url="task-veiw.jsp"/>
        </div>
    </c:forEach>
    <div class="modal fade" id="new-task-modal">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">Add New Task</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <form action="${pageContext.request.contextPath}/create-task" method="post">
                    <div class="modal-body">
                        <div class="form-group">
                            <label for="new-task-name" class="form-control-label">Name:</label>
                            <input type="text" class="form-control" id="new-task-name" name="name">
                        </div>
                        <div class="form-group">
                            <label for="new-task-top-priority" class="form-control-label">Top Priority:</label>
                            <input type="checkbox" data-toggle="toggle" id="new-task-top-priority" name="top-priority">
                        </div>
                        <div class="form-group">
                            <label for="new-task-estimation" class="form-control-label">Estimation (hours):</label>
                            <input type="number" class="form-control" id="new-task-estimation" name="estimation">
                        </div>
                        <div class="form-group">
                            <label for="new-task-description" class="form-control-label">Description:</label>
                            <textarea class="form-control" id="new-task-description" name="description"></textarea>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <input type="submit" class="btn btn-primary" value="Save changes">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                    </div>
                </form>
            </div>
        </div>
    </div>

</div>
</body>
</html>
