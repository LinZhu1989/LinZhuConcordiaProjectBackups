# -*- coding: utf-8 -*-
##############################################################################

__author__      = "Safan Maredia"
__copyright__   = "Copyright 2013, POTUM: THE BEVERAGE COASTER PROJECT"
__license__     = "GPL"
__version__     = "1.1.0"
__email__       = "marediasafan@gmail.com"
__status__      = "Production"
__description__ = """Module calculating value of l for which area of overlapping coaster
                  is half the area of any two of the coaster"""

##############################################################################

from decimal import Decimal
import math
import constants

#get power number upto which series needs to be solved based on provided accuracy
def calculatePowerNum(accuracy):
    pi = constants.calculatePi(100)
    powerNum = 3                            #series starts with power of 3
    for powerNum in range(3,1000,2):        #range from 3 to 1000 with a step size=2
        if pi**powerNum/(constants.factorial(powerNum)) < accuracy:
            break 

    return powerNum                

#expand the series upto powerNum to get the value of alpha based on provided accuracy
def calculateAlpha(accuracy):
    powerNum = calculatePowerNum(accuracy)
    pi = constants.calculatePi(100)

    times = 1                             #counter to track iteration performed for getting value of alpha 
    alpha = 2*pi/3                        #lower bound of alpha

    while alpha < pi:
        sum = 0
        for x in range(1, int((powerNum-1)/2)):
            sum += (-1)**(x+1)*(alpha**(2*x+1)) / constants.factorial(2*x+1)

        if(abs(Decimal(pi/2) - Decimal(sum))) < accuracy:
            break
                        
        alpha = alpha + accuracy
        times = times + 1

    return alpha

#get value of l for a certain value of r
def calculateLength(accuracy, r):
    R=r
    pi    = constants.calculatePi(100)
    alpha = calculateAlpha(accuracy)

    #calculating trignometric functions    
    sinAlpha = alpha-pi/2
    cosAlpha = 0
    cosAlpha = -math.sqrt(1-sinAlpha*sinAlpha)
    cosHalfAlpha = math.sqrt((1+cosAlpha)/2)

    valueL = 2 * R * Decimal((1-cosHalfAlpha))
                
    #Print the output
    print ("\nCalculated Pi value: ", pi)
    print ("\nCalculated Alpha value: ", alpha)
    print ("\nLength of Overlap of the two circles (L): ", valueL)
    return valueL

def main():
    run = 1
    while run:
        r = input("Please enter the value of R: \n")
        if r.isdigit() and int(r) > 0:
            r = Decimal(r)
            run = 0
        else:
            print ("Please enter a valid integer value of R: \n")    

    calculateLength(Decimal(0.0001), r)
    
if __name__ == "__main__":
    main()
    

