var oldRow = null;

function setOnclick(table, idElement) {
    table.addEventListener('click', function (event) {
        if (oldRow != null) {
            oldRow.className = "";
        }
        var row = event.target.parentNode;
        row.className = "select";
        idElement.value = row.childNodes[0].textContent;
        get();
        oldRow = row;
    });
}