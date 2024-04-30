import java.util.Scanner;

public class Main {
    /*
     * Sum: (N1*D2 + N2*D1) / (D1*D2)
     * Subtraction: (N1*D2 - N2*D1) / (D1*D2)
     * Multiplication: (N1*N2) / (D1*D2)
     * Division: (N1/D1) / (N2/D2), that means (N1*D2)/(N2*D1)
     * */

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        int  a = sc.nextInt();
        String input;
        Scanner sc2 = new Scanner(System.in);
        int N1, N2, D1, D2;
        //N1 =[0] 
        //D1 =[2]
        //N2 =[4]
        //D2 =[6]
        for (int i = 0; i <a;i++) {
            
            input=sc2.nextLine();
            char operation=input.charAt(6);
            String[] numbers=input.split(" ");
            N1=Integer.parseInt(numbers[0]);
            D1=Integer.parseInt(numbers[2]);
            N2=Integer.parseInt(numbers[4]);
            D2=Integer.parseInt(numbers[6]);
            int resp1,resp2;
            switch (operation) {

                case '+':   
                resp1 = N1 * D2 + N2 * D1;
                resp2 = D1 * D2;
                break;

                case '-':
                resp1 = N1 * D2 - N2 * D1;
                resp2 = D1 * D2;
                break;

                case '*': 
                resp1=N1*N2;
                resp2=D1*D2;
                break;

                case '/':
                resp1=N1/N2;
                resp2=D1/D2;
                break;

                default:
                resp1=1;
                resp2=1;
                break;
            }
           
            int resp3=resp1, resp4=resp2, maior, menor;

            //Define maior elemento
            if(resp3>resp4){
                maior=resp3; 
                menor=resp4;}
            else if(resp3<resp4){
                maior=resp4; 
                menor=resp3;}
                else {
                    maior = menor = resp3; 
                }


            if(resp3!=resp4){
                while(menor!=0){
                    int resto=maior%menor;
                    maior=menor;
                    menor=resto;
                }  
            }
            resp3/=maior;
            resp4/=maior;
            

            System.out.println(resp1+"/"+resp2+" = "+resp3+"/"+resp4);
        }
        sc.close();
        sc2.close();
    }
   /*  public static boolean isPrime(int n){
            if(n<=1)return false;
            for(int x=2;x<n;x++){
                if(n%x==0)return false;
            }
            return true;
    } */
}
