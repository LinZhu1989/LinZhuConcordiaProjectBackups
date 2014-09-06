function valueL= calculateLength( accuracy , r )
% Calculate the value of L with a certain r
% Initialize the variables
t1=clock;
R=r;
myPi=calculatePi(25);
% Calculate the values of trigonometric functions 
myAlpha = calculateAlpha( accuracy );
sinAlpha = myAlpha-myPi/2;
cosAlpha = -sqrt(1-sinAlpha*sinAlpha);
cosHalfAlpha = sqrt((1+cosAlpha)/2);
% Calculate the value of L
valueL=2*R*(1-cosHalfAlpha);
% Print the information of all the values of the variables
fprintf('\nThe value of Alpha is:\n');
disp(myAlpha);
fprintf('The value of sinAlpha is:\n');
disp(sinAlpha);
fprintf('The value of cosAlpha is:\n');
disp(cosAlpha);
fprintf('The value of cosHalfAlpha is:\n');
disp(cosHalfAlpha);
fprintf('Successfully calculating the value of L\n')
t2=clock;
disp(['Runing Time for Calculating Length in Matlab is£º',num2str(etime(t2,t1))]);
end

