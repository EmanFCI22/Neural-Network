import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


public class Main {
	public static Integer M,L,N;//number of input Hidden output
    public static Integer K;//number of trainning examples
   // public static Double[] xValues,yValues;
    public static ArrayList<Double[]>xValues=new ArrayList<Double[]>();
    public static ArrayList<Double[]>yValues=new ArrayList<Double[]>();
  //array list of input arrays
    //public static ArrayList<Double[]>input =new ArrayList<Double[]>();
  //array list of output arrays
   // public static ArrayList<Double[]>output =new ArrayList<Double[]>();

    public static Double[][] Wh; //L*M
    public static Double[][] Wo;//N*L
    public static void writeFile()
    {
    	try{
    	    PrintWriter writer = new PrintWriter("result.txt", "UTF-8");
    	    String line;
    	    line="MSE "+backPropagate.MSE;
    	    writer.println(line);
    	    writer.println("Weights of hidden ");
    	    for(int i=0;i<L;i++)
    	    {
    	    	line ="";
    	    	for(int j=0;j<M;j++)
    	    	{
    	    		line+=Wh[i][j]+" ";
    	    	}
    	    writer.println(line);
    	    }
    	    writer.println("Weights of output ");
    	    for(int i=0;i<N;i++)
    	    {
    	    	line ="";
    	    	for(int j=0;j<L;j++)
    	    	{
    	    		line+=Wo[i][j]+" ";
    	    	}
    	    writer.println(line);
    	    }
    	    writer.close();
    	} catch (IOException e) {
    	   // do something
    	}
    }
    public static void readFile(String file) throws FileNotFoundException, IOException
    {
    	BufferedReader br =null;
        String currentLine;
        br=new BufferedReader(new FileReader(file));
        currentLine=br.readLine();
        String []firstLine=currentLine.split(" ");
        M=Integer.valueOf(firstLine[0]);
        L=Integer.valueOf(firstLine[1]);
        N=Integer.valueOf(firstLine[2]);
        
        currentLine=br.readLine();
        K=Integer.valueOf(currentLine);
        
        while((currentLine=br.readLine())!=null)
        {
            Double[]x=new Double[M];
            Double[]y=new Double[N];
            String[]otherLines= currentLine.split("[ ]+");
            
            for(int i=0;i<M;i++)
            {
                //System.out.println(otherLines[i+1]);
                x[i]=Double.valueOf(otherLines[i+1]);
            }
            xValues.add(x);
            for(int i=0;i<N;i++)
            {
                y[i]=Double.valueOf(otherLines[M+i+1]);
            }
            yValues.add(y);
            //System.out.println(currentLine);
        }
          
    }
    
	public static void main(String[] args) throws FileNotFoundException, IOException {
		// TODO Auto-generated method stub
		//readFile(String file);
		Scanner in =new Scanner (System.in);
	    readFile("/home/eman/workspace/GE/src/train.txt");
		Wh=new Double[L][M];
		Wo =new Double [N][L];
		//initialize weights
		feedForward.initializeWeights();
		backPropagate.backPropagateExecute();
		System.out.println("Final weights in hidden layer ");
		for(int i=0;i<L;i++)
		{
			for(int j=0;j<M;j++)
				System.out.print(Wh[i][j]+" ");
			System.out.println();
		}
		System.out.println("Final weights in output Layer ");
		for(int i=0;i<N;i++)
		{
			for(int j=0;j<L;j++)
				System.out.print(Wo[i][j]+" ");
			System.out.println();
		}
		
		writeFile();
		
		System.out.println("Enter path of file :");
		String name =in.next();
		readFile(name);
		for(int i=0;i<K;i++)
		{
			feedForward.FeedAlgorithm(i);
			for(int j=0;j<N;j++)
				System.out.print(backPropagate.outputK[j]+" ");
			System.out.println();
		}
		
	}

}
