package com.example.contacts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Contact> contactList;
    private ContactAdapter contactAdapter;
    private EditText nameEditText, phoneEditText, descriptionEditText;
    private View addContactView;
    private MaterialButton saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LinearLayout createContact = findViewById(R.id.createContactLY);

        // Define The RecyclerView And The Adapter.
        RecyclerView recyclerView = findViewById(R.id.recyclerViewContact);
        contactList = new ArrayList<>();
        contactAdapter = new ContactAdapter(contactList, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(contactAdapter);

        //Create The Contact.
        createContact.setOnClickListener(view -> addContact());
    }

    private void defineViews() {
        nameEditText = addContactView.findViewById(R.id.contactNameEditTxt);
        phoneEditText = addContactView.findViewById(R.id.contactPhoneEditTxt);
        descriptionEditText = addContactView.findViewById(R.id.contactDescriptionEditTxt);
        saveButton = addContactView.findViewById(R.id.saveBtn);
    }

    private void addContact() {
        addContactView = getLayoutInflater().inflate(R.layout.dialog_add_contact, null);
        defineViews();

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(addContactView);
        AlertDialog alertDialog = builder.create();

        buttonClicked(alertDialog);

        alertDialog.show();

    }

    private void buttonClicked(AlertDialog alertDialog) {
        saveButton.setOnClickListener(v -> {
            String name = nameEditText.getText().toString();
            String phone = phoneEditText.getText().toString();
            String description = descriptionEditText.getText().toString();

            if (name.length() <= 3) {
                Toast.makeText(this, "Name Should Be At Least 3 Char", Toast.LENGTH_LONG).show();
            }
            else if (phone.length() < 11) {
                Toast.makeText(this, "Number Should Be 11 Digits", Toast.LENGTH_LONG).show();
            } else {
                Contact newContact = new Contact(name, phone, description);
                contactList.add(newContact);
                contactList.sort(Comparator.comparing(contact -> contact.name));
                contactAdapter.notifyDataSetChanged();
                nameEditText.setText("");
                phoneEditText.setText("");
                descriptionEditText.setText("");
                alertDialog.dismiss();
            }
        });
    }

    public void showContactDetails(Contact contact) {
        Intent intent = new Intent(this, DetailsActivity.class);
        intent.putExtra("name", contact.getName());
        intent.putExtra("phone", contact.getPhone());
        intent.putExtra("description", contact.getDescription());
        startActivity(intent);
    }

}