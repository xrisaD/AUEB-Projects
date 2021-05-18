var darkOn = false;
var sunny = "Toggle 🌞";
var dark = "Toggle 🌚";
var today = new Date().getDay();

window.onload = function () {
  var elem = document.createElement("img");
  elem.setAttribute("src", 'images/robots_inlove.PNG');
  elem.setAttribute("height", "auto");
  elem.setAttribute("width", "50%");
  elem.setAttribute("alt", "Flower"); 
  document.querySelector("footer").appendChild(elem);

  document.querySelector("footer p").textContent = "Χρύσα";

  counter=0;
  let links = document.querySelectorAll("aside > ul > li");
  for (let link of links){
    if(counter%2==0){
        link.style.fontWeight  = "bold";
    }
    counter++;
  }

  var days = ["Κυριακή", "Δευτέρα", "Τρίτη", "Τετάρτη", "Πέμπτη", "Παρασκευή", "Σάββατο"]
  if(today!=6){
    document.querySelector(".day").textContent ="Καλή "+days[today]+"!";
  }else{
    document.querySelector(".day").textContent ="Καλό Σάββατο!";
  }

  var darks = document.getElementsByClassName("toggle");
  for (let d of darks){
    d.addEventListener("click", darkMode);
  }

  document.body.addEventListener("copy", deleteBody);
}

window.on

// function that changes css property and content
function darkMode() {

  if (!darkOn){
    console.log("Dark Mode On");
    document.querySelector(".container").style.backgroundColor = "black";
    document.querySelector("button").textContent = dark;
   
  let images = document.getElementsByClassName("neural_network");
  for(let i = 0;i < images.length; i++){
   images[i].style.backgroundColor = "#013220";
  }

    darkOn = true;
  } else {
    console.log("Dark Mode Off");
    document.querySelector(".container").style.backgroundColor = "#c64756";
    document.querySelector("button").textContent  = sunny;
    darkOn = false;

    let images = document.getElementsByClassName("neural_network");
    for(let i = 0;i < images.length; i++){
        images[i].style.backgroundColor = "";
    }
  }
}

// function that changes css content
function deleteBody(){
  document.body.textContent="Σε έπιασα!!!!";
  document.body.style.backgroundColor="red";
  document.body.style.fontSize = "x-large";
}