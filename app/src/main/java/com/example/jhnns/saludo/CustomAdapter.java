package com.example.jhnns.saludo;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jhnns on 30.08.2016.
 */

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {
    private static final String TAG = "CustomAdapter";

    private List<Object> mDataSet;
    private List<Integer> mDataSetTypes;

    public static final int PATIENT__DETAIL = 0;
    public static final int PERSONALIA__DETAIL = 1;
    public static final int SYMPTOM__LIST = 2;


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View v) {
            super(v);
        }
    }

    public class PatientDetailViewHolder extends ViewHolder {
        TextView patient_name;

        public PatientDetailViewHolder(View v) {
            super(v);
            this.patient_name = (TextView) v.findViewById(R.id.patient_name);
        }
    }

    public class PersonaliaDetailViewHolder extends ViewHolder {
        public final TextView personalia_last_name;
        public final TextView personalia_birthday;
        public final TextView personalia_name;

        public PersonaliaDetailViewHolder(View v) {
            super(v);
            this.personalia_name = (TextView) v.findViewById(R.id.personalia_name);
            this.personalia_last_name = (TextView) v.findViewById(R.id.personalia_last_name);
            this.personalia_birthday = (TextView) v.findViewById(R.id.personalia_birthday);
        }
    }

    public class SymptomListViewHolder extends ViewHolder {
        TextView symptom__list;

        public SymptomListViewHolder(View v) {
            super(v);
            this.symptom__list = (TextView) v.findViewById(R.id.symptom__list);
        }
    }


    public CustomAdapter() {
        mDataSet = new ArrayList<>();
        mDataSetTypes = new ArrayList<>();
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v;
        if (viewType == PATIENT__DETAIL) {
            v = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.patient_card, viewGroup, false);

            return new PatientDetailViewHolder(v);
        } else if (viewType == PERSONALIA__DETAIL) {
            v = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.personalia_card, viewGroup, false);
            return new PersonaliaDetailViewHolder(v);
        } else if (viewType == SYMPTOM__LIST){
            v = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.symptoms_card, viewGroup, false);
            return new SymptomListViewHolder(v);
        }
        else {
            v = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.symptoms_card, viewGroup, false);
            return new SymptomListViewHolder(v);
        }
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        if (viewHolder.getItemViewType() == PATIENT__DETAIL) {
            PatientDetailViewHolder holder = (PatientDetailViewHolder) viewHolder;
            Patient patient = (Patient) mDataSet.get(position);
            holder.patient_name.setText(patient.username);
        }
        else if (viewHolder.getItemViewType() == SYMPTOM__LIST) {
            SymptomListViewHolder holder = (SymptomListViewHolder) viewHolder;
            Symptom symptom = (Symptom) mDataSet.get(position);
            holder.symptom__list.setText(symptom.name);
        }
        else {
            PersonaliaDetailViewHolder holder = (PersonaliaDetailViewHolder) viewHolder;
            Personalia personalia = (Personalia) mDataSet.get(position);
            holder.personalia_name.setText(personalia.name);
            holder.personalia_last_name.setText(personalia.last_name);
            holder.personalia_birthday.setText(personalia.birthday);
        }
    }

    public void addSaludView(SaludView saludview){
        mDataSet.add(saludview.asc_model);
        mDataSetTypes.add(saludview.viewType);
    }
    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    @Override
    public int getItemViewType(int position) {
        return mDataSetTypes.get(position);
    }
}