var thead = document.getElementById("tableHeader");
thead.addEventListener('click', function (ev) {
    sort(ev.target.cellIndex, ev.target.getAttribute("datatype"));
});

function sort(cellIndex, type) {
    var tbody = document.getElementsByTagName("tbody")[0];
    var rows = [].slice.call(tbody.rows);
    var rowsToSort = [].slice.call(tbody.rows);
    var compareFunction;
    switch (type) {
        case "number":
            compareFunction = function (first, second) {
                return first.cells[cellIndex].innerHTML - second.cells[cellIndex].innerHTML;
            };
            break;
        case "string":
            compareFunction = function (first, second) {
                return first.cells[cellIndex].innerHTML > second.cells[cellIndex].innerHTML;
            };
            break;
        case "date":
            compareFunction = function (first, second) {
                return Date.parse(first.cells[cellIndex].innerHTML) > Date.parse(second.cells[cellIndex].innerHTML);
            };
            break;
    }
    rowsToSort.sort(compareFunction);
    if (!isSortedInAnother(rows, rowsToSort))
        rowsToSort.reverse();
    var table = document.getElementsByTagName("table")[0];
    table.removeChild(tbody);
    for (var i = 0; i < rowsToSort.length; i++) {
        tbody.appendChild(rowsToSort[i]);
    }
    table.appendChild(tbody);
}

function isSortedInAnother(rows, sorted) {
    for (var i = 0; i < rows.length; i++) {
        if (rows[i] !== sorted[i]) {
            return true;
        }
    }
    return false;
}