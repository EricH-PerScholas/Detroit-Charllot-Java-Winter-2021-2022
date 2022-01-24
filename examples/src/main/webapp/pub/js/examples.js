
function fizzBuzz(num){
	  
	  for (var i = 1 ; i <= num ; i++){
	    var c = i%3;
	    var d = i%5;
	    
	    if( !(i%3) && !(i%5) )
	      console.log("fizzBuzz")
	    else if (!c)
	      console.log("fizz")
	    else if (!d)
	       console.log ("buzz")
	    else 
	       console.log(i)
	  }
	  
	}	
