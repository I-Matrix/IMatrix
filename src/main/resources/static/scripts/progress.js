var i = 0;
var progressBar = $("#bar");
function countNumbers(){
    if(i < 100){
        i = i + 0.8;
        progressBar.css("width", i + "%");
    }
    setTimeout("countNumbers()", 500);
}