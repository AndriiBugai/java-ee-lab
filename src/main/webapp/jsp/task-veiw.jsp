<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--@elvariable id="task" type="app.entity.Task"--%>

<c:set var="task" value="${taskItem}"/>
<div class="task-view">

    <div class="task-view__header">
        <div class="task-view__name">
            ${task.name}
        </div>
        <button class="btn btn-success control-btn" data-toggle="modal" data-target="#task-modal-${task.id}">
            <i class="fa fa-pencil-square-o edit" aria-hidden="true"></i>
        </button>
        <form action="${pageContext.request.contextPath}/delete-task" method="post">
            <input type="hidden" name="id" value="${task.id}">
            <button type="submit" class="btn btn-success control-btn">
                <i class="fa fa-times" aria-hidden="true"></i>
            </button>
        </form>
    </div>

    <div class="task-view__subcontent">
        <div class="task-view__description">
            Description: ${task.description}
        </div>
        <div class="task-view__top-priority">
            Top priority: ${task.topPriority}
        </div>
    </div>
    <div class="task-view__subcontent">
        <div class="task-view__estimation">
            Estimation: ${task.estimation} hours
        </div>
        <div class="task-view__date-created">
            Date created: <fmt:formatDate type="both" value="${task.dateCreated}"/>
        </div>
    </div>

    <div class="modal fade" id="task-modal-${task.id}">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">Edit Task</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <form action="${pageContext.request.contextPath}/update-task" method="post">
                    <input type="hidden" name="id" value="${task.id}">
                    <div class="modal-body">
                        <div class="form-group">
                            <label for="name-${task.id}" class="form-control-label">Name:</label>
                            <input type="text" class="form-control" id="name-${task.id}" value="${task.name}" name="name">
                        </div>
                        <div class="form-group">
                            <label for="top-priority-${task.id}" class="form-control-label">Top Priority:</label>
                            <c:set var="checked" value=""/>
                            <c:if test="${task.topPriority}">
                                <c:set var="checked" value="checked"/>
                            </c:if>
                            <input type="checkbox" ${checked} data-toggle="toggle" id="top-priority-${task.id}" name="top-priority">
                        </div>
                        <div class="form-group">
                            <label for="estimation-${task.id}" class="form-control-label">Estimation (hours):</label>
                            <input type="number" class="form-control" id="estimation-${task.id}"
                                   value="${task.estimation}" name="estimation">
                        </div>
                        <div class="form-group">
                            <label for="description-${task.id}" class="form-control-label">Description:</label>
                            <textarea class="form-control" id="description-${task.id}" name="description">${task.description}</textarea>
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
