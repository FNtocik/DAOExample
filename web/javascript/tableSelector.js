var oldRow = null;

function setOnclick(table) {
    table.addEventListener('click', function (event) {
        if (oldRow != null) {
            oldRow.className = "";
        }
        var row = event.target.parentNode;
        row.className = "select";
        get(row.rowIndex - 1);
        oldRow = row;
    });
}