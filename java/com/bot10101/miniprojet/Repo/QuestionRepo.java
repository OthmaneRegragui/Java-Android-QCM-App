package com.bot10101.miniprojet.Repo;

import androidx.lifecycle.MutableLiveData;

import com.bot10101.miniprojet.Model.Question;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;

public class QuestionRepo {
    public static InputStream i;
    public MutableLiveData<Question> getQuestion(int Num) {
        MutableLiveData<Question> mld = new MutableLiveData<>();
        Question que = new Question();
        try {
            i.reset();
            byte[] b = new byte[i.available()];
            i.read(b);
            String data = new String(b, "UTF-8");
            JSONObject d = new JSONObject(data);
            JSONArray questions = d.getJSONArray("questions");
            for(int i = 0; i < questions.length(); i ++) {
                JSONObject q = questions.getJSONObject(i);
                if (Num==q.getInt("number")) {
                    que = new Question(q.getInt("number"),q.getString("question"),q.getString("answer1"),q.getString("answer2"),q.getString("answer3"),q.getInt("rightAnswer"));
                    break;
                }
            }
        } catch (Exception e) {}
        mld.setValue(que);
        return mld;
    }


}
