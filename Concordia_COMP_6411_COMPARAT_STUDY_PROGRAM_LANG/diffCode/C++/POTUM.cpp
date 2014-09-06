/*
Filename:POTUM.cpp
Author: Shivangi Rathore
Email: shivangi1890@gmail.com
Team: TEAM - D
Course Code : COMP 6411
Last Updated Date: 07/24/13
Application Version: C++ 11
Description: The Beverage Coaster Project: Find the degree of overlap of two beverage coasters
such that all the three sections have same Area A/2.
*/

#include<iostream>
#include<iomanip> // used to setprecision of pi
#include<cmath>   // used for pow and sqrt functions
#include<math.h>
using namespace std;

double calculatePi(int n);
double calculateAlpha(double accuracy);
double calculateLength(double accuracy,double r);
unsigned long long int calculateFactorial(int CalculatePowerNum);

//unsigned long long datatype takes 8bytes of space and it ranges from 0 to 18,446,744,073,709,551,615
//to calculate factorial in many functions

unsigned long long int calculateFactorial(int n)
{
	if(n < 1)
		return 1;
	else
		return n * calculateFactorial(n-1);
}

// Calculating Value of Pi with Chudnovsky Algorithm
double calculatePi(double n)
{
	double pi=0.0;
    for (int k = 0; k < 10.0; k++)
	{
        pi += (pow(-1.0,k) *calculateFactorial(6.0 * k) * (13591409.0 + (545140134.0 * k))) 
            / (calculateFactorial(3.0 * k) * pow(calculateFactorial(k), 3.0) * pow(640320.0, 3.0 * k + 3.0/2.0));
    }
    pi *= 12.0;
	pi  = 1/pi;		//take an inverse of result to get the value of pi
    return pi;
}

//get power number upto which series needs to be solved based on provided accuracy which is 0.0001
int calculatePowerNum(double accuracy){
	double pi=calculatePi(10.0);
	int powerNum;

	//range from 3 to 1000 with a step size=2
	for (powerNum=3; powerNum<100;powerNum+=2)
	{
		if ((pow(pi,powerNum)/calculateFactorial(powerNum)) < accuracy)
		{
			break;
		}
	}
	return powerNum;
}

// Calculating the Value of Alpha
double calculateAlpha(double accuracy)
{
	double pi=calculatePi(10.0);
	double alpha;
	int times = 1;

	int CalculatePowerNum = calculatePowerNum(accuracy);
	for (alpha = 2*(pi/3) ; alpha<pi ; alpha+=accuracy)
	{
		double value = 0.0;
		for (int n = 1; n < (CalculatePowerNum-1)/2; n++)
		{
			value += pow(-1,(n+1))*pow(alpha,(2*n+1))/calculateFactorial(2*n+1);
		}

		if(double(pi/2-value) < accuracy)
		{
			break;
		}
		times=times+1;
	}

	return alpha;
}

//Calculating Value of Length that will have overlap area A/2
double calculateLength(double accuracy, int r)
{
	double pi=calculatePi(10.0);
	double alpha = calculateAlpha(accuracy);
	double sinAlpha = alpha-(pi/2);
	double cosAlpha =- sqrt(1-sinAlpha*sinAlpha);
	double cosHalfAlpha = sqrt((1+cosAlpha)/2);
	double valueL = 0.0;
	double R = r;
	valueL = 2*R*(1-cosHalfAlpha);

	cout << endl << "Calculated Pi value: " << pi << endl;
	cout << "Calculated Alpha value: " << alpha << endl ;
	cout << "Length of Overlap of the two circles (L): " << valueL << endl << endl;

	return valueL;
}

int main()
{
	int r;
	bool run = true;

	while(run){
		cout << "Please enter the value of R: " << endl;
		cin >> r;

		//check if input value of r is numeric and is greater than zero
		if(cin.fail() || r<=0){
			cout << "Value enter for radius is Invalid, please re-enter: " << endl;
	
			//if value of r is invalid, clear input stream
			cin.clear() ;
			cin.ignore(std::numeric_limits<streamsize> :: max(), '\n') ;
		}
		else{
			run = false;
		}
	}
	
	calculateLength(0.0001, r); 
	system("pause");
	return 0;
}