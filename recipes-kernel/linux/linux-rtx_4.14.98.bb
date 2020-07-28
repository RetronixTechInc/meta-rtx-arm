# Copyright (C) 2013-2016 Freescale Semiconductor
# Copyright 2017-2018 NXP
# Released under the MIT license (see COPYING.MIT for the terms)

SUMMARY = "Linux Kernel provided and supported by NXP"
DESCRIPTION = "Linux Kernel provided and supported by NXP with focus on \
i.MX Family Reference Boards. It includes support for many IPs such as GPU, VPU and IPU."

require recipes-kernel/linux/linux-imx.inc
require recipes-kernel/linux/linux-imx-src-${PV}.inc

DEPENDS += "lzop-native bc-native"

DEFAULT_PREFERENCE = "1"

SRCBRANCH = "RTX_4.14.98_2.0.0"
SRC_URI = "git://github.com/RetronixTechInc/linux-rtx.git;protocol=https;branch=${SRCBRANCH}"
SRCREV = "9da2f922c5a39316827d58201e4d9ec85ba7a7e7"

#SRCBRANCH = "imx_4.14.98_2.0.0_ga"
#SRC_URI = "git://git@192.168.11.5/~/Disk/project_kernel/kernel-alston.git;protocol=ssh;branch=${SRCBRANCH}"
#SRCREV = "68ea20f8c98664a6fc64141a2cbe988d0e58fefe"

# SRC_URI += "file://defconfig "
SCMVERSION = ""
LOCALVERSION = ""

DO_CONFIG_V7_COPY = "no"
DO_CONFIG_V7_COPY_mx6 = "yes"
DO_CONFIG_V7_COPY_mx7 = "yes"
DO_CONFIG_V7_COPY_mx8 = "no"

addtask copy_defconfig after do_unpack before do_preconfigure
do_copy_defconfig () {
    install -d ${B}
    if [ ${DO_CONFIG_V7_COPY} = "yes" ]; then
        # copy latest imx_v7_defconfig to use for mx6, mx6ul and mx7
        mkdir -p ${B}
        cp ${S}/arch/arm/configs/imx_v7_defconfig ${B}/.config
        cp ${S}/arch/arm/configs/imx_v7_defconfig ${B}/../defconfig
    else
        # copy latest defconfig to use for mx8
        mkdir -p ${B}
    	if [ -n "${KERNEL_CONFIG}" ]; then
        	cp ${S}/rtx/configs/${KERNEL_CONFIG} ${B}/.config
	        cp ${S}/rtx/configs/${KERNEL_CONFIG} ${B}/../defconfig
	else
        	cp ${S}/rtx/configs/rtx-imx8mm-cse_defconfig ${B}/.config
	        cp ${S}/rtx/configs/rtx-imx8mm-cse_defconfig ${B}/../defconfig
	fi
    fi
}

COMPATIBLE_MACHINE = "(mx6|mx7|mx8)"
