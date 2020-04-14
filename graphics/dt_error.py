from matplotlib import pyplot as plt
from functions import *
import subprocess
import sys

dts = range(0, 1000)
dts = [(x+1) / 10000.0 for x in dts]
algorithms = ["Beeman", "Verlet", "GP5"]

runs = []
for r in range(0, len(algorithms)):
    errors = []
    for i in dts:
        command = 'java -jar ../target/harmonic-oscillator-1.0-SNAPSHOT-jar-with-dependencies.jar -alg {alg} -dt {dt} '\
            .format(alg = algorithms[r], dt = i)
        p = subprocess.Popen(command, shell=True, stdout=subprocess.PIPE, stderr=subprocess.STDOUT)
        lines =  p.stdout.readlines()
        times = [float(lines[x].split()[0]) for x in range(0, len(lines))]
        positions = [float(lines[x].split()[1]) for x in range(0, len(lines))]
        analytic_values = analytic(times)
        errors.append(errorc(analytic_values, positions))

    runs.append(errors)


plt.style.use('seaborn')
for i in range(0, len(algorithms)):
    plt.semilogy(dts, runs[i], label=algorithms[i])

plt.xlabel('Dt')
plt.ylabel('Error Cuadratico')
plt.legend()
plt.show()

