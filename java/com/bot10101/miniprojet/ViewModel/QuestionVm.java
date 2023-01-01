package com.bot10101.miniprojet.ViewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.bot10101.miniprojet.Model.Question;
import com.bot10101.miniprojet.Repo.QuestionRepo;

public class QuestionVm extends ViewModel {
    QuestionRepo qRepo;
    public MutableLiveData<Question> returnData(int num) {
        qRepo = new QuestionRepo();
        MutableLiveData<Question> mld = new MutableLiveData<>();
        return qRepo.getQuestion(num);
    }




}
