#!/bin/bash

currentUser=$(whoami) 
userUID=$(id -u)
userGID=$(id -g)
PID=$(echo $$)

OSname=$(uname -o)
OSversion=$(uname -r)
OSbase=$(uname)

CPUs=$(nproc)
usedCPU=$(vmstat 1 2 | awk 'END {print 100 - $15}')
loadCPU=$(uptime | awk -F'load average:' '{print $2}')
CPUprocess=$(ps -p "$PID" -o %cpu --no-headers)

memoryInfo=$(free -m | grep '^Mem:' | awk '{printf "%s %s %s\n", $2, $3, $7}')
RAMtotal=$(echo $memoryInfo | cut -d' ' -f1)
RAMused=$(echo $memoryInfo | cut -d' ' -f2)
RAMavailable=$(echo $memoryInfo | cut -d' ' -f3)
                                                             
diskInfo=$(df -BM | awk 'NR==3 {printf "%s %s %s\n", $2, $3, $4}')
diskTotal=$(echo $diskInfo | cut -d' ' -f1)
diskUsed=$(echo $diskInfo | cut -d' ' -f2)
diskAvailable=$(echo $diskInfo | cut -d' ' -f3)

RAMprocess=$(ps -p "$PID" -o rss --no-headers)

IPpublic=$(curl ifconfig.me)
IPprivate=$(hostname -I)

jsonFile="sysinfo.json"

if [ -e "$jsonFile" ]; then
	rm "$jsonFile"
fi

jsonData="{"
jsonData+="\"CurrentUser\": \"$currentUser\", \n"
jsonData+="\"UID\": $userUID, \n"
jsonData+="\"GID\": $userGID, \n"
jsonData+="\"PID\": $PID, \n"
jsonData+="\"OSbase\": \"$OSbase\", \n"
jsonData+="\"OSname\": \"$OSname\", \n"
jsonData+="\"OSversion\": \"$OSversion\", \n"
jsonData+="\"CPUs\": \"$CPUs\", \n"
jsonData+="\"UsedCPU\": \"$usedCPU\", \n"
jsonData+="\"CPUloadAverage\": \"$loadCPU\", \n"
jsonData+="\"CPUforTheProcess\": \"$CPUprocess\", \n"
jsonData+="\"RAMtotal\": \"$RAMtotal\", \n"
jsonData+="\"RAMused\": \"$RAMused\", \n"
jsonData+="\"RAMavailable\": \"$RAMavailable\", \n"
jsonData+="\"RAMforTheProcess\": \"$RAMprocess\", \n"
jsonData+="\"DiskTotal\": \"$diskTotal\", \n"
jsonData+="\"DiskUsed\": \"$diskUsed\", \n"
jsonData+="\"DiskAvailable\": \"$diskAvailable\", \n"
jsonData+="\"PublicIP\": \"$IPpublic\", \n"
jsonData+="\"PrivateIP\": \"$IPprivate\""
jsonData+="}"

echo -e "$jsonData" > "$jsonFile"
