package com.androidprojects.esprit.ikotlin.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.androidprojects.esprit.ikotlin.R;
import com.androidprojects.esprit.ikotlin.models.Chapter;

/**
 * Created by Amal on 25/11/2017.
 */

public class ChaptersList_Adapter extends BaseAdapter {

    private Context context;
    private Chapter[] chapters;

    public ChaptersList_Adapter(Context context,Chapter[]chapters){
        this.context=context;
        this.chapters=chapters;
    }
    @Override
    public int getCount() {
        return chapters.length;
    }

    @Override
    public Object getItem(int position) {
        return chapters[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView= ((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE))
                .inflate(R.layout.chapterslist_item, parent, false);
        ((TextView) rowView.findViewById(R.id.chapterTitle)).setText(chapters[position].getTitle());
        ((TextView) rowView.findViewById(R.id.chapterDescription)).setText(chapters[position].getDescription());
        ((TextView) rowView.findViewById(R.id.nbbadgesEarned_chapter)).setText(String.valueOf(chapters[position].getNbBadges()));
        ((TextView) rowView.findViewById(R.id.timeNeeded_chapter)).setText(String.valueOf(chapters[position].getTimeTocomplete()));
        return rowView;
    }
}
