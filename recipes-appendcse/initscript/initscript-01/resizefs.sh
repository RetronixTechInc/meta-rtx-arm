#! /bin/sh
#########################################################
#	resize.sh			2023/11/13	#
#########################################################
DEVNODE_NAME="mmcblk2"
DEVNODE="/dev/${DEVNODE_NAME}"

#### Debug Port ####
#DEBUG_PORT="/dev/ttymxc1"
CMDLINE=`cat /proc/cmdline`
CONSOLE_TMP=${CMDLINE#*console=}
CONSOLE=${CONSOLE_TMP%%,*}
DEBUG_PORT=/dev/${CONSOLE}

#### Disk Total size ####
let LIMIT_SIZE=3620*1000*1000

SD_PARTITION_FILE=/tmp/.SD_PARTITION

#### Partition information ####
PARTITIONS="0:s:64 1:-:64 2:d:- 2:p:1200 3:p:2000"

#### Partition formate ####
PARTITIONS_FS_3="3:ext4:DATA:-F"
PARTITIONS_FS="PARTITIONS_FS_1 PARTITIONS_FS_2 PARTITIONS_FS_3 PARTITIONS_FS_4 PARTITIONS_FS_5 PARTITIONS_FS_6 PARTITIONS_FS_7 PARTITIONS_FS_8 PARTITIONS_FS_9"

print()
{
    echo $1 > /dev/tty1
    echo $1 > ${DEBUG_PORT}
}

mk_fdisk() {
    TOTAL_SIZE=`fdisk -l ${DEVNODE} | grep 'Disk /dev/' | awk '{print $5}'`

    if [ `fdisk -l ${DEVNODE} | grep 'Units = cylinders' | wc -l` == 1 ]
    then
        UNITS_SIZE=`fdisk -l ${DEVNODE} | grep 'Units = cylinders' | awk '{print $9}'`
    else
        UNITS_SIZE=`fdisk -l ${DEVNODE} | grep 'Units: sectors' | awk '{print $8}'`
    fi

    if [ $TOTAL_SIZE -lt ${LIMIT_SIZE} ]
    then
        print "Target ${DEVNODE} size too small"
        exit 1
    fi
    print "Total Size:${TOTAL_SIZE} Unit Size:${UNITS_SIZE}"

    rm -f $SD_PARTITION_FILE
    touch $SD_PARTITION_FILE

    COUNT=0
    S_SECTOR=0
    E_SECTOR=0
    EXT_S_SECTOR=0
    EXT_E_SECTOR=0
    EXT_T_SECTOR=0
    let T_SECTOR=LIMIT_SIZE/UNITS_SIZE

    for P_INFO in ${PARTITIONS}
    do
        P_NUM=${P_INFO:0:1}
        P_TYPE=${P_INFO:2:1}
        P_SIZE=${P_INFO:4}
        if [ ${P_NUM} -le 4 ]
        then
            if [ ${COUNT} -eq ${P_NUM} ]
            then
                if [ ${P_NUM} -eq 0 ]
                then
                    let S_SECTOR=P_SIZE*1024*1024/UNITS_SIZE
                    print "MBR : ${P_SIZE} MB -> Sectors : ${S_SECTOR}"
                else
                    if [ ${P_SIZE} = "-" ]
                    then
                        if [ ${P_TYPE} = "p" ] || [ ${P_TYPE} = "n" ]
                        then
                            print "P${P_NUM} : ${P_SIZE} MB -> Start Sector : ${S_SECTOR} , End Sector : ${T_SECTOR}"

                            echo "		n" >> $SD_PARTITION_FILE
                            if [ ${P_TYPE} = "p" ]
                            then
                                echo "		p" >> $SD_PARTITION_FILE
                            else
                                echo "		e" >> $SD_PARTITION_FILE
                                let EXT_S_SECTOR=S_SECTOR
                                let EXT_T_SECTOR=T_SECTOR
                            fi
                            if [ ${P_NUM} -ne 4 ]
                            then
                                echo "		${P_NUM}" >> $SD_PARTITION_FILE
                            fi
                            echo "		${S_SECTOR}" >> $SD_PARTITION_FILE
                            echo "		${T_SECTOR}" >> $SD_PARTITION_FILE
                            echo "" >> $SD_PARTITION_FILE
                        fi
                        if [ ${P_TYPE} = "d" ]
                        then
                            echo "		d" >> $SD_PARTITION_FILE
                            echo "		${P_NUM}" >> $SD_PARTITION_FILE
                            let COUNT=COUNT-1
                        fi
                    else
                        let E_SECTOR=S_SECTOR+P_SIZE*1024*1024/UNITS_SIZE-1
                        if [ ${P_TYPE} = "p" ] || [ ${P_TYPE} = "n" ]
                        then
                            print "P${P_NUM} : ${P_SIZE} MB -> Start Sector : ${S_SECTOR} , End Sector : ${E_SECTOR}"

                            echo "		n" >> $SD_PARTITION_FILE
                            if [ ${P_TYPE} = "p" ]
                            then
                                echo "		p" >> $SD_PARTITION_FILE
                            else
                                echo "		e" >> $SD_PARTITION_FILE
                                let EXT_S_SECTOR=S_SECTOR
                                let EXT_T_SECTOR=E_SECTOR
                            fi
                            if [ ${P_NUM} -ne 4 ]
                            then
                                echo "		${P_NUM}" >> $SD_PARTITION_FILE
                            fi
                            echo "		${S_SECTOR}" >> $SD_PARTITION_FILE
                            echo "		${E_SECTOR}" >> $SD_PARTITION_FILE
                            echo "" >> $SD_PARTITION_FILE
                        fi
                        let S_SECTOR=E_SECTOR+1
                    fi
                fi
            else
                print "[Error] Partitions information was incorrect"
                exit 1
            fi
        else
            if [ ${EXT_SECTOR} -eq 0 ]
            then
                print "[Error] No extern partition"
            else
                if [ ${P_SIZE} = "-" ]
                then
                    print "P${P_NUM} : ${P_SIZE} MB -> Start Sector : ${EXT_S_SECTOR} , End Sector : ${EXT_T_SECTOR}"
                    echo "		n" >> $SD_PARTITION_FILE
                    echo "		${EXT_S_SECTOR}" >> $SD_PARTITION_FILE
                    echo "		${EXT_T_SECTOR}" >> $SD_PARTITION_FILE
                    echo "" >> $SD_PARTITION_FILE
                else
                    let EXT_E_SECTOR=EXT_S_SECTOR+P_SIZE*1024*1024/UNITS_SIZE-1
                    print "P${P_NUM} : ${P_SIZE} MB -> Start Sector : ${EXT_S_SECTOR} , End Sector : ${EXT_E_SECTOR}"
                    echo "		n" >> $SD_PARTITION_FILE
                    echo "		${EXT_S_SECTOR}" >> $SD_PARTITION_FILE
                    echo "		${EXT_E_SECTOR}" >> $SD_PARTITION_FILE
                    echo "" >> $SD_PARTITION_FILE
                    let EXT_S_SECTOR=EXT_E_SECTOR+1
                fi
            fi
        fi
        let COUNT=COUNT+1
    done

    # - Finish write-------------------------
    echo "		w" >> $SD_PARTITION_FILE
    echo "" >> $SD_PARTITION_FILE

    # - run fdisk ---------------------------
    print "run fdisk"
    fdisk ${DEVNODE} < $SD_PARTITION_FILE
    sync
    sleep 1
}

partitions_formate()
{
    print "Format partition"

    for PARTITION_FS_TMP in ${PARTITIONS_FS}
    do
        eval PARTITION_FS=\$${PARTITION_FS_TMP}

        if [ "${PARTITION_FS}" == "" ]
        then
            continue ;
        fi

        P_NUM=`echo ${PARTITION_FS} | awk -F':' '{print $1}'`
        P_TYPE=`echo ${PARTITION_FS} | awk -F':' '{print $2}'`
        P_LABEL=`echo ${PARTITION_FS} | awk -F':' '{print $3}'`
        P_ARGS=`echo ${PARTITION_FS} | awk -F':' '{print $4}'`

        print "mkfs.${P_TYPE} ${DEVNODE}p${P_NUM} -L/-n ${P_LABEL} ${P_ARGS}"

        if [ -b ${DEVNODE}p${P_NUM} ]
        then
            case ${P_TYPE} in
                "ext2")
                    if [ "${P_ARGS}" == "" ]
                    then
                        mkfs.ext2 ${DEVNODE}p${P_NUM} -L ${P_LABEL}
                    else
                        mkfs.ext2 ${DEVNODE}p${P_NUM} -L ${P_LABEL} ${P_ARGS}
                    fi
                    mount ${DEVNODE}p${P_NUM} /mnt
                    rm -rf /mnt/*
                    umount /mnt
                    ;;
                "ext3")
                    if [ "${P_ARGS}" == "" ]
                    then
                        mkfs.ext3 ${DEVNODE}p${P_NUM} -L ${P_LABEL}
                    else
                        mkfs.ext3 ${DEVNODE}p${P_NUM} -L ${P_LABEL} ${P_ARGS}
                    fi
                    mount ${DEVNODE}p${P_NUM} /mnt
                    rm -rf /mnt/*
                    umount /mnt
                    ;;
                "ext4")
                    if [ "${P_ARGS}" == "" ]
                    then
                        mkfs.ext4 ${DEVNODE}p${P_NUM} -L ${P_LABEL}
                    else
                        mkfs.ext4 ${DEVNODE}p${P_NUM} -L ${P_LABEL} ${P_ARGS}
                    fi
                    mount ${DEVNODE}p${P_NUM} /mnt
                    rm -rf /mnt/*
                    umount /mnt
                    ;;
                "vfat")
                    if [ "${P_ARGS}" == "" ]
                    then
                        mkfs.vfat ${DEVNODE}p${P_NUM} -n ${P_LABEL}
                    else
                        mkfs.vfat ${DEVNODE}p${P_NUM} -n ${P_LABEL} ${P_ARGS}
                    fi
                    mount ${DEVNODE}p${P_NUM} /mnt
                    rm -rf /mnt/*
                    umount /mnt
                    ;;
                *)
                    print "Unknow cmd"
                    exit 1
                    ;;
            esac
        else
            print "${DEVNODE}p${P_NUM} was not exist."
            exit 1
        fi
    done

    sync
    sleep 1
}

ROOTFS_PERCENT=`df | grep '/dev/root' | awk '{print $5}' | tr -d '%'`
if [ $ROOTFS_PERCENT -gt 97 ]
then
    mk_fdisk

    resize2fs ${DEVNODE}p2

    partitions_formate

    sync
fi
