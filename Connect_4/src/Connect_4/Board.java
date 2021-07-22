/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Connect_4;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Board {
    boolean f;
    public boolean turn=false;
    public int arr_col[]=new int[7]; 
    public int[][] matrix = new int[6][7];
    public int c[][]=new int[6][7];
    public Board(){
        /////////create matrix and array for valid column
             for (int row = 0; row < 6; row++)
                   {
                 for (int col = 0; col < 7; col++)
                   {
                     
                       matrix[row][col] = 0; 
                     
                   }
                   }
             for (int i=0 ; i<arr_col.length; i++)
                 arr_col[i]=-1;
          
    }
        ////////Drop Piece
    public void drop(int x,int y,boolean t,int mat){
        int p1=1;
        int p2=2;
        if(mat==1){
         if(t==false)
          matrix[x][y]=p1;
          else matrix[x][y]=p2;
          System.out.print("Board");  
         print(matrix);
         turn=!turn;
          }
        else if(mat==0){
         if(t==false)
          c[x][y]=p1;
          else c[x][y]=p2;
          }
         
         
    }
    /////////Check if the column is valid to drop a piece
    public boolean valid_column(int x){
        int flag=0;
        boolean fv;
         for(int row=0; row< 6;row++){
              if (matrix[row][x]==0){
                 flag=1;
              break;
              }
                 }
         if(flag==1)
             fv=true;
         else fv=false;
          return fv;
    
    }
    
    ///////// find a valid row in valid column
    public int valid_row(int x){
         int row;
         for(row=0; row< 6;row++){
              if (matrix[row][x]==0)
                 break;
                 }
         return row;
    }
    //////////Choose a column
    public int setColumn(int x){
            int y=7;  
            if(valid_column(x)){
              y=valid_row(x);
              drop(y,x,turn,1);
            }
            return y;
    }
    //////////check all valid column
    public int [] check_valid_column(){  
        for(int c=0 ; c<7; c++)
         if(valid_column(c))
            arr_col[c]=c;
         else arr_col[c]=-1;
        return arr_col;
    }
     //////find best cost for best move
    public int cost(int row,int col){
         int count=0;
         int count2=0;
         int count3=0;
         int count4=0;
         int count5=0;
         int count6=0;
         
         int counter []=new int[6];
         for(int i=0; i<counter.length; i++)
             counter[i]=0;
         
        int cost=0;
        int flag=0;
        int flag_c=0;
        ////////////////////////Horizontal  count 
           for(int co=0;co <7 ;co++){
               if(c[row][co]==2){
                   count++;
                   flag=1; //// check if first node in counting is 2
                if(co==col)
                    flag_c=1; /////////check if it reach a given node
               }
               ///////////////if not = 2
               else if(flag==1) {
                   /////////////check if it reach the given node / if not count =0 and counting again
                   if(flag_c!=1){
                      count=0;
                      continue;
                   }
                    ///////////////if not = 2 / if it =0 or 1
                   else break;}
             }
           counter[0]=count; /// store horizontal cout
         
           ////////////////////////Virtical  count 
           int flag2=0;
           int flag2_c=0;
            for(int ro=0;ro <6 ;ro++){
               if(c[ro][col]==2){
                   count2++;
                   flag2=1; /// check if first node in counting is 2
                   if(ro==row)
                    flag2_c=1; /////////check if it reach a given node
               }
                ///////////////if not = 2
               else if(flag2==1) {
                   /////////////check if it reach the given node / if not count =0 and counting again
                   if(flag2_c!=1){
                      count2=0;
                      continue;
                   }
                    ///////////////if not = 2 / if it =0 or 1
                   else break;}
             }
             counter[1]=count2; /////////store virtical count
             
            ////Negative diagonal  descending count
            count3++;
            int co=col;
              for(int ro=row;ro>0 ;){
                  ro--;
                  co++;
               if(co<7){
               if(c[ro][co]==2){
                   count3++;
               }
                ///////////////if not = 2 / if it =0 or 1
               else break;
             }}
              counter[2]=count3; /////////store Negative diagonal  descending count
              
              ////Negative diagonal  ascending count
               count4++;
             co=col;
              for(int ro=row;ro<5 ;){
                  ro++;
                  co--;
                if(co>=0){
               if(c[ro][co]==2){
                   count4++;
               }
                ///////////////if not = 2 / if it =0 or 1
               else break;
             }}
               counter[3]=count4; /////////store Negative diagonal  ascending count
               
                    ////Positive diagonal decending count 
                    co=col;
                    count5++;
              for(int ro=row;ro>0 ;){
                    co--;
                  ro--;
         
               if(co>=0){
               if(c[ro][co]==2){
                   count5++;
               }
                ///////////////if not = 2 / if it =0 or 1
               else break;
             }}
               counter[4]=count5;  /////////store Negative diagonal  ascending count
               
                 ////Positive diagonal acending count 
                      co=col;
                    count6++;
              for(int ro=row;ro<5 ;){
                    co++;
                  ro++;
               if(co<7){
               if(c[ro][co]==2){
                   count6++;
               }
                ///////////////if not = 2 / if it =0 or 1
               else break;
             }}
               counter[5]=count6;   /////////store Positive diagonal  ascending count
               
               int counting=0;
              for(int i=0;i<counter.length;i++)
                  if(counter[i]>counting)
                     counting=counter[i]; //////////find best count
              
             if(counting==4) 
               cost+=100;
             if(counting==3) 
                cost+=10;
             if(counting==2) 
                cost+=1;
             
             System.out.println("row="+row);
             System.out.println("count="+count);
             System.out.println("count2="+count2);
             System.out.println("count3="+count3);
             System.out.println("count4="+count4);
             System.out.println("count5="+count5);
             System.out.println("count6="+count6);
             System.out.println("cost="+cost);
             System.out.println("Expected probability ");
             print(c);
    return cost;
    }
        /////////find best move
    public int best_loc(){
      int y[]=new int[7];
      y=check_valid_column();
      int best_score=0;
      int best_col;
      ////////// random column from valid column array
      while(true){
          int a= (int)(Math.random()*(6-0+1)+0); 
          if(y[a]!=-1){
          best_col=y[a];
          break;
          }
      }
      int row=0;
      int score=0;
      int i;
      for(i=0;i<7;i++){
        if(y[i]!=-1){
        row=valid_row(y[i]);
        for(int cc=0;cc<7;cc++)
            for(int rr=0; rr<6 ;rr++)
        c[rr][cc]=matrix[rr][cc];
        drop(row,y[i],turn,0);
        score=cost(row,y[i]);
      if (score>best_score){
         best_score=score;
         best_col=i;
        }
        }
      }
      
      
      return best_col;
    }
    /////////print board to check results
    public void print(int x[][]){
        System.out.println(""); 
         for (int row = 5; row >= 0; row--)
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
     public boolean checkDraw(){
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
     public void checkforwin(){
        for (int row = 0; row <= 5; row++){
              for (int col=0; col <= 6; col++)
             {
                 if(matrix[row][col]==1)
                 {
                     if(col+3<=6)///////////////////////check horizontal win for player 1
                     {
                          if(matrix[row][col+1]==1 && matrix[row][col+2]==1 && matrix[row][col+3]==1)
                         {
                              JOptionPane.showMessageDialog(null,"PLAYER 1 WIN (Horizontal)");
                              f=true;
                              break;
                         }
                     }

                     if(row+3<=5)///////////////////////check vertical win for player 1
                     {
                         if(matrix[row+1][col]==1 && matrix[row+2][col]==1 && matrix[row+3][col]==1)
                          {
                              JOptionPane.showMessageDialog(null,"PLAYER 1 WIN (Vertical)");
                              f=true;
                              break;
                          }
                     }
                     if(row+3<=5 && col+3<=6)///////////////////////check diagonal win for player 1
                     {
                        if(matrix[row+1][col+1]==1 && matrix[row+2][col+2]==1 && matrix[row+3][col+3]==1)
                         {
                            JOptionPane.showMessageDialog(null,"PLAYER 1 WIN (Positive Diagonal)");
                            f=true;
                            break;
                         }
                     }
                     if(row-3>=0 && col+3<=6)///////////////////////check diagonal win for player 1
                     {
                        if(matrix[row-1][col+1]==1 && matrix[row-2][col+2]==1 && matrix[row-3][col+3]==1)
                         {
                            JOptionPane.showMessageDialog(null,"PLAYER 1 WIN (Negative Diagonal)");
                            f=true;
                            break;
                         }
                     }
                 }
                
                if(matrix[row][col]==2)
                 {
                     if(col+3<=6)///////////////////////check horizontal win for player 2
                     {
                          if(matrix[row][col+1]==2 && matrix[row][col+2]==2 && matrix[row][col+3]==2)
                         {
                              JOptionPane.showMessageDialog(null,"PLAYER 2 WIN (Horizontal)");
                              f=true;
                              break;
                         }
                     }

                     if(row+3<=5)///////////////////////check vertical win for player 2
                     {
                         if(matrix[row+1][col]==2 && matrix[row+2][col]==2 && matrix[row+3][col]==2)
                          {
                              JOptionPane.showMessageDialog(null,"PLAYER 2 WIN (Vertical)");
                              f=true;
                              break;
                          }
                     }

                     if(row+3<=5 && col+3<=6)///////////////////////check diagonal win for player 2
                     {
                        if(matrix[row+1][col+1]==2 && matrix[row+2][col+2]==2 && matrix[row+3][col+3]==2)
                         {
                             JOptionPane.showMessageDialog(null,"PLAYER 2 WIN (Positive Diagonal)");
                             f=true;
                            break;
                         }
                     }
                     if(row-3>=0 && col+3<=6)///////////////////////check diagonal win for player 1
                     {
                        if(matrix[row-1][col+1]==2 && matrix[row-2][col+2]==2 && matrix[row-3][col+3]==2)
                         {
                            JOptionPane.showMessageDialog(null,"PLAYER 2 WIN (Negative Diagonal)");
                            f=true;
                            break;
                         }
                     }
                 }
             }       
        }
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
     public int ai(){
       return best_loc();
     }
 }






