function myPi = calculatePi( n )
% Calculate the value of pi with the Chudnovsky algorithm
% n is the times of the iterations of the Chudnovsky formula
% Define temp variable PiTemp
t1=clock;
piTemp = 0.00;
% It will do n iterations of the Chudnovsky formula
for k=0:1:(n-1)     
    % The Chudnovsky formula
    piTemp = piTemp+(((-1)^k)*factorial(6*k)*(13591409 + (545140134 * k))) / (factorial(3 * k) * (factorial(k)^3)* 640320^(3 * k + 3/2));
end
piTemp = piTemp*12;
% Calculate the value of pi
myPi = 1/piTemp;  
t2=clock;
%disp(['Runing Time for Calculating Pi in Matlab is£º',num2str(etime(t2,t1))]);
end

