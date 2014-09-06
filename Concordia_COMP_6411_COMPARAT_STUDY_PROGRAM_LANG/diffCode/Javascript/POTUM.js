/* 
 *Filename:POTUM.JS
 *Author: Aravindan Balasubramanian
 *Team: TEAM - D
 *Course Code : COMP 6411
 *Date: 07/11/13
 *Description: The Beverage Coaster Project: Finds the length of overlap of two beverage coasters
  such that all the three sections have same Area A/2.
  
  To run this Implementation, please read the Readme.txt (available in the source folder)

 */
     
        // Desired and Chosen Accuracy of calculations
        var accuracy;
         
        //alpha's exponent : powerNum
        var Num;
        var n;
         
        /*calculate the Factorial of the power*/
        function calculateFactorial(n){
          
            if(n<1){
                return 1;
            }
            else {
                return n*calculateFactorial(n-1);
            }   
        
          }
          
        /*Calculating the Pi by Chudnovsky Approximation */
        function calculatePi(){
            
            var Pi=0;
            var k =0;
            while (k < 28){
                
                var a =(Math.pow(-1,k)); 
                
                var b = (calculateFactorial(6*k));
                
                var c = (calculateFactorial(3*k));
                
                var d = (calculateFactorial(k));
                
                var e = Math.pow(d,3);
                
                var f = (13591409+545140134*k);
                
                var g = Math.pow(640320,3*k);
                
                
                Pi+=((a*b)/(c*e))*(f/g); 
                
                k++;
                
              } 
             
             Pi *= Math.sqrt(10005)/(4270934400);
             Pi = Math.pow(Pi,-1);
             
            var myDiv2=document.getElementById("d2");
            myDiv2.innerHTML=Pi;
            
            
            return Pi;  
            
        }  
        
        /*PowerNum = no.of terms to which the series is calculated. Decides Stopping point.*/      
        function calculatePowerNum(accuracy){     
            
            //powerNum = 3 as starting term alpha^3.Step size = 2 as  alpha^3,alpha^5,alpha^7..
           for(var powerNum=3;powerNum<1000;powerNum+=2){  
                
                //calculating Pi
                var calPi=calculatePi();
                
                //accuracy condition : (pi^k/k!)<accuracy
                if((Math.pow(calPi,powerNum)/(calculateFactorial(powerNum)))<accuracy){
                  //stop when accuracy reached  
                  break;
                }
                                         
             }  
            
             return powerNum;  
             
          }    
            
        /*Solve for alpha : a - sina = pi/2 using Taylor's Series for Sine */   
        function calculateAlpha(accuracy){
            
           //get the power upto which the series should be calculated
           var power = calculatePowerNum(accuracy);
           
           //get the calculated pi
           var calPi = calculatePi();
           
           // 2*pi/3 < alpha < pi 
           var alpha;
           
           for(alpha = 2*calPi/3;alpha<calPi;alpha+= accuracy){
             // seriesValue is the LHS of the above equation    
             var seriesValue=0;
             for(var n=1;n<=(power-1)/2;n++){
             // calculating the seriesValue upto a^13   

             seriesValue = seriesValue + (Math.pow(-1,n+1)*Math.pow(alpha,2*n+1))/
                        calculateFactorial(2*n+1);
                } 
               
                if(((calPi/2)-(seriesValue))<accuracy){//stop on reaching accuracy
                
                    break; 
                }
            }

            var myDiv6=document.getElementById("d6");
            myDiv6.innerHTML=alpha;
            return alpha;
            
        }      
        
        /*Calculate L using alpha and given Radius*/
        function calculateLength(accuracy,r){ 
            
            var R = r;
                         
            //get alpha
            var a = calculateAlpha(accuracy);          
            
            //get Pi
            var calPi = calculatePi();           
            
            //given: a - sin a = pi/2, calculate Sin Alpha 
            var sinAlpha = a - calPi/2;        
                   
            //Pythagorean trignometric Identity
            var cosAlpha = 0;
            cosAlpha= -1*(Math.sqrt((1-sinAlpha*sinAlpha)));            
            
            //Cos Half Angle Formula
            var cosHalfAlpha = Math.sqrt((1+cosAlpha)/2);           
            
            //Calculate the Overlap: X1X2 using given Eqn
            var lengthofOverlap = 2*R*(1-cosHalfAlpha);         
           
            return lengthofOverlap;
            
        }
         
        /*gets the radius from user*/    
        function  getRadius(accuracy){
            
            try {
                
                  var R = document.getElementById("rad").value ; 
                  // validate user input in the text box
                  if(R=="") throw "Error :empty box ; Please enter a value";
                  if(isNaN(R)) throw "Error: not a number; Please enter a number";
                  if(R<0) throw "Error: Radius cannot be negative; Please enter a positive number;";
                  if(R==0) throw "Error:  Radius cannot be zero ; Enter Radius greater than zero";
                  else throw "Accepted : Entered value is within range";
               }
               
            catch(err){
                 
                  var msg = document.getElementById("errormsg");
                  msg.innerHTML =" " + err + ".";
               }
                  if(R>0){
                       var L= calculateLength(accuracy,R);
                  }
                  var myDiv0=document.getElementById("d0");
                  myDiv0.innerHTML=L; 
                        
        }
             