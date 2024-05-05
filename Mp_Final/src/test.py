import subprocess

command = ["java", "Main"]
process = subprocess.Popen(command, stdin=subprocess.PIPE,
                           stdout=subprocess.PIPE, stderr=subprocess.PIPE, text=True)
output, errors = process.communicate(input="1")
print(output, errors)
