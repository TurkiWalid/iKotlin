package com.androidprojects.esprit.ikotlin.utils;

import com.androidprojects.esprit.ikotlin.models.Chapter;
import com.androidprojects.esprit.ikotlin.models.Course;

import java.util.ArrayList;

/**
 * Created by Amal on 25/11/2017.
 */

public class AllCourses {


    /**** course 1 ****/

    private static ArrayList<Chapter> chapters;
    private static Course course;

    public static Course getCourse(int i){
        chapters=new ArrayList<Chapter>();
        switch (i){
            case 0:
                chapters.add(AllChapters.course1_chapter1);
                chapters.add(AllChapters.course1_chapter2);
                chapters.add(AllChapters.course1_chapter3);
                chapters.add(AllChapters.course1_chapter4);
                course=new Course(chapters,"Overview","This course containes 4 chapters. Introducing the 4 major applications of Kotlin language",2,1,120);
                break;
            case 1:
                chapters.add(AllChapters.course1_chapter1);
                chapters.add(AllChapters.course1_chapter2);
                chapters.add(AllChapters.course1_chapter3);
                chapters.add(AllChapters.course1_chapter4);
                course=new Course(chapters,"Overview","This course containes 4 chapters. Introducing the 4 major applications of Kotlin language",2,1,120);
                break;
            default:
                break;
        }

        return course;
    }

}
