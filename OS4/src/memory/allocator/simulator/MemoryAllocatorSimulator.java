/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memory.allocator.simulator;

import java.util.Scanner;
import java.util.Vector;

/**
 *
 * @author Wahba
 */
public class MemoryAllocatorSimulator {  
    
    public static Vector <Node> nodes=new Vector<>();
    public static Vector <Node> case3=new Vector<>();
     
    public static class Node
    {
        int StartingAddress, Size, MemorySize;
        int FreeSpace=Size-MemorySize;
        boolean Availability;
        
        public Node(int s, int ss)
        {
            StartingAddress=s;
            Size=ss;
            MemorySize=0;
            Availability=true;
            
        }
    }
    public void updatefree( Node n)
    {
        n.FreeSpace=n.Size-n.MemorySize;
    }
    
    public void SetPartitions (int x)
    {
        
    }
        
    
    public void FirstFit(int m)
    {
        boolean test=true;
        for (int i=0;i<nodes.size(); i++)
        {
            if (nodes.get(i).Size>=m && nodes.get(i).Availability==true)
            {
                nodes.get(i).MemorySize=m;
                nodes.get(i).Availability=false;
                updatefree(nodes.get(i));
                test=false;
                System.out.print(" The address of the allocated partition is: ");
                System.out.println(nodes.get(i).StartingAddress);
                break;              
            }   
        }   
        if (test==true)
            System.out.println(" The address of the allocated partition is: "+"-1");
       
    }
    
    public void BestFit(int m)
    {
        int temp=0;
        boolean t=true;
        for (int i=0; i<nodes.size(); i++)
        {
            if(nodes.get(i).Size>=m && nodes.get(i).Availability==true&&nodes.get(i).Size<nodes.get(temp).Size)        
            {
                temp=i;
                t=false;
            }   
        }
        if(t==true)
            System.out.println(" The address of the allocated partition is: "+"-1");
        else
        {
            nodes.get(temp).Availability=false;
            nodes.get(temp).MemorySize=m;
            updatefree(nodes.get(temp));
            System.out.print(" The address of the allocated partition is: ");
            System.out.println(nodes.get(temp).StartingAddress);
        }
        
    }
    
    public void WorestFit (int m)
    {
        int temp=0;
        boolean t=false;
        for (int i=0; i<nodes.size(); i++)
        {
            if(nodes.get(i).Size>=m && nodes.get(i).Availability==true&&nodes.get(i).Size>nodes.get(temp).Size)        
            {
                temp=i;
                t=false;
            }   
        }
        if(t==true)
            System.out.println(" The address of the allocated partition is: "+"-1");
        else
        {
            nodes.get(temp).Availability=false;
            nodes.get(temp).MemorySize=m;
            updatefree(nodes.get(temp));
            System.out.print(" The address of the allocated partition is: ");
            System.out.println(nodes.get(temp).StartingAddress);
        }
        
    }
    
    public void PrintMemory()
    {
        for (int i=0; i<nodes.size(); i++)
        {
            System.out.println("partition "+(i+1)+":" );
            System.out.println("Startting Address: "+ nodes.get(i).StartingAddress );
            System.out.println("Size: "+ nodes.get(i).Size );
            System.out.println("Size of allocated memory: "+ nodes.get(i).MemorySize );
            System.out.println("Free Space: "+ nodes.get(i).FreeSpace );
            if(nodes.get(i).Availability==true)
                System.out.println("Availability: Free" );
            else
                System.out.println("Availability: occupied" );
            System.out.println("--------------------------------" );
            
        }
    }
    
    
    public void Deallocation ()
    {
        PrintMemory();
        
        System.out.println("Enter the Startting Address of the partition you want to Deallocate: ");
        Scanner in = new Scanner(System.in);
        int m = in.nextInt();
        boolean t=true;
        
        for (int i=0; i<nodes.size(); i++)
        {
            if (nodes.get(i).StartingAddress==m && nodes.get(i).MemorySize!=0)
            {
                nodes.get(i).MemorySize=0;
                nodes.get(i).Availability=true;
                updatefree(nodes.get(i));
                System.out.println("Partition is successfully Deallocated.");
                t=false;
                break;
            } 
        }
        if (t==true)
            System.out.println("Address is not Valid OR Partition is already Free!!");
         
    }
    
    public void DEFcase1 ()
    {
        for (int i=0; i<nodes.size(); i++)
        {
            if (nodes.get(i).MemorySize!=0 && nodes.get(i).MemorySize<nodes.get(i).Size )
            {
                int newSA=nodes.get(i).MemorySize+nodes.get(i).StartingAddress;
                int newS= nodes.get(i).Size-nodes.get(i).MemorySize;
                updatefree(nodes.get(i));
                nodes.get(i).Size-=newS;
                nodes.add(i+1,new Node(newSA, newS));
            }
        }
        PrintMemory();
    }
    
    public void DEFcase2 ()
    {
        for(int i=1; i<nodes.size();i++)
        {
            
            
            if( nodes.get(i).Availability==true && nodes.get(i-1).FreeSpace>0)
            {
                System.out.println("jfhhjhgjg");
                nodes.get(i).Size+=nodes.get(i-1).FreeSpace;
                nodes.get(i).StartingAddress=nodes.get(i-1).MemorySize;
                updatefree(nodes.get(i));
                nodes.get(i-1).Size=nodes.get(i-1).MemorySize;
                updatefree(nodes.get(i-1));
                
            }
            
            
        }
        PrintMemory();
    }
    
    public void DEFcase3 ()
    {
        int FreeSize=0;
        int newadd=0;
        for (int i=0; i<nodes.size(); i++)
        {
            if (nodes.get(i).MemorySize!=0 && nodes.get(i).MemorySize<nodes.get(i).Size )
            {
                int newSA=nodes.get(i).MemorySize+nodes.get(i).StartingAddress;
                int newS= nodes.get(i).Size-nodes.get(i).MemorySize;
                nodes.get(i).Size-=newS;
                updatefree(nodes.get(i));
                nodes.add(i+1,new Node(newSA, newS));
                updatefree(nodes.get(i+1));
            }
        }
        
        for(int i=0; i<nodes.size();i++)
        {
            if (nodes.get(i).MemorySize==0)
            {
                FreeSize+=nodes.get(i).Size;
                updatefree(nodes.get(i));
                
            }
            else
            {
                case3.add(nodes.get(i));
            }
        }
        
        nodes=case3;
        
        for(int i=0; i<nodes.size(); i++)
        {
            nodes.get(i).StartingAddress=newadd;
            updatefree(nodes.get(i));
            newadd+=nodes.get(i).MemorySize;
        }
                
        nodes.add(new Node(newadd,FreeSize));
        
        PrintMemory();
        
        
        
        
        
        
    }
    

    
 
    
    
            
    
    public static void main(String[] args) {
        // TODO code application logic here
        
       
        //nodes.add(new Node(0,50));
        //nodes.add(new Node(50,30));
        //nodes.add(new Node(80,20));
        
        MemoryAllocatorSimulator x=new MemoryAllocatorSimulator();
        
        
       // x.FirstFit(30);
        //x.BestFit(5);
        //x.DEFcase2();
     
        System.out.println("Enter the intial free memory size: ");
        
        Scanner in = new Scanner(System.in);
        int m = in.nextInt();
        x.SetPartitions(m);
        System.out.println("1) Memory Allocation");
        System.out.println("2) Memory Deallocation");
        System.out.println("3) Defragmentaion");
        System.out.println("4) Print Memory Satus");
        System.out.println("5) Exit");
        
        Scanner inn= new Scanner(System.in);
        int swch=inn.nextInt();
        
        if(swch==1)
        {
            int num;
            System.out.println("Entenr the size of the memory to be allocated: ");
            Scanner ii= new Scanner(System.in);
            num=ii.nextInt();
            System.out.println("Entenr the number of Policy: ");
            System.out.println("1) Best Fit");
            System.out.println("2) Worst Fit");
            System.out.println("3) First Fit");
            Scanner n= new Scanner(System.in);
            int ww=n.nextInt();
            if(ww==1)
                x.BestFit(num);
            else if(ww==2)
                x.WorestFit(num);
            else if(ww==3)
                x.FirstFit(num);
            else
                System.out.println("Unvalid policy number!");
                
        }
        else if(swch==2)
        {
            x.Deallocation();
        }
        else if(swch==3)
        {
            int num;
            System.out.println("Entenr the case number ");
            System.out.println("1) case 1");
            System.out.println("2) case 2");
            System.out.println("3) case 3");
            Scanner ii= new Scanner(System.in);
            num=ii.nextInt();
            if(num==1)
                x.DEFcase1();
            else if(num==2)
                x.DEFcase2();
            else if(num==3)
                x.DEFcase3();
            else
               System.out.println("Unvalid case number!"); 
            
        }
        else if (swch==4)
            x.PrintMemory();
        else if(swch==5)
            return;
        else
            System.out.println("Unvalid option number!");
        
        
        
    }
    
}
