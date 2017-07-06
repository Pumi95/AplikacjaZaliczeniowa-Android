package com.example.pumi.quizpilkarski;

/**
 * Created by Pumi on 2017-07-05.
 */

public class Question {
    String img_src, question, ans_1, ans_2, ans_3, ans_4, ans_good;

    public Question(String img_src, String question, String ans_1, String ans_2, String ans_3, String ans_4, String ans_good) {
        this.img_src = img_src;
        this.question = question;
        this.ans_1 = ans_1;
        this.ans_2 = ans_2;
        this.ans_3 = ans_3;
        this.ans_4 = ans_4;
        this.ans_good = ans_good;
    }

    public String inLine(){
        return img_src + "x007xx" + question + "x007xx" + ans_1 + "x007xx" + ans_2 + "x007xx" + ans_3 + "x007xx" + ans_4 + "x007xx" + ans_good;
    }

}
