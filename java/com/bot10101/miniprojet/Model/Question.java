package com.bot10101.miniprojet.Model;

public class Question {
    public int  Num, numeroR;
    public String Question ,rep1, rep2, rep3;


    public Question(){

    }
    public Question(int num, String question, String rep1, String rep2, String rep3,int numeroR) {
        Num = num;
        this.numeroR = numeroR;
        Question = question;
        this.rep1 = rep1;
        this.rep2 = rep2;
        this.rep3 = rep3;
    }

    public boolean check(int rep){
        if (rep==this.numeroR){
            return true;
        }else {
            return false;
        }

    }
}
