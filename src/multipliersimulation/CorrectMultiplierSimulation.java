package multipliersimulation;

/**
 * @author Kiyeon
 * Kyle Del Castillo
 * CS 147 - Homework 2
 * Multiplication Simulator
 */

public class CorrectMultiplierSimulation 
{
    public static void main(String[] args) throws Exception 
    {
        //4-bit signed numbers are from -8 to +7
        byte[] multiplierArray = {
            -8, -7, -6, -5,
            -4, -3, -2, -1,
            0, 1, 2, 3,
            4, 5, 6, 7
        };
        
        //4-bit signed numbers are from -8 to +7
        byte[] multiplicandArray = {
            -8, -7, -6, -5,
            -4, -3, -2, -1,
            0, 1, 2, 3,
            4, 5, 6, 7
        };
     
        //String binary values for 4-bit multiplicands
        String binaryMultiplicand[] = {
            "1000", "1001", "1010", "1011", //-8, -7, -6, -5
            "1100", "1101", "1110", "1111", //-4, -3, -2, -1
            "0000", "0001", "0010", "0011", //0, 1, 2, 3
            "0100", "0101", "0110", "0111", //4, 5, 6, 7
        };
        
        //String binary values for 4-bit multipliers
        String binaryMultiplier[] = {
            "1000", "1001", "1010", "1011", //-8, -7, -6, -5
            "1100", "1101", "1110", "1111", //-4, -3, -2, -1
            "0000", "0001", "0010", "0011", //0, 1, 2, 3
            "0100", "0101", "0110", "0111", //4, 5, 6, 7
        };
        
        String twosExpected; //String for 2's complement of the expected result
        String twosResult; //String for 2's complement of the obtained result
        
        short[] expectedResult = new short[100]; //Array to store the expected result
        short[] result = new short[100]; //Array to store the obtained result
           
        //Double for loop for proper multiplication and printing
        //Simulate every possible 4-bit signed multiplication
        for(int i = 0; i < multiplicandArray.length; i++) //For every multiplier
        {
            for(int j = 0; j < multiplierArray.length; j++) //Multiply the multiplier to the multiplicand
            {
                expectedResult[i] = (short) (multiplicandArray[i] * multiplierArray[j]);
                result[i] = multiply(multiplicandArray[i], multiplierArray[j]);
                twosExpected = Integer.toBinaryString(expectedResult[i]);
                twosResult = Integer.toBinaryString(result[i]);
                
                //The following if/else statements are used to turn the corresponding binary into 8-bit format
                if(twosExpected.length() > 8)
                {
                    twosExpected = twosExpected.substring(24, twosExpected.length()); //Cuts the string into 8-bit format 
                }
                else
                {
                    twosExpected = ("00000000" + twosExpected).substring(twosExpected.length()); //Formats the binary string into 8-bit format
                }    
                
                if(twosResult.length() > 8)
                {
                    twosResult = twosResult.substring(24, twosResult.length()); //Cuts the string into 8-bit format
                }
                else
                {
                    twosResult = ("00000000" + twosResult).substring(twosResult.length()); //Formats the binary string into 8-bit format
                }
                
                //If the obtained result is not the same as expected result, print out the incorrect numbers.
                if(expectedResult[i] != result[i])
                {
                    System.out.printf("%10s%2s (%2s)%15s%2s (%2s)%20s%10s(%3s dec)%15s%10s(%3s)\n", "Multiplicand: ", binaryMultiplicand[i], multiplicandArray[i], "Multiplier: ", binaryMultiplier[j], multiplierArray[j],"Expected Result: ", twosExpected, expectedResult[i], "Obtained: ", twosResult,result[i]);
                }

            }
        }
    }
    
    //Hardware/Binary multiplication
    public static short multiply(byte multiplicand, byte multiplier)
    {
        short product = 0; //Initialize product
        
        if(multiplier < 0) //Check if multiplier is negative
        {
            multiplicand = (byte)(~multiplicand+1);
            multiplier = (byte)(~multiplier+1);
            for(int i = 0; i < 4; i++) //Condition to check if multiplier != 0 based on the hardware protocol
            {   
                if ((multiplier & 1) != 0) //Logical AND
                {
                    product = (short) (product + multiplicand); //Add previous product to multiplicand
                }
            
                multiplier = (byte) (multiplier >> 1); //Shift the multiplier to the right by 1
                multiplicand = (byte) (multiplicand << 1); //Shift the multiplicand to the left by 1
            }
        }
        else
        {
            for(int i = 0; i < 4; i++) //Condition to check if multiplier != 0 based on the hardware protocol
            {
                if ((multiplier & 1) != 0) //Logical AND
                {
                    product = (short) (product + multiplicand); //Add previous product to multiplicand
  
                }
            
                multiplier = (byte) (multiplier >> 1); //Shift the multiplier to the right by 1
                multiplicand = (byte) (multiplicand << 1); //Shift the multiplicand to the left by 1
            }
        }

        return product; //Return product
    }
    
}