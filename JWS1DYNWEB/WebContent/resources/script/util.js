function check(table, cbox, check) {
	try {
		var tbl = document.getElementById(table);
		var rows = tbl.getElementsByTagName('tbody')[0].getElementsByTagName('tr').length;
		for ( var n = 0; n < rows; n++)
			document.getElementById(table + ':' + n + ':' + cbox).checked = check;
	} catch (e) {
	}
}

function checkAll(table, value) {
	try {
		var body = document.getElementById(table).getElementsByTagName('tbody')[0];
		var cbox = body.getElementsByTagName('input');
		for ( var n = 0; n < cbox.length; n++)
			cbox[n].checked = value;
	} catch (e) {}
}

function onRowOver(row, color, pointer) {
	row.style.backgroundColor = color;
	row.style.cursor = pointer;
}
