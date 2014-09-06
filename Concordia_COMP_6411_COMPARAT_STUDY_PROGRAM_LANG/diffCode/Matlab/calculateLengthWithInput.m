function calculateLengthWithInput(  )
% Calculate the value of L with the given r and accuracy
% Handling the exception cases
fprintf('Preparing for calculating the value of L\n');
% Initialize the running condition value
run=1;      
rAccuracy=0.0001;
while run
    % Ask for the value of R
    r=input('Please enter the value of R:\n');  
    % Transfer the given data into double type
    rTemp=double(r);
    % Whether the given data is a positive double            
    if rTemp==r && r>0
         % If yes,break the while
                run=0;
            % If not,ask for retyping the value of R
    else                    
        fprintf('A wrong data type of R,please retype:\n');                
    end
end                
% Calculating the value of L
myL = calculateLength( rAccuracy , r );  
% Print the information
fprintf('The value of L is:\n')
% Display the value of L
display(myL);                            
end
