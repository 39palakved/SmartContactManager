console.log("script loaded")

let currentTheme = getTheme();
changeTheme()

//todo
function changeTheme(){
   //set to web page
   document.querySelector('html').classList.add(currentTheme);
   //set listener to change in button
  const changetheme=  document.querySelector('#theme');
  changetheme.addEventListener('click',()=>{
    document.querySelector('html').classList.remove(currentTheme);
    if(currentTheme == "dark"){
           currentTheme="light";
    }
    else{
        currentTheme="dark"
    }
    setTheme(currentTheme);
    document.querySelector('html').classList.add(currentTheme);

    changetheme.querySelector('span').textContent = currentTheme=='light'?'Dark':"Light";
  });

  
}
function setTheme(theme){
    localStorage.setItem("theme" , theme);
}
function getTheme(){
    let theme =  localStorage.getItem("theme");
    if(theme){
   return theme;
    }
    else{
        return "light";
    }

}
//change text of button


