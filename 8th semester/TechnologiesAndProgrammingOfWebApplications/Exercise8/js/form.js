window.addEventListener('load', (event) => {
    document.getElementById("password").oninput = checkPassword;
    document.getElementById("confirm_password").oninput = checkPasswords;
  
    document.getElementById("birthday").onchange = checkDate;
    addressInput = document.getElementById("address");
    addressInput.addEventListener('invalid', () => {
        addressInput.setCustomValidity("Η διεύθυνση πρέπει να δοθεί στη μορφή: Οδός Αριθμός Τ.Κ.");
    });
    document.getElementById("eye").onmousedown  = mouseDown;
    document.getElementById("eye").onmouseup = mouseUp;
});


function checkPasswords(){
  // read passwords
  passwordInput = document.getElementById("password");
  passwordInput2 = document.getElementById("confirm_password");

  if(passwordInput.value == passwordInput2.value){
      passwordInput2.setCustomValidity("");
  }else{
      passwordInput2.setCustomValidity("Οι κωδικοί πρέπει να είναι ίδιοι!");
  }

}

function checkDate(){
  // read date of birth
  input = document.getElementById('birthday');
  d = new Date(input.value);
  
  // compare dates
  if(new Date().getFullYear() < d.getFullYear()){
      input.setCustomValidity("Κάτι μπέρδεψες στη χρονολογία..");
  }else if(new Date().getFullYear() - d.getFullYear() < 18){
      input.setCustomValidity("Πρέπει να είσαι άνω των 18 για να εγγραφείς!");
  }else{
      input.setCustomValidity("");
  }   
}

function checkPassword(){
  // read username and password
  usernameInput = document.getElementById('username');
  passwordInput = document.getElementById("password");
  
  // check that your password doesn't contain your username
  if(passwordInput.value.includes(usernameInput.value)){
      passwordInput.setCustomValidity("Ο κωδικός σου δε πρέπει να περιέχει το username σου!");
  }else{
      passwordInput.setCustomValidity("");
  }
}

function mouseDown() {
  var x = document.getElementById("password");
  x.type = "text";
}

function mouseUp() {
  var x = document.getElementById("password");
  x.type = "password";
}
