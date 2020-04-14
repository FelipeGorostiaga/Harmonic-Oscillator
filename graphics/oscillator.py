from matplotlib import pyplot as plt
import math
import subprocess

# Analytic solution
def analytic(times):
    m = 70.0
    gamma = 100.0
    k = 10000.0
    return [math.exp(-(gamma*t)/(2*m)) * math.cos(math.sqrt(k/m - gamma ** 2/ (4* (m ** 2))) * t) for t in times]

algorithms = ["Beeman", "Verlet", "GP5"]
run_results = []
for r in range(0, len(algorithms)):
    command = 'java -jar ../target/harmonic-oscillator-1.0-SNAPSHOT-jar-with-dependencies.jar -alg {alg}'.format(alg = algorithms[r])
    p = subprocess.Popen(command, shell=True, stdout=subprocess.PIPE, stderr=subprocess.STDOUT)
    lines =  p.stdout.readlines()
    times = [float(lines[x].split()[0]) for x in range(0, len(lines))]
    positions = [float(lines[x].split()[1]) for x in range(0, len(lines))]
    run = [times, positions]
    run_results.append(run)

analytics = analytic(run_results[0][0])

plt.style.use('seaborn')
plt.plot(run_results[0][0], analytics, label='Analitico', color='yellow')
for i in range(0, len(algorithms)):
    plt.plot(run_results[i][0], run_results[i][1], label=algorithms[i])

plt.xlabel('Time (s)')
plt.ylabel('Posicion (m)')
plt.legend()
plt.show()

