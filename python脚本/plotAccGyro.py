import pandas as pd
import numpy as np
import matplotlib as plt

def PlotAccGyro(csv_file):
    df = pd.read_csv(csv_file)
    df = df[df['DeviceTime'] != 0.0]
    if df['PhoneTime'].all() == 0.0:
        ts = df['DeviceTime'].values
    else:
        ts = df['PhoneTime'].values*1000
    ts = ts.astype(float)
    ts /= 1e6

    phone_ts = df['PhoneTime'].values
    phone_ts = phone_ts.astype(float)
    phone_ts /= 1e3

    data = df.iloc[:, 2:8].values

    freq = np.diff(ts)
    frequency = 1 / np.mean(freq)
    print('mean frequency', 1 / np.mean(freq), freq.shape[0] / (ts[-1] - ts[0]))
    print('max frequency', 1 / np.min(freq))
    print('min frequency', 1 / np.max(freq))
    print('total number: {:.1f}, number of less than 50HZ: {:1f}, '
          'number of greater than 210HZ: {:.1f}'.format(freq.shape[0],
                                                        sum(np.sum(i < 50) for i in 1/freq),
                                                        sum(np.sum(i > 210) for i in 1/freq)))

    x = ts - ts[0]
    colors = ['red', 'blue', 'green', 'red', 'blue', 'green']
    plt.figure(figsize=(50, 50), dpi=200)
    # 设置全局字体大小
    plt.rcParams.update({'font.size': 24})
    plt.subplot(211)
    plt.title('Frequency: {:.2f}'.format(frequency))
    for i in range(3, 6):
        plt.plot(x, data[:, i], label=df.columns[2+i], color=colors[i])
    plt.legend()
    plt.xlim(x[0], x[-1])

    plt.subplot(212)
    for i in range(0, 3):
        plt.plot(x, data[:, i], label=df.columns[2+i], color=colors[i])
    plt.legend()
    plt.xlim(x[0], x[-1])

    png_name = csv_file.split('.csv')[0]
    plt.savefig(png_name + '_acc_gyro_analysis.png')