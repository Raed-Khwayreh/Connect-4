/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Connect_4;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Board {
    boolean f;
    public static int x,y=0;
    public boolean turn=false;
    public int arr_col[]=new int[7]; 
    public int board[][]=new int[6][7];
    boolean t=false;
        boolean b=true;
    int count=0;
    public Board(){
        /////////create matrix and array for valid column
        for(int r=0;r<6;r++)
            for(int c=0;c<7;c++)
                board[r][c]=0;
        

    }
        ////////Drop Piece
    public int[][] drop(int matrix[][],int x,int y,boolean t,int mat){
        int p1=1;
        int p2=2;
        if(mat==1){
         if(t==false)
          board[x][y]=p1;
          else board[x][y]=p2;
          System.out.print("Board");  
          print(board);
         turn=!turn;
          
          }
        else if(mat==0){
         if(t==false)
          matrix[x][y]=p1;
          else matrix[x][y]=p2;
          }
         return matrix;
         
    }
  
    public int setColumn(int x){
        int row=0;           
        
        for(row=5;row>=0;row--)
            if(board[row][x]==0)
                break;
       if(row>=0)
        drop(board, row, x, turn, 1);
            return row;
    }
 

    
     //////find best cost for best move
    public int evaluation(int matrix[][],boolean turn){
        int player; 
        
    	if(turn)player=2;
    	else player=1;
    	
    	
    	int cost =0;
    	int countrow =0,countcol=0,countleft=0,countright=0;
    	
    	//row
        for(int i=0;i<6;i++)
        	for(int j=0;j<4;j++) 
             for(int k=0;k<4;k++) {
            	 if(matrix[i][j+k]!=0&&matrix[i][j+k]!=player)break;
            	 if(k==3)countrow++;
        	}

        //column
        for(int i=0;i<3;i++)
        	for(int j=0;j<7;j++) 
             for(int k=0;k<4;k++) {
            	 if(matrix[i+k][j]!=0&&matrix[i+k][j]!=player)break;
            	 if(k==3) countcol++;
        	}
        
       //right 
        for(int i=0;i<3;i++)
        	for(int j=0;j<4;j++) 
             for(int k=0;k<4;k++) {
            	 if(matrix[i+k][j+k]!=0&&matrix[i+k][j+k]!=player)break;
            	 if(k==3) countright++;
        	}
        //left
        for(int i=3;i<6;i++)
        	for(int j=3;j<7;j++) 
             for(int k=0;k<4;k++) {
            	 if(matrix[i-k][j-k]!=0&&matrix[i-k][j-k]!=player)break;
            	 if(k==3) countleft++;
        	}
        
    return countcol+countrow+countright+countleft;
    }
        /////////find best move
    public int alpha_beta(int matrix[][],int depth,int alpha,int beta,boolean maximizing){
    	
   print(matrix);
   
     if(checkDraw(matrix))return 0;
     else if(winai(matrix))return 100;
     else if(winplayer(matrix))return -100;
     else if(depth==0) return evaluation(matrix,maximizing);	
  
 
   	
     if(maximizing) {
    	 int maxeval=-1000;
    	 for(int i=0;i<6;i++)
    		 for(int j=0;j<7;j++) {

    			 
    			 if(i<5) {
    				 if((matrix[i][j]==0&&matrix[i+1][j]!=0)) {
    					 count++;
    					 int eval=alpha_beta(drop(matrix,i, j,true, 0),depth-1,alpha,beta,false);
    					 matrix[i][j]=0;
    					 count--;
    					 if( eval>maxeval) {
    						 maxeval=eval;
    						 if(count==0) {
    							 x=i;
        						 y=j; 
        						 b=true;
    						 }
    						
    					 }
    					
    					 alpha=Math.max(alpha,eval);
    					 if(beta<=alpha)break;
    				 }
    		 	 }
    				  if(i==5) {
    					if( matrix[i][j]==0) {
    						 count++;
    					 int eval=alpha_beta(drop(matrix,i, j,true, 0),depth-1,alpha,beta,false);
    					 matrix[i][j]=0;
    					 count--;
    					 if( eval>maxeval) {
    						 maxeval=eval;
    						 if(count==0) {
    							 x=i;
        						 y=j; 
        						 b=true;
    						 }
    					 }
    					 
    					 alpha=Math.max(alpha,eval);
    					 if(beta<=alpha)break;
    					}
    				 }
    			
    				 
    				
    				 
    		 }
   
    	 return maxeval;
     }
     else {
    	 int mineval=1000;
    	 for(int i=0;i<6;i++)
    		 for(int j=0;j<7;j++) {
    			 if(i<5) {
    				 if((matrix[i][j]==0&&matrix[i+1][j]!=0)) {
    					 int eval=alpha_beta(drop(matrix,i, j,false, 0),depth-1,alpha,beta,true);
        				 matrix[i][j]=0;
        				
    					  if(eval<mineval) {
    						 mineval=eval;


    					 }
        				 beta=Math.min(beta,eval);
        				 if(beta<=alpha)break; 
    				 }
    			 }
    				if(i==5) {
    					if( matrix[i][j]==0) {
    					 int eval=alpha_beta(drop(matrix,i, j,false, 0),depth-1,alpha,beta,true);
           				 matrix[i][j]=0;
           				 if(eval<mineval) {
    						 mineval=eval;
    						 x=i;
    						 y=j;
    					 }
           				 beta=Math.min(beta,eval);
           				 if(beta<=alpha)break; 
    					}
    				 }
    			
    		 }

    	 return mineval;
    			 }
    	
         

     }
    	
    /////////print board to check results
    public void print(int x[][]){
        System.out.println(""); 
         for (int row = 0; row < 6; row++)
                 {  
                     
                 for (int col =0; col < 7; col++)
                     {
                      System.out.print(x[row][col]);
                       System.out.print(" ");   
                     }
                      System.out.println("");   
                 }
     System.out.println("");   
    
    }
    ///////////check if draw
     public boolean checkDraw(int matrix[][]){
         int count=0;
           for (int row = 5; row >= 0; row--)
                 {  
                     
                 for (int col =0; col < 7; col++)
                     {
                         if(matrix[row][col]!=0)
                             count++;
                     } 
                 }
           if (count==42)
               f=true;
           else f=false;
           return f; 
    }
     //////check if someone win
     public boolean winai(int matrix[][]){
        for (int row = 0; row <=5; row++){
              for (int col=0; col <=6; col++)
             {
                 if(matrix[row][col]==2)
                 {
                     if(col<4)///////////////////////check horizontal win for player 1
                     {
                          if(matrix[row][col+1]==2 && matrix[row][col+2]==2 && matrix[row][col+3]==2)
                         {
                           //   JOptionPane.showMessageDialog(null,"PLAYER 1 WIN (Horizontal)");
                              return true;
                        
                         }
                     }

                     if(row<3)///////////////////////check vertical win for player 1
                     {
                         if(matrix[row+1][col]==2 && matrix[row+2][col]==2 && matrix[row+3][col]==2)
                          {
                            //  JOptionPane.showMessageDialog(null,"PLAYER 1 WIN (Vertical)");
                              return true;
                             
                          }
                     }
                     if(row<3 && col<4)///////////////////////check diagonal win for player 1
                     {
                        if(matrix[row+1][col+1]==2 && matrix[row+2][col+2]==2 && matrix[row+3][col+3]==2)
                         {
                          //  JOptionPane.showMessageDialog(null,"PLAYER 1 WIN (Positive Diagonal)");
                            return true;
                          
                         }
                     }
                     if(row<3 && col>2)///////////////////////check diagonal win for player 1
                     {
                        if(matrix[row+1][col-1]==2 && matrix[row+2][col-2]==2 && matrix[row+3][col-3]==2)
                         {
                           // JOptionPane.showMessageDialog(null,"PLAYER 1 WIN (Negative Diagonal)");
                            return true;
                         
                         }
                     }
                  
                
              
                  
                    
                     }
                    
                     }
                }
                  



        return false;
     }
     
     
     
     
     //////check if someone win
     public boolean  winplayer(int matrix[][]){
        for (int row = 0; row <=5; row++){
              for (int col=0; col <=6; col++)
             {
                
               
               
                if(matrix[row][col]==1)
                 {
                     if(col<4)///////////////////////check horizontal win for player 2
                     {
                          if(matrix[row][col+1]==1 && matrix[row][col+2]==1 && matrix[row][col+3]==1)
                         {
                            //  JOptionPane.showMessageDialog(null,"PLAYER 2 WIN (Horizontal)");
                              return true;
                         
                         }
                     }

                     if(row<3)///////////////////////check vertical win for player 2
                     {
                         if(matrix[row+1][col]==1 && matrix[row+2][col]==1 && matrix[row+3][col]==1)
                          {
                            //  JOptionPane.showMessageDialog(null,"PLAYER 2 WIN (Vertical)");
                              return true;
                          
                          }
                     }

                     if(row<3 && col<4)///////////////////////check diagonal win for player 2
                     {
                        if(matrix[row+1][col+1]==1 && matrix[row+2][col+2]==1 && matrix[row+3][col+3]==1)
                         {
                           //  JOptionPane.showMessageDialog(null,"PLAYER 2 WIN (Positive Diagonal)");
                            return true;
                      
                         }
                        
                        if(row<3 && col>2)///////////////////////check diagonal win for player 2
                        {
                           if(matrix[row+1][col-1]==1 && matrix[row+2][col-2]==1 && matrix[row+3][col-3]==1)
                            {
                           //     JOptionPane.showMessageDialog(null,"PLAYER 2 WIN (Positive Diagonal)");
                               return true;
                         
                            }
                     }
                 
                     }
                 }
                
             }   
              
        }
        return false;
     }
        
      
     
     ///////// Give the turn
     public boolean getturn(){
         return turn;
     }
     ///////// someone win so stop
     public boolean getf(){
         return f;
     }
     ///////// Artifical intelligence turn
     public int ai(int l){
         int a;
         int z=0;
      if(l==0){
         a= (int)(Math.random()*(6-0+1)+0); 
         z=a;
      }
       if(l==1){
          alpha_beta(board,1,-1000,1000,true); 
         z=y;
      }
        if(l==2){
         alpha_beta(board,3,-1000,1000,true); 
         z=y;
      }
      
      return z;
     }
     public void checkDraw(){
         int count=0;
           for (int row = 5; row >= 0; row--)
                 {  
                     
                 for (int col =0; col < 7; col++)
                     {
                         if(board[row][col]!=0)
                             count++;
                     } 
                 }
           if (count==42)
             
             JOptionPane.showMessageDialog(null, "Draw");
           
    }
     //////check if someone win
     public void checkforwin(){
        for (int row = 0; row <= 5; row++){
              for (int col=0; col <= 6; col++)
             {
                 if(board[row][col]==1)
                 {
                     if(col+3<=6)///////////////////////check horizontal win for player 1
                     {
                          if(board[row][col+1]==1 && board[row][col+2]==1 && board[row][col+3]==1)
                         {
                              JOptionPane.showMessageDialog(null,"PLAYER 1 WIN (Horizontal)");
                              f=true;
                              break;
                         }
                     }

                     if(row+3<=5)///////////////////////check vertical win for player 1
                     {
                         if(board[row+1][col]==1 && board[row+2][col]==1 && board[row+3][col]==1)
                          {
                              JOptionPane.showMessageDialog(null,"PLAYER 1 WIN (Vertical)");
                              f=true;
                              break;
                          }
                     }
                     if(row+3<=5 && col+3<=6)///////////////////////check diagonal win for player 1
                     {
                        if(board[row+1][col+1]==1 && board[row+2][col+2]==1 && board[row+3][col+3]==1)
                         {
                            JOptionPane.showMessageDialog(null,"PLAYER 1 WIN (Positive Diagonal)");
                            f=true;
                            break;
                         }
                     }
                     if(row-3>=0 && col+3<=6)///////////////////////check diagonal win for player 1
                     {
                        if(board[row-1][col+1]==1 && board[row-2][col+2]==1 && board[row-3][col+3]==1)
                         {
                            JOptionPane.showMessageDialog(null,"PLAYER 1 WIN (Negative Diagonal)");
                            f=true;
                            break;
                         }
                     }
                 }
                
                if(board[row][col]==2)
                 {
                     if(col+3<=6)///////////////////////check horizontal win for player 2
                     {
                          if(board[row][col+1]==2 && board[row][col+2]==2 && board[row][col+3]==2)
                         {
                              JOptionPane.showMessageDialog(null,"PLAYER 2 WIN (Horizontal)");
                              
                              break;
                         }
                     }

                     if(row+3<=5)///////////////////////check vertical win for player 2
                     {
                         if(board[row+1][col]==2 && board[row+2][col]==2 && board[row+3][col]==2)
                          {
                              JOptionPane.showMessageDialog(null,"PLAYER 2 WIN (Vertical)");
                             
                              break;
                          }
                     }

                     if(row+3<=5 && col+3<=6)///////////////////////check diagonal win for player 2
                     {
                        if(board[row+1][col+1]==2 && board[row+2][col+2]==2 && board[row+3][col+3]==2)
                         {
                             JOptionPane.showMessageDialog(null,"PLAYER 2 WIN (Positive Diagonal)");
                             
                            break;
                         }
                     }
                     if(row-3>=0 && col+3<=6)///////////////////////check diagonal win for player 1
                     {
                        if(board[row-1][col+1]==2 && board[row-2][col+2]==2 && board[row-3][col+3]==2)
                         {
                            JOptionPane.showMessageDialog(null,"PLAYER 2 WIN (Negative Diagonal)");
                           
                            break;
                         }
                     }
                 }
             }       
        }
     }
 }







