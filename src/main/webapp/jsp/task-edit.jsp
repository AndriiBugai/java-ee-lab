<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--@elvariable id="task" type="app.entity.Task"--%>

<c:set var="task" value="${taskItem}"/>
<div class="task-view" >

    <div class="task-view__header">
        <div class="task-view__name">
            ${task.name}
        </div>
        <i class="fa fa-pencil-square-o fa-2x edit" aria-hidden="true"></i>
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
            Date created: <fmt:formatDate type="both" value="${task.dateCreated}" />
        </div>
    </div>
</div>
