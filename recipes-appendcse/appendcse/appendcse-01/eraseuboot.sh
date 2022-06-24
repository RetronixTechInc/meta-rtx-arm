#! /bin/sh
#########################################################
#	eraseuboot.sh			2022/04/07	#
#########################################################
CMDLINE=`cat /proc/cmdline`
BLOCK_TMP=${CMDLINE#*root=/dev/mmcblk}
BLOCK=${BLOCK_TMP%%p*}
DEVNODE_NAME="mmcblk${BLOCK}"
DEVNODE="/dev/${DEVNODE_NAME}"

#### Debug Port ####
CONSOLE_TMP=${CMDLINE#*console=}
CONSOLE=${CONSOLE_TMP%%,*}
DEBUG_PORT=/dev/${CONSOLE}

BSPVER=`cat /etc/version 2>/dev/null`

print()
{
    echo $1 > /dev/tty1
    echo $1 > ${DEBUG_PORT}
}

erase()
{
	echo "0" > /sys/block/${DEVNODE_NAME}boot0/force_ro
	sleep 1
	dd if=/dev/zero of=${DEVNODE}boot0 bs=1024 seek=33 count=1
	sleep 1
	echo "1" > /sys/block/${DEVNODE_NAME}boot0/force_ro
}

if [ X$BSPVER = X ]; then
	print "BSP Version Error!"
	exit 0
fi

print "Do you want erase EMMC? (yes/No)"
read CHECKYES
if [ "$CHECKYES" = yes ]; then
	print "Enter BSP version..."
	read CHECKVER
	if [ "$CHECKVER" = $BSPVER ]; then
		print "EMMC erase..."
		erase
		sync
		print "EMMC erase...OK and reboot BSP."
	else
		print "BSP Version Error!"
		exit 0
	fi
else
	exit 0
fi
