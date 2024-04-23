/**
 * 
 */
//Code for Form Validation
function registerStatus(event){
    console.log("Running func");
    let consumerNumber = document.getElementById('consumer-number').value; 
    console.log(consumerNumber);
    let consumerNumberinNum = Number(consumerNumber);
    if(!consumerNumber.length==13 || isNaN(consumerNumberinNum)){
        alert('Consumer Number is a 13 digit number');
        event.preventDefault();
        return false;
    }
    let title = document.getElementById('title').value;
    console.log(title);
    if(title==="select"){
        alert('Please select your title');
        event.preventDefault();
        return false;
    }
    
    let email = document.getElementById('email').value;
    console.log(email);
    let name = document.getElementById('name').value;
    console.log(name);
    if(name=="" || !isNaN(name)){
        alert('Your name cannot be empty and not number');
        event.preventDefault();
        return false;
    }
    let mobileCode = document.getElementById('mobile-code').value;
    console.log(mobileCode);
    if(mobileCode==""){
        alert('Please select your mobile code');
        event.preventDefault();
        return false;
    }

    let mobileNumber = document.getElementById('mobile-number').value;
    console.log(mobileNumber);
    if(!mobileNumber.length==10 || isNaN(mobileNumber)){
        alert('Please enter a 10 digit mobile number');
        event.preventDefault();
        return false;
    }
    let password = document.getElementById('password').value;
    console.log(password);
    if(password==""){
        alert('Please enter your password');
        event.preventDefault();
        return false;
    }
    let rePassword = document.getElementById('re-password').value;
    console.log(rePassword);
    if(rePassword!=password){
        alert('Passwords mismatch. Please ensure both passwords are same');
        event.preventDefault();
        return false;
    }

    //showProgressBar(event);
    console.log("Before final true in main func");
    return true;
}


//Code for Showing Progress Bar
function showProgressBar(event){
    //event.preventDefault();
    console.log("Running progress bar");
    //console.log(progressBar);
    let progressBar = document.getElementById('progress-bar');
    progressBar.style.display='block';
    let infoBox = document.getElementById('info-box');
    infoBox.style.opacity = 0.5;
    infoBox.classList.add("disable");
    setTimeout(()=>{
        let registerStatus = document.getElementById('register-status');
        registerStatus.innerHTML = "Generating acknowledgement receipt ...";
    },2000);
    setTimeout(()=>{
        let registerStatus = document.getElementById('register-status');
        registerStatus.innerHTML="Redirecting to acknowledgement page <br/><br/> Hang on tight";
    },3000);
    //setTimeout(()=>{
    //    registerStatus.innerHTML = "Last step";
    //},5000);
    console.log("Before final true in progress bar");
    return true;

}
