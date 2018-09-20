/**
 * The class  <b>LinearRegression</b> implements gradient
 * descent for multiple variables
 *
 * @author gvj (gvj@eecs.uottawa.ca)
 *
 */

public class LinearRegression{


	/** 
     * Number of features (usually "n" in litterature) 
     */
	private int nbreOfFeatures;

	/** 
     * Number of samples (usually "m" in litterature) 
     */
	private int nbreOfSamples;


	/** 
     * the nbreOfFeatures X nbreOfSamples Matrix of samples
     */
	private double[][] samplesMatrix;

	/** 
     * the nbreOfSamples Matrix of samples target values
     */
	private double[] samplesValues;

	/** 
     * the nbreOfFeatures Matrix theta of current hypthesis function
     */
	private double[] theta;


	/** 
     * number of samples received so far
     */
	private int currentNbreOfSamples;

	/** 
     * a place holder for theta during descent calculation
     */
	private double[] tempTheta;


	/** 
     * counts how many iterations have been performed
     */
	private int iteration;


	/** 
     * Object's contructor. The constructor initializes the instance
     * variables. The starting hypthesis is theta[i]=0.0 for all i
     * 
     * @param n the number of features that we will have
     * @param m the number of samples that we will have
	 *
     */
 	public LinearRegression(int n, int m){

		// your code goes there
		nbreOfFeatures = n+1;
		nbreOfSamples = m;
		samplesMatrix = new double[m][nbreOfFeatures];
		samplesValues = new double[m];
		iteration = 0;
		theta = new double [nbreOfFeatures];
		tempTheta = new double [nbreOfFeatures];

	}

	/** 
     * Add a new sample to samplesMatrix and samplesValues
     * 
     * @param x the vectors of samples
     * @param y the coresponding expected value
     *
	 */
	public void addSample(double[] x, double y){

		// your code goes there
		samplesMatrix[currentNbreOfSamples]=x;
		samplesValues[currentNbreOfSamples]=y;
		currentNbreOfSamples++;

	}

	/** 
     * Returns the current hypothesis for the value of the input
     * @param x the input vector for which we want the current hypothesis
     * 
	 * @return h(x)
	 */

	private double hypothesis(double[] x){

		// your code goes there
		double h=0;
		for(int i=0; i<nbreOfFeatures;i++){
			h += theta[i]*x[i];
		}
		return h;

	}

	/** 
     * Returns a string representation of hypthesis function
     * 
	 * @return the string "theta0 x_0 + theta1 x_1 + .. thetan x_n"
	 */

	public String currentHypothesis(){

		// your code goes there
		double [] p = new double [nbreOfFeatures];
		String s = Double.toString(theta[0]);

		for(int i=1; i<nbreOfFeatures;i++){
			s+= " + " + theta[i] + "x_" + i;
		}

		return s;

	}

	/** 
     * Returns the current cost
     * 
	 * @return the current value of the cost function
	 */

	public double currentCost(){

		// your code goes there
		double cost=0;
		for(int i=0;i<nbreOfSamples;i++){
				cost += Math.pow(hypothesis(samplesMatrix[i]) - samplesValues[i], 2);
		}
		return cost/nbreOfSamples;

	}

	/** 
     * runs several iterations of the gradient descent algorithm
     * 
     * @param alpha the learning rate
     *
     * @param numberOfSteps how many iteration of the algorithm to run
     */

	public void gradientDescent(double alpha, int numberOfSteps) {


		// your code goes there
		for (int i = 0; i < numberOfSteps; i++) {
			double [] sum = new double [nbreOfFeatures];

			for (int j = 0; j <nbreOfFeatures; j++ ) {

				for (int k=0; k<nbreOfSamples;k++){
					sum[j] += (hypothesis(samplesMatrix[k]) - samplesValues[k])*samplesMatrix[k][j];
				}

				tempTheta[j] = tempTheta[j]-alpha*(2.0/nbreOfSamples)*sum[j];
			}
			for (int m=0; m<nbreOfFeatures; m++){
				theta [m] = tempTheta[m];

			}



			iteration++;
		}

	}


	/** 
     * Getter for theta
     *
	 * @return theta
	 */

	public double[] getTheta(){

		// your code goes there
		return theta;

	}

	/** 
     * Getter for iteration
     *
	 * @return iteration
	 */

	public int getIteration(){

		// your code goes there
		return iteration;


	}
}