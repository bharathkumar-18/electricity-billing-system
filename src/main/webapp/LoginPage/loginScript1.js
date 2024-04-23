function validate(event){
    var cusno=document.getElementById("cust_in").value;
    var pwd=document.getElementById("pwd").value;
    var email=document.getElementById("e_in").value;
    var text=document.getElementById("id");
    var echeck=document.getElementById("e_in");
    if(checklength(cusno)&&checklength(email)){
       text.innerHTML="Enter either one of the id's";
       event.preventDefault();
    }
    if(!chechcustid(cusno)&&!checkpwd(pwd)){
        window.open("https://www.youtube.com/watch?v=dQw4w9WgXcQ","_self");
        event.preventDefault();
    }
    else if(!checklength(cusno)&&chechcustid(cusno)){
        text.innerHTML="Enter valid Customer ID";
        event.preventDefault();
    }
    else if(!checklength(cusno)&&!chechcustid(cusno)&&checkpwd(pwd)){
        text.innerHTML="Enter valid PWD";
        event.preventDefault();
    }
    if(!checklength(email)&&echeck.checkValidity()&&!checkpwd(pwd)){
        window.open("https://www.youtube.com/watch?v=dQw4w9WgXcQ","_self");
        event.preventDefault();
    }
    else if(!checklength(email)&&!echeck.checkValidity()){
        text.innerHTML="Enter valid Email ID";
        event.preventDefault();
    }
    else if(!checklength(email)&&!echeck.checkValidity()&&checkpwd(pwd)){
        text.innerHTML="Enter valid PWD";
            event.preventDefault();
    }
return true;
}
function checklength(text){
    return(text.length==0)
}
function chechcustid(cusno){
    return(cusno.length<13||isNaN(cusno))
}
function checkpwd(pwd){
    return(pwd.length<2)
}