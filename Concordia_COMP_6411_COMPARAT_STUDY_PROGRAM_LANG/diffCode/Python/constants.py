# -*- coding: utf-8 -*-
##############################################################################

__author__      = "Safan Maredia"
__copyright__   = "Copyright 2013, POTUM: THE BEVERAGE COASTER PROJECT"
__license__     = "GPL"
__version__     = "1.0.2"
__email__       = "marediasafan@gmail.com"
__status__      = "Production"
__description__ = "Module calculating value of math functions used in POTUM implementation"

##############################################################################

from decimal import Decimal
import math

#method to calculate factorial of a number using a recursive method
def factorial(n):
    if n<1:
        return 1
    else:
        return n*factorial(n-1)

#Chudnovsky method to calculate pi using n iterations
def calculatePi(n): 
    pi = Decimal(0)
    k = 0
    #iterating n times
    while k < n:        
        #The Chudnovsky formula
        pi += (Decimal(-1)**k)*(Decimal(factorial(6*k))/((factorial(k)**3)*(factorial(3*k)))* (13591409+545140134*k)/(640320**(3*k)))
        k  += 1
        
    pi = pi*Decimal(10005).sqrt()/4270934400
    pi = pi**(-1)   #take a inverse of result to get value of pi
    return pi

   
    
