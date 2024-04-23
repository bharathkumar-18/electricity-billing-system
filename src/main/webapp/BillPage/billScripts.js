function calculateTotal() {
	var checkboxes = document.querySelectorAll('input[type="checkbox"]');
	var total = 0;
	checkboxes.forEach(function(checkbox) {
		if (checkbox.checked) {
			console.log(checkbox);
			var row = checkbox.parentNode.parentNode;
			var payable = row.cells[4].innerText;
			total += parseFloat(payable);
		}
	});
	document.getElementById('totalPayable').innerText = total.toFixed(2);
	console.log(document.getElementById('totalPayable').innerHTML);
	var inputTag = document.getElementById('totalAmount');
	console.log(inputTag);
	inputTag.value = document.getElementById('totalPayable').innerText;
	console.log(inputTag.value);
}

function validatePayableAmount(event){
	if(document.getElementById('totalPayable').value!=""){
		return true;
	}
	return false;
}