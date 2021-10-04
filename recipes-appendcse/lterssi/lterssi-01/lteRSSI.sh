#!/bin/sh

LEVEL1="9"
LEVEL2="14"

CHANNEL="ttyUSB_MODULE3"
FLAG=OFF
SLEEPTIME=1
TASK=wvdial
cmd_wvdial=${TASK}
cmd_conf=/tmp/.wvdial_CSQ.conf

WV_OUTPUT=/tmp/.wvdial.log

WV_Signal=${CHANNEL}SignalStatus

signal_status()
{
#	local strength_limit=$(expr ${1})

	if [ $(cat ${cmd_conf} | grep $WV_Signal | wc -l) == 0 ]; then
		echo "[Dialer ${CHANNEL}SignalStatus]" >> $cmd_conf
		echo "Init1 = AT+CSQ" >> $cmd_conf
		echo "Init2 =" >> $cmd_conf
		echo "Init3 =" >> $cmd_conf
		echo "Init4 =" >> $cmd_conf
		echo "Dial Command =" >> $cmd_conf
		echo "Dial Attempts = 1" >> $cmd_conf
		echo "Phone =" >> $cmd_conf
		echo "Modem = /dev/${CHANNEL}" >> $cmd_conf
		echo "" >> $cmd_conf
	fi

	${cmd_wvdial} -C ${cmd_conf} ${WV_Signal} &>${WV_OUTPUT} &
	sleep 1
#	killall ${cmd_wvdial}
	RETLIST=`cat ${WV_OUTPUT} | grep +CSQ: | awk '{print $2}'`

	for loop in ${RETLIST}; do
		signal_str=( ${loop} )
		# slipt arg use ','
		OLD_IFS="$IFS"
		IFS=","
		arrayALL=( $signal_str[1] )
		IFS="$OLD_IFS"
		RETVAL=$(expr ${arrayALL[0]})
#		echo $RETVAL
		if [ ${RETVAL} -lt ${LEVEL2} ]; then
			gpioset gpiochip5 3=1
		else
			gpioset gpiochip5 3=0
		fi
		if [ ${RETVAL} -gt ${LEVEL1} ]; then
			gpioset gpiochip5 2=1
		else
			gpioset gpiochip5 2=1
		fi
		if [ ${RETVAL} = 99 ]; then
			gpioset gpiochip5 2=0
			gpioset gpiochip5 3=0
		fi

	done
}

# check command

while true ;do
	if [ -e /dev/$CHANNEL ];then
		signal_status
		if [ ${SLEEPTIME} -lt 5 ]; then
			SLEEPTIME=$(expr $SLEEPTIME + $SLEEPTIME )
		fi
		if [ $FLAG = "OFF" ]; then
			SLEEPTIME=1
			FLAG=ON
		fi
		sleep $SLEEPTIME
	else
		if [ $FLAG = "ON" ]; then
			gpioset gpiochip5 2=0
			gpioset gpiochip5 3=0
			SLEEPTIME=16
			FLAG=OFF
		fi
		sleep $SLEEPTIME
	fi
done

