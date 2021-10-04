#!/bin/sh

logger "starting MAC init script"

LANMAC=$(cat /proc/cmdline)
LAN0CHECK=$(ifconfig lan0 | grep HWaddr | awk '{print $5}')
LAN1CHECK=$(ifconfig lan1 | grep HWaddr | awk '{print $5}')

if [ $(echo $LANMAC | grep fec_mac | wc -l ) = 1 ];then
	LAN0MAC=${LANMAC##*fec_mac=}
	LAN0MAC=${LAN0MAC:0:17}
else
	LAN0MAC=""
fi

if [ $(echo $LANMAC | grep smsc_mac | wc -l ) = 1 ];then
	LAN1MAC=${LANMAC##*smsc_mac=}
	LAN1MAC=${LAN1MAC:0:17}
else
	LAN1MAC=""
fi

if [ ! ${LAN0MAC}X = X ];then
	if [ ! $LAN0MAC = $LAN0CHECK ];then
		#ifconfig lan0 down
		#ifconfig lan0 hw ether $LAN0MAC
		#ifconfig lan0 up
		:
	fi
fi

if [ ! ${LAN1MAC}X = X ];then
	if [ ! $LAN1MAC = $LAN1CHECK ];then
		#ifconfig lan1 down
		ifconfig lan1 hw ether $LAN1MAC
		#ifconfig lan1 up
	fi
fi

logger "initscript work done"
