function graphLengthWithR( rAccuracy ,left , right )    
% Show the relationship of L and R in a graph
% Value of L changes with the changing of value of R
% Increase the R with the step size of a given accuracy
r=left:rAccuracy:right;
% Calculate the value of L with the ValueLTeamD function                     
myL = calculateLength( rAccuracy , r );
% Convert r into double type , save it as variable x
x=double(r);
% Convert LTeamD into double type , save it as variable y
y=double(myL);
% Calculate the change of R
deltaX=double(r-left); 
% Calculate the change of L
deltaY=double(calculateLength( rAccuracy , r )-calculateLength( rAccuracy , left ));
% Calculate the ratio of the two numerical values above
k=double(deltaY)/(deltaX);                  
% Print the successful information
fprintf('Successfully calculating all the variables\n')
% Print the preparing information
fprintf('Preparing drawing the graph of r and l\n')     
% Print the relationship of L and R in graph one
% All the settings of the graph one

subplot(1,2,1),plot(x,y,'-r','LineWidth',2);            
set(gca,'FontName','Times New Roman','FontSize',19);
title('L changes with the increasing of R','FontSize',14);
xlabel('Value of the Radius');
ylabel('Value of L'); 
% Print the ratio of L and R changes in graph two
% All the settings of the graph two
subplot(1,2,2),plot(x,k,'-b','LineWidth',2);
set(gca,'FontName','Times New Roman','FontSize',19);
title('Ratio of L and R changes with the increasing of R','FontSize',14);
xlabel('Value of the Radius');
ylabel('Ratio of L and R'); 
% End of printing graphs
% Print the successful information
fprintf('Successfully drawing the graph of r and l\n')      
end

