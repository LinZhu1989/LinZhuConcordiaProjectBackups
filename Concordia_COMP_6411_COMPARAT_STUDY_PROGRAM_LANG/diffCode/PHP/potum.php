    <?php
      /**
     * "Module calculating value of math functions used in POTUM implementation"
     * PHP version 5.4.16
     * LICENSE: The PHP License, version 3.01
     *          Copyright (c) 1999 - 2013 The PHP Group
     *
     
     * @author     Romil Bandi <romilbandi@gmail.com>
     * @copyright  1997-2013 The PHP Group
     * @license    http://www.php.net/license/3_01.txt  PHP License 3.01
     */
     
    //get power number upto which series needs to be solved based on provided accuracy
    function calculatepowerNum($accuracy){
           $pi = calculatePi(100);
        
        //range from 3 to 1000 with a step size=2
        for($powerNum=3; $powerNum<1000; $powerNum+=2){
            if ((pow($pi, $powerNum)/factorial($powerNum)) < $accuracy){
                break;
            }
        }
        return $powerNum;
    }

    //Chudnovsky method to calculate pi using n iterations 
    function calculatePi($n){
        $pi=0.0;
        for($k=0;$k < 10;$k++){
            //The Chudnovsky formula
           $pi = $pi+ (pow((-1.0),$k) *factorial(6.0 * $k) * (13591409.0 + (545140134.0 * $k)))/ 
                 (factorial(3.0 * $k) * pow(factorial($k),3.0) * pow(640320.0,(3.0 * $k + 3.0/2.0)) );
        }      
       
        $pi = (1.0)/($pi*12.0);
        return $pi;
    }

    //method to calculate factorial of a number using a recursive method
    function factorial($powerNum){
        if($powerNum < 1)
            return 1;
        else
            return $powerNum * factorial($powerNum-1);
    }

    //function for calculating the value of alpha
    function calculateAlpha($accuracy){  
        $pi =  calculatePi(100);
        $alpha;
        $times = 1;
        $powerNum = 13;
      
        for ($alpha = 2*($pi/3);$alpha<$pi;$alpha+=$accuracy){
            $value = 0.0;
            for($n=1;$n < ($powerNum-1)/2; $n++){
                $value += pow(-1,($n+1))*pow($alpha,(2*$n+1))/factorial(2*$n+1);  
            }
            
            if(($pi/2-$value) < $accuracy){
                break;
            }
            $times=$times+1;
        }        
        return $alpha;
    }
    //get value of l for a certain value of r 
    function calculateLength($accuracy, $r){
        $R = $_GET["r"]; 
        if($R<0 || $R==0) {
            try {
            throw new Exception("");
            }
            catch(Exception $e)
            {
                print "Please Enter the correct value of RADIUS";
                return ;
            }
        }
        else {
        $pi = calculatePi(100);
        $alpha = calculateAlpha($accuracy); 
        $sinAlpha = $alpha-($pi/2);
        $cosAlpha = sqrt(1-$sinAlpha*$sinAlpha);
        $cosAlpha = 0;
        $cosAlpha =- sqrt((1-$sinAlpha*$sinAlpha));
        $cosHalfAlpha = sqrt((1+$cosAlpha)/2);
        
        $valueL = 2 * $R * (1-$cosHalfAlpha);
       ?>
        <html >
        <div id="opuput" style="margin-left:500px;">
        <?php
        //Print the output
        print "The Entered value of Radius is: $R <br />";
        print "Calculated Alpha Value: $alpha <br />";
        print "Calculated Pi Value: $pi <br />";
        print "Length of the overlap of two circles is : $valueL";
        ?>
        </div> 
        </html>
         <?php
        }
        return $valueL;
    }

    calculateLength(0.0001, 10);
    ?>