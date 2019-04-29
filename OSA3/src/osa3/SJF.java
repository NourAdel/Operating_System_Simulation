/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package osa3;

import java.util.Collections;
import java.util.Scanner;
import java.util.Vector;

/**
 *
 * @author Wahba
 */
public class SJF {
    
    class psjf implements Comparable<psjf>{
     
        int Pn,At,Bt,Wt,Tt;
        psjf (int p, int a,int b )
        {
            Pn=p;
            At=a;
            Bt=b;
            Wt=0;
            Tt=0;
        }
        psjf(){}
        public int compareTo(psjf o) {
            int curr = Integer.compare(this.At,o.At);
            if(curr != 0) return curr;
            return Integer.compare(this.Bt, o.Bt);
        }
    }
        public void PSJF ()
        {
            int n,wait=0;
            Scanner in = new Scanner(System.in);
            System.out.println("Enter number of processes: ");
            n=in.nextInt();
            
           Vector <psjf> process=new Vector<>();
           Vector <psjf> left=new Vector<>();
            for (int i=0; i<n;i++)
            {
                System.out.println("Enter process" + (i+1) + " Arrival time: ");
                int a=in.nextInt();
                System.out.println("Enter process" + (i+1) + " burst time: ");
                int b=in.nextInt();
                process.add(new psjf(i,a,b));
                
            }
            Collections.sort(process);
            psjf p=new psjf();
            int temp;
            
            for (int i=0; i<n; i++)
            {
               p=process.get(i);
               temp=process.get(i+1).At-p.At;
             if (p.Bt<left.get(0).Bt||left.isEmpty())
             {
                 if (p.Bt>temp)
                 {
                     p.Bt-=temp;
                     if(!left.isEmpty())
                     {
                         for(int j=0;j<left.size();j++)
                         {
                             left.get(j).Wt+=temp;
                         }
                         Collections.sort(left);
                     }
                     p.At=0;
                     left.add(p);
                 }
                 else
                 {
                     if(!left.isEmpty())
                     {
                         for(int j=0;j<left.size();j++)
                         {
                             left.get(j).Wt+=p.Bt;
                         }  
                     }
                      p.Bt=0;
                 }
             
                        
            }
             else
             {
                 if(i==n-1)
                 {
                     p.At=0;
                     left.add(p);
                     Collections.sort(left);
                 }
                 else
                 {
                     p.At=0;
                     left.add(p);
                     Collections.sort(left);
                     
                     left.get(0).Bt-=temp;
                     
                     for(int j=1; j<left.size(); j++)
                     {
                         left.get(j).Wt+=temp;
                     }
                 }
             }
            
            
            
        }
            
            for (int i=0; i<left.size(); i++)
            {
                for (int j=i+1; j<left.size(); j++)
                {
                    left.get(j).Wt+=left.get(i).Bt;
                }
                left.remove(i);
            }
            int tw=0,tt=0;
            for (int i=0; i<n; i++)
            {
                process.get(i).Tt=process.get(i).Wt+process.get(i).Bt;
                tw+=process.get(i).Wt;
                tt+=process.get(i).Tt;
            }
            
           int avgw=tw/n;
           int avgt=tt/n;
           
           for (int i=0; i<process.size();i++)
        {
            int y=i+1;
            int w=process.get(i).Wt;
            int t=process.get(i).Tt;
            System.out.println("Process number:"+y+ "  "+"Its wait time:"+w+"  "+"its turnaround time:"+t);
            
         }
        
        System.out.println("Avg waiting time:"+ avgw);
        System.out.println("Avg turnaround time:"+ avgt);
        
        }

        
        
    }
    

