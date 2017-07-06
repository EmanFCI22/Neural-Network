//package ga4;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.ArrayList;

/**
 *
 * @author rawan
 */
public class backPropagate 
{
   /* public static int M,L,N;//number of input Hidden output
    public static int K;//number of trainning examples
*/    //public static Double []xValues,yValues;
   /* public static ArrayList<Double[]>xValues=new ArrayList<Double[]>();//the real values
    public static ArrayList<Double[]>yValues=new ArrayList<Double[]>();
    public static Double [][]wh;//L*M
    public static Double [][]wo;//N*L
*/  
    public static Double []outputK;
    public static Double learningRate=0.4;
    public static Double []I;//size L
    public static Double MSE=0.0;
    public static Double acceptedMES=0.5;
    public static int iterationsNumber=1000;
    
    public static Double backPropagateExecute()
    {
        //first iteration
        //forward the compute output
        //MSE
        for(int y=0;y<Main.K;y++)
        {
            //call feedForward algorithm 
            feedForward.FeedAlgorithm(y);
            for(int k=0;k<Main.N;k++)
            {
                MSE+=Math.pow(Main.yValues.get(y)[k]-outputK[k],2);
            }
            
        }
        for(int z=1;z<iterationsNumber;z++)
        {
            
            MSE=MSE/Main.K;
            System.out.println("MSE is "+MSE);
            if(MSE<=acceptedMES)
            {
                //Write on file
            	System.out.println("Final MSE "+MSE);
                return MSE;
            }
            MSE=0.0;
            for(int y=0;y<Main.K;y++)
            {

                //feedForward return outputK;
            	feedForward.FeedAlgorithm(y);
                Double segmaK[]=new Double[Main.N];
                Double segmaOk[]=new Double[Main.N];
                //calculate error on output
                for(int k=0;k<Main.N;k++)
                {
                    segmaK[k]=Main.yValues.get(y)[k]-outputK[k];
                    segmaOk[k]=outputK[k]*(1-outputK[k])*segmaK[k];
                }
                Double [][]newWo=new Double[Main.N][Main.L];//N*L
                /*calculate weights on output layer "no update here because we will
                use wo later"*/
                for(int k=0;k<Main.N;k++)
                {
                    for(int j=0;j<Main.L;j++)
                    {
                        newWo[k][j]=Main.Wo[k][j]+(learningRate*segmaOk[k]*I[j]);
                    }
                }

                Double segmaJH[]=new Double[Main.L];
                double sum = 0.0;
                //calculate error on hidden
                for(int j=0;j<Main.L;j++)
                {
                    sum=0;
                    for(int k=0;k<Main.N;k++)
                    {
                        sum+=segmaK[k]*Main.Wo[k][j];
                    }
                    segmaJH[j]=I[j]*(1-I[j])*sum;
                    

                }
                //update weights on output layer
                for(int k=0;k<Main.N;k++)
                {
                    for(int j=0;j<Main.L;j++)
                    {
                        Main.Wo[k][j]=newWo[k][j];
                    }
                }
                //update weights on hidden layer
                for(int j=0;j<Main.L;j++)
                {
                    for(int i=0;i<Main.M;i++)
                    {
                        Main.Wh[j][i]=Main.Wh[j][i]+(learningRate*segmaJH[j]*
                                Main.xValues.get(y)[i]);
                    }

                }
                for(int k=0;k<Main.N;k++)
                {
                    MSE+=Math.pow(Main.yValues.get(y)[k]-outputK[k],2);
                } 
            }   
        }
        MSE=MSE/Main.K;
        //Write on file
        System.out.println("Final MSA "+MSE);
        return MSE;
    }


    
    
}