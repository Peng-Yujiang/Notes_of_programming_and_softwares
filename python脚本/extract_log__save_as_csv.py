import os
import re
import numpy as np
import pandas as pd

user_num = 0
CIRCLE_DATA_PATH = '/user_' + str(user_num) + '/'
for dirs in os.listdir(CIRCLE_DATA_PATH):       # 遍历文件夹下所有文件（名）
    print("\nFile name: %s"%(dirs))
    LOG_FILE = CIRCLE_DATA_PATH + dirs
    print(LOG_FILE)

    CSV_FILE = CIRCLE_DATA_PATH + os.path.splitext(dirs)[0]  + '.csv'       # 构造要保存的文件名

    
    if os.path.exists(LOG_FILE) and (os.path.splitext(dirs)[-1] == ".log"):     # 判断文件是否存在，及是否是指定格式文件
        print("\nExtracting data from log file: " + LOG_FILE)
        print("Generating data file: " + CSV_FILE)
        with open(LOG_FILE, 'r', encoding='utf-8', errors='replace') as file:       # 打开文件，替换字符不兼容的部分
            log_content = file.read()

            # 正则匹配字段
            pattern = re.compile(
                r'timestamp (\d+\.\d+)\n.*acc (-?\d+) (-?\d+) (-?\d+)\n.*gyro (-?\d+) (-?\d+) (-?\d+)')
            matches = pattern.findall(log_content)
            data_array = np.array([list(map(float, match)) for match in matches])

            data_array = data_array.astype(np.float64)
            data_array[:, 0] *= 1000000.0

            # correct timestamp when it ecceeds 2148
            ts_correct = 0
            last_ts = 0
            for ts in range(len(data_array)):
                if data_array[ts, 0] < last_ts:
                    ts_correct += 1e6
                last_ts = data_array[ts, 0]
                if ts_correct > 0:
                    print("timestamp before correction: " + str(data_array[ts,0]))
                    data_array[ts, 0] += ts_correct
                    print("timestamp after correction: " + str(data_array[ts,0]))
                
            data_array[:, 1:] /= 1e8

            csv_data = pd.DataFrame(data_array)
            csv_data.insert(1, 'PhoneTime', 0)
            csv_data.columns = ['DeviceTime', 'PhoneTime', 'ACC_X', 'ACC_Y', 'ACC_Z', 'GYRO_X', 'GYRO_Y', 'GYRO_Z']

            csv_data.to_csv(CSV_FILE, index=False, encoding='utf-8-sig')        # 以兼容的形式保存为csv文件