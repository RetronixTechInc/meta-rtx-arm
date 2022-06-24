#!/bin/sh

logger "starting initscript"

# do some work here. Mount rootfs as rw if needed.
ROOTFS_PERCENT=`df | grep '/dev/root' | awk '{print $5}' | tr -d '%'`
if [ -f /usr/sbin/resizefs.sh ] && [ $ROOTFS_PERCENT -gt 97 ]
then
    /usr/sbin/resizefs.sh

    ROOTFS_SIZE=`df | grep '/dev/root' | awk '{print $2}'`
    if [ $ROOTFS_SIZE -eq 3055368 ]
    then
        rm /usr/sbin/resizefs.sh
    fi
fi


logger "initscript work done"

echo ds2482 0x18 > /sys/bus/i2c/devices/i2c-2/new_device
