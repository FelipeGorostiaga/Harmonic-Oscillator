from matplotlib import pyplot as plt
from functions import *
import math
import subprocess


algorithms = ["Beeman", "Verlet", "GP5"]
errors = []
for r in range(0, len(algorithms)):
    command = 'java -jar ../target/harmonic-oscillator-1.0-SNAPSHOT-jar-with-dependencies.jar -alg {alg}'.format(alg = algorithms[r])
    p = subprocess.Popen(command, shell=True, stdout=subprocess.PIPE, stderr=subprocess.STDOUT)
    lines =  p.stdout.readlines()
    times = [float(lines[x].split()[0]) for x in range(0, len(lines))]
    positions = [float(lines[x].split()[1]) for x in range(0, len(lines))]
    analytic_values = analytic(times)
    errors.append(1 / math.log(errorc(analytic_values, positions)) * -1 )

print(errors)
plt.style.use('seaborn')
plt.bar(algorithms, errors)
plt.xlabel('Algoritmo')
plt.ylabel('Error cuadratico')
plt.legend()
plt.show()




