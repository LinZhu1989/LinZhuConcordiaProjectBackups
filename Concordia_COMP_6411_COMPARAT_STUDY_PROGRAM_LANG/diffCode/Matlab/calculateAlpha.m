function myAlpha = calculateAlpha( accuracy )
% Calculate the value of alpha with a certain accuracy 
% Initialize the value of Pi
t1=clock;
myPi=calculatePi(25);
% Initialize the lower case value of alpha
myAlpha=2*myPi/3;
% Calculate power number of item we stop with the certain accuracy
powerNum=calculatePowerNum(accuracy);  
% Print out all the values of the variables we use in the certain case
fprintf('The accuracy we calculate this time is:')
disp(accuracy);
fprintf('The value of Pi we use this time is:')
disp(myPi);
fprintf('The ordinal number of item we stop with the certain accuracy is:')
disp((powerNum-1)/2);
fprintf('The power number of item we stop with the certain accuracy is:')
disp(powerNum);
% End of the printing area
% A timer to record the times of the operations
times=1;    
% Calculate the value of alpha with a certain accuracy 
for myAlpha=2*myPi/3:accuracy:myPi 
    value=0;
    for n=1:1:(powerNum-1)/2
       value=value+(-1)^(n+1)*myAlpha^(2*n+1)/factorial(2*n+1);
    end
   if abs(double(myPi/2-value))<accuracy
       break
   else
   end
   % For each time of the operation 
   times=times+1;   
end
fprintf('The number of times we do the alpha iterations with the certain accuracy is:')
disp(times);
fprintf('Successfully calculating the value of alpha')
t2=clock;
disp(['Runing Time for Calculating Alpha in Matlab is£º',num2str(etime(t2,t1))]);
end

