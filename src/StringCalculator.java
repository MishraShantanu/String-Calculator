/**
 * NAME : SHANTANU MISHRA
 * EMAIL: shantanu.mishra@usask.ca
 *
 */


import java.lang.*;



public class StringCalculator {


    /***
     * Method for extracting the numbers from string and applying
     * computation on them.
     * @param numbers String which hold the passed string with numbers
     * @param del String which hold value of delimiter to be used
     * @return int sum of the number
     */

    public static int sum(String numbers,String del){

        //to remove the new line char from string
        numbers = numbers.replace("\n", "");

        //base case for extracting numbers
        String[] array = numbers.split(",");

        if(!(del==",")) {
            String delArray[] = del.split(",");

            if (numbers.length() > 0) {
                String mainDel = "";
                //for extracting number of delimiter
                for (int j = 0; j < delArray.length; j++) {
                    String temp = "";


                    // "\\" to make regex and all other symbol work properly without any exception
                    for (int i = 0; i < delArray[j].length(); i++) {
                        if (temp.length() == 0) {
                            temp = "\\" + delArray[j].charAt(i);
                        } else temp = temp + "\\" + delArray[j].charAt(i);

                    }

                    if (mainDel.length() == 0) {
                        mainDel = "[" + temp;
                    } else mainDel = mainDel + "," + temp;


                }

                mainDel = mainDel + "]";
                //multi or single delimiter can be passed this way
                array = numbers.split(mainDel);
            }

        }




        //checking if string contain negative numbers
        // if it contains then make a string of those numbers
        int sum = 0;
        String tempNegative = "";
        for(int i=0;i<array.length;i++){
            try {
                if(Integer.parseInt(array[i])<0){
                    if(tempNegative.length()==0){
                            tempNegative = array[i];
                    }else tempNegative = tempNegative +","+array[i];
                    }
            }catch (NumberFormatException t){

                }

            }
        //for terminating program if negative numbers are found
        // and displaying msg with the negative numbers
        try {
            if(tempNegative.length()>0){
                throw new NegativeNumberException("Negative numbers not allowed"+tempNegative);
                }
        }catch (NegativeNumberException e){
                System.out.println(e);
            }


       //for adding the numbers
        for (int i = 0; i < array.length; i++) {

            try {
                //if greater than 1001 then value of number will
                // not be added to the sum
                if(Integer.parseInt(array[i])<1001){

                    sum = sum + Integer.parseInt(array[i]);
                    }

                } catch (NumberFormatException e) {

                }

            }

            return sum;
        }

    /***
     *
      * @param numbers : String passes for computation
     * @return int: sum of the number, if empty string is passed it will return 0
     */
    public static int Add(String numbers) {
        //return 0 if given empty string
        if(numbers.length()!=0){
            //cheking the format of string
            if (!(numbers.charAt(0) == '/' && numbers.charAt(1) == '/')) {
                return sum(numbers,",");
            } else if((numbers.charAt(0) == '/' && numbers.charAt(1) == '/')){
                //for finding the delimiter present
                int tracker = 2;
                String del = "";
                while (!(numbers.charAt(tracker) == '\n')) {
                    del = del + numbers.charAt(tracker);
                    tracker++;
                }


                return sum(numbers,del);



            }

        }

        return 0;
    }


    /***
     * This method is for testing, It shows the string passed, expected result
     * and output produced.
     *
     */
    public static void test(){
        System.out.println("Empty string should return 0 result:"+Add(" "));
        System.out.println("Sum of \"1,2,3\" should be 6 result: " +Add("1,2,3"));
        System.out.println("Sum of \"1\\n,2,3\" should be 6 result: "+Add("1\n,2,3"));
        System.out.println("Sum of \"//;\\n1;3;4\" should be 8 result: "+Add("//;\n1;3;4"));
        System.out.println("Sum of \"//$\\n1$2$3\" should be 6 result: "+Add("//$\n1$2$3"));
        System.out.println("Sum of \"//@\\n2@3@8\" should be 13 result: "+Add("//@\n2@3@8"));

        System.out.println("Sum of \"2,1000,1001\" should be 1002 result: "+Add("2,1000,1001"));

        System.out.println("Sum of \"//;;;;;;\\n1;;;;;;3;;;;;;4\" should be 8 result: "+Add("//;;;;;;\n1;;;;;;3;;;;;;4"));
        System.out.println("Sum of \"//$$$$$$$$$$\\n1$$$$$$$$$$2$$$$$$$$$$3\" should be 6 result: "+Add("//$$$$$$$$$$\n1$$$$$$$$$$2$$$$$$$$$$3"));
        System.out.println("Sum of \"//@&,$,*^\\n2@&3$8*1\" result should be 14:  "+Add("//@&,$,*^\n2@&3$8*1"));
        System.out.println("Sum of \"//@&,$,*^\\n-2@&3$8*-1\" should be  Negative numbers not allowed-2,-1 "+Add("//@&,$,*^\n-2@&3$8*-1"));
    }



    public static void main (String[]args){

        //calling test cases
        test();
    }

    }
