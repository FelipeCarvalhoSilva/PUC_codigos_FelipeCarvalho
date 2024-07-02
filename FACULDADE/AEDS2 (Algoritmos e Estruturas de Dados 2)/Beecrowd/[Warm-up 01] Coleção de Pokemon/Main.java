public class Main {
    
    public static void main(String[] args) throws Exception {
       int n=MyIO.readInt();
       int capturado=0;
       String[]inputs=new String[151];
       String novo;
       boolean achou=false;
       for(int i=0;i<n;i++){
        novo = MyIO.readLine();
        for(int j=0;j<i;j++){
            if((novo.equals(inputs[j]))){achou=true;
            break;}
        }
        if(!achou){
            capturado++;
            inputs[i]=novo;
        }
        achou=false;
       }
       MyIO.print("Falta(m) " +(151-capturado)+ " pomekon(s).");
    }
}
