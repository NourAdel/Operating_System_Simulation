/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package osa3;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Wahba
 */
public class RoundRobin {
    
    class RR 
    {
    public int Pn, Btime,waitt,turnat;
    
    RR(int p, int b){
        Pn=p;
        Btime=b;
        waitt=0;
        turnat=0;
    }
    }
    public void RRs ()
    {
        int Pnumber, Qt;
        
        Scanner in = new Scanner(System.in);
        System.out.println("Enter number of processes: ");
        Pnumber=in.nextInt();
        
        System.out.println("Enter Time Quantum: ");
        Qt=in.nextInt();
       
        
        RR[] process=new RR[Pnumber];
        ArrayList <Integer> waittime=new ArrayList<>();
        ArrayList <Integer> turaround=new ArrayList<>();
        
        for (int i=0; i<Pnumber; i++)
        {
            System.out.println("Enter process" + (i+1) + " burst time: ");
            int bt = in.nextInt();
            process[i]=(new RR(i+1,bt));
        }
        
        
        
        
        boolean a= true;
        
        while(a)
        {
            for(int i=0;i<Pnumber;i++)
            {
                if(process[i].Btime>=Qt)
                {
                    process[i].Btime-=Qt;
                    System.out.println(process[i].Pn + " Burst time is: " + process[i].Btime);
                    for (int j=0; j<Pnumber;j++)
                    {
                        if (j!=i && process[j].Btime!=0)
                        {
                            process[j].waitt+=Qt;
                        }
                        
                    }
                 
                 }
                else
                {
                    for (int j=0; j<Pnumber;j++)
                    {
                        if (j!=i && process[j].Btime!=0)
                        {
                            process[j].waitt+=process[i].Btime;
                        }
                        process[i].Btime=0;
                        
                    }
                }
                int count=0;
                for (int k=0; k<Pnumber; k++)
                {
                    if(process[k].Btime==0)
                        count++;
                        
                }
                if(count==Pnumber)
                    a=false;
                }
            }
        
        int avgw,totalw=0;
        int avgt,totalt=0;
        for (int i=0; i<Pnumber; i++)
        {
            process[i].turnat=process[i].waitt+process[i].Btime;
            totalw+=process[i].waitt;
            totalt+=process[i].turnat;
        }
        
        avgw=totalw/Pnumber;
        avgt=totalt/Pnumber;
        
        for (int i=0; i<process.length;i++)
        {
            int y=i+1;
            int w=process[i].waitt;
            int t=process[i].turnat;
            System.out.println("Process number:"+y+ "  "+"Its wait time:"+w+"  "+"its turnaround time:"+t);
            
         }
        
        System.out.println("Avg waiting time:"+ avgw);
        System.out.println("Avg turnaround time:"+ avgt);
        
        }
        
        
    }
    
    

