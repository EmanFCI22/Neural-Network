import java.util.Random;


public class feedForward {
	public static Double [] multiplyMatrixVector(Double[][] mat ,Double[] vector)
	{
		Double []result=new Double [mat.length]; 
		Double sum=0.0;
		for(int i =0 ;i<mat.length ;i++)
		{
				for(int k=0;k<mat[0].length;k++)
				{
					sum+=mat[i][k]*vector[k];
				}
			result[i]=sum;
			sum=0.0;
		}
		return result;
	}
	
	public static double randomFloat(double min,double max)
    {

        Random rand = new Random();

        return (double) (rand.nextDouble()* (max - min) + min);   
    }
	
	public static void initializeWeights()
    {
    	for(int i=0;i<Main.L;i++)
    	{
    		for(int j=0;j<Main.M;j++)
    			Main.Wh[i][j]=randomFloat(-5.0,5.0);
    	}
    	for(int i=0;i<Main.N;i++)
    	{
    		for(int j=0;j<Main.L;j++)
    			Main.Wo[i][j]=randomFloat(-5.0,5.0);
    	}
    }
	public static Double [] sigmoidFunction(Double [] Arr)
    {
    	Double [] I =new Double [Arr.length];
    	for(int i=0;i<Arr.length;i++)
    	{
    		I[i]=(1/(1+Math.exp(-Arr[i])));
    	}
    	return I;
    }
	public static void FeedAlgorithm(int index)
	{
		
	//compute net-input to hidden layer
		Double [] net_h=multiplyMatrixVector(Main.Wh ,Main.xValues.get(index));
	//compute net-output of hidden layer 
		backPropagate.I =sigmoidFunction(net_h);
	//compute net-input to output layer
		Double [] net_o=multiplyMatrixVector(Main.Wo ,backPropagate.I);
	//compute net-output of output layer
		backPropagate.outputK=sigmoidFunction(net_o);
	}
}
