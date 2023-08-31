/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Atm;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Admin
 */
public class Atmmachine
{
    private int accno;
    private int pin;
    private String name;
    private float balance;
    
    public Atmmachine(int accno, int pin, String name)
    {
        this.accno = accno;
        this.pin = pin;
        this.name = name;
    }
    public void read(DataInputStream dis) throws IOException
    {
        accno = dis.readInt();
        name = dis.readUTF();
        pin = dis.readInt();
        balance = dis.readFloat();
    }
    public void write(DataOutputStream dos) throws IOException
    {
        dos.writeInt(accno);
        dos.writeUTF(name);
        dos.writeInt(pin);
        dos.writeFloat(balance);
    }

    public float getBalance()
    {
        return balance;
    }
    public float deposit(float f)
    {
        this.balance+=f;
        return balance;
    }
    public boolean widthdraw(Scanner sc , float f)
    {
//        System.out.print("Enter withdraw amount : ");
//        float f = sc.nextFloat();
        if(this.balance<f)
            System.out.println("Cant widthdraw.Widthdraw amount is greater than your balance./n First deposite some money");
        else
        {
            for (int i = 3; i >= 1; i--)
            {
                System.out.print("Enter your pin : ");
                int userpin = sc.nextInt();
                if(this.pin==userpin)
                {
                  this.balance-=f;
                    System.out.println("Widthdraw successful .");
                    return true;
                  //break;
                }
                else
                  System.out.println("Wrong pin . Attemts left : "+(i-1));
            }
        }
        return false;
        
        
        
        

    }

    public int getPin()
    {
        return pin;
    }

    public void setPin(int pin)
    {
        this.pin = pin;
    }

    public String getName()
    {
        return name;
    }
    
    
    
    
    
    
    
    
    
    
    
 //        //0 hole deposit kor 
//        // low 
//        //high hole tule debe
//        
//        if(this.getBalance()==0)
//            System.out.println("You dont have any balance . First deposit some amount .");
//        else if(this.getBalance()>=f)
//        {
//                return  this.balance -= f;
//                
//        }
////        else
////            return -1;
//        
//        
//      //  this.balance -= f;
//        return -1; //balance;   
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
