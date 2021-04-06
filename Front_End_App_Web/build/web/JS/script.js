//Variables de Inicio

var tab1 = document.getElementById("tab1");
var tab2 = document.getElementById("tab2");
var tab3 = document.getElementById("tab3");
var btn1 = document.getElementById("btn1");
var btn2 = document.getElementById("btn2");
var btn3 = document.getElementById("btn3");



function openFlight() {
  tab1.style.visibility = "visible";
  tab2.style.visibility = "hidden";
  tab3.style.visibility = "hidden";

  btn1.style.color = "rgba(242, 38, 19, 0.8)";
  btn1.style.background = "white";
  btn2.style.color = "rgba(171, 183, 183, 1)";
  btn2.style.background = "transparent";
  btn3.style.color = "rgba(171, 183, 183, 1)";
  btn3.style.background = "transparent";
}

function openCheckIn() {
  tab1.style.visibility = "hidden";
  tab2.style.visibility = "visible";
  tab3.style.visibility = "hidden";

  tab1.style.transition = "0.3";
  tab2.style.transition = "0";
  tab3.style.transition = "0";

  btn1.style.color = "rgba(171, 183, 183, 1)";
  btn1.style.background = "transparent";
  btn2.style.color = "rgba(242, 38, 19, 0.8)";
  btn2.style.background = "white";
  btn3.style.color = "rgba(171, 183, 183, 1)";
  btn3.style.background = "transparent";
}

function openStatus() {
  tab1.style.visibility = "hidden";
  tab2.style.visibility = "hidden";
  tab3.style.visibility = "visible";

  btn1.style.color = "rgba(171, 183, 183, 1)";
  btn1.style.background = "transparent";
  btn2.style.color = "rgba(171, 183, 183, 1)";
  btn2.style.background = "transparent";
  btn3.style.color = "rgba(242, 38, 19, 0.8)";
  btn3.style.background = "white";
}