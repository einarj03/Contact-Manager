package com.example.einarjonsson.myapplication;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //declares editable text variables for the inputs
    EditText nameTxt, phoneTxt, emailTxt, addressTxt;

    //declares an array list called Contacts
    List<Contact> Contacts = new ArrayList<Contact>();

    //declares a new list view called contactListView
    ListView contactListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //defines the variables for the inputs
        nameTxt = (EditText) findViewById(R.id.inputName);
        phoneTxt = (EditText) findViewById(R.id.inputPhone);
        emailTxt = (EditText) findViewById(R.id.inputEmail);
        addressTxt = (EditText) findViewById(R.id.inputAddress);
        final Button addBtn = (Button) findViewById(R.id.btnAdd);
        contactListView = (ListView) findViewById(R.id.listView);

        //Sets up the tab menu
        TabHost tabHost = (TabHost) findViewById(R.id.tabHost);
        tabHost.setup();

        //Names the first tab as "New Contact"
        TabHost.TabSpec tabSpec = tabHost.newTabSpec("creator");
        tabSpec.setContent(R.id.tabCreator);
        tabSpec.setIndicator("New Contact");
        tabHost.addTab(tabSpec);

        //Names the second tab as "My Contacts"
        tabSpec = tabHost.newTabSpec("list");
        tabSpec.setContent(R.id.tabContactList);
        tabSpec.setIndicator("My Contacts");
        tabHost.addTab(tabSpec);

        //Tells the application to look out for text changes in the name input
        nameTxt.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //do nothing before text is changed
            }

            //every time the text in the name input is changed this code runs
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //only enables the button if the name input is not empty
                addBtn.setEnabled(!nameTxt.getText().toString().trim().isEmpty());
            }


            @Override
            public void afterTextChanged(Editable s) {
                //do nothing after text is changed
            }
        });

        //do the following when the Add Contact button is pressed
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Creates a new contact with the current details that were added in
                addContact(nameTxt.getText().toString(), phoneTxt.getText().toString(), emailTxt.getText().toString(), addressTxt.getText().toString());

                //adds the latest contact details to "My Contacts"
                populateList();

                //Prints a message when the contact has been created
                Toast.makeText(getApplicationContext(), nameTxt.getText().toString() + " has been added to your contacts!", Toast.LENGTH_SHORT).show();

                //sorts the array list
                Collections.sort(Contacts, new ContactNameComparator());
            }
        });

    }



    //defines a function to create contacts, adds a new Contact object to the array list Contacts
    private void addContact (String name, String phone, String email, String address) {
        Contacts.add(new Contact(name, phone, email, address));

    }

    //defines the function that adds new contacts to the contact list
    private void populateList() {
        ArrayAdapter<Contact> adapter = new ContactListAdapter();
        contactListView.setAdapter(adapter);
    }

    //defines a new class that helps to add new contacts to the contact list
    public class ContactListAdapter extends ArrayAdapter<Contact> {

        public ContactListAdapter() {
            super (MainActivity.this, R.layout.listview_item, Contacts);
        }

        @Override
        public View getView(int position, View view, ViewGroup parent) {
            //if the view is empty inflate the view
            if (view == null)
                view = getLayoutInflater().inflate(R.layout.listview_item, parent, false);

            Contact currentContact = Contacts.get(position);

            //sets the contactName textbox on the screen to be the name input for the new contact
            TextView name = (TextView) view.findViewById(R.id.contactName);
            name.setText(currentContact.getName());

            //sets the phoneNumber textbox on the screen to be the phone number input for the new contact
            TextView phone = (TextView) view.findViewById(R.id.phoneNumber);
            phone.setText(currentContact.getPhone());

            //sets the email textbox on the screen to be the email input for the new contact
            TextView email = (TextView) view.findViewById(R.id.email);
            email.setText(currentContact.getEmail());

            //sets the address textbox on the screen to be the address input for the new contact
            TextView address = (TextView) view.findViewById(R.id.address);
            address.setText(currentContact.getAddress());

            return view;
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

}
