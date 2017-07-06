package com.example.pumi.quizpilkarski;

import android.view.View;

/**
 * Created by Pumi on 2017-07-05.
 */

public class MyLovelyOnClickListener implements View.OnClickListener
{

    int myLovelyVariable;
    public MyLovelyOnClickListener(int myLovelyVariable) {
        this.myLovelyVariable = myLovelyVariable;
    }

    @Override
    public void onClick(View v)
    {
        //read your lovely variable
    }

};