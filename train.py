
#import numpy as np
import pandas as pd
from sklearn.model_selection import train_test_split
from sklearn.linear_model import LogisticRegression
#from sklearn.metrics import accuracy_score


# Data collection and processing
# loding csv data to a Pandas Dataframe
heart_data = pd.read_csv('heart.csv')

#getting some data about data
# print(heart_data.head())
# print(heart_data.head())
# print(heart_data.shape)
# print(heart_data.info())
# print(heart_data.isnull().sum())

#statastical measures about data
 
# checking the distribution of traget value

# print(heart_data['target'].value_counts())

# 1 detect defectivie heart and 0 healthy heart
#spliting the features and target
x = heart_data.drop(columns='target',axis=1)
y = heart_data['target']
# print(x)
# print(y)

# spliting the data imto the training data and testing data

x_train,x_test,y_train,y_test = train_test_split(x, y, test_size=0.2, stratify=y, random_state=2)
# print(x.shape, x_train.shape, x_test.shape)

# model training
# Logostic regression model

model = LogisticRegression()

# train logistic regressin model with traiing data

model.fit(x_train,y_train)

# model evaluation
# accuracy score
# accuracy on training data

# x_train_prediction = model.predict(x_train)
# training_data_accuracy = accuracy_score(x_train_prediction, y_train)
# print('Accuracy on training data: ', training_data_accuracy)    

# accuracy on test data

# x_test_prediction = model.predict(x_test)
# testing_data_accuracy = accuracy_score(x_test_prediction, y_test)
# print('Accuracy on testing data: ', testing_data_accuracy)  
