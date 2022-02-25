#!/bin/sh
#################################################
#		0:off				#
#		1:green				#
#		2:red				#
#		3:orange			#
#		4:not set			#
#################################################

. /etc/default/initLed

if [ X$LEDDATA == "X0" ]; then
	gpioset gpiochip5 6=1
	gpioset gpiochip5 7=1
elif [ X$LEDDATA == "X1" ]; then
	gpioset gpiochip5 6=0
	gpioset gpiochip5 7=1
elif [ X$LEDDATA == "X2" ]; then
	gpioset gpiochip5 6=1
	gpioset gpiochip5 7=0
elif [ X$LEDDATA == "X3" ]; then
	gpioset gpiochip5 6=0
	gpioset gpiochip5 7=0
fi

if [ X$LEDGPS == "X0" ]; then
	gpioset gpiochip5 4=1
	gpioset gpiochip5 5=1
elif [ X$LEDGPS == "X1" ]; then
	gpioset gpiochip5 4=0
	gpioset gpiochip5 5=1
elif [ X$LEDGPS == "X2" ]; then
	gpioset gpiochip5 4=1
	gpioset gpiochip5 5=0
elif [ X$LEDGPS == "X3" ]; then
	gpioset gpiochip5 4=0
	gpioset gpiochip5 5=0
fi

if [ X$LEDLTE == "X0" ]; then
	gpioset gpiochip5 2=1
	gpioset gpiochip5 3=1
elif [ X$LEDLTE == "X1" ]; then
	gpioset gpiochip5 2=0
	gpioset gpiochip5 3=1
elif [ X$LEDLTE == "X2" ]; then
	gpioset gpiochip5 2=1
	gpioset gpiochip5 3=0
elif [ X$LEDLTE == "X3" ]; then
	gpioset gpiochip5 2=0
	gpioset gpiochip5 3=0
fi

if [ X$LEDPWR == "X0" ]; then
	gpioset gpiochip5 0=1
	gpioset gpiochip5 1=1
elif [ X$LEDPWR == "X1" ]; then
	gpioset gpiochip5 0=0
	gpioset gpiochip5 1=1
elif [ X$LEDPWR == "X2" ]; then
	gpioset gpiochip5 0=1
	gpioset gpiochip5 1=0
elif [ X$LEDPWR == "X3" ]; then
	gpioset gpiochip5 0=0
	gpioset gpiochip5 1=0
fi

