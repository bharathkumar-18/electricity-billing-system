function validateComplaintForm(event){
	let complaintType = document.getElementById('complaint_type').value;
	let landmark = document.getElementById('landmark').value;
	let category = document.getElementById('category').value;
	let consumerNumber = document.getElementById('consumer_number').value;
	let contactPerson = document.getElementById('contact_person').value;
	let problemDescription = document.getElementById('problem_description').value;
	let mobileNumber = document.getElementById('mobile_number').value;
	let address = document.getElementById('address').value;
	
	console.log("Running");
	
	if(complaintType==""){
		alert('Please select a complaint type');
		event.preventDefault();
		return false;
	}
	if(landmark==""){
		alert('Please enter the landmark');
		event.preventDefault();
		return false;
	}
	if(category==""){
		alert("Please select a category");
		event.preventDefault();
		return false;
	}
	if(isNaN(Number(consumerNumber)) || consumerNumber.length!=13){
		alert("Please enter your 13 digit consumer number");
		event.preventDefault();
		return false;
	}
	if(contactPerson==""){
		alert("Please enter a contact person name");
		event.preventDefault();
		return false;
	}
	if(problemDescription==""){
		alert("Please fill out the problem description");
		event.preventDefault();
		return false;
	}
	if(isNaN(Number(mobileNumber)) || mobileNumber.length!=10){
		alert("Please enter a 10 digit mobile number");
		event.preventDefault();
		return false;
	}
	if(address==""){
		alert("Please enter the address");
		event.preventDefault();
		return false;
	}
	return true;
}