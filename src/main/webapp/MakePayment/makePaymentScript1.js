function validateCardDetails(event) {
	console.log("Validating card details");
	//event.preventDefault();
	let cardNumber = Number(document.getElementById('card-number').value);
	let month = document.getElementById('month').value;
	let year = Number(document.getElementById('year').value);
	let cvv = Number(document.getElementById('cvv').value);
	let cardHolderName = document.getElementById('card-holder-name').value;
	if(isNaN(cardNumber)  || isNaN(year) || isNaN(cvv)){
		alert('Card number, year and cvv can only be digits. Please check your details. ');
		event.preventDefault();
		return false;
	}
	if(cardNumber.toString().length!=16){
		alert("Card number is of 16 digits. Please check your details");
		event.preventDefault();
		return false;
	}
	if(month==='Month'){
		alert('Please select expiry month. ');
		return false;
	}
	if(year.toString().length!=4){
		alert('Year is of 4 digits. Please check your details');
		event.preventDefault();
		return false;
	}
	if(cvv.toString().length!=3){
		alert('CVV is of 3 digits. Please check your details');
		event.preventDefault();
		return false;
	}
	if(cardHolderName.length==0) {
		alert('Please enter Card Holder name');
		event.preventDefault();
		return false;
	}
	return true;
}