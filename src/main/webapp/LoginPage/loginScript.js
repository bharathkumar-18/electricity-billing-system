document.addEventListener("DOMContentLoaded", function() {
	var cusno = document.getElementById("cusId");
	var pwd = document.getElementById("pwd");
	var email = document.getElementById("email");
	var text = document.getElementById("id");
	var echeck = document.getElementById("e_in");
	let allowToSubmit = false;
	let loginMode = "";
	console.log(cusno);
	if (cusno && email) {
		cusno.addEventListener("input", function() {
			if (cusno.value !== "") {
				email.disabled = true;
			}
			else {
				email.disabled = false;
			}
		});


		email.addEventListener("input", function() {
			if (email.value !== "") {
				cusno.disabled = true;
			}
			else {
				cusno.disabled = false;
			}
		});
	}
	else {
		console.error("One or both inputs not found");
	}


}
);



function validate(event) {
	var cusno = document.getElementById("cusId");
	var pwd = document.getElementById("pwd");
	var email = document.getElementById("email");
	var text = document.getElementById("id");
	var echeck = document.getElementById("e_in");
	let allowToSubmit = false;
	let loginMode;
	if (cusno.value !== "") {
		loginMode = "cusno";
	}
	else {
		loginMode = "email";
	}
	console.log("Running validate");
	console.log(cusno);
	console.log(email);
	console.log(loginMode);

	if (loginMode === "email") {
		console.log(loginMode);
		if (pwd.value.length < 8) {
			alert("Minimum 8 characters in password");
			return false;
		}
	}
	else {
		let cusIdInNumber = Number(cusno.value);
		if (isNaN(cusIdInNumber)) {
			alert("Customer Number is a 13 digit number");
			return false;
		}
		if (cusno.value.length !== 13) {
			alert("Customer Number is a 13 digit number");
			return false;
		}
		if (pwd.value.length < 8) {
			alert("Minimum 8 characters in password");
			return false;
		}
	}
	//event.preventDefault();
	return true;
}