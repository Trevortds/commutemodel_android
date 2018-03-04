package com.etymachine.commutemodel;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.etymachine.commutemodel.db.AppDatabase;
import com.etymachine.commutemodel.db.DatabaseInitializer;
import com.etymachine.commutemodel.db.TimeEntry;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentMainPage.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentMainPage#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentMainPage extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;


    private List<TimeEntry> returnedResult;

    public FragmentMainPage() {
        // Required empty public constructor
    }

    public void populateTestData(View view) {
        Log.v("DEBUG", "HI");
        DatabaseInitializer.populateAsync(AppDatabase.getAppDatabase(getActivity()));
        Log.v("DEBUG", "Done");
        final ProgressBar spinner;
        spinner = (ProgressBar) view.findViewById(R.id.progressBar);
        spinner.setVisibility(View.VISIBLE);
        DatabaseInitializer.getAllAsync(AppDatabase.getAppDatabase(getActivity()),
                new DatabaseInitializer.GetAllAsync.AsyncResponse() {
                    @Override
                    public void processFinish(List<TimeEntry> result) {
                        spinner.setVisibility(View.GONE);
                        Log.v("DEBUG", result.toString());
                        returnedResult = result;
                    }
                });

    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentMainPage.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentMainPage newInstance(String param1, String param2) {
        FragmentMainPage fragment = new FragmentMainPage();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main_page, container, false);

        if (mListener != null) {
            mListener.onFragmentInteraction("Main Page");
        }

        // onclick listeners go here

        final ProgressBar spinner;
        spinner = (ProgressBar) view.findViewById(R.id.progressBar);
        View button = view.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.v("DEBUG", "HI");
                DatabaseInitializer.populateAsync(AppDatabase.getAppDatabase(getActivity()));
                Log.v("DEBUG", "Done");
                spinner.setVisibility(View.VISIBLE);
                DatabaseInitializer.getAllAsync(AppDatabase.getAppDatabase(getActivity()),
                        new DatabaseInitializer.GetAllAsync.AsyncResponse() {
                            @Override
                            public void processFinish(List<TimeEntry> result) {
                                spinner.setVisibility(View.GONE);
                                Log.v("DEBUG", result.toString());
                                returnedResult = result;
                            }
                        });
            }
        });

        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(String titlestring);
    }
}
