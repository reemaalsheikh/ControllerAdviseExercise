package com.example.capstone2.Service;

import com.example.capstone2.Api.ApiException;
import com.example.capstone2.Model.CDPrediction;
import com.example.capstone2.Model.Doctor;
import com.example.capstone2.Model.User;
import com.example.capstone2.Repsitory.CDPredictionRepository;
import com.example.capstone2.Repsitory.DoctorRepository;
import com.example.capstone2.Repsitory.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
//Chronic Disease Prediction
public class CDPredictionService {

    private final CDPredictionRepository cdPredictionRepository;
    private final UserRepository userRepository;
    private final DoctorRepository doctorRepository;

    // Get all Chronic Disease Prediction
    public List<CDPrediction> getAllChronicDP() {
        return cdPredictionRepository.findAll();
    }


    // Add new Chronic Disease Prediction
    public void addChronicDP (CDPrediction cdPrediction) {
       User uId = userRepository.findUserByUserId(cdPrediction.getUserId());

       if (uId == null) {
           throw new ApiException("User id is not valid! Please try again!");
       }
        Doctor doc = doctorRepository.findDoctorByDoctorId(cdPrediction.getDoctorId());
        if(doc == null){
            throw new ApiException("Doctor id is not valid, Only doctors can add Chronic Disease Prediction!");
        }
        cdPrediction.setCreatedAt(LocalDate.now());
        cdPrediction.setUpdatedAt(LocalDate.now());
        cdPredictionRepository.save(cdPrediction);

    }


    //Update Chronic Disease Prediction
    public void updateChronicDP (CDPrediction cdPrediction , Integer id) {
        // Check if the user exists
        User uId = userRepository.findUserByUserId(cdPrediction.getUserId());
        if (uId == null) {
            throw new ApiException("User id is not valid! Please try again!");
        }
        Doctor doc = doctorRepository.findDoctorByDoctorId(cdPrediction.getDoctorId());
        if(doc == null){
            throw new ApiException("Doctor id is not valid, Only doctors can add Chronic Disease Prediction!");
        }
    // Check if the Chronic Disease Prediction id exists
        CDPrediction cd = cdPredictionRepository.findCDPredictionById(id);
        if (cd == null) {
            throw new ApiException("Chronic Disease Prediction id not found! Please try again!");
        }

        cd.setBloodPressure(cdPrediction.getBloodPressure());
        cd.setBloodSugar(cdPrediction.getBloodSugar());
        cd.setCholesterolLevel(cdPrediction.getCholesterolLevel());
        cd.setStressLevel(cdPrediction.getStressLevel());
        cd.setPredictedCondition(cdPrediction.getPredictedCondition());
        cd.setRecommendations(cdPrediction.getRecommendations());
        cd.setUpdatedAt(LocalDate.now());
        cdPredictionRepository.save(cd);
    }


    //delete Chronic Disease Prediction
    public void deleteChronicDP (Integer id) {
        CDPrediction cd = cdPredictionRepository.findCDPredictionById(id);
        if (cd == null) {
            throw new ApiException("Chronic Disease Prediction id not found!");
        }

        cdPredictionRepository.delete(cd);
    }


    //Blood pressure indication
    public CDPrediction bloodPressureIndication (Integer id) {
        CDPrediction cd = cdPredictionRepository.findCDPredictionById(id);
        if (cd == null) {
            throw new ApiException(" Chronic Disease Prediction is not found! Please try again!");
        }
        User uId = userRepository.findUserByUserId(cd.getUserId());
        if (uId == null) {
            throw new ApiException("User id is not valid! Please try again!");
        }

        Doctor doc = doctorRepository.findDoctorByDoctorId(cd.getDoctorId());
        if(doc == null){
            throw new ApiException("Doctor id is not valid, Only doctors can add Chronic Disease Prediction!");
        }

        int bloodPressure = cd.getBloodPressure();

        if (bloodPressure < 120){
            cd.setPredictedCondition("Normal Blood Pressure!");
            cd.setRecommendations("stick with heart-healthy habits such as following a balanced diet and getting regular exercise.");

        } else if (bloodPressure >= 120 && bloodPressure <= 129) {
            cd.setPredictedCondition("Elevated blood pressure!");
            cd.setRecommendations("work with your health care professional to control the condition.");

        } else if (bloodPressure >= 130 && bloodPressure <= 139) {
            cd.setPredictedCondition("Hypertension Stage 1");
            cd.setRecommendations("your health care professional is likely to prescribe lifestyle changes. They may consider adding medication based on your risk of heart disease or stroke and should add medication if you have other conditions such as diabetes, heart failure and kidney disease.");

        } else if (bloodPressure >=140 && bloodPressure <= 180 ) {
        cd.setPredictedCondition("Hypertension Stage 2");
        cd.setRecommendations("your health care professional should prescribe blood pressure medication and lifestyle changes.");

        } else if (bloodPressure > 180) {
            cd.setPredictedCondition("Hypertensive crisis!");
            cd.setRecommendations("- you need medical attention." +
                    "- Wait five minutes after your first reading."+
                    "- Take your blood pressure again."+
                    "- If your readings are still unusually high, contact your health care professional immediately." +
                    "- Call 911 if your blood pressure is higher than 180/120 and you are having:" +
                    "- chest pain" +
                    "- shortness of breath" +
                    "- back pain" +
                    "- numbness" +
                    "- weakness" +
                    "- change in vision" +
                    "-difficulty speaking  ");
        }
        cdPredictionRepository.save(cd);
        return cd;
    }



    //Blood pressure indication
    public CDPrediction bloodSugarIndication (Integer id) {
        CDPrediction bs = cdPredictionRepository.findCDPredictionById(id);
        if (bs == null) {
            throw new ApiException(" Chronic Disease Prediction is not found! Please try again!");
        }
        User uId = userRepository.findUserByUserId(bs.getUserId());
        if (uId == null) {
            throw new ApiException("User id is not valid! Please try again!");
        }
        Doctor doc = doctorRepository.findDoctorByDoctorId(bs.getDoctorId());
        if(doc == null){
            throw new ApiException("Doctor id is not valid, Only doctors can add Chronic Disease Prediction!");
        }

        int bloodSugar = bs.getBloodSugar();

        if (bloodSugar >= 70 && bloodSugar <=90){
            bs.setPredictedCondition("Normal Blood Sugar!");
            bs.setRecommendations("The focus should be on maintaining a healthy lifestyle to prevent the onset of diabetes and other health issues.\n" +
                    "- Diet: Continue to eat a balanced diet rich in fruits, vegetables, whole grains, and lean proteins. Avoid excessive consumption of sugary and processed foods.\n" +
                    "- Exercise: Engage in regular physical activity, such as brisk walking, cycling, or swimming, for at least 150 minutes per week.\n" +
                    "- Monitoring: Regularly monitor blood sugar levels, especially if there are any changes in health or lifestyle.\n" +
                    "- Hydration: Drink plenty of water throughout the day.\n" +
                    "- Sleep: Ensure 7-9 hours of quality sleep each night to support overall health.");

        } else if (bloodSugar >= 100 && bloodSugar <= 125) {
            bs.setPredictedCondition("Pre Diabetes!");
            bs.setRecommendations("the goal is to prevent or delay the progression to type 2 diabetes through lifestyle modifications.\n" +
                    "- Diet: Low-sugar, high-fiber diet.\n" +
                    "- Exercise: Increase physical activity to at least 30 minutes most days.\n" +
                    "- Weight Management: Gradual weight loss if overweight.\n" +
                    "- Regular Monitoring:Regular blood sugar monitoring.\n" +
                    "- Stress Management: Stress management techniques like yoga or meditation." );

        } else if (bloodSugar >= 126) {
            bs.setPredictedCondition("Diabetes");
            bs.setRecommendations("managing blood sugar levels and preventing complications is crucial."+"\n"+
                    "- Diet: Follow a diabetes-friendly diet with frequent small meals." +
                    "- Exercise: Regular physical activity suited to individual fitness levels." +
                    "- Medication Management: Adherence to prescribed medications or insulin therapy.\n" +
                    "- Blood Sugar Monitoring: Regular blood glucose monitoring.\n" +
                    "- Foot Care: Daily foot care to prevent infections.\n" +
                    "- Regular Check-ups: Regular check-ups with healthcare providers for comprehensive management." +
                    "- Education and Support: Engage in diabetes education programs and seek support from diabetes educators, support groups, or counseling services.");
        }

        cdPredictionRepository.save(bs);
        return bs;
    }


    //Cholesterol Level indication
    public CDPrediction cholesterolLevelIndication (Integer id) {
        CDPrediction chol = cdPredictionRepository.findCDPredictionById(id);
        if (chol == null) {
            throw new ApiException(" Chronic Disease Prediction is not found! Please try again!");
        }
        User uId = userRepository.findUserByUserId(chol.getUserId());
        if (uId == null) {
            throw new ApiException("User id is not valid! Please try again!");
        }
        Doctor doc = doctorRepository.findDoctorByDoctorId(chol.getDoctorId());
        if(doc == null){
            throw new ApiException("Doctor id is not valid, Only doctors can add Chronic Disease Prediction!");
        }

        int cholesterolLevel = chol.getCholesterolLevel();

        if (cholesterolLevel < 200){
            chol.setPredictedCondition("Normal Cholesterol Level");
            chol.setRecommendations("Maintain healthy cholesterol levels to prevent cardiovascular disease.\n" +
                    "- Diet: Continue a balanced diet rich in fruits, vegetables, whole grains, and lean proteins. Limit intake of saturated fats and avoid trans fats.\n" +
                    "- Exercise: Engage in regular physical activity, such as walking, cycling, or swimming, for at least 150 minutes per week.\n" +
                    "- Monitoring: Regularly check cholesterol levels during routine health check-ups.\n" +
                    "- Lifestyle: Avoid smoking and limit alcohol consumption.");

        } else if ((cholesterolLevel >= 200) && (cholesterolLevel <=239) ) {
            chol.setPredictedCondition("Borderline High Cholesterol");
            chol.setRecommendations("Prevent further increase in cholesterol levels and reduce cardiovascular risk.\n"+
                    "- Diet: Reduce intake of saturated fats (e.g., fatty meats, butter) and trans fats (e.g., processed snacks). Increase intake of fiber-rich foods like oats, beans, and vegetables.\n" +
                    "- Exercise: Increase physical activity to help lower LDL (bad cholesterol) and raise HDL (good cholesterol).\n" +
                    "- Weight Management: Achieve and maintain a healthy weight.\n" +
                    "- Lifestyle: Avoid smoking, and manage stress effectively.");

        } else if (cholesterolLevel >= 240) {
            chol.setPredictedCondition("High Cholesterol!");
            chol.setRecommendations("Lower cholesterol levels to reduce the risk of heart disease and stroke.\n" +
                    "- Diet: Follow a heart-healthy diet, such as the DASH or Mediterranean diet. Focus on foods that lower LDL cholesterol, like nuts, seeds, and fatty fish rich in omega-3.\n" +
                    "- Exercise: Engage in more intense physical activities, like jogging, swimming, or cycling, for 30-60 minutes most days.\n" +
                    "- Medication Management: Take cholesterol-lowering medications if prescribed by a healthcare provider.\n" +
                    "- Regular Monitoring: Frequently monitor cholesterol levels and other related health metrics.\n" +
                    "- Lifestyle: Strictly avoid smoking and minimize alcohol intake. Implement stress-reduction practices like yoga or meditation.");

        }
        cdPredictionRepository.save(chol);
        return chol;
    }


    //Stress Level indication
    public CDPrediction stressLevelIndication (Integer id) {
        CDPrediction stress = cdPredictionRepository.findCDPredictionById(id);
        if (stress == null) {
            throw new ApiException(" Chronic Disease Prediction is not found! Please try again!");
        }
        User uId = userRepository.findUserByUserId(stress.getUserId());
        if (uId == null) {
            throw new ApiException("User id is not valid! Please try again!");
        }
        Doctor doc = doctorRepository.findDoctorByDoctorId(stress.getDoctorId());
        if(doc == null){
            throw new ApiException("Doctor id is not valid, Only doctors can add Chronic Disease Prediction!");
        }
        String stressLevel = stress.getStressLevel();

        if (stressLevel.equalsIgnoreCase("Relaxed")) {
            stress.setPredictedCondition("Low stress Level");
            stress.setRecommendations("Relaxed and balanced" +
                    "- Lifestyle: Continue with healthy habits and stress management techniques such as mindfulness, balanced diet, and regular exercise.\n" +
                    "- Monitoring: Regularly track stress levels to ensure they remain low and adjust practices as necessary.");

        } else if (stressLevel.equalsIgnoreCase("Occasional")) {
            stress.setPredictedCondition("Moderate stress Level");
            stress.setRecommendations("Occasional stress, mild anxiety or pressure" +
                    "- Diet: Maintain a balanced diet to support overall well-being and stress management.\n" +
                    "- Exercise: Engage in regular physical activity like walking, yoga, or recreational sports.\n" +
                    "- Relaxation: Practice relaxation techniques such as deep breathing exercises, meditation, or hobbies.\n" +
                    "- Time Management: Prioritize tasks and manage time effectively to reduce stress.");

        } else if (stressLevel.equalsIgnoreCase("Frequent")) {
            stress.setPredictedCondition("High stress Level");
            stress.setRecommendations("Frequent or intense stress, affecting daily life" +
                    "- Seek counseling or therapy to address underlying issues.\n" +
                    "- Engage in stress-reducing activities like exercise, relaxation techniques, and enjoyable hobbies.\n" +
                    "- Reach out to support systems like friends, family, or groups.\n" +
                    "- Evaluate and adjust work-life balance to reduce stressors.");

        } else if (stressLevel.equalsIgnoreCase("Chronic")) {
            stress.setPredictedCondition("Severe stress");
            stress.setRecommendations("Chronic and severe stress, significant impact on health " +
                    "-Consult mental health professionals for therapy, medication, and comprehensive treatment plans.\n" +
                    "- Make major lifestyle changes, including adjustments to work environment and daily routines.\n" +
                    "- Regularly monitor physical and mental health with healthcare providers.\n" +
                    "- Implement crisis management strategies for acute stress episodes.");
        }
        cdPredictionRepository.save(stress);
        return stress;
    }






        }








