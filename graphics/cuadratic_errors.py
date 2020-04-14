from matplotlib import pyplot as plt
import math
import subprocess

def errorc(analytic_values, given):
    error_cuadratic = 0
    for i in range(0, len(given)):
        error = given[i] - analytic_values[i]
        error = error ** 2
        error_cuadratic += error
    return error_cuadratic / len(given)


def analytic(times):
    m = 70.0
    gamma = 100.0
    k = 10000.0
    return [math.exp(-(gamma*t)/(2*m)) * math.cos(math.sqrt(k/m - gamma ** 2/ (4* (m ** 2))) * t) for t in times]

algorithms = ["Beeman", "Verlet", "GP5"]
errors = []
for r in range(0, len(algorithms)):
    command = 'java -jar ../target/harmonic-oscillator-1.0-SNAPSHOT-jar-with-dependencies.jar -alg {alg}'.format(alg = algorithms[r])
    p = subprocess.Popen(command, shell=True, stdout=subprocess.PIPE, stderr=subprocess.STDOUT)
    lines =  p.stdout.readlines()
    times = [float(lines[x].split()[0]) for x in range(0, len(lines))]
    positions = [float(lines[x].split()[1]) for x in range(0, len(lines))]
    analytic_values = analytic(times)
    errors.append(1/math.log(errorc(analytic_values, positions)) * -1)

plt.style.use('seaborn')
plt.bar(algorithms, errors)
plt.xlabel('Algoritmo')
plt.ylabel('Error cuadratico 1/log(err)')
plt.legend()
plt.show()




