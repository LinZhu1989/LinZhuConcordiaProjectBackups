function powerNum = calculatePowerNum( accuracy )
% Calculate power number of item we stop with the certain accuracy
%Variable initialize
t1=clock;
powerNum = 3;
pi = calculatePi(25);
% Calculate power number of item we stop with the certain accuracy
% Increase the order by step size of 2
for powerNum = 3:2:1000      
    if double(pi^(powerNum)/factorial(powerNum))<accuracy
        % Break when the cert-ain term is less than the accuracy
        break           
    end
end
t2=clock;
disp(['Runing Time for Calculating Power Number in Matlab is£º',num2str(etime(t2,t1))]);
end

