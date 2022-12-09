
import numpy as np
# import pandas as pd
# from sklearn.model_selection import train_test_split
# from sklearn.linear_model import LogisticRegression
# from sklearn.metrics import accuracy_score
import train


# buliding a predicting system

age = input('age: ') 
sex = input('sex(1=male,0=female): ') 
chest_pain_type = input('chest pain type(4 values): ')
resting_blood_pressure = input('resting blood pressure: ')
serum_cholestoral = input('serum cholestoral in mg/dl: ')
fasting_blood_sugar = input('fasting blood sugar > 120 mg/dl: ')
resting_electrocardiographic_results = input('resting electrocardiographic results (values 0,1,2): ')
maximum_heart_rate_achieved = input('maximum heart rate achieved: ')
exercise_induced_angina = input('exercise induced angina: ')
oldpeak = input('oldpeak = ST depression induced by exercise relative to rest: ')
the_slope_of_the_peak_exercise_ST_segment = input('the slope of the peak exercise ST segment: ')
number_of_major_vessels = input('number of major vessels (0-3) colored by flourosopy: ')
thal = input('thal: 0 = normal; 1 = fixed defect; 2 = reversable defect: ')

input_data = (age, sex, chest_pain_type, resting_blood_pressure, serum_cholestoral,fasting_blood_sugar, resting_electrocardiographic_results, maximum_heart_rate_achieved, exercise_induced_angina, oldpeak ,the_slope_of_the_peak_exercise_ST_segment, number_of_major_vessels,thal)
#input_data = (63,1,3,145,233,1,0,150,0,2.3,0,0,1)
# change the input data into numpy array
input_data_as_numpy_array = np.asarray(input_data)

# reshape the numpy array as we are predicting for only one instance
input_data_reshaped = input_data_as_numpy_array.reshape(1,-1)
prediction = train.model.predict(input_data_reshaped)
print(prediction)

if (prediction[0] == 0):
    print("The person does not have heart disease.")
else:
    print("The person has heart disease.")



 