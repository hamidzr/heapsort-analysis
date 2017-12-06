#!/usr/bin/env python3.6
import matplotlib.pyplot as plt
from scipy.interpolate import spline
import numpy as np
import os
import subprocess
import math
import re
from tqdm import tqdm

JAVA = '/usr/bin/java'
JVM_INIT_MEMORY = '500m'
JVM_MAX_MEMORY = '4g'
EAT_MEMORY = "a" * int(2 * math.pow(10,9))

def testWith(size, maxNum):
    cmd = f'{JAVA} -Xms{JVM_INIT_MEMORY} -Xmx{JVM_MAX_MEMORY} Driver -array_size {size} -max_number {maxNum}'
    p = subprocess.Popen(cmd, shell=True, stdout=subprocess.PIPE)
    resRaw, stderr = p.communicate()
    # resRaw = os.popen(cmd).read()
    return extractSortResults(resRaw.decode())


# in line
def extractSortResults(raw, asTuple=True):
    lines = raw.split('\n')
    results = []
    timeRegex = re.compile(r'(.+) took (\d+) nanoseconds');
    for line in lines:
        match = timeRegex.match(line)
        if (match):
            pair = {'name': match.group(1), 'time': match.group(2)};
            results.append(pair)
    if (asTuple):
        times = list(map(lambda pair: pair['time'], results))
        names = list(map(lambda pair: pair['name'], results))
        return names, times
    return results


def generateConfigs(numTests=30, startSize=1000, growthRate=1000):
    # numRange = 10 * 1000000
    configs = []
    for i in range(0, numTests):
        size = startSize + i * growthRate
        numRange = size # increase the range with size
        arrayMemorySize = size * 4 / math.pow(10,6)
        print('memory size', arrayMemorySize, 'mb')
        configs.append([size, numRange ])
    return configs

def analyze(configs):
    timesMatrix = np.empty((len(configs), len(sortNames)), dtype=int)
    for idx, [size, maxNum] in enumerate(configs):
        print(f'[{idx+1}/{len(configs)}] testing with array of size {size}, and range {maxNum}')
        names, times = testWith(size, maxNum)
        timesMatrix[idx] = times
    return timesMatrix

def smooth(xs, ys):
    xs_smooth = np.linspace(xs.min(), xs.max(), 300)
    ys_smooth = spline(xs, ys, xs_smooth)
    return xs_smooth, ys_smooth

def plot(configs, times, names, xAxis):
    xs = None
    if (xAxis == 'size'):
        xs = np.array(list(map(lambda c: c[0]/1000, configs)))
    elif (xAxis == 'range'): #chart by range
        xs = np.array(list(map(lambda c: c[1]/1000, configs)))




    for y in range(0,len(names)):
        ys = np.array(list(map(lambda nano: nano/math.pow(10,6), times[:,y])))
        if (xAxis):
            xs_smooth, ys_smooth = smooth(xs, ys)
            plt.plot(xs_smooth, ys_smooth) # chart xs
        else:
            xs = np.arange(0,len(configs),1)
            xs_smooth, ys_smooth = smooth(xs, ys)
            plt.plot(xs_smooth, ys_smooth)
    plt.title(f'Comparison')
    plt.ylabel('Time (ms)')
    if (xAxis): plt.xlabel(f'Input Size {xAxis}(k)')
    plt.legend(names, loc='upper left');
    # plt.savefig(constants.RESULTS_DIR + f'/acc-{configToString()}.png', bbox_inches='tight')
    plt.show()
    plt.close() # clear fig

sortNames = testWith(1,1)[0]
configs = generateConfigs(startSize=20000000, growthRate=0, numTests=10)
times = analyze(configs)
plot(configs, times, sortNames, xAxis='size')

EAT_MEMORY = EAT_MEMORY + 1 # keep the it in memory 
