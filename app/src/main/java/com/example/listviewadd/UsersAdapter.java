package com.example.listviewadd;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class UsersAdapter extends BaseAdapter {

    Context context;
    List<Users> users;

    public UsersAdapter(Context context, List<Users> users) {
        this.context = context;
        this.users = users;
    }
    @Override
    public int getCount() {
        return users.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(context).inflate(R.layout.users_list_item, viewGroup, false);

        TextView email = view.findViewById(R.id.emailTextView);
        TextView password = view.findViewById(R.id.passwordTextView);

        email.setText(users.get(i).getEmail());
        password.setText(users.get(i).getPassword());

        return view;
    }
}
