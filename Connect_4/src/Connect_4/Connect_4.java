/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Connect_4;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author raedk
 */
public class Connect_4 {


    public static void main(String[] args) throws IOException {
             Front f=new Front();
             f.setVisible(true);
             f.setResizable(false);
              int a= (int)(Math.random()*(1-0+1)+0); 
             if(a==0){
             f.setAI();}
             }


    }
