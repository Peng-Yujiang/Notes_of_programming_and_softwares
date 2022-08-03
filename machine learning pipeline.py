#%%
import os
import numpy as np
import pandas as pd
import pathlib
from sklearn.base import BaseEstimator, TransformerMixin
from sklearn.impute import SimpleImputer
from sklearn.preprocessing import OneHotEncoder
from sklearn.model_selection import StratifiedShuffleSplit
from sklearn.pipeline import Pipeline

#%% get current file path
currPath = os.getcwd()
resultPath = currPath + "\Results"
if not pathlib.Path(resultPath).is_dir():
    os.makedirs(resultPath)
print("current path: " + currPath)

#%% 目录和文件名合成一个路径
datapath = os.path.join("FolderName", "LowerFolderName", "")


#%% load data
def load_raw_data(data_path, csv_File_Name):
    csv_path = os.path.join(data_path, csv_File_Name)
    return pd.read_csv(csv_path)

#%% save data
resultsData.to_csv(os.path.join(resultPath, "results.csv"))

#%%
fileName = "rawData.csv"
rawData = load_raw_data(currPath, fileName)

#%% Data preprocessing
# split raw data
rawData["Time_cat"] = pd.cut(rawData["Time"],
                               bins=[0., 0.5, 1.0, 1.5, 2., np.inf],
                               labels=[1, 2, 3, 4, 5])
rawData["Time_cat"].hist()
split = StratifiedShuffleSplit(n_splits=1, test_size=0.2, random_state=35)
for train_index, test_index in split.split(rawData, rawData["Time_cat"]):
    strat_train_set = rawData.loc[train_index]
    strat_test_set = rawData.loc[test_index]

#%% look at the time category proportions in the test set
print("time category proportions")
strat_test_set["Time_cat"].value_counts() / len(strat_test_set)

#%% remove the Time_cat attribute
for set_ in (strat_train_set, strat_test_set):
    set_.drop("Time_cat", axis=1, inplace=True)


#%% looking for correlation matrix
corr_matrix = rawData.corr()
print(corr_matrix)


#%% remove label of training set
rawData = strat_train_set.drop("Amplitude", axis=1)
rawData_labels = strat_train_set["Amplitude"].copy()


#%% data cleaning
# tackle incomplete data
sample_incomplete_rows = rawData[rawData.isnull().any(axis=1)].head()
# missing values
imputer = SimpleImputer(strategy="median")
rawData_num = rawData.drop("Status", axis=1)
imputer.fit(rawData_num)
imputer.statistics_
rawData_num.median().values
X = imputer.transform(rawData_num)
rawData_tr = pd.DataFrame(X, columns=rawData_num.columns, index=rawData_num.index)
rawData_tr.loc[sample_incomplete_rows.index.values]
# missing categorical values
rawData_cat = rawData[["Status"]]
cat_encoder = OneHotEncoder()
rawData_cat_1hot = cat_encoder.fit_transform(rawData_cat)
rawData_cat_1hot.toarray()
cat_encoder = OneHotEncoder(sparse=False)
rawData_cat_1hot = cat_encoder.fit_transform(rawData_cat)


# transformer class
time_ix, amplitude_ix, status_ix = 3,4,5,6 # column indice
class CombinedAttributesAdder(BaseEstimator, TransformerMixin):
    def __init__(self, add_ColumnName_ToAdd = True): # no *args or **kargs
        # 这段程序中transformer超参数为add_ColumnName_ToAdd
        self.ColumnName_ToAdd = ColumnName_ToAdd
    def fit(self, X, y=None):
        return self  # nothing else to do
    def transform(self, X):
        New_ColumnName = X[:, ColumnIndex_3] / X[:, ColumnIndex_4]
        if self.add_ColumnName_ToAdd:
            ColumnName_ToAdd = X[:, ColumnIndex_1] / X[:, ColumnIndex_2]
            return np.c_[X, New_ColumnName, ColumnName_ToAdd]
        else:
            return np.c_[X, New_ColumnName]
attr_adder = CombinedAttributesAdder(add_ColumnName_ToAdd=False)
Data_1_extra_attribs = attr_adder.transform(rawData.values) # Numpy array
# 可通过下列代码动态地获取columnIndex
col_names = "ColumnName_1", "ColumnName_2", "ColumnName_3"
ColumnIndex_1, ColumnIndex_2, ColumnIndex_3 = [
    rawData.columns.get_loc(c) for c in col_names] # get the column indices
Data_1_extra_attribs = pd.DataFrame(
    Data_1_extra_attribs,
    columns=list(rawData.columns)+["ColumnName_1", "ColumnName_2"],
    index=rawData.index)
Data_1_extra_attribs.head()

num_pipeline = Pipeline([
    ('imputer', SimpleImputer(strategy='median')),
    ('attribs_adder', CombinedAttributesAdder()),
    ('std_scaler', StandardScaler()),
])