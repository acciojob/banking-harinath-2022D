package com.driver;

public class CurrentAccount extends BankAccount{
    String tradeLicenseId; //consists of Uppercase English characters only

    public CurrentAccount(String name, double balance, String tradeLicenseId) throws Exception {
        // minimum balance is 5000 by default. If balance is less than 5000, throw "Insufficient Balance" exception
        super(name,balance,5000);
        if(balance < 5000) throw new Exception("Insufficient Balance");
        this.tradeLicenseId = tradeLicenseId;
        validateLicenseId();
    }

    public void validateLicenseId() throws Exception {
        // A trade license Id is said to be valid if no two consecutive characters are same
        // If the license Id is valid, do nothing
        // If the characters of the license Id can be rearranged to create any valid license Id
        // If it is not possible, throw "Valid License can not be generated" Exception

        boolean isAlready_valid = check(tradeLicenseId);
        if(!isAlready_valid){
            int[] fre = new int[26];
            for(int i=0; i<tradeLicenseId.length(); i++){
                fre[tradeLicenseId.charAt(i)-'A']++;
            }

            int max = 0,letter = 0;
            for(int i=0; i<fre.length; i++){
                if(fre[i]>max){
                    max = fre[i];
                    letter = i;
                }
            }
            if(max > (tradeLicenseId.length()+1)/2) throw new Exception("Valid License can not be generated");
            char[] arr = new char[tradeLicenseId.length()];
            int idx = 0;
            while(max > 0){
                arr[idx] = (char) (letter+'A');
                idx += 2;
                fre[letter]--;
            }

            for(int i=0; i< fre.length; i++){
                while(fre[i]>0){
                    if(idx >= arr.length) idx = 1;
                    arr[idx] = (char) (i+'A');
                    idx += 2;
                    fre[i]--;
                }
            }
            tradeLicenseId = String.valueOf(arr);
        }

    }

    public boolean check(String str){
        for(int i=1; i<str.length(); i++){
            if(str.charAt(i)==str.charAt(i-1)) return false;
        }
        return true;
    }

}
