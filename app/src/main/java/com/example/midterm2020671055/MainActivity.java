package com.example.midterm2020671055;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        String tmp = "<books><book><title>불변의법칙</title><author>모건하우절</author><pub>서삼독</pub><year>2024</year></book>"+"<book><title>일류의조건</title><author>사이토다카시</author><pub>필름</pub><year>2024</year></book>"+"<book><title>삼체1</title><author>류츠신</author><pub>자음과모음</pub><year>2024</year></book>"+"<book><title>모순</title><author>양귀자</author><pub>쓰다</pub><year>2023</year></book>"+"<book><title>무엇이나를행복하게만드는가</title><author>리처드J.라이더</author><pub>북플레저</pub><year>2024</year></book>"+"<book><title>돈의심리학</title><author>최설민</author><pub>북모먼트</pub><year>2024</year></book>"+"<book><title>양수인간:삶의격을높이는... </title><author>모건하우절</author><pub>인플루엔셜</pub><year>2023</year></book>"+"<book><title>나를소모하지않는현명한태도...</title><author>마티아스뇔케</author><pub>퍼스트펭귄</pub><year>2024</year></book></books>";
        ArrayList<book> list = new ArrayList<>();
        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            XmlPullParser xpp = factory.newPullParser();

            xpp.setInput(new StringReader(tmp));
            int eventType = xpp.getEventType();
            boolean tFlag = false, pFlag = false, yFlag = false ,bFlag= false;
            String tmpTitle = "", tmpPeople="", tmpYear="";
            int count = 0;
            while (eventType != XmlPullParser.END_DOCUMENT){
                if (eventType == XmlPullParser.START_TAG){
                    if (xpp.getName().equals("title")) tFlag = true;
                    if (xpp.getName().equals("author")) pFlag = true;
                    if (xpp.getName().equals("year")) yFlag = true;
                    if (xpp.getName().equals("pub")) bFlag =true;
                } else if (eventType == XmlPullParser.TEXT) {
                    if (tFlag){
                        tmpTitle = xpp.getText();
                        count++;
                        tFlag = false;
                    }else if (pFlag){
                        tmpPeople = xpp.getText();
                        count++;
                        pFlag = false;
                    }else if (bFlag){
                        count++;
                        bFlag = false;
                    }else if (yFlag){
                        tmpYear = xpp.getText();
                        count++;
                        yFlag = false;
                    }
                }
                if (count == 4){
                    list.add(new book(tmpTitle,tmpPeople,Integer.parseInt(tmpYear)));
                    count = 0;
                }
                eventType = xpp.next();
            }
        } catch (XmlPullParserException | IOException e) {
            throw new RuntimeException(e);
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new recyclerView(list));
    }
}