package com.androidprojects.esprit.ikotlin.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.androidprojects.esprit.ikotlin.R;
import com.androidprojects.esprit.ikotlin.fragments.LearnFragment;

import java.util.HashMap;
import java.util.List;

public class List2_Adapter extends BaseExpandableListAdapter {

    private Context context;
    private List listTitles;
    private HashMap<String, List> listData;
    private int[] icons;

    public List2_Adapter (Context context, List listTitles, HashMap<String, List>listData, int[]icons) {
        this.context = context;
        this.listTitles = listTitles;
        this.listData= listData;
        this.icons=icons;
    }

    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        return this.listData.get(this.listTitles.get(groupPosition))
                .get(childPosititon);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        final String childText = (String) getChild(groupPosition, childPosition);
        if (convertView == null)
            convertView = ((LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.list2_row, null);
        ((TextView) convertView.findViewById(R.id.listItem_text)).setText(childText);

        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this.listData.get(this.listTitles.get(groupPosition))
                .size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this.listTitles.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this.listTitles.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        String title = (String) getGroup(groupPosition);
        int  courseIcon = icons[groupPosition];

        if (convertView == null)
            convertView = ((LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.list_group, null);
        ((TextView) convertView.findViewById(R.id.listTitle_text)).setText(title);
        ((ImageView)convertView.findViewById(R.id.courseIcon)).setImageResource(courseIcon);
        (convertView.findViewById((R.id.courseOptionsBtn))).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(context,v);
                popup.inflate(R.menu.menu_course);
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        if(item.getItemId()==R.id.action_startcourse){
                            LearnFragment.dismissList();
                            LearnFragment.switchFragments();
                        }
                        if(item.getItemId()==R.id.action_startcourse){

                        }
                        if(item.getItemId()==R.id.action_startcourse){

                        }
                        return true;
                    }
                });
                popup.show();
            }
        });
        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}