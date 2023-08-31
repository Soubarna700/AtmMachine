package Atm;

import static Atm.AtmScreen.pinCheck;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;
public class AtmScreen
{
    public static void main(String[] args) throws IOException
    {
        float f ;int pin;
        var file  = new File("Atm_Database"); 
        file.mkdir(); // creating a separate folder 
        
        
        var sc = new Scanner(System.in);
        System.out.println("Welcome to noobri payment bank :");
        
        String name = null;
        System.out.print("Enter your 6 digit account no : ");
        int acc = sc.nextInt();              //int e nilam
        Atmmachine atm = new Atmmachine(0, 0, name);
        String accno = acc +".bank";             //int + string ...string holo
        System.out.println("File name : "+accno);
        File user;
        try
        {//file exists korche...tai kulche 
         
           user = new File(file, accno);
           var fis = new FileInputStream(user);
           var dis = new DataInputStream(fis);
           atm.read(dis);
           dis.close();
           System.out.println("Welcome Mr "+atm.getName());
        } catch (Exception e)
        {
            System.out.println("No bank acc found .");
            System.out.print("Do you want to register ?(yes=1/no=2) : ");
            int input = sc.nextInt();
            if(input == 2)
                return;
            System.out.println("Register yourself by entering your acc no ,name and pin .");
            System.out.print("Enter Account no : ");
            acc = sc.nextInt();
            String dummy = sc.nextLine(); ///// enter take tule nilam
            System.out.print("Enter your name : ");
            name = sc.nextLine();
            System.out.print("Enter your pin : ");
            pin = sc.nextInt();
            atm = new Atmmachine(acc, pin, name);
            System.out.println("Account register successfully .");
            accno = acc +".bank";             //int + string ...string holo
            user = new File(file, accno);
            System.out.println("File name : "+accno);
            user.createNewFile();
            dataStore(atm, user);
        }
        mainloop:
        while(true)
        {
            System.out.print("1.Deposit || ");
            System.out.print("2.widthdraw || ");
            System.out.print("3.check balance || ");
            System.out.print("4.Change pin || ");
            System.out.print("5.Transfer money || ");
            System.out.print("7.Exit || ");
            System.out.println();
            System.out.print("Enter your choice : ");
            int n = sc.nextInt();
         switch (n)
          {
              case 1:    //deposit
                  System.out.print("Enter deposit amount : ");
                  f = sc.nextFloat();
                  atm.deposit(f);
                  System.out.println("Deposit successful .");
                  break;
              case 2:     //widthdraw
                //  subloop:
                  System.out.print("Enter widthdraw amount : ");
                  f = sc.nextFloat();
                  atm.widthdraw(sc,f);
             
                  break;
              case 3:
                  System.out.println("Your current balance is : "+atm.getBalance());
                  break;
              case 4:
                  // save(sc, mt);
                  for (int i = 3; i >= 1; i--)
                  {
                      System.out.print("Enter previous pin : ");
                      pin = sc.nextInt();
                      if(pinCheck(atm, pin))
                      {
                         System.out.print("Enter new pin : ");
                         pin = sc.nextInt();
                         if(pin!=atm.getPin())
                         {
                           atm.setPin(pin);
                           System.out.println("Pin set successfully .");
                           break;
                         }
                         else
                              System.out.println("You enter previous pin..please enter new pin.");
                      }
                      else
                         System.out.println("Wrong pin . Attempts left :"+(i-1));
                  }
                   break;
              case 5: 
                 // transfer
                  var other = new Atmmachine(0, 0, "other");
                  transfer(other, atm , file);
                  break;
              case 6:  // can
               //   cancel any tranjaction
                  
                  break;
              case 7:
                  dataStore(atm, user);
                  System.out.println("Thank you ");
                  break mainloop;
                  
              default:
                throw new AssertionError();
          }
        }//while er loop end 
    }
    public static void transfer( Atmmachine other, Atmmachine atm, File file ) throws IOException
    {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter another 6 digit account no : ");
        int another = sc.nextInt();
        String filename = another+".bank";
//        var file  = new File("Atm_Database"); 
        File otheruser = new File(file , filename);
        System.out.println(otheruser.getName());
        System.out.println("File name : "+filename);
        try
        {

           var fis = new FileInputStream(otheruser);
           var dis = new DataInputStream(fis);
           other.read(dis);
           System.out.println("Transfer money to : "+other.getName());
           System.out.print("Enter widthdraw amount : ");
           float f=sc.nextFloat();
           

           if(atm.widthdraw(sc,f))
           {
               other.deposit(f);
               dataStore(other, otheruser);
               System.out.println("Transfer successful");

           }

        } catch (Exception e)
        {
            System.out.println("No account found .");
        }
    }

    public static boolean pinCheck(Atmmachine atm , int pin )
    {
        return atm.getPin()==pin;
    }
    public static void dataStore(Atmmachine atm , File user) throws IOException
    {
         
         var fos = new FileOutputStream(user);
         var dos = new DataOutputStream(fos);
         atm.write(dos);
         dos.close();
    }

}




















//     for (int i = 3; i >=1; i--)
//                  {
//                      System.out.print("Enter your 4 digit pin : ");
//                      pin = sc.nextInt();
//                      if(pinCheck(atm, pin))
//                        {
//                           System.out.print("Enter widthdraw amount : ");
//                           f= sc.nextFloat();
//                           while(f>atm.getBalance())
//                           {
//                               if(atm.getBalance()==0)
//                               {
//                                   System.out.println(" First deposit some money : ");
//                                   break subloop;
//                               }
//                              System.out.println("Can't widthdraw . Widthdraw amount is grater than your balance .");
//                              System.out.print("Re enter your widthdraw amount .");
//                              f=sc.nextFloat();
//                               
//                           }
//                           atm.widthdraw(f);
//                           System.out.println("Widthdraw successful .");
//                           break;
//                        }
//                      else
//                          System.out.println("Wrong pin . Attempts left :"+(i-1));
//                  }





//           for (int i = 3; i >=1; i--)
//           {
//             System.out.print("Enter your 4 digit pin : ");
//             int pin = sc.nextInt();
//             if(pinCheck(atm, pin))
//             {
//                 System.out.print("Enter widthdraw amount : ");
//                 float f= sc.nextFloat();
//                  while(f>atm.getBalance())
//                 {
//                     
//                     System.out.println("Can't transfer . Transfer amount is grater than your balance .");
//                     System.out.print("Re enter your Transfer amount .");
//                     f=sc.nextFloat();
//                 }
//                  atm.widthdraw(f);
//                  other.deposit(f);
//                  dataStore(other, otheruser);
//                  System.out.println("Transfer successful .");
//                  break;
//             }
//               else
//                  System.out.println("Wrong pin . Attempts left :"+(i-1));
//            }

//    public static void deposit(Scanner sc, Atmmachine atm ) throws IOException
//    {
//        System.out.println("Enter your account no : ");
//        String acc = sc.nextLine();
//    }

//    public static void widthdraw(float f , Scanner sc)
//    {
//        for (int i = 3; i <=1; i--)
//        {
//            System.out.println("Enter your withdraw amount : ");
//            f=sc.nextFloat();
//            
//        }
//        
//    }