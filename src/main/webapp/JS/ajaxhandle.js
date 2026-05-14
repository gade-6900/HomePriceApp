let searchState=(str)=>{
    let tableBody=document.getElementById("tbody");
    tableBody.innerHTML="";  
    let xhttp=new XMLHttpRequest();    
    xhttp.onreadystatechange=function(){
        if(this.readyState==4 && this.status==200){
            tableBody.innerHTML=this.responseText;
        }
    };  
    xhttp.open("GET","/HousePriceWebApplication/searchstate?s="+str,true);
    xhttp.send();
}

let fetchCities=()=>{  
    let stateDropDown=document.getElementById("state");
    let stateId=stateDropDown.value;   
    let xhttp=new XMLHttpRequest();
    xhttp.onreadystatechange=function(){      
    };  
    xhttp.open("GET","/HousePriceWebApplication/fetchstatewisecity?stid="+stateId,true);
    xhttp.send();
}