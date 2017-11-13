package com.androidprojects.esprit.ikotlin.adapters;

import android.app.ListActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.androidprojects.esprit.ikotlin.R;


public class List1_Adapter extends BaseAdapter{
    private static final int ITEM_VIEW_TYPE_CONTENT = 0;
    private static final int ITEM_VIEW_TYPE_SEPARATOR = 1;
    private static final int ITEM_VIEW_TYPE_COUNT = 2;
    private String[] content;
    private ListActivity adaptTo;

    public List1_Adapter(ListActivity adaptTo, String[]content){
        this.adaptTo=adaptTo;
        this.content=content;
    }

    @Override
    public int getCount() {
        return content.length;
    }

    @Override
    public Object getItem(int i) {
        return content[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
    @Override
    public int getViewTypeCount() {
        return ITEM_VIEW_TYPE_COUNT;
    }

    @Override
    public int getItemViewType(int position) {
        return (content[position].startsWith("Sep_")) ? ITEM_VIEW_TYPE_SEPARATOR : ITEM_VIEW_TYPE_CONTENT;
    }
    @Override
    public boolean isEnabled(int position) {
        // A separator cannot be clicked !
        return getItemViewType(position) != ITEM_VIEW_TYPE_SEPARATOR;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final int type = getItemViewType(i);

        // First, let's create a new convertView if needed. You can also
        // create a ViewHolder to speed up changes if you want ;)
        if (view == null) {
            view = LayoutInflater.from(adaptTo.getBaseContext()).inflate(
                    type == ITEM_VIEW_TYPE_SEPARATOR ? R.layout.list1_rowseperator : R.layout.list1_row, viewGroup, false);
        }

        // We can now fill the list item view with the appropriate data.
        if (type == ITEM_VIEW_TYPE_SEPARATOR) {
            ((TextView) view.findViewById(R.id.separatorTxt)).setText(getItem(i).toString().substring(4,getItem(i).toString().length())); // remove the "_Sep" from the string !
        } else {

            ((TextView) view.findViewById(R.id.itemContent)).setText((String) getItem(i));
        }
        return view;
    }
}
