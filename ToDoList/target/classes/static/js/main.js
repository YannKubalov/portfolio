$(function () {

    const appendTask = function (data) {
        var taskCode = '<a class="task-link" id="' +
            data.id + '">' + data.id + ". " + data.name + ' </a> ';
        $('#task-list')
            .append('<div>' + taskCode + '</div>');
    };

    $('#show-add-task-form').click(function () {
        $('#task-form').css('display', 'flex')
    });

    $('#task-form').click(function (event) {
        if (event.target === this) {
            $(this).css('display', 'none');
        }
    });

    $('#show-update-task-form').click(function () {
        $('#update-form').css('display', 'flex')
    });

    $('#update-form').click(function (event) {
        if (event.target === this) {
            $(this).css('display', 'none');
        }
    });
    $('#show-delete-task-form').click(function () {
        $('#delete-form').css('display', 'flex')
    });

    $('#delete-form').click(function (event) {
        if (event.target === this) {
            $(this).css('display', 'none');
        }
    });


    $('#save-task').click(function () {
        var data = $('#task-form form').serialize();
        $.ajax({
            method: "POST",
            url: "/tasks/",
            data: data,
            success: function (response) {
                $('#task-form').css('display', 'none');
                var task = {};
                task.id = response;
                var dataArray = $('#task-form form').serializeArray();
                for (i in dataArray) {
                    task[dataArray[i]['name']] = dataArray[i]['value'];
                }
                appendTask(task);
            }
        });
        return false;
    });

    $('#update-task').click(function () {
        var task = $('#update-form form').serializeArray()
        var taskArr = {}
        for (i in task) {
            taskArr[task[i]['name']] = task[i]['value']
        }
        var taskId = task[0]['value']
        $.ajax({
            method: "PUT",
            url: "/tasks/" + taskId,
            data: taskArr,
            success: function () {
                $('#update-form').css('display', 'none');
                var p = document.getElementById(taskId)
                p.innerHTML = taskArr.id + ". " + taskArr.name
            }
        })
        return false;
    })

    $('#delete-task').click(function () {
        var task = $('#delete-form form').serializeArray()
        var taskId = task[0]['value']
        $.ajax({
            method: "DELETE",
            url: "/tasks/" + taskId,
            success: function () {
                $('#delete-form').css('display', 'none');
                var p = document.getElementById(taskId)
                p.remove()
            }
        })
        return false;
    })

});


