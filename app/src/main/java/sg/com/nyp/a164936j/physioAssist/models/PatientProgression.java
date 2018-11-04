package sg.com.nyp.a164936j.physioAssist.models;

import java.util.ArrayList;

public class PatientProgression {

    private String exerciseTitle;
    private String noOfPetals;
    private ArrayList<String> selectedExercise;
    private ArrayList<String> selectedNoOfPetals;

    public String getExerciseTitle() {
        return exerciseTitle;
    }

    public void setExerciseTitle(String exerciseTitle) {
        this.exerciseTitle = exerciseTitle;
    }

    public String getNoOfPetals() {
        return noOfPetals;
    }

    public void setNoOfPetals(String noOfPetals) {
        this.noOfPetals = noOfPetals;
    }

    public ArrayList<String> getSelectedExercise() {
        return selectedExercise;
    }

    public void setSelectedExercise(ArrayList<String> selectedExercise) {
        this.selectedExercise = selectedExercise;
    }

    public ArrayList<String> getSelectedNoOfPetals() {
        return selectedNoOfPetals;
    }

    public void setSelectedNoOfPetals(ArrayList<String> selectedNoOfPetals) {
        this.selectedNoOfPetals = selectedNoOfPetals;
    }
}
