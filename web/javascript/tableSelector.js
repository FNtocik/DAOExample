var oldRow = null;
var header = "";

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

function setHeadersOnClick(headers) {
    headers.addEventListener('click', function (event) {
        var clickedHeader = event.target;
        header = clickedHeader.getAttribute("id").replace("header", "");
        getAll();
    })
}